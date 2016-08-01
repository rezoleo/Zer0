/* 
 * File 	: ./utils/hash.js
 * Author(s)	: Zidmann
 * Function 	: This file defines some hash functions
 * Version  	: 1.0.0
 */

var 	sha512  = require('sha512'),
	isEmpty = require('./isEmpty.js');


function sha512Func(str, salt){
	if(isEmpty(str)){
		return null;
	}
	if(isEmpty(salt)){
		salt='';
	}
	str=str+salt;
	var hash = sha512(str);
	return hash.toString('hex');
}


module.exports = {
	sha512 : sha512Func
}
