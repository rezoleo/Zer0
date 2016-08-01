/* 
 * File 	: ./server/models/ws_picture.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model used to call Picture Service
 * Version  	: 1.0.0
 */

var fs	           = require('fs'),
    conf           = require('../conf/distant.js').picture,
    findError      = require('../errors/referential.js'),
    checkAttribute = require('toolbox')('CHECKATTRIBUTE'),
    isEmpty        = require('toolbox')('ISEMPTY'),
    ws_client      = null;

if(!isEmpty(conf) && !isEmpty(conf.dir_uri) && conf.dir_uri.toLowerCase().indexOf('https://')===0){
    ws_client  = require('toolbox')('WSCLIENT')({ server_ca   : conf.security.ca,
					          client_cert : conf.security.cert,
					          client_key  : conf.security.key });
}
else{
    ws_client  = require('toolbox')('WSCLIENT')(null);
}

function getFunc(data, res, callback){
	if(!checkAttribute(data.dir) || !checkAttribute(data.file)){
		return callback(findError("PROVIDER.F.1.1"));
	}

	var final_uri = conf.dir_uri+'/'+data.dir+'/'+data.file;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	return ws_client.redirect({ uri : final_uri}, res, callback);
}

function getInfosFunc(data, callback){
	if(!checkAttribute(data.dir) || !checkAttribute(data.file)){
		return callback(findError("PROVIDER.F.2.1"));
	}

	var final_uri = conf.uri+'/'+data.dir+'/'+data.file+'?action=get-cache-infos';
	if(!isEmpty(conf.token)){
		final_uri+='&token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}


module.exports={
	get      : getFunc,
	getInfos : getInfosFunc
};
