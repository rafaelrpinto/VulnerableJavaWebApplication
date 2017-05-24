## Scanning the application with Arachni Scanner

The application is running on the **HTTPS port 9000**.

The command line initiates the scan with the following configurations:

1. Logs in as 'User 1'
2. Ignores some checks to improve the scan performance
3. Replaces the arachni User-Agent with a recent Chrome one
4. Saves the result adding the scan date to the file name

`./arachni --checks=*,-code_injection_php_input_wrapper,-ldap_injection,-no_sql*,-backup_files,-backup_directories,-captcha,-cvs_svn_users,-credit_card,-ssn,-localstart_asp,-webdav --plugin=autologin:url=https://$(hostname):9000/login,parameters='login=user1@user1.com&password=abcd1234',check='Hi User 1|Logout' --scope-exclude-pattern='logout' --scope-exclude-pattern='resources' --session-check-pattern='Hi User 1' --session-check-url=https://$(hostname):9000 --http-user-agent='Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36' --report-save-path=../scan_report_$(date -d "today" +"%Y%m%d%H%M").afr https://$(hostname):9000`

## Processing the result report to a zip file containing HTML pages (replace _X for the generated timestamp)
`./arachni_reporter ../scan_report_X.afr --reporter=html:outfile=../scan_report.html_X.zip`

 For more scanning options check https://github.com/Arachni/arachni.

The article that I wrote explaining how to use Arachni to check this application [is available on linkedin](https://www.linkedin.com/pulse/identifying-security-flaws-legacy-web-applications-arachni-pinto). 
