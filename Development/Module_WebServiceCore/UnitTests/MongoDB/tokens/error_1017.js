/* 
 * File 	: ./tokens/error_1017.js
 * Author(s)	: Zidmann
 * Function 	: Script to describe a token to test 1017 code error for webservicecore module
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
token.dest_service   = 'Webservicecore_Testing';
token.end_date       = new Date();
token.access         = ['GET', 'POST', 'PUT', 'DELETE'];

module.exports = {name	: 'error_1017',
		  token : token};
