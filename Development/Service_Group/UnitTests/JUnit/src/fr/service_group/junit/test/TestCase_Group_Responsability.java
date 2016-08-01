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
 * Class 	: TestCase_Group_Member
 * Author(s): Zidmann
 * Function : This class contains the webService client JUnit tests to check if there was no regression in Group service 
 * Version  : 1.0.0 
 * Note		: This Test Case supposes that you started the NodeJS server
 * 			  and removed all the group in the MongoDB database
 */
public class TestCase_Group_Responsability extends TestCase_Model
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

		group1=ws_client.addResponsibleToGroup(group1.get_id(), "login", "responsability", "updator");
		TreeSet<Responsible> responsibles = new TreeSet<Responsible>();
		responsibles.add(new Responsible("login", "responsability"));

		Group groupRef = new Group(group1.get_id(), 	
								    "nameone", "type1", "description1", "mail1@mail.fr",
								    "logo1.png", "picture1.png", 
								    new TreeSet<String>(), responsibles,
								    "creator", group1.getCreated(), "JunitTests", 
								    "updator", group1.getUpdated(), "JunitTests");
		assertEquals(groupRef, group1);
		checkGroupQuantity(1);

		group1 = ws_client.removeResponsibleToGroup(group1.get_id(), "login", "responsability", "updator");
		groupRef.setUpdated(group1.getUpdated());
		groupRef.setResponsibles(new TreeSet<Responsible>());
		assertEquals(groupRef, group1);

		Group group2=ws_client.deleteOneGroup(group1.get_id());
		assertEquals(groupRef, group2);
		checkGroupQuantity(0);
	}
}