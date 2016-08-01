package fr.service_picture.error;

/*
 * Copyright 2015-2016 Emmanuel ZIDEL-CAUFFET
 *
 * This class is used in a project designed by some Ecole Centrale de Lille students.
 * This program is distributed in the hope that it will be useful.
 * 
 * It is a free code: you can redistribute it and/or modify it under the terms of the GNU General Public License 
 * as published by the Free Software Foundation, either Version 3 of the License.
 *
 * However the source code is distributed without any warranty
 * See the GNU General Public License for more details.
 *
 */


/* 
 * Class 	: ErrorReferential
 * Author(s): Zidmann
 * Function : This class describes all the errors returned by the picture service
 * Version  : 1.0.0
 */
public class ErrorReferential{
	protected static fr.webservicecore.error.ErrorReferential instance = fr.webservicecore.error.ErrorReferential.getInstance();

	//Definition of all the referenced errors	
	@SuppressWarnings("static-access")
	public ErrorReferential() {
		instance.addError("PICTURE.1.1.1",  "Le répertoire du fichier est absent");
		instance.addError("PICTURE.1.1.2",  "Le nom du fichier est absent");
		instance.addError("PICTURE.1.1.3",  "Le répertoire du fichier contient des caractères non-autorisés");
		instance.addError("PICTURE.1.1.4",  "Le nom du fichier ne respecte pas la règle de gestion");
		instance.addError("PICTURE.1.1.5",  "L'extension du fichier ne respecte pas la règle de gestion");
		instance.addError("PICTURE.1.1.6",  "Le jeton transmis ne contient pas les droits pour accéder à ce répertoire");
		instance.addError("PICTURE.1.1.7",  "Erreur lors de la lecture du fichier source");
		instance.addError("PICTURE.1.1.8",  "Erreur lors du calcul du checksum");
		instance.addError("PICTURE.1.2.1",  "Le répertoire du fichier est absent");
		instance.addError("PICTURE.1.2.2",  "Le répertoire du fichier contient des caractères non-autorisés");
		instance.addError("PICTURE.1.2.3",  "Le jeton transmis ne contient pas les droits pour accéder à ce répertoire");
		instance.addError("PICTURE.1.2.4",  "Le formulaire est absent");
		instance.addError("PICTURE.1.2.5",  "Le fichier avec pour nom 'file' n'a pas été transmis à travers la requête");
		instance.addError("PICTURE.1.2.6",  "Les propriétés du fichier transmis sont incomplètes");
		instance.addError("PICTURE.1.2.7",  "La taille du fichier transmis est supérieure à la limite autorisée");
		instance.addError("PICTURE.1.2.8",  "Le nom du fichier ne respecte pas la règle de gestion");
		instance.addError("PICTURE.1.2.9",  "L'extension du fichier ne respecte pas la règle de gestion");
		instance.addError("PICTURE.1.2.10", "Le fichier source ne peut-être lu");
		instance.addError("PICTURE.1.2.11", "Erreur lors de l'écriture du fichier final");
		instance.addError("PICTURE.1.2.12", "Erreur lors du calcul du checksum");
		instance.addError("PICTURE.1.3.1",  "Le répertoire du fichier est absent");
		instance.addError("PICTURE.1.3.2",  "Le nom du fichier est absent");
		instance.addError("PICTURE.1.3.3",  "Le répertoire du fichier contient des caractères non-autorisés");
		instance.addError("PICTURE.1.3.4",  "Le nom du fichier ne respecte pas la règle de gestion");
		instance.addError("PICTURE.1.3.5",  "L'extension du fichier ne respecte pas la règle de gestion");
		instance.addError("PICTURE.1.3.6",  "Le jeton transmis ne contient pas les droits pour accéder à ce répertoire");
		instance.addError("PICTURE.1.3.7",  "Erreur lors de la lecture du fichier source");
		instance.addError("PICTURE.1.3.8",  "Erreur lors du calcul du checksum");
		instance.addError("PICTURE.1.3.9",  "Erreur lors de la suppression du fichier");
		instance.addError("PICTURE.A.1.1",  "Le connecteur qui redirige vers l'image a généré une uri avec au moins un caractère interdit");
	}
}