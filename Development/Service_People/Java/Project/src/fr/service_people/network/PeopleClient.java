package fr.service_people.network;

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
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import fr.cache.object.Cache;
import fr.service_people.object.Person;
import fr.webservicecore.network.HttpMethod;
import fr.webservicecore.network.WebServiceClient;
import fr.webservicecore.error.APIException;
import fr.webservicecore.object.APIObject;
import fr.webservicecore.toolbox.AttributesTool;

/** 
 * Client to manage people in the People service
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class PeopleClient extends WebServiceClient
{ 	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Person FUNCTIONS
    // HTTP GET requests
	/**
	 * Get the list of the people
	 * @return An array of all People objects
	 * @throws APIException Exception containing the error message coming from the People service
	 */
    public Vector<Person> getAllPeople() throws APIException{
    	String http_address=this.getURL()+"/api/people";
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		return this.accessAuxiSeveral(HttpMethod.GET, http_address, null);
    }

    /**
	 * Get information of one specific person by his/her id
	 * @param id Identification number of the person
	 * @return The People object whose its id is the one requested
	 * @throws APIException Exception containing the error message coming from the People service
	 */
	public Person getOnePersonById(String id) throws APIException{
		AttributesTool.isEmptyThrowsError(id);

		AttributesTool.checkRegexThrowsError(id);

		String http_address=this.getURL()+"/api/people/"+id;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }

    /**
	 * Get information of one specific person by his/her login
	 * @param login Login of the person
	 * @return The People object whose its login is the one requested
	 * @throws APIException Exception containing the error message coming from the People service
	 */
	public Person getOnePersonByLogin(String login) throws APIException{
		AttributesTool.isEmptyThrowsError(login);

		AttributesTool.checkRegexThrowsError(login);

		String http_address=this.getURL()+"/api/people/login/"+login;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }

	/**
	 * Get information of one specific person by his/her mail
	 * @param mail Mail of the person
	 * @return The People object whose its mail is the one requested
	 * @throws APIException Exception containing the error message coming from the People service
	 */
	public Person getOnePersonByMail(String mail) throws APIException{
		AttributesTool.isEmptyThrowsError(mail);

		AttributesTool.checkRegexThrowsError(mail);

		String http_address=this.getURL()+"/api/people/mail/"+mail;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }

	/**
	 * Get information about the last updates of the people list
	 * @return The Cache object which describes the information about the last updates
	 * @throws APIException Exception containing the error message coming from the People service
	 */
	public Cache getCacheInformation() throws APIException{
		String http_address=this.getURL()+"/api/people?action=get-cache-infos";
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="&token="+this.getToken();
		}

		return this.accessAuxiCache(HttpMethod.GET, http_address, null);
    }


	// HTTP POST request
	/**
	 * Create one person on the Node JS server
	 * @param login Login of the person
	 * @param lastname Lastname of the person
	 * @param firstname Firstname of the person
	 * @param sex Sex of the person
	 * @param birthdate Birthdate of the person
	 * @param major Flag to define if the person is aged 18 or more
	 * @param mail Mail of the person
	 * @param tel Phone number of the person
	 * @param picture Picture path of the person
	 * @param creator Creator user which created the person
	 * @return The People object created
	 * @throws APIException Exception containing the error message coming from the People service
	 */
	public Person createPerson(	String login,	String lastname, String firstname, String sex,
								Date birthdate, boolean major, 
								String mail, 	 String tel,	   String picture, 	 
								String creator) throws APIException{
		AttributesTool.isEmptyThrowsError(login);
		AttributesTool.isEmptyThrowsError(lastname);
		AttributesTool.isEmptyThrowsError(firstname);

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

		String http_address=this.getURL()+"/api/people";
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		return this.accessAuxiOne(HttpMethod.POST, http_address, urlParameters);
	}


	// HTTP PUT request	
	/**
	 * Update one person on the Node JS server
	 * @param id Identification number of the person
	 * @param login Login of the person
	 * @param lastname Lastname of the person
	 * @param firstname Firstname of the person
	 * @param sex Sex of the person
	 * @param birthdate Birthdate of the person
	 * @param major Flag to define if the person is aged 18 or more
	 * @param mail Mail of the person
	 * @param tel Phone number of the person
	 * @param picture Picture path of the person
	 * @param updator Updator user which updated the person
	 * @return The People object after the update
	 * @throws APIException Exception containing the error message coming from the People service
	 */
	public Person updatePerson(	String id, 		
								String login,	String lastname, String firstname, String sex,
								Date birthdate, boolean major, 
								String mail, 	 String tel,	   String picture,
								String updator) throws APIException{
		AttributesTool.isEmptyThrowsError(id);
		AttributesTool.isEmptyThrowsError(login);
		AttributesTool.isEmptyThrowsError(lastname);
		AttributesTool.isEmptyThrowsError(firstname);

		AttributesTool.checkRegexThrowsError(id);

		String http_address=this.getURL()+"/api/people/"+id;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
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

	/**
	 * Associate a tag to one person
	 * @param id Identification number of the person
	 * @param tag Tag to add in the tag list of the person
	 * @param updator Updator user which updated the person
	 * @return The People object after the update
	 * @throws APIException Exception containing the error message coming from the People service
	 */
	public Person addTagToPerson(String id, String tag, String updator) throws APIException{
		AttributesTool.isEmptyThrowsError(id);
		AttributesTool.isEmptyThrowsError(tag);

		AttributesTool.checkRegexThrowsError(id);
		AttributesTool.checkRegexThrowsError(tag);

		String http_address=this.getURL()+"/api/people/"+id+"/tag/"+tag;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("updator", updator));
		return this.accessAuxiOne(HttpMethod.POST, http_address, urlParameters);
	}

	/**
	 * Remove a tag to one person
	 * @param id Identification number of the person
	 * @param tag Tag to remove in the tag list of the person
	 * @param updator Updator user which updated the person
	 * @return The People object after the update
	 * @throws APIException Exception containing the error message coming from the People service
	 */
	public Person removeTagToPerson(String id, String tag, String updator) throws APIException{
		AttributesTool.isEmptyThrowsError(id);
		AttributesTool.isEmptyThrowsError(tag);

		AttributesTool.checkRegexThrowsError(id);
		AttributesTool.checkRegexThrowsError(tag);

		String http_address=this.getURL()+"/api/people/"+id+"/tag/"+tag;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		List<NameValuePair> urlParameters=new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("updator", updator));
		return this.accessAuxiOne(HttpMethod.PUT, http_address, urlParameters);
	}

	// HTTP DELETE request
	/**
	 * Delete one specific person
	 * @param id Identification number of the person
	 * @return The Person object removed
	 * @throws APIException Exception containing the error message coming from the Person service
	 */
	public Person deleteOnePerson(String id) throws APIException{
		AttributesTool.isEmptyThrowsError(id);

		AttributesTool.checkRegexThrowsError(id);

		String http_address=this.getURL()+"/api/people/"+id;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}

		return this.accessAuxiOne(HttpMethod.DELETE, http_address, null);
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Auxilary functions for webservice clients
	/**
	 * Get one people
	 * @param method The HTTP method chosen to interact with the server
	 * @param http_address The URL address of the People service (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @return One People object
	 * @throws APIException Exception containing the error message coming from the People service
	 */
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

	/**
	 * Get cache information
	 * @param method The HTTP method chosen to interact with the server
	 * @param http_address The URL address of the People service (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @return The Cache object which describes the information about the last updates
	 * @throws APIException Exception containing the error message coming from the People service
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
	 * Get several people
	 * @param method The HTTP method chosen to interact with the server
	 * @param http_address The URL address of the People service (used for all HTTP method)
	 * @param urlParameters The list of parameters to send (used for POST and PUT HTTP methods)
	 * @return Several people objects
	 * @throws APIException Exception containing the error message coming from the People service
	 */
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