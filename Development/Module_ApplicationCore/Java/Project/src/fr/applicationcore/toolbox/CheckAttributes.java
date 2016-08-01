package fr.applicationcore.toolbox;

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


import fr.applicationcore.error.ErrorReferential;
import fr.applicationcore.object.APIException;
import fr.applicationcore.object.ErrorMessage;

/* 
 * Class 	: CheckAttributes
 * Author(s): Zidmann
 * Function : This class contains the functions to the check if an attribute contains forbidden letters
 * Version  : 1.0.0
 */
public class CheckAttributes extends fr.core.toolbox.CheckAttributes
{
	public static void isEmptyThrowsError(String str) throws APIException{
		if(isEmpty(str)){
        	APIException objectDBExpct = new APIException();
        	objectDBExpct.setErrorMsg(getMessageByCode("APPCORE-JAR-1"));

			throw objectDBExpct;
		}
	}

	public static void checkRegexThrowsError(String str) throws APIException{
		if(!checkRegex(str)){
        	APIException objectDBExpct = new APIException();
        	objectDBExpct.setErrorMsg(getMessageByCode("APPCORE-JAR-2"));

			throw objectDBExpct;
		}
	}

	protected static ErrorMessage getMessageByCode(String code){
		return new ErrorMessage(ErrorReferential.getErrorByCode(code));
	}
}
