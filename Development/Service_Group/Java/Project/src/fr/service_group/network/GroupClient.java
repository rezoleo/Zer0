package fr.service_group.network;

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


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import fr.cache.object.Cache;
import fr.service_group.object.Group;
import fr.webservicecore.network.HttpMethod;
import fr.webservicecore.network.WebServiceClient;
import fr.webservicecore.object.APIException;
import fr.webservicecore.object.APIObject;
import fr.webservicecore.toolbox.CheckAttributes;

/* Class 	: GroupClient
 * Author(s): Zidmann
 * Function : This class contains the WebService Client to manage group in NodeJS server. 
 * Version  : 1.0.0
 * Note		: This class uses directly HttpRequest class
 */
public class GroupClient extends WebServiceClient
{ 	
	protected DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Group FUNCTIONS
    // HTTP GET requests

	// Name        : getAllGroup
    // Type        : function
    // Description : Get the list of all the group
    public Vector<Group> getAllGroup() throws APIException{
    	String http_address=URL+"/api/group";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiSeveral(HttpMethod.GET, http_address, null);
    }

    // Name        : getOneGroupById
    // Type        : function
    // Description : Get information of one specific group by its id
	public Group getOneGroupById(String id) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);

		CheckAttributes.checkRegexThrowsError(id);

		String http_address=URL+"/api/group/"+id;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }

    // Name        : getOneGroupByName
    // Type        : function
    // Description : Get information of one specific group by its name
	public Group getOneGroupByName(String name) throws APIException{
		CheckAttributes.isEmptyThrowsError(name);

		CheckAttributes.checkRegexThrowsError(name);

		String http_address=URL+"/api/group/name/"+name;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }

    // Name        : searchByLogin
    // Type        : function
    // Description : Get information of all the groups which are belonged by a specific login
	public Vector<Group> searchByLogin(String login) throws APIException{
		CheckAttributes.isEmptyThrowsError(login);

		CheckAttributes.checkRegexThrowsError(login);

		String http_address=URL+"/api/group/search/"+login;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiSeveral(HttpMethod.GET, http_address, null);
    }

	// Name        : getCacheInformation
    // Type        : function
    // Description : Get information about the last updates of the group list
	public Cache getCacheInformation() throws APIException{
		String http_address=URL+"/api/group?action=get-cache-infos";
		if(this.token!=null && !this.token.equals("")){
			http_address+="&token="+this.token;
		}

		return this.accessAuxiCache(HttpMethod.GET, http_address, null);
    }

	// HTTP POST request

	// Name        : createGroup
    // Type        : function
    // Description : Create one group on the Node JS server
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

	// Name        : updateGroup
    // Type        : function
    // Description : Update one group on the Node JS server
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
	
	// Name        : addMemberToGroup
    // Type        : function
    // Description : Add a member inside one group
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

	// Name        : removeMemberToGroup
    // Type        : function
    // Description : Remove a member inside one group
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

	// Name        : addResponsibleToGroup
    // Type        : function
    // Description : Add a responsible inside one group
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

	// Name        : removeResponsibleToGroup
    // Type        : function
    // Description : Remove a responsible inside one group
	public Group removeResponsibleToGroup(String id, String login, String responsability, String updator) throws APIException{
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

	// Name        : deleteOneGroup
	// Type        : function
	// Description : Delete one specific group
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