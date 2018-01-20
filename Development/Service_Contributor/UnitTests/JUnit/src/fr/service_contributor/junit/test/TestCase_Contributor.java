package fr.service_contributor.junit.test;

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

import fr.service_contributor.object.Contributor;
import fr.service_contributor.junit.model.TestCase_Model;

/**
 * This class contains the webService client JUnit tests to check if there was no regression in Contributor service
 * <p>
 * This Test Case supposes that you started the NodeJS server and removed all the Contributor elements in the MongoDB database
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_Contributor extends TestCase_Model
{
	/**
	 * Testing all the student contributor APIs for usual actions 
	 * @throws Exception Exception returned by the system
	 */ 
	@Test
	public void testContributorClientAPI() throws Exception{	
		initSettings();

		//Creation of two contributors - Test POST function
		//Get all the contributors - Test GET function
		checkContributorQuantity(0);
		assertEquals(false, ws_client.checkOneContributorByLogin("loginone"));
		assertEquals(false, ws_client.checkOneContributorByLogin("logintwo"));
		
		Contributor contributor1=ws_client.createContributor("loginone", "creatoro");
		assertNotNull(contributor1);
		assertNotNull(contributor1.get_id());
		assertNotNull(contributor1.getCreated());
		Contributor contributorRef = new Contributor(contributor1.get_id(), "loginone", 
													"creatoro", contributor1.getCreated(), "JunitTests");
		assertEquals(contributorRef, contributor1);

		checkContributorQuantity(1);
		assertEquals(true,  ws_client.checkOneContributorByLogin("loginone"));
		assertEquals(false, ws_client.checkOneContributorByLogin("logintwo"));

		Contributor contributor2=ws_client.createContributor("logintwo", "creatort");
		assertNotNull(contributor2);
		assertNotNull(contributor2.get_id());
		assertNotNull(contributor2.getCreated());
		Contributor contributorRef2 = new Contributor(contributor2.get_id(), "logintwo", 
													  "creatort", contributor2.getCreated(), "JunitTests");
		assertEquals(contributorRef2, contributor2);

		checkContributorQuantity(2);
		assertEquals(true, ws_client.checkOneContributorByLogin("loginone"));
		assertEquals(true, ws_client.checkOneContributorByLogin("logintwo"));

		Contributor contributor3=ws_client.getOneContributorByLogin("logintwo");
		assertEquals(contributorRef2, contributor3);

		//Get one single contributor1
		checkContributorQuantity(2);
		assertEquals(true, ws_client.checkOneContributorByLogin("loginone"));
		assertEquals(true, ws_client.checkOneContributorByLogin("logintwo"));

		Contributor contributor5=ws_client.getOneContributorById(contributor2.get_id());
		assertEquals(contributorRef2, contributor5);

		//Delete the contributors - Test DELETE function
		checkContributorQuantity(2);
		assertEquals(true, ws_client.checkOneContributorByLogin("loginone"));
		assertEquals(true, ws_client.checkOneContributorByLogin("logintwo"));

		Contributor contributor6=ws_client.deleteOneContributor(contributor1.get_id());
		assertEquals(contributorRef, contributor6);

		checkContributorQuantity(1);
		assertEquals(false, ws_client.checkOneContributorByLogin("loginone"));
		assertEquals(true,  ws_client.checkOneContributorByLogin("logintwo"));

		Contributor contributor7=ws_client.deleteOneContributor(contributor2.get_id());
		assertEquals(contributorRef2, contributor7);

		checkContributorQuantity(0);
		assertEquals(false, ws_client.checkOneContributorByLogin("loginone"));
		assertEquals(false, ws_client.checkOneContributorByLogin("logintwo"));
	}
}