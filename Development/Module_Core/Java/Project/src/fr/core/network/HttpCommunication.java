package fr.core.network;

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


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

/* 
 * Class 	: HttpCommunication
 * Author(s): Zidmann
 * Function : This class contains basic functions to communicate with HTTP protocol to a server
 * Version  : 1.0.0
 * Note     : This class implements a singleton pattern
 */
public class HttpCommunication 
{
	private static volatile HttpCommunication instance 	= null;

	protected String 	 defaultUserAgent = "JavaApplication/1.0.0";
	protected String 	 userAgent 		  = null;	 

	protected HttpClient client = null;

	// Attributes for using HTTPS with SSL
	protected String keyStorePath  	  = null;
	protected String keyStorePassword = null;

	protected HttpCommunication(){
		this.userAgent = this.defaultUserAgent;

		try{
			this.regenerateClient();
		}
		catch(Exception e){
			
		}
	}

	public static HttpCommunication getInstance(){
		if(HttpCommunication.instance == null){
			synchronized(HttpCommunication.class){
				if(HttpCommunication.instance == null){
					HttpCommunication.instance = new HttpCommunication();
				}
			}
		}
		return HttpCommunication.instance;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Functions for user agent settings
	public String getUserAgent(){
		return this.userAgent;
	}

	public void setUserAgent(String userAgent){
		if(userAgent==null || userAgent.equals("")){
			this.userAgent = this.defaultUserAgent;
		}
		else{
			this.userAgent = userAgent;
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Functions for SSL settings
	public String getKeystorePath(){
		return this.keyStorePath;
	}
	public String getKeystorePassword(){
		return this.keyStorePassword;
	}
	public void setKeystorePath(String keyStorePath) throws Exception{
		this.keyStorePath = keyStorePath;
		this.regenerateClient();  
	}
	public void setKeystorePassword(String keyStorePassword) throws Exception{
		this.keyStorePassword = keyStorePassword;
		this.regenerateClient(); 
	}
	public void setKeystoreParameters(String keyStorePath, String keyStorePassword) throws Exception{
		this.keyStorePath 	  = keyStorePath;
		this.keyStorePassword = keyStorePassword;
		this.regenerateClient();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Functions for proxy parameters
	public String getProxyAddress(){
		Properties systemProperties = System.getProperties();
		if(systemProperties!=null){
			return systemProperties.getProperty("http.proxyHost");
		}
		return null;
	}
	public String getProxyPort(){
		Properties systemProperties = System.getProperties();
		if(systemProperties!=null){
			return systemProperties.getProperty("http.proxyPort");
		}
		return null;
	}
	public void setProxyAddress(String ProxyAddress){
		Properties systemProperties = System.getProperties();
		if(systemProperties==null){
			return;
		}
		//Case where no proxy address is set
		if(ProxyAddress==null || ProxyAddress.equals("")){
			systemProperties.remove("http.proxyHost");
			return;
		}
		systemProperties.setProperty("http.proxyHost", ProxyAddress);		
	}
	public void setProxyPort(int ProxyPort){
		Properties systemProperties = System.getProperties();
		if(systemProperties==null){
			return;
		}
		//Case where no proxy port is set
		if(ProxyPort<=0){
			systemProperties.remove("http.proxyPort");
			return;
		}
		systemProperties.setProperty("http.proxyPort", Integer.toString(ProxyPort));
	}
	public void setProxyParameters(String ProxyAddress, int ProxyPort){
		this.setProxyAddress(ProxyAddress);
		this.setProxyPort(ProxyPort);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Function to renew the HTTP client; it removes all the information of previous connections
	public void regenerateClient() throws Exception{
		this.client = null;
		if(this.keyStorePath!=null && 
	       this.keyStorePassword!=null){
			try{
				KeyStore 		trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
				FileInputStream instream   = new FileInputStream(new File(this.keyStorePath));
				try{
					trustStore.load(instream, this.keyStorePassword.toCharArray());
				}
				finally{
					instream.close();
				}

				// Trust own CA and all self-signed certs
				SSLContext sslcontext = SSLContexts.custom()
				        						   .loadTrustMaterial(trustStore)
				        						   .build();
				// Allow TLSv1.2 protocol only
				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,
																				  new String[] { "TLSv1.2" },
																				  null,
																				  SSLConnectionSocketFactory.STRICT_HOSTNAME_VERIFIER);
				this.client = HttpClientBuilder.create().setSSLSocketFactory(sslsf).build();
			}
			catch(Exception e){
				throw e;
			}
		}
		else{
			this.client = HttpClientBuilder.create().build();
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Functions to send request (GET, POST, PUT, DELETE) to server
	// HTTP GET request
	public String sendGet(String url) throws Exception{
		return this.sendRequest(HttpMethod.GET, url, null, null);
 	}
	// HTTP POST request
	//Note : To add parameters, we use add(new BasicNameValuePair(<Key>, <Value>));
	public String sendPost(String url, List<NameValuePair> urlParameters) throws Exception{
		return this.sendRequest(HttpMethod.POST, url, urlParameters, null);	
	}
	// HTTP POST request
	//Note : This version is used to send a file to the server through a POST HTTP request
	public String sendPost(String url, File file) throws Exception{
		return this.sendRequest(HttpMethod.POSTfile, url, null, file);
	}
	// HTTP PUT request
	public String sendPut(String url, List<NameValuePair> urlParameters) throws Exception{
		return this.sendRequest(HttpMethod.PUT, url, urlParameters, null);	
	}
	// HTTP DELETE request
	public String sendDelete(String url) throws Exception{	
		return this.sendRequest(HttpMethod.DELETE, url, null, null);
	}

	// Auxiliary function to define an HTTP request
	protected synchronized String sendRequest(HttpMethod method,
											  String url, List<NameValuePair> urlParameters, File file) throws Exception{
		try{
			StringBuffer result 	= new StringBuffer();
			HttpUriRequest request 	= null;
			if((method==HttpMethod.POST || method==HttpMethod.PUT) && urlParameters==null){
				urlParameters=new Vector<NameValuePair>();
			}
			//Definition of the HTTP request in function of the method
			switch(method){
				case GET:
					request = new HttpGet(url);			
					break;
				case POST:
					request = new HttpPost(url);
					((HttpPost)request).setEntity(new UrlEncodedFormEntity(urlParameters));
					break;
				case POSTfile:
					request = new HttpPost(url);
					FileEntity reqEntity = new FileEntity(file);
					reqEntity.setContentType("binary/octet-stream");
				    reqEntity.setChunked(true);
				    ((HttpPost)request).setEntity(reqEntity);
					break;
				case PUT:
					request = new HttpPut(url);
					((HttpPut)request).setEntity(new UrlEncodedFormEntity(urlParameters));
					break;
				case DELETE:
					request = new HttpDelete(url);
					break;
				default :
					return null;
			}
			request.addHeader("User-Agent", userAgent);
			HttpResponse response = this.client.execute(request);

			BufferedReader rd = new BufferedReader
									(new InputStreamReader
										 (response.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null){
				result.append(line);
			}			

			return result.toString();
		}
		catch(Exception e){
			throw e;
		}
	}
}