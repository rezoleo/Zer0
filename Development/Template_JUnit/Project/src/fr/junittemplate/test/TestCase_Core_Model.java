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


import java.util.Date;
import junit.framework.TestCase;

import fr.core.error.Error;
import fr.core.object.ErrorMessage;

/* 
 * Class 	: TestCase_Core_Model
 * Author(s): Zidmann
 * Function : This class contains the super class used by the different JUnit test cases 
 * Version  : 1.0.0 
 */
public class TestCase_Core_Model extends TestCase
{	
	protected TestCase_Core_Model(){
		
	}

	//Function to check if the error message has the expected properties
	protected void checkMessage(String version, Error err, ErrorMessage msg){
		assertEquals(version,		msg.getVersion());
		assertEquals(err.code,		msg.getCode());
		assertEquals(err.message,	msg.getMessage());
		assertEquals(null,			msg.getStack());
		assertEquals(404,			msg.getStatus());
	}

	//Functions to compare two dates
	protected boolean inferiorDate(Date a, Date b){
		if(a==null){
			return true;
		}
		else if(a!=null  && b==null){
			return false;
		}
		return a.before(b);
	}

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