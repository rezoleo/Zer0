package fr.webservicecore.junit.model;

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


import fr.core.network.HttpCommunication;
import fr.webservicecore.Common;
import fr.webservicecore.error.Error;
import fr.webservicecore.junit.network.ObjectDBClient;
import fr.webservicecore.junit.token.TokenReferential;
import fr.webservicecore.object.ErrorMessage;

/* 
 * Class 	: TestCase_Model
 * Author(s): Zidmann
 * Function : This class defines the super class used by the different JUnit test cases 
 * Version  : 1.0.0 
 */
public class TestCase_Model extends fr.junittemplate.test.TestCase_WebService_Model
{ 
	protected ObjectDBClient 	ws_client 	= new ObjectDBClient();
	protected TokenReferential	token_list	= new TokenReferential();

	protected String service = "Webservicecore_Testing";
	protected String version = "1.0.0";

	protected String keyStorePath  	  = "/opt/centrale-datacore/Development/Module_WebServiceCore/UnitTests/NodeJS/certificates/keystore.jks";
	protected String keyStorePassword = "password";

	//Function to check if the error message has the expected properties
	protected void checkBasicMessage(Error err, ErrorMessage msg){
		msg.setStatus(404);
		super.checkMessage(null, null, err, msg);
	}
	protected void checkMessage(Error err, ErrorMessage msg){
		super.checkMessage(service, version, err, msg);
	}

	//Function to prepare the environment before a test
	protected void initSettings() throws Exception{
		ws_client.setURL(Common.URL);
		ws_client.setProxyParameters(Common.ProxyAdress, Common.ProxyPort);
		ws_client.setKeystoreParameters(keyStorePath, keyStorePassword);

		//Reset the traces used for flood protection
		HttpCommunication.getInstance().sendGet(Common.URL+"/api/floodtest/reset");
	}
}