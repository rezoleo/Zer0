/* 
 * File 	: ./server/utils/token.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the function to generate a random token
 * Version  	: 1.0.0
 */


function randomStringFunc(string_length) {
    var chars = "0123456789abcdefghiklmnopqrstuvwxyz";
    var sol = '';

    for (var i=0; i<string_length; i++) {
	var rnum = Math.floor(Math.random() * chars.length);
	sol += chars.substring(rnum,rnum+1);
    }

    return sol;
}


module.exports = {
	generate : randomStringFunc
};
