package fr.webservicecore.junit.network;

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


import java.util.Vector;

import fr.webservicecore.junit.object.ObjectDB;
import fr.webservicecore.network.HttpMethod;
import fr.webservicecore.network.WebServiceClient;
import fr.webservicecore.object.APIException;
import fr.webservicecore.object.APIObject;

/* 
 * Class 	: ObjectDBClient
 * Author(s): Zidmann
 * Function : This class contains an example of WebService client to test NodeJS module named 'WebServiceCore'
 * Version  : 1.0.0
 * Note		: This class uses directly HttpRequest class by its extension of the WebService class
 *		  	  The protected functions are used as auxiliary functions to be reused by an extension of it
 */
public class ObjectDBClient extends WebServiceClient
{ 	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//ObjectDB FUNCTIONS
    // HTTP GET requests

	// Name        : getAllObjectDB
    // Type        : function
    // Description : Get the list of all the objectDB elements
    public Vector<ObjectDB> getAllObjectDB() throws APIException{
		String http_address=URL+"/api/object";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}
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

    // Name        : getOneObjectDB
    // Type        : function
    // Description : Get information of one specific objectDB element
	public ObjectDB getOneObjectDB(String id) throws APIException{
		if(id==null || id.equals("")){
			return null;
		}
		String http_address=URL+"/api/object/"+id;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}
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
	
	// Name        : createObjectDB
    // Type        : function
    // Description : Create an objectDB element on the Node JS server
	public ObjectDB createObjectDB() throws APIException{
		String http_address=URL+"/api/object";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}
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
	
	// Name        : updateObjectDB
    // Type        : function
    // Description : Update all the objectDB elements on the Node JS server
	public void updateObjectDB() throws APIException{
		String http_address=URL+"/api/object";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}
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
	
	// Name        : deleteObjectDB
	// Type        : function
	// Description : Delete all objectDB elements
	public void deleteObjectDB() throws APIException{
		String http_address=URL+"/api/object";
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}
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