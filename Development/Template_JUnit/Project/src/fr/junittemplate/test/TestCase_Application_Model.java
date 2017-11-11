package fr.junittemplate.test;

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


import fr.applicationcore.error.Error;
import fr.applicationcore.error.ErrorMessage;

/**
 * This class contains the super class used by the different JUnit test cases with applications
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_Application_Model extends TestCase_Core_Model
{
	/**
	 * Check if the error message has the expected properties
	 * @param application Application name expected from the server
	 * @param version Version expected from the server
	 * @param err Information about code, message, stack and status
	 * @param msg Error message to check
	 */
	protected void checkMessage(String application, String version, Error err, ErrorMessage msg){
		assertEquals(application, msg.getApplication());
		super.checkMessage(version, err, msg);
	}
}
