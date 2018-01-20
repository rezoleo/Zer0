package fr.service_contributor.network;

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

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;

import fr.cache.object.Cache;
import fr.service_contributor.object.Contributor;
import fr.service_contributor.object.Data;
import fr.webservicecore.error.APIException;
import fr.webservicecore.network.HttpMethod;
import fr.webservicecore.network.WebServiceClient;
import fr.webservicecore.object.APIObject;
import fr.webservicecore.toolbox.AttributesTool;;

/** 
 * Client to manage contributors in the Contributor service
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class ContributorClient extends WebServiceClient
{ 	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Contributor FUNCTIONS
    // HTTP GET requests
	/**
	 * Get the list of the contributors
	 * @return An array of all Contributor objects
	 * @throws APIException Exception containing the error message coming from the Contributor service
	 */
    public Vector<Contributor> getAllContributor() throws APIException{
    	String http_address=this.getURL()+"/api/contributor";
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		return this.accessAuxiSeveral(HttpMethod.GET, http_address, null);
    }

    /**
	 * Get information of one specific contributor identified by its id
	 * @param id Identification number of the contributor
	 * @return The Contributor object whose its id is the one requested
	 * @throws APIException Exception containing the error message coming from the Contributor service
	 */
	public Contributor getOneContributorById(String id) throws APIException{
		AttributesTool.isEmptyThrowsError(id);

		AttributesTool.checkRegexThrowsError(id);

		String http_address=this.getURL()+"/api/contributor/"+id;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }

/**
	 * Get information of one specific contributor by his/her login
	 * @param login Login of the contributor
	 * @return The Contributor object whose its code is the one requested
	 * @throws APIException Exception containing the error message coming from the Contributor service
	 */
	public Contributor getOneContributorByLogin(String login) throws APIException{
		AttributesTool.isEmptyThrowsError(login);

		AttributesTool.checkRegexThrowsError(login);

		String http_address=this.getURL()+"/api/contributor/login/"+login;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }

    /**
	 * Check if one login is in the contributor list
	 * @param login Login of the contributor
	 * @return The Contributor object whose its code is the one requested
	 * @throws APIException Exception containing the error message coming from the Contributor service
	 */
	public boolean checkOneContributorByLogin(String login) throws APIException{
		AttributesTool.isEmptyThrowsError(login);

		AttributesTool.checkRegexThrowsError(login);

		String http_address=this.getURL()+"/api/contributor/login/"+login+"?action=check";
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="&token="+this.getToken();
		}

		Data data = this.accessAuxiOneData(HttpMethod.GET, http_address, null);
		if(data!=null && data.getIsRegistered()==true){
			return true;
		}
		return false;
    }

	/**
	 * Get information about the last updates of the contributor list
	 * @return The Cache object which describes the information about the last updates
	 * @throws APIException Exception containing the error message coming from the Contributor service
	 */
	public Cache getCacheInformation() throws APIException{
		String http_address=this.getURL()+"/api/contributor?action=get-cache-infos";
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="&token="+this.getToken();
		}

		return this.accessAuxiCache(HttpMethod.GET, http_address, null);
    }


	// HTTP POST request
	/**
	 * Create a contributor on the Node JS server
	 * @param login Login of the contributor
	 * @param creator Creator user which created the contributor
	 * @return The Contributor object created
	 * @throws APIException Exception containing the error message coming from the Contributor service
	 */
	public Contributor createContributor(String login,
										 String creator) throws APIException{
		AttributesTool.isEmptyThrowsError(login);

		String http_address=this.getURL()+"/api/contributor";
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("login",    login));
		urlParameters.add(new BasicNameValuePair("creator",  creator));

		return this.accessAuxiOne(HttpMethod.POST, http_address, urlParameters);
	}

	// HTTP DELETE request
	/**
	 * Delete one specific contributor
	 * @param id Identification number of the contributor
	 * @return The Contributor object removed
	 * @throws APIException Exception containing the error message coming from the Contributor service
	 */
	public Contributor deleteOneContributor(String id) throws APIException{
		AttributesTool.isEmptyThrowsError(id);

		AttributesTool.checkRegexThrowsError(id);

		String http_address=this.getURL()+"/api/contributor/"+id;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		return this.accessAuxiOne(HttpMethod.DELETE, http_address, null);
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Auxilary functions for webservice clients
	/**
	 * Get one contributor
	 * @param method The HTTP method chosen to interact with the server
	 * @param http_address The URL address of the Contributor service (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @return One contributor object
	 * @throws APIException Exception containing the error message coming from the Contributor service
	 */
	protected Contributor accessAuxiOne(HttpMethod method, String http_address, List<NameValuePair> urlParameters) throws APIException{
		try{
			Contributor contributor = (Contributor)this.requestOne(method, Contributor.class, http_address, urlParameters, null);
			if(contributor==null || contributor.isEmpty()){
				throw new APIException();
			}
			return contributor;
		}
		catch (APIException e){
			throw e;
		}
	}
	protected Data accessAuxiOneData(HttpMethod method, String http_address, List<NameValuePair> urlParameters) throws APIException{
		try{
			Data data = (Data)this.requestOne(method, Data.class, http_address, urlParameters, null);
			if(data==null || data.isEmpty()){
				throw new APIException();
			}
			return data;
		}
		catch (APIException e){
			throw e;
		}
	}

	/**
	 * Get cache information
	 * @param method The HTTP method chosen to interact with the server
	 * @param http_address The URL address of the Contributor service (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @return The Cache object which describes the information about the last updates
	 * @throws APIException Exception containing the error message coming from the Contributor service
	 */
	protected Cache accessAuxiCache(HttpMethod method, String http_address, List<NameValuePair> urlParameters) throws APIException{
		try{
			Cache cache = (Cache)this.requestOne(method, Cache.class, http_address, urlParameters, null);
			if(cache==null){
				throw new APIException();
			}
			return cache;
		}
		catch (APIException e){
			throw e;
		}
	}

	/**
	 * Get several contributors
	 * @param method The HTTP method chosen to interact with the server
	 * @param http_address The URL address of the Contributor service (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @return Several contributor objects
	 * @throws APIException Exception containing the error message coming from the Contributor service
	 */
	protected Vector<Contributor> accessAuxiSeveral(HttpMethod method, String http_address, List<NameValuePair> urlParameters) throws APIException{
		try{
    		Vector<APIObject>	vector_auxi 	= this.requestSeveral(method, Contributor.class, http_address, urlParameters, null);
    		Vector<Contributor>	vector_return	= new Vector<Contributor>();

    		for(APIObject obj:vector_auxi){
    			Contributor objDB = (Contributor)obj;
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