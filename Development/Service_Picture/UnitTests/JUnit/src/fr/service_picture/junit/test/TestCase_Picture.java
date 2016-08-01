package fr.service_picture.junit.test;

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
import org.junit.Test;
import fr.service_picture.junit.model.TestCase_Model;
import fr.service_picture.object.PictureInformation;

/* 
 * Class 	: TestCase_Picture
 * Author(s): Zidmann
 * Function : This class contains the webService client JUnit tests to check if there was no regression in Picture service 
 * Version  : 1.0.0 
 * Note		: This Test Case supposes that you started the NodeJS server
 */
public class TestCase_Picture extends TestCase_Model
{
	//Testing all the picture APIs for usual actions
	@Test 
	public void testPictureClientAPI() throws Exception{	
		initSettings();

		File fileImg = new File(path+"file1.png");

		PictureInformation pictureImg = ws_client.sendPicture(this.directory, fileImg);
		assertNotNull(pictureImg);
		assertNotNull(pictureImg.getChecksum());
		assertNotNull(pictureImg.getFile_path());

		String path1 = pictureImg.getFile_path();

		assertEquals(-1, path1.indexOf("."));

		assertEquals(this.directory, extractDirectory(path1));
		assertEquals(pictureImg, ws_client.getPictureInformation(path1));
		assertEquals(pictureImg, ws_client.deleteOnePicture(extractDirectory(path1), extractFilename(path1)));
	}

	@Test
	public void testPictureClientInformationAPI() throws Exception{	
		initSettings();

		File fileImg1 = new File(path+"file1.png");
		File fileImg2 = new File(path+"file2.png");		//file2.png is the same file that file1.png

		PictureInformation pictureImg1 = ws_client.sendPicture(this.directory, fileImg1, "png");
		PictureInformation pictureImg2 = ws_client.sendPicture(this.directory, fileImg2);

		assertEquals(pictureImg1.getChecksum(),   pictureImg2.getChecksum());
		assertNotSame(pictureImg1.getFile_path(), pictureImg2.getFile_path());

		String path1 = pictureImg1.getFile_path();
		String path2 = pictureImg2.getFile_path();

		assertEquals(true, path1.endsWith(".png"));
		assertEquals(-1, path2.indexOf("."));

		assertEquals(this.directory, extractDirectory(path1));
		assertEquals(this.directory, extractDirectory(path2));
		assertEquals(pictureImg1, ws_client.deleteOnePicture(extractDirectory(path1), extractFilename(path1)));
		assertEquals(pictureImg2, ws_client.deleteOnePicture(extractDirectory(path2), extractFilename(path2)));
	}

	@Test
	public void testPictureClientInformationAPIBis() throws Exception{	
		initSettings();

		File fileImg1 = new File(path+"file1.png");
		File fileImg2 = new File(path+"file3.png");		//file3.png is NOT the same file that file1.png

		PictureInformation pictureImg1 = ws_client.sendPicture(this.directory, fileImg1, "png");
		PictureInformation pictureImg2 = ws_client.sendPicture(this.directory, fileImg2);

		assertNotSame(pictureImg1.getChecksum(),   pictureImg2.getChecksum());
		assertNotSame(pictureImg1.getFile_path(), pictureImg2.getFile_path());

		String path1 = pictureImg1.getFile_path();
		String path2 = pictureImg2.getFile_path();

		assertEquals(true, path1.endsWith(".png"));
		assertEquals(-1, path2.indexOf("."));

		assertEquals(this.directory, extractDirectory(path1));
		assertEquals(this.directory, extractDirectory(path2));
		assertEquals(pictureImg1, ws_client.deleteOnePicture(extractDirectory(path1), extractFilename(path1)));
		assertEquals(pictureImg2, ws_client.deleteOnePicture(extractDirectory(path2), extractFilename(path2)));
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