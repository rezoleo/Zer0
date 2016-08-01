package fr.service_card.network;

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
import fr.service_card.object.Card;
import fr.webservicecore.network.HttpMethod;
import fr.webservicecore.network.WebServiceClient;
import fr.webservicecore.object.APIException;
import fr.webservicecore.object.APIObject;
import fr.webservicecore.toolbox.CheckAttributes;

/* Class 	: CardClient
 * Function : This class contains the WebService Client to manage cards in NodeJS server. 
 * Note		: This class uses directly HttpRequest class
 */
public class CardClient extends WebServiceClient
{ 	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Card FUNCTIONS
    // HTTP GET requests

	// Name        : getAllCards
    // Type        : function
    // Description : Get the list of all the cards
    public Vector<Card> getAllCard() throws APIException{
    	String http_address=URL+"/api/card";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiSeveral(HttpMethod.GET, http_address, null);
    }

    // Name        : getOneCardById
    // Type        : function
    // Description : Get information of one specific card by id of the card id
	public Card getOneCardById(String id) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);

		CheckAttributes.checkRegexThrowsError(id);

		String http_address=URL+"/api/card/"+id;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }

    // Name        : getOneCardByCode
    // Type        : function
    // Description : Get information of one specific card by id of the card code
	public Card getOneCardByCode(String code) throws APIException{
		CheckAttributes.isEmptyThrowsError(code);

		CheckAttributes.checkRegexThrowsError(code);

		String http_address=URL+"/api/card/code/"+code;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }

    // Name        : getCacheInformation
    // Type        : function
    // Description : Get information about the last updates of the card list
	public Cache getCacheInformation() throws APIException{
		String http_address=URL+"/api/card?action=get-cache-infos";
		if(this.token!=null && !this.token.equals("")){
			http_address+="&token="+this.token;
		}

		return this.accessAuxiCache(HttpMethod.GET, http_address, null);
    }


	// HTTP POST request

	// Name        : createCard
    // Type        : function
    // Description : Create a card on the Node JS server
	public Card createCard(	String owner, 
							String code,    String status,
							String creator) throws APIException{
		CheckAttributes.isEmptyThrowsError(owner);
		CheckAttributes.isEmptyThrowsError(code);
		CheckAttributes.isEmptyThrowsError(status);

		String http_address=URL+"/api/card";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("owner",    owner));
		urlParameters.add(new BasicNameValuePair("code",     code));
		urlParameters.add(new BasicNameValuePair("status",   status));
		urlParameters.add(new BasicNameValuePair("creator",  creator));

		return this.accessAuxiOne(HttpMethod.POST, http_address, urlParameters);
	}


	// HTTP PUT request

	// Name        : updateCard
    // Type        : function
    // Description : Update a card on the Node JS server
	public Card updateCard(	String id, 		String owner, 
							String code,    String status,
							String updator) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);
		CheckAttributes.isEmptyThrowsError(owner);
		CheckAttributes.isEmptyThrowsError(code);
		CheckAttributes.isEmptyThrowsError(status);

		CheckAttributes.checkRegexThrowsError(id);

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("owner",    owner));
		urlParameters.add(new BasicNameValuePair("code",     code));
		urlParameters.add(new BasicNameValuePair("status",   status));
		urlParameters.add(new BasicNameValuePair("updator",  updator));

		String http_address=URL+"/api/card/"+id;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.PUT, http_address, urlParameters);
	}


	// HTTP DELETE request

	// Name        : deleteOneCard
	// Type        : function
	// Description : Delete one specific card
	public Card deleteOneCard(String id) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);

		CheckAttributes.checkRegexThrowsError(id);

		String http_address=URL+"/api/card/"+id;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.DELETE, http_address, null);
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Auxilary functions for webservice clients
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