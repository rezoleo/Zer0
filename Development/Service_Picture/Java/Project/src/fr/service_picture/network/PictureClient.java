package fr.service_picture.network;

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


import java.io.File;

import fr.service_picture.object.PictureInformation;
import fr.webservicecore.network.HttpMethod;
import fr.webservicecore.network.WebServiceClient;
import fr.webservicecore.object.APIException;
import fr.webservicecore.toolbox.CheckAttributes;

/* 
 * Class 	: PictureClient
 * Function : This class contains the WebService Client to manage pictures in NodeJS server. 
 * Note		: This class uses directly HttpRequest class
 * NoteBis  : To store the pictures of a service, be sure that you created the directory in the storage whose name is the service name
 */
public class PictureClient extends WebServiceClient
{ 	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Picture FUNCTIONS
    // HTTP GET requests

    // Name        : getPictureInformation
    // Type        : function
    // Description : Get information of one specific Picture by it filename and the directory where it is contained
	public PictureInformation getPictureInformation(String directory, String filename) throws APIException{
		CheckAttributes.isEmptyThrowsError(directory);
		CheckAttributes.isEmptyThrowsError(filename);

		CheckAttributes.checkRegexThrowsError(directory);
		CheckAttributes.checkRegexThrowsError(filename);

		return getPictureInformation(directory+"/"+filename);
    }

	// Name        : getPictureInformation
    // Type        : function
    // Description : Get information of one specific Picture by it filepath
	public PictureInformation getPictureInformation(String filepath) throws APIException{
		CheckAttributes.isEmptyThrowsError(CheckAttributes.deleteFirstBackslash(filepath));

		CheckAttributes.checkRegexThrowsError(CheckAttributes.deleteFirstBackslash(filepath));

		if(filepath==null || filepath.equals("")){
			return null;
		}

		String http_address=URL+"/api/file/"+filepath+"?action=get-cache-infos";
		if(this.token!=null && !this.token.equals("")){
			http_address+="&token="+this.token;
		}
		return this.accessAuxiOne(HttpMethod.GET, http_address, null);
    }


	// HTTP POST request

	// Name        : sendPicture
    // Type        : function
    // Description : Create one Picture on the Node JS server in a specific directory
	public PictureInformation sendPicture(String directory, File file) throws APIException{
		return this.sendPicture(directory, file, null);
	}
	public PictureInformation sendPicture(String directory, File file, String extension) throws APIException{
		CheckAttributes.isEmptyThrowsError(directory);

		CheckAttributes.checkRegexThrowsError(directory);

		if(file==null){
			return null;
		}

		String http_address=URL+"/api/file/"+directory;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}
		if(extension!=null && !extension.equals("")){
			if(this.token!=null && !this.token.equals("")){
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

	// Name        : deleteOnePicture
	// Type        : function
	// Description : Delete one specific Picture by it filename and the directory where it is contained
	public PictureInformation deleteOnePicture(String directory, String filename) throws APIException{
		CheckAttributes.isEmptyThrowsError(directory);
		CheckAttributes.isEmptyThrowsError(filename);

		CheckAttributes.checkRegexThrowsError(directory);
		CheckAttributes.checkRegexThrowsError(filename);

		return deleteOnePicture(directory+"/"+filename);
	}

	// Name        : deleteOnePicture
	// Type        : function
	// Description : Delete of one specific Picture by it filepath
	public PictureInformation deleteOnePicture(String filepath) throws APIException{
		CheckAttributes.isEmptyThrowsError(CheckAttributes.deleteFirstBackslash(filepath));

		CheckAttributes.checkRegexThrowsError(CheckAttributes.deleteFirstBackslash(filepath));

		String http_address=URL+"/api/file/"+filepath;
		if(this.token!=null && !this.token.equals("")){
			http_address+="?token="+this.token;
		}
		return this.accessAuxiOne(HttpMethod.DELETE, http_address, null);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Auxilary function for webservice clients
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