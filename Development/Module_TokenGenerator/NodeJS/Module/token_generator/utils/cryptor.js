/* 
 * File 	: ./utils/cryptor.js
 * Author(s)	: Zidmann
 * Function 	: This file generates and prints on the console some tokens to use them for modules or services using WebServiceCore
 * Note		: mongo  - refers to the configuration file which describes the MongoDB setting used to store information
 *		  rest   - refers to the configuration file which describes the REST setting on the webservice 
 *		  tokens - refers to the list of all the tokens to encrypt (and to hash)
 * Version  	: 1.0.0
 */

var 	isArray	 = require('toolbox')('ISARRAY'),
	isEmpty	 = require('toolbox')('ISEMPTY'),
	jwt	 = require('jwt-simple'),
	passhash = require('password-hash'),
	Token 	 = require('toolbox')('TOKEN')(jwt);


function cryptor_auxi(mongo, rest, elmt){
	if(isEmpty(mongo)      || isEmpty(rest) || isEmpty(elmt) ||
	   isEmpty(mongo.databasename) || 
	   isEmpty(rest.token) || isEmpty(rest.token.secretkey) || isEmpty(rest.hash)   ||
	   isEmpty(elmt.name)  || isEmpty(elmt.token) ||
	   isEmpty(mongo.databasename)){
		return;
	}

	var	name    = elmt.name,
		token   = elmt.token,
		encrypt = token.encrypt(rest.token.secretkey);

	console.log("***************************************");
	console.log(name+"="+encrypt);

	if(!isEmpty(elmt.action) && elmt.action.toUpperCase()=='HASH'){
		var hash    = passhash.generate(encrypt, rest.hash);

		console.log("To use this token in a project, follow these instructions");
		console.log("> mongo");
		console.log("> use "+mongo.databasename);
		console.log("> db.tokens.insert({\"source\" : \""+token.source_service+"\", \"hash\" : \""+hash+"\", \"created\" : \""+new Date()+"\"})");
		console.log("> quit()");			
	}
}

function cryptorFunc(mongo, rest, tokens){
	if(isEmpty(tokens)){
		return;
	}
	if(isArray(tokens)){
		for (var i = 0; i<tokens.length; i++) {
			cryptor_auxi(mongo, rest, tokens[i]);
		}
	}
	else{
		cryptor_auxi(mongo, rest, tokens);
	}
	console.log("***************************************");
}


module.exports = cryptorFunc;
