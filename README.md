# Vulnerable Java Web Application

This repository provides a simple and self-contained Java web application with security flaws common to mid-00's legacy projects that have not been updated.

The application uses Spring Boot and an embedded H2 database that resets every time it starts. If you break it just restart and everything will be reset.

The application will run on **HTTPS port 9000**. If this port is not available you will need to change the `application.properties` file on the source folder with the new one. (if you are using docker you just need to map the container's 9000 port to another port in the host).

## Running the application manually

If you have a Java 8 + Maven 3.x development environment, just import the project on your IDE and run the class `com.github.rafaelrpinto.vulnerablejavawebapp.config.AppLauncher`.

## Running with docker

If your workstation is not configured for Java 8 development the easiest way to run the application is with Docker.

```bash
# gets the code
git clone https://github.com/rafaelrpinto/VulnerableJavaWebApplication
cd VulnerableJavaWebApplication

# creates the docker image
docker build -t vulnerable-java-application:0.1 .

# creates/starts the container
docker run --name vulnerable-java-application -p 9000:9000 -d vulnerable-java-application:0.1
```
## Testing the application with Arachni and ModSecurity

The [ARACHNI.MD](https://github.com/rafaelrpinto/VulnerableJavaWebApplication/blob/master/ARACHNI.MD) file has an example of default security scan with automatic login and other tweaks enabled. For more scanning options check https://github.com/Arachni/arachni.

The ModSecurity scripts to protect this application are available [in my other repository](https://github.com/rafaelrpinto/ModSecurityScripts).

The article that I wrote explaining how to use Arachni to check this application [is available on linkedin](https://www.linkedin.com/pulse/identifying-security-flaws-legacy-web-applications-arachni-pinto). 

If is there any problem running the application or you want to add more security flaws, feel free to open an issue or send a pull request.
