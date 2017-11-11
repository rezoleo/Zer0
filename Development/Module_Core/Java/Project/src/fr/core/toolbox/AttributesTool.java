package fr.core.toolbox;

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


import fr.core.error.APIException;
import fr.core.toolbox.ErrorTool;

/**
 * Useful functions to analyze parameters sent to the server and used in the URL by checking if an attribute contains forbidden letters
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class AttributesTool
{
	/**
	 * Regex which defines the authorized letters in a string
	 */
	private static String regex = "[a-zA-Z0-9-_@.]{1,}";

	/**
	 * Check if a string is empty
	 * @param str String to check
	 * @return 'true' if the string is empty or 'false' if not
	 */
	public static boolean isEmpty(String str){
		if(str==null || str.equals("")){
			return true;
		}
		return false;
	}
	/**
	 * Check if a string is empty and returns an error if it is not the case
	 * @param str String to check
	 * @throws APIException containing the error message whose code is 'CORE-JAR-1'
	 */
	public static void isEmptyThrowsError(String str) throws APIException{
		if(isEmpty(str)){
        	APIException objectDBExpct = new APIException();
        	objectDBExpct.setErrorMsg(ErrorTool.getMessageByCode("CORE-JAR-1"));

			throw objectDBExpct;
		}
	}

	/**
	 * Check if a string respects the regex which describes only authorized letters
	 * @param str String to check
	 * @return 'true' if the string respects the regex or 'false' if not
	 */
	public static boolean checkRegex(String str){
		if(str==null || regex==null){
			return false;
		}
		return str.matches(regex);
	}
	/**
	 * Check if a string respects the regex which describes only authorized letters and returns an error if it is not the case
	 * @param str String to check
	 * @throws APIException containing the error message whose code is 'CORE-JAR-2'
	 */
	public static void checkRegexThrowsError(String str) throws APIException{
		if(!checkRegex(str)){
        	APIException objectDBExpct = new APIException();
        	objectDBExpct.setErrorMsg(ErrorTool.getMessageByCode("CORE-JAR-2"));

			throw objectDBExpct;
		}
	}
}
