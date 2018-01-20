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


import java.util.TreeSet;

import org.junit.Test;

import fr.service_provider.junit.model.TestCase_Model;
import fr.service_people.object.Person;

/**
 * This class contains the webService client JUnit tests to check if there was no regression in Provider service for People service
 * <p>
 * This Test Case supposes that you started the NodeJS server and removed all the People elements in the MongoDB database
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_People extends TestCase_Model
{
	/**
	 * Testing all the student contributors APIs for usual actions 
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testPersonClientAPI() throws Exception{	
		initSettings();

		//Creation of two people - Test POST function
		//Get all the people - Test GET function
		checkPeopleQuantity(0);

		Person person1 = ws_client_people.createPerson("loginone", "lastnameone", "firstnameone", "M", null, false, "mailone@mail.com", null, "pictureone.png", "creator");
		assertNotNull(person1);
		assertNotNull(person1.get_id());
		assertNotNull(person1.getCreated());

		Person personRef = new Person(person1.get_id(), 	
									  "loginone", "LASTNAMEONE", "Firstnameone", "M",
									  null, false, "mailone@mail.com", "", "pictureone.png",
									  new TreeSet<String>(),
									  "creator", person1.getCreated(), "JunitTests", 	
									  null, null, null);
		checkPeopleQuantity(1);

		Person person2 = ws_client_people.createPerson("logintwo", "lastnametwo", "firstnametwo", "F", null, false, "mailtwo@mail.com", null, "picturetwo.png", "creator");
		assertNotNull(person2);
		assertNotNull(person2.get_id());
		assertNotNull(person2.getCreated());

		Person personRef2 = new Person(person2.get_id(), 	
									   "logintwo", "LASTNAMETWO", "Firstnametwo", "F",
									   null, false, "mailtwo@mail.com", "", "picturetwo.png",
									   new TreeSet<String>(),
									   "creator", person2.getCreated(), "JunitTests", 	
									   null, null, null);
		assertEquals(personRef2, person2);
		checkPeopleQuantity(2);

		Person person3 = ws_client_people_proxy.getOnePersonById(person1.get_id());
		personRef.setCreator(null);
		personRef.setCreated(person3.getCreated());
		personRef.setCreatorService(null);
		assertEquals(personRef, person3);

		Person person4 = ws_client_people_proxy.getOnePersonByLogin("logintwo");
		personRef2.setCreator(null);
		personRef2.setCreated(person4.getCreated());
		personRef2.setCreatorService(null);
		assertEquals(personRef2, person4);

		Person person5 = ws_client_people_proxy.getOnePersonByMail("mailone@mail.com");
		personRef.setCreated(person5.getCreated());
		assertEquals(personRef, person5);

		//Update one person
		checkPeopleQuantity(2);
		Person person6=ws_client_people.updatePerson(person2.get_id(), "login", "lastname", "firstname", "M", null, true, null, null, null, "updator");
		assertNotNull(person6.getUpdated());
		personRef2.setLogin("login");
		personRef2.setLastname("LASTNAME");
		personRef2.setFirstname("Firstname");
		personRef2.setSex("M");
		personRef2.setMajor(true);
		personRef2.setMail("");
		personRef2.setPicture("");
		personRef2.setCreator("creator");
		personRef2.setCreated(person6.getCreated());
		personRef2.setCreatorService("JunitTests");
		personRef2.setUpdated(person6.getUpdated());
		personRef2.setUpdator("updator");
		personRef2.setUpdatorService("JunitTests");
		assertEquals(personRef2, person6);

		//Get one single person
		checkPeopleQuantity(2);
		Person person7=ws_client_people_proxy.getOnePersonById(person2.get_id());
		personRef2.setCreator(null);
		personRef2.setCreated(person7.getCreated());
		personRef2.setCreatorService(null);
		personRef2.setUpdator(null);
		personRef2.setUpdated(person7.getUpdated());
		personRef2.setUpdatorService(null);
		assertEquals(personRef2, person7);

		//Delete the people - Test DELETE function
		checkPeopleQuantity(2);
		Person person8=ws_client_people.deleteOnePerson(person1.get_id());
		personRef.setCreator("creator");
		personRef.setCreated(person8.getCreated());
		personRef.setCreatorService("JunitTests");
		assertEquals(personRef, person8);

		checkPeopleQuantity(1);
		Person person9=ws_client_people.deleteOnePerson(person2.get_id());
		personRef2.setCreator("creator");
		personRef2.setCreated(person9.getCreated());
		personRef2.setCreatorService("JunitTests");
		personRef2.setUpdator("updator");
		personRef2.setUpdated(person9.getUpdated());
		personRef2.setUpdatorService("JunitTests");
		assertEquals(personRef2, person9);

		checkPeopleQuantity(0);
	}
}