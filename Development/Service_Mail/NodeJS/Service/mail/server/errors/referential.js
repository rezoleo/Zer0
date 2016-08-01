/* 
 * File 	: ./server/errors/referential.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the registered error of the NodeJS module for managing the webservice
 * Version  	: 1.0.0
 */


var findError = require('toolbox')('ERROR_REFERENTIAL');

var errors = [];
errors.push({status : 404, code : 'MAIL.1.1.1',  message : "Le jeton transmis ne contient pas l'expéditeur du message",	stack : null});
errors.push({status : 404, code : 'MAIL.1.1.2',  message : "Le jeton transmis ne contient pas le sujet du message",	stack : null});
errors.push({status : 404, code : 'MAIL.1.1.3',  message : "Le destinataire du message est absent",			stack : null});
errors.push({status : 404, code : 'MAIL.1.1.4',  message : "Le contenu du message est absent",				stack : null});
errors.push({status : 404, code : 'MAIL.1.1.5',  message : "Une erreur est survenue lors de l'envoi du message",	stack : null});


module.exports = findError(errors);
