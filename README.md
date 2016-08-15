# Vulnerable Java Web Application
This repository provides a simple and self-contained Java web application with security flaws common to mid-00's legacy projects that have not been updated.

The application uses Spring Boot and an embedded H2 database that resets every time the application starts. So just import it on your favourite IDE, execute the AppLauncher class and attack it at will. If you break it just restart the application that everything will be reset.

The applicatin will run on **HTTPS por 8080**. If this port is not available you will need to create an 'application.properties' file on the source folder with the desired Spring Boot port configuration.

The [ARACHNI.MD](https://github.com/rafaelrpinto/VulnerableJavaWebApplication/blob/master/ARACHNI.MD) file has an example of default security scan with automatic login and other tweaks enabled. For more scanning options check https://github.com/Arachni/arachni.

The ModSecurity scripts to protect this application are available [in my other repository](https://github.com/rafaelrpinto/ModSecurityScripts).

The article that I wrote explaining how to use Arachni to check this application [is available on linkedin](https://www.linkedin.com/pulse/identifying-security-flaws-legacy-web-applications-arachni-pinto). 

If is there any problem running the application or you want to add more security flaws, feel free to open an issue or send a pull request.
