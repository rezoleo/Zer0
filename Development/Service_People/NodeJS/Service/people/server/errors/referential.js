/* 
 * File 	: ./server/errors/referential.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the registered error of the NodeJS module for managing the web application
 * Version  	: 1.0.0
 */


var findError = require('toolbox')('ERROR_REFERENTIAL');

var errors = [];
errors.push({status : 404, code : 'PEOPLE.1.1.1',  message : "L'identifiant ID de la personne est absent",	stack : null});
errors.push({status : 404, code : 'PEOPLE.1.1.2',  message : "Aucune personne identifiée avec cet identifiant",	stack : null});
errors.push({status : 404, code : 'PEOPLE.1.2.1',  message : "Le login de la personne est absent", 			stack : null});
errors.push({status : 404, code : 'PEOPLE.1.2.2',  message : "Aucune personne identifiée avec ce login", 		stack : null});
errors.push({status : 404, code : 'PEOPLE.1.3.1',  message : "L'e-mail de la personne est absent",		stack : null});
errors.push({status : 404, code : 'PEOPLE.1.3.2',  message : "Aucune personne identifiée avec cet e-mail",	stack : null});
errors.push({status : 404, code : 'PEOPLE.1.4.1',  message : "Le login de la personne n'a pas été précisé",								stack : null});
errors.push({status : 404, code : 'PEOPLE.1.4.2',  message : "Le nom de famille de la personne n'a pas été précisé",							stack : null});
errors.push({status : 404, code : 'PEOPLE.1.4.3',  message : "Le prénom de la personne n'a pas été précisé",								stack : null});
errors.push({status : 404, code : 'PEOPLE.1.4.4',  message : "Impossible d'ajouter la personne dans le système comme le login est déja utilisé par une autre personne",	stack : null});
errors.push({status : 404, code : 'PEOPLE.1.4.5',  message : "Impossible d'ajouter la personne dans le système comme l'e-mail est déja utilisé par une autre personne",	stack : null});
errors.push({status : 404, code : 'PEOPLE.1.4.6',  message : "Impossible d'ajouter la personne dans le système",							stack : null});
errors.push({status : 404, code : 'PEOPLE.1.5.1',  message : "L'identifiant ID de la personne est absent",			stack : null});
errors.push({status : 404, code : 'PEOPLE.1.5.2',  message : "Le tag à ajouter pour la personne est absent",			stack : null});
errors.push({status : 404, code : 'PEOPLE.1.5.3',  message : "Aucune personne identifiée avec cet identifiant",			stack : null});
errors.push({status : 404, code : 'PEOPLE.1.5.4',  message : "Un tag avec ce libellé existe déjà pour l'utilisateur désigné",	stack : null});
errors.push({status : 404, code : 'PEOPLE.1.5.5',  message : "Impossible de trouver la personne après la mise à jour",		stack : null});
errors.push({status : 404, code : 'PEOPLE.1.6.1',  message : "L'identifiant ID de la personne est absent",								stack : null});
errors.push({status : 404, code : 'PEOPLE.1.6.2',  message : "Le login de la personne n'a pas été précisé",								stack : null});
errors.push({status : 404, code : 'PEOPLE.1.6.3',  message : "Le nom de famille de la personne n'a pas été précisé",							stack : null});
errors.push({status : 404, code : 'PEOPLE.1.6.4',  message : "Le prénom de la personne n'a pas été précisé",								stack : null});
errors.push({status : 404, code : 'PEOPLE.1.6.5',  message : "Aucune personne identifiée avec cet identifiant",								stack : null});
errors.push({status : 404, code : 'PEOPLE.1.6.6',  message : "Impossible d'ajouter la personne dans le système comme le login est déja utilisé par une autre personne",	stack : null});
errors.push({status : 404, code : 'PEOPLE.1.6.7',  message : "Impossible d'ajouter la personne dans le système comme l'e-mail est déja utilisé par une autre personne",	stack : null});
errors.push({status : 404, code : 'PEOPLE.1.6.8',  message : "Impossible de trouver la personne après la mise à jour",							stack : null});
errors.push({status : 404, code : 'PEOPLE.1.7.1',  message : "L'identifiant ID de la personne est absent",			stack : null});
errors.push({status : 404, code : 'PEOPLE.1.7.2',  message : "Le tag à retirer pour la personne est absent",			stack : null});
errors.push({status : 404, code : 'PEOPLE.1.7.3',  message : "Aucune personne identifiée avec cet identifiant",			stack : null});
errors.push({status : 404, code : 'PEOPLE.1.7.4',  message : "Aucun tag avec ce libellé n'existe pour l'utilisateur désigné",	stack : null});
errors.push({status : 404, code : 'PEOPLE.1.7.5',  message : "Impossible de trouver la personne après la mise à jour",		stack : null});
errors.push({status : 404, code : 'PEOPLE.1.8.1',  message : "L'identifiant ID de la personne est absent",	stack : null});
errors.push({status : 404, code : 'PEOPLE.1.8.2',  message : "Aucune personne identifiée avec cet identifiant",	stack : null});


module.exports = findError(errors);
