package fr.service_people.error;

/*
 * Copyright 2015-2017 Emmanuel ZIDEL-CAUFFET
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


/**
 * List of all the errors returned by the People service
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class ErrorReferential{
	private static fr.webservicecore.error.ErrorReferential instance = fr.webservicecore.error.ErrorReferential.getInstance();

	//Definition of all the referenced errors	
	@SuppressWarnings("static-access")
	public ErrorReferential() {
		instance.addError("PEOPLE.1.1.1", "L'identifiant ID de la personne est absent");
		instance.addError("PEOPLE.1.1.2", "Aucune personne identifiée avec cet identifiant");
		instance.addError("PEOPLE.1.2.1", "Le login de la personne est absent");
		instance.addError("PEOPLE.1.2.2", "Aucune personne identifiée avec ce login");
		instance.addError("PEOPLE.1.3.1", "L'e-mail de la personne est absent");
		instance.addError("PEOPLE.1.3.2", "Aucune personne identifiée avec cet e-mail");
		instance.addError("PEOPLE.1.4.1", "Le login de la personne n'a pas été précisé");
		instance.addError("PEOPLE.1.4.2", "Le nom de famille de la personne n'a pas été précisé");
		instance.addError("PEOPLE.1.4.3", "Le prénom de la personne n'a pas été précisé");
		instance.addError("PEOPLE.1.4.4", "Impossible d'ajouter la personne dans le système comme le login est déja utilisé par une autre personne");
		instance.addError("PEOPLE.1.4.5", "Impossible d'ajouter la personne dans le système comme l'e-mail est déja utilisé par une autre personne");
		instance.addError("PEOPLE.1.4.6", "Impossible d'ajouter la personne dans le système");
		instance.addError("PEOPLE.1.5.1", "L'identifiant ID de la personne est absent");
		instance.addError("PEOPLE.1.5.2", "Le tag à ajouter pour la personne est absent");
		instance.addError("PEOPLE.1.5.3", "Aucune personne identifiée avec cet identifiant");
		instance.addError("PEOPLE.1.5.4", "Un tag avec ce libellé existe déjà pour l'utilisateur désigné");
		instance.addError("PEOPLE.1.5.5", "Impossible de trouver la personne après la mise à jour");
		instance.addError("PEOPLE.1.6.1", "L'identifiant ID de la personne est absent");
		instance.addError("PEOPLE.1.6.2", "Le login de la personne n'a pas été précisé");
		instance.addError("PEOPLE.1.6.3", "Le nom de famille de la personne n'a pas été précisé");
		instance.addError("PEOPLE.1.6.4", "Le prénom de la personne n'a pas été précisé");
		instance.addError("PEOPLE.1.6.5", "Aucune personne identifiée avec cet identifiant");
		instance.addError("PEOPLE.1.6.6", "Impossible d'ajouter la personne dans le système comme le login est déja utilisé par une autre personne");
		instance.addError("PEOPLE.1.6.7", "Impossible d'ajouter la personne dans le système comme l'e-mail est déja utilisé par une autre personne");
		instance.addError("PEOPLE.1.6.8", "Impossible de trouver la personne après la mise à jour");
		instance.addError("PEOPLE.1.7.1", "L'identifiant ID de la personne est absent");
		instance.addError("PEOPLE.1.7.2", "Le tag à retirer pour la personne est absent");
		instance.addError("PEOPLE.1.7.3", "Aucune personne identifiée avec cet identifiant");
		instance.addError("PEOPLE.1.7.4", "Aucun tag avec ce libellé n'existe pour l'utilisateur désigné");
		instance.addError("PEOPLE.1.7.5", "Impossible de trouver la personne après la mise à jour");
		instance.addError("PEOPLE.1.8.1", "L'identifiant ID de la personne est absent");
		instance.addError("PEOPLE.1.8.2", "Aucune personne identifiée avec cet identifiant");
	}
}