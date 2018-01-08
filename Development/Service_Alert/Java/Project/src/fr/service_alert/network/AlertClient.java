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
import fr.webservicecore.error.APIException;
import fr.webservicecore.network.HttpMethod;
import fr.webservicecore.network.WebServiceClient;
import fr.webservicecore.object.APIObject;
import fr.webservicecore.toolbox.CheckAttributes;

/** 
 * Client to manage alerts in the Alert service
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.0.0
 */
public class AlertClient extends WebServiceClient
{ 	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Alert FUNCTIONS
    // HTTP GET requests
	/**
	 * Get the list of all the alerts
	 * @param minimumlevel Level from which the alerts are returned
	 * @return An array of Alert objects
	 * @throws APIException Exception containing the error message coming from the Alert service
	 */
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

	/**
	 * Get information of one specific alert by id of the alert id
	 * @param id Identification number of the alert
	 * @return An Alert object with the id
	 * @throws APIException Exception containing the error message coming from the Alert service
	 */
	public Alert getOneAlertById(String id) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);

		CheckAttributes.checkRegexThrowsError(id);

		String http_address=URL+"/api/alert/"+id;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
	}

	/**
	 * Get information about the last updates of the alert list
	 * @throws APIException Exception containing the error message coming from the Alert service
	 */
	public Cache getCacheInformation() throws APIException{
		String http_address=URL+"/api/card?action=get-cache-infos";
		if(this.token!=null && !this.token.equals("")){
			http_address+="&token="+this.token;
		}

		return this.accessAuxiCache(HttpMethod.GET, http_address, null);
    }

	// HTTP POST request
	/**
	 * Create an alert on the Node JS server
	 * @param message Message of the alert
	 * @param level Level of the alert
	 * @throws APIException Exception containing the error message coming from the Alert service
	 */
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

	/**
	 * Auxilary function for webservice clients
	 * @param method The HTTP method chosen to interact with the server
	 * @param http_address The URL address of the Alert service (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @throws APIException Exception containing the error message coming from the Alert service
	 */
	private Alert accessAuxiOne(HttpMethod method, String http_address, List<NameValuePair> urlParameters) throws APIException{
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

	private Cache accessAuxiCache(HttpMethod method, String http_address, List<NameValuePair> urlParameters) throws APIException{
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

	private Vector<Alert> accessAuxiSeveral(HttpMethod method, String http_address, List<NameValuePair> urlParameters) throws APIException{
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