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


import fr.applicationcore.junit.object.Gate;
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
public class GateClient extends ApplicationClient
{
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Gate FUNCTIONS
    // HTTP GET requests

	/**
     * Check the gate used by user
     * @return The gate found
	 * @throws APIException Exception returned by the application
     */
    public Gate checkGate() throws APIException{
		String http_address=this.getURL()+"/api/gate";
		return checkGateAuxi(http_address);
    }
    protected Gate checkGateAuxi(String http_address) throws APIException{
    	try{
    		Gate gate = (Gate)this.requestOne(HttpMethod.GET, Gate.class, http_address, null, null);
			return gate;
        }
        catch (APIException e){
        	throw e;
        }
    }
    
    /**
     * Get a gate for the user
     * @return The gate found
	 * @throws APIException Exception returned by the application
     */
    public Gate getGate() throws APIException{
		String http_address=this.getURL()+"/api/gate";
		return getGateAuxi(http_address);
    }
    protected Gate getGateAuxi(String http_address) throws APIException{
    	try{
    		Gate gate = (Gate)this.requestOne(HttpMethod.POST, Gate.class, http_address, null, null);
			return gate;
        }
        catch (APIException e){
        	throw e;
        }
    }

    /**
     * Remove the gate used by user
     * @return The gate deleted
	 * @throws APIException Exception returned by the application
     */
    public Gate eraseGate() throws APIException{
		String http_address=this.getURL()+"/api/gate";
		return eraseGateAuxi(http_address);
    }
    protected Gate eraseGateAuxi(String http_address) throws APIException{
    	try{
    		Gate gate = (Gate)this.requestOne(HttpMethod.DELETE, Gate.class, http_address, null, null);
			return gate;
        }
        catch (APIException e){
        	throw e;
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
}