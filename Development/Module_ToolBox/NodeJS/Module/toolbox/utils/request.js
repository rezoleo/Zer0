/* 
 * File 	: ./utils/request.js
 * Author(s)	: Zidmann
 * Function 	: This file defines some functions to make HTTP requests to a server and to manage the error response
 * Version  	: 1.0.0
 */

var request = require('request'),
    Err     = require('./err.js'),
    isEmpty = require('./isEmpty.js');


function generate_auxi_function(callback){
	return function(err, response, body){
		if(isEmpty(err)){
			var obj = null;

			if(!isEmpty(body) && body!=""){
				try{
					obj = JSON.parse(body);
				}
				catch(err){
					obj = null;
				}
			}

			if (response.statusCode === 200) {
				callback(null, obj);
				return;
			}
			else{
				var status  = (!isEmpty(obj.status))  ? obj.status  : null;
				var code    = (!isEmpty(obj.code))    ? obj.code    : null;
				var message = (!isEmpty(obj.message)) ? obj.message : null;

				var error = new Err(status, code, message, body);
				callback(error, null);
				return;
			}
			return;
		}
		else{
			callback(err, null);
			return;
		}
	}
}


// Functions to send an HTTP request
function sendRequestFunc(options, callback){
	var auxi_function=generate_auxi_function(callback);
	request(options, auxi_function);
}


module.exports = sendRequestFunc;
