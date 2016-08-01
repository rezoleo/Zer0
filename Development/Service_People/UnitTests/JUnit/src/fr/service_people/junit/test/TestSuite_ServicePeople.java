package fr.service_people.junit.test;

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


import junit.framework.TestSuite;

/* 
 * Class 	: TestSuite_ServicePeople
 * Author(s): Zidmann
 * Function : This class is used to define the JUnit test cases for testing People service
 * Version  : 1.0.0
 */
public class TestSuite_ServicePeople extends TestSuite {
	public static TestSuite suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(TestCase_People.class);
		suite.addTestSuite(TestCase_People_Cache.class);
		suite.addTestSuite(TestCase_People_ErrorMsg.class);
		suite.addTestSuite(TestCase_People_NoSecretPublication.class);
		suite.addTestSuite(TestCase_People_Tag.class);
		return suite;
	}
}