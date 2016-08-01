/* 
 * File 	: ./server/conf/distant.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the distant paths to call the different webservices
 * Version  	: 1.0.0
 */

var security_auth= { ca   : 'certificates/distant/server-cert-auth.pem',
		     cert : 'certificates/server-cert.pem',
		     key  : 'certificates/server-key.pem'},
security_mail    = { ca   : 'certificates/distant/server-cert-mail.pem',
		     cert : 'certificates/server-cert.pem',
		     key  : 'certificates/server-key.pem'};


module.exports = {
	authentification : { uri      : 'https://localhost:8111/api/authentification',
			     salt     : 'ssd~jsd16fèzejzojié"çè)"èefgsfgo&jp^51fgsg3sqgrh"f',
			     security : security_auth,
			     token    : 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX0F1dGhlbnRpZmljYXRpb24iLCJlbmRfZGF0ZSI6IjIwMTUtMDItMTFUMjM6MDY6MzMuNDc3WiIsInJhbmRvbSI6MC4xMDI4OTgxMTA0MDI3NDc5OSwiYWNjZXNzIjpbIkdFVCIsIkdFVE9uZSIsIlBPU1QiLCJQT1NUbG9naW4iLCJQVVQiLCJERUxFVEUiXX0.0z9YGk62lczd_tl2AZD-VT85PBpPG3SAcxSDKxAnNpo' },
	mail             : { uri      : 'https://localhost:8011/api/mail',
			     security : security_mail,
			     tokenLink:  'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6IlNlcnZpY2VfUGFzc3dvcmRSZXNldF8xIiwiZGVzdF9zZXJ2aWNlIjoiU2VydmljZV9NYWlsIiwiZW5kX2RhdGUiOiIyMDE1LTA2LTAxVDA1OjMyOjA3LjAzN1oiLCJhY2Nlc3MiOlsiUE9TVG1haWwiLCIiXSwiZXh0cmEiOnsiZnJvbSI6InBhc3N3b3JkLXJlc2V0QGVjLWxpbGxlLmZyIiwic3ViamVjdCI6IlLDqWluaXRpYWxpc2F0aW9uIG1vdCBkZSBwYXNzZSJ9fQ.4nmFoZ3aBsqke2U2X0tSPHZDELkQ5-NZajMyiBidAeY',
			     tokenConfirm:  'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6IlNlcnZpY2VfUGFzc3dvcmRSZXNldF8yIiwiZGVzdF9zZXJ2aWNlIjoiU2VydmljZV9NYWlsIiwiZW5kX2RhdGUiOiIyMDE1LTA2LTAxVDA1OjM1OjA0LjUxMloiLCJhY2Nlc3MiOlsiUE9TVG1haWwiLCIiXSwiZXh0cmEiOnsiZnJvbSI6InBhc3N3b3JkLXJlc2V0QGVjLWxpbGxlLmZyIiwic3ViamVjdCI6IkNoYW5nZW1lbnQgZGUgbW90IGRlIHBhc3NlIGVmZmVjdHXDqSJ9fQ.NKXePqISnv9i9nWejdKEd1USDi3Q6-HUuj7JPARGfcI',
 }
}
