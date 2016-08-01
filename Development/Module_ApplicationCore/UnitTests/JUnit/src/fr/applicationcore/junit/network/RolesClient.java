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


import fr.applicationcore.junit.object.Roles;
import fr.applicationcore.network.HttpMethod;
import fr.applicationcore.network.ApplicationClient;
import fr.applicationcore.object.APIException;

/* 
 * Class 	: RolesClient
 * Author(s): Zidmann
 * Function : This class contains an example of application client to test NodeJS module named 'ApplicationCore'
 * Version  : 1.0.0
 * Note		: This class uses directly HttpRequest class by its extension of the ApplicationClient class
 *		  	  The protected functions are used as auxiliary functions to be reused by an extension of it
 */
public class RolesClient extends ApplicationClient
{
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Roles FUNCTIONS
    // HTTP GET requests

	// Name        : checkRoles
    // Type        : function
    // Description : Check the roles used by user
    public Roles checkRoles() throws APIException{
		String http_address=URL+"/api/roles";
		return checkRolesAuxi(http_address);
    }
    protected Roles checkRolesAuxi(String http_address) throws APIException{
    	try{
    		Roles Roles = (Roles)this.requestOne(HttpMethod.GET, Roles.class, http_address, null, null);
			return Roles;
        }
        catch (APIException e){
        	throw e;
        }
    }

    // Name        : getRoles
    // Type        : function
    // Description : Get the list of roles for the user
    public Roles getRoles() throws APIException{
		String http_address=URL+"/api/roles";
		return getRolesAuxi(http_address);
    }
    protected Roles getRolesAuxi(String http_address) throws APIException{
    	try{
    		Roles Roles = (Roles)this.requestOne(HttpMethod.POST, Roles.class, http_address, null, null);
			return Roles;
        }
        catch (APIException e){
        	throw e;
        }
    }

    // Name        : eraseRoles
    // Type        : function
    // Description : Remove the roles used by user
    public Roles eraseRoles() throws APIException{
		String http_address=URL+"/api/roles";
		return eraseRolesAuxi(http_address);
    }
    protected Roles eraseRolesAuxi(String http_address) throws APIException{
    	try{
    		Roles Roles = (Roles)this.requestOne(HttpMethod.DELETE, Roles.class, http_address, null, null);
			return Roles;
        }
        catch (APIException e){
        	throw e;
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
}