/* 
 * File 	: ./utils/isString.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the 'isString' function to know if an element is a string
 * Version  	: 1.0.1
 */

var isEmpty = require('./isEmpty.js');

function isStringFunc(obj){
	if(isEmpty(obj)){
		return false;
	}
	return (typeof(obj)==='string'/* && obj instanceof String*/);
}


module.exports = isStringFunc;
