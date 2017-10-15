/* 
 * File 	: ./scripts/prepare-mongo/module-applicationcore.js
 * Author(s)	: Zidmann
 * Function 	: This file determines which data to put inside the ApplicationCore testing mongo database for Unit Test
 * Version  	: 1.0.0
 * Note		: To run the script : mongo Testing_Applicationcore module-applicationcore.js
 */

// Remove the traces used for flood protection
db.floods.remove({});

