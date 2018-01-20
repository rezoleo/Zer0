package fr.service_picture.junit.test;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import fr.service_picture.junit.model.TestCase_Model;
import fr.service_picture.junit.token.TokenReferential;
import fr.service_picture.object.PictureInformation;
import fr.webservicecore.error.APIException;
import fr.webservicecore.error.ErrorMessage;
import fr.webservicecore.error.ErrorReferential;

/**
 * This class contains the webService client JUnit tests to check if there was no regression in Picture service
 * <p>
 * This Test Case supposes that you started the NodeJS server and removed all the People elements in the MongoDB database
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_Picture_ErrorMsg extends TestCase_Model
{ 
	protected Gson 					gson  	      = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
	protected String 				response  	  = null;

	protected PictureInformation picture1 = null;
	protected PictureInformation picture2 = null;

	protected String[] listOfFilename  = {"imagé", "imagè", "éimag", "imag+", "i(mag", "imag$e"};
	protected String[] listOfExtension = {"image.pnG", "image.pnG", "image.bpm", "image.BPM", "image.jPG", "image.JPg"};

	@Test
	public void testErrorGetBadLettersInDir() throws Exception{
		initSettings();

		try{
			ws_client.getPictureInformation(directory+"@", "picture.png");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PICTURE.1.1.3"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}

	@Test
	public void testErrorGetUsingRefusedFilename() throws Exception{
		for(int i=0; i<listOfFilename.length; i++){
			testErrorGetUsingRefusedFilenameAuxi(listOfFilename[i]);
		}
	}
	protected void testErrorGetUsingRefusedFilenameAuxi(String filename) throws Exception{
		initSettings();
		try{
			ws_client.getPictureInformation(directory, filename);
			assertNotNull(null);
		}
		catch(APIException e){
			ErrorMessage msg=e.getMsg();
			msg.setService(service);
			msg.setVersion(version);
			checkMessage(ErrorReferential.getErrorByCode("WSCORE-JAR-2"), msg);
		}
		catch(Exception e) {	assertNotNull(null);	}
	}

	@Test
	public void testErrorGetRefusedExtension() throws Exception{
		for(int i=0; i<listOfExtension.length; i++){
			testErrorGetRefusedExtensionAuxi(listOfExtension[i]);
		}
	}
	protected void testErrorGetRefusedExtensionAuxi(String filename) throws Exception{
		initSettings();
		try{
			ws_client.getPictureInformation(directory, filename);
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PICTURE.1.1.5"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}

	@Test
	public void testErrorGetInUnauthorizedDirectory() throws Exception{
		initSettings();
		try{
			ws_client.setToken(TokenReferential.getToken("token_picture_no_read"));
			ws_client.getPictureInformation(directory, "filename.png");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PICTURE.1.1.6"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}

	@Test
	public void testErrorGetUnexistingFile() throws Exception{
		initSettings();
		try{
			ws_client.getPictureInformation(directory, "filename.png");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PICTURE.1.1.7"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}

	@Test
	public void testErrorPostUsingRefusedFilenameAuxi() throws Exception{
		initSettings();

		try{
			File file = new File(path+"file1.png");
			ws_client.sendPicture(directory+"@", file);
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PICTURE.1.2.2"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}

	@Test
	public void testErrorPostInUnauthorizedDirectory() throws Exception{
		initSettings();
		try{
			ws_client.setToken(TokenReferential.getToken("token_picture_no_add"));
			File file = new File(path+"file1.png");
			ws_client.sendPicture(directory, file);
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PICTURE.1.2.3"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}

	@Test
	public void testErrorPostSendingTooBigFile() throws Exception{
		initSettings();

		try{
			File file = new File(path+"file4.png");
			ws_client.sendPicture(directory, file);
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PICTURE.1.2.7"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}

	@Test
	public void testErrorDeleteBadLettersInDir() throws Exception{
		initSettings();

		try{
			ws_client.deleteOnePicture(directory+"@", "picture.png");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PICTURE.1.3.3"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}

	@Test
	public void testErrorDeleteRefusedFilename() throws Exception{
		for(int i=0; i<listOfFilename.length; i++){
			testErrorDeleteRefusedFilenameAuxi(listOfFilename[i]);
		}
	}
	protected void testErrorDeleteRefusedFilenameAuxi(String filename) throws Exception{
		initSettings();
		try{
			ws_client.deleteOnePicture(directory, filename);
			assertNotNull(null);
		}
		catch(APIException e){
			ErrorMessage msg=e.getMsg();
			msg.setService(service);
			msg.setVersion(version);
			checkMessage(ErrorReferential.getErrorByCode("WSCORE-JAR-2"), msg);
		}
		catch(Exception e) {	assertNotNull(null);	}
	}

	@Test
	public void testErrorDeleteRefusedExtension() throws Exception{
		for(int i=0; i<listOfExtension.length; i++){
			testErrorGetRefusedExtensionAuxi(listOfExtension[i]);
		}
	}
	protected void testErrorDeleteRefusedExtensionAuxi(String filename) throws Exception{
		initSettings();
		try{
			ws_client.deleteOnePicture(directory, filename);
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PICTURE.1.3.5"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}

	@Test
	public void testErrorDeleteInUnauthorizedDirectory() throws Exception{
		initSettings();
		try{
			ws_client.setToken(TokenReferential.getToken("token_picture_no_delete"));
			ws_client.deleteOnePicture(directory, "filename.png");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PICTURE.1.3.6"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}

	@Test
	public void testErrorDeleteUnexistingFile() throws Exception{
		initSettings();
		try{
			ws_client.deleteOnePicture(directory, "filename.png");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("PICTURE.1.3.7"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
}