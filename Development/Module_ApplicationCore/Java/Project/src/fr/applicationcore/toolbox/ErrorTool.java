package fr.applicationcore.toolbox;

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


import fr.applicationcore.error.ErrorMessage;
import fr.applicationcore.error.ErrorReferential;

/**
 * Useful functions to get errors
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class ErrorTool extends fr.core.toolbox.ErrorTool
{
	/**
	 * Find an error associated to a code in the list defined through ErrorReferential singleton
	 * @param code Error code
	 * @return An ErrorMessage object which contains Error object information
	 */
	public static ErrorMessage getMessageByCode(String code){
		return new ErrorMessage(ErrorReferential.getErrorByCode(code));
	}
}
