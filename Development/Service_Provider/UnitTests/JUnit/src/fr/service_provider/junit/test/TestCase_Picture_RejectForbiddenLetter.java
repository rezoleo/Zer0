package fr.service_provider.junit.test;

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

import fr.service_provider.junit.model.TestCase_Model;
import fr.webservicecore.error.ErrorReferential;
import fr.webservicecore.object.APIException;

/* 
 * Class 	: TestCase_Picture_RejectForbiddenLetter
 * Author(s): Zidmann
 * Function : This class contains the webService client JUnit tests to check if the service do not accept forbidden letters 
 * Version  : 1.0.0 
 * Note		: This Test Case supposes that you started the NodeJS server
 */
public class TestCase_Picture_RejectForbiddenLetter extends TestCase_Model
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
			ws_client_picture_proxy.getPictureInformation("directory"+forbiddenLetter, "filename");
			assertNotNull(null);
		}
		catch(APIException e){
			checkBasicMessage(ErrorReferential.getErrorByCode("WSCORE-JAR-2"), e.getMsg());
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
			ws_client_picture_proxy.getPictureInformation("directory", "filename"+forbiddenLetter);
			assertNotNull(null);
		}
		catch(APIException e){
			checkBasicMessage(ErrorReferential.getErrorByCode("WSCORE-JAR-2"), e.getMsg());
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
			ws_client_picture_proxy.getPictureInformation("directory"+forbiddenLetter, "filename"+forbiddenLetter);
			assertNotNull(null);
		}
		catch(APIException e){
			checkBasicMessage(ErrorReferential.getErrorByCode("WSCORE-JAR-2"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
}