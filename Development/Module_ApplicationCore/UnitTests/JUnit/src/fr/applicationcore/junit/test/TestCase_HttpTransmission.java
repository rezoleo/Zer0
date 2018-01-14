package fr.applicationcore.junit.test;

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


import java.util.Date;
import java.util.Vector;

import org.junit.Test;

import fr.applicationcore.junit.model.TestCase_Model;
import fr.applicationcore.junit.network.ObjectDBClient;
import fr.applicationcore.junit.network.ObjectDBTokenClient;
import fr.applicationcore.junit.object.ObjectDB;
import fr.applicationcore.junit.object.SubField;

/**
 * This class contains JUnit tests to check if there was no regression in the communication between an application and a service.
 * <p>
 * This Test Case supposes that you started the NodeJS server designed to test 'ApplicationCore' class and remove all ObjectDBs in the MongoDB database of the application called by the server
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_HttpTransmission extends TestCase_Model
{
	/**
	 * Testing all the communication between an application and a webservice
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testObjectDBClientAPI() throws Exception{
		ws_objectdb_client = new ObjectDBClient();
		auxi_function();
	}

	/**
	 * Testing all the communication between an application and a webservice with a token
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testObjectDBTokenClientAPI() throws Exception{
		ws_objectdb_client = new ObjectDBTokenClient();
		auxi_function();
	}

	protected void auxi_function() throws Exception{
		initSettings();
		//Removing all the objects in MongoDB
		ws_objectdb_client.deleteObjectDB();

		Vector<ObjectDB> ObjDB=this.ws_objectdb_client.getAllObjectDB();
		assertEquals(0, ObjDB.size());

		//Creation of two ObjectDBs - Test POST function
		ObjectDB objectRef = new ObjectDB("_id",
										  null, null, "field2", "YES",
										  "A", new Date(), true, new SubField(0, 0));
		ObjectDB object1   = ws_objectdb_client.createObjectDB();
		ObjectDB object2   = ws_objectdb_client.createObjectDB();
		assertNotNull(object1);
		assertNotNull(object2);
		assertNotNull(objectRef);

		assertNotNull(object1.getField5());
		object1.setField5(objectRef.getField5());
		object2.setField5(objectRef.getField5());
		assertEquals(objectRef, object1);
		assertEquals(objectRef, object2);

		//Get all the ObjectDBs - Test GET function
		ObjDB=ws_objectdb_client.getAllObjectDB();
		assertEquals(2, ObjDB.size());
		assertNotNull(ObjDB.get(0));
		assertNotNull(ObjDB.get(1));
		ObjDB.get(0).setField5(objectRef.getField5());
		ObjDB.get(1).setField5(objectRef.getField5());
		assertEquals(objectRef, ObjDB.get(0));
		assertEquals(objectRef, ObjDB.get(1));

		//Update one ObjectDB
		ws_objectdb_client.updateObjectDB();

		//Get one single ObjectDB
		ObjDB=ws_objectdb_client.getAllObjectDB();
		assertEquals(2, ObjDB.size());
		assertNotNull(ObjDB.get(0));
		assertNotNull(ObjDB.get(1));
		ObjDB.get(0).setField5(objectRef.getField5());
		ObjDB.get(1).setField5(objectRef.getField5());
		assertEquals(ObjDB.get(0), objectRef);
		assertEquals(ObjDB.get(1), objectRef);

		//Delete all the elements
		ws_objectdb_client.deleteObjectDB();
		ObjDB=ws_objectdb_client.getAllObjectDB();
		assertEquals(ObjDB.size(), 0);
	}
}