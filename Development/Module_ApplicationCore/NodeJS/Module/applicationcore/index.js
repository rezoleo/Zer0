/* 
 * File 	: ./index.js
 * Author(s)	: Zidmann
 * Function 	: This file enables to choose which server to use for an application (HTTP or HTTPS)
 * Note		: str - refers to the chosen type of server
 * Version  	: 1.0.0
 */

var isEmpty 	    = require('toolbox')('ISEMPTY'),
    default_require = './server/https_server.js';

function dynamicExport(str){
	if(isEmpty(str)){
		return require(default_require);
	}
	switch (str.toUpperCase()){
		case 'HTTP':
			return require('./server/http_server.js');
		case 'HTTPS':
			return require('./server/https_server.js');
		default:
			return require(default_require);
	}
	return require(default_require);
}


module.exports = dynamicExport;
