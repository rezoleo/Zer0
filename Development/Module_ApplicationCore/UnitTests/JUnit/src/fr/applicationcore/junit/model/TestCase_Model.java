package fr.applicationcore.junit.model;

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


import fr.applicationcore.junit.network.GateClient;
import fr.applicationcore.junit.network.LoginClient;
import fr.applicationcore.junit.network.ObjectDBClient;
import fr.applicationcore.junit.network.RolesClient;
import fr.applicationcore.Common;
import fr.applicationcore.error.Error;
import fr.applicationcore.object.ErrorMessage;

/* 
 * Class 	: TestCase_Model
 * Author(s): Zidmann
 * Function : This class defines the super class used by the different JUnit test cases 
 * Version  : 1.0.0 
 */
public class TestCase_Model extends fr.junittemplate.test.TestCase_Application_Model
{ 
	protected ObjectDBClient ws_objectdb_client = new ObjectDBClient();
	protected GateClient 	 gate_client 		= new GateClient();
	protected LoginClient 	 login_client 		= new LoginClient();
	protected RolesClient 	 role_client 		= new RolesClient();

	protected String application = "Applicationcore_Testing";
	protected String version     = "1.0.0";

	protected String keyStorePath  	  = "/opt/centrale-datacore/Development/Module_ApplicationCore/UnitTests/NodeJS/certificates/keystore.jks";
	protected String keyStorePassword = "password";

	//Function to check if the error message has the expected properties
	protected void checkBasicMessage(Error err, ErrorMessage msg){
		msg.setStatus(404);
		super.checkMessage(null, null, err, msg);
	}
	protected void checkMessage(Error err, ErrorMessage msg){
		super.checkMessage(application, version, err, msg);
	}

	//Function to prepare the environment before a test
	protected void initSettings() throws Exception{
		ws_objectdb_client.setURL(Common.URL);
		ws_objectdb_client.setProxyParameters(Common.ProxyAdress, Common.ProxyPort);
		ws_objectdb_client.setKeystoreParameters(keyStorePath, keyStorePassword);

		gate_client.setURL(Common.URL);
		gate_client.setProxyParameters(Common.ProxyAdress, Common.ProxyPort);
		gate_client.setKeystoreParameters(keyStorePath, keyStorePassword);

		login_client.setURL(Common.URL);
		login_client.setProxyParameters(Common.ProxyAdress, Common.ProxyPort);
		login_client.setKeystoreParameters(keyStorePath, keyStorePassword);

		role_client.setURL(Common.URL);
		role_client.setProxyParameters(Common.ProxyAdress, Common.ProxyPort);
		role_client.setKeystoreParameters(keyStorePath, keyStorePassword);		
	}
}