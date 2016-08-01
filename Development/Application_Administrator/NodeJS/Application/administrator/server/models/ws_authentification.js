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


function getFunc(callback){
	var final_uri = conf.uri+'/';
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}

function getOneFunc(data, callback){
	if(!checkAttribute(data.login)){
		return callback(findError("ADMIN.A.1.1"));
	}

	var final_uri = conf.uri+'/'+data.login;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}

function checkFunc(data, callback){
	if(!checkAttribute(data.login)){
		return callback(findError("ADMIN.A.2.1"));
	}

	var final_uri = conf.uri+'/'+data.login;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}

	var hash = sha512(data.password, conf.salt);
	hash = sha512(hash, "");
	ws_client.post({ uri  : final_uri,
			 form : {password : hash}}, callback);
}

function signupFunc(data, callback){
	var final_uri = conf.uri+'/';
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}

	var hash = sha512(data.password, conf.salt);
	hash = sha512(hash, "");
	ws_client.post({ uri  : final_uri,
			 form : { login    : data.login,
			     	  mail     : data.mail,
			     	  password : hash,
				  status   : data.status,
			     	  creator  : data.creator }}, callback);
}

function updateFunc(data, callback){
	if(!checkAttribute(data.login)){
		return callback(findError("ADMIN.A.3.1"));
	}

	var final_uri = conf.uri+'/'+data.login;
	var symbol = '?';
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
		symbol = '&';
	}

	if(!isEmpty(data.password)){
		var hash = sha512(data.password, conf.salt);
		hash = sha512(hash, "");
		ws_client.put({ uri  : final_uri,
				form : { login    : data.login,
				     	 mail     : data.mail,
				     	 password : hash,
					 status   : data.status,
				     	 updator  : data.updator }}, callback);
	}
	else{
		ws_client.put({ uri  : final_uri+symbol+'action=keep-old-password',
				form : { login    : data.login,
				     	 mail     : data.mail,
					 status   : data.status,
				     	 updator  : data.updator }}, callback);
	}
}

function deleteFunc(data, callback){
	if(!checkAttribute(data.login)){
		return callback(findError("ADMIN.A.4.1"));
	}

	var final_uri = conf.uri+'/'+data.login;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.delete({ uri : final_uri }, callback);
}


module.exports={
	get    : getFunc,
	getOne : getOneFunc,
	check  : checkFunc,
	signup : signupFunc,
	update : updateFunc,
	delete : deleteFunc
};
