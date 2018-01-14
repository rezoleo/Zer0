package fr.webservicecore.junit.test;

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


import org.junit.Test;

import fr.webservicecore.error.ErrorReferential;
import fr.webservicecore.error.APIException;
import fr.webservicecore.junit.model.TestCase_Model;
import fr.webservicecore.toolbox.AttributesTool;;

/**
 * This class contains the test to check the functions of the WebServiceCore JAR toolbox
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_Toolbox extends TestCase_Model
{
	/**
	 * Testing of the function isEmpty is correct
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testIsEmpty() throws Exception{
		assertEquals(true,  AttributesTool.isEmpty(null));
		assertEquals(true,  AttributesTool.isEmpty(""));
		assertEquals(false, AttributesTool.isEmpty("a"));
		assertEquals(false, AttributesTool.isEmpty("A"));
	}

	/**
	 * Testing of the function isEmptyThrowsError is correct
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testIsEmptyThrowsError() throws Exception{
		auxiIsEmpty(null);
		auxiIsEmpty("");
		AttributesTool.isEmptyThrowsError("a");
		AttributesTool.isEmptyThrowsError("A");
	}
	protected void auxiIsEmpty(String str) throws Exception{
		try{
			AttributesTool.isEmptyThrowsError(str);
		}
		catch(APIException e){
			checkBasicMessage(ErrorReferential.getErrorByCode("WSCORE-JAR-1"), e.getMsg());
			return;
		}
		assertNotNull(null);
	}

	/**
	 * Testing of the function checkRegex is correct
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testCheckRegex() throws Exception{
		assertEquals(false, AttributesTool.checkRegex(null));
		assertEquals(false, AttributesTool.checkRegex(""));
		assertEquals(true,  AttributesTool.checkRegex("a"));
		assertEquals(false, AttributesTool.checkRegex("&"));
		assertEquals(true,  AttributesTool.checkRegex("att"));
		assertEquals(false, AttributesTool.checkRegex("&tt"));
		assertEquals(true,  AttributesTool.checkRegex("batt"));
		assertEquals(false, AttributesTool.checkRegex("b&tt"));
		assertEquals(true,  AttributesTool.checkRegex("test"));
		assertEquals(false, AttributesTool.checkRegex("test&"));
	}

	/**
	 * Testing of the function checkRegexThrowsError is correct
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testCheckRegexThrowsError() throws Exception{
		auxiCheckRegex(null);
		auxiCheckRegex("");
		AttributesTool.checkRegexThrowsError("a");
		auxiCheckRegex("&");
		AttributesTool.checkRegexThrowsError("att");
		auxiCheckRegex("&tt");
		AttributesTool.checkRegexThrowsError("batt");
		auxiCheckRegex("b&tt");
		AttributesTool.checkRegexThrowsError("test");
		auxiCheckRegex("test&");
	}
	protected void auxiCheckRegex(String str) throws Exception{
		try{
			AttributesTool.checkRegexThrowsError(str);
		}
		catch(APIException e){
			checkBasicMessage(ErrorReferential.getErrorByCode("WSCORE-JAR-2"), e.getMsg());
			return;
		}
		assertNotNull(null);
	}
}
