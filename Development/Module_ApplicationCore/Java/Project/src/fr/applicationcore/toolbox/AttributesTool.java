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


import fr.applicationcore.error.APIException;
import fr.applicationcore.toolbox.ErrorTool;

/**
 * Useful functions to analyze parameters sent to the server and used in the URL by checking if an attribute contains forbidden letters
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class AttributesTool extends fr.core.toolbox.AttributesTool
{
	/**
	 * Check if a string is empty and returns an error if it is not the case
	 * @param str String to check
	 * @throws APIException containing the error message whose code is 'APPCORE-JAR-1'
	 */
	public static void isEmptyThrowsError(String str) throws APIException{
		if(isEmpty(str)){
        	APIException objectDBExpct = new APIException();
        	objectDBExpct.setErrorMsg(ErrorTool.getMessageByCode("APPCORE-JAR-1"));

			throw objectDBExpct;
		}
	}

	/**
	 * Check if a string respects the regex which describes only authorized letters and returns an error if it is not the case
	 * @param str String to check
	 * @throws APIException containing the error message whose code is 'APPCORE-JAR-2'
	 */
	public static void checkRegexThrowsError(String str) throws APIException{
		if(!checkRegex(str)){
        	APIException objectDBExpct = new APIException();
        	objectDBExpct.setErrorMsg(ErrorTool.getMessageByCode("APPCORE-JAR-2"));

			throw objectDBExpct;
		}
	}
}
