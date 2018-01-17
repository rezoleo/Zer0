package fr.service_group.junit.test;

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

import fr.service_group.junit.model.TestCase_Model;
import fr.service_group.object.Group;
import fr.service_group.object.Responsible;

/**
 * This class contains the webService client JUnit tests to check if there was no regression in Group service
 * <p>
 * This Test Case supposes that you started the NodeJS server and removed all the Group elements in the MongoDB database
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_Group_Member extends TestCase_Model
{
	/**
	 * Testing all the group APIs for usual actions 
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testgroupClientAPI() throws Exception{	
		initSettings();

		checkGroupQuantity(0);

		//Creation of two groups - Test POST function
		Group group1 = ws_client.createGroup("nameone", "type1", "description1", "mail1@mail.fr",
											 "logo1.png", "picture1.png", "creator");
		assertNotNull(group1);
		assertNotNull(group1.get_id());

		group1=ws_client.addMemberToGroup(group1.get_id(), "login", "updator");
		group1=ws_client.addMemberToGroup(group1.get_id(), "login2", "updator");
		TreeSet<String> users = new TreeSet<String>();
		users.add("login");
		users.add("login2");

		Group groupRef = new Group(group1.get_id(), 	
								    "nameone", "type1", "description1", "mail1@mail.fr",
								    "logo1.png", "picture1.png", 
								    users, new TreeSet<Responsible>(),
								    "creator", group1.getCreated(), "JunitTests", 
								    "updator", group1.getUpdated(), "JunitTests");
		assertEquals(groupRef, group1);
		checkGroupQuantity(1);

		group1 = ws_client.removeMemberToGroup(group1.get_id(), "login", "updator");
		users.remove("login");
		groupRef.setUpdated(group1.getUpdated());
		groupRef.setMembers(users);
		assertEquals(groupRef, group1);

		group1 = ws_client.removeMemberToGroup(group1.get_id(), "login2", "updator");
		users.remove("login2");
		groupRef.setUpdated(group1.getUpdated());
		groupRef.setMembers(users);
		assertEquals(groupRef, group1);

		Group group2=ws_client.deleteOneGroup(group1.get_id());
		assertEquals(groupRef, group2);
		checkGroupQuantity(0);
	}
}