/* 
 * File 	: ./scripts/prepare-mongo/serv-alert.js
 * Author(s)	: Zidmann
 * Function 	: This file determines which data to put inside the Alert mongo database for Unit Test
 * Version  	: 1.0.0
 * Note		: To run the script : mongo Service_Alert serv-alert.js
 */

// Remove the cache
db.caches.remove({});

// Define the alerts used for testing
db.alerts.remove({});
db.alerts.insert({ message : "message_info",     level : "INFO",     created : new Date(), creatorIP : "127.0.0.1", creatorService : "JunitTests_1" });
db.alerts.insert({ message : "message_warning",  level : "WARNING",  created : new Date(), creatorIP : "127.0.0.1", creatorService : "JunitTests_2" });
db.alerts.insert({ message : "message_error",    level : "ERROR",    created : new Date(), creatorIP : "127.0.0.1", creatorService : "JunitTests_3" });
db.alerts.insert({ message : "message_critical", level : "CRITICAL", created : new Date(), creatorIP : "127.0.0.1", creatorService : "JunitTests_4" });

// Define the token for the local tests
db.tokens.remove({});
db.tokens.insert({ source : "JunitTests", hash : "sha512$9fd9b4f54f$5$bddc8a432d12033acb0dec891c68fd0e36ccdaa48c97f98b037210ac0acf93c716595a66bf63d5e576bae4e05b58b6f8aad06e59ca4e309b96e819a629e4c412", created : new Date() });

