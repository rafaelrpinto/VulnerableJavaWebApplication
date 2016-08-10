# ArachniJavaTest
This repository provides a simple and self-contained Java web application with security flaws common to mid-2000's legacy projects that have not been updated.

The application uses Spring Boot and an embedded H2 database that resets every time the application starts. So just import it on your favourite IDE, execute the AppLauncher class and attack it at will. If you break it just restart the application that everything will be reset.

The 'arachni_commands.txt' file has an example of default security scan with automatic login and other tweaks enabled. For more scanning options check https://github.com/Arachni/arachni.
