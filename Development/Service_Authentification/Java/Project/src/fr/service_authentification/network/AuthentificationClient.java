package fr.service_authentification.network;

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


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import fr.service_authentification.object.Authentification;
import fr.webservicecore.network.HttpMethod;
import fr.webservicecore.network.WebServiceClient;
import fr.webservicecore.object.APIException;
import fr.webservicecore.object.APIObject;
import fr.webservicecore.toolbox.CheckAttributes;

/* 
 * Class 	: AuthentificationClient
 * Author(s): Zidmann
 * Function : This class contains the WebService client to manage access in authorization service. 
 * Version  : 1.0.0
 * Note		: This class uses directly HttpRequest class
 */
public class AuthentificationClient extends WebServiceClient
{ 	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Authentification FUNCTIONS

	// Name        : getAllAccess
    // Type        : function
    // Description : Get the list of all the accesses
    public Vector<Authentification> getAllAccess() throws APIException{
    	String http_address=URL+"/api/authentification";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiSeveral(HttpMethod.GET, http_address, null);
    }

	// Name        : checkAccess
	// Type        : function
	// Description : Check if a specific access exists
	public Authentification checkAccess(String login) throws APIException{
		CheckAttributes.isEmptyThrowsError(login);

		CheckAttributes.checkRegexThrowsError(login);

		String http_address=URL+"/api/authentification/"+login;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxi(HttpMethod.GET, http_address, null);
	}
	
	// HTTP POST request

	// Name        : createAccess
    // Type        : function
    // Description : Create an access on the Node JS server
	public Authentification createAccess(String login, String password, String mail, 	
										 String status,
										 String creator) throws APIException{
		CheckAttributes.isEmptyThrowsError(login);
		CheckAttributes.isEmptyThrowsError(password);
		CheckAttributes.isEmptyThrowsError(mail);
		CheckAttributes.isEmptyThrowsError(status);

    	String http_address=URL+"/api/authentification";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("login",    login));
		urlParameters.add(new BasicNameValuePair("mail",     mail));
		urlParameters.add(new BasicNameValuePair("password", password));
		urlParameters.add(new BasicNameValuePair("status",   status));
		urlParameters.add(new BasicNameValuePair("creator",  creator));

		return this.accessAuxi(HttpMethod.POST, http_address, urlParameters);
	}

	// Name        : checkPassword
	// Type        : function
	// Description : Check if the connecting information are correct
	public Authentification checkPassword(String login, String password) throws APIException{
		CheckAttributes.isEmptyThrowsError(login);
		CheckAttributes.isEmptyThrowsError(password);

		CheckAttributes.checkRegexThrowsError(login);

    	String http_address=URL+"/api/authentification/"+login;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("password", password));

		return this.accessAuxi(HttpMethod.POST, http_address, urlParameters);
	}


	// HTTP PUT request

	// Name        : updatePassword
    // Type        : function
    // Description : Update a password in the Node JS server
	public Authentification updatePassword(String login, String password, String mail, 
										   String status, 
										   boolean keepOldPassword,
										   String updator) throws APIException{
		CheckAttributes.isEmptyThrowsError(login);
		if(!keepOldPassword){
			CheckAttributes.isEmptyThrowsError(password);
		}
		CheckAttributes.isEmptyThrowsError(mail);
		CheckAttributes.isEmptyThrowsError(status);

		CheckAttributes.checkRegexThrowsError(login);

    	String http_address=URL+"/api/authentification/"+login;
    	boolean flag = true;

    	if(keepOldPassword==true){
    		http_address+="?action=keep-old-password";
    		flag = false;
		}
		if(this.token!=null && !this.token.equals("")){
			if(flag){
				http_address+="?";
			}
			else{
				http_address+="&";
			}
			http_address+="token="+this.token;
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

	// Name        : deleteAccess
	// Type        : function
	// Description : Delete one specific access
	public Authentification deleteAccess(String login) throws APIException{
		CheckAttributes.isEmptyThrowsError(login);

		CheckAttributes.checkRegexThrowsError(login);

		String http_address=URL+"/api/authentification/"+login;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxi(HttpMethod.DELETE, http_address, null);
	}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Auxilary function for webservice clients
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