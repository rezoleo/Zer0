/* 
 * File 	: ./server/errors/referential.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the registered error of the NodeJS module for managing the web application
 * Version  	: 1.0.0
 */


var findError = require('toolbox')('ERROR_REFERENTIAL');

var errors = [];
errors.push({status : 404, code : 'CARD.1.1.1',  message : "L'identifiant ID de la carte est absent",		stack : null});
errors.push({status : 404, code : 'CARD.1.1.2',  message : "Aucune carte identifiée avec cet identifiant",	stack : null});
errors.push({status : 404, code : 'CARD.1.2.1',  message : "Le code de la carte est absent", 				stack : null});
errors.push({status : 404, code : 'CARD.1.2.2',  message : "Aucune carte identifiée avec ce code", 			stack : null});
errors.push({status : 404, code : 'CARD.1.3.1',  message : "Le propriétaire de la carte n'a pas été précisé",							stack : null});
errors.push({status : 404, code : 'CARD.1.3.2',  message : "Le code de la carte n'a pas été précisé",								stack : null});
errors.push({status : 404, code : 'CARD.1.3.3',  message : "Le status de la carte n'a pas été précisé",								stack : null});
errors.push({status : 404, code : 'CARD.1.3.4',  message : "Impossible d'ajouter la carte dans le système comme le code est déja utilisé par une autre carte",	stack : null});
errors.push({status : 404, code : 'CARD.1.3.5',  message : "Impossible d'ajouter la carte dans le système",							stack : null});
errors.push({status : 404, code : 'CARD.1.4.1',  message : "L'identifiant ID de la carte est absent",										stack : null});
errors.push({status : 404, code : 'CARD.1.4.2',  message : "Le propriétaire de la carte n'a pas été précisé",									stack : null});
errors.push({status : 404, code : 'CARD.1.4.3',  message : "Le code de la carte n'a pas été précisé",										stack : null});
errors.push({status : 404, code : 'CARD.1.4.4',  message : "Le status de la carte n'a pas été précisé",										stack : null});
errors.push({status : 404, code : 'CARD.1.4.5',  message : "Aucune carte identifiée avec cet identifiant",									stack : null});
errors.push({status : 404, code : 'CARD.1.4.6',  message : "Impossible de mettre à jour les données de la carte comme le nouveau code est déja utilisé par une autre carte",	stack : null});
errors.push({status : 404, code : 'CARD.1.4.7',  message : "Impossible de trouver la carte après la mise à jour",								stack : null});
errors.push({status : 404, code : 'CARD.1.5.1',  message : "L'identifiant ID de la carte est absent",		stack : null});
errors.push({status : 404, code : 'CARD.1.5.2',  message : "Aucune carte identifiée avec cet identifiant",	stack : null});


module.exports = findError(errors);
