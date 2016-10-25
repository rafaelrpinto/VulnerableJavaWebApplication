package com.github.rafaelrpinto.vulnerablejavawebapp.config;

import java.util.Collections;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.sql.DataSource;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Class that configures and launches the application.
 * 
 * @author Rafael
 *
 */
@ComponentScan(basePackages = { "com.github.rafaelrpinto.vulnerablejavawebapp.controller",
		"com.github.rafaelrpinto.vulnerablejavawebapp.repository" })
@EnableAutoConfiguration
public class AppLauncher {

	@Autowired
	private DataSource dataSource;

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		return builder.setType(EmbeddedDatabaseType.H2).addScript("db/schema.sql").addScript("db/data.sql").build();
	}

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public FilterRegistrationBean someFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(sessionUserFilter());
		registration.addUrlPatterns("/secure/*");
		registration.setName("sessionUserFilter");
		registration.setOrder(1);
		return registration;
	}

	@Bean(name = "sessionUserFilter")
	public Filter sessionUserFilter() {
		return new SessionUserFilter();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AppLauncher.class, args);
	}

	// The following configurations are usally on the application servers and
	// not in the code
	// but since we are using spring boot to make things simple we configure via
	// code
	@SuppressWarnings("deprecation")
	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return new ServletContextInitializer() {
			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
				SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
				sessionCookieConfig.setHttpOnly(true);
			}
		};

	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		// https config
		final String keystorePass = "abcd1234";
		final String keystoreType = "PKCS12";
		final String keystoreProvider = "SunJSSE";
		final String keystoreAlias = "tomcat";
		final String keystoreAbsolutePath = getClass().getClassLoader().getResource("ssl/keystore.p12").getFile();

		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		factory.addConnectorCustomizers((TomcatConnectorCustomizer) (Connector con) -> {
			con.setScheme("https");
			con.setSecure(true);
			Http11NioProtocol proto = (Http11NioProtocol) con.getProtocolHandler();
			proto.setSSLEnabled(true);
			proto.setKeystoreFile(keystoreAbsolutePath);
			proto.setKeystorePass(keystorePass);
			proto.setKeystoreType(keystoreType);
			proto.setProperty("keystoreProvider", keystoreProvider);
			proto.setKeyAlias(keystoreAlias);
		});

		return factory;
	}
}
