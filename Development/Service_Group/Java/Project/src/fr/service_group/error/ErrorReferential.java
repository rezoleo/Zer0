package fr.service_group.error;

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
 * List of all the errors returned by the Group service
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
	public ErrorReferential() {
		instance.addError("GROUP.1.1.1",  "L'identifiant ID du groupe est absent");
		instance.addError("GROUP.1.1.2",  "Aucun groupe identifié avec cet identifiant");
		instance.addError("GROUP.1.2.1",  "Le nom du groupe est absent");
		instance.addError("GROUP.1.2.2",  "Aucun groupe identifié avec ce nom");
		instance.addError("GROUP.1.3.1",  "Le nom du groupe est absent");
		instance.addError("GROUP.1.4.1",  "Le nom du groupe n'a pas été précisé");
		instance.addError("GROUP.1.4.2",  "Le type du groupe n'a pas été précisé");
		instance.addError("GROUP.1.4.3",  "Impossible d'ajouter le groupe dans le système comme le nom est déja utilisé par un autre groupe");
		instance.addError("GROUP.1.4.4",  "Impossible d'ajouter le groupe dans le système");
		instance.addError("GROUP.1.5.1",  "L'identifiant ID du groupe n'a pas été précisé");
		instance.addError("GROUP.1.5.2",  "Le nom du groupe n'a pas été précisé");
		instance.addError("GROUP.1.5.3",  "Le type du groupe n'a pas été précisé");
		instance.addError("GROUP.1.5.4",  "Aucun groupe identifié avec cet identifiant");
		instance.addError("GROUP.1.5.5",  "Impossible d'ajouter le groupe dans le système comme le nom est déja utilisé par un autre groupe");
		instance.addError("GROUP.1.5.6",  "Impossible d'ajouter le groupe dans le système comme l'adresse e-mail est déja utilisée par un autre groupe");
		instance.addError("GROUP.1.5.7",  "Impossible de trouver le groupe après la mise à jour");
		instance.addError("GROUP.1.6.1",  "L'identifiant ID du groupe est absent");
		instance.addError("GROUP.1.6.2",  "Aucun groupe identifié avec cet identifiant");
		instance.addError("GROUP.1.7.1",  "L'identifiant ID du groupe est absent");
		instance.addError("GROUP.1.7.2",  "Le login à ajouter dans le groupe est absent");
		instance.addError("GROUP.1.7.3",  "Aucun groupe identifié avec cet identifiant");
		instance.addError("GROUP.1.7.4",  "Un login existe déjà dans le groupe désigné");
		instance.addError("GROUP.1.7.5",  "Impossible de trouver le groupe après la mise à jour");
		instance.addError("GROUP.1.8.1",  "L'identifiant ID du groupe est absent");
		instance.addError("GROUP.1.8.2",  "Le login à ajouter dans les responsables du groupe est absent");
		instance.addError("GROUP.1.8.3",  "La responsabilité à ajouter dans le groupe pour ce login est absente");
		instance.addError("GROUP.1.8.4",  "Aucun groupe identifié avec cet identifiant");
		instance.addError("GROUP.1.8.5",  "Le login a déjà cette responsabilité d'affectée dans le groupe désigné");
		instance.addError("GROUP.1.8.6",  "Impossible de trouver le groupe après la mise à jour");
		instance.addError("GROUP.1.9.1",  "L'identifiant ID du groupe est absent");
		instance.addError("GROUP.1.9.2",  "Le login du membre à retirer est absent");
		instance.addError("GROUP.1.9.3",  "Aucun groupe identifié avec cet identifiant");
		instance.addError("GROUP.1.9.4",  "Aucun membre avec ce login n'existe pour le groupe désigné");
		instance.addError("GROUP.1.9.5",  "Impossible de trouver le groupe après la mise à jour");
		instance.addError("GROUP.1.10.1", "L'identifiant ID du groupe est absent");
		instance.addError("GROUP.1.10.2", "Le login du responsable à retirer est absent");
		instance.addError("GROUP.1.10.3", "La responsabilité du responsable à retirer est absente");
		instance.addError("GROUP.1.10.4", "Aucun groupe identifié avec cet identifiant");
		instance.addError("GROUP.1.10.5", "Aucun responsable avec cette responsabilité n'existe pour le groupe désigné");
		instance.addError("GROUP.1.10.6", "Impossible de trouver le groupe après la mise à jour");
	}
}