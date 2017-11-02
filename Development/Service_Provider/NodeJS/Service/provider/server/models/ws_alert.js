/* 
 * File 	: ./server/models/ws_alert.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model used to call Alert Service
 * Version  	: 1.0.0
 */

var conf      = require('../conf/distant.js').alert,
    isEmpty   = require('toolbox')('ISEMPTY'),
    ws_client = require('toolbox')('WSCLIENT')({ server_ca   : conf.security.ca,
				                 client_cert : conf.security.cert,
				                 client_key  : conf.security.key});


function postFunc(data, callback){
	var final_uri = conf.uri+'/';
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.post({  uri  : final_uri,
			  form : { message : data.message,
				   level   : data.level }}, callback);
}


module.exports={
	post : postFunc,
};
