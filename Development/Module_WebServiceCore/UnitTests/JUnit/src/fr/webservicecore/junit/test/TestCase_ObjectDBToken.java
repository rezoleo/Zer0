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

import fr.webservicecore.junit.network.ObjectDBTokenClient;
import fr.webservicecore.junit.token.TokenReferential;

/**
 * This class contains the webService client JUnit tests to check if there was no regression in ObjectDB referential server.
 * <p>
 * This Test Case supposes that you started the NodeJS server designed to test 'WS_ObjectDBClient' class and remove all ObjectDBs in the MongoDB database.
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_ObjectDBToken extends TestCase_ObjectDB
{ 
	/**
	 * Testing the ObjectDB with a token
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testObjectDBClientAPI() throws Exception
	{
		ws_client = new ObjectDBTokenClient();
		ws_client.setToken(TokenReferential.getToken("token_objDB"));
		super.testObjectDBClientAPI();
	}
}
