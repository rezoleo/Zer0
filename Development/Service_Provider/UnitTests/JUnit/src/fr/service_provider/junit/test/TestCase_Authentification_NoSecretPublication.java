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


import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import fr.service_authentification.object.Authentification;
import fr.service_provider.junit.model.TestCase_Model;
import fr.core.network.HttpCommunication;

/* 
 * Class 	: TestCase_Authentification_NoSecretPublication
 * Author(s): Zidmann
 * Function : This class contains the webService client JUnit tests to check if the version (__v), IP (creatorIP, updatorIP) and the password are not shown when data are extracted from Provider service through Authentification service 
 * Version  : 1.0.0 
 * Note		: This Test Case supposes that you started the NodeJS server
 * 			  and removed all the Authentification elements in the MongoDB database
 */
public class TestCase_Authentification_NoSecretPublication extends TestCase_Model
{ 
	protected Gson 					gson  	      = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
	protected String 				response  	  = null;

	protected Authentification access =null;

	@Test
	public void testSecretPublicationGetRequest() throws Exception{
		initSettings();

		//Creation of one access
		ws_client_auth.createAccess("loginone", "password", "mailone@mail.com", "ON", "creator");

		//Update of the password
    	String http_address = TestCase_Model.URL+"/api/authentification/"+"loginone";
    	@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_provider");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}

		String response = HttpCommunication.getInstance().sendGet(http_address);

		//Deletion of the access
		ws_client_auth.deleteAccess("loginone");

		//Check to see if the JSON response does not contain a field with IP or password or error or version
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("password"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("__v"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretPublicationPostLoginRequest() throws Exception{
		initSettings();

		//Creation of one access
		ws_client_auth.createAccess("loginone", "password", "mailone@mail.com", "ON", "creator");

		//Check of the password
    	String http_address = TestCase_Model.URL+"/api/authentification/"+"loginone";
    	@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_provider");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("password", "password"));

		String response = HttpCommunication.getInstance().sendPost(http_address, urlParameters);

		//Deletion of the access
		ws_client_auth.deleteAccess("loginone");

		//Check to see if the JSON response does not contain a field with IP or password or error or version
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("password"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("__v"));
		assertEquals(-1, response.indexOf("CORE"));
	}
}