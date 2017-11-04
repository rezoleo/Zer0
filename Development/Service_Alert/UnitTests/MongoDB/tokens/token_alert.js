/* 
 * File 	: ./tokens/token_alert.js
 * Author(s)	: Zidmann
 * Function 	: Script to describe a token to test the alert service
 * Version  	: 1.0.0
 */

//Define all necessary requirements
var 	jwt	 = require('jwt-simple'),
	Token 	 = require('toolbox')('TOKEN')(jwt);


//Definition of the token and all its properties
var token    = new Token();

//HERE - ADAPT THE DEFINITION IN FUNCTION OF YOUR NEEDS
token.source_ip      = '127.0.0.1';
token.source_service = 'JunitTests';
token.dest_service   = 'Service_Alert';
token.end_date       = new Date();
token.access         = ['GET', 'GETid', 'POST'];

module.exports = {name	: 'token_alert',
		  token : token,
		  action: 'hash'};
