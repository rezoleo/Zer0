/* 
 * File 	: ./server/conf/distant.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the distant paths to call the service devised to test WebServiceCore module
 * Version  	: 1.0.0
 */


module.exports = {
	adress        : 'https://localhost:8000',
	ca            : 'certificates/server-cert.pem',
	cert          : 'certificates/server-cert.pem',
	key           : 'certificates/server-key.pem',
	normal_prefix : '/api/object/',
	secure_prefix : '/api/objectToken/',
	token         : 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJXZWJzZXJ2aWNlY29yZV9UZXN0aW5nIiwiZW5kX2RhdGUiOiIyMDE0LTExLTA3VDE5OjMzOjMwLjYzOFoiLCJyYW5kb20iOjAuMTc1ODAxMjcyMDM4MzcwMzcsImFjY2VzcyI6WyJHRVQiLCJHRVRpZCIsIlBPU1QiLCJQVVQiLCJERUxFVEUiXX0.H4a21rl9gTx7PlcfOVYYKxsd2ewfc1IPr3HldAq4xew'
}
