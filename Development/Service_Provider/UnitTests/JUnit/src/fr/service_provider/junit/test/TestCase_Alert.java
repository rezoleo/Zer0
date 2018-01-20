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


import java.util.Vector;

import org.junit.Test;

import fr.core.network.HttpCommunication;
import fr.service_alert.object.Alert;
import fr.service_provider.junit.model.TestCase_Model;

/**
 * This class contains the webService client JUnit tests to check if there was no regression in Provider service for Alert service
 * <p>
 * This Test Case supposes that you started the NodeJS server and removed all the Alert elements in the MongoDB database
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_Alert extends TestCase_Model
{
	/**
	 * Testing all the alerts APIs for usual actions 
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testAlertBroadcast() throws Exception{
		initSettings();

		int count=countAlertQuantity();
		checkAlertQuantity(count);

    	String http_address = TestCase_Model.URL+"/api/nothing";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_provider");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		HttpCommunication.getInstance().sendGet(http_address);
		Thread.sleep(1000);		// Pause necessary to let the Provider service to send an alert to Alert service

		checkAlertQuantity(count+1);
		Vector<Alert> alert_list=ws_client_alert.getAllAlert(null);

		assertEquals("{\"service\":\"Service_Provider\",\"version\":\"1.0.0\",\"code\":\"WSCORE.2.1.1\",\"message\":\"Api inexistante\",\"stack\":null,\"status\":404}", alert_list.get(count).getMessage());
		assertEquals("CRITICAL", alert_list.get(count).getLevel());
	}
}