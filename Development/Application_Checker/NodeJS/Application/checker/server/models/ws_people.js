/* 
 * File 	: ./server/models/ws_people.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model used to call People Service
 * Version  	: 1.0.0
 */

var conf           = require('../conf/distant.js').people,
    checkAttribute = require('toolbox')('CHECKATTRIBUTE'),
    isEmpty        = require('toolbox')('ISEMPTY'),
    ws_client      = require('toolbox')('WSCLIENT')({ server_ca   : conf.security.ca,
					              client_cert : conf.security.cert,
					              client_key  : conf.security.key});


function getOneLoginFunc(data, callback){
	if(!checkAttribute(data.login)){
		return callback(findError("CHECKER.D.1.1"));
	}

	var final_uri = conf.uri+'/login/'+data.login;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}


module.exports={
        getOneLogin : getOneLoginFunc
};
