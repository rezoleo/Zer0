package fr.applicationcore.junit.test;

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

import fr.core.network.HttpCommunication;
import fr.applicationcore.Common;
import fr.applicationcore.junit.model.TestCase_Model;

/**
 * This class contains the webService client JUnit tests to check if the user agent can be defined by the JAR
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_UserAgent extends TestCase_Model
{
	protected String 				response  	  = null;

	/**
	 * Testing the default user agent of the client
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testDefaultUserAgent() throws Exception{
		initSettings();

		//Update of the password
    	String http_address = Common.URL+"/api/useragent";

    	HttpCommunication.getInstance().setUserAgent(null);
		String response = HttpCommunication.getInstance().sendGet(http_address);

		assertEquals("{\"user_agent\":\"JavaApplication/1.0.0\"}", response);
	}

	/**
	 * Testing if user agent of the client can be changed
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testUserAgent() throws Exception{
		initSettings();

		//Update of the password
    	String http_address = Common.URL+"/api/useragent";

    	HttpCommunication.getInstance().setUserAgent("TOTO");
		String response = HttpCommunication.getInstance().sendGet(http_address);

		assertEquals("{\"user_agent\":\"TOTO\"}", response);
	}
}