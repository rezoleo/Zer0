/* 
 * File 	: ./tokens/token_people.js
 * Author(s)	: Zidmann
 * Function 	: Script to describe a token to test the people service
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
token.dest_service   = 'Service_People';
token.end_date       = new Date();
token.access         = ['GET', 'GETid', 'GETlogin', 'GETmail', 'POST', 'POSTtag', 'PUT', 'DELETE', 'DELETEtag'];

module.exports = {name	: 'token_people',
		  token : token,
		  action: 'hash'};
