/* 
 * File 	: ./scripts/prepare-mongo/serv-people.js
 * Author(s)	: Zidmann
 * Function 	: This file determines which data to put inside the People mongo database for Unit Test
 * Version  	: 1.0.0
 * Note		: To run the script : mongo Service_People serv-people.js
 */

// Remove the cache
db.caches.remove({});

// Remove the people
db.peoples.remove({});

// Define the token for the local tests
db.tokens.remove({});
db.tokens.insert({ source : "JunitTests", hash : "sha512$22831b1067$5$7655a3e96f3ac61de216ba400c17f01953e11167fb88027e81d02997a179f6e2b0411a19b0b29af23a842c0a42bf77ccdfc16b266aeea17a8f57338a4bcdb100", created : new Date() });

