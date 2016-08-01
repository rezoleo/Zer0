package fr.service_card.junit.test;

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


import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import fr.service_card.Common;
import fr.service_card.junit.model.TestCase_Model;
import fr.service_card.object.Card;
import fr.core.network.HttpCommunication;

/* 
 * Class 	: TestCase_Card_NoSecretPublication
 * Author(s): Zidmann
 * Function : This class contains the webService client JUnit tests to check if the IP (creatorIP, updatorIP) are not shown when data are extracted from Card service 
 * Version  : 1.0.0 
 * Note		: This Test Case supposes that you started the NodeJS server
 * 			  and removed all the Card elements in the MongoDB database
 */
public class TestCase_Card_NoSecretPublication extends TestCase_Model
{
	protected Gson 					gson  	      = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
	protected String 				response  	  = null;

	protected Card 	card =null;

	@Test
	public void testSecretGetAllCardsRequest() throws Exception{
		initSettings();

		//Creation of one card
		card = ws_client.createCard("owner", "aaaaaaaa", "ON", "creator");

    	String http_address = Common.URL+"/api/card";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_card");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendGet(http_address);

		//Deletion of the card
		ws_client.deleteOneCard(card.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretGetOneCardByIdRequest() throws Exception{
		initSettings();

		//Creation of one card
		card = ws_client.createCard("owner", "aaaaaaaa", "ON", "creator");

    	String http_address = Common.URL+"/api/card/"+card.get_id();
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_card");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendGet(http_address);

		//Deletion of the card
		ws_client.deleteOneCard(card.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretGetOneCardByCodeRequest() throws Exception{
		initSettings();

		//Creation of one card
		card = ws_client.createCard("owner", "aaaaaaaa", "ON", "creator");

    	String http_address = Common.URL+"/api/card/code/aaaaaaaa";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_card");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendGet(http_address);

		//Deletion of the card
		ws_client.deleteOneCard(card.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretPostOneCardRequest() throws Exception{
		initSettings();

		//Creation of one card
    	String http_address = Common.URL+"/api/card";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_card");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("owner",    "owner"));
		urlParameters.add(new BasicNameValuePair("code",     "aaaaaaaa"));
		urlParameters.add(new BasicNameValuePair("status",   "ON"));
		urlParameters.add(new BasicNameValuePair("creator",  "creator"));
		String response = HttpCommunication.getInstance().sendPost(http_address, urlParameters);

		card = (Card)gson.fromJson(response, Card.class);

		//Deletion of the card
		ws_client.deleteOneCard(card.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretPutOneCardRequest() throws Exception{
		initSettings();

		//Creation of one card
		card=ws_client.createCard("owner", "aaaaaaaa", "ON", "creator");

		//Update of one card
		String http_address = Common.URL+"/api/card/"+card.get_id();
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_card");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("owner",    "owner"));
		urlParameters.add(new BasicNameValuePair("code",     "aaaaaaaa"));
		urlParameters.add(new BasicNameValuePair("status",   "ON"));
		urlParameters.add(new BasicNameValuePair("updator",  "updator"));
		String response = HttpCommunication.getInstance().sendPut(http_address, urlParameters);

		//Deletion of the card
		ws_client.deleteOneCard(card.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretDeleteOneCardByIdRequest() throws Exception{
		initSettings();

		//Creation of one card
		card = ws_client.createCard("owner", "aaaaaaaa", "ON", "creator");

		//Deletion of the card
    	String http_address = Common.URL+"/api/card/"+card.get_id();
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_card");
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