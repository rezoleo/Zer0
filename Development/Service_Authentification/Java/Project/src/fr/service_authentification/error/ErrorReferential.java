package fr.service_authentification.error;

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
 * List of all the errors returned by the Authentification service
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class ErrorReferential{
	/**
	 * Unique instance since ErrorReferential class uses singleton pattern
	 */
	protected static fr.webservicecore.error.ErrorReferential instance = fr.webservicecore.error.ErrorReferential.getInstance();

	/**
	 * Constructor ErrorReferential to define of all the referenced errors
	 */	
	@SuppressWarnings("static-access")
	public ErrorReferential(){
		instance.addError("AUTH.1.1.1", "Le login de la personne n'a pas été précisé");
		instance.addError("AUTH.1.1.2", "Aucun accès accessible avec ce login");
		instance.addError("AUTH.1.2.1", "Le login de la personne n'a pas été précisé");
		instance.addError("AUTH.1.2.2", "Le mail de la personne n'a pas été précisé");
		instance.addError("AUTH.1.2.3", "Le mot de passe n'a pas été précisé");
		instance.addError("AUTH.1.2.4", "Le statut n'a pas été précisé");
		instance.addError("AUTH.1.2.5", "Impossible d'ajouter cet accès dans le système comme le login est déja utilisé par un autre accès");
		instance.addError("AUTH.1.2.6", "Impossible d'ajouter cet accès dans le système comme le mail est déja utilisé par un autre accès");
		instance.addError("AUTH.1.2.7", "Impossible d'ajouter l'accès dans le système");
		instance.addError("AUTH.1.3.1", "Le login de la personne n'a pas été précisé");
		instance.addError("AUTH.1.3.2", "Le mot de passe n'a pas été précisé");
		instance.addError("AUTH.1.3.3", "Aucun accès accessible avec ces identifiants");
		instance.addError("AUTH.1.3.4", "L'accès pour ce login a été désactivé");
		instance.addError("AUTH.1.4.1", "Le login de la personne n'a pas été précisé");
		instance.addError("AUTH.1.4.2", "Le mail de la personne n'a pas été précisé");
		instance.addError("AUTH.1.4.3", "Le mot de passe n'a pas été précisé");
		instance.addError("AUTH.1.4.4", "Le statut n'a pas été précisé");
		instance.addError("AUTH.1.4.5", "Aucun accès accessible avec ce login");
		instance.addError("AUTH.1.4.6", "Impossible de mettre à jour cet accès comme le mail est déja utilisé par un autre accès");
		instance.addError("AUTH.1.4.7", "Impossible de trouver l'accès après la mise à jour");
		instance.addError("AUTH.1.5.1", "Le login de la personne est absent");
		instance.addError("AUTH.1.5.2", "Aucun accès identifié avec ce login");
	}
}