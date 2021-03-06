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


import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.applicationcore.Common;
import fr.applicationcore.error.ErrorReferential;
import fr.applicationcore.junit.model.TestCase_Model;
import fr.applicationcore.error.ErrorMessage;
import fr.core.network.HttpCommunication;

/**
 * This class contains the application client JUnit tests to check some error message in a NodeJS server using the 'ApplicationCore' module
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_ErrorMsg extends TestCase_Model
{
	protected Gson 				gson  	  = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
	protected String 			response  = null;

	/** 
	 * Testing the connection to an unknown HTTP address using the API prefix
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testErrorUnknownAPI() throws Exception{
		initSettings();

		ErrorMessage msg = null;
		response=HttpCommunication.getInstance().sendGet(Common.URL+"/api/");
		msg = gson.fromJson(response, ErrorMessage.class);
		checkMessage(ErrorReferential.getErrorByCode("APPCORE.2.1.1"), msg);

		response=HttpCommunication.getInstance().sendGet(Common.URL+"/api/nothing");
		msg = gson.fromJson(response, ErrorMessage.class);
		checkMessage(ErrorReferential.getErrorByCode("APPCORE.2.1.1"), msg);
	}

	/** 
	 * Testing the connection to an unknown HTTP address not using the API prefix
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testUnknownPage() throws Exception{
		initSettings();

		response=HttpCommunication.getInstance().sendGet(Common.URL+"/toto");
		assertEquals("", response);

		response=HttpCommunication.getInstance().sendGet(Common.URL+"/toto/");
		assertEquals("", response);
	}
}
