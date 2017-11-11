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


import fr.applicationcore.junit.object.Login;
import fr.applicationcore.network.HttpMethod;
import fr.applicationcore.network.ApplicationClient;
import fr.applicationcore.error.APIException;

/**
 * Example of application client to test NodeJS module named 'ApplicationCore'
 * <p>
 * This class uses directly HttpRequest class by its extension of the ApplicationClient class
 * The protected functions are used as auxiliary functions to be reused by an extension of it
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class LoginClient extends ApplicationClient
{
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Login FUNCTIONS
    // HTTP GET requests

	// Name        : checkLogin
    // Type        : function
    // Description : Check the login used by user
    public Login checkLogin() throws APIException{
		String http_address=this.getURL()+"/api/login";
		return checkLoginAuxi(http_address);
    }
    protected Login checkLoginAuxi(String http_address) throws APIException{
    	try{
    		Login login = (Login)this.requestOne(HttpMethod.GET, Login.class, http_address, null, null);
			return login;
        }
        catch (APIException e){
        	throw e;
        }
    }

    // Name        : getLogin
    // Type        : function
    // Description : Get a login for the user
    public Login getLogin() throws APIException{
		String http_address=this.getURL()+"/api/login";
		return getLoginAuxi(http_address);
    }
    protected Login getLoginAuxi(String http_address) throws APIException{
    	try{
    		Login login = (Login)this.requestOne(HttpMethod.POST, Login.class, http_address, null, null);
			return login;
        }
        catch (APIException e){
        	throw e;
        }
    }

    // Name        : eraseLogin
    // Type        : function
    // Description : Remove the login used by user
    public Login eraseLogin() throws APIException{
		String http_address=this.getURL()+"/api/login";
		return eraseLoginAuxi(http_address);
    }
    protected Login eraseLoginAuxi(String http_address) throws APIException{
    	try{
    		Login login = (Login)this.requestOne(HttpMethod.DELETE, Login.class, http_address, null, null);
			return login;
        }
        catch (APIException e){
        	throw e;
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
}