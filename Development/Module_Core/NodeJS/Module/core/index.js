/* 
 * File 	: ./index.js
 * Author(s)	: Zidmann
 * Function 	: This file enables to choose which functionality to use for 'WebServiceCore' or 'ApplicationCore' module.
 * Note		: str - refers to the chosen type of server
 * Version  	: 1.0.0
 */

var isEmpty = require('toolbox')('ISEMPTY');


function dynamicExport(str){
	if(isEmpty(str)){
		return null;
	}
	switch (str.toUpperCase()){
		case 'APPLICATION_ERROR_HANDLER':
			return require('./server/application_error_handler.js');
		case 'CHECK':
			return require('./server/check.js');
		case 'CONNECT_MONGOOSE':
			return require('./server/connect_mongoose.js');
		case 'HEADER_HANDLER':
			return require('./server/header_handler.js');
		case 'HTTP_SERVER_BASICS':
			return require('./server/http_server_basics.js');
		case 'HTTPS_SERVER_BASICS':
			return require('./server/https_server_basics.js');
		case 'SERVICE_ERROR_HANDLER':
			return require('./server/service_error_handler.js');
		case 'WRITE_LOG':
			return require('./server/write_log.js');
		default:
			return null;
	}
	return null;
}

module.exports = dynamicExport;
