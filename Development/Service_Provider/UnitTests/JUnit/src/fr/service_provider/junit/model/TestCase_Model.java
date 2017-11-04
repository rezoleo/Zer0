package fr.service_provider.junit.model;

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

import fr.service_alert.network.AlertClient;
import fr.service_alert.object.Alert;
import fr.service_authentification.network.AuthentificationClient;
import fr.service_authentification.tool.Hash;
import fr.service_card.Common;
import fr.service_card.network.CardClient;
import fr.service_card.object.Card;
import fr.service_contributor.network.ContributorClient;
import fr.service_contributor.object.Contributor;
import fr.service_group.network.GroupClient;
import fr.service_group.object.Group;
import fr.service_people.network.PeopleClient;
import fr.service_people.object.Person;
import fr.service_picture.network.PictureClient;
import fr.service_provider.junit.token.TokenReferential;
import fr.webservicecore.object.APIException;
import fr.webservicecore.object.ErrorMessage;
import fr.webservicecore.error.Error;
import fr.webservicecore.error.ErrorReferential;

/* 
 * Class 	: TestCase_Model
 * Author(s): Zidmann
 * Function : This class contains the super class used by the different JUnit test cases 
 * Version  : 1.0.0 
 */
public class TestCase_Model extends fr.junittemplate.test.TestCase_WebService_Model
{ 
	protected static String URL   		 = "https://localhost:8301";

	protected AlertClient 				ws_client_alert		  = new AlertClient();
	protected AuthentificationClient 	ws_client_auth	 	  = new AuthentificationClient();
	protected CardClient 				ws_client_card		  = new CardClient();
	protected ContributorClient 		ws_client_contributor = new ContributorClient();
	protected GroupClient 				ws_client_group	  	  = new GroupClient();
	protected PeopleClient 				ws_client_people  	  = new PeopleClient();
	protected PictureClient 			ws_client_picture 	  = new PictureClient();

	protected AuthentificationClient 	ws_client_auth_proxy  		= new AuthentificationClient();
	protected CardClient 				ws_client_card_proxy		= new CardClient();
	protected ContributorClient 		ws_client_contributor_proxy = new ContributorClient();
	protected GroupClient 				ws_client_group_proxy		= new GroupClient();
	protected PeopleClient 				ws_client_people_proxy  	= new PeopleClient();
	protected PictureClient 			ws_client_picture_proxy 	= new PictureClient();

	protected String[]					forbiddenLetters = {"é", "à", "&", "+", "=", ":", "("};

	protected TokenReferential 	token_list = new TokenReferential();

	protected String service = "Service_Provider";
	protected String version = "1.0.0";

	protected String path	   = "/opt/centrale-datacore/Development/Service_Picture/UnitTests/JUnit/picture/";
	protected String directory = "JunitTests";

