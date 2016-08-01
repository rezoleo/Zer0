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

if(!isEmpty(conf) && !isEmpty(conf.uri) && conf.uri.toLowerCase().indexOf('https://')===0){
    ws_client  = require('toolbox')('WSCLIENT')({ server_ca   : conf.security.ca,
					          client_cert : conf.security.cert,
					          client_key  : conf.security.key });
}
else{
    ws_client  = require('toolbox')('WSCLIENT')(null);
}

function replaceFirstBackSlash(str){	
	if(!isEmpty(str) && typeof(str)==='string'){
		var count=0;
		for(var i=0; i<str.length; i++){
			if(str[i]==='/'){
				count++;
			}
			if(count>1){
				// If there is more than one '/', we do not remove the backslash
				return str;
			}
		}
		// If there is no backslash or just one, we replace it by a zero
		return str.replace('/', '0');	
	}
	return str;
}

function getFunc(data, res, callback){
	if(!checkAttribute(replaceFirstBackSlash(data.filepath))){
		return callback(findError("CHECKER.E.1.1"));
	}

	var final_uri = conf.uri+'/'+data.filepath;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	return ws_client.redirect({ uri : final_uri}, res, callback);
}


module.exports={
	get : getFunc
};
