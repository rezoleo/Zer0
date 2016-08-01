/* 
 * File 	: ./utils/isArray.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the 'isArray' function to know if an element is an array
 * Version  	: 1.0.0
 */

var isEmpty = require('./isEmpty.js');

function isArrayFunc(obj){
	if(isEmpty(obj)){
		return false;
	}
	return (Object.prototype.toString.call(obj)==='[object Array]');
}


module.exports = isArrayFunc;
