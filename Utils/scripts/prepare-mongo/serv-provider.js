/* 
 * File 	: ./scripts/prepare-mongo/serv-provider.js
 * Author(s)	: Zidmann
 * Function 	: This file determines which data to put inside the Provider mongo database for Unit Test
 * Version  	: 1.0.0
 * Note		: To run the script : mongo Service_Provider serv-provider.js
 */

// Remove the cache
db.caches.remove({});

// Define the token for the local tests
db.tokens.remove({});
db.tokens.insert({ source : "JunitTests", hash : "sha512$5d434540fe$5$277f83a0690efc66da94477de7d3d3ebad780481d660a1618bdda5ae0aa8e3839016f816be028211bd425ad0e3f09ca2b016d15a7b98f3ac1c4877f16a99c875",  created : new Date() });

// Remove the traces used for flood protection
db.floods.remove({});

