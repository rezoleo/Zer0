/* 
 * File 	: ./server/errors/interceptors.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the interceptors to trigger some events according the error message
 * Version  	: 1.0.0
 */

var	isEmpty   = require('toolbox')('ISEMPTY'),
	isString  = require('toolbox')('ISSTRING'),
	ws_client = require('../models/ws_alert.js');


function errorFunc(err){
	if(!isEmpty(err) && !isEmpty(err.code) && isString(err.code)){
		if(err.code.indexOf('WSCORE.')===0){
			ws_client.post({ message : JSON.stringify(err),
				         level   : 'CRITICAL' },    function(){});
		}
		else if(err.code.indexOf('FLOODPROTECTION.')===0){
			ws_client.post({ message : JSON.stringify(err),
				         level   : 'ERROR' }, function(){});
		}
		else if(err.code.indexOf('ECONNREFUSED')===0){
			ws_client.post({ message : JSON.stringify(err),
				         level   : 'ERROR' }, function(){});
		}
		else{
			ws_client.post({ message : JSON.stringify(err),
				         level   : 'WARNING' },  function(){});
		}
	}
}


module.exports = {
	error : errorFunc
};
