package fr.webservicecore.error;

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


import fr.webservicecore.error.Error;

/**
 * List of referenced errors that a server using the WebServiceCore module for a service can return
 * <p>
 * This class implements a singleton pattern
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class ErrorReferential extends fr.core.error.ErrorReferential
{
	/**
	 * Unique instance since ErrorReferential class uses singleton pattern
	 */
	private static volatile ErrorReferential instance = null;

	/**
	 * Flag to indicate if 'putErrors' function has already been called
	 */
	private	static boolean				 	 flag	  = false;

	/**
	 * Constructor ErrorReferential, protected since ErrorReferential class uses singleton pattern
	 */
	protected ErrorReferential(){
		super();
		if(!flag){
			putErrors();
		}
	}

	/**
	 * Get the unique instance since ErrorReferential class uses singleton pattern
	 * @return ErrorReferential instance
	 */
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

	/**
	 * Define all the errors for the WebServiceCore module
	 */
	private synchronized static void putErrors(){
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

	/**
	 * Add an error in the list
	 * @param code The code of the error to add
	 * @param msg The message of the error to add 
	 */
	public static void addError(String code, String msg){
		addError(new Error(code, msg));
	}

	/**
	 * Find an error with its code
	 * @param code The code of the error looked for
	 * @return The Error instance with a code equals of the one given in parameter 
	 */
	public static Error getErrorByCode(String code){
		if(!flag){
			putErrors();
		}
		return (Error)fr.core.error.ErrorReferential.getErrorByCode(code);
	}
}