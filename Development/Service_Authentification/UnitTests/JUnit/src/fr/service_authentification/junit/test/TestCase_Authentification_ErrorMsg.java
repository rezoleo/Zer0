package fr.service_authentification.junit.test;

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


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import fr.service_authentification.junit.model.TestCase_Model;
import fr.service_authentification.object.Authentification;
import fr.webservicecore.error.ErrorReferential;
import fr.webservicecore.object.APIException;

/* 
 * Class 	: TestCase_Authentification_ErrorMsg
 * Author(s): Zidmann
 * Function : This class contains the webService client JUnit tests to check if there was no regression in Authentification service 
 * Version  : 1.0.0 
 * Note		: This Test Case supposes that you started the NodeJS server
 * 			  and removed all the Authentification elements in the MongoDB database
 */
public class TestCase_Authentification_ErrorMsg extends TestCase_Model
{ 
	protected Gson 					gson  	      = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
	protected String 				response  	  = null;

	protected Authentification access =null;

	@Test
	public void testErrorGetUnkownAccess() throws Exception{
		initSettings();
		try{
			ws_client.checkAccess("login");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("AUTH.1.1.2"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorGetUnkownAccessBis() throws Exception{
		initSettings();
		try{
			access = ws_client.createAccess("lunknown", "password", "mailone@mail.com", "ON", "creator");
			ws_client.checkAccess("login");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("AUTH.1.1.2"), e.getMsg());
			ws_client.deleteAccess(access.getLogin());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorPostUnkownAccess() throws Exception{
		initSettings();
		try{
			ws_client.checkPassword("login", "passwordbis");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("AUTH.1.3.3"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorPostUnkownAccessBis() throws Exception{
		initSettings();
		try{
			access = ws_client.createAccess("lunknown", "password", "mailone@mail.com", "ON", "creator");
			ws_client.checkPassword("login", "passwordbis");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("AUTH.1.3.3"), e.getMsg());
			ws_client.deleteAccess(access.getLogin());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorPostLockedAccess() throws Exception{
		initSettings();
		try{
			access = ws_client.createAccess("login", "password", "mailone@mail.com", "OFF", "creator");
			ws_client.checkPassword("login", "password");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("AUTH.1.3.4"), e.getMsg());
			ws_client.deleteAccess(access.getLogin());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorPostLoginDuplication() throws Exception{
		initSettings();
		try{
			access = ws_client.createAccess("loginpst", "passOne", "mailone@mail.com", "ON", "creator");
			ws_client.createAccess("loginpst", "passTwo", "mailtwo@mail.com", "ON", "creator");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("AUTH.1.2.5"), e.getMsg());
			ws_client.deleteAccess(access.getLogin());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorPostMailDuplication() throws Exception{
		initSettings();
		try{
			access = ws_client.createAccess("loginone", "passOne", "mailpst@mail.com", "ON", "creator");
			ws_client.createAccess("logintwo", "passTwo", "mailpst@mail.com", "ON", "creator");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("AUTH.1.2.6"), e.getMsg());
			ws_client.deleteAccess(access.getLogin());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorPutUnknownAccess() throws Exception{
		initSettings();
		try{
			ws_client.updatePassword("login", "password", "mailone@mail.com", "ON", false, "updator");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("AUTH.1.4.5"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorPutUnknownAccessBis() throws Exception{
		initSettings();
		try{
			access = ws_client.createAccess("loginput", "passOne", "mailone@mail.com", "ON", "creator");
			ws_client.updatePassword("login", "password", "mailone@mail.com", "ON", false, "updator");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("AUTH.1.4.5"), e.getMsg());
			ws_client.deleteAccess(access.getLogin());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorDeleteUnknownAccess() throws Exception{
		initSettings();
		try{
			ws_client.deleteAccess("logindel");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("AUTH.1.5.2"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorDeleteUnknownAccessBis() throws Exception{
		initSettings();
		try{
			access = ws_client.createAccess("logindel", "passOne", "mailone@mail.com", "ON", "creator");
			ws_client.deleteAccess("login");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("AUTH.1.5.2"), e.getMsg());
			ws_client.deleteAccess(access.getLogin());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
}