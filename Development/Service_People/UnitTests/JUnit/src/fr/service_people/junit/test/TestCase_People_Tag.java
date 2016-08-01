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


import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

import fr.service_people.junit.model.TestCase_Model;
import fr.service_people.object.Person;
import fr.webservicecore.object.APIException;

/* 
 * Class 	: TestCase_People_Tag
 * Author(s): Zidmann
 * Function : This class contains the webService client JUnit tests to check if there was no regression in People service for tag management
 * Version  : 1.0.0 
 * Note		: This Test Case supposes that you started the NodeJS server
 * 			  and removed all the people in the MongoDB database
 */
public class TestCase_People_Tag extends TestCase_Model
{
	SortedSet<String> tags = new TreeSet<String>();

	//Testing all the people APIs for usual actions
	@Test
	public void testPersonClientAPI() throws Exception{	
		initSettings();

		//Creation of two people - Test POST function
		//Get all the people - Test GET function
		checkPeopleQuantity(0);

		Person person1 = ws_client.createPerson("loginone", "lastnameone", "firstnameone", "M", null, false, "mailone@mail.com", null, "pictureone.png", "creator");
		checkPeopleQuantity(1);
		assertNotNull(person1);
		assertNotNull(person1.get_id());
		assertNotNull(person1.getCreated());

		Person personRef = new Person(person1.get_id(), 	
									  "loginone", "LASTNAMEONE", "Firstnameone", "M",
									  null, false, "mailone@mail.com", "", "pictureone.png",
									  tags,
									  "creator", person1.getCreated(), "JunitTests", 	
									  null, null, null);
		assertEquals(personRef, person1);
		testOperation(personRef, person1);

		personRef.setUpdatorService("JunitTests");
		personRef.setUpdator("updator");
		
		tags.add("TAG1");
		testOperation(personRef, ws_client.addTagToPerson(person1.get_id(), "tag1", "updator"));

		tags.add("TAG2");
		testOperation(personRef, ws_client.addTagToPerson(person1.get_id(), "TAG2", "updator"));

		tags.add("TAG0");
		testOperation(personRef, ws_client.addTagToPerson(person1.get_id(), "Tag0", "updator"));

		tags.remove("TAG1");
		testOperation(personRef, ws_client.removeTagToPerson(person1.get_id(), "taG1", "updator"));

		tags.add("TAG4");
		testOperation(personRef, ws_client.addTagToPerson(person1.get_id(), "TAg4", "updator"));

		Person person6 = ws_client.deleteOnePerson(person1.get_id());
		personRef.setUpdated(person6.getUpdated());
		assertEquals(personRef, person6);
		checkPeopleQuantity(0);
	}

	protected void testOperation(Person personRef, Person person) throws APIException{
		personRef.setUpdated(person.getUpdated());
		personRef.setTags(tags);
		assertEquals(personRef, person);
		checkPeopleQuantity(1);
	}
}