/* 
 * File 	: ./server/errors/referential.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the registered error of the NodeJS module for managing the web application
 * Version  	: 1.0.0
 */


var findError = require('toolbox')('ERROR_REFERENTIAL');

var errors = [];
errors.push({status : 404, code : 'ALERT.1.1.1',  message : "Le niveau d'alerte ne correspond à aucun référencé",	stack : null});
errors.push({status : 404, code : 'ALERT.1.2.1',  message : "L'identifiant ID de l'alerte est absent",		stack : null});
errors.push({status : 404, code : 'ALERT.1.2.2',  message : "Aucune alerte identifiée avec cet identifiant",	stack : null});
errors.push({status : 404, code : 'ALERT.1.3.1',  message : "Le message de l'alerte n'a pas été précisé",		stack : null});
errors.push({status : 404, code : 'ALERT.1.3.2',  message : "Le niveau de l'alerte n'a pas été précisé",		stack : null});
errors.push({status : 404, code : 'ALERT.1.3.3',  message : "Impossible d'ajouter l'alerte dans le système",		stack : null});


module.exports = findError(errors);
