/*
 * File 	: ./server/errors/referential.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the registered error of the NodeJS module for managing the web application
 * Version  	: 1.0.0
 */


var findError = require('toolbox')('ERROR_REFERENTIAL');

var errors = [];

errors.push({status : 404, code : 'PICTURE.1.1.1',  message : "Le répertoire du fichier est absent",						stack : null});
errors.push({status : 404, code : 'PICTURE.1.1.2',  message : "Le nom du fichier est absent",							stack : null});
errors.push({status : 404, code : 'PICTURE.1.1.3',  message : "Le répertoire du fichier contient des caractères non-autorisés",			stack : null});
errors.push({status : 404, code : 'PICTURE.1.1.4',  message : "Le nom du fichier ne respecte pas la règle de gestion",				stack : null});
errors.push({status : 404, code : 'PICTURE.1.1.5',  message : "L'extension du fichier ne respecte pas la règle de gestion",			stack : null});
errors.push({status : 404, code : 'PICTURE.1.1.6',  message : "Le jeton transmis ne contient pas les droits pour accéder à ce répertoire",	stack : null});
errors.push({status : 404, code : 'PICTURE.1.1.7',  message : "Erreur lors de la lecture du fichier source",					stack : null});
errors.push({status : 404, code : 'PICTURE.1.1.8',  message : "Erreur lors du calcul du checksum",						stack : null});
errors.push({status : 404, code : 'PICTURE.1.2.1',  message : "Le répertoire du fichier est absent",							stack : null});
errors.push({status : 404, code : 'PICTURE.1.2.2',  message : "Le répertoire du fichier contient des caractères non-autorisés",				stack : null});
errors.push({status : 404, code : 'PICTURE.1.2.3',  message : "Le jeton transmis ne contient pas les droits pour accéder à ce répertoire",		stack : null});
errors.push({status : 404, code : 'PICTURE.1.2.4',  message : "Le formulaire est absent",								stack : null});
errors.push({status : 404, code : 'PICTURE.1.2.5',  message : "Le fichier avec pour nom 'file' n'a pas été transmis à travers la requête",		stack : null});
errors.push({status : 404, code : 'PICTURE.1.2.6',  message : "Les propriétés du fichier transmis sont incomplètes",					stack : null});
errors.push({status : 404, code : 'PICTURE.1.2.7',  message : "La taille du fichier transmis est supérieure à la limite autorisée",			stack : null});
errors.push({status : 404, code : 'PICTURE.1.2.8',  message : "Le nom du fichier ne respecte pas la règle de gestion",					stack : null});
errors.push({status : 404, code : 'PICTURE.1.2.9',  message : "L'extension du fichier ne respecte pas la règle de gestion",				stack : null});
errors.push({status : 404, code : 'PICTURE.1.2.10', message : "Le fichier source ne peut-être lu",							stack : null});
errors.push({status : 404, code : 'PICTURE.1.2.11', message : "Erreur lors de l'écriture du fichier final",						stack : null});
errors.push({status : 404, code : 'PICTURE.1.2.12', message : "Erreur lors du calcul du checksum",							stack : null});
errors.push({status : 404, code : 'PICTURE.1.3.1',  message : "Le répertoire du fichier est absent",						stack : null});
errors.push({status : 404, code : 'PICTURE.1.3.2',  message : "Le nom du fichier est absent",							stack : null});
errors.push({status : 404, code : 'PICTURE.1.3.3',  message : "Le répertoire du fichier contient des caractères non-autorisés",			stack : null});
errors.push({status : 404, code : 'PICTURE.1.3.4',  message : "Le nom du fichier ne respecte pas la règle de gestion",				stack : null});
errors.push({status : 404, code : 'PICTURE.1.3.5',  message : "L'extension du fichier ne respecte pas la règle de gestion",			stack : null});
errors.push({status : 404, code : 'PICTURE.1.3.6',  message : "Le jeton transmis ne contient pas les droits pour accéder à ce répertoire",	stack : null});
errors.push({status : 404, code : 'PICTURE.1.3.7',  message : "Erreur lors de la lecture du fichier source",					stack : null});
errors.push({status : 404, code : 'PICTURE.1.3.8',  message : "Erreur lors du calcul du checksum",						stack : null});
errors.push({status : 404, code : 'PICTURE.1.3.9',  message : "Erreur lors de la suppression du fichier",					stack : null});
errors.push({status : 404, code : 'PICTURE.A.1.1',  message : "Le connecteur qui redirige vers l'image a généré une uri avec au moins un caractère interdit",	stack : null});


module.exports = findError(errors);
