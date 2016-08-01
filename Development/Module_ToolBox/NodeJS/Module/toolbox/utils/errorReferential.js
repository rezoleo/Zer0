/* 
 * File 	: ./utils/errorReferential.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the error referential used to registered all the error message
 * Version  	: 1.0.0
 */

var	Err	= require('./err.js'),
	isEmpty	= require('./isEmpty.js');


function dynamicExport(list){
	var error_list = [];

	for(var i=0; i<list.length; i++){
		err = list[i];

		if(!isEmpty(err)){
			error_list[err.code] = new Err(err.status, err.code, err.message, err.stack);
		}
	}

	function findErrorFunc(code, stack){
		var error = error_list[code];
		if(!isEmpty(error)){
			error.stack = stack;
		}

		return error;
	}

	return findErrorFunc;
}


module.exports = dynamicExport;
