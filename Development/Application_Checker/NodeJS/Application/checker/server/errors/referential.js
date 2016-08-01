/* 
 * File 	: ./server/errors/referential.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the registered error of the NodeJS module for managing the webservice
 * Version  	: 1.0.0
 */


var findError = require('toolbox')('ERROR_REFERENTIAL');

var errors = [];
errors.push({status : 404, code : 'CHECKER.1.1.1',  message : "Le login de la personne n'a pas été précisé",			stack : null});
errors.push({status : 404, code : 'CHECKER.1.1.2',  message : "Le mot de passe n'a pas été précisé",				stack : null});
errors.push({status : 404, code : 'CHECKER.1.1.3',  message : "Aucun accès n'a été trouvé avec ce login pour l'application",	stack : null});
errors.push({status : 404, code : 'CHECKER.1.1.4',  message : "Aucun accès n'a été trouvé avec ces informations de connexion",	stack : null});
errors.push({status : 404, code : 'CHECKER.2.1.1',  message : "La catégorie n'a pas été précisée",				stack : null});
errors.push({status : 404, code : 'CHECKER.2.1.2',  message : "La catégorie précisée n'existe pas",				stack : null});
errors.push({status : 404, code : 'CHECKER.2.1.3',  message : "Aucune liste d'autorizations n'a été retournée",			stack : null});
errors.push({status : 404, code : 'CHECKER.2.2.1',  message : "La catégorie n'a pas été précisée",					stack : null});
errors.push({status : 404, code : 'CHECKER.2.2.2',  message : "La catégorie précisée n'existe pas",					stack : null});
errors.push({status : 404, code : 'CHECKER.2.2.3',  message : "Le login de la personne n'a pas été précisé",				stack : null});
errors.push({status : 404, code : 'CHECKER.2.2.4',  message : "Le role à attribuer n'a pas été précisé",					stack : null});
errors.push({status : 404, code : 'CHECKER.2.2.5',  message : "Le role précisé n'est pas recensé dans la catégorie choisie",		stack : null});
errors.push({status : 404, code : 'CHECKER.2.3.1',  message : "La catégorie n'a pas été précisée",				stack : null});
errors.push({status : 404, code : 'CHECKER.2.3.2',  message : "La catégorie précisée n'existe pas",				stack : null});
errors.push({status : 404, code : 'CHECKER.2.3.3',  message : "Le login de la personne n'a pas été précisé",			stack : null});
errors.push({status : 404, code : 'CHECKER.2.3.4',  message : "Aucun accès n'existe pour ce login",				stack : null});
errors.push({status : 404, code : 'CHECKER.3.1.1',  message : "Le code de la carte est absent",			stack : null});
errors.push({status : 404, code : 'CHECKER.4.1.1',  message : "Le login de la personne est absent",					stack : null});
errors.push({status : 404, code : 'CHECKER.4.1.2',  message : "L'accès aux informations relative à ce login ne vous est pas autorisé",	stack : null});
errors.push({status : 404, code : 'CHECKER.5.1.1',  message : "Le login de la personne est absent",						stack : null});
errors.push({status : 404, code : 'CHECKER.5.1.2',  message : "L'accès aux informations relative à login ne vous est pas autorisé",		stack : null});
errors.push({status : 404, code : 'CHECKER.6.1.1',  message : "Le message n'a pas été précisé",			stack : null});
errors.push({status : 404, code : 'CHECKER.6.1.2',  message : "Le niveau d'importance n'a pas été précisé",	stack : null});
errors.push({status : 404, code : 'CHECKER.7.1.1',  message : "Le login de la personne n'a pas été précisé",				stack : null});
errors.push({status : 404, code : 'CHECKER.7.1.2',  message : "L'accès aux informations relative à ce login ne vous est pas autorisé",	stack : null});
errors.push({status : 404, code : 'CHECKER.7.1.3',  message : "L'utilisateur n'a aucun portrait d'associée",				stack : null});
errors.push({status : 404, code : 'CHECKER.A.1.1',  message : "Le connecteur vers le service des authentifiants a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'CHECKER.B.1.1',  message : "Le connecteur vers le service des cartes a généré une uri avec au moins un caractère interdit", stack : null});
errors.push({status : 404, code : 'CHECKER.C.1.1',  message : "Le connecteur vers le service des cotisants a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'CHECKER.D.1.1',  message : "Le connecteur vers le service des personnes a généré une uri avec au moins un caractère interdit", stack : null});
errors.push({status : 404, code : 'CHECKER.E.1.1',  message : "Le connecteur vers le service des images a généré une uri avec au moins un caractère interdit", stack : null});


module.exports = findError(errors);
