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


import java.util.Date;
import junit.framework.TestCase;

import fr.core.error.Error;
import fr.core.error.ErrorMessage;

/**
 * This class contains the super class used by the different JUnit test cases
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_Core_Model extends TestCase
{	
	/**
	 * Constructor TestCase_Core_Model
	 */
	protected TestCase_Core_Model(){
		
	}

	/**
	 * Check if the error message has the expected properties
	 * @param version Version expected from the server
	 * @param err Information about code, message, stack and status
	 * @param msg Error message to check
	 */
	protected void checkMessage(String version, Error err, ErrorMessage msg){
		assertEquals(version,			msg.getVersion());
		assertEquals(err.getCode(),		msg.getCode());
		assertEquals(err.getMessage(),	msg.getMessage());
		assertEquals(null,				msg.getStack());
		assertEquals(404,				msg.getStatus());
	}

	/**
	 * Functions to compare two dates
	 * @return 'true' if 'a' is inferior to 'b' or false if not
	 */
	protected boolean inferiorDate(Date a, Date b){
		if(a==null){
			return true;
		}
		else if(a!=null  && b==null){
			return false;
		}
		return a.before(b);
	}

	/**
	 * Functions to compare two dates
	 * @return 'true' if 'a' is equal to 'b' or false if not
	 */
	protected boolean equalsDate(Date a, Date b){
		if(a==null && b==null){
			return true;
		}
		else if(a!=null && b!=null){
			return a.equals(b);
		}
		return false;
	}
}