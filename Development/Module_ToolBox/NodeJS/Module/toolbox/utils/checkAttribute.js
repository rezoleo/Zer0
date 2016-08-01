/* 
 * File 	: ./utils/checkAttribute.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the 'checkAttribute' function to know if an attribute can be add in a url path
 * Version  	: 1.0.0
 */

var isEmpty  = require('./isEmpty.js'),
	isString = require('./isString.js');

function checkAttributeFunc(obj){
	if(isEmpty(obj) || typeof(obj)!=='string'){
		return false;
	}
	var regex = /^([a-zA-Z0-9-_@.]){1,}$/;
	return regex.test(obj);
}


module.exports = checkAttributeFunc;
