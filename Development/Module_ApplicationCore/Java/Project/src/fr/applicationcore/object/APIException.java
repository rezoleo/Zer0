package fr.applicationcore.object;

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
 * Class 	: APIException
 * Author(s): Zidmann
 * Function : This class describes an error message coming from a NodeJS server using the module entitled 'ApplicationCore'
 * Version  : 1.0.0
 */
@SuppressWarnings("serial")
public class APIException extends fr.core.object.APIException
{
	public ErrorMessage getMsg(){
		return (ErrorMessage)this.msg;
	}
	public void setMsg(ErrorMessage msg){
		this.msg = msg;
	}
}