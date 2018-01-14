package fr.service_authentification.junit.model;

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


import fr.service_authentification.Common;
import fr.service_authentification.junit.token.TokenReferential;
import fr.service_authentification.network.AuthentificationClient;
import fr.webservicecore.error.Error;
import fr.webservicecore.error.ErrorMessage;

/**
 * Super class which will be extended by the different JUnit test cases
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_Model extends fr.junittemplate.test.TestCase_WebService_Model
{ 
	protected AuthentificationClient ws_client 	= new AuthentificationClient();
	protected TokenReferential 		 token_list = new TokenReferential();

	protected String service = "Service_Authentification";
	protected String version = "1.0.0";

	protected String keyStorePath  	  = "/opt/centrale-datacore/Development/Service_Authentification/NodeJS/Service/authentification/certificates/keystore.jks";
	protected String keyStorePassword = "password";

	/**
	 * Check if the error message has the expected properties
	 * @param err Error object
	 * @param msg Message expected in the error object
	 */
	protected void checkMessage(Error err, ErrorMessage msg){
		super.checkMessage(service, version, err, msg);
	}

	/**
	 * Prepare the environment before a test
	 * @throws Exception Exception returned by the system
	 */
	@SuppressWarnings("static-access")
	protected void initSettings() throws Exception{
		ws_client.setToken(token_list.getToken("token_authentification"));
		ws_client.setURL(Common.URL);
		ws_client.setProxyParameters(Common.ProxyAdress, Common.ProxyPort);
		ws_client.setKeystoreParameters(keyStorePath, keyStorePassword);
		new fr.service_authentification.error.ErrorReferential();
	}
}