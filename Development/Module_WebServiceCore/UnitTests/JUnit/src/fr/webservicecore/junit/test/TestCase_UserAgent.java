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

import fr.core.network.HttpCommunication;
import fr.webservicecore.Common;
import fr.webservicecore.junit.model.TestCase_Model;

/* 
 * Class 	: TestCase_UserAgent
 * Author(s): Zidmann
 * Function : This class contains the webService client JUnit tests to check if the user agent can be defined by the JAR 
 * Version  : 1.0.0 
 */
public class TestCase_UserAgent extends TestCase_Model
{
	protected String 				response  	  = null;

	@Test
	public void testDefaultUserAgent() throws Exception{
		initSettings();

		//Update of the password
    	String http_address = Common.URL+"/api/useragent";

    	HttpCommunication.getInstance().setUserAgent(null);
		String response = HttpCommunication.getInstance().sendGet(http_address);

		assertEquals("{\"user_agent\":\"JavaApplication/1.0.0\"}", response);
	}

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