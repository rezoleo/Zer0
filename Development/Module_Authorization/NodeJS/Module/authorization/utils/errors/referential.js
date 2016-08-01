/* 
 * File 	: ./utils/errors/referential.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the registered error of the NodeJS module for managing authorization
 * Version  	: 1.0.0
 */


var findError = require('toolbox')('ERROR_REFERENTIAL');

var errors = [];
errors.push({status : 404, code : 'AUTHORIZATION.1.1.1', message : "La catégorie n'existe pas",	stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.1.2.1', message : "Le login est absent",	stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.1.2.2', message : "La catégorie n'existe pas",	stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.1.3.1', message : "Le login est absent", 										stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.1.3.2', message : "Impossible d'ajouter l'accès dans le système comme le login est déja utilisé par un autre accès", 	stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.1.3.3', message : "Impossible d'ajouter cet accès dans le système", 							stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.1.4.1', message : "Le login de l'accès est absent", 		stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.1.4.2', message : "Aucun accès trouvé pour cet identifiant", 	stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.1.4.3', message : "La catégorie n'existe pas",	stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.1.5.1', message : "Le login de l'accès est absent", 		stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.1.5.2', message : "Aucun accès trouvé pour cet identifiant", 	stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.1.5.3', message : "La catégorie n'existe pas",	stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.1.1', message : "Le login de l'accès est absent", 					stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.1.2', message : "Le droit à ajouter pour l'identifant transmis est absent", 		stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.1.3', message : "La catégorie n'existe pas", 		stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.1.4', message : "Le role n'appartient pas à la catégorie précisée", 		stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.1.5', message : "Aucun accès trouvé pour cet identifiant", 				stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.1.6', message : "Un accès avec ce droit existe déjà pour le login désigné", 		stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.1.7', message : "Impossible de trouver l'accès après la mise à jour", 		stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.2.1', message : "Le login de l'accès est absent", 							stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.2.2', message : "Le droit à retirer pour l'identifant transmis est absent", 			stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.2.3', message : "La catégorie n'existe pas", 		stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.2.4', message : "Le role n'appartient pas à la catégorie précisée", 		stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.2.5', message : "Aucun accès trouvé pour cet identifiant", 					stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.2.6', message : "Aucun role avec le nom transmis n'existe pour cet identifiant", 		stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.2.7', message : "Impossible de trouver l'accès après la mise à jour", 			stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.3.1', message : "Le login de l'accès est absent", 					stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.3.2', message : "La liste des roles à retirer est absente", 				stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.3.3', message : "La liste des roles à retirer n'est pas correcte", 			stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.3.4', message : "La liste des roles à retirer est vide", 				stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.3.5', message : "Aucun accès trouvé pour cet identifiant", 				stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.4.1', message : "Le login de l'accès est absent", 					stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.4.2', message : "Le droit à appliquer pour l'identifant transmis est absent", 				stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.4.3', message : "La catégorie n'existe pas", 		stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.4.4', message : "Le role n'appartient pas à la catégorie précisée", 		stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.4.5', message : "Le droit à appliquer n'a pas de catégorie rattachée", 		stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.4.6', message : "Le droit à appliquer n'appartient pas à la catégorie spécifiée", 		stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.4.7', message : "Aucun accès trouvé pour cet identifiant", 					stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.4.8', message : "Aucun accès n'existe pour ce login",					stack : null});
errors.push({status : 404, code : 'AUTHORIZATION.2.4.9', message : "Aucun accès n'existe pour ce login",					stack : null});

module.exports = findError(errors);
