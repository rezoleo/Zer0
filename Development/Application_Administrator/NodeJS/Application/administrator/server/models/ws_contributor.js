/* 
 * File 	: ./server/models/ws_contributor.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model used to call Contributor Service
 * Version  	: 1.0.0
 */

var conf           = require('../conf/distant.js').contributor,
	findError      = require('../errors/referential.js'),
	checkAttribute = require('toolbox')('CHECKATTRIBUTE'),
    isEmpty        = require('toolbox')('ISEMPTY'),
    ws_client      = require('toolbox')('WSCLIENT')({ server_ca   : conf.security.ca,
											          client_cert : conf.security.cert,
											          client_key  : conf.security.key});


function getCacheInfoFunc(callback){
	var final_uri = conf.uri+'/?action=get-cache-infos';
	if(!isEmpty(conf.token)){
		final_uri+='&token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}

function getFunc(callback){
	var final_uri = conf.uri+'/';
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}

function getOneFunc(data, callback){
	if(!checkAttribute(data.id)){
		return callback(findError("ADMIN.C.1.1"));
	}

	var final_uri = conf.uri+'/'+data.id;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}

function getOneLoginFunc(data, callback){
	if(!checkAttribute(data.code)){
		return callback(findError("ADMIN.C.2.1"));
	}

	var final_uri = conf.uri+'/login/'+data.code;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}

function postFunc(data, callback){
	var final_uri = conf.uri+'/';
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.post({  uri  : final_uri,
			  form : { login   : data.login,
				   creator : data.creator }}, callback);
}

function deleteFunc(data, callback){
	if(!checkAttribute(data.id)){
		return callback(findError("ADMIN.C.3.1"));
	}

	var final_uri = conf.uri+'/'+data.id;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.delete({ uri : final_uri}, callback);
}


module.exports={
	getCacheInfo : getCacheInfoFunc,
	get	     : getFunc,
	getOne	     : getOneFunc,
	getOneLogin  : getOneLoginFunc,
	post	     : postFunc,
	delete	     : deleteFunc
};
