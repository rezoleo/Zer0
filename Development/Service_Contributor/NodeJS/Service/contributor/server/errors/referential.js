/* 
 * File 	: ./server/errors/referential.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the registered error of the NodeJS module for managing the web application
 * Version  	: 1.0.0
 */


var findError = require('toolbox')('ERROR_REFERENTIAL');

var errors = [];
errors.push({status : 404, code : 'CONTRIBUTOR.1.1.1',  message : "L'identifiant ID du cotisant est absent",		stack : null});
errors.push({status : 404, code : 'CONTRIBUTOR.1.1.2',  message : "Aucun cotisant identifiée avec cet identifiant",	stack : null});
errors.push({status : 404, code : 'CONTRIBUTOR.1.2.1',  message : "Le login du cotisant est absent", 							stack : null});
errors.push({status : 404, code : 'CONTRIBUTOR.1.2.2',  message : "Une erreur est survenue lors de la recherche du cotisant",	stack : null});
errors.push({status : 404, code : 'CONTRIBUTOR.1.2.3',  message : "Aucun cotisant identifié avec ce login", 					stack : null});
errors.push({status : 404, code : 'CONTRIBUTOR.1.3.1',  message : "Le login du cotisant n'a pas été précisé",															stack : null});
errors.push({status : 404, code : 'CONTRIBUTOR.1.3.2',  message : "Impossible d'ajouter la cotisant dans le système comme le login est déja utilisé par un cotisant",	stack : null});
errors.push({status : 404, code : 'CONTRIBUTOR.1.3.3',  message : "Impossible d'ajouter le cotisant dans le système",													stack : null});
errors.push({status : 404, code : 'CONTRIBUTOR.1.4.1',  message : "L'identifiant ID du cotisant est absent",		stack : null});
errors.push({status : 404, code : 'CONTRIBUTOR.1.4.2',  message : "Aucun cotisant identifié avec cet identifiant",	stack : null});


module.exports = findError(errors);
