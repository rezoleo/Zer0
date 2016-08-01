/* 
 * File 	: ./tokens/token_provier.js
 * Author(s)	: Zidmann
 * Function 	: Script to describe a token to test the provider service
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
token.dest_service   = 'Service_Provider';
token.end_date       = new Date();
token.access         = ['GETAuth', 'POSTAuthLogin', 'GETCard', 'GETCardId', 'GETCardCode', 'GETPeople', 'GETPeopleId', 'GETPeopleLogin', 'GETPeopleMail', 'GETPicture'];

module.exports = {name	: 'token_provider',
		  token : token,
		  action: 'hash'};
