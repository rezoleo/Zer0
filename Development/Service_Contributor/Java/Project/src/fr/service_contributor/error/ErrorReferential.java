package fr.service_contributor.error;

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
 * Function : This class describes all the errors returned by the contributor service
 * Version  : 1.0.0
 */
public class ErrorReferential{
	protected static fr.webservicecore.error.ErrorReferential instance = fr.webservicecore.error.ErrorReferential.getInstance();
	
	//Definition of all the referenced errors	
	@SuppressWarnings("static-access")
	public ErrorReferential(){
		instance.addError("CONTRIBUTOR.1.1.1", "L'identifiant ID du cotisant est absent");
		instance.addError("CONTRIBUTOR.1.1.2", "Aucun cotisant identifiée avec cet identifiant");
		instance.addError("CONTRIBUTOR.1.2.1", "Le login du cotisant est absent");
		instance.addError("CONTRIBUTOR.1.2.2", "Une erreur est survenue lors de la recherche du cotisant");
		instance.addError("CONTRIBUTOR.1.2.3", "Aucun cotisant identifié avec ce login");
		instance.addError("CONTRIBUTOR.1.3.1", "Le login du cotisant n'a pas été précisé");
		instance.addError("CONTRIBUTOR.1.3.2", "Impossible d'ajouter la cotisant dans le système comme le login est déja utilisé par un cotisant");
		instance.addError("CONTRIBUTOR.1.3.3", "Impossible d'ajouter le cotisant dans le système");
		instance.addError("CONTRIBUTOR.1.4.1", "L'identifiant ID du cotisant est absent");
		instance.addError("CONTRIBUTOR.1.4.2", "Aucun cotisant identifié avec cet identifiant");
	}
}