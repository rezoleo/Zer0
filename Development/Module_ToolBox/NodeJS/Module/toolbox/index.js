/* 
 * File 	: ./index.js
 * Author(s)	: Zidmann
 * Function 	: This file enables to choose which functionality to use
 * Note		: str - refers to the chosen functionality
 * Version  	: 1.0.0
 */

var isEmpty = require('./utils/isEmpty.js');

function dynamicExport(str){
	if(isEmpty(str)){
		return null;
	}
	switch (str.toUpperCase()){
		case "CHECKATTRIBUTE":
			return require('./utils/checkAttribute.js');
		case "ERR":
			return require('./utils/err.js');
		case "ERROR_REFERENTIAL":
			return require('./utils/errorReferential.js');
		case "HASH":
			return require('./utils/hash.js');
		case "ISARRAY":
			return require('./utils/isArray.js');
		case "ISEMPTY":
			return require('./utils/isEmpty.js');
		case "ISSTRING":
			return require('./utils/isString.js');
		case "REQUEST":
			return require('./utils/request.js');
		case "SESSION_INFO":
			return require('./utils/session_info.js');
		case "TOKEN":
			return require('./utils/token.js');
		case "WSCLIENT":
			return require('./utils/ws_client.js');
		default:
			return null;
	}
	return null;
}

module.exports = dynamicExport;
