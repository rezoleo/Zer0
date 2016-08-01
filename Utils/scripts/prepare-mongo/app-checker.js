/* 
 * File 	: ./scripts/prepare-mongo/app-checker.js
 * Author(s)	: Zidmann
 * Function 	: This file determines which data to put inside the Checker mongo database for Unit Test
 * Version  	: 1.0.0
 * Note		: To run the script : mongo Application_Checker app-checker.js
 */

// Define the authorizations
db.authorizations.remove({});
db.authorizations.insert({created : new Date(), creator : null, login : "user", roles : [ "CARD_UNLOCKED", "CONTRIBUTOR_UNLOCKED", "ROLE_NOTIFICATION", "PEOPLE_UNLOCKED", "ROLE_MANAGER" ], updated : null, updator : null});

