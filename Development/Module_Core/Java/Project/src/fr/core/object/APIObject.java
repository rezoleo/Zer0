package fr.core.object;

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
 * Interface : APIObject
 * Author(s) : Zidmann
 * Function  : This interface is used to process an element returned by a NodeJS server using the Core module for a service or an application
 * Version   : 1.0.0
 * Note      : The function 'isEmpty' is used by the 'WebService' class to know if the element returned by the client is a message that the server returned or an APIObject instance.
 */
public interface APIObject
{
	public boolean isEmpty();
}