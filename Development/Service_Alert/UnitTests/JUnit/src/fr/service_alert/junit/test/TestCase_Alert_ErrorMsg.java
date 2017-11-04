package fr.service_alert.junit.test;

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

import fr.service_alert.junit.model.TestCase_Model;
import fr.webservicecore.error.ErrorReferential;
import fr.webservicecore.object.APIException;

/* 
 * Class 	: TestCase_Alert_ErrorMsg
 * Author(s): Zidmann
 * Function : This class contains the webService client JUnit tests to check if there was no regression in Alert service 
 * Version  : 1.0.0 
 * Note		: This Test Case supposes that you started the NodeJS server
 */
public class TestCase_Alert_ErrorMsg extends TestCase_Model
{ 
	protected Gson 					gson  	      = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
	protected String 				response  	  = null;

	@Test
	public void testErrorGetAllAlertdBadLevel() throws Exception{
		initSettings();
		try{
			ws_client.getAllAlert("BADLevel");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("ALERT.1.1.1"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorGetUnkownAlertdByID() throws Exception{
		initSettings();
		try{
			ws_client.getOneAlertById("id1");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("ALERT.1.2.2"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorGetUnkownAlertByIDBis() throws Exception{
		initSettings();
		try{
			ws_client.createAlert("messageINFO_1", "INFO");
			ws_client.getOneAlertById("id1");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("ALERT.1.2.2"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
}