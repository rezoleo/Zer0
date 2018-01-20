package fr.service_people.junit.test;

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

import fr.service_people.Common;
import fr.service_people.junit.model.TestCase_Model;
import fr.service_people.object.Person;
import fr.core.network.HttpCommunication;

/**
 * This class contains the webService client JUnit tests to check if the IP (creatorIP, updatorIP) are not shown when data are extracted from People service
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
		people = ws_client.createPerson("login", "lastname", "firstname", "M", null, false, "mail@mail.com", null, "picture.png", "creator");

    	String http_address = Common.URL+"/api/people";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_people");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendGet(http_address);

		//Deletion of the person
		ws_client.deleteOnePerson(people.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretGetOnePersonByIdRequest() throws Exception{
		initSettings();

		//Creation of one person
		people = ws_client.createPerson("login", "lastname", "firstname", "M", null, false, "mail@mail.com", null, "picture.png", "creator");

    	String http_address = Common.URL+"/api/people/"+people.get_id();
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_people");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendGet(http_address);

		//Deletion of the person
		ws_client.deleteOnePerson(people.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretGetOnePersonByLoginRequest() throws Exception{
		initSettings();

		//Creation of one person
		people = ws_client.createPerson("login", "lastname", "firstname", "M", null, false, "mail@mail.com", null, "picture.png", "creator");

    	String http_address = Common.URL+"/api/people/login/login";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_people");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendGet(http_address);

		//Deletion of the person
		ws_client.deleteOnePerson(people.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretGetOnePersonByMailRequest() throws Exception{
		initSettings();

		//Creation of one person
		people = ws_client.createPerson("login", "lastname", "firstname", "M", null, false, "mail@mail.com", null, "picture.png", "creator");

    	String http_address = Common.URL+"/api/people/mail/mail@mail.com";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_people");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendGet(http_address);

		//Deletion of the person
		ws_client.deleteOnePerson(people.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretPostOnePersonRequest() throws Exception{
		initSettings();

		//Creation of one people
    	String http_address = Common.URL+"/api/people";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_people");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("login",     "login"));
		urlParameters.add(new BasicNameValuePair("lastname",  "lastname"));
		urlParameters.add(new BasicNameValuePair("firstname", "firstname"));
		urlParameters.add(new BasicNameValuePair("sex", 	  "F"));
		urlParameters.add(new BasicNameValuePair("birthdate", null));
		urlParameters.add(new BasicNameValuePair("mail",      "mail@mail.com"));
		urlParameters.add(new BasicNameValuePair("tel",       null));
		urlParameters.add(new BasicNameValuePair("picture",   "picture.png"));
		urlParameters.add(new BasicNameValuePair("creator",   "creator"));
		String response = HttpCommunication.getInstance().sendPost(http_address, urlParameters);

		people = (Person)gson.fromJson(response, Person.class);

		//Deletion of the person
		ws_client.deleteOnePerson(people.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretPutOnePersonRequest() throws Exception{
		initSettings();

		//Creation of one person
		people = ws_client.createPerson("login", "lastname", "firstname", "M", null, false, "mail@mail.com", null, "picture.png", "creator");

		//Update of one people
		String http_address = Common.URL+"/api/people/"+people.get_id();
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_people");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("login",     "login"));
		urlParameters.add(new BasicNameValuePair("lastname",  "lastname"));
		urlParameters.add(new BasicNameValuePair("firstname", "firstname"));
		urlParameters.add(new BasicNameValuePair("sex", 	  "H"));
		urlParameters.add(new BasicNameValuePair("birthdate", null));
		urlParameters.add(new BasicNameValuePair("mail",      "mail@mail.com"));
		urlParameters.add(new BasicNameValuePair("tel",       null));
		urlParameters.add(new BasicNameValuePair("picture",   "picture.png"));
		urlParameters.add(new BasicNameValuePair("updator",   "updator"));
		String response = HttpCommunication.getInstance().sendPut(http_address, urlParameters);

		//Deletion of the person
		ws_client.deleteOnePerson(people.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretDeleteOnePersonByIdRequest() throws Exception{
		initSettings();

		//Creation of one person
		people = ws_client.createPerson("login", "lastname", "firstname", "M", null, false, "mail@mail.com", null, "picture.png", "creator");

		//Deletion of the people
    	String http_address = Common.URL+"/api/people/"+people.get_id();
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_people");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendDelete(http_address);

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
}