	protected String keyStorePath  	  = "/opt/centrale-datacore/Development/Service_Provider/NodeJS/Service/provider/certificates/keystore.jks";
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
	@SuppressWarnings("static-access")
	protected void initSettings() throws Exception{
		ws_client_alert.setToken(token_list.getToken("token_alert"));
		ws_client_alert.setURL(fr.service_alert.Common.URL);
		ws_client_alert.setProxyParameters(fr.service_alert.Common.ProxyAdress, fr.service_alert.Common.ProxyPort);
		ws_client_alert.setKeystoreParameters(keyStorePath, keyStorePassword);

		ws_client_auth.setToken(token_list.getToken("token_authentification"));
		ws_client_auth.setURL(fr.service_authentification.Common.URL);
		ws_client_auth.setProxyParameters(fr.service_authentification.Common.ProxyAdress, fr.service_authentification.Common.ProxyPort);
		ws_client_auth.setKeystoreParameters(keyStorePath, keyStorePassword);

		ws_client_auth_proxy.setToken(token_list.getToken("token_provider"));
		ws_client_auth_proxy.setURL(URL);
		ws_client_auth_proxy.setProxyParameters(Common.ProxyAdress, Common.ProxyPort);
		ws_client_auth_proxy.setKeystoreParameters(keyStorePath, keyStorePassword);

		ws_client_card.setToken(token_list.getToken("token_card"));
		ws_client_card.setURL(fr.service_card.Common.URL);
		ws_client_card.setProxyParameters(fr.service_card.Common.ProxyAdress, fr.service_card.Common.ProxyPort);
		ws_client_card.setKeystoreParameters(keyStorePath, keyStorePassword);

		ws_client_card_proxy.setToken(token_list.getToken("token_provider"));
		ws_client_card_proxy.setURL(URL);
		ws_client_card_proxy.setProxyParameters(Common.ProxyAdress, Common.ProxyPort);
		ws_client_card_proxy.setKeystoreParameters(keyStorePath, keyStorePassword);

		ws_client_contributor.setToken(token_list.getToken("token_contributor"));
		ws_client_contributor.setURL(fr.service_contributor.Common.URL);
		ws_client_contributor.setProxyParameters(fr.service_contributor.Common.ProxyAdress, fr.service_contributor.Common.ProxyPort);
		ws_client_contributor.setKeystoreParameters(keyStorePath, keyStorePassword);

		ws_client_contributor_proxy.setToken(token_list.getToken("token_provider"));
		ws_client_contributor_proxy.setURL(URL);
		ws_client_contributor_proxy.setProxyParameters(Common.ProxyAdress, Common.ProxyPort);
		ws_client_contributor_proxy.setKeystoreParameters(keyStorePath, keyStorePassword);

		ws_client_group.setToken(token_list.getToken("token_group"));
		ws_client_group.setURL(fr.service_group.Common.URL);
		ws_client_group.setProxyParameters(fr.service_group.Common.ProxyAdress, fr.service_group.Common.ProxyPort);
		ws_client_group.setKeystoreParameters(keyStorePath, keyStorePassword);

		ws_client_group_proxy.setToken(token_list.getToken("token_provider"));
		ws_client_group_proxy.setURL(URL);
		ws_client_group_proxy.setProxyParameters(Common.ProxyAdress, Common.ProxyPort);
		ws_client_group_proxy.setKeystoreParameters(keyStorePath, keyStorePassword);

		ws_client_people.setToken(token_list.getToken("token_people"));
		ws_client_people.setURL(fr.service_people.Common.URL);
		ws_client_people.setProxyParameters(fr.service_people.Common.ProxyAdress, fr.service_people.Common.ProxyPort);
		ws_client_people.setKeystoreParameters(keyStorePath, keyStorePassword);

		ws_client_people_proxy.setToken(token_list.getToken("token_provider"));
		ws_client_people_proxy.setURL(URL);
		ws_client_people_proxy.setProxyParameters(Common.ProxyAdress, Common.ProxyPort);
		ws_client_people_proxy.setKeystoreParameters(keyStorePath, keyStorePassword);

		ws_client_picture.setToken(token_list.getToken("token_picture"));
		ws_client_picture.setURL(fr.service_picture.Common.URL);
		ws_client_picture.setProxyParameters(fr.service_picture.Common.ProxyAdress, fr.service_picture.Common.ProxyPort);
		ws_client_picture.setKeystoreParameters(keyStorePath, keyStorePassword);

		ws_client_picture_proxy.setToken(token_list.getToken("token_provider"));
		ws_client_picture_proxy.setURL(URL);
		ws_client_picture_proxy.setProxyParameters(Common.ProxyAdress, Common.ProxyPort);
		ws_client_picture_proxy.setKeystoreParameters(keyStorePath, keyStorePassword);

		new fr.service_alert.error.ErrorReferential();
		new fr.service_authentification.error.ErrorReferential();
		new fr.service_card.error.ErrorReferential();
		new fr.service_contributor.error.ErrorReferential();
		new fr.service_group.error.ErrorReferential();
		new fr.service_people.error.ErrorReferential();
		new fr.service_picture.error.ErrorReferential();

		ErrorReferential.addError("PROVIDER.1.1.1", "Le jeton transmis ne contient pas les droits pour accéder au répertoire distant");
		ErrorReferential.addError("PROVIDER.A.1.1", "Le connecteur vers le service des authentifiants a généré une uri avec au moins un caractère interdit");
		ErrorReferential.addError("PROVIDER.A.2.1", "Le connecteur vers le service des authentifiants a généré une uri avec au moins un caractère interdit");
		ErrorReferential.addError("PROVIDER.B.1.1", "Le connecteur vers le service des cartes a généré une uri avec au moins un caractère interdit");
		ErrorReferential.addError("PROVIDER.B.2.1", "Le connecteur vers le service des cartes a généré une uri avec au moins un caractère interdit");
		ErrorReferential.addError("PROVIDER.C.1.1", "Le connecteur vers le service des cotisants a généré une uri avec au moins un caractère interdit");
		ErrorReferential.addError("PROVIDER.C.2.1", "Le connecteur vers le service des cotisants a généré une uri avec au moins un caractère interdit");
		ErrorReferential.addError("PROVIDER.D.1.1", "Le connecteur vers le service des groupes a généré une uri avec au moins un caractère interdit");
		ErrorReferential.addError("PROVIDER.D.2.1", "Le connecteur vers le service des groupes a généré une uri avec au moins un caractère interdit");
		ErrorReferential.addError("PROVIDER.D.3.1", "Le connecteur vers le service des groupes a généré une uri avec au moins un caractère interdit");
		ErrorReferential.addError("PROVIDER.E.1.1", "Le connecteur vers le service des personnes a généré une uri avec au moins un caractère interdit");
		ErrorReferential.addError("PROVIDER.E.2.1", "Le connecteur vers le service des personnes a généré une uri avec au moins un caractère interdit");
		ErrorReferential.addError("PROVIDER.E.3.1", "Le connecteur vers le service des personnes a généré une uri avec au moins un caractère interdit");
		ErrorReferential.addError("PROVIDER.F.1.1", "Le connecteur vers le service des images a généré une uri avec au moins un caractère interdit");
		ErrorReferential.addError("PROVIDER.F.2.1", "Le connecteur vers le service des images a généré une uri avec au moins un caractère interdit");
	}

