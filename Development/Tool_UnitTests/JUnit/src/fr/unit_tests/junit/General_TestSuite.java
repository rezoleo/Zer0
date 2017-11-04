package fr.unit_tests.junit;

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


import junit.framework.TestCase;
import junit.framework.TestSuite;

/* 
 * Class 	: General_TestSuite
 * Author(s): Zidmann
 * Function : This class is used to call all the JUnit tests
 * Version  : 1.0.0
 */

public class General_TestSuite extends TestCase{
	public static TestSuite suite() {
		TestSuite testSuite = new TestSuite();
		testSuite.addTest(fr.applicationcore.junit.test.TestSuite_ApplicationCore.suite());
		testSuite.addTest(fr.webservicecore.junit.test.TestSuite_WebServiceCore.suite());
		testSuite.addTest(fr.service_alert.junit.test.TestSuite_ServiceAlert.suite());
		testSuite.addTest(fr.service_authentification.junit.test.TestSuite_ServiceAuthentification.suite());
		testSuite.addTest(fr.service_card.junit.test.TestSuite_ServiceCard.suite());
		testSuite.addTest(fr.service_contributor.junit.test.TestSuite_ServiceContributor.suite());
		testSuite.addTest(fr.service_group.junit.test.TestSuite_ServiceGroup.suite());
		testSuite.addTest(fr.service_people.junit.test.TestSuite_ServicePeople.suite());
		testSuite.addTest(fr.service_picture.junit.test.TestSuite_ServicePicture.suite());
		testSuite.addTest(fr.service_provider.junit.test.TestSuite_ServiceProvider.suite());
		return testSuite;
	}
}