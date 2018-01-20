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

import fr.service_provider.junit.model.TestCase_Model;
import fr.webservicecore.error.APIException;
import fr.webservicecore.error.ErrorMessage;
import fr.webservicecore.error.ErrorReferential;

/**
 * This class contains the webService client JUnit tests to check if the service does not accept forbidden letters
 * <p>
 * This Test Case supposes that you started the NodeJS server and removed all the Poeple elements in the MongoDB database
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_People_RejectForbiddenLetter extends TestCase_Model
{ 
	protected String 				response  	  = null;

	@Test
	public void testGetOneByIdRequest() throws Exception{
		for(int i=0; i<this.forbiddenLetters.length; i++){
			testGetOneByIdRequestAuxi(this.forbiddenLetters[i]);
		}
	}
	protected void testGetOneByIdRequestAuxi(String forbiddenLetter) throws Exception{
		initSettings();

		try{
			ws_client_people_proxy.getOnePersonById("id"+forbiddenLetter);
			assertNotNull(null);
		}
		catch(APIException e){
			ErrorMessage msg=e.getMsg();
			msg.setService(service);
			msg.setVersion(version);
			checkMessage(ErrorReferential.getErrorByCode("WSCORE-JAR-2"), msg);
		}
		catch(Exception e) {	assertNotNull(null);	}
	}

	@Test
	public void testGetOneByNameRequest() throws Exception{
		for(int i=0; i<this.forbiddenLetters.length; i++){
			testGetOneByNameRequestAuxi(this.forbiddenLetters[i]);
		}
	}
	protected void testGetOneByNameRequestAuxi(String forbiddenLetter) throws Exception{
		initSettings();

		try{
			ws_client_people_proxy.getOnePersonByLogin("login"+forbiddenLetter);
			assertNotNull(null);
		}
		catch(APIException e){
			ErrorMessage msg=e.getMsg();
			msg.setService(service);
			msg.setVersion(version);
			checkMessage(ErrorReferential.getErrorByCode("WSCORE-JAR-2"), msg);
		}
		catch(Exception e) {	assertNotNull(null);	}
	}

	@Test
	public void testGetOneByNameSearchRequest() throws Exception{
		for(int i=0; i<this.forbiddenLetters.length; i++){
			testGetOneByNameSearchRequestAuxi(this.forbiddenLetters[i]);
		}
	}
	protected void testGetOneByNameSearchRequestAuxi(String forbiddenLetter) throws Exception{
		initSettings();

		try{
			ws_client_people_proxy.getOnePersonByMail("mail"+forbiddenLetter);
			assertNotNull(null);
		}
		catch(APIException e){
			ErrorMessage msg=e.getMsg();
			msg.setService(service);
			msg.setVersion(version);
			checkMessage(ErrorReferential.getErrorByCode("WSCORE-JAR-2"), msg);
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
}