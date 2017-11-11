package fr.applicationcore.network;

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


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Vector;

import org.apache.http.NameValuePair;

import fr.applicationcore.Common;
import fr.applicationcore.network.HttpMethod;
import fr.applicationcore.error.APIException;
import fr.applicationcore.error.ErrorMessage;
import fr.applicationcore.object.APIObject;
import fr.core.network.HttpCommunication;

/**
 * Client to extract data from a JSON file coming from a server running with ApplicationClient module
 * <p>
 * This class uses directly HttpRequest class and must be extended to define an application client client since it is an abstract class
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public abstract class ApplicationClient
{
	/**
	 * Uniqu instance of HttpCommunication to send HTTP requests to the server
	 */
	private HttpCommunication	communication = HttpCommunication.getInstance();

	/**
	 * URL of an application to connect
	 */
	private String 				URL   		  = Common.URL;

	/**
	 * Gson instance to convert the server responses from JSON format to object 
	 */
	private Gson 	 			gson  		  = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

	/**
	 * Constructor ApplicationClient
	 */
	public ApplicationClient(){
		
	}

	/**
	 * Constructor ApplicationClient
	 * @param URL URL of an application to connect
	 */
	public ApplicationClient(String URL){
		this.URL = URL;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Get nothing from the server JSON response (excepted an error message)
	 * @param method The HTTP method chosen to interact with the server
	 * @param apiClass The class definition of APIObject that are wanted. It must implements APIObject
	 * @param url The URL address of the server (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @param file The pointer which defines the file to send (used for POSTfile HTTP method)
	 * @throws APIException Exception containing the error message coming from a NodeJS server or from one client function or the system
	 */
	protected void requestZero(HttpMethod method, Class<?> apiClass, String url, List<NameValuePair> urlParameters, File file) throws APIException{
		String response = null;
    	try{
    		response=this.request(method, url, urlParameters, file);
    		if(response==null || !response.equals("")){
    			throw (new Exception());
    		}
    		return;
        }
        catch (Exception e){
        	APIException objectDBExpct = new APIException();
        	objectDBExpct.setStackTrace(e.getStackTrace());

        	ErrorMessage msg = null;
        	try{ msg = gson.fromJson(response, ErrorMessage.class);}
        	catch(Exception eBis){}

        	objectDBExpct.setMsg(msg);
            throw objectDBExpct;
        }
	}

	/**
	 * Get one single element from the server JSON response
	 * @param method The HTTP method chosen to interact with the server
	 * @param apiClass The class definition of APIObject that are wanted. It must implements APIObject
	 * @param url The URL address of the server (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @param file The pointer which defines the file to send (used for POSTfile HTTP method)
	 * @return An APIObject object
	 * @throws APIException Exception containing the error message coming from a NodeJS server or from one client function or the system
	 */
	protected APIObject requestOne(HttpMethod method, Class<?> apiClass, String url, List<NameValuePair> urlParameters, File file) throws APIException{
		String response = null;
    	try{
    		response=this.request(method, url, urlParameters, file);
    		APIObject apiObject = (APIObject)gson.fromJson(response, apiClass);
    		if(apiObject.isEmpty()){
    			throw (new Exception());
    		}
            return apiObject;
        }
        catch (Exception e){
        	APIException objectDBExpct = new APIException();
        	objectDBExpct.setStackTrace(e.getStackTrace());
        	
        	ErrorMessage msg = null;
        	try{ msg = gson.fromJson(response, ErrorMessage.class);}
        	catch(Exception eBis){}

        	objectDBExpct.setMsg(msg);
            throw objectDBExpct;
        }
	}

	/**
	 * Get several elements from the server JSON response
	 * @param method The HTTP method chosen to interact with the server
	 * @param apiClass The class definition of APIObject that are wanted. It must implements APIObject
	 * @param url The URL address of the server (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @param file The pointer which defines the file to send (used for POSTfile HTTP method)
	 * @return An array of APIObject
	 * @throws APIException Exception containing the error message coming from a NodeJS server or from one client function or the system
	 */
	protected Vector<APIObject> requestSeveral(HttpMethod method, Class<?> apiClass, String url, List<NameValuePair> urlParameters, File file) throws APIException{
		String response = null;
    	try{
    		Vector<APIObject> apiObjectList = new Vector<APIObject>();
    		response=this.request(method, url, urlParameters, file);        	
    		InputStream is 		= new ByteArrayInputStream(response.getBytes());
    		JsonReader  reader 	= new JsonReader(new InputStreamReader(is, "UTF-8"));
            
    		reader.beginArray();
            while (reader.hasNext()){
            	APIObject apiObject = gson.fromJson(reader, apiClass);
                apiObjectList.add(apiObject);
            }
            reader.endArray();
            reader.close();
            return apiObjectList;
        }
        catch (Exception e){
        	APIException objectDBExpct = new APIException();
        	objectDBExpct.setStackTrace(e.getStackTrace());
        	
        	ErrorMessage msg = null;
        	try{ msg = gson.fromJson(response, ErrorMessage.class);}
        	catch(Exception eBis){}

        	objectDBExpct.setMsg(msg);
            throw objectDBExpct;
        }
	}

	/**
	 * Auxiliary function to make an HTTP request
	 * @param method The HTTP method chosen to interact with the server
	 * @param url The URL address of the server (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @param file The pointer which defines the file to send (used for POSTfile HTTP method)
	 * @return The string returned by the server
	 * @throws Exception Exception returned by the system
	 */
	private String request(HttpMethod method, String url, List<NameValuePair> urlParameters, File file) throws Exception{
		switch(method){
			case GET:
				return HttpCommunication.getInstance().sendGet(url);
			case POST:
				return HttpCommunication.getInstance().sendPost(url, urlParameters);
			case POSTfile:
				return HttpCommunication.getInstance().sendPost(url, file);
			case PUT:
				return HttpCommunication.getInstance().sendPut(url, urlParameters);
			case DELETE:
				return HttpCommunication.getInstance().sendDelete(url);
			default :
				break;
		}
		return null;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getUserAgent(){
		return this.communication.getUserAgent();
	}
	
	public void setUserAgent(String userAgent){
		this.communication.setUserAgent(userAgent);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Get the proxy address used by the system
	 * @return Host address defined in the proxy settings 
	 */
	public String getProxyAddress(){
		return this.communication.getProxyAddress();
	}

	/**
	 * Get the proxy port used by the system
	 * @return Port defined in the proxy settings 
	 */
	public String getProxyPort(){
		return this.communication.getProxyPort();
	}

	/**
	 * Set the new host address in the proxy settings of the system
	 * @param ProxyAddress The new host address to set
	 * @throws Exception Exception returned by the system
	 */
	public void setProxyAddress(String ProxyAddress) throws Exception{
		this.communication.setProxyAddress(ProxyAddress);
	}

	/**
	 * Set the new port in the proxy settings of the system
	 * @param ProxyPort The new port to set
	 * @throws Exception Exception returned by the system
	 */
	public void setProxyPort(int ProxyPort) throws Exception{
		this.communication.setProxyPort(ProxyPort);
	}

	/**
	 * Set the new host address and port in the proxy settings of the system
	 * @param ProxyAddress The new host address to set
	 * @param ProxyPort The new port to set
	 * @throws Exception Exception returned by the system
	 */
	public void setProxyParameters(String ProxyAddress, int ProxyPort) throws Exception{
		this.communication.setProxyParameters(ProxyAddress, ProxyPort);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getURL(){
		return this.URL;
	}
	public void setURL(String URL){
		this.URL = URL;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getKeystorePath(){
		return this.communication.getKeystorePath();
	}
	public String getKeystorePassword(){
		return this.communication.getKeystorePassword();
	}
	public void setKeystorePath(String keyStorePath) throws Exception{
		this.communication.setKeystorePath(keyStorePath);
	}
	public void setKeystorePassword(String keyStorePassword) throws Exception{
		this.communication.setKeystorePassword(keyStorePassword);
	}
	public void setKeystoreParameters(String keyStorePath, String keyStorePassword) throws Exception{
		this.communication.setKeystoreParameters(keyStorePath, keyStorePassword);
	}
}