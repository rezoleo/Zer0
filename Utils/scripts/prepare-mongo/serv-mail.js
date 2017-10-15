/* 
 * File 	: ./scripts/prepare-mongo/serv-mail.js
 * Author(s)	: Zidmann
 * Function 	: This file determines which data to put inside the Mail mongo database for Unit Test
 * Version  	: 1.0.0
 * Note		: To run the script : mongo Service_Mail serv-mail.js
 */

// Remove the cache
db.caches.remove({});

// Define the token for the local tests
db.tokens.remove({});
db.tokens.insert({source : "Service_PasswordReset_1", hash : "sha512$1bd167e36e$5$79ed80a180f391c1e92c148e0695848428b878d5fa094cfbd532bd3feecdbdeaa89384c7fb829564146f3b3ffb8d810d6044a35e10029b0d55d6f587711a35ec", "created" : "Sun May 17 2015 07:32:45 GMT+0200 (CEST)"});
db.tokens.insert({source : "Service_PasswordReset_2", hash : "sha512$ae3c9b772d$5$e4e40f6ab888df2697b754e4d4da1eaecc06e214af6f309425bfdd2bdc58cc7e0aea59afa26968a21f9f06e6769b6a190c08f67a8cd301fccb53bf3c96587f6f", "created" : "Sun May 17 2015 07:35:32 GMT+0200 (CEST)"});

// Remove the traces used for flood protection
db.floods.remove({});

