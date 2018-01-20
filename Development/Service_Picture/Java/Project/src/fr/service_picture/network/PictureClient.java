package fr.service_picture.network;

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


import java.io.File;

import fr.service_picture.object.PictureInformation;
import fr.webservicecore.network.HttpMethod;
import fr.webservicecore.network.WebServiceClient;
import fr.webservicecore.error.APIException;
import fr.webservicecore.toolbox.AttributesTool;
import fr.webservicecore.toolbox.StringTool;;

/** 
 * Client to manage picture in the Picture service
 * <p>
 * To store the pictures of a service, be sure that you created the directory in the storage whose name is the service name
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class PictureClient extends WebServiceClient
{ 	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Picture FUNCTIONS
    // HTTP GET requests
    /**
	 * Get information of one specific Picture by it filename and the directory where it is contained
	 * @param directory Directory where the picture is stored
	 * @param filename Filename of the picture
	 * @return The PictureInformation object whose its id is the one requested
	 * @throws APIException Exception containing the error message coming from the Picture service
	 */
	public PictureInformation getPictureInformation(String directory, String filename) throws APIException{
		AttributesTool.isEmptyThrowsError(directory);
		AttributesTool.isEmptyThrowsError(filename);

		AttributesTool.checkRegexThrowsError(directory);
		AttributesTool.checkRegexThrowsError(filename);

		return getPictureInformation(directory+"/"+filename);
    }

	/**
	 * Get information of one specific Picture by it filepath
	 * @param filepath Path to the picture 
	 * @return The PictureInformation object whose its id is the one requested
	 * @throws APIException Exception containing the error message coming from the Picture service
	 */
	public PictureInformation getPictureInformation(String filepath) throws APIException{
		AttributesTool.isEmptyThrowsError(StringTool.deleteFirstBackslash(filepath));

		AttributesTool.checkRegexThrowsError(StringTool.deleteFirstBackslash(filepath));

		if(filepath==null || filepath.equals("")){
			return null;
		}

		String http_address=this.getURL()+"/api/file/"+filepath+"?action=get-cache-infos";
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="&token="+this.getToken();
		}
		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }


	// HTTP POST request
	/**
	 * Create one Picture on the Node JS server in a specific directory
	 * @param directory Directory where the picture is stored
	 * @param file File to create 
	 * @return The PictureInformation object created
	 * @throws APIException Exception containing the error message coming from the Picture service
	 */
	public PictureInformation sendPicture(String directory, File file) throws APIException{
		return this.sendPicture(directory, file, null);
	}
	public PictureInformation sendPicture(String directory, File file, String extension) throws APIException{
		AttributesTool.isEmptyThrowsError(directory);

		AttributesTool.checkRegexThrowsError(directory);

		if(file==null){
			return null;
		}

		String http_address=this.getURL()+"/api/file/"+directory;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}
		if(extension!=null && !extension.equals("")){
			if(this.getToken()!=null && !this.getToken().equals("")){
				http_address+="&";
			}
			else{
				http_address+="?";
			}
			http_address+="extension="+extension;
		}
		return this.accessAuxiOne(HttpMethod.POSTfile, http_address, file);
	}


	// HTTP DELETE request
	/**
	 * Delete one specific Picture by it filename and the directory where it is contained
	 * @param directory Directory where the picture is stored
	 * @param filename Filename of the picture to remove
	 * @return The PictureInformation object removed
	 * @throws APIException Exception containing the error message coming from the Picture service
	 */
	public PictureInformation deleteOnePicture(String directory, String filename) throws APIException{
		AttributesTool.isEmptyThrowsError(directory);
		AttributesTool.isEmptyThrowsError(filename);

		AttributesTool.checkRegexThrowsError(directory);
		AttributesTool.checkRegexThrowsError(filename);

		return deleteOnePicture(directory+"/"+filename);
	}

	/**
	 * Delete one specific picture
	 * @param filepath Path to the picture to remove
	 * @return The PictureInformation object removed
	 * @throws APIException Exception containing the error message coming from the Picture service
	 */
	public PictureInformation deleteOnePicture(String filepath) throws APIException{
		AttributesTool.isEmptyThrowsError(StringTool.deleteFirstBackslash(filepath));

		AttributesTool.checkRegexThrowsError(StringTool.deleteFirstBackslash(filepath));

		String http_address=this.getURL()+"/api/file/"+filepath;
		if(this.getToken()!=null && !this.getToken().equals("")){
			http_address+="?token="+this.getToken();
		}
		return this.accessAuxiOne(HttpMethod.DELETE, http_address, null);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Auxilary function for webservice clients
	/**
	 * Get one picture
	 * @param method The HTTP method chosen to interact with the server
	 * @param http_address The URL address of the Picture service (used for all HTTP method)
	 * @param file File object to send (used for POST)
	 * @return One Picture object
	 * @throws APIException Exception containing the error message coming from the Picture service
	 */
	protected PictureInformation accessAuxiOne(HttpMethod method, String http_address, File file) throws APIException{
		try{
			PictureInformation pictureInfo = (PictureInformation)this.requestOne(method, PictureInformation.class, http_address, null, file);
			if(pictureInfo==null || pictureInfo.getChecksum()==null || pictureInfo.getFile_path()==null){
				throw new APIException();
			}
			return pictureInfo;
		}
		catch (APIException e){
			throw e;
		}
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
}