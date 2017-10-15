/* 
 * File 	: ./server/conf/distant.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the distant paths to call the different webservices
 * Version  	: 1.1.0
 */

var security_alert ={ ca   : 'certificates/distant/server-cert-alert.pem',
		      cert : 'certificates/server-cert.pem',
		      key  : 'certificates/server-key.pem'},
security_auth =     { ca   : 'certificates/distant/server-cert-auth.pem',
		      cert : 'certificates/server-cert.pem',
		      key  : 'certificates/server-key.pem'},
security_card =     { ca   : 'certificates/distant/server-cert-card.pem',
		      cert : 'certificates/server-cert.pem',
		      key  : 'certificates/server-key.pem'},
security_contrib =  { ca   : 'certificates/distant/server-cert-contributor.pem',
		      cert : 'certificates/server-cert.pem',
		      key  : 'certificates/server-key.pem'},
security_group =    { ca   : 'certificates/distant/server-cert-group.pem',
		      cert : 'certificates/server-cert.pem',
		      key  : 'certificates/server-key.pem'},
security_people =   { ca   : 'certificates/distant/server-cert-people.pem',
		      cert : 'certificates/server-cert.pem',
		      key  : 'certificates/server-key.pem'},
security_picture =  { ca   : 'certificates/distant/server-cert-picture.pem',
		      cert : 'certificates/server-cert.pem',
		      key  : 'certificates/server-key.pem'};


module.exports = {
	alert            : { uri      : 'https://localhost:8122/api/alert',
			     security : security_alert,
			     token    : 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX0FsZXJ0IiwiZW5kX2RhdGUiOiIyMDE3LTExLTIzVDIxOjMxOjEwLjI2OFoiLCJhY2Nlc3MiOlsiR0VUIiwiR0VUT25lIiwiUE9TVCJdLCJleHRyYSI6bnVsbH0.p12rFsIGB1v_OpyjT1Ud38C7NjWhoq-z25YgemRPeec' },
	authentification : { uri      : 'https://localhost:8111/api/authentification',
			     salt     : 'ssd~jsd16fèzejzojié"çè)"èefgsfgo&jp^51fgsg3sqgrh"f',
			     security : security_auth,
			     token    : 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX0F1dGhlbnRpZmljYXRpb24iLCJlbmRfZGF0ZSI6IjIwMTUtMDItMTFUMjM6MDY6MzMuNDc3WiIsInJhbmRvbSI6MC4xMDI4OTgxMTA0MDI3NDc5OSwiYWNjZXNzIjpbIkdFVCIsIkdFVE9uZSIsIlBPU1QiLCJQT1NUbG9naW4iLCJQVVQiLCJERUxFVEUiXX0.0z9YGk62lczd_tl2AZD-VT85PBpPG3SAcxSDKxAnNpo' },
	card             : { uri      : 'https://localhost:8112/api/card',
			     security : security_card,
			     token    : 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX0NhcmQiLCJlbmRfZGF0ZSI6IjIwMTQtMTEtMTBUMjM6MDc6MzQuMzQwWiIsInJhbmRvbSI6MC41MzYwNzgwMzA5NDIwMDc5LCJhY2Nlc3MiOlsiR0VUIiwiR0VUaWQiLCJHRVRjYXJkIiwiUE9TVCIsIlBVVCIsIkRFTEVURSJdfQ.TN7Zm5AdtqD8tNDD2fvstjyJLxYhwXtEkCYjlaLy7X0' },
	contributor      : { uri      : 'https://localhost:8121/api/contributor',
			     security : security_contrib,
			     token    : 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX0NvbnRyaWJ1dG9yIiwiZW5kX2RhdGUiOiIyMDE1LTA1LTAyVDIwOjI1OjAyLjkwMFoiLCJhY2Nlc3MiOlsiR0VUIiwiR0VUaWQiLCJHRVRsb2dpbiIsIlBPU1QiLCJERUxFVEUiXSwiZXh0cmEiOm51bGx9.TmJBcAHDJBmLgzjoLd9a1C9G3khM8XHKVp_U95mfEU8' },
	group            : { uri      : 'https://localhost:8113/api/group',
			     security : security_group,
			     token    : 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX0dyb3VwIiwiZW5kX2RhdGUiOiIyMDE1LTA0LTE4VDE0OjI2OjAyLjY4N1oiLCJyYW5kb20iOjAuNTE5NDg5NDIzNjA0NjgyMSwiYWNjZXNzIjpbIkdFVCIsIkdFVGlkIiwiR0VUbmFtZSIsIkdFVHNlYXJjaCIsIlBPU1QiLCJQT1NUbWVtYmVyIiwiUE9TVHJlc3BvbnNhYmlsaXR5IiwiUFVUIiwiREVMRVRFIiwiREVMRVRFbWVtYmVyIiwiREVMRVRFcmVzcG9uc2FiaWxpdHkiXX0.4-3ZgABTbGNbr3rezqA10Z6CNYj278KG6Jab7HFGDxQ' },
	people           : { uri      : 'https://localhost:8114/api/people',
			     security : security_people,
			     token    : 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX1Blb3BsZSIsImVuZF9kYXRlIjoiMjAxNC0xMS0xOVQwMTozMTo0My45NzdaIiwicmFuZG9tIjowLjQxMzgyODU5NDE0NDQzMzc0LCJhY2Nlc3MiOlsiR0VUIiwiR0VUaWQiLCJHRVRsb2dpbiIsIkdFVG1haWwiLCJQT1NUIiwiUE9TVHRhZyIsIlBVVCIsIkRFTEVURSIsIkRFTEVURXRhZyJdfQ.T05Hxx4EPyifKg_yIKtkcU1VP5OsEZ8cg4Thv_Gq3qk' },
	picture          : { uri      : 'https://localhost:8115/api/file',
			     security : security_picture,
			     group_dir  : 'JunitTests',
			     people_dir : 'JunitTests',
			     token    : 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX1BpY3R1cmUiLCJlbmRfZGF0ZSI6IjIwMTYtMTItMDlUMjI6MzI6MDIuMjkyWiIsImFjY2VzcyI6WyJHRVQiLCJQT1NUIiwiREVMRVRFIl0sImV4dHJhIjp7InJlYWQiOlsiSnVuaXRUZXN0cyJdLCJhZGQiOlsiSnVuaXRUZXN0cyJdLCJkZWxldGUiOlsiSnVuaXRUZXN0cyJdfX0.2zsxJW4Ep529Vs0lu9T5dOUH_5eonn5aYpoT65mkL6s' }
}
