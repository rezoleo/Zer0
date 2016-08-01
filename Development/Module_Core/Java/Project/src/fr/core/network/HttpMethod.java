package fr.core.network;

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
 * Enumeration 	: HttpMethod
 * Author(s)	: Zidmann
 * Function 	: This enumeration represents the different types of HTTP request (GET, POST, PUT, DELETE)
 * Version  	: 1.0.0
 */
public enum HttpMethod
{
	GET,
	POST,
	POSTfile,	//Specific to send a file
	PUT,
	DELETE
}