package fr.applicationcore.error;

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


import fr.applicationcore.error.Error;

/* 
 * Class 	: ErrorReferential
 * Author(s): Zidmann
 * Function : This class describes all the errors returned by a server using the ApplicationCore module
 * Version  : 1.0.0
 */
public class ErrorReferential extends fr.core.error.ErrorReferential
{
	private 	static volatile ErrorReferential instance = null;
	protected	static boolean				 	 flag	  = false;

	protected ErrorReferential(){
		super();
		if(!flag){
			putErrors();
		}
	}

	public static ErrorReferential getInstance(){
		if (ErrorReferential.instance == null){
			synchronized(ErrorReferential.class){
				if (ErrorReferential.instance == null){
					ErrorReferential.instance = new ErrorReferential();
				}
			}
		}
		return ErrorReferential.instance;
	}

	//Definition of all the errors for the ApplicationCore module
	protected static void putErrors(){
		fr.core.error.ErrorReferential.putErrors();
		if(!flag){
			flag = true;

			addError("APPCORE.1.1.1", "Api inexistante");
			addError("APPCORE.2.1.1", "Api inexistante");
			addError("APPCORE.3.1.1", "Fonctionnalité refusée; identification requise");
			addError("APPCORE.3.1.2", "Fonctionnalité refusée; la méthode d'accès n'est pas acceptée");
			addError("APPCORE.3.1.3", "Fonctionnalité refusée; autorisation requise");
			addError("APPCORE.3.1.4", "Fonctionnalité refusée; condition d'accès non satisfaite");
			addError("APPCORE.3.1.5", "Erreur lors de la vérification des permissions");
			addError("APPCORE.3.1.6", "Erreur lors de l'exécution du contrôleur");

			addError("APPCORE-JAR-1", "Un des paramètres est vide");
			addError("APPCORE-JAR-2", "Un des paramètres comporte un caractère interdit");
		}
	}

	//Functions to add an error in the list
	public static void addError(String code, String msg){
		addError(new Error(code, msg));
	}

	//Functions to find an error with its reference
	public static Error getErrorByCode(String code){
		if(!flag){
			putErrors();
		}
		if(code!=null && errorListRef.containsKey(code)){
			if(errorListRef.get(code) instanceof Error){
				return (Error) errorListRef.get(code);
			}
		}
		return null;
	}
}