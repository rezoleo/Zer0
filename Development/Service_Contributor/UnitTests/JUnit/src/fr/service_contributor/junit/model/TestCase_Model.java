package fr.service_contributor.junit.model;

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


import java.util.Vector;

import fr.service_contributor.Common;
import fr.service_contributor.network.ContributorClient;
import fr.service_contributor.object.Contributor;
import fr.service_contributor.junit.token.TokenReferential;
import fr.webservicecore.error.APIException;
import fr.webservicecore.error.ErrorMessage;
import fr.webservicecore.error.Error;

/**
 * Super class which will be extended by the different JUnit test cases
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_Model extends fr.junittemplate.test.TestCase_WebService_Model
{ 
	protected ContributorClient ws_client  = new ContributorClient();
	protected TokenReferential 	token_list = new TokenReferential();

	protected String service = "Service_Contributor";
	protected String version = "1.0.0";

	protected String keyStorePath  	  = "/opt/centrale-datacore/Development/Service_Contributor/NodeJS/Service/contributor/certificates/keystore.jks";
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
		ws_client.setToken(token_list.getToken("token_contributor"));
		ws_client.setURL(Common.URL);
		ws_client.setProxyParameters(Common.ProxyAdress, Common.ProxyPort);
		ws_client.setKeystoreParameters(keyStorePath, keyStorePassword);
		new fr.service_contributor.error.ErrorReferential();
	}

	/**
	 * Get the list of cards and count them to check if the quantity is the one expected
	 * @param number The quantity of cards expected
	 * @throws APIException Exception returned by the service
	 */
	protected void checkContributorQuantity(int number) throws APIException{
		Vector<Contributor> contributorList=ws_client.getAllContributor();
		assertEquals(number, contributorList.size());
	}
}