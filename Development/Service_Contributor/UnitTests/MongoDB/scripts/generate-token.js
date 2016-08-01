/* 
 * File 	: ./mongo/generate-tokens.js
 * Author(s)	: Zidmann
 * Function 	: Script used to generate the differents tokens used for testing the contributor service with JUnit scripts.
 * Version  	: 1.0.0
 */

//Define all necessary requirements
var 	passhash  = require('password-hash'),

	mongo	  = require('../conf/mongo.js'),
	rest	  = require('../conf/rest.js'),

	generator = require('token_generator')('CRYPTOR');
	Token 	  = require('toolbox')('TOKEN');

//Definition of the structure which contain all the token
var	token_list	= [];

//Adding the tokens and their properties in the list of tokens
token_list.push(require('../tokens/token_contributor.js'));

//Generation of the list of tokens
generator(mongo, rest, token_list);
