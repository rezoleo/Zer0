/* 
 * File 	: ./server/models/ws_picture.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model used to download the picture
 * Version  	: 1.0.0
 */

var sslConf        = require('../conf/ssl.js'),
    rootUrl        = require('../conf/http.js').rootUrl,
    findError      = require('../errors/referential.js'),
    checkAttribute = require('toolbox')('CHECKATTRIBUTE'),
    isEmpty        = require('toolbox')('ISEMPTY'),
    ws_client      = null;

if(!isEmpty(sslConf) && !isEmpty(rootUrl) && rootUrl.toLowerCase().indexOf('https://')===0){
    ws_client  = require('toolbox')('WSCLIENT')({ server_ca   : sslConf.cert,
					          client_cert : sslConf.cert,
					          client_key  : sslConf.key });
}
else{
    ws_client  = require('toolbox')('WSCLIENT')(null);
}

function getFunc(data, res, callback){
	if(!checkAttribute(data.dir) || !checkAttribute(data.file)){
		return callback(findError("PICTURE.A.1.1"));
	}

	var final_uri = rootUrl+'/'+data.dir+'/'+data.file;
	return ws_client.redirect({ uri : final_uri}, res, callback);
}


module.exports={
	get : getFunc
};
