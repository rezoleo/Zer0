/* 
 * File 	: ./server/models/ws_alert.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model used to call Alert Service
 * Version  	: 1.0.0
 */

var conf           = require('../conf/distant.js').alert,
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

function getFunc(data, callback){
	var final_uri = conf.uri+'/';
	var delimeter='?';

	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
		delimeter='&';
	}
	if(!isEmpty(data.minimumlevel)){
		final_uri+=delimeter;
		final_uri+='minimumlevel='+data.minimumlevel;
		delimeter='&';
	}
	if(!isEmpty(data.month)){
		final_uri+=delimeter;
		final_uri+='month='+data.month;
		delimeter='&';
	}

	ws_client.get({ uri : final_uri}, callback);
}

function getOneFunc(data, callback){
	if(!checkAttribute(data.id)){
		return callback(findError("ADMIN.G.1.1"));
	}

	var final_uri = conf.uri+'/'+data.id;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}


module.exports={
	getCacheInfo : getCacheInfoFunc,
	get	     : getFunc,
	getOne	     : getOneFunc
};
