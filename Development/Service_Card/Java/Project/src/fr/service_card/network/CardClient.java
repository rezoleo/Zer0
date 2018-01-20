package fr.service_card.network;

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
import fr.service_card.object.Card;
import fr.webservicecore.error.APIException;
import fr.webservicecore.network.HttpMethod;
import fr.webservicecore.network.WebServiceClient;
import fr.webservicecore.object.APIObject;
import fr.webservicecore.toolbox.AttributesTool;

/** 
 * Client to manage accesses in the Card service
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class CardClient extends WebServiceClient
{
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Card FUNCTIONS
    // HTTP GET requests
	/**
	 * Get the list of the cards
	 * @return An array of all Card objects
	 * @throws APIException Exception containing the error message coming from the Card service
	 */
    public Vector<Card> getAllCard() throws APIException{
    	String http_address=this.getURL()+"/api/card";
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		return this.accessAuxiSeveral(HttpMethod.GET, http_address, null);
    }

    /**
	 * Get information of one specific card identified by its id
	 * @param id Identification number of the card
	 * @return The Card object whose its id is the one requested
	 * @throws APIException Exception containing the error message coming from the Card service
	 */
	public Card getOneCardById(String id) throws APIException{
		AttributesTool.isEmptyThrowsError(id);

		AttributesTool.checkRegexThrowsError(id);

		String http_address=this.getURL()+"/api/card/"+id;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }

    /**
	 * Get information of one specific card identified by its code
	 * @param code Code of the card
	 * @return The Card object whose its code is the one requested
	 * @throws APIException Exception containing the error message coming from the Card service
	 */
	public Card getOneCardByCode(String code) throws APIException{
		AttributesTool.isEmptyThrowsError(code);

		AttributesTool.checkRegexThrowsError(code);

		String http_address=this.getURL()+"/api/card/code/"+code;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }

	/**
	 * Get information about the last updates done in the Card service
	 * @return The Cache object which describes the information about the last updates
	 * @throws APIException Exception containing the error message coming from the Card service
	 */
	public Cache getCacheInformation() throws APIException{
		String http_address=this.getURL()+"/api/card?action=get-cache-infos";
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="&token="+this.getToken();
		}

		return this.accessAuxiCache(HttpMethod.GET, http_address, null);
    }


	// HTTP POST request
	/**
	 * Create a card on the Card service
	 * @param owner Login of the owner of the card
	 * @param code Code of the card
	 * @param status Status of the card
	 * @param creator Creator user which created the access
	 * @return The Card object created
	 * @throws APIException Exception containing the error message coming from the Card service
	 */
	public Card createCard(	String owner, 
							String code,    String status,
							String creator) throws APIException{
		AttributesTool.isEmptyThrowsError(owner);
		AttributesTool.isEmptyThrowsError(code);
		AttributesTool.isEmptyThrowsError(status);

		String http_address=this.getURL()+"/api/card";
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("owner",    owner));
		urlParameters.add(new BasicNameValuePair("code",     code));
		urlParameters.add(new BasicNameValuePair("status",   status));
		urlParameters.add(new BasicNameValuePair("creator",  creator));

		return this.accessAuxiOne(HttpMethod.POST, http_address, urlParameters);
	}


	// HTTP PUT request
	/**
	 * Update a card in the Node JS server
	 * @param id Identification number of the card
	 * @param owner Login of the owner of the card
	 * @param code Code of the card
	 * @param status Status of the card
	 * @param updator Updator user which updated the access
	 * @return The Card object after the update
	 * @throws APIException Exception containing the error message coming from the Card service
	 */
	public Card updateCard(	String id, 		String owner, 
							String code,    String status,
							String updator) throws APIException{
		AttributesTool.isEmptyThrowsError(id);
		AttributesTool.isEmptyThrowsError(owner);
		AttributesTool.isEmptyThrowsError(code);
		AttributesTool.isEmptyThrowsError(status);

		AttributesTool.checkRegexThrowsError(id);

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("owner",    owner));
		urlParameters.add(new BasicNameValuePair("code",     code));
		urlParameters.add(new BasicNameValuePair("status",   status));
		urlParameters.add(new BasicNameValuePair("updator",  updator));

		String http_address=this.getURL()+"/api/card/"+id;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		return this.accessAuxiOne(HttpMethod.PUT, http_address, urlParameters);
	}


	// HTTP DELETE request
	/**
	 * Delete one specific card
	 * @param id Identification number of the card
	 * @return The Card object removed
	 * @throws APIException Exception containing the error message coming from the Card service
	 */
	public Card deleteOneCard(String id) throws APIException{
		AttributesTool.isEmptyThrowsError(id);

		AttributesTool.checkRegexThrowsError(id);

		String http_address=this.getURL()+"/api/card/"+id;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		return this.accessAuxiOne(HttpMethod.DELETE, http_address, null);
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Auxilary functions for webservice clients
	/**
	 * Get one card
	 * @param method The HTTP method chosen to interact with the server
	 * @param http_address The URL address of the Card service (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @return One card object
	 * @throws APIException Exception containing the error message coming from the Card service
	 */
	protected Card accessAuxiOne(HttpMethod method, String http_address, List<NameValuePair> urlParameters) throws APIException{
		try{
			Card card = (Card)this.requestOne(method, Card.class, http_address, urlParameters, null);
			if(card==null || card.isEmpty()){
				throw new APIException();
			}
			return card;
		}
		catch (APIException e){
			throw e;
		}
	}

	/**
	 * Get cache information
	 * @param method The HTTP method chosen to interact with the server
	 * @param http_address The URL address of the Card service (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @return The Cache object which describes the information about the last updates
	 * @throws APIException Exception containing the error message coming from the Card service
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
	 * Get several cards
	 * @param method The HTTP method chosen to interact with the server
	 * @param http_address The URL address of the Card service (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @return Several card objects
	 * @throws APIException Exception containing the error message coming from the Card service
	 */
	protected Vector<Card> accessAuxiSeveral(HttpMethod method, String http_address, List<NameValuePair> urlParameters) throws APIException{
		try{
    		Vector<APIObject>	vector_auxi 	= this.requestSeveral(method, Card.class, http_address, urlParameters, null);
    		Vector<Card>		vector_return	= new Vector<Card>();

    		for(APIObject obj:vector_auxi){
    			Card objDB = (Card)obj;
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