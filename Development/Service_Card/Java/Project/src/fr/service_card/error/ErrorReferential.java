package fr.service_card.error;

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
 * Function : This class describes all the errors returned by the card service
 * Version  : 1.0.0
 */
public class ErrorReferential{
	protected static fr.webservicecore.error.ErrorReferential instance = fr.webservicecore.error.ErrorReferential.getInstance();
	
	//Definition of all the referenced errors	
	@SuppressWarnings("static-access")
	public ErrorReferential(){
		instance.addError("CARD.1.1.1", "L'identifiant ID de la carte est absent");
		instance.addError("CARD.1.1.2", "Aucune carte identifiée avec cet identifiant");
		instance.addError("CARD.1.2.1", "Le code de la carte est absent");
		instance.addError("CARD.1.2.2",	"Aucune carte identifiée avec ce code");
		instance.addError("CARD.1.3.1",	"Le propriétaire de la carte n'a pas été précisé");
		instance.addError("CARD.1.3.2",	"Le code de la carte n'a pas été précisé");
		instance.addError("CARD.1.3.3",	"Le status de la carte n'a pas été précisé");
		instance.addError("CARD.1.3.4",	"Impossible d'ajouter la carte dans le système comme le code est déja utilisé par une autre carte");
		instance.addError("CARD.1.3.5",	"Impossible d'ajouter la carte dans le système");
		instance.addError("CARD.1.4.1",	"L'identifiant ID de la carte est absent");
		instance.addError("CARD.1.4.2",	"Le propriétaire de la carte n'a pas été précisé");
		instance.addError("CARD.1.4.3",	"Le code de la carte n'a pas été précisé");
		instance.addError("CARD.1.4.4",	"Le status de la carte n'a pas été précisé");
		instance.addError("CARD.1.4.5",	"Aucune carte identifiée avec cet identifiant");
		instance.addError("CARD.1.4.6",	"Impossible de mettre à jour les données de la carte comme le nouveau code est déja utilisé par une autre carte");
		instance.addError("CARD.1.4.7",	"Impossible de trouver la carte après la mise à jour");
		instance.addError("CARD.1.5.1",	"L'identifiant ID de la carte est absent");
		instance.addError("CARD.1.5.2",	"Aucune carte identifiée avec cet identifiant");
	}
}