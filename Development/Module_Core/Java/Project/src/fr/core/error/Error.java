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


/* 
 * Class 	: Error
 * Author(s): Zidmann
 * Function : This class describes an error returned by a NodeJS server using the Core module for a service or an application
 * Version  : 1.0.0
 */
public class Error implements ErrorInterface
{
	public String	code 	= "0";		// Identification number of the error
	public String	message = null;		// Text message of the error
	public String	stack	= null;		// 
	public int 		status	= 404;		// Status of the error

	public Error(String code, String message){
		this.code	 = code;
		this.message = message;
	}
	public Error(String code, String message, String stack, int status){
		this.code 	 = code;
		this.message = message;
		this.stack   = stack;
		this.status  = status;
	}

	@Override
	public String toString() {
		return "Error [code=" + this.code + ", message=" + this.message
				+ ", stack=" + this.stack + ", status=" + this.status + "]";
	}
}