package fr.service_alert.error;

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
 * List of all the errors returned by the Alert service
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class ErrorReferential{
	/**
	 * Unique instance since ErrorReferential class uses singleton pattern
	 */
	private static fr.webservicecore.error.ErrorReferential instance = fr.webservicecore.error.ErrorReferential.getInstance();
	
	/**
	 * Constructor ErrorReferential to define of all the referenced errors
	 */
	@SuppressWarnings("static-access")
	public ErrorReferential(){
		instance.addError("ALERT.1.1.1", "Le niveau d'alerte ne correspond à aucun référencé");
		instance.addError("ALERT.1.2.1", "L'identifiant ID de l'alerte est absent");
		instance.addError("ALERT.1.2.2", "Aucune alerte identifiée avec cet identifiant");
		instance.addError("ALERT.1.3.1", "Le message de l'alerte n'a pas été précisé");
		instance.addError("ALERT.1.3.2", "Le niveau de l'alerte n'a pas été précisé");
		instance.addError("ALERT.1.3.3", "Impossible d'ajouter l'alerte dans le système");
	}
}