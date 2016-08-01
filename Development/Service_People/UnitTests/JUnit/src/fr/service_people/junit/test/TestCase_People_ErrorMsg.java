package fr.service_people.junit.test;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.service_people.junit.model.TestCase_Model;
import fr.service_people.object.Person;
import fr.webservicecore.error.ErrorReferential;
import fr.webservicecore.object.APIException;

/* 
 * Class 	: TestCase_People_ErrorMsg
 * Author(s): Zidmann
 * Function : This class contains the webService client JUnit tests to check if there was no regression in People service 
 * Version  : 1.0.0 
 * Note		: This Test Case supposes that you started the NodeJS server
 * 			  and removed all the people in the MongoDB database
 */
public class TestCase_People_ErrorMsg extends TestCase_Model
{ 
	protected Gson 					gson  	      = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
	protected String 				response  	  = null;

	protected Person person1 = null;
	protected Person person2 = null;

	@Test
	public void testErrorGetUnkownPersonByID() throws Exception{
		initSettings();
		try{
			ws_client.getOnePersonById("id1");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.1.2"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorGetUnkownPersonByIDBis() throws Exception{
		initSettings();
		try{
			person1 = ws_client.createPerson("loginone", "lastnameone", "firstnameone", "M", null, false, "mailone@mail.com", null, "pictureone.png", "creator");
			ws_client.getOnePersonById("id1");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.1.2"), e.getMsg());
			ws_client.deleteOnePerson(person1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorGetUnkownPersonByLogin() throws Exception{
		initSettings();
		try{
			ws_client.getOnePersonByLogin("login");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.2.2"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorGetUnkownPersonByLoginBis() throws Exception{
		initSettings();
		try{
			person1 = ws_client.createPerson("loginone", "lastnameone", "firstnameone", "M", null, false, "mailone@mail.com", null, "pictureone.png", "creator");
			ws_client.getOnePersonByLogin("login");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.2.2"), e.getMsg());
			ws_client.deleteOnePerson(person1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorGetUnkownPersonByMail() throws Exception{
		initSettings();
		try{
			ws_client.getOnePersonByMail("mail@mail.com");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.3.2"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorGetUnkownPersonByMailBis() throws Exception{
		initSettings();
		try{
			person1 = ws_client.createPerson("loginone", "lastnameone", "firstnameone", "M", null, false, "mailone@mail.com", null, "pictureone.png", "creator");
			ws_client.getOnePersonByMail("mail@mail.com");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.3.2"), e.getMsg());
			ws_client.deleteOnePerson(person1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorPostPersonLoginDuplication() throws Exception{
		initSettings();
		try{
			person1 = ws_client.createPerson("loginone", "lastnameone", "firstnameone", "M", null, false, "mailone@mail.com", null, "pictureone.png", "creatoro");
			ws_client.createPerson("loginone", "lastnametwo", "firstnametwo", "M", null, false, "mailtwo@mail.com", null, "picturetwo.png", "creatort");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.4.4"), e.getMsg());
			ws_client.deleteOnePerson(person1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorPostPersonMailDuplication() throws Exception{
		initSettings();
		try{
			person1 = ws_client.createPerson("loginone", "lastnameone", "firstnameone", "M", null, false, "mailone@mail.com", null, "pictureone.png", "creatoro");
			ws_client.createPerson("logintwo", "lastnametwo", "firstnametwo", "M", null, false, "mailone@mail.com", null, "picturetwo.png", "creatort");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.4.5"), e.getMsg());
			ws_client.deleteOnePerson(person1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorPostTagUnknownPerson() throws Exception{
		initSettings();
		try{
			ws_client.addTagToPerson("id", "TAG", "updator");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.5.3"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorPostTagUnknownPersonBis() throws Exception{
		initSettings();
		try{
			person1 = ws_client.createPerson("loginone", "lastnameone", "firstnameone", "M", null, false, "mailone@mail.com", null, "pictureone.png", "creatoro");
			ws_client.addTagToPerson("id", "TAG", "updator");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.5.3"), e.getMsg());
			ws_client.deleteOnePerson(person1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorPostTagDuplication() throws Exception{
		initSettings();
		try{
			person1 = ws_client.createPerson("loginone", "lastnameone", "firstnameone", "M", null, false, "mailone@mail.com", null, "pictureone.png", "creatoro");
			ws_client.addTagToPerson(person1.get_id(), "TAG", "updator");
			ws_client.addTagToPerson(person1.get_id(), "TAG", "updator");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.5.4"), e.getMsg());
			ws_client.deleteOnePerson(person1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorPutUnknownPerson() throws Exception{
		initSettings();
		try{
			ws_client.updatePerson("id", "login", "lastname", "firstname", "M", null, false, null, null, "picture.png", "updator");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.6.5"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorPutUnknownPersonBis() throws Exception{
		initSettings();
		try{
			person1 = ws_client.createPerson("loginone", "lastnameone", "firstnameone", "M", null, false, "mailone@mail.com", null, "pictureone.png", "creatoro");
			ws_client.updatePerson("id", "login", "lastname", "firstname", "M", null, false, null, null, "picture.png", "updator");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.6.5"), e.getMsg());
			ws_client.deleteOnePerson(person1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorUpdatePersonLoginDuplication() throws Exception{
		initSettings();
		try{
			person1 = ws_client.createPerson("loginone", "lastnameone", "firstnameone", "M", null, false, "mailone@mail.com", null, "pictureone.png", "creatoro");
			person2 = ws_client.createPerson("logintwo", "lastnametwo", "firstnametwo", "M", null, false, "mailtwo@mail.com", null, "picturetwo.png", "creatort");
			ws_client.updatePerson(person2.get_id(), "logintwo", "lastname", "firstname", "M", null, false, null, null, null, "updator");
			ws_client.updatePerson(person2.get_id(), "loginone", "lastname", "firstname", "M", null, false, null, null, null, "updator");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.6.6"), e.getMsg());
			ws_client.deleteOnePerson(person1.get_id());
			ws_client.deleteOnePerson(person2.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorUpdateUpdateMailDuplication() throws Exception{
		initSettings();
		try{
			person1 = ws_client.createPerson("loginone", "lastnameone", "firstnameone", "M", null, false, "mailone@mail.com", null, "pictureone.png", "creatoro");
			person2 = ws_client.createPerson("logintwo", "lastnametwo", "firstnametwo", "M", null, false, "mailtwo@mail.com", null, "picturetwo.png", "creatort");
			ws_client.updatePerson(person2.get_id(), "logintwo", "lastname", "firstname", "M", null, false, "mailtwo@mail.com", null, null, "updator");
			ws_client.updatePerson(person2.get_id(), "logintwo", "lastname", "firstname", "M", null, false, "mailone@mail.com", null, null, "updator");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.6.7"), e.getMsg());
			ws_client.deleteOnePerson(person1.get_id());
			ws_client.deleteOnePerson(person2.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorDeleteTagUnknownPerson() throws Exception{
		initSettings();
		try{
			ws_client.removeTagToPerson("id", "TAG", "updator");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.7.3"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorDeleteTagUnknownPersonBis() throws Exception{
		initSettings();
		try{
			person1 = ws_client.createPerson("loginone", "lastnameone", "firstnameone", "M", null, false, "mailone@mail.com", null, "pictureone.png", "creatoro");
			ws_client.removeTagToPerson("id", "TAG", "updator");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.7.3"), e.getMsg());
			ws_client.deleteOnePerson(person1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorDeleteUnknownTag() throws Exception{
		initSettings();
		try{
			person1 = ws_client.createPerson("loginone", "lastnameone", "firstnameone", "M", null, false, "mailone@mail.com", null, "pictureone.png", "creatoro");
			ws_client.removeTagToPerson(person1.get_id(), "TAG", "updator");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.7.4"), e.getMsg());
			ws_client.deleteOnePerson(person1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorDeleteUnknownTagBis() throws Exception{
		initSettings();
		try{
			person1 = ws_client.createPerson("loginone", "lastnameone", "firstnameone", "M", null, false, "mailone@mail.com", null, "pictureone.png", "creatoro");
			ws_client.addTagToPerson(person1.get_id(), "TAG", "updator");
			ws_client.removeTagToPerson(person1.get_id(), "TAG2", "updator");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.7.4"), e.getMsg());
			ws_client.deleteOnePerson(person1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorDeleteUnknownPerson() throws Exception{
		initSettings();
		try{
			ws_client.deleteOnePerson("id");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.8.2"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorDeleteUnknownPersonBis() throws Exception{
		initSettings();
		try{
			person1 = ws_client.createPerson("loginone", "lastnameone", "firstnameone", "M", null, false, "mailone@mail.com", null, "pictureone.png", "creatoro");
			ws_client.deleteOnePerson("id");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PEOPLE.1.8.2"), e.getMsg());
			ws_client.deleteOnePerson(person1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
}