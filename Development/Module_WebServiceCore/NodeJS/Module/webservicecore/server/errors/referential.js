/* 
 * File 	: ./server/errors/referential.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the registered error of the NodeJS module for managing the web application
 * Version  	: 1.0.0
 */


var findError = require('toolbox')('ERROR_REFERENTIAL');

var errors = [];
errors.push({status : 404, code : 'WSCORE.1.1.1',  message : "Api inexistante", 						     stack : null});
errors.push({status : 404, code : 'WSCORE.2.1.1',  message : "Api inexistante", 						     stack : null});
errors.push({status : 404, code : 'WSCORE.3.1.1',  message : "Le jeton transmis est invalide ou absent",			     stack : null});
errors.push({status : 404, code : 'WSCORE.3.1.2',  message : "L'adresse IP du client n'est pas compatible avec ce jeton", 	     stack : null});
errors.push({status : 404, code : 'WSCORE.3.1.3',  message : "Le nom de service source n'est pas compatible avec ce jeton", 	     stack : null});
errors.push({status : 404, code : 'WSCORE.3.1.4',  message : "Le nom de service de destination n'est pas compatible avec ce jeton",  stack : null});
errors.push({status : 404, code : 'WSCORE.3.1.5',  message : "La période d'utisation n'est pas exploitable avec ce jeton", 	     stack : null});
errors.push({status : 404, code : 'WSCORE.3.1.6',  message : "Le jeton ne contient aucune règle d'accès", 			     stack : null});
errors.push({status : 404, code : 'WSCORE.3.1.7',  message : "Le jeton n'est pas reconnu dans la base de données", 		     stack : null});
errors.push({status : 404, code : 'WSCORE.3.1.8',  message : "Le jeton ne dispose pas des droits suffisants pour accéder à cet API", stack : null});
errors.push({status : 404, code : 'WSCORE.3.1.9',  message : "Erreur lors de la vérification des permissions", 			     stack : null});
errors.push({status : 404, code : 'WSCORE.3.1.10', message : "Erreur lors de l'exécution du contrôleur", 			     stack : null});


module.exports = findError(errors);
