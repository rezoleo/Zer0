package fr.applicationcore.network;

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
import fr.applicationcore.object.APIException;
import fr.applicationcore.object.APIObject;
import fr.applicationcore.object.ErrorMessage;

import fr.core.network.HttpCommunication;

/* 
 * Class 	: ApplicationClient
 * Author(s): Zidmann
 * Function : This class contains the basis of ApplicationClient client to extract data from JSON file coming from a server 
 * Version  : 1.0.0
 * Note		: This class uses directly HttpRequest class and must be extended to define a ApplicationClient client since it is an abstract class
 */
public abstract class ApplicationClient
{
	protected HttpCommunication communication = HttpCommunication.getInstance();
	protected String 			URL   		  = Common.URL;
	protected Gson 	 			gson  		  = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

	public ApplicationClient(){
		
	}
	public ApplicationClient(String URL){
		this.URL = URL;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Functions to interact with HTTP request with NodeJS server and extract information 
	//Note : apiClass must implement APIObject

	//Function to get no element in JSON file (excepted an error message)
	protected void requestZero(HttpMethod method, Class<?> apiClass,
							   String http_address, List<NameValuePair> urlParameters, File file) throws APIException{
		String response = null;
    	try{
    		response=this.request(method, http_address, urlParameters, file);
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

	//Function to get one single element in JSON file
	protected APIObject requestOne(HttpMethod method, Class<?> apiClass,
								   String http_address, List<NameValuePair> urlParameters, File file) throws APIException{
		String response = null;
    	try{
    		response=this.request(method, http_address, urlParameters, file);
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
	//Function to get several elements in JSON file with an array
	protected Vector<APIObject> requestSeveral(HttpMethod method, Class<?> apiClass,
											   String http_address, List<NameValuePair> urlParameters, File file) throws APIException{
		String response = null;
    	try{
    		Vector<APIObject> apiObjectList = new Vector<APIObject>();
    		response=this.request(method, http_address, urlParameters, file);        	
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
	protected String request(HttpMethod method,
						  	 String http_address, List<NameValuePair> urlParameters, File file) throws Exception{
		switch(method){
			case GET:
				return HttpCommunication.getInstance().sendGet(http_address);
			case POST:
				return HttpCommunication.getInstance().sendPost(http_address, urlParameters);
			case POSTfile:
				return HttpCommunication.getInstance().sendPost(http_address, file);
			case PUT:
				return HttpCommunication.getInstance().sendPut(http_address, urlParameters);
			case DELETE:
				return HttpCommunication.getInstance().sendDelete(http_address);
			default :
				break;
		}
		return null;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Functions for user agent settings
	public String getUserAgent(){
		return this.communication.getUserAgent();
	}
	
	public void setUserAgent(String userAgent){
		this.communication.setUserAgent(userAgent);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Functions for proxy settings
	public String getProxyAddress(){
		return this.communication.getProxyAddress();
	}
	public String getProxyPort(){
		return this.communication.getProxyPort();
	}
	public void setProxyAddress(String ProxyAddress){
		this.communication.setProxyAddress(ProxyAddress);
	}
	public void setProxyPort(int ProxyPort){
		this.communication.setProxyPort(ProxyPort);
	}
	public void setProxyParameters(String ProxyAddress, int ProxyPort){
		this.communication.setProxyParameters(ProxyAddress, ProxyPort);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Functions for URL settings	
	public String getURL(){
		return this.URL;
	}
	public void setURL(String URL){
		this.URL = URL;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Functions for SSL settings
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