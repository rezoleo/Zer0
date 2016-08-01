/* 
 * File 	: ./index.js
 * Author(s)	: Zidmann
 * Function 	: This file enables to choose which functionality to use
 * Note		: str - refers to the chosen functionality
 * Version  	: 1.0.0
 */

var isEmpty = require('toolbox')('ISEMPTY');

function dynamicExport(str){
	if(isEmpty(str)){
		return null;
	}
	switch (str.toUpperCase()){
		case "CRYPTOR":
			return require('./utils/cryptor.js');
		default:
			return null;
	}
	return null;
}

module.exports = dynamicExport;
