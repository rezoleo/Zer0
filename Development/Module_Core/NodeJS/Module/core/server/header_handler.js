/* 
 * File 	: ./server/header_handler.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the function to handle header of HTTP server (or HTTPS server)
 * Version  	: 1.0.0
 */

var isEmpty = require('toolbox')('ISEMPTY');


function headerHandlerFunc(req, res, options, next){
	if(!isEmpty(options.allowed_origin)){
		res.header('Access-Control-Allow-Origin', options.allowed_origin);
	}
	res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE');
	res.header('Access-Control-Allow-Headers', 'Content-Type');
	if (req.method === 'OPTIONS'){
		return res.end();
	}
	next();
}


module.exports = headerHandlerFunc;
