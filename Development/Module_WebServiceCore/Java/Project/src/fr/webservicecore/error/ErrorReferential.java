package fr.webservicecore.error;

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


import fr.webservicecore.error.Error;

/* 
 * Class 	: ErrorReferential
 * Author(s): Zidmann
 * Function : This class describes all the errors returned by a server using the WebServiceCore module
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

	//Definition of all the errors for the WebServiceCore module
	protected static void putErrors(){
		fr.core.error.ErrorReferential.putErrors();
		if(!flag){
			flag = true;

			addError("WSCORE.1.1.1", "Api inexistante");
			addError("WSCORE.2.1.1", "Api inexistante");
			addError("WSCORE.3.1.1", "Le jeton transmis est invalide ou absent");
			addError("WSCORE.3.1.2", "L'adresse IP du client n'est pas compatible avec ce jeton");
			addError("WSCORE.3.1.3", "Le nom de service source n'est pas compatible avec ce jeton");
			addError("WSCORE.3.1.4", "Le nom de service de destination n'est pas compatible avec ce jeton");
			addError("WSCORE.3.1.5", "La période d'utisation n'est pas exploitable avec ce jeton");
			addError("WSCORE.3.1.6", "Le jeton ne contient aucune règle d'accès");
			addError("WSCORE.3.1.7", "Le jeton n'est pas reconnu dans la base de données");
			addError("WSCORE.3.1.8", "Le jeton ne dispose pas des droits suffisants pour accéder à cet API");
			addError("WSCORE.3.1.9", "Erreur lors de la vérification des permissions");
			addError("WSCORE.3.1.10","Erreur lors de l'exécution du contrôleur");

			addError("WSCORE-JAR-1", "Un des paramètres est vide");
			addError("WSCORE-JAR-2", "Un des paramètres comporte un caractère interdit");
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
			if(errorListRef.get(code) instanceof Error)
			{
				return (Error) errorListRef.get(code);
			}
		}
		return null;
	}
}