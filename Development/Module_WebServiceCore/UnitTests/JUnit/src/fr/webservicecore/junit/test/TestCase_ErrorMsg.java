package fr.webservicecore.junit.test;

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

import fr.core.network.HttpCommunication;
import fr.webservicecore.Common;
import fr.webservicecore.error.ErrorReferential;
import fr.webservicecore.junit.model.TestCase_Model;
import fr.webservicecore.junit.network.ObjectDBTokenClient;
import fr.webservicecore.junit.token.TokenReferential;
import fr.webservicecore.network.HttpMethod;
import fr.webservicecore.error.APIException;
import fr.webservicecore.error.ErrorMessage;

/**
 * This class contains the webService client JUnit tests to check the token management in a NodeJS server using the 'WebServiceCore' module
 * <p>
 * This Test Case supposes that you started the NodeJS server designed to test 'ObjectDBClient' and 'ObjectDBTokenClient' classes
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_ErrorMsg extends TestCase_Model
{
	protected ObjectDBTokenClient 	ws_client_sec = new ObjectDBTokenClient();
	protected Gson 					gson  	      = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
	protected String 				response  	  = null;

	@Test
	public void testErrorUnknownAPI() throws Exception{
		initSettings();

		ErrorMessage msg = null;
		response=HttpCommunication.getInstance().sendGet(Common.URL+"/api/");
		msg = gson.fromJson(response, ErrorMessage.class);
		checkMessage(ErrorReferential.getErrorByCode("WSCORE.2.1.1"), msg);

		response=HttpCommunication.getInstance().sendGet(Common.URL+"/api/nothing");
		msg = gson.fromJson(response, ErrorMessage.class);
		checkMessage(ErrorReferential.getErrorByCode("WSCORE.2.1.1"), msg);
	}
	@Test
	public void testErrorUnknownPage() throws Exception{
		initSettings();

		response=HttpCommunication.getInstance().sendGet(Common.URL);
		assertEquals("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\"><title>Error</title></head><body><pre>Cannot GET /</pre></body></html>", response);

		response=HttpCommunication.getInstance().sendGet(Common.URL+"/toto");
		assertEquals("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\"><title>Error</title></head><body><pre>Cannot GET /toto</pre></body></html>", response);

		response=HttpCommunication.getInstance().sendGet(Common.URL+"/toto/");
		assertEquals("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\"><title>Error</title></head><body><pre>Cannot GET /toto/</pre></body></html>", response);
	}
	@Test
	public void testErrorInvalidToken() throws Exception{
		testError("token_1011", "WSCORE.3.1.1");
	}
	@Test
	public void testErrorInvalidSourceIPToken() throws Exception{
		testError("token_1012", "WSCORE.3.1.2");
	}
	@Test
	public void testErrorInvalidSourceServiceNameToken() throws Exception{
		testError("token_1013", "WSCORE.3.1.3");
	}
	@Test
	public void testErrorInvalidDestinationServiceNameToken() throws Exception{
		testError("token_1014", "WSCORE.3.1.4");
	}
	/*
	@Test
	public void testErrorInvalidDateToken() throws Exception
	{
		testError("token_1015", "WSCORE.3.1.5");
	}
	*/
	@Test
	public void testErrorInvalidRulesToken() throws Exception{
		testError("token_1016", "WSCORE.3.1.6");
	}
	@Test
	public void testErrorNotRegisterToken() throws Exception{
		testError("token_1017", "WSCORE.3.1.7");
	}
	@Test
	public void testErrorNotErrorIncorrectAccessrightToken() throws Exception{
		testError("token_1018", "WSCORE.3.1.8");
	}

	protected void testError(String token_id, String error) throws Exception{
		initSettings();

		String token=TokenReferential.getToken(token_id);
		ws_client_sec.setToken(token);
		testErrorAuxi(HttpMethod.GET,	error);
		testErrorAuxi(HttpMethod.POST,	error);
		testErrorAuxi(HttpMethod.PUT,	error);
		testErrorAuxi(HttpMethod.DELETE,error);
	}
	protected void testErrorAuxi(HttpMethod method, String error) throws Exception{
		try{
			switch(method){
				case GET:
					ws_client_sec.getAllObjectDB();
					break;
				case POST:
					ws_client_sec.createObjectDB();
					break;
				case PUT:
					ws_client_sec.updateObjectDB();
					break;
				case DELETE:
					ws_client_sec.deleteObjectDB();
					break;
				default:
					break;
			}
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode(error), e.getMsg());
		}
		catch(Exception e){
			e.printStackTrace();
			assertNotNull(null);
		}
	}
}