/* 
 * File 	: ./scripts/prepare-mongo/serv-authentification.js
 * Author(s)	: Zidmann
 * Function 	: This file determines which data to put inside the Authentification mongo database for Unit Test
 * Version  	: 1.1.0
 * Note		: To run the script : mongo Service_Authentification serv-authentification.js
 */

// Remove the cache
db.caches.remove({});

// Define the single authorization authorized (user=user && password=Password1)
db.authentifications.remove({});
db.authentifications.insert({login : 'user', mail : 'test@test.com', password : '$2a$14$kgXx7D0MFRu.CWU26EWyJujtk7vW9XnctJW8I23IQF9nXjDENEV5W', status : "ON", created : new Date(), creator : '', creatorIP : '127.0.0.1', creatorService : 'JunitTests', updated : null, updator : null, updatorIP : null, updatorService : null });

// Define the token for the local tests
db.tokens.remove({});
db.tokens.insert({ source : 'JunitTests', hash : "sha512$3dd231fc76$5$ac44e1cc35b9ade6e0a8e9ac127e2f701ccb69bd32fc41c5a69584ef284ce8af1480db2fc6ed18f42e1aa422e9ee876c165448d3635ad09fa2a979448ef4eecb", created : new Date() });

