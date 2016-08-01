package fr.webservicecore.junit.test;

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


import org.junit.Test;

import fr.webservicecore.error.ErrorReferential;
import fr.webservicecore.object.APIException;
import fr.webservicecore.junit.model.TestCase_Model;
import fr.webservicecore.toolbox.CheckAttributes;

/* 
 * Class 	: TestCase_Toolbox
 * Author(s): Zidmann
 * Function : This class contains the test to check the functions of the WebServiceCore JAR toolbox 
 * Version  : 1.0.0 
 */

public class TestCase_Toolbox extends TestCase_Model
{
	@Test
	public void testIsEmpty() throws Exception{
		assertEquals(true,  CheckAttributes.isEmpty(null));
		assertEquals(true,  CheckAttributes.isEmpty(""));
		assertEquals(false, CheckAttributes.isEmpty("a"));
		assertEquals(false, CheckAttributes.isEmpty("A"));
	}

	@Test
	public void testIsEmptyThrowsError() throws Exception{
		auxiIsEmpty(null);
		auxiIsEmpty("");
		CheckAttributes.isEmptyThrowsError("a");
		CheckAttributes.isEmptyThrowsError("A");
	}
	protected void auxiIsEmpty(String str) throws Exception{
		try{
			CheckAttributes.isEmptyThrowsError(str);
		}
		catch(APIException e){
			checkBasicMessage(ErrorReferential.getErrorByCode("WSCORE-JAR-1"), e.getMsg());
			return;
		}
		assertNotNull(null);
	}

	@Test
	public void testCheckRegex() throws Exception{
		assertEquals(false, CheckAttributes.checkRegex(null));
		assertEquals(false, CheckAttributes.checkRegex(""));
		assertEquals(true,  CheckAttributes.checkRegex("a"));
		assertEquals(false, CheckAttributes.checkRegex("&"));
		assertEquals(true,  CheckAttributes.checkRegex("att"));
		assertEquals(false, CheckAttributes.checkRegex("&tt"));
		assertEquals(true,  CheckAttributes.checkRegex("batt"));
		assertEquals(false, CheckAttributes.checkRegex("b&tt"));
		assertEquals(true,  CheckAttributes.checkRegex("test"));
		assertEquals(false, CheckAttributes.checkRegex("test&"));
	}
	@Test
	public void testCheckRegexThrowsError() throws Exception{
		auxiCheckRegex(null);
		auxiCheckRegex("");
		CheckAttributes.checkRegexThrowsError("a");
		auxiCheckRegex("&");
		CheckAttributes.checkRegexThrowsError("att");
		auxiCheckRegex("&tt");
		CheckAttributes.checkRegexThrowsError("batt");
		auxiCheckRegex("b&tt");
		CheckAttributes.checkRegexThrowsError("test");
		auxiCheckRegex("test&");
	}
	protected void auxiCheckRegex(String str) throws Exception{
		try{
			CheckAttributes.checkRegexThrowsError(str);
		}
		catch(APIException e){
			checkBasicMessage(ErrorReferential.getErrorByCode("WSCORE-JAR-2"), e.getMsg());
			return;
		}
		assertNotNull(null);
	}
}
