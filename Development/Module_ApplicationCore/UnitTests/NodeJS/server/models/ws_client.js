/* 
 * File 	: ./server/models/ws_client.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the different REST clients
 * Version  	: 1.0.0
 */


var request  = require('toolbox')('REQUEST'),
    conf     = require('../conf/distant.js'),
    ws_client= require('toolbox')('WSCLIENT')({ server_ca   : conf.ca,
					        client_cert : conf.cert,
					        client_key  : conf.key});


function choose_prefix(security){
	if(security.toUpperCase()=='ON'){
		return conf.secure_prefix;
	}
	return conf.normal_prefix;
}

function add_token(security){
	if(security.toUpperCase()=='ON'){
		return '?token='+conf.token;
	}
	return '';
}

function dynamicExport(security){
	function getFunc(callback){
		var final_uri = conf.adress + choose_prefix(security);
		final_uri += add_token(security);
		ws_client.get({ uri : final_uri}, callback);
	}

	function getOneFunc(id, callback){
		var final_uri = conf.adress + choose_prefix(security)+id;
		final_uri += add_token(security);
		ws_client.get({ uri: final_uri}, callback);
	}

	function postFunc(callback){
		var final_uri = conf.adress + choose_prefix(security);
		final_uri += add_token(security);
		ws_client.post({ uri: final_uri}, callback);
	}

	function putFunc(callback){
		var final_uri = conf.adress + choose_prefix(security);
		final_uri += add_token(security);
		ws_client.put({ uri: final_uri}, callback);
	}

	function deleteFunc(callback){
		var final_uri = conf.adress + choose_prefix(security);
		final_uri += add_token(security);
		ws_client.delete({ uri: final_uri}, callback);
	}

	return{
		get    : getFunc,
		getOne : getOneFunc,
		post   : postFunc,
		put    : putFunc,
		delete : deleteFunc
	};
}


module.exports = dynamicExport;
