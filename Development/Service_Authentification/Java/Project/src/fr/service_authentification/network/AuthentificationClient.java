package fr.service_authentification.network;

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


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import fr.service_authentification.object.Authentification;
import fr.webservicecore.error.APIException;
import fr.webservicecore.network.HttpMethod;
import fr.webservicecore.network.WebServiceClient;
import fr.webservicecore.object.APIObject;
import fr.webservicecore.toolbox.AttributesTool;

/** 
 * Client to manage accesses in the Authentification service
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class AuthentificationClient extends WebServiceClient
{
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Authentification FUNCTIONS
    // HTTP GET requests
	/**
	 * Get the list of the accesses
	 * @return An array of all Authentification objects
	 * @throws APIException Exception containing the error message coming from the Authentification service
	 */
    public Vector<Authentification> getAllAccess() throws APIException{
    	String http_address=this.getURL()+"/api/authentification";
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		return this.accessAuxiSeveral(HttpMethod.GET, http_address, null);
    }

    /**
     * Check if a specific access exists
     * @param login The login of the Authentification object expected
     * @return The Authentification object expected
     * @throws APIException Exception containing the error message coming from the Authentification service
     */
	public Authentification checkAccess(String login) throws APIException{
		AttributesTool.isEmptyThrowsError(login);

		AttributesTool.checkRegexThrowsError(login);

		String http_address=this.getURL()+"/api/authentification/"+login;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		return this.accessAuxi(HttpMethod.GET, http_address, null);
	}

	// HTTP POST request
	/**
	 * Create an access on the Node JS server
	 * @param login Login of the access
	 * @param password Password of the access
	 * @param mail Mail of the access
	 * @param status Status of the access
	 * @param creator Creator user which created the access
	 * @return The Authentification object created
	 * @throws APIException Exception containing the error message coming from the Authentification service
	 */
	public Authentification createAccess(String login, String password, String mail, 	
										 String status,
										 String creator) throws APIException{
		AttributesTool.isEmptyThrowsError(login);
		AttributesTool.isEmptyThrowsError(password);
		AttributesTool.isEmptyThrowsError(mail);
		AttributesTool.isEmptyThrowsError(status);

    	String http_address=this.getURL()+"/api/authentification";
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("login",    login));
		urlParameters.add(new BasicNameValuePair("mail",     mail));
		urlParameters.add(new BasicNameValuePair("password", password));
		urlParameters.add(new BasicNameValuePair("status",   status));
		urlParameters.add(new BasicNameValuePair("creator",  creator));

		return this.accessAuxi(HttpMethod.POST, http_address, urlParameters);
	}

	/**
	 * Check if the connecting information are correct
	 * @param login Login of the access
	 * @param password Password of the access
	 * @return The Authentification object with the expected login
	 * @throws APIException Exception containing the error message coming from the Authentification service
	 */
	public Authentification checkPassword(String login, String password) throws APIException{
		AttributesTool.isEmptyThrowsError(login);
		AttributesTool.isEmptyThrowsError(password);

		AttributesTool.checkRegexThrowsError(login);

    	String http_address=this.getURL()+"/api/authentification/"+login;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("password", password));

		return this.accessAuxi(HttpMethod.POST, http_address, urlParameters);
	}


	// HTTP PUT request
	/**
	 * Update an access in the Node JS server
	 * @param login Login of the access which must be updated
	 * @param password New password of the access
	 * @param mail New mail of the access
	 * @param status New status of the access
	 * @param keepOldPassword Flag to indicate if the previous password must be kept 
	 * @param updator Updator user which updates the access
	 * @return The Authentification object after the update
	 * @throws APIException Exception containing the error message coming from the Authentification service
	 */
	public Authentification updatePassword(String login, String password, String mail, 
										   String status, 
										   boolean keepOldPassword,
										   String updator) throws APIException{
		AttributesTool.isEmptyThrowsError(login);
		if(!keepOldPassword){
			AttributesTool.isEmptyThrowsError(password);
		}
		AttributesTool.isEmptyThrowsError(mail);
		AttributesTool.isEmptyThrowsError(status);

		AttributesTool.checkRegexThrowsError(login);

    	String http_address=this.getURL()+"/api/authentification/"+login;
    	boolean flag = true;

    	if(keepOldPassword==true){
    		http_address+="?action=keep-old-password";
    		flag = false;
		}
		if(this.getToken()!=null && !this.getToken().equals("")){
			if(flag){
				http_address+="?";
			}
			else{
				http_address+="&";
			}
			http_address+="token="+this.getToken();
			flag = false;
		}

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("mail", mail));
		if(!keepOldPassword){
			urlParameters.add(new BasicNameValuePair("password", password));
		}
		urlParameters.add(new BasicNameValuePair("status",   status));
		urlParameters.add(new BasicNameValuePair("updator",  updator));

		return this.accessAuxi(HttpMethod.PUT, http_address, urlParameters);
	}


	// HTTP DELETE request

	/**
	 * Delete one specific access
	 * @param login Login of the access which must be removed
	 * @return The Authentification object removed
	 * @throws APIException Exception containing the error message coming from the Authentification service
	 */
	public Authentification deleteAccess(String login) throws APIException{
		AttributesTool.isEmptyThrowsError(login);

		AttributesTool.checkRegexThrowsError(login);

		String http_address=this.getURL()+"/api/authentification/"+login;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		return this.accessAuxi(HttpMethod.DELETE, http_address, null);
	}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Auxilary function for webservice clients
	/**
	 * Get one access
	 * @param method The HTTP method chosen to interact with the server
	 * @param http_address The URL address of the Authentification service (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @return One Authentification object
	 * @throws APIException Exception containing the error message coming from the Authentification service
	 */
	protected Authentification accessAuxi(HttpMethod method, String http_address, List<NameValuePair> urlParameters) throws APIException{
		try{
			Authentification authentification = (Authentification)this.requestOne(method, Authentification.class, http_address, urlParameters, null);
			if(authentification==null || authentification.isEmpty()){
	        	throw new APIException();
        	}
        	return authentification;
        }
		catch (APIException e){
        	throw e;
        }
	}

	/**
	 * Get several accesses
	 * @param method The HTTP method chosen to interact with the server
	 * @param http_address The URL address of the Authentification service (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @return Several Authentification objects
	 * @throws APIException Exception containing the error message coming from the Authentification service
	 */
	protected Vector<Authentification> accessAuxiSeveral(HttpMethod method, String http_address, List<NameValuePair> urlParameters) throws APIException{
		try{
    		Vector<APIObject>	vector_auxi 	= this.requestSeveral(method, Authentification.class, http_address, urlParameters, null);
    		Vector<Authentification>		vector_return	= new Vector<Authentification>();

    		for(APIObject obj:vector_auxi){
    			Authentification objDB = (Authentification)obj;
    			vector_return.add(objDB);
    		}
    		return vector_return;
        }
		catch (APIException e){
			throw e;
		}
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
}