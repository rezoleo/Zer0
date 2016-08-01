/* 
 * File 	: ./server/errors/referential.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the registered error of the NodeJS module for managing the web application
 * Version  	: 1.0.0
 */


var findError = require('toolbox')('ERROR_REFERENTIAL');

var errors = [];
errors.push({status : 404, code : 'PROVIDER.1.1.1',  message : "Le jeton transmis ne contient pas les droits pour accéder au répertoire distant",	stack : null});
errors.push({status : 404, code : 'PROVIDER.A.1.1',  message : "Le connecteur vers le service des authentifiants a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'PROVIDER.A.2.1',  message : "Le connecteur vers le service des authentifiants a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'PROVIDER.B.1.1',  message : "Le connecteur vers le service des cartes a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'PROVIDER.B.2.1',  message : "Le connecteur vers le service des cartes a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'PROVIDER.C.1.1',  message : "Le connecteur vers le service des cotisants a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'PROVIDER.C.2.1',  message : "Le connecteur vers le service des cotisants a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'PROVIDER.D.1.1',  message : "Le connecteur vers le service des groupes a généré une uri avec au moins un caractère interdit", stack : null});
errors.push({status : 404, code : 'PROVIDER.D.2.1',  message : "Le connecteur vers le service des groupes a généré une uri avec au moins un caractère interdit", stack : null});
errors.push({status : 404, code : 'PROVIDER.D.3.1',  message : "Le connecteur vers le service des groupes a généré une uri avec au moins un caractère interdit", stack : null});
errors.push({status : 404, code : 'PROVIDER.E.1.1',  message : "Le connecteur vers le service des personnes a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'PROVIDER.E.2.1',  message : "Le connecteur vers le service des personnes a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'PROVIDER.E.3.1',  message : "Le connecteur vers le service des personnes a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'PROVIDER.F.1.1',  message : "Le connecteur vers le service des images a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'PROVIDER.F.2.1',  message : "Le connecteur vers le service des images a généré une uri avec au moins un caractère interdit",	stack : null});


module.exports = findError(errors);
