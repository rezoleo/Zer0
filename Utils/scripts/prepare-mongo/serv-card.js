/* 
 * File 	: ./scripts/prepare-mongo/serv-card.js
 * Author(s)	: Zidmann
 * Function 	: This file determines which data to put inside the Card mongo database for Unit Test
 * Version  	: 1.0.0
 * Note		: To run the script : mongo Service_Card serv-card.js
 */

// Remove the cache
db.caches.remove({});

// Remove the card
db.cards.remove({});

// Define the token for the local tests
db.tokens.remove({});
db.tokens.insert({ source : "JunitTests", hash : "sha512$48b9e68865$5$2d005aed42cf90fe1ebeeaff28365d88aac89ac67eb63f008c6bdc8fd2fe8094e1356c570e2ed7d055430ad41969be6665576db52456caf0e09ea38f8cdcf940", created : new Date() });

