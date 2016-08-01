/* 
 * File 	: ./server/models/ws_group.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model used to call Group Service
 * Version  	: 1.0.0
 */

var conf           = require('../conf/distant.js').group,
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
		return callback(findError("PROVIDER.D.1.1"));
	}

	var final_uri = conf.uri+'/'+data.id;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}

function getOneByNameFunc(data, callback){
	if(!checkAttribute(data.name)){
		return callback(findError("PROVIDER.D.2.1"));
	}

	var final_uri = conf.uri+'/name/'+data.name;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}

function searchByLoginFunc(data, callback){
	if(!checkAttribute(data.login)){
		return callback(findError("PROVIDER.D.3.1"));
	}

	var final_uri = conf.uri+'/search/'+data.login;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}

module.exports={
	getCacheInfo  : getCacheInfoFunc,
	get	      : getFunc,
	getOne	      : getOneFunc,
	getOneByName  : getOneByNameFunc,
	searchByLogin : searchByLoginFunc
};
