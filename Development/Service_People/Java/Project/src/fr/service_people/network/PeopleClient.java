package fr.service_people.network;

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
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import fr.cache.object.Cache;
import fr.service_people.object.Person;
import fr.webservicecore.network.HttpMethod;
import fr.webservicecore.network.WebServiceClient;
import fr.webservicecore.object.APIException;
import fr.webservicecore.object.APIObject;
import fr.webservicecore.toolbox.CheckAttributes;

/* Class 	: PeopleClient
 * Function : This class contains the WebService Client to manage people in NodeJS server. 
 * Note		: This class uses directly HttpRequest class
 */
public class PeopleClient extends WebServiceClient
{ 	
	protected DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Person FUNCTIONS
    // HTTP GET requests

	// Name        : getAllPeople
    // Type        : function
    // Description : Get the list of all the people
    public Vector<Person> getAllPeople() throws APIException{
    	String http_address=URL+"/api/people";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiSeveral(HttpMethod.GET, http_address, null);
    }

    // Name        : getOnePersonById
    // Type        : function
    // Description : Get information of one specific person by his/her id
	public Person getOnePersonById(String id) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);

		CheckAttributes.checkRegexThrowsError(id);

		String http_address=URL+"/api/people/"+id;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }

    // Name        : getOnePersonByLogin
    // Type        : function
    // Description : Get information of one specific person by his/her login
	public Person getOnePersonByLogin(String login) throws APIException{
		CheckAttributes.isEmptyThrowsError(login);

		CheckAttributes.checkRegexThrowsError(login);

		String http_address=URL+"/api/people/login/"+login;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }

    // Name        : getOnePersonByMail
    // Type        : function
    // Description : Get information of one specific person by his/her mail
	public Person getOnePersonByMail(String mail) throws APIException{
		CheckAttributes.isEmptyThrowsError(mail);

		CheckAttributes.checkRegexThrowsError(mail);

		String http_address=URL+"/api/people/mail/"+mail;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }

    // Name        : getCacheInformation
    // Type        : function
    // Description : Get information about the last updates of the people list
	public Cache getCacheInformation() throws APIException{
		String http_address=URL+"/api/people?action=get-cache-infos";
		if(this.token!=null && !this.token.equals("")){
			http_address+="&token="+this.token;
		}

		return this.accessAuxiCache(HttpMethod.GET, http_address, null);
    }


	// HTTP POST request

	// Name        : createPerson
    // Type        : function
    // Description : Create one person on the Node JS server
	public Person createPerson(	String login,	String lastname, String firstname, String sex,
								Date birthdate, boolean major, 
								String mail, 	 String tel,	   String picture, 	 
								String creator) throws APIException{
		CheckAttributes.isEmptyThrowsError(login);
		CheckAttributes.isEmptyThrowsError(lastname);
		CheckAttributes.isEmptyThrowsError(firstname);

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("login",     login));
		urlParameters.add(new BasicNameValuePair("lastname",  lastname));
		urlParameters.add(new BasicNameValuePair("firstname", firstname));
		urlParameters.add(new BasicNameValuePair("sex",		  sex));
		if(birthdate==null){
			urlParameters.add(new BasicNameValuePair("birthdate", null));
		}
		else{
			urlParameters.add(new BasicNameValuePair("birthdate", df.format(birthdate)));
		}
		urlParameters.add(new BasicNameValuePair("major",     major ? "true":"false"));
		urlParameters.add(new BasicNameValuePair("mail",      mail));
		urlParameters.add(new BasicNameValuePair("tel",       tel));
		urlParameters.add(new BasicNameValuePair("picture",   picture));
		urlParameters.add(new BasicNameValuePair("creator",   creator));

		String http_address=URL+"/api/people";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.POST, http_address, urlParameters);
	}


	// HTTP PUT request	

	// Name        : updatePerson
    // Type        : function
    // Description : Update one person on the Node JS server
	public Person updatePerson(	String id, 		
								String login,	String lastname, String firstname, String sex,
								Date birthdate, boolean major, 
								String mail, 	 String tel,	   String picture,
								String updator) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);
		CheckAttributes.isEmptyThrowsError(login);
		CheckAttributes.isEmptyThrowsError(lastname);
		CheckAttributes.isEmptyThrowsError(firstname);

		CheckAttributes.checkRegexThrowsError(id);

		String http_address=URL+"/api/people/"+id;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("login",     login));
		urlParameters.add(new BasicNameValuePair("lastname",  lastname));
		urlParameters.add(new BasicNameValuePair("firstname", firstname));
		urlParameters.add(new BasicNameValuePair("sex",		  sex));
		if(birthdate==null){
			urlParameters.add(new BasicNameValuePair("birthdate", null));
		}
		else{
			urlParameters.add(new BasicNameValuePair("birthdate", df.format(birthdate)));
		}
		urlParameters.add(new BasicNameValuePair("major",     major ? "true":"false"));
		urlParameters.add(new BasicNameValuePair("mail",      mail));
		urlParameters.add(new BasicNameValuePair("tel",       tel));
		urlParameters.add(new BasicNameValuePair("picture",   picture));
		urlParameters.add(new BasicNameValuePair("updator",   updator));

		return this.accessAuxiOne(HttpMethod.PUT, http_address, urlParameters);
	}

	// Name        : addTagToPerson
    // Type        : function
    // Description : Associate a tag to one person
	public Person addTagToPerson(String id, String tag, String updator) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);
		CheckAttributes.isEmptyThrowsError(tag);

		CheckAttributes.checkRegexThrowsError(id);
		CheckAttributes.checkRegexThrowsError(tag);

		String http_address=URL+"/api/people/"+id+"/tag/"+tag;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("updator", updator));
		return this.accessAuxiOne(HttpMethod.POST, http_address, urlParameters);
	}

	// Name        : removeTagToPerson
    // Type        : function
    // Description : Remove a tag to one person
	public Person removeTagToPerson(String id, String tag, String updator) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);
		CheckAttributes.isEmptyThrowsError(tag);

		CheckAttributes.checkRegexThrowsError(id);
		CheckAttributes.checkRegexThrowsError(tag);

		String http_address=URL+"/api/people/"+id+"/tag/"+tag;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("updator", updator));
		return this.accessAuxiOne(HttpMethod.PUT, http_address, urlParameters);
	}

	// HTTP DELETE request

	// Name        : deleteOnePerson
	// Type        : function
	// Description : Delete one specific person
	public Person deleteOnePerson(String id) throws APIException{
		CheckAttributes.isEmptyThrowsError(id);

		CheckAttributes.checkRegexThrowsError(id);

		String http_address=URL+"/api/people/"+id;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}

		return this.accessAuxiOne(HttpMethod.DELETE, http_address, null);
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Auxilary functions for webservice clients
	protected Person accessAuxiOne(HttpMethod method, String http_address, List<NameValuePair> urlParameters) throws APIException{
		try{
			Person person = (Person)this.requestOne(method, Person.class, http_address, urlParameters, null);
			if(person==null || person.isEmpty()){
				throw new APIException();
			}
			return person;
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

	protected Vector<Person> accessAuxiSeveral(HttpMethod method, String http_address, List<NameValuePair> urlParameters) throws APIException{
		try{
    		Vector<APIObject>	vector_auxi 	= this.requestSeveral(method, Person.class, http_address, urlParameters, null);
    		Vector<Person>		vector_return	= new Vector<Person>();

    		for(APIObject obj:vector_auxi){
    			Person objDB = (Person)obj;
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