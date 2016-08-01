package fr.applicationcore.error;

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
 * Function : This class describes an error returned by a NodeJS server using the ApplicationCore module
 * Version  : 1.0.0
 */
public class Error extends fr.core.error.Error
{
	public Error(String code, String message){
		super(code, message);
	}

	public Error(String code, String message, String errors, int status){
		super(code, message, errors, status);
	}
}