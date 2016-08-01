/* 
 * File 	: ./utils/ws_client.js
 * Author(s)	: Zidmann
 * Function 	: This file defines some functions to make HTTP requests to a server and to manage the error response
 * Version  	: 1.0.0
 */

var fs		= require('fs'),
    request	= require('request'),
    request_bis	= require('./request.js'),
    isEmpty	= require('./isEmpty.js');


function dynamicExport(security_conf){
	if(!isEmpty(security_conf)){
		var agentOptions = {
			ca		   : fs.readFileSync(security_conf.server_ca),
			cert		   : fs.readFileSync(security_conf.client_cert),
			key		   : fs.readFileSync(security_conf.client_key),
			securityOptions	   : 'SSL_OP_NO_SSLv3',
			strictSSL	   : true,
			rejectUnauthorized : true
		};
	}

	function redirectFunc(options, res, callback){
		try{
			request({ uri	       : options.uri,
				  agentOptions : agentOptions,
				  method       : 'GET'}).pipe(res);
		}
		catch(err){
			return callback(err);
		}
	}

	function getFunc(options, callback){
		request_bis({ uri	   : options.uri,
			      agentOptions : agentOptions,
			      method       : 'GET'}, callback);
	}

	function postFunc(options, callback){
		request_bis({ uri	   : options.uri,
			      form	   : options.form,
			      agentOptions : agentOptions,
			      method       : 'POST'}, callback);
	}

	function uploadFunc(options, callback){
		request_bis({ uri	   : options.uri,
			      formData     : { file : fs.createReadStream(options.filepath) },
			      agentOptions : agentOptions,
			      method       : 'POST'}, callback);
	}

	function putFunc(options, callback){
		request_bis({ uri	   : options.uri,
			      form	   : options.form,
			      agentOptions : agentOptions,
			      method       : 'PUT'}, callback);
	}

	function deleteFunc(options, callback){
		request_bis({ uri	   : options.uri,
			      agentOptions : agentOptions,
			      method       : 'DELETE'}, callback);
	}

	return {
		redirect : redirectFunc,
		get      : getFunc,
		post     : postFunc,
		upload   : uploadFunc,
		put      : putFunc,
		delete   : deleteFunc
	}
}


module.exports = dynamicExport;
