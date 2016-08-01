/* 
 * File 	: ./server/errors/referential.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the registered error of the NodeJS module for managing the web application
 * Version  	: 1.0.0
 */


var findError = require('toolbox')('ERROR_REFERENTIAL');

var errors = [];
errors.push({status : 404, code : 'GROUP.1.1.1',  message : "L'identifiant ID du groupe est absent",		stack : null});
errors.push({status : 404, code : 'GROUP.1.1.2',  message : "Aucun groupe identifié avec cet identifiant",	stack : null});
errors.push({status : 404, code : 'GROUP.1.2.1',  message : "Le nom du groupe est absent", 		stack : null});
errors.push({status : 404, code : 'GROUP.1.2.2',  message : "Aucun groupe identifié avec ce nom", 	stack : null});
errors.push({status : 404, code : 'GROUP.1.3.1',  message : "Le nom du groupe est absent", 			stack : null});
errors.push({status : 404, code : 'GROUP.1.4.1',  message : "Le nom du groupe n'a pas été précisé",								stack : null});
errors.push({status : 404, code : 'GROUP.1.4.2',  message : "Le type du groupe n'a pas été précisé",								stack : null});
errors.push({status : 404, code : 'GROUP.1.4.3',  message : "Impossible d'ajouter le groupe dans le système comme le nom est déja utilisé par un autre groupe",	stack : null});
errors.push({status : 404, code : 'GROUP.1.4.4',  message : "Impossible d'ajouter le groupe dans le système",							stack : null});
errors.push({status : 404, code : 'GROUP.1.5.1',  message : "L'identifiant ID du groupe n'a pas été précisé",									stack : null});
errors.push({status : 404, code : 'GROUP.1.5.2',  message : "Le nom du groupe n'a pas été précisé",										stack : null});
errors.push({status : 404, code : 'GROUP.1.5.3',  message : "Le type du groupe n'a pas été précisé",										stack : null});
errors.push({status : 404, code : 'GROUP.1.5.4',  message : "Aucun groupe identifié avec cet identifiant",									stack : null});
errors.push({status : 404, code : 'GROUP.1.5.5',  message : "Impossible d'ajouter le groupe dans le système comme le nom est déja utilisé par un autre groupe",			stack : null});
errors.push({status : 404, code : 'GROUP.1.5.6',  message : "Impossible d'ajouter le groupe dans le système comme l'adresse e-mail est déja utilisée par un autre groupe",	stack : null});
errors.push({status : 404, code : 'GROUP.1.5.7',  message : "Impossible de trouver le groupe après la mise à jour",								stack : null});
errors.push({status : 404, code : 'GROUP.1.6.1',  message : "L'identifiant ID du groupe est absent",		stack : null});
errors.push({status : 404, code : 'GROUP.1.6.2',  message : "Aucun groupe identifié avec cet identifiant",	stack : null});
errors.push({status : 404, code : 'GROUP.1.7.1',  message : "L'identifiant ID du groupe est absent",			stack : null});
errors.push({status : 404, code : 'GROUP.1.7.2',  message : "Le login à ajouter dans le groupe est absent",		stack : null});
errors.push({status : 404, code : 'GROUP.1.7.3',  message : "Aucun groupe identifié avec cet identifiant",		stack : null});
errors.push({status : 404, code : 'GROUP.1.7.4',  message : "Un login existe déjà dans le groupe désigné",		stack : null});
errors.push({status : 404, code : 'GROUP.1.7.5',  message : "Impossible de trouver le groupe après la mise à jour",	stack : null});
errors.push({status : 404, code : 'GROUP.1.8.1',  message : "L'identifiant ID du groupe est absent",					stack : null});
errors.push({status : 404, code : 'GROUP.1.8.2',  message : "Le login à ajouter dans les responsables du groupe est absent",		stack : null});
errors.push({status : 404, code : 'GROUP.1.8.3',  message : "La responsabilité à ajouter dans le groupe pour ce login est absente",	stack : null});
errors.push({status : 404, code : 'GROUP.1.8.4',  message : "Aucun groupe identifié avec cet identifiant",				stack : null});
errors.push({status : 404, code : 'GROUP.1.8.5',  message : "Le login a déjà cette responsabilité d'affectée dans le groupe désigné",	stack : null});
errors.push({status : 404, code : 'GROUP.1.8.6',  message : "Impossible de trouver le groupe après la mise à jour",			stack : null});
errors.push({status : 404, code : 'GROUP.1.9.1',  message : "L'identifiant ID du groupe est absent",				stack : null});
errors.push({status : 404, code : 'GROUP.1.9.2',  message : "Le login du membre à retirer est absent",				stack : null});
errors.push({status : 404, code : 'GROUP.1.9.3',  message : "Aucun groupe identifié avec cet identifiant",			stack : null});
errors.push({status : 404, code : 'GROUP.1.9.4',  message : "Aucun membre avec ce login n'existe pour le groupe désigné",	stack : null});
errors.push({status : 404, code : 'GROUP.1.9.5',  message : "Impossible de trouver le groupe après la mise à jour",		stack : null});
errors.push({status : 404, code : 'GROUP.1.10.1', message : "L'identifiant ID du groupe est absent",						stack : null});
errors.push({status : 404, code : 'GROUP.1.10.2', message : "Le login du responsable à retirer est absent",					stack : null});
errors.push({status : 404, code : 'GROUP.1.10.3', message : "La responsabilité du responsable à retirer est absente",				stack : null});
errors.push({status : 404, code : 'GROUP.1.10.4', message : "Aucun groupe identifié avec cet identifiant",					stack : null});
errors.push({status : 404, code : 'GROUP.1.10.5', message : "Aucun responsable avec cette responsabilité n'existe pour le groupe désigné",	stack : null});
errors.push({status : 404, code : 'GROUP.1.10.6', message : "Impossible de trouver le groupe après la mise à jour",				stack : null});


module.exports = findError(errors);
