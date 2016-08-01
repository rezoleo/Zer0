/* 
 * File 	: ./server/errors/referential.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the registered errors of the NodeJS module for managing the web application
 * Version  	: 1.0.0
 */


var findError = require('toolbox')('ERROR_REFERENTIAL');

var errors = [];
errors.push({status : 404, code : 'APPCORE.1.1.1', message : "Api inexistante", 				stack : null});
errors.push({status : 404, code : 'APPCORE.2.1.1', message : "Api inexistante", 				stack : null});
errors.push({status : 404, code : 'APPCORE.3.1.1', message : "Fonctionnalité refusée; identification requise",	stack : null});
errors.push({status : 404, code : 'APPCORE.3.1.2', message : "Fonctionnalité refusée; la méthode d'accès n'est pas acceptée", stack : null});
errors.push({status : 404, code : 'APPCORE.3.1.3', message : "Fonctionnalité refusée; autorisation requise", 	stack : null});
errors.push({status : 404, code : 'APPCORE.3.1.4', message : "Fonctionnalité refusée; condition d'accès non satisfaite", stack : null});
errors.push({status : 404, code : 'APPCORE.3.1.5', message : "Erreur lors de la vérification des permissions", 	stack : null});
errors.push({status : 404, code : 'APPCORE.3.1.6', message : "Erreur lors de l'exécution du contrôleur", 	stack : null});


module.exports = findError(errors);
