/* 
 * File 	: ./scripts/prepare-mongo/serv-group.js
 * Author(s)	: Zidmann
 * Function 	: This file determines which data to put inside the Group mongo database for Unit Test
 * Version  	: 1.0.0
 * Note		: To run the script : mongo Service_Group serv-group.js
 */

// Remove the cache
db.caches.remove({});

// Remove the group
db.groups.remove({});

// Define the token for the local tests
db.tokens.remove({});
db.tokens.insert({ source : "JunitTests", hash : "sha512$b9a6c92ebc$5$5d1d9d3131891c3d246c49777efea3c3c9399c53a8d8adf480690782c2758486cc15463e8684d8044a5caa78b67762397e5284405e93e92334e60f5e77ed55c3", created : new Date() });

