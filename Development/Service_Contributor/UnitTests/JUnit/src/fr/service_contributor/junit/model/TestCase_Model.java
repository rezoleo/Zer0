package fr.service_contributor.junit.model;

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


import java.util.Vector;

import fr.service_contributor.Common;
import fr.service_contributor.network.ContributorClient;
import fr.service_contributor.object.Contributor;
import fr.service_contributor.junit.token.TokenReferential;
import fr.webservicecore.object.APIException;
import fr.webservicecore.object.ErrorMessage;
import fr.webservicecore.error.Error;

/* 
 * Class 	: TestCase_Model
 * Author(s): Zidmann
 * Function : This class contains the super class used by the different JUnit test cases 
 * Version  : 1.0.0 
 */
public class TestCase_Model extends fr.junittemplate.test.TestCase_WebService_Model
{ 
	protected ContributorClient ws_client  = new ContributorClient();
	protected TokenReferential 	token_list = new TokenReferential();

	protected String service = "Service_Contributor";
	protected String version = "1.0.0";

	protected String keyStorePath  	  = "/opt/centrale-datacore/Development/Service_Contributor/NodeJS/Service/contributor/certificates/keystore.jks";
	protected String keyStorePassword = "password";

	//Function to check if the error message has the expected properties
	protected void checkMessage(Error err, ErrorMessage msg){
		super.checkMessage(service, version, err, msg);
	}

	//Function to prepare the environment before a test
	@SuppressWarnings("static-access")
	protected void initSettings() throws Exception{
		ws_client.setToken(token_list.getToken("token_contributor"));
		ws_client.setURL(Common.URL);
		ws_client.setProxyParameters(Common.ProxyAdress, Common.ProxyPort);
		ws_client.setKeystoreParameters(keyStorePath, keyStorePassword);
		new fr.service_contributor.error.ErrorReferential();
	}

	//Function to get the list of contributor and count them
	protected void checkContributorQuantity(int number) throws APIException{
		Vector<Contributor> contributorList=ws_client.getAllContributor();
		assertEquals(number, contributorList.size());
	}
}