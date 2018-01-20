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


import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import fr.service_alert.Common;
import fr.service_alert.junit.model.TestCase_Model;
import fr.service_alert.object.Alert;
import fr.core.network.HttpCommunication;

/**
 * This class contains the webService client JUnit tests to check if the IP (creatorIP, updatorIP) are not shown when data are extracted from Alert service
 * <p>
 * This Test Case supposes that you started the NodeJS server
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_Alert_NoSecretPublication extends TestCase_Model
{
	protected Gson 					gson  	      = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
	protected String 				response  	  = null;

	protected Alert 	alert =null;

	@Test
	public void testSecretGetAllAlertsRequest() throws Exception{
		initSettings();

		//Creation of one alert
		alert = ws_client.createAlert("messageINFO_2", "INFO");

    	String http_address = Common.URL+"/api/alert";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_alert");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendGet(http_address);

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
	}
	@Test
	public void testSecretGetOneAlertByIdRequest() throws Exception{
		initSettings();

		//Creation of one alert
		alert = ws_client.createAlert("messageINFO_3", "INFO");

    	String http_address = Common.URL+"/api/alert/"+alert.get_id();
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_alert");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendGet(http_address);

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretPostOneAlertRequest() throws Exception{
		initSettings();

		//Creation of one alert
    	String http_address = Common.URL+"/api/alert";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_alert");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("message", "messageINFO"));
		urlParameters.add(new BasicNameValuePair("level",   "INFO"));
		String response = HttpCommunication.getInstance().sendPost(http_address, urlParameters);

		alert = (Alert)gson.fromJson(response, Alert.class);

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
}