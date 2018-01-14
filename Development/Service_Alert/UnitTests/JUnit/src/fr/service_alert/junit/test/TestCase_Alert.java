package fr.service_alert.junit.test;

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

import fr.service_alert.junit.model.TestCase_Model;
import fr.service_alert.object.Alert;

/**
 * This class contains the webService client JUnit tests to check if there was no regression in Alert service
 * <p>
 * This Test Case supposes that you started the NodeJS server
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_Alert extends TestCase_Model
{
	/**
	 * Testing all the alerts APIs for usual actions 
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testAlertClientAPI() throws Exception{	
		initSettings();

		//Creation of four alerts - Test POST function
		//Get all the alerts - Test GET function
		int count_info    =countAlertQuantity("INFO");
		int count_warning =countAlertQuantity("WARNING");
		int count_error   =countAlertQuantity("ERROR");
		int count_critical=countAlertQuantity("CRITICAL");

		checkAlertQuantity(count_info, count_warning, count_error, count_critical, 0);
		Alert alert1=ws_client.createAlert("messageINFO_4", "INFO");
		assertNotNull(alert1);
		assertNotNull(alert1.get_id());
		assertNotNull(alert1.getCreated());
		Alert alertRef1 = new Alert(alert1.get_id(), "messageINFO_4", "INFO",
								    alert1.getCreated(), "JunitTests");
		assertEquals(alertRef1, alert1);

		checkAlertQuantity(count_info, count_warning, count_error, count_critical, 1);
		Alert alert2=ws_client.createAlert("messageWARNING_4", "WARNING");
		assertNotNull(alert2);
		assertNotNull(alert2.get_id());
		assertNotNull(alert2.getCreated());
		Alert alertRef2 = new Alert(alert2.get_id(), "messageWARNING_4", "WARNING",
								 	alert2.getCreated(), "JunitTests");
		assertEquals(alertRef2, alert2);

		checkAlertQuantity(count_info, count_warning, count_error, count_critical, 2);
		Alert alert3=ws_client.createAlert("messageERROR_4", "ERROR");
		assertNotNull(alert3);
		assertNotNull(alert3.get_id());
		assertNotNull(alert3.getCreated());
		Alert alertRef3 = new Alert(alert3.get_id(), "messageERROR_4", "ERROR",
								 	alert3.getCreated(), "JunitTests");
		assertEquals(alertRef3, alert3);

		checkAlertQuantity(count_info, count_warning, count_error, count_critical, 3);
		Alert alert4=ws_client.createAlert("messageCRITICAL_4", "CRITICAL");
		assertNotNull(alert4);
		assertNotNull(alert4.get_id());
		assertNotNull(alert4.getCreated());
		Alert alertRef4 = new Alert(alert4.get_id(), "messageCRITICAL_4", "CRITICAL",
								 	alert4.getCreated(), "JunitTests");
		assertEquals(alertRef4, alert4);

		//Get one single alert
		checkAlertQuantity(count_info, count_warning, count_error, count_critical, 4);
		Alert alert5=ws_client.getOneAlertById(alert1.get_id());
		assertEquals(alertRef1, alert5);

		Alert alert6=ws_client.getOneAlertById(alert2.get_id());
		assertEquals(alertRef2, alert6);

		Alert alert7=ws_client.getOneAlertById(alert3.get_id());
		assertEquals(alertRef3, alert7);

		Alert alert8=ws_client.getOneAlertById(alert4.get_id());
		assertEquals(alertRef4, alert8);
	}
}