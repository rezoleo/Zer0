/* 
 * File 	: ./scripts/prepare-mongo/serv-passwordreset.js
 * Author(s)	: Zidmann
 * Function 	: This file determines which data to put inside the PasswordReset mongo database for Unit Test
 * Version  	: 1.0.0
 * Note		: To run the script : mongo Service_PasswordReset serv-passwordreset.js
 */

// Remove the links
db.links.remove({});

