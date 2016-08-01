/* 
 * File 	: ./utils/err.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the 'Err' element used to notify an error
 * Version  	: 1.0.0
 */

function Err(status, code, message, stack){
	this.message = message || "Err";
	this.code    = code    || "0";
	this.stack   = stack;
	this.status  = status;
}
Err.prototype = new Error();
Err.prototype.constructor = Err;


module.exports = Err;
