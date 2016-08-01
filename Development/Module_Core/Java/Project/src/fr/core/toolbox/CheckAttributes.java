package fr.core.toolbox;

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


import fr.core.error.ErrorReferential;
import fr.core.object.APIException;
import fr.core.object.ErrorMessage;

/* 
 * Class 	: CheckAttributes
 * Author(s): Zidmann
 * Function : This class contains the functions to the check if an attribute contains forbidden letters
 * Version  : 1.0.0
 */
public class CheckAttributes
{
	protected static String regex = "[a-zA-Z0-9-_@.]{1,}";
	public static boolean isEmpty(String str){
		if(str==null || str.equals("")){
			return true;
		}
		return false;
	}
	public static void isEmptyThrowsError(String str) throws APIException{
		if(isEmpty(str)){
        	APIException objectDBExpct = new APIException();
        	objectDBExpct.setErrorMsg(getMessageByCode("CORE-JAR-1"));

			throw objectDBExpct;
		}
	}

	public static String deleteFirstBackslash(String str){
		if(str==null){
			return null;
		}

		int length = str.length();
		int pos    = str.indexOf("/");

		if(length==1 && pos==0){
			return "";
		}
		else if(length>1){
			if(pos==0){
				return str.substring(1, length-1);
			}
			else if(pos==length-1){
				return str.substring(0, length-2);
			}
			else{
				return str.substring(0, pos-1)+str.substring(pos+1, length-1);
			}
		}

		return str;
	}

	public static boolean checkRegex(String str){
		if(str==null || regex==null){
			return false;
		}
		return str.matches(regex);
	}
	public static void checkRegexThrowsError(String str) throws APIException{
		if(!checkRegex(str)){
        	APIException objectDBExpct = new APIException();
        	objectDBExpct.setErrorMsg(getMessageByCode("CORE-JAR-2"));

			throw objectDBExpct;
		}
	}

	protected static ErrorMessage getMessageByCode(String code){
		return new ErrorMessage(ErrorReferential.getErrorByCode(code));
	}
}
