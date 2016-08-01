/* 
 * File 	: ./server/errors/referential.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the registered error of the NodeJS module for managing the web application
 * Version  	: 1.0.0
 */


var findError = require('toolbox')('ERROR_REFERENTIAL');

var errors = [];
errors.push({status : 404, code : 'AUTH.1.1.1',  message : "Le login de la personne n'a pas été précisé",	stack : null});
errors.push({status : 404, code : 'AUTH.1.1.2',  message : "Aucun accès accessible avec ce login",		stack : null});
errors.push({status : 404, code : 'AUTH.1.2.1',  message : "Le login de la personne n'a pas été précisé",							stack : null});
errors.push({status : 404, code : 'AUTH.1.2.2',  message : "Le mail de la personne n'a pas été précisé",							stack : null});
errors.push({status : 404, code : 'AUTH.1.2.3',  message : "Le mot de passe n'a pas été précisé", 								stack : null});
errors.push({status : 404, code : 'AUTH.1.2.4',  message : "Le statut n'a pas été précisé", 									stack : null});
errors.push({status : 404, code : 'AUTH.1.2.5',  message : "Impossible d'ajouter cet accès dans le système comme le login est déja utilisé par un autre accès", stack : null});
errors.push({status : 404, code : 'AUTH.1.2.6',  message : "Impossible d'ajouter cet accès dans le système comme le mail est déja utilisé par un autre accès",  stack : null});
errors.push({status : 404, code : 'AUTH.1.2.7',  message : "Impossible d'ajouter l'accès dans le système", 							stack : null});
errors.push({status : 404, code : 'AUTH.1.3.1',  message : "Le login de la personne n'a pas été précisé",	stack : null});
errors.push({status : 404, code : 'AUTH.1.3.2',  message : "Le mot de passe n'a pas été précisé", 		stack : null});
errors.push({status : 404, code : 'AUTH.1.3.3',  message : "Aucun accès accessible avec ces identifiants",	stack : null});
errors.push({status : 404, code : 'AUTH.1.3.4',  message : "L'accès pour ce login a été désactivé",		stack : null});
errors.push({status : 404, code : 'AUTH.1.4.1',  message : "Le login de la personne n'a pas été précisé", 		stack : null});
errors.push({status : 404, code : 'AUTH.1.4.2',  message : "Le mail de la personne n'a pas été précisé",		stack : null});
errors.push({status : 404, code : 'AUTH.1.4.3',  message : "Le mot de passe n'a pas été précisé", 			stack : null});
errors.push({status : 404, code : 'AUTH.1.4.4',  message : "Le statut n'a pas été précisé", 								stack : null});
errors.push({status : 404, code : 'AUTH.1.4.5',  message : "Aucun accès accessible avec ce login", 							stack : null});
errors.push({status : 404, code : 'AUTH.1.4.6',  message : "Impossible de mettre à jour cet accès comme le mail est déja utilisé par un autre accès", 	stack : null});
errors.push({status : 404, code : 'AUTH.1.4.7',  message : "Impossible de trouver l'accès après la mise à jour",					stack : null});
errors.push({status : 404, code : 'AUTH.1.5.1',  message : "Le login de la personne est absent",	stack : null});
errors.push({status : 404, code : 'AUTH.1.5.2',  message : "Aucun accès identifié avec ce login",	stack : null});


module.exports = findError(errors);
