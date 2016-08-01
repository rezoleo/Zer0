/* 
 * File 	: ./scripts/prepare-mongo/module-webservicecore.js
 * Author(s)	: Zidmann
 * Function 	: This file determines which data to put inside the WebServiceCore testing mongo database for Unit Test
 * Version  	: 1.0.0
 * Note		: To run the script : mongo Testing_WebServiceCore module-webservicecore.js
 */

// Remove the objects
db.objectdbs.remove({});
db.objectdbstokens.remove({});

// Define the token for the local tests
db.tokens.remove({});
db.tokens.insert({ source : "JunitTests",  hash : "sha512$5e92d6829e$5$e4fbbcd7080330f45cf779335b5e72677e9dfe858ecf8f026770badeecff90f6238d7e44f95c647bf6684593fc1897ef368de15be7f2a686b654165470956022", created : new Date() });

