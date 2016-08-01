/* 
 * File 	: ./server/errors/referential.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the registered error of the NodeJS module for managing the web application
 * Version  	: 1.0.0
 */


var findError = require('toolbox')('ERROR_REFERENTIAL');

var errors = [];
errors.push({status : 404, code : 'PASSWDRESET.1.1.1',  message : "Le jeton n'a pas été précisé",								stack : null});
errors.push({status : 404, code : 'PASSWDRESET.1.1.2',  message : "Aucune demande de renouvellement n'est associée à ce jeton",	stack : null});
errors.push({status : 404, code : 'PASSWDRESET.1.2.1',  message : "Le login de la personne est absent",											stack : null});
errors.push({status : 404, code : 'PASSWDRESET.1.2.2',  message : "Une demande de renouvellement est déjà en cours pour ce login",				stack : null});
errors.push({status : 404, code : 'PASSWDRESET.1.2.3',  message : "Aucun accès n'existe pour ce login",											stack : null});
errors.push({status : 404, code : 'PASSWDRESET.1.2.4',  message : "Aucune adresse e-mail n'est associée à ce login",							stack : null});
errors.push({status : 404, code : 'PASSWDRESET.1.2.5',  message : "Impossible de trouver le jeton après son insertion dans la base de données",	stack : null});
errors.push({status : 404, code : 'PASSWDRESET.1.2.6',  message : "Echec lors de l'envoi du mail",												stack : null});
errors.push({status : 404, code : 'PASSWDRESET.2.1.1',  message : "Le jeton n'a pas été précisé",										stack : null});
errors.push({status : 404, code : 'PASSWDRESET.2.1.2',  message : "Le nouveau mot de passe n'a pas été précisé",						stack : null});
errors.push({status : 404, code : 'PASSWDRESET.2.1.3',  message : "Aucune demande de renouvellement n'est associée à ce jeton",			stack : null});
errors.push({status : 404, code : 'PASSWDRESET.2.1.4',  message : "Aucun accès n'existe pour ce login",									stack : null});
errors.push({status : 404, code : 'PASSWDRESET.2.1.5',  message : "Echec lors de l'envoi du mail de confirmation",						stack : null});
errors.push({status : 404, code : 'PASSWDRESET.A.1.1',  message : "Le connecteur vers le service des authentifiants a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'PASSWDRESET.A.2.1',  message : "Le connecteur vers le service des authentifiants a généré une uri avec au moins un caractère interdit",	stack : null});


module.exports = findError(errors);
