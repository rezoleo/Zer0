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


import java.util.TreeSet;

import org.junit.Test;

import fr.service_people.junit.model.TestCase_Model;
import fr.service_people.object.Person;

/* 
 * Class 	: TestCase_People
 * Author(s): Zidmann
 * Function : This class contains the webService client JUnit tests to check if there was no regression in People service 
 * Version  : 1.0.0 
 * Note		: This Test Case supposes that you started the NodeJS server
 * 			  and removed all the people in the MongoDB database
 */
public class TestCase_People extends TestCase_Model
{
	//Testing all the people APIs for usual actions
	@Test
	public void testPersonClientAPI() throws Exception{	
		initSettings();

		//Creation of two people - Test POST function
		//Get all the people - Test GET function
		checkPeopleQuantity(0);

		Person person1 = ws_client.createPerson("loginone", "lastnameone", "firstnameone", "M", null, false, "mailone@mail.com", null, "pictureone.png", "creator");
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

		Person person2 = ws_client.createPerson("logintwo", "lastnametwo", "firstnametwo", "F", null, false, "mailtwo@mail.com", null, "picturetwo.png", "creator");
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

		Person person3 = ws_client.getOnePersonById(person1.get_id());
		assertEquals(personRef, person3);

		Person person4 = ws_client.getOnePersonByLogin("logintwo");
		assertEquals(personRef2, person4);

		Person person5 = ws_client.getOnePersonByMail("mailone@mail.com");
		assertEquals(personRef, person5);

		//Update one person1
		checkPeopleQuantity(2);
		Person person6=ws_client.updatePerson(person2.get_id(), "login", "lastname", "firstname", "M", null, true, null, null, null, "updator");
		assertNotNull(person6.getUpdated());
		personRef2.setLogin("login");
		personRef2.setLastname("LASTNAME");
		personRef2.setFirstname("Firstname");
		personRef2.setSex("M");
		personRef2.setMajor(true);
		personRef2.setMail("");
		personRef2.setPicture("");
		personRef2.setUpdated(person6.getUpdated());
		personRef2.setUpdator("updator");
		personRef2.setUpdatorService("JunitTests");
		assertEquals(personRef2, person6);

		//Get one single person1
		checkPeopleQuantity(2);
		Person person7=ws_client.getOnePersonById(person2.get_id());
		assertEquals(personRef2, person7);

		//Delete the people - Test DELETE function
		checkPeopleQuantity(2);
		Person person8=ws_client.deleteOnePerson(person1.get_id());
		assertEquals(personRef, person8);

		checkPeopleQuantity(1);
		Person person9=ws_client.deleteOnePerson(person2.get_id());
		assertEquals(personRef2, person9);

		checkPeopleQuantity(0);
	}
}