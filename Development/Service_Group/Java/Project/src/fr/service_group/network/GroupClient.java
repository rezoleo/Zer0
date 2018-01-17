package fr.service_group.network;

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


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import fr.cache.object.Cache;
import fr.service_group.object.Group;
import fr.webservicecore.error.APIException;
import fr.webservicecore.network.HttpMethod;
import fr.webservicecore.network.WebServiceClient;
import fr.webservicecore.object.APIObject;
import fr.webservicecore.toolbox.CheckAttributes;

/** 
 * Client to manage groups in the Group service
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class GroupClient extends WebServiceClient
{ 	
	protected DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Group FUNCTIONS
    // HTTP GET requests
	/**
	 * Get the list of the groups
	 * @return An array of all Group objects
	 * @throws APIException Exception containing the error message coming from the Group service
	 */
    public Vector<Group> getAllGroup() throws APIException{
    	String http_address=URL+"/api/group";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiSeveral(HttpMethod.GET, http_address, null);
    }

    /**
	 * Get information of one specific group by its id
	 * @param id Identification number of the card
	 * @return The Group object whose its id is the one requested
	 * @throws APIException Exception containing the error message coming from the Group service
	 */
	public Group getOneGroupById(String id) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);

		CheckAttributes.checkRegexThrowsError(id);

		String http_address=URL+"/api/group/"+id;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }

    /**
	 * Get information of one specific group by its name
	 * @param name Name of the group
	 * @return The Group object whose its id is the one requested
	 * @throws APIException Exception containing the error message coming from the Group service
	 */
	public Group getOneGroupByName(String name) throws APIException{
		CheckAttributes.isEmptyThrowsError(name);

		CheckAttributes.checkRegexThrowsError(name);

		String http_address=URL+"/api/group/name/"+name;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }

    /**
	 * Get information of all the groups where a specific login belongs
	 * @param login Login of a member
	 * @return The list of Group objects whose the login belongs
	 * @throws APIException Exception containing the error message coming from the Group service
	 */
	public Vector<Group> searchByLogin(String login) throws APIException{
		CheckAttributes.isEmptyThrowsError(login);

		CheckAttributes.checkRegexThrowsError(login);

		String http_address=URL+"/api/group/search/"+login;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiSeveral(HttpMethod.GET, http_address, null);
    }

	/**
	 * Get information about the last updates of the group list
	 * @return The Cache object which describes the information about the last updates
	 * @throws APIException Exception containing the error message coming from the Group service
	 */
	public Cache getCacheInformation() throws APIException{
		String http_address=URL+"/api/group?action=get-cache-infos";
		if(this.token!=null && !this.token.equals("")){
			http_address+="&token="+this.token;
		}

		return this.accessAuxiCache(HttpMethod.GET, http_address, null);
    }

	// HTTP POST request
	/**
	 * Create one group on the Node JS server
	 * @param name Name of the group
	 * @param type Type of the group
	 * @param description Description of the group
	 * @param mail Mail of the group
	 * @param logo Logo path of the group
	 * @param picture Picture path of the group
	 * @param creator Creator user which created the group
	 * @return The Contributor object created
	 * @throws APIException Exception containing the error message coming from the Group service
	 */
	public Group createGroup(	String name, String type, String description, String mail,
								String logo, String picture, 	 
								String creator) throws APIException{
		CheckAttributes.isEmptyThrowsError(name);
		CheckAttributes.isEmptyThrowsError(type);

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("name",		name));
		urlParameters.add(new BasicNameValuePair("type",		type));
		urlParameters.add(new BasicNameValuePair("description", description));
		urlParameters.add(new BasicNameValuePair("mail",		mail));
		urlParameters.add(new BasicNameValuePair("logo",		logo));
		urlParameters.add(new BasicNameValuePair("picture",		picture));
		urlParameters.add(new BasicNameValuePair("creator",		creator));

		String http_address=URL+"/api/group/";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.POST, http_address, urlParameters);
	}

	// HTTP PUT request	
	/**
	 * Update a card in the Node JS server
	 * @param id Identification number of the group
	 * @param name Name of the group
	 * @param type Type of the group
	 * @param description Description of the group
	 * @param mail Mail of the group
	 * @param logo Logo path of the group
	 * @param picture Picture path of the group
	 * @param updator Updator user which updated the group
	 * @return The Group object after the update
	 * @throws APIException Exception containing the error message coming from the Group service
	 */
	public Group updateGroup(	String id,
								String name, String type, String description, String mail,
								String logo, String picture,
								String updator) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);
		CheckAttributes.isEmptyThrowsError(name);
		CheckAttributes.isEmptyThrowsError(type);

		CheckAttributes.checkRegexThrowsError(id);

		String http_address=URL+"/api/group/"+id;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("name",		name));
		urlParameters.add(new BasicNameValuePair("type",		type));
		urlParameters.add(new BasicNameValuePair("description", description));
		urlParameters.add(new BasicNameValuePair("mail",		mail));
		urlParameters.add(new BasicNameValuePair("logo",		logo));
		urlParameters.add(new BasicNameValuePair("picture",		picture));
		urlParameters.add(new BasicNameValuePair("updator",		updator));

		return this.accessAuxiOne(HttpMethod.PUT, http_address, urlParameters);
	}
	
	/**
	 * Add a member inside one group
	 * @param id Identification number of the group
	 * @param login Login of a member to add in the group
	 * @param updator Updator user which updated the group
	 * @return The Group object after the update
	 * @throws APIException Exception containing the error message coming from the Group service
	 */
	public Group addMemberToGroup(String id, String login, String updator) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);
		CheckAttributes.isEmptyThrowsError(login);

		CheckAttributes.checkRegexThrowsError(id);

		String http_address=URL+"/api/group/"+id+"/member";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("login", login));
		urlParameters.add(new BasicNameValuePair("updator", updator));
		return this.accessAuxiOne(HttpMethod.POST, http_address, urlParameters);
	}

	/**
	 * Remove a member inside one group
	 * @param id Identification number of the group
	 * @param login Login of a member to remove in the group
	 * @param updator Updator user which updated the group
	 * @return The Group object after the update
	 * @throws APIException Exception containing the error message coming from the Group service
	 */
	public Group removeMemberToGroup(String id, String login, String updator) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);
		CheckAttributes.isEmptyThrowsError(login);

		CheckAttributes.checkRegexThrowsError(id);

		String http_address=URL+"/api/group/"+id+"/member";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("login", login));
		urlParameters.add(new BasicNameValuePair("updator", updator));
		return this.accessAuxiOne(HttpMethod.PUT, http_address, urlParameters);
	}

	/**
	 * Add a responsible inside one group
	 * @param id Identification number of the group
	 * @param login Login of a responsible to add in the group
	 * @param responsability Responsability of the person
	 * @param updator Updator user which updated the group
	 * @return The Group object after the update
	 * @throws APIException Exception containing the error message coming from the Group service
	 */
	public Group addResponsibleToGroup(String id, String login, String responsability, String updator) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);
		CheckAttributes.isEmptyThrowsError(login);
		CheckAttributes.isEmptyThrowsError(responsability);

		CheckAttributes.checkRegexThrowsError(id);

		String http_address=URL+"/api/group/"+id+"/responsible";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("login", login));
		urlParameters.add(new BasicNameValuePair("responsability", responsability));
		urlParameters.add(new BasicNameValuePair("updator", updator));
		return this.accessAuxiOne(HttpMethod.POST, http_address, urlParameters);
	}

	/**
	 * Remove a member inside one group
	 * @param id Id of the group
	 * @param login Login of a responsible to remove in the group
	 * @param responsability Responsability of the person
	 * @param updator Updator user which updated the group
	 * @return The Group object after the update
	 * @throws APIException Exception containing the error message coming from the Group service
	 */	public Group removeResponsibleToGroup(String id, String login, String responsability, String updator) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);
		CheckAttributes.isEmptyThrowsError(login);
		CheckAttributes.isEmptyThrowsError(responsability);

		CheckAttributes.checkRegexThrowsError(id);

		String http_address=URL+"/api/group/"+id+"/responsible";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("login", login));
		urlParameters.add(new BasicNameValuePair("responsability", responsability));
		urlParameters.add(new BasicNameValuePair("updator", updator));
		return this.accessAuxiOne(HttpMethod.PUT, http_address, urlParameters);
	}

	// HTTP DELETE request
	/**
	 * Delete one specific group
	 * @param id Identification number of the group
	 * @return The Group object removed
	 * @throws APIException Exception containing the error message coming from the Group service
	 */
	public Group deleteOneGroup(String id) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);

		CheckAttributes.checkRegexThrowsError(id);

		String http_address=URL+"/api/group/"+id;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.DELETE, http_address, null);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Auxilary functions for webservice clients
	/**
	 * Get one group
	 * @param method The HTTP method chosen to interact with the server
	 * @param http_address The URL address of the Group service (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @return One Group object
	 * @throws APIException Exception containing the error message coming from the Group service
	 */
	protected Group accessAuxiOne(HttpMethod method, String http_address, List<NameValuePair> urlParameters) throws APIException{
		try{
			Group Group = (Group)this.requestOne(method, Group.class, http_address, urlParameters, null);
			if(Group==null || Group.isEmpty()){
				throw new APIException();
			}
			return Group;
		}
		catch (APIException e){
			throw e;
		}
	}

	/**
	 * Get cache information
	 * @param method The HTTP method chosen to interact with the server
	 * @param http_address The URL address of the Group service (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @return The Cache object which describes the information about the last updates
	 * @throws APIException Exception containing the error message coming from the Group service
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
	 * Get several groups
	 * @param method The HTTP method chosen to interact with the server
	 * @param http_address The URL address of the Group service (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @return Several group objects
	 * @throws APIException Exception containing the error message coming from the Group service
	 */
	protected Vector<Group> accessAuxiSeveral(HttpMethod method, String http_address, List<NameValuePair> urlParameters) throws APIException{
		try{
    		Vector<APIObject>	vector_auxi 	= this.requestSeveral(method, Group.class, http_address, urlParameters, null);
    		Vector<Group>		vector_return	= new Vector<Group>();

    		for(APIObject obj:vector_auxi){
    			Group objDB = (Group)obj;
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