	//Function to get the list of alert and count them
	protected int countAlertQuantity() throws APIException{
		Vector<Alert> alertList=ws_client_alert.getAllAlert(null);
		return alertList.size();
	}
	protected void checkAlertQuantity(int number) throws APIException{
		Vector<Alert> alertList=ws_client_alert.getAllAlert(null);
		assertEquals(number, alertList.size());
	}

	//Function to get the list of card and count them
	protected void checkCardQuantity(int number) throws APIException{
		Vector<Card> cardList=ws_client_card.getAllCard();
		assertEquals(number, cardList.size());
	}

	//Function to get the list of contributor and count them
	protected void checkContributorQuantity(int number) throws APIException{
		Vector<Contributor> contributorList=ws_client_contributor.getAllContributor();
		assertEquals(number, contributorList.size());
	}

	//Function to get the list of group and count them
	protected void checkGroupQuantity(int number) throws APIException{
		Vector<Group> groupList=ws_client_group.getAllGroup();
		assertEquals(number, groupList.size());
	}

	//Function to get the list of person and count them
	protected void checkPeopleQuantity(int number) throws APIException{
		Vector<Person> peopleList=ws_client_people.getAllPeople();
		assertEquals(number, peopleList.size());
	}

	public static String hashPassword(String str, String salt) throws Exception{
		return Hash.sha512(Hash.sha512("passwordOne", salt), "");
	}
}