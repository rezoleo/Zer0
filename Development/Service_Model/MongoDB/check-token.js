//////////////////////////////////////////////////////////////////////////////////////////////////////////
// Script to generate a token used for the client of a service                                          //
// Auteur      : Zidmann (zidmann@gmail.com)                                                            //
// Date        : 05/10/2014                                                                             //
// Version     : 0.0.1                                                                                  //
//////////////////////////////////////////////////////////////////////////////////////////////////////////

//Define all necessary requirements
var 	passhash = require('password-hash'),

	conf	= require('../server/conf/rest.js'),
	Token 	= require('../server/lib/webservice_model/server/models/token.js'),
	isNull  = require('../server/utils/isNull.js');


//Encryption of the token to find the token string
var tokenString	= "";
var hash	= "";

console.log("***************************************");
console.log("**      Token Checker (by Zidmann)   **");
console.log("***************************************");
console.log("Manuel : ");
console.log("Step 1 - Edit this script to change the token string and hash definition");
console.log("Step 2 - Run the script by entering :");
console.log("> nodejs check-token.js");
console.log("***************************************");
console.log("tokenString=")
console.log(tokenString);
console.log("***************************************");
console.log("token=");
console.log(hash);
console.log("***************************************");
console.log("check=");
console.log(passhash.verify(tokenString, hash));
console.log("***************************************");

