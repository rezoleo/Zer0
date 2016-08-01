/* 
 * File 	: ./index.js
 * Author(s)	: Zidmann
 * Function 	: This file loads the cache functions
 * Version  	: 1.0.0
 */

var isEmpty = require('toolbox')('ISEMPTY');

function dynamicExport(str){
	if(isEmpty(str)){
		return null;
	}
	switch (str.toUpperCase()){
		case "INFOS":
			return require('./utils/cache-infos.js');
		default:
			return null;
	}
	return null;
}

module.exports = dynamicExport;
