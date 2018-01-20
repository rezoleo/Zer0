package fr.service_authentification.junit.test;

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

import fr.service_authentification.junit.model.TestCase_Model;
import fr.service_authentification.object.Authentification;

/**
 * This class contains the webService client JUnit tests to check if there was no regression in Authentification service
 * <p>
 * This Test Case supposes that you started the NodeJS server and removed all the Authentification elements in the MongoDB database
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_Authentification extends TestCase_Model
{
	/**
	 * Testing all the access APIs for usual actions 
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testAuthentificationClientAPI() throws Exception
	{	
		initSettings();

		//Creation of two accesses - Test POST function
		//Get all the accesses - Test GET function
		Authentification access1=ws_client.createAccess("loginone", "passwordOne", "mailone@mail.com", "ON", "creator");
		assertNotNull(access1);
		assertNotNull(access1.get_id());
		assertNotNull(access1.getCreated());
		Authentification accessRef = new Authentification(access1.get_id(), "loginone", "mailone@mail.com", "ON", 
														  "creator", access1.getCreated(), "JunitTests", 
													      null, null, null);
		assertEquals(accessRef, access1);

		Authentification access2=ws_client.createAccess("logintwo", "passwordTwo", "mailtwo@mail.com", "ON", "creator");
		assertNotNull(access2);
		assertNotNull(access2.get_id());
		assertNotNull(access2.getCreated());
		Authentification accessRef2= new Authentification(access2.get_id(), "logintwo", "mailtwo@mail.com", "ON", 
														  "creator", access2.getCreated(), "JunitTests", 
													      null, null, null);
		assertEquals(accessRef2, access2);

		Authentification access3=ws_client.updatePassword("logintwo", "passwordThree", "mailtwo@mail.com", "ON", false, "updator");
		assertNotNull(access3);
		assertNotNull(access3.getUpdated());
		accessRef2.setUpdator("updator");
		accessRef2.setUpdated(access3.getUpdated());
		accessRef2.setUpdatorService("JunitTests");
		assertEquals(accessRef2, access3);

		Authentification access4=ws_client.checkAccess("loginone");
		assertEquals(accessRef, access4);

		Authentification access5=ws_client.checkAccess("logintwo");	
		assertEquals(accessRef2, access5);

		Authentification access6=ws_client.checkPassword("loginone", "passwordOne");
		assertEquals(accessRef, access6);

		Authentification access7=ws_client.checkPassword("logintwo", "passwordThree");	
		assertEquals(accessRef2, access7);

		Authentification access8=ws_client.updatePassword("logintwo", "passwordThreeBis", "mailtwo@mail.com", "ON", true, "updator");
		Authentification accessRef3 = new Authentification(access2.get_id(), "logintwo", "mailtwo@mail.com", "ON", 
														   "creator", access2.getCreated(), "JunitTests", 
														   "updator", access8.getUpdated(), "JunitTests");
		Authentification access9=ws_client.checkPassword("logintwo", "passwordThree");	
		assertEquals(accessRef3, access9);

		Authentification access10=ws_client.deleteAccess("loginone");
		assertEquals(accessRef, access10);

		Authentification access11=ws_client.deleteAccess("logintwo");
		assertEquals(accessRef3, access11);
	}
}