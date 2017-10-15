/* 
 * File 	: ./scripts/prepare-mongo/app-administrator.js
 * Author(s)	: Zidmann
 * Function 	: This file determines which data to put inside the Administrator mongo database for Unit Test
 * Version  	: 1.0.0
 * Note		: To run the script : mongo Application_Administrator app-administrator.js
 */

// Define the authorizations
db.authorizations.remove({});
db.authorizations.insert({created : new Date(), creator : null, login : "user", roles : ["ALERT_ADMIN", "AUTH_ADMIN", "CARD_ADMIN", "CONTRIBUTOR_ADMIN", "GROUP_ADMIN", "PEOPLE_ADMIN"], updated : null, updator : null });

// Remove the traces used for flood protection
db.floods.remove({});

