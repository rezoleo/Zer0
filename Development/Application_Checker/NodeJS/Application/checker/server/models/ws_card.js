/* 
 * File 	: ./server/models/ws_card.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model used to call Card Service
 * Version  	: 1.0.0
 */

var conf           = require('../conf/distant.js').card,
    checkAttribute = require('toolbox')('CHECKATTRIBUTE'),
    isEmpty        = require('toolbox')('ISEMPTY'),
    ws_client      = require('toolbox')('WSCLIENT')({ server_ca   : conf.security.ca,
					              client_cert : conf.security.cert,
					              client_key  : conf.security.key});


function getOneCardFunc(data, callback){
	if(!checkAttribute(data.code)){
		return callback(findError("CHECKER.B.1.1"));
	}

	var final_uri = conf.uri+'/code/'+data.code;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}


module.exports={
	getOneCard : getOneCardFunc
};
