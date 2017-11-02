/* 
 * File 	: ./server/application_error_handler.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the function to handle error message for an application
 * Version  	: 1.0.0
 */

var isEmpty = require('toolbox')('ISEMPTY'),
    util    = require('util');


function errorHandlerFunc(req, res, err, options){
	var error = (err instanceof Error) ? err : new Error(err);

	error.message = (!isEmpty(error.message)) ? error.message.toString()  : null;
	error.code    = (!isEmpty(error.code))    ? error.code                : "0";
	error.stack   = (!isEmpty(error.stack))   ? util.inspect(error.stack) : null;
	error.status  = (!isEmpty(error.status))  ? error.status              : 404;

	res.statusCode = error.status;

	var msg = { application : options.name,
		    version     : options.version,
		    code        : error.code,
		    message     : error.message,
		    stack       : error.stack,
		    status      : error.status };

	if(!isEmpty(options.interceptors)
	 &&!isEmpty(options.interceptors.error)){
		options.interceptors.error(msg);
	}

	res.json(msg);
}


module.exports = errorHandlerFunc;
