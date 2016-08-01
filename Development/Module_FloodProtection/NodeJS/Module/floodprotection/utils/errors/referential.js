/* 
 * File 	: ./utils/errors/referential.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the registered error of the NodeJS module for managing bruteforce protection
 * Version  	: 1.0.0
 */


var findError = require('toolbox')('ERROR_REFERENTIAL');

var errors = [];
errors.push({status : 404, code : 'FLOODPROTECTION.1.1.1',  message : "La clé auquelle est associée la connexion est absente",																stack : null});
errors.push({status : 404, code : 'FLOODPROTECTION.1.1.2',  message : "La clé auquelle est associée la connexion n'est pas valide",															stack : null});
errors.push({status : 404, code : 'FLOODPROTECTION.1.1.3',  message : "Une erreur est survenue lors de la suppression des historiques périmés",												stack : null});
errors.push({status : 404, code : 'FLOODPROTECTION.1.1.4',  message : "Une erreur est survenue lors de la recherche des historiques pour la clé associée à la connexion",					stack : null});
errors.push({status : 404, code : 'FLOODPROTECTION.1.1.5',  message : "Accès restreint : la protection anti-bruteforce pour cette connexion est toujours active",							stack : null});
errors.push({status : 404, code : 'FLOODPROTECTION.1.1.6',  message : "Accès restreint : activation de la protection anti-bruteforce pour cette connexion",									stack : null});
errors.push({status : 404, code : 'FLOODPROTECTION.1.1.7',  message : "Une erreur est survenue lors de l'incrémentation du compteur pour la clé associée à cette connexion",				stack : null});
errors.push({status : 404, code : 'FLOODPROTECTION.1.1.8',  message : "Impossible de trouver l'historique de la clé associée à cette connexion après l'incrémentation du compteur",			stack : null});
errors.push({status : 404, code : 'FLOODPROTECTION.1.1.9',  message : "Impossible d'ajouter un historique pour la clé associée à cette connexion",											stack : null});
errors.push({status : 404, code : 'FLOODPROTECTION.1.1.10', message : "Impossible de trouver l'historique pour la clé associée à cette connexion après son insertion dans les historiques",	stack : null});


module.exports = findError(errors);
