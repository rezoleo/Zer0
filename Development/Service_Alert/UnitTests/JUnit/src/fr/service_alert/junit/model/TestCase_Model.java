package fr.service_alert.junit.model;

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

import fr.service_alert.Common;
import fr.service_alert.network.AlertClient;
import fr.service_alert.object.Alert;
import fr.service_alert.junit.token.TokenReferential;
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
	protected AlertClient 		ws_client  = new AlertClient();
	protected TokenReferential 	token_list = new TokenReferential();

	protected String service = "Service_Alert";
	protected String version = "1.0.0";

	protected String keyStorePath  	  = "/opt/centrale-datacore/Development/Service_Alert/NodeJS/Service/alert/certificates/keystore.jks";
	protected String keyStorePassword = "password";

	//Function to check if the error message has the expected properties
	protected void checkMessage(Error err, ErrorMessage msg){
		super.checkMessage(service, version, err, msg);
	}

	//Function to prepare the environment before a test
	@SuppressWarnings("static-access")
	protected void initSettings() throws Exception{
		ws_client.setToken(token_list.getToken("token_alert"));
		ws_client.setURL(Common.URL);
		ws_client.setProxyParameters(Common.ProxyAdress, Common.ProxyPort);
		ws_client.setKeystoreParameters(keyStorePath, keyStorePassword);
		new fr.service_alert.error.ErrorReferential();
	}

	//Function to get the list of alerts and count them
	protected int countAlertQuantity(String minimumlevel) throws APIException{
		Vector<Alert> alertList=ws_client.getAllAlert(minimumlevel);
		return alertList.size();
	}
	protected void checkAlertQuantity(int count_info, int count_warning, int count_error, int count_critical, int count) throws APIException{	
		if(count<0){
			count=0;
		}
		this.checkAlertQuantityAuxi(null,       count+count_info);
		this.checkAlertQuantityAuxi("INFO",     count+count_info);

		count=decrement(count);
		this.checkAlertQuantityAuxi("WARNING",  count+count_warning);

		count=decrement(count);
		this.checkAlertQuantityAuxi("ERROR",    count+count_error);

		count=decrement(count);
		this.checkAlertQuantityAuxi("CRITICAL", count+count_critical);
	}
	protected void checkAlertQuantityAuxi(String minimumlevel, int number) throws APIException{
		assertEquals(number, this.countAlertQuantity(minimumlevel));
	}
	
	//Auxilary function
	int decrement(int i){
		if(i<=0){
			return 0;
		}
		return (i-1);
	}
}