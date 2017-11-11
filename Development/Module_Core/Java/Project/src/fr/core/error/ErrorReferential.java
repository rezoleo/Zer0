package fr.core.error;

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


import java.util.Collection;
import java.util.HashMap;

/**
 * List of referenced errors that a server using the Core module for an application or a service can return
 * <p>
 * This class implements a singleton pattern
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class ErrorReferential
{
	/**
	 * Unique instance since ErrorReferential class uses singleton pattern
	 */
	private static volatile ErrorReferential instance = null;

	/**
	 * Hashmap which associates the error code to the complete Error object with the status code and the message
	 */
	private static HashMap<String, Error>	errorListRef = new HashMap<String, Error>();

	/**
	 * Flag to indicate if 'putErrors' function has already been called
	 */
	private static boolean					flag		 = false;

	/**
	 * Constructor ErrorReferential, protected since ErrorReferential class uses singleton pattern
	 */
	protected ErrorReferential(){
		if(!flag){
			putErrors();
		}
	}

	/**
	 * Get the unique instance since ErrorReferential class uses singleton pattern
	 * @return ErrorReferential instance
	 */
	public static ErrorReferential getInstance(){
		if(ErrorReferential.instance == null){
			synchronized(ErrorReferential.class){
				if(ErrorReferential.instance == null){
					ErrorReferential.instance = new ErrorReferential();
				}
			}
		}
		return ErrorReferential.instance;
	}

	/**
	 * Define all the errors common for the ApplicationCore or the WebServiceCore module
	 */
	private synchronized static void putErrors(){
		if(!flag){
			flag = true;

			addError("CORE-JAR-1", "Un des paramètres est vide");
			addError("CORE-JAR-2", "Un des paramètres comporte un caractère interdit");

			addError("AUTHORIZATION.1.1.1", "La catégorie n'existe pas");
			addError("AUTHORIZATION.1.2.1", "Le login est absent");
			addError("AUTHORIZATION.1.2.2", "La catégorie n'existe pas");
			addError("AUTHORIZATION.1.3.1", "Le login est absent");
			addError("AUTHORIZATION.1.3.2", "Impossible d'ajouter l'accès dans le système comme le login est déja utilisé par un autre accès");
			addError("AUTHORIZATION.1.3.3", "Impossible d'ajouter cet accès dans le système");
			addError("AUTHORIZATION.1.4.1", "Le login de l'accès est absent");
			addError("AUTHORIZATION.1.4.2", "Aucun accès trouvé pour cet identifiant");
			addError("AUTHORIZATION.1.4.3", "La catégorie n'existe pas");
			addError("AUTHORIZATION.1.5.1", "Le login de l'accès est absent");
			addError("AUTHORIZATION.1.5.2", "Aucun accès trouvé pour cet identifiant");
			addError("AUTHORIZATION.1.5.3", "La catégorie n'existe pas");
			addError("AUTHORIZATION.2.1.1", "Le login de l'accès est absent");
			addError("AUTHORIZATION.2.1.2", "Le droit à ajouter pour l'identifant transmis est absent");
			addError("AUTHORIZATION.2.1.3", "La catégorie n'existe pas");
			addError("AUTHORIZATION.2.1.4", "Le role n'appartient pas à la catégorie précisée");
			addError("AUTHORIZATION.2.1.5", "Aucun accès trouvé pour cet identifiant");
			addError("AUTHORIZATION.2.1.6", "Un accès avec ce droit existe déjà pour le login désigné");
			addError("AUTHORIZATION.2.1.7", "Impossible de trouver l'accès après la mise à jour");
			addError("AUTHORIZATION.2.2.1", "Le login de l'accès est absent");
			addError("AUTHORIZATION.2.2.2", "Le droit à retirer pour l'identifant transmis est absent");
			addError("AUTHORIZATION.2.2.3", "La catégorie n'existe pas");
			addError("AUTHORIZATION.2.2.4", "Le role n'appartient pas à la catégorie précisée");
			addError("AUTHORIZATION.2.2.5", "Aucun accès trouvé pour cet identifiant");
			addError("AUTHORIZATION.2.2.6", "Aucun role avec le nom transmis n'existe pour cet identifiant");
			addError("AUTHORIZATION.2.2.7", "Impossible de trouver l'accès après la mise à jour");
			addError("AUTHORIZATION.2.3.1", "Le login de l'accès est absent");
			addError("AUTHORIZATION.2.3.2", "La liste des roles à retirer est absente");
			addError("AUTHORIZATION.2.3.3", "La liste des roles à retirer n'est pas correcte");
			addError("AUTHORIZATION.2.3.4", "La liste des roles à retirer est vide");
			addError("AUTHORIZATION.2.3.5", "Aucun accès trouvé pour cet identifiant");
			addError("AUTHORIZATION.2.4.1", "Le login de l'accès est absent");
			addError("AUTHORIZATION.2.4.2", "Le droit à appliquer pour l'identifant transmis est absent");
			addError("AUTHORIZATION.2.4.3", "La catégorie n'existe pas");
			addError("AUTHORIZATION.2.4.4", "Le role n'appartient pas à la catégorie précisée");
			addError("AUTHORIZATION.2.4.5", "Le droit à appliquer n'a pas de catégorie rattachée");
			addError("AUTHORIZATION.2.4.6", "Le droit à appliquer n'appartient pas à la catégorie spécifiée");
			addError("AUTHORIZATION.2.4.7", "Aucun accès trouvé pour cet identifiant");
			addError("AUTHORIZATION.2.4.8", "Aucun accès n'existe pour ce login");
			addError("AUTHORIZATION.2.4.9", "Aucun accès n'existe pour ce login");

			addError("FLOODPROTECTION.1.1.1", "La clé auquelle est associée la connexion est absente");
			addError("FLOODPROTECTION.1.1.2", "La clé auquelle est associée la connexion n'est pas valide");
			addError("FLOODPROTECTION.1.1.3", "Une erreur est survenue lors de la suppression des historiques périmés");
			addError("FLOODPROTECTION.1.1.4", "Une erreur est survenue lors de la recherche des historiques pour la clé associée à la connexion");
			addError("FLOODPROTECTION.1.1.5", "Accès restreint : la protection anti-bruteforce pour cette connexion est toujours active");
			addError("FLOODPROTECTION.1.1.6", "Accès restreint : activation de la protection anti-bruteforce pour cette connexion");
			addError("FLOODPROTECTION.1.1.7", "Une erreur est survenue lors de l'incrémentation du compteur pour la clé associée à cette connexion");
			addError("FLOODPROTECTION.1.1.8", "Impossible de trouver l'historique de la clé associée à cette connexion après l'incrémentation du compteur");
			addError("FLOODPROTECTION.1.1.9", "Une erreur est survenue lors de la pause du serveur");
			addError("FLOODPROTECTION.1.1.10","Impossible d'ajouter un historique pour la clé associée à cette connexion");
			addError("FLOODPROTECTION.1.1.11","Impossible de trouver l'historique pour la clé associée à cette connexion après son insertion dans les historiques");
			addError("FLOODPROTECTION.1.2.1", "La clé auquelle est associée la connexion est absente");
			addError("FLOODPROTECTION.1.2.2", "La clé auquelle est associée la connexion n'est pas valide");
			addError("FLOODPROTECTION.1.2.3", "Une erreur est survenue lors de la réinitialisation de l'historique");
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
	 * Auxiliary function to add an error in the list
	 * @param err Error object to add
	 */
	protected synchronized static void addError(Error err){
		if(err!=null && err.getCode()!=null && !err.getCode().equals("") && 
		   !errorListRef.containsKey(err.getCode())){
			errorListRef.put(err.getCode(), err);
		}
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
		if(code!=null && errorListRef.containsKey(code)){
			return errorListRef.get(code);
		}
		return null;
	}

	/**
	 * Get all the errors
	 * @return An 'Error' collection
	 */
	public static Collection<Error> getErrors(){
		if(!flag){
			putErrors();
		}
		return errorListRef.values();
	}
}