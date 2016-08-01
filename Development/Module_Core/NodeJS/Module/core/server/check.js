/* 
 * File 	: ./server/check.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the auxilary function to check if a variable is defined and prints a message on the console if it's not the case
 * Version  	: 1.0.0
 */

var isEmpty = require('toolbox')('ISEMPTY');


function checkFunc(param, msg){
	if(isEmpty(param)){
		process.exit(1);
	}
}


module.exports = checkFunc;
