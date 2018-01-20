package fr.service_picture.junit.model;

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


import java.io.BufferedReader;
import java.io.InputStreamReader;

import fr.service_picture.Common;
import fr.service_picture.junit.token.TokenReferential;
import fr.service_picture.network.PictureClient;
import fr.webservicecore.error.Error;
import fr.webservicecore.error.ErrorMessage;

/**
 * Super class which will be extended by the different JUnit test cases
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_Model extends fr.junittemplate.test.TestCase_WebService_Model
{
	protected PictureClient 	ws_client  = new PictureClient();
	protected TokenReferential 	token_list = new TokenReferential();

	protected String service = "Service_Picture";
	protected String version = "1.0.0";

	protected String path	   = "/opt/centrale-datacore/Development/Service_Picture/UnitTests/JUnit/picture/";
	protected String directory = "JunitTests";

	protected String keyStorePath  	  = "/opt/centrale-datacore/Development/Service_Picture/NodeJS/Service/picture/certificates/keystore.jks";
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
		ws_client.setToken(token_list.getToken("token_picture"));
		ws_client.setURL(Common.URL);
		ws_client.setProxyParameters(Common.ProxyAdress, Common.ProxyPort);
		ws_client.setKeystoreParameters(keyStorePath, keyStorePassword);
		new fr.service_picture.error.ErrorReferential();

		// Count the temporary files of NodeJS in the /tmp directory
		assertEquals(executeCommand("ls /tmp/ | grep upload").indexOf("upload_"), -1);
	}

	/**
	 * Function to extract the filename of a file in its path
	 * @param path Path of a file
	 * @return Filename of the file
	 */
	protected String extractFileName(String path){
		if(path==null){
			return null;
		}
		return path.substring(path.indexOf("/")+1);
	}

	/**
	 * Function to execute bash command
	 * @param command Bash command
	 * @return Output of the bash command
	 */
	protected static String executeCommand(String command){
		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";			
			while ((line = reader.readLine())!= null){
				output.append(line + "\n");
			}

		} catch (Exception e){
			e.printStackTrace();
		}

		return output.toString();

	}
}