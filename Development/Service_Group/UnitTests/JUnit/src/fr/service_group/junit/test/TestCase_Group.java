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


import java.util.TreeSet;
import org.junit.Test;

import fr.service_group.junit.model.TestCase_Model;
import fr.service_group.object.Group;
import fr.service_group.object.Responsible;

/* 
 * Class 	: TestCase_Group
 * Author(s): Zidmann
 * Function : This class contains the webService client JUnit tests to check if there was no regression in Group service 
 * Version  : 1.0.0 
 * Note		: This Test Case supposes that you started the NodeJS server
 * 			  and removed all the group in the MongoDB database
 */
public class TestCase_Group extends TestCase_Model
{
	//Testing all the group APIs for usual actions
	@Test
	public void testgroupClientAPI() throws Exception{	
		initSettings();

		checkGroupQuantity(0);

		//Creation of two groups - Test POST function
		Group group1 = ws_client.createGroup("nameone", "type1", "description1", "mail1@mail.fr",
											 "logo1.png", "picture1.png", "creator");
		assertNotNull(group1);
		assertNotNull(group1.get_id());
		assertNotNull(group1.getCreated());

		Group groupRef = new Group(group1.get_id(), 	
								   "nameone", "type1", "description1", "mail1@mail.fr",
								   "logo1.png", "picture1.png", 
								   new TreeSet<String>(), new TreeSet<Responsible>(),
								   "creator", group1.getCreated(), "JunitTests", 
								   null, null, null);
		checkGroupQuantity(1);

		Group group2 = ws_client.createGroup("nametwo", "type2", "description2", "mail2@mail.fr",
				 							 "logo2.png", "picture2.png", "creator");
		assertNotNull(group2);
		assertNotNull(group2.get_id());
		assertNotNull(group2.getCreated());

		Group groupRef2 = new Group(group2.get_id(), 	
								    "nametwo", "type2", "description2", "mail2@mail.fr",
								    "logo2.png", "picture2.png", 
								    new TreeSet<String>(), new TreeSet<Responsible>(),
								    "creator", group2.getCreated(), "JunitTests", 
								    null, null, null);
		assertEquals(groupRef2, group2);
		checkGroupQuantity(2);

		Group group3 = ws_client.getOneGroupById(group1.get_id());
		assertEquals(groupRef, group3);

		Group group4 = ws_client.getOneGroupByName("nametwo");
		assertEquals(groupRef2, group4);

		//Update one group1
		checkGroupQuantity(2);
		Group group6=ws_client.updateGroup(group2.get_id(),
										   "nametwo", "type2", "description2", "mail2@mail.fr",
										   "logo2.png", "picture2.png",
										   "updator");
		assertNotNull(group6.getUpdated());
		groupRef2.setName("nametwo");
		groupRef2.setType("type2");
		groupRef2.setDescription("description2");
		groupRef2.setMail("mail2@mail.fr");
		groupRef2.setPicture("logo2.png");
		groupRef2.setPicture("picture2.png");
		groupRef2.setUpdated(group6.getUpdated());
		groupRef2.setUpdator("updator");
		groupRef2.setUpdatorService("JunitTests");
		assertEquals(groupRef2, group6);

		//Get one single group1
		checkGroupQuantity(2);
		Group group7=ws_client.getOneGroupById(group2.get_id());
		assertEquals(groupRef2, group7);

		//Delete the group - Test DELETE function
		checkGroupQuantity(2);
		Group group8=ws_client.deleteOneGroup(group1.get_id());
		assertEquals(groupRef, group8);

		checkGroupQuantity(1);
		Group group9=ws_client.deleteOneGroup(group2.get_id());
		assertEquals(groupRef2, group9);

		checkGroupQuantity(0);
	}
}