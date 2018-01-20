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


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import fr.service_contributor.object.Contributor;
import fr.service_contributor.junit.model.TestCase_Model;
import fr.webservicecore.error.APIException;
import fr.webservicecore.error.ErrorReferential;

/**
 * This class contains the webService client JUnit tests to check if there was no regression in Contributor service
 * <p>
 * This Test Case supposes that you started the NodeJS server and removed all the Contributor elements in the MongoDB database
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_Contributor_ErrorMsg extends TestCase_Model
{ 
	protected Gson 					gson  	      = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
	protected String 				response  	  = null;

	protected Contributor contributor1 = null;
	protected Contributor contributor2 = null;

	@Test
	public void testErrorGetUnkownContributorByID() throws Exception{
		initSettings();
		try{
			ws_client.getOneContributorById("id1");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("CONTRIBUTOR.1.1.2"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorGetUnkownContributorByIDBis() throws Exception{
		initSettings();
		try{
			contributor1 = ws_client.createContributor("login", "creator");
			ws_client.getOneContributorById("id1");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("CONTRIBUTOR.1.1.2"), e.getMsg());
			ws_client.deleteOneContributor(contributor1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorGetUnkownContributorByLogin() throws Exception{
		initSettings();
		try{
			ws_client.getOneContributorByLogin("login");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("CONTRIBUTOR.1.2.3"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorGetUnkownContributorByLoginBis() throws Exception{
		initSettings();
		try{
			contributor1 = ws_client.createContributor("logino", "creator");
			ws_client.getOneContributorByLogin("logint");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("CONTRIBUTOR.1.2.3"), e.getMsg());
			ws_client.deleteOneContributor(contributor1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorPostContributorDuplication() throws Exception{
		initSettings();
		try{
			contributor1 = ws_client.createContributor("login", "creatoro");
			ws_client.createContributor("login", "creatort");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("CONTRIBUTOR.1.3.2"), e.getMsg());
			ws_client.deleteOneContributor(contributor1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorDeleteUnknowncontributor() throws Exception{
		initSettings();
		try{
			ws_client.deleteOneContributor("id1");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("CONTRIBUTOR.1.4.2"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorDeleteUnknownContributorBis() throws Exception{
		initSettings();
		try{
			contributor1 = ws_client.createContributor("login", "creatoro");
			ws_client.deleteOneContributor("id1");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("CONTRIBUTOR.1.4.2"), e.getMsg());
			ws_client.deleteOneContributor(contributor1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
}