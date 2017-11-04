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
db.alerts.insert({ message : "message_INFO",     level : "INFO",     created : new Date(), creatorIP : "127.0.0.1", creatorService : "JunitTests_1" });
db.alerts.insert({ message : "message_WARNING",  level : "WARNING",  created : new Date(), creatorIP : "127.0.0.1", creatorService : "JunitTests_2" });
db.alerts.insert({ message : "message_ERROR",    level : "ERROR",    created : new Date(), creatorIP : "127.0.0.1", creatorService : "JunitTests_3" });
db.alerts.insert({ message : "message_CRITICAL", level : "CRITICAL", created : new Date(), creatorIP : "127.0.0.1", creatorService : "JunitTests_4" });

// Define the token for the local tests
db.tokens.remove({});
db.tokens.insert({ source : "JunitTests", hash : "sha512$8b52697ede$5$9ac9f1bd09c1abfe966f8872318e864397d635a219d7fd4360f94abe31e00d31bc48e9292e3bef60d393f50b919f6e90c8ceb6485aec5eee62cd5f9f0227aede", created : new Date() });

