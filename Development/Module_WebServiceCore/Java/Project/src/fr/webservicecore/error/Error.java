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


/** 
 * Description of an error returned by a NodeJS server using the WebserviceCore module for a service
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version  1.1.0
 */
public class Error extends fr.core.error.Error
{
	/**
	 * Constructor Error
	 * @param code HTTP code returned by the error
	 * @param message Text message of the error
	 */
	public Error(String code, String message){
		super(code, message);
	}

	/**
	 * Constructor Error
	 * @param code HTTP code returned by the error
	 * @param message Text message of the error
	 * @param stack Details of the error
	 * @param status Status of the error
	 */
	public Error(String code, String message, String stack, int status){
		super(code, message, stack, status);
	}
}