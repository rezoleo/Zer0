package fr.service_contributor.network;

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

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;

import fr.cache.object.Cache;
import fr.service_contributor.object.Contributor;
import fr.service_contributor.object.Data;
import fr.webservicecore.network.HttpMethod;
import fr.webservicecore.network.WebServiceClient;
import fr.webservicecore.object.APIException;
import fr.webservicecore.object.APIObject;
import fr.webservicecore.toolbox.CheckAttributes;

/* 
 * Class 	: ContributorClient
 * Function : This class contains the WebService Client to manage contributors in NodeJS server. 
 * Note		: This class uses directly HttpRequest class
 */
public class ContributorClient extends WebServiceClient
{ 	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Contributor FUNCTIONS
    // HTTP GET requests

	// Name        : getAllContributor
    // Type        : function
    // Description : Get the list of all the contributors
    public Vector<Contributor> getAllContributor() throws APIException{
    	String http_address=URL+"/api/contributor";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiSeveral(HttpMethod.GET, http_address, null);
    }

    // Name        : getOneContributorById
    // Type        : function
    // Description : Get information of one specific contributor by the contribution id
	public Contributor getOneContributorById(String id) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);

		CheckAttributes.checkRegexThrowsError(id);

		String http_address=URL+"/api/contributor/"+id;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }

    // Name        : getOneContributorByLogin
    // Type        : function
    // Description : Get information of one specific contributor by his/her login
	public Contributor getOneContributorByLogin(String login) throws APIException{
		CheckAttributes.isEmptyThrowsError(login);

		CheckAttributes.checkRegexThrowsError(login);

		String http_address=URL+"/api/contributor/login/"+login;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }

    // Name        : checkOneContributorByLogin
    // Type        : function
    // Description : Check if one login is in the contributor list
	public boolean checkOneContributorByLogin(String login) throws APIException{
		CheckAttributes.isEmptyThrowsError(login);

		CheckAttributes.checkRegexThrowsError(login);

		String http_address=URL+"/api/contributor/login/"+login+"?action=check";
		if(this.token!=null && !this.token.equals("")){
			http_address+="&token="+this.token;
		}

		Data data = this.accessAuxiOneData(HttpMethod.GET, http_address, null);
		if(data!=null && data.getIsRegistered()==true){
			return true;
		}
		return false;
    }

	// Name        : getCacheInformation
    // Type        : function
    // Description : Get information about the last updates of the contributor list
	public Cache getCacheInformation() throws APIException{
		String http_address=URL+"/api/contributor?action=get-cache-infos";
		if(this.token!=null && !this.token.equals("")){
			http_address+="&token="+this.token;
		}

		return this.accessAuxiCache(HttpMethod.GET, http_address, null);
    }


	// HTTP POST request

	// Name        : createContributor
    // Type        : function
    // Description : Create a contributor on the Node JS server
	public Contributor createContributor(String login,
										 String creator) throws APIException{
		CheckAttributes.isEmptyThrowsError(login);

		String http_address=URL+"/api/contributor";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("login",    login));
		urlParameters.add(new BasicNameValuePair("creator",  creator));

		return this.accessAuxiOne(HttpMethod.POST, http_address, urlParameters);
	}

	// HTTP DELETE request

	// Name        : deleteOneContributor
	// Type        : function
	// Description : Delete one specific contributor
	public Contributor deleteOneContributor(String id) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);

		CheckAttributes.checkRegexThrowsError(id);

		String http_address=URL+"/api/contributor/"+id;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.DELETE, http_address, null);
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Auxilary functions for webservice clients
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