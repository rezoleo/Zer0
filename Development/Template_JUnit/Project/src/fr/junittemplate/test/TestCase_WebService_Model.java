package fr.junittemplate.test;

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


import fr.webservicecore.error.Error;
import fr.webservicecore.object.ErrorMessage;

/* 
 * Class 	: TestCase_WebService_Model
 * Author(s): Zidmann
 * Function : This class contains the super class used by the different JUnit test cases 
 * Version  : 1.0.0 
 */
public class TestCase_WebService_Model extends TestCase_Core_Model
{
	//Function to check if the error message has the expected properties
	protected void checkMessage(String service, String version, Error err, ErrorMessage msg){
		assertEquals(service, msg.getService());
		super.checkMessage(version, err, msg);
	}
}
