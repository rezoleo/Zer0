package fr.service_people.junit;

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


import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.Result;

import fr.service_people.junit.test.TestSuite_ServicePeople;

/* 
 * Class 	: TestRunner
 * Author(s): Zidmann
 * Function : This class is used to start JUnit tests through a main function
 * Version  : 1.0.0
 */
public class TestRunner {
	public static void main(String[] args) {
		Result rslt = JUnitCore.runClasses(TestSuite_ServicePeople.class);
		if(rslt.wasSuccessful()){
			System.exit(0);
		}
		else{
			for(Failure fail:rslt.getFailures()){
				System.err.println(fail.toString());
			}
			System.exit(1);
		}
	}
}