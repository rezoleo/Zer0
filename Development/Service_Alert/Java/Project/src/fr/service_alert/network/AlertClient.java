package fr.service_alert.network;

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

import fr.cache.object.Cache;
import fr.service_alert.object.Alert;
import fr.webservicecore.network.HttpMethod;
import fr.webservicecore.network.WebServiceClient;
import fr.webservicecore.object.APIException;
import fr.webservicecore.object.APIObject;
import fr.webservicecore.toolbox.CheckAttributes;

/* 
 * Class 	: AlertClient
 * Author(s): Zidmann
 * Function : This class contains the WebService client to manage alerts in NodeJS server. 
 * Version  : 1.0.0
 * Note		: This class uses directly HttpRequest class
 */
public class AlertClient extends WebServiceClient
{ 	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Alert FUNCTIONS
    // HTTP GET requests

	// Name        : getAllAlert
    // Type        : function
    // Description : Get the list of all the alerts
    public Vector<Alert> getAllAlert(String minimumlevel) throws APIException{
    	String http_address=URL+"/api/alert";

		if(minimumlevel!=null && !minimumlevel.equals("")){
			http_address+="?minimumlevel="+minimumlevel;
		}

		if(this.token!=null && !this.token.equals("")){
			if(minimumlevel!=null && !minimumlevel.equals("")){
				http_address+="&";
			}
			else{
				http_address+="?";
			}
			http_address+="token="+this.token;
		}

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("minimumlevel", minimumlevel));

		return this.accessAuxiSeveral(HttpMethod.GET, http_address, urlParameters);
    }

	// Name        : getOneAlertById
    // Type        : function
    // Description : Get information of one specific alert by id of the alert id
	public Alert getOneAlertById(String id) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);

		CheckAttributes.checkRegexThrowsError(id);

		String http_address=URL+"/api/alert/"+id;
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

	// Name        : createAlert
    // Type        : function
    // Description : Create an alert on the Node JS server
	public Alert createAlert(String message, String level) throws APIException{
		CheckAttributes.isEmptyThrowsError(message);
		CheckAttributes.isEmptyThrowsError(level);

    	String http_address=URL+"/api/alert";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("message", message));
		urlParameters.add(new BasicNameValuePair("level",   level));

		return this.accessAuxiOne(HttpMethod.POST, http_address, urlParameters);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Auxilary function for webservice clients
	protected Alert accessAuxiOne(HttpMethod method, String http_address, List<NameValuePair> urlParameters) throws APIException{
		try{
			Alert alert = (Alert)this.requestOne(method, Alert.class, http_address, urlParameters, null);
			if(alert==null || alert.isEmpty()){
	        	throw new APIException();
        	}
        	return alert;
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

	protected Vector<Alert> accessAuxiSeveral(HttpMethod method, String http_address, List<NameValuePair> urlParameters) throws APIException{
		try{
    		Vector<APIObject>	vector_auxi 	= this.requestSeveral(method, Alert.class, http_address, urlParameters, null);
    		Vector<Alert>		vector_return	= new Vector<Alert>();

    		for(APIObject obj:vector_auxi){
    			Alert objDB = (Alert)obj;
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