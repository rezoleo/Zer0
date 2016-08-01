/* 
 * File 	: ./server/models/ws_people.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model used to call People Service
 * Version  	: 1.0.0
 */

var conf           = require('../conf/distant.js').people,
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
		return callback(findError("PROVIDER.E.1.1"));
	}

	var final_uri = conf.uri+'/'+data.id;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}

function getOneLoginFunc(data, callback){
	if(!checkAttribute(data.login)){
		return callback(findError("PROVIDER.E.2.1"));
	}

	var final_uri = conf.uri+'/login/'+data.login;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}

function getOneMailFunc(data, callback){
	if(!checkAttribute(data.mail)){
		return callback(findError("PROVIDER.E.3.1"));
	}

	var final_uri = conf.uri+'/mail/'+data.mail;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}


module.exports = {
	getCacheInfo : getCacheInfoFunc,
	get          : getFunc,
	getOne       : getOneFunc,
    getOneLogin  : getOneLoginFunc,
    getOneMail   : getOneMailFunc
};
