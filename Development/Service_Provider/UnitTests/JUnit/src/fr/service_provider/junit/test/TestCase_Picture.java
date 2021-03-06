package fr.service_provider.junit.test;

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

import org.junit.Test;

import fr.service_provider.junit.model.TestCase_Model;
import fr.service_picture.object.PictureInformation;

/**
 * This class contains the webService client JUnit tests to check if there was no regression in Picture service for People service
 * <p>
 * This Test Case supposes that you started the NodeJS server and removed all the Picture elements in the MongoDB database
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_Picture extends TestCase_Model
{
	/**
	 * Testing all the student contributors APIs for usual actions 
	 * @throws Exception Exception returned by the system
	 */
	@Test 
	public void testPictureClientAPI() throws Exception{	
		initSettings();

		File fileImg = new File(path+"file1.png");

		PictureInformation pictureImg = ws_client_picture.sendPicture(this.directory, fileImg);
		assertNotNull(pictureImg);
		assertNotNull(pictureImg.getChecksum());
		assertNotNull(pictureImg.getFile_path());

		String path1 = pictureImg.getFile_path();

		assertEquals(this.directory, extractDirectory(path1));
		assertEquals(pictureImg, ws_client_picture_proxy.getPictureInformation(path1));
		assertEquals(pictureImg, ws_client_picture.deleteOnePicture(extractDirectory(path1), extractFilename(path1)));
	}


	// Auxilary functions to extract the directory and the filename with the complete filepath
	protected String extractDirectory(String filepath){
		int		pos = filepath.lastIndexOf('/');
		return filepath.substring(0, pos);
	}

	protected String extractFilename(String filepath){
		int		pos = filepath.lastIndexOf('/');
		return filepath.substring(pos+1);
	}
}