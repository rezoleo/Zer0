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


/**
 * Description of an error message coming from a NodeJS server using the Core module for an application or a service
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class APIException extends Exception
{
	private static final long serialVersionUID = 1L;

	/**
	 * Details of the error returned by NodeJS server
	 */
	private ErrorMessage msg = null;

	public APIException(){
		super();
	}

	public ErrorMessage getErrorMsg(){
		return this.msg;
	}
	public void setErrorMsg(ErrorMessage msg){
		this.msg = msg;
	}
}