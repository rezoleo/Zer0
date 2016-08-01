/* 
 * File 	: ./server/models/ws_authentification.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model used to call Authentification Service
 * Version  	: 1.0.0
 */

var conf           = require('../conf/distant.js').authentification,
	findError      = require('../errors/referential.js'),
	checkAttribute = require('toolbox')('CHECKATTRIBUTE'),
    sha512         = require('toolbox')('HASH').sha512,
    isEmpty        = require('toolbox')('ISEMPTY'),
    ws_client      = require('toolbox')('WSCLIENT')({ server_ca   : conf.security.ca,
											          client_cert : conf.security.cert,
											          client_key  : conf.security.key});

function getOneFunc(data, callback){
	if(!checkAttribute(data.login)){
		return callback(findError("PROVIDER.A.1.1"));
	}

	var final_uri = conf.uri+'/'+data.login;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}

function checkFunc(data, callback){
	if(!checkAttribute(data.login)){
		return callback(findError("PROVIDER.A.2.1"));
	}

	var final_uri = conf.uri+'/'+data.login;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.post({ uri  : final_uri,
			 form : {password : data.password}}, callback);
}


module.exports={
	getOne : getOneFunc,
	check  : checkFunc
};
