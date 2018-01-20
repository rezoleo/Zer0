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


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import fr.service_people.object.Person;
import fr.service_provider.junit.model.TestCase_Model;
import fr.core.network.HttpCommunication;

/**
 * This class contains the webService client JUnit tests to check if the version (__v), IP (creatorIP, updatorIP) and the password are not shown when data are extracted from Provider service through People service
 * <p>
 * This Test Case supposes that you started the NodeJS server and removed all the People elements in the MongoDB database
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_People_NoSecretPublication extends TestCase_Model
{ 
	protected Gson 					gson  	      = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
	protected String 				response  	  = null;

	protected Person 	people =null;

	@Test
	public void testSecretGetAllPeopleRequest() throws Exception{
		initSettings();

		//Creation of one person
		people = ws_client_people.createPerson("login", "lastname", "firstname", "M", null, false, "mail@mail.com", null, "picture.png", "creator");

    	String http_address = TestCase_Model.URL+"/api/people";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_provider");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendGet(http_address);

		//Deletion of the person
		ws_client_people.deleteOnePerson(people.get_id());

		//Check to see if the JSON response does not contain a field with IP or error or version
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("__v"));
		assertEquals(-1, response.indexOf("CORE"));

		//Check to see if the JSON response does not contain the birthdate attribute
		assertEquals(-1, response.indexOf("birthdate"));
	}
	@Test
	public void testSecretGetOnePersonByIdRequest() throws Exception{
		initSettings();

		//Creation of one person
		people = ws_client_people.createPerson("login", "lastname", "firstname", "M", null, false, "mail@mail.com", null, "picture.png", "creator");

    	String http_address = TestCase_Model.URL+"/api/people/"+people.get_id();
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_provider");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendGet(http_address);

		//Deletion of the person
		ws_client_people.deleteOnePerson(people.get_id());

		//Check to see if the JSON response does not contain a field with IP or error or version
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("__v"));
		assertEquals(-1, response.indexOf("CORE"));

		//Check to see if the JSON response does not contain the birthdate attribute
		assertEquals(-1, response.indexOf("birthdate"));
	}
	@Test
	public void testSecretGetOnePersonByLoginRequest() throws Exception{
		initSettings();

		//Creation of one person
		people = ws_client_people.createPerson("login", "lastname", "firstname", "M", null, false, "mail@mail.com", null, "picture.png", "creator");

    	String http_address = TestCase_Model.URL+"/api/people/login/login";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_provider");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendGet(http_address);

		//Deletion of the person
		ws_client_people.deleteOnePerson(people.get_id());

		//Check to see if the JSON response does not contain a field with IP or error or version
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("__v"));
		assertEquals(-1, response.indexOf("CORE"));

		//Check to see if the JSON response does not contain the birthdate attribute
		assertEquals(-1, response.indexOf("birthdate"));
	}
	@Test
	public void testSecretGetOnePersonByMailRequest() throws Exception{
		initSettings();

		//Creation of one person
		people = ws_client_people.createPerson("login", "lastname", "firstname", "M", null, false, "mail@mail.com", null, "picture.png", "creator");

    	String http_address = TestCase_Model.URL+"/api/people/mail/mail@mail.com";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_provider");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendGet(http_address);

		//Deletion of the person
		ws_client_people.deleteOnePerson(people.get_id());

		//Check to see if the JSON response does not contain a field with IP or error or version
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("__v"));
		assertEquals(-1, response.indexOf("CORE"));

		//Check to see if the JSON response does not contain the birthdate attribute
		assertEquals(-1, response.indexOf("birthdate"));
	}
}