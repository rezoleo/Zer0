package fr.service_group.junit.test;

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

import fr.service_group.junit.model.TestCase_Model;
import fr.service_group.Common;
import fr.service_group.object.Group;
import fr.core.network.HttpCommunication;

/* 
 * Class 	: TestCase_Group_NoSecretPublication
 * Author(s): Zidmann
 * Function : This class contains the webService client JUnit tests to check if the IP (creatorIP, updatorIP) are not shown when data are extracted from Group service 
 * Version  : 1.0.0 
 * Note		: This Test Case supposes that you started the NodeJS server
 * 			  and removed all the Group elements in the MongoDB database
 */
public class TestCase_Group_NoSecretPublication extends TestCase_Model
{ 
	protected Gson 					gson  	      = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
	protected String 				response  	  = null;

	protected Group 	group =null;

	@Test
	public void testSecretGetAllGroupRequest() throws Exception{
		initSettings();

		//Creation of one group
		group = ws_client.createGroup("nameone", "type", "description", "mail@mail.fr", "logo.png", "picture.png", "creator");

    	String http_address = Common.URL+"/api/group";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_group");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendGet(http_address);

		//Deletion of the group
		ws_client.deleteOneGroup(group.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretGetOneGroupByIdRequest() throws Exception{
		initSettings();

		//Creation of one group
		group = ws_client.createGroup("nameone", "type", "description", "mail@mail.fr", "logo.png", "picture.png", "creator");

    	String http_address = Common.URL+"/api/group/"+group.get_id();
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_group");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendGet(http_address);

		//Deletion of the group
		ws_client.deleteOneGroup(group.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretGetOneGroupByNameRequest() throws Exception{
		initSettings();

		//Creation of one group
		group = ws_client.createGroup("nameone", "type", "description", "mail@mail.fr", "logo.png", "picture.png", "creator");

    	String http_address = Common.URL+"/api/group/name/"+group.getName();
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_group");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendGet(http_address);

		//Deletion of the group
		ws_client.deleteOneGroup(group.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretGetSeveralGroupByLoginRequest() throws Exception{
		initSettings();

		//Creation of one group
		group = ws_client.createGroup("nameone", "type", "description", "mail@mail.fr", "logo.png", "picture.png", "creator");
		ws_client.addMemberToGroup(group.get_id(), "login", "updator");

    	String http_address = Common.URL+"/api/group/search/login";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_group");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendGet(http_address);

		//Deletion of the group
		ws_client.deleteOneGroup(group.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretPostOneGroupRequest() throws Exception{
		initSettings();

		//Creation of one group
    	String http_address = Common.URL+"/api/group";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_group");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("name",		"name"));
		urlParameters.add(new BasicNameValuePair("type",		"type"));
		urlParameters.add(new BasicNameValuePair("description", "description"));
		urlParameters.add(new BasicNameValuePair("mail",		"mail@mail.com"));
		urlParameters.add(new BasicNameValuePair("logo",		"logo.png"));
		urlParameters.add(new BasicNameValuePair("picture",		"picture.png"));
		urlParameters.add(new BasicNameValuePair("creator",		"creator"));
		String response = HttpCommunication.getInstance().sendPost(http_address, urlParameters);

		group = (Group)gson.fromJson(response, Group.class);

		//Deletion of the group
		ws_client.deleteOneGroup(group.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretPostOneMemberRequest() throws Exception{
		initSettings();

		//Creation of one group
		group = ws_client.createGroup("nameone", "type", "description", "mail@mail.fr", "logo.png", "picture.png", "creator");

    	String http_address = Common.URL+"/api/group/"+group.get_id()+"/member";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_group");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("login",		"login"));
		String response = HttpCommunication.getInstance().sendPost(http_address, urlParameters);

		//Deletion of the group
		ws_client.deleteOneGroup(group.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretPostOneResponsibleRequest() throws Exception{
		initSettings();

		//Creation of one group
		group = ws_client.createGroup("nameone", "type", "description", "mail@mail.fr", "logo.png", "picture.png", "creator");

    	String http_address = Common.URL+"/api/group/"+group.get_id()+"/responsible";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_group");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("login",		   "login"));
		urlParameters.add(new BasicNameValuePair("responsability", "responsability"));
		String response = HttpCommunication.getInstance().sendPost(http_address, urlParameters);

		//Deletion of the group
		ws_client.deleteOneGroup(group.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretPutOneGroupRequest() throws Exception{
		initSettings();

		//Creation of one group
		group = ws_client.createGroup("nameone", "type", "description", "mail@mail.fr", "logo.png", "picture.png", "creator");

		//Update of one group
		String http_address = Common.URL+"/api/group/"+group.get_id();
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_group");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("name",		"name"));
		urlParameters.add(new BasicNameValuePair("type",		"type"));
		urlParameters.add(new BasicNameValuePair("description", "description"));
		urlParameters.add(new BasicNameValuePair("mail",		"mail@mail.com"));
		urlParameters.add(new BasicNameValuePair("logo",		"logo.png"));
		urlParameters.add(new BasicNameValuePair("picture",		"picture.png"));
		urlParameters.add(new BasicNameValuePair("creator",		"creator"));
		String response = HttpCommunication.getInstance().sendPut(http_address, urlParameters);

		//Deletion of the group
		ws_client.deleteOneGroup(group.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretDeleteOneGroupByIdRequest() throws Exception{
		initSettings();

		//Creation of one group
		group = ws_client.createGroup("nameone", "type", "description", "mail@mail.fr", "logo.png", "picture.png", "creator");

		//Deletion of the group
    	String http_address = Common.URL+"/api/group/"+group.get_id();
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_group");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		String response = HttpCommunication.getInstance().sendDelete(http_address);
		//Check to see if the JSON response does not contain the IP

		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretDeleteOneMemberRequest() throws Exception{
		initSettings();

		//Creation of one group
		group = ws_client.createGroup("nameone", "type", "description", "mail@mail.fr", "logo.png", "picture.png", "creator");
		ws_client.addMemberToGroup(group.get_id(), "login", "updator");

    	String http_address = Common.URL+"/api/group/"+group.get_id()+"/member";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_group");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("login",		"login"));
		String response = HttpCommunication.getInstance().sendPut(http_address, urlParameters);

		//Deletion of the group
		ws_client.deleteOneGroup(group.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
	@Test
	public void testSecretDeleteOneResponsibleRequest() throws Exception{
		initSettings();

		//Creation of one group
		group = ws_client.createGroup("nameone", "type", "description", "mail@mail.fr", "logo.png", "picture.png", "creator");
		ws_client.addResponsibleToGroup(group.get_id(), "login", "responsability", "updator");

    	String http_address = Common.URL+"/api/group/"+group.get_id()+"/responsible";
		@SuppressWarnings("static-access")
		String token 	   = token_list.getToken("token_group");
		if(token!=null && !token.equals("")){
			http_address+="?token="+token;
		}
		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("login",		   "login"));
		urlParameters.add(new BasicNameValuePair("responsability", "responsability"));
		String response = HttpCommunication.getInstance().sendPost(http_address, urlParameters);

		//Deletion of the group
		ws_client.deleteOneGroup(group.get_id());

		//Check to see if the JSON response does not contain the IP
		assertEquals(-1, response.indexOf("IP"));
		assertEquals(-1, response.indexOf("error"));
		assertEquals(-1, response.indexOf("CORE"));
	}
}