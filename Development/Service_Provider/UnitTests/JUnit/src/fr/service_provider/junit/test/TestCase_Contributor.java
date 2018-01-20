package fr.service_provider.junit.test;

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
import fr.service_provider.junit.model.TestCase_Model;

/* 
 * Class 	: TestCase_Contributor
 * Author(s): Zidmann
 * Function : This class contains the webService client JUnit tests to check if there was no regression in Provider service for Contributor service 
 * Version  : 1.0.0 
 * Note		: This Test Case supposes that you started the NodeJS server
 * 			  and removed all the contributors in the MongoDB database
 */
public class TestCase_Contributor extends TestCase_Model
{
	//Testing all the student contributors APIs for usual actions 
	@Test
	public void testContributorClientAPI() throws Exception{	
		initSettings();

		//Creation of two contributors - Test POST function
		//Get all the contributors - Test GET function
		checkContributorQuantity(0);
		assertEquals(false, ws_client_contributor_proxy.checkOneContributorByLogin("loginone"));
		assertEquals(false, ws_client_contributor_proxy.checkOneContributorByLogin("logintwo"));

		Contributor contributor1=ws_client_contributor.createContributor("loginone", "creatoro");
		assertNotNull(contributor1);
		assertNotNull(contributor1.get_id());
		assertNotNull(contributor1.getCreated());
		Contributor contributorRef = new Contributor(contributor1.get_id(), "loginone", 
													"creatoro", contributor1.getCreated(), "JunitTests");
		assertEquals(contributorRef, contributor1);

		checkContributorQuantity(1);
		assertEquals(true,  ws_client_contributor_proxy.checkOneContributorByLogin("loginone"));
		assertEquals(false, ws_client_contributor_proxy.checkOneContributorByLogin("logintwo"));

		Contributor contributor2=ws_client_contributor.createContributor("logintwo", "creatort");
		assertNotNull(contributor2);
		assertNotNull(contributor2.get_id());
		assertNotNull(contributor2.getCreated());
		Contributor contributorRef2 = new Contributor(contributor2.get_id(), "logintwo", 
													  "creatort", contributor2.getCreated(), "JunitTests");
		assertEquals(contributorRef2, contributor2);

		checkContributorQuantity(2);
		assertEquals(true, ws_client_contributor_proxy.checkOneContributorByLogin("loginone"));
		assertEquals(true, ws_client_contributor_proxy.checkOneContributorByLogin("logintwo"));

		Contributor contributor3=ws_client_contributor_proxy.getOneContributorByLogin("logintwo");
		contributorRef2.setCreator(null);
		contributorRef2.setCreated(null);
		contributorRef2.setCreatorService(null);
		assertEquals(contributorRef2, contributor3);

		//Get one single contributor1
		checkContributorQuantity(2);
		assertEquals(true, ws_client_contributor_proxy.checkOneContributorByLogin("loginone"));
		assertEquals(true, ws_client_contributor_proxy.checkOneContributorByLogin("logintwo"));

		Contributor contributor5=ws_client_contributor_proxy.getOneContributorById(contributor2.get_id());
		assertEquals(contributorRef2, contributor5);

		//Delete the contributors - Test DELETE function
		checkContributorQuantity(2);
		assertEquals(true, ws_client_contributor_proxy.checkOneContributorByLogin("loginone"));
		assertEquals(true, ws_client_contributor_proxy.checkOneContributorByLogin("logintwo"));

		Contributor contributor6=ws_client_contributor.deleteOneContributor(contributor1.get_id());
		assertEquals(contributorRef, contributor6);

		checkContributorQuantity(1);
		assertEquals(false, ws_client_contributor_proxy.checkOneContributorByLogin("loginone"));
		assertEquals(true,  ws_client_contributor_proxy.checkOneContributorByLogin("logintwo"));

		Contributor contributor7=ws_client_contributor.deleteOneContributor(contributor2.get_id());
		contributorRef2.setCreator("creatort");
		contributorRef2.setCreated(contributor2.getCreated());
		contributorRef2.setCreatorService("JunitTests");
		assertEquals(contributorRef2, contributor7);

		checkContributorQuantity(0);
		assertEquals(false, ws_client_contributor_proxy.checkOneContributorByLogin("loginone"));
		assertEquals(false, ws_client_contributor_proxy.checkOneContributorByLogin("logintwo"));
	}
}