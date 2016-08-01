/* 
 * File 	: ./scripts/prepare-mongo/serv-contributor.js
 * Author(s)	: Zidmann
 * Function 	: This file determines which data to put inside the Contributor mongo database for Unit Test
 * Version  	: 1.0.0
 * Note		: To run the script : mongo Service_Contributor serv-contributor.js
 */

// Remove the cache
db.caches.remove({});

// Remove the contributor
db.contributors.remove({});

// Define the token for the local tests
db.tokens.remove({});
db.tokens.insert({ source : "JunitTests", hash : "sha512$70ce2d4428$5$5671ef4cc5599ea5215358c25565516cfee0fb0036acae98a942774f72c949cc0bd364795a9ec14507137634542800ecf62916d7a61206e97336847fd93e604c", created : new Date() });

