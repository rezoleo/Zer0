package fr.applicationcore.junit.network;

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

import fr.applicationcore.junit.object.ObjectDB;
import fr.applicationcore.object.APIException;

/* 
 * Class 	: ObjectDBTokenClient
 * Author(s): Zidmann
 * Function : This class contains an example of application client to test NodeJS module named 'ApplicationCore' using the token transmission
 * Version  : 1.0.0
 * Note		: This class extends the ObjectDBClient to adapt the HTTP address reached by the client
 */
public class ObjectDBTokenClient extends ObjectDBClient
{
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//ObjectDB FUNCTIONS
    // HTTP GET requests

	// Name        : getAllObjectDB
    // Type        : function
    // Description : Get the list of all the objectDB elements
    public Vector<ObjectDB> getAllObjectDB() throws APIException{
    	String http_address=URL+"/api/objectToken";
		return getAllObjectDBAuxi(http_address);
    }

    // Name        : getOneObjectDB
    // Type        : function
    // Description : Get information of one specific objectDB element
	public ObjectDB getOneObjectDB(String id) throws APIException{
		if(id==null || id.equals("")){
			return null;
		}
		String http_address=URL+"/api/objectToken/"+id;
		return getOneObjectDBAuxi(http_address);
    }


	// HTTP POST request

	// Name        : createObjectDB
    // Type        : function
    // Description : Create an objectDB element on the Node JS server
	public ObjectDB createObjectDB() throws APIException{
		String http_address=URL+"/api/objectToken";
		return this.createObjectDBAuxi(http_address);
	}

	// Name        : updateObjectDB
    // Type        : function
    // Description : Update all the objectDB elements on the Node JS server
	public void updateObjectDB() throws APIException{
		String http_address=URL+"/api/objectToken";
		this.updateObjectDBAuxi(http_address);
	}


	// HTTP DELETE request

	// Name        : deleteObjectDB
	// Type        : function
	// Description : Delete all objectDB elements
	public void deleteObjectDB() throws APIException{
		String http_address=URL+"/api/objectToken";
		this.deleteObjectDBAuxi(http_address);
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
}