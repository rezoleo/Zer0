/* 
 * File 	: ./server/errors/referential.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the registered error of the NodeJS module for managing the webservice
 * Version  	: 1.0.0
 */


var findError = require('toolbox')('ERROR_REFERENTIAL');

var errors = [];
errors.push({status : 404, code : 'ADMIN.1.1.1',  message : "Le login de la personne n'a pas été précisé",			stack : null});
errors.push({status : 404, code : 'ADMIN.1.1.2',  message : "Le mot de passe n'a pas été précisé",				stack : null});
errors.push({status : 404, code : 'ADMIN.1.1.3',  message : "Aucun accès n'a été trouvé avec ce login pour l'application",	stack : null});
errors.push({status : 404, code : 'ADMIN.1.1.4',  message : "Aucun accès n'a été trouvé avec ces informations de connexion",	stack : null});
errors.push({status : 404, code : 'ADMIN.2.1.1',  message : "L'utilisateur n'a aucun portrait d'associée",	stack : null});
errors.push({status : 404, code : 'ADMIN.2.2.1',  message : "Le formulaire est absent",								stack : null});
errors.push({status : 404, code : 'ADMIN.2.2.2',  message : "Le fichier avec pour nom 'file' n'a pas été transmis à travers la requête",	stack : null});
errors.push({status : 404, code : 'ADMIN.2.2.3',  message : "Les propriétés du fichier transmis sont incomplètes",				stack : null});
errors.push({status : 404, code : 'ADMIN.2.3.1',  message : "L'utilisateur n'a aucun portrait d'associée",	stack : null});
errors.push({status : 404, code : 'ADMIN.3.1.1',  message : "La catégorie n'a pas été précisée",				stack : null});
errors.push({status : 404, code : 'ADMIN.3.1.2',  message : "La catégorie précisée n'existe pas",				stack : null});
errors.push({status : 404, code : 'ADMIN.3.1.3',  message : "Aucune liste d'autorizations n'a été retournée",			stack : null});
errors.push({status : 404, code : 'ADMIN.3.2.1',  message : "La catégorie n'a pas été précisée",					stack : null});
errors.push({status : 404, code : 'ADMIN.3.2.2',  message : "La catégorie précisée n'existe pas",					stack : null});
errors.push({status : 404, code : 'ADMIN.3.2.3',  message : "Le login de la personne n'a pas été précisé",				stack : null});
errors.push({status : 404, code : 'ADMIN.3.2.4',  message : "Le role à attribuer n'a pas été précisé",					stack : null});
errors.push({status : 404, code : 'ADMIN.3.2.5',  message : "Le role précisé n'est pas recensé dans la catégorie choisie",		stack : null});
errors.push({status : 404, code : 'ADMIN.3.3.1',  message : "La catégorie n'a pas été précisée",				stack : null});
errors.push({status : 404, code : 'ADMIN.3.3.2',  message : "La catégorie précisée n'existe pas",				stack : null});
errors.push({status : 404, code : 'ADMIN.3.3.3',  message : "Le login de la personne n'a pas été précisé",			stack : null});
errors.push({status : 404, code : 'ADMIN.3.3.4',  message : "Aucun accès n'existe pour ce login",				stack : null});
errors.push({status : 404, code : 'ADMIN.A.1.1',  message : "Le connecteur vers le service des authentifiants a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'ADMIN.A.2.1',  message : "Le connecteur vers le service des authentifiants a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'ADMIN.A.3.1',  message : "Le connecteur vers le service des authentifiants a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'ADMIN.A.4.1',  message : "Le connecteur vers le service des authentifiants a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'ADMIN.B.1.1',  message : "Le connecteur vers le service des cartes a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'ADMIN.B.2.1',  message : "Le connecteur vers le service des cartes a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'ADMIN.B.3.1',  message : "Le connecteur vers le service des cartes a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'ADMIN.B.4.1',  message : "Le connecteur vers le service des cartes a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'ADMIN.C.1.1',  message : "Le connecteur vers le service des cotisants a généré une uri avec au moins un caractère interdit",		stack : null});
errors.push({status : 404, code : 'ADMIN.C.2.1',  message : "Le connecteur vers le service des cotisants a généré une uri avec au moins un caractère interdit",		stack : null});
errors.push({status : 404, code : 'ADMIN.C.3.1',  message : "Le connecteur vers le service des cotisants a généré une uri avec au moins un caractère interdit",		stack : null});
errors.push({status : 404, code : 'ADMIN.D.1.1',  message : "Le connecteur vers le service des groupes a généré une uri avec au moins un caractère interdit", stack : null});
errors.push({status : 404, code : 'ADMIN.D.2.1',  message : "Le connecteur vers le service des groupes a généré une uri avec au moins un caractère interdit", stack : null});
errors.push({status : 404, code : 'ADMIN.D.3.1',  message : "Le connecteur vers le service des groupes a généré une uri avec au moins un caractère interdit", stack : null});
errors.push({status : 404, code : 'ADMIN.D.4.1',  message : "Le connecteur vers le service des groupes a généré une uri avec au moins un caractère interdit", stack : null});
errors.push({status : 404, code : 'ADMIN.D.5.1',  message : "Le connecteur vers le service des groupes a généré une uri avec au moins un caractère interdit", stack : null});
errors.push({status : 404, code : 'ADMIN.D.6.1',  message : "Le connecteur vers le service des groupes a généré une uri avec au moins un caractère interdit", stack : null});
errors.push({status : 404, code : 'ADMIN.D.7.1',  message : "Le connecteur vers le service des groupes a généré une uri avec au moins un caractère interdit", stack : null});
errors.push({status : 404, code : 'ADMIN.D.8.1',  message : "Le connecteur vers le service des groupes a généré une uri avec au moins un caractère interdit", stack : null});
errors.push({status : 404, code : 'ADMIN.D.9.1',  message : "Le connecteur vers le service des groupes a généré une uri avec au moins un caractère interdit", stack : null});
errors.push({status : 404, code : 'ADMIN.E.1.1',  message : "Le connecteur vers le service des personnes a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'ADMIN.E.2.1',  message : "Le connecteur vers le service des personnes a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'ADMIN.E.3.1',  message : "Le connecteur vers le service des personnes a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'ADMIN.E.4.1',  message : "Le connecteur vers le service des personnes a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'ADMIN.E.5.1',  message : "Le connecteur vers le service des personnes a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'ADMIN.E.6.1',  message : "Le connecteur vers le service des personnes a généré une uri avec au moins un caractère interdit",	stack : null});
errors.push({status : 404, code : 'ADMIN.F.1.1',  message : "Le connecteur vers le service des images a généré une uri avec au moins un caractère interdit ou alors plus de un seul '/'", stack : null});
errors.push({status : 404, code : 'ADMIN.F.2.1',  message : "Le connecteur vers le service des images a généré une uri avec au moins un caractère interdit", 							  stack : null});
errors.push({status : 404, code : 'ADMIN.F.3.1',  message : "Le connecteur vers le service des images a généré une uri avec au moins un caractère interdit ou alors plus de un seul '/'", stack : null});


module.exports = findError(errors);
