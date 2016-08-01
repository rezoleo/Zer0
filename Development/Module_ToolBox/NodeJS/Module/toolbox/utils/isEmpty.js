/* 
 * File 	: ./utils/isEmpty.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the 'isEmpty' function
 * Version  	: 1.0.0
 */

function isEmptyFunc(str){
	if(str===undefined || str===null || str==="" || str===[]){
		return true;
	}
	return false;
}


module.exports = isEmptyFunc;
