package fr.applicationcore.junit.network;

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


import java.util.Vector;

import fr.applicationcore.junit.object.ObjectDB;
import fr.applicationcore.network.HttpMethod;
import fr.applicationcore.network.ApplicationClient;
import fr.applicationcore.error.APIException;
import fr.applicationcore.object.APIObject;

/**
 * Example of application client to test NodeJS module named 'ApplicationCore'
 * <p>
 * This class uses directly HttpRequest class by its extension of the ApplicationClient class
 * The protected functions are used as auxiliary functions to be reused by an extension of it
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class ObjectDBClient extends ApplicationClient
{
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//ObjectDB FUNCTIONS
    // HTTP GET requests

	/**
     * Get the list of all the objectDB elements
     * @return The objects found
	 * @throws APIException Exception returned by the application
     */
    public Vector<ObjectDB> getAllObjectDB() throws APIException{
		String http_address=this.getURL()+"/api/object";
		return getAllObjectDBAuxi(http_address);
    }
    protected Vector<ObjectDB> getAllObjectDBAuxi(String http_address) throws APIException{
    	try{
    		Vector<APIObject>	vector_auxi 	= this.requestSeveral(HttpMethod.GET, ObjectDB.class, http_address, null, null);
    		Vector<ObjectDB>	vector_return	= new Vector<ObjectDB>();

    		for(APIObject obj:vector_auxi){
    			ObjectDB objDB = (ObjectDB)obj;
    			vector_return.add(objDB);
    		}
    		return vector_return;
        }
        catch (APIException e){
        	throw e;
        }
    }

    /**
     * Get information of one specific objectDB element
     * @param id The id of the expected object
     * @return The object found
	 * @throws APIException Exception returned by the application
     */
	public ObjectDB getOneObjectDB(String id) throws APIException{
		if(id==null || id.equals("")){
			return null;
		}
		String http_address=this.getURL()+"/api/object/"+id;
		return getOneObjectDBAuxi(http_address);
	}
	protected ObjectDB getOneObjectDBAuxi(String http_address) throws APIException{
		try{
			ObjectDB objectDB = (ObjectDB)this.requestOne(HttpMethod.GET, ObjectDB.class, http_address, null, null);
			return objectDB;
        }
		catch (APIException e){
        	throw e;
        }
    }
	
	// HTTP POST request
	
	/**
     * Create an objectDB element on the Node JS server
     * @return The object created
	 * @throws APIException Exception returned by the application
     */
	public ObjectDB createObjectDB() throws APIException{
		String http_address=this.getURL()+"/api/object";
		return this.createObjectDBAuxi(http_address);
	}
	public ObjectDB createObjectDBAuxi(String http_address) throws APIException{
		try{
			ObjectDB objectDB = (ObjectDB)this.requestOne(HttpMethod.POST, ObjectDB.class, http_address, null, null);
			return objectDB;
        }
		catch (APIException e){
        	throw e;
        }
	}

	// HTTP PUT request
	
	/**
     * Update all the objectDB elements on the Node JS server
	 * @throws APIException Exception returned by the application
     */
	public void updateObjectDB() throws APIException{
		String http_address=this.getURL()+"/api/object";
		this.updateObjectDBAuxi(http_address);
	}
	protected void updateObjectDBAuxi(String http_address) throws APIException{
		try{
			this.requestZero(HttpMethod.PUT, ObjectDB.class, http_address, null, null);
			return;
        }
		catch (APIException e){
        	throw e;
        }
	}	

	// HTTP DELETE request
	
	/**
     * Delete all objectDB elements
	 * @throws APIException Exception returned by the application
     */
	public void deleteObjectDB() throws APIException{
		String http_address=this.getURL()+"/api/object";
		this.deleteObjectDBAuxi(http_address);
	}
	protected void deleteObjectDBAuxi(String http_address) throws APIException{
		try{
			this.requestZero(HttpMethod.DELETE, ObjectDB.class, http_address, null, null);
        	return;
        }
		catch (APIException e){
        	throw e;
        }
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
}