/* 
 * File 	: ./server/models/ws_mail.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model used to call Mail Service
 * Version  	: 1.0.0
 */

var conf      = require('../conf/distant.js').mail,
    isEmpty   = require('toolbox')('ISEMPTY'),
    ws_client = require('toolbox')('WSCLIENT')({ server_ca   : conf.security.ca,
					         client_cert : conf.security.cert,
					         client_key  : conf.security.key});


function postLinkByMailFunc(data, callback){
	var final_uri = conf.uri;
	if(!isEmpty(conf.tokenLink)){
		final_uri+='?token='+conf.tokenLink;
	}
	ws_client.post({ uri  : final_uri,
			 form : { to      : data.to,
			     	  content : data.content }}, callback);
}

function postConfirmationByMailFunc(data, callback){
	var final_uri = conf.uri;
	if(!isEmpty(conf.tokenConfirm)){
		final_uri+='?token='+conf.tokenConfirm;
	}
	ws_client.post({ uri  : final_uri,
			 form : { to      : data.to,
			     	  content : data.content }}, callback);
}

module.exports = {
        postLinkByMail		: postLinkByMailFunc,
        postConfirmationByMail	: postConfirmationByMailFunc
};
