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


import org.junit.Test;

import fr.service_provider.junit.model.TestCase_Model;
import fr.service_card.object.Card;

/**
 * This class contains the webService client JUnit tests to check if there was no regression in Provider service for Card service
 * <p>
 * This Test Case supposes that you started the NodeJS server and removed all the Card elements in the MongoDB database
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_Card_OfflineRestriction extends TestCase_Model
{
	/**
	 * Testing all the student cards APIs for usual actions 
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testOfflineCardRestriction() throws Exception{
		initSettings();

		//Creation of two cards - Test POST function
		//Get all the cards - Test GET function
		checkCardQuantity(0);
		Card card1=ws_client_card.createCard("ownerone", "cde1ffffffffff", "OFF", "creatoro");
		assertNotNull(card1);
		assertNotNull(card1.get_id());
		assertNotNull(card1.getCreated());
		Card cardRef = new Card(card1.get_id(), "ownerone", "cde1ffffffffff", "OFF", "creatoro",
								card1.getCreated(), "JunitTests", 
								null, null, null);
		assertEquals(cardRef, card1);

		checkCardQuantity(1);
		Card card2=ws_client_card.createCard("ownertwo",  "cde2bbbbbbbbbb", "OFF", "creatort");
		assertNotNull(card2);
		assertNotNull(card2.get_id());
		assertNotNull(card2.getCreated());
		Card cardRef2 = new Card(card2.get_id(), "ownertwo", "cde2bbbbbbbbbb", "OFF", "creatort",
								 card2.getCreated(), "JunitTests", 
								 null, null, null);
		assertEquals(cardRef2, card2);

		checkCardQuantity(2);
		Card card3=ws_client_card_proxy.getOneCardByCode("cde2bbbbbbbbbb");
		Card cardRef3 = new Card(card3.get_id(), null, "cde2bbbbbbbbbb", "OFF",
								 null, card3.getCreated(), null, 
								 null, null, null);
		assertEquals(cardRef3, card3);

		//Update one card1
		checkCardQuantity(2);
		Card card4=ws_client_card.updateCard(card2.get_id(), "ownersix", "cde3cccccccccc", "OFF", "updator");
		assertNotNull(card4.getUpdated());
		cardRef2.setOwner("ownersix");
		cardRef2.setCode("cde3cccccccccc");
		cardRef2.setUpdated(card4.getUpdated());
		cardRef2.setUpdator("updator");
		cardRef2.setUpdatorService("JunitTests");
		assertEquals(cardRef2, card4);

		//Get one single card1
		checkCardQuantity(2);
		Card card5=ws_client_card_proxy.getOneCardById(card2.get_id());
		cardRef3.setCode("cde3cccccccccc");
		cardRef3.setCreated(card5.getCreated());
		cardRef3.setUpdated(card5.getUpdated());
		assertEquals(cardRef3, card5);

		//Delete the cards - Test DELETE function
		checkCardQuantity(2);
		Card card6=ws_client_card.deleteOneCard(card1.get_id());
		assertEquals(cardRef, card6);

		checkCardQuantity(1);
		Card card7=ws_client_card.deleteOneCard(card2.get_id());
		assertEquals(cardRef2, card7);

		checkCardQuantity(0);
	}
}
