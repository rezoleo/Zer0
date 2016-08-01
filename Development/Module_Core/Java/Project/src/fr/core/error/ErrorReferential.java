package fr.core.error;

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


import java.util.Collection;
import java.util.HashMap;

/* 
 * Class 	: ErrorReferential
 * Author(s): Zidmann
 * Function : This class describes all the errors returned by a server using the Core module for a service or an application
 * Version  : 1.0.0
 */
public class ErrorReferential
{
	private static volatile ErrorReferential instance = null;

	protected static HashMap<String, Error>	errorListRef = new HashMap<String, Error>();
	protected static boolean				flag		 = false;

	protected ErrorReferential(){
		if(!flag){
			putErrors();
		}
	}

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

	//Definition of all the errors for the ApplicationCore or the WebServiceCore module
	protected static void putErrors(){
		if(!flag){
			flag = true;

			addError("CORE-JAR-1", "Un des paramètres est vide");
			addError("CORE-JAR-2", "Un des paramètres comporte un caractère interdit");
		}
	}

	//Functions to add an error int the list
	public static void addError(String code, String msg){
		addError(new Error(code, msg));
	}
	protected static void addError(Error err){
		if(err!=null && err.code!=null && !err.code.equals("") && 
		   !errorListRef.containsKey(err.code)){
			errorListRef.put(err.code, err);
		}
	}

	//Functions to find an error with its reference
	public static Error getErrorByCode(String code){
		if(!flag){
			putErrors();
		}
		if(code!=null && errorListRef.containsKey(code)){
			return errorListRef.get(code);
		}
		return null;
	}

	//Function to get all the errors
	public static Collection<Error> getErrors(){
		if(!flag){
			putErrors();
		}
		return errorListRef.values();
	}
}