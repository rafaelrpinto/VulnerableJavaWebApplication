package com.github.rafaelrpinto.vulnerablejavawebapp.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.rafaelrpinto.vulnerablejavawebapp.model.User;

/**
 * 
 * Simple filter to manually check if the user is logged on secure context
 * requests.
 * 
 * @author Rafael
 *
 */
public class SessionUserFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
					throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		User user = (User) request.getSession().getAttribute("sessionUser");
		if (user == null) {
			response.sendRedirect("/login");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
