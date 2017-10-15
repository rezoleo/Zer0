package fr.applicationcore.junit.test;

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
 * Class 	: TestSuite_ApplicationCore
 * Author(s): Zidmann
 * Function : This class is used to define the JUnit test cases for testing NodeJS framework named 'ApplicationCore'
 * Version  : 1.0.0
 */
public class TestSuite_ApplicationCore extends TestSuite {
	public static TestSuite suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(TestCase_ErrorMsg.class);
		suite.addTestSuite(TestCase_FloodProtection.class);
		suite.addTestSuite(TestCase_Gate.class);
		suite.addTestSuite(TestCase_HttpTransmission.class);
		suite.addTestSuite(TestCase_Login.class);
		suite.addTestSuite(TestCase_Roles.class);
		suite.addTestSuite(TestCase_Toolbox.class);
		suite.addTestSuite(TestCase_UserAgent.class);
		return suite;
	}
}