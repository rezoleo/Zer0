package fr.service_card.junit.test;

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


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import fr.service_card.junit.model.TestCase_Model;
import fr.service_card.object.Card;
import fr.webservicecore.error.APIException;
import fr.webservicecore.error.ErrorReferential;

/**
 * This class contains the webService client JUnit tests to check if there was no regression in Card service
 * <p>
 * This Test Case supposes that you started the NodeJS server and removed all the Card elements in the MongoDB database
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_Card_ErrorMsg extends TestCase_Model
{ 
	protected Gson 					gson  	      = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
	protected String 				response  	  = null;

	protected Card card1 = null;
	protected Card card2 = null;

	@Test
	public void testErrorGetUnkownCardByID() throws Exception{
		initSettings();
		try{
			ws_client.getOneCardById("id1");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("CARD.1.1.2"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorGetUnkownCardByIDBis() throws Exception{
		initSettings();
		try{
			card1 = ws_client.createCard("owner", "ffffffffffffff", "ON", "creator");
			ws_client.getOneCardById("id1");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("CARD.1.1.2"), e.getMsg());
			ws_client.deleteOneCard(card1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorGetUnkownCardByCode() throws Exception{
		initSettings();
		try{
			ws_client.getOneCardByCode("cde1ffff");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("CARD.1.2.2"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorGetUnkownCardByIDCode() throws Exception{
		initSettings();
		try{
			card1 = ws_client.createCard("owner", "ffffffffffffff", "ON", "creator");
			ws_client.getOneCardByCode("cde1ffff");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("CARD.1.2.2"), e.getMsg());
			ws_client.deleteOneCard(card1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorPostCardDuplication() throws Exception{
		initSettings();
		try{
			card1 = ws_client.createCard("ownerone", "ffffffffffffff", "ON", "creatoro");
			ws_client.createCard("ownertwo", "ffffffffffffff", "OFF", "creatort");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("CARD.1.3.4"), e.getMsg());
			ws_client.deleteOneCard(card1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorPutUnknownCard() throws Exception{
		initSettings();
		try{
			ws_client.updateCard("id1", "owner", "cde1ffffffffff", "ON", "updator");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("CARD.1.4.5"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorPutUnknownCardBis() throws Exception{
		initSettings();
		try{
			card1 = ws_client.createCard("ownerone", "ffffffffffffff", "ON", "creatoro");
			card2 = ws_client.createCard("ownertwo", "aaaaaaaaaaaaaa", "OFF", "creatort");
			ws_client.updateCard(card2.get_id(), "owner", "aaaaaaaaaaaaaa", "ON", "updator");
			ws_client.updateCard(card2.get_id(), "owner", "ffffffffffffff", "OFF", "updator");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("CARD.1.4.6"), e.getMsg());
			ws_client.deleteOneCard(card1.get_id());
			ws_client.deleteOneCard(card2.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorUpdateCardDuplication() throws Exception{
		initSettings();
		try{
			card1 = ws_client.createCard("ownerone", "ffffffffffffff", "ON", "creatoro");
			ws_client.createCard("ownertwo", "ffffffffffffff", "OFF", "creatort");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("CARD.1.3.4"), e.getMsg());
			ws_client.deleteOneCard(card1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorDeleteUnknowncard() throws Exception{
		initSettings();
		try{
			ws_client.deleteOneCard("id1");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("CARD.1.5.2"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
	@Test
	public void testErrorDeleteUnknownCardBis() throws Exception{
		initSettings();
		try{
			card1 = ws_client.createCard("ownerone", "ffffffffffffff", "ON", "creatoro");
			ws_client.deleteOneCard("id1");
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("CARD.1.5.2"), e.getMsg());
			ws_client.deleteOneCard(card1.get_id());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
}