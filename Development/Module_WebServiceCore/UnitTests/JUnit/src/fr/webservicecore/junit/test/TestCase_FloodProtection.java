package fr.webservicecore.junit.test;

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


import java.util.Date;

import org.junit.Test;

import fr.core.network.HttpCommunication;
import fr.webservicecore.Common;
import fr.webservicecore.junit.model.TestCase_Model;

/**
 * This class contains the webService client JUnit tests to check if there was no regression in flood protection module through WebserviceCore module
 * <p>
 * This Test Case supposes that you started the NodeJS server designed to test 'WS_ObjectDBClient' class and remove all ObjectDBs in the MongoDB database.
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_FloodProtection extends TestCase_Model
{
	/** 
	 * Testing the flood protection
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testGlobalSlowdowning() throws Exception{
		// Global floodprotection configuration
		int min_wait = 500;
		int max_wait = 900;
		int free_retries  = 105;
		int burst_retries = 110;

		int routeid=1;
		auxiAnalysisFunction(routeid, min_wait, max_wait, free_retries, burst_retries);
	}

	/** 
	 * Testing the flood protection
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testLocalSlowdowning() throws Exception{
		// Local floodprotection configuration
		int min_wait = 400;
		int max_wait = 700;
		int free_retries  = 120;
		int burst_retries = 125;

		int routeid=2;
		auxiAnalysisFunction(routeid, min_wait, max_wait, free_retries, burst_retries);
	}

	/** 
	 * Testing the flood protection when it is disabled
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testDisabledSlowdowning() throws Exception{
		int min_wait = 500;
		int max_wait = 0;
		int free_retries  = 150;
		int burst_retries = free_retries-5;

		int routeid=3;

		auxiAnalysisFunction(routeid, min_wait, max_wait, free_retries, burst_retries);
	}

	protected void auxiAnalysisFunction(int routeid, int min_wait, int max_wait, int free_retries, int burst_retries) throws Exception{
		initSettings();
		int nb_requests=burst_retries+5;

		String response="";
		long   delay=0;
		long   expectedDelay=0;

		// Requesting the NodeJS server
		for(int i=0; i<nb_requests; i++){
			Date now = new Date();
			response=requestServer(routeid);
			delay=(new Date()).getTime()-(now.getTime());

			if(i<free_retries){
				assertEquals("{\"message\":\"hello\"}", response);

				assertTrue(delay<=min_wait);
			}
			else if(i<burst_retries){
				assertEquals("{\"message\":\"hello\"}", response);

				expectedDelay=waitFunction(i, min_wait, max_wait, free_retries, burst_retries);
				assertTrue(delay>=expectedDelay*0.95);
				assertTrue(delay<=expectedDelay*1.10);
			}
			else if(i==burst_retries){
				assertTrue(response.contains("FLOODPROTECTION.1.1.6"));
			}
			else{
				assertTrue(response.contains("FLOODPROTECTION.1.1.5"));

				assertTrue(delay<=min_wait);
			}
		}
	}

	protected String requestServer(int routeid) throws Exception{
		return HttpCommunication.getInstance().sendGet(Common.URL+"/api/floodtest/increment_route"+routeid);
	}

	/**
	 * Function to calculate how much time the server will last
	 * @param iteration The quantity of requests which have been done since the beginning
	 * @param min_wait Setting on the server side to define when the restriction is triggered how much time the client must wait at least
	 * @param max_wait Setting on the server side to define when the restriction is triggered how much time the client must wait at the maximum
	 * @param free_retries Setting on the server side to define the number of request necessary to trigger the wait period
	 * @param burst_retries Setting on the server side to define the number of request necessary to trigger the lock on the server side
	 * @return the delay in millisecond
	 */
	protected long waitFunction(int iteration, int min_wait, int max_wait, int free_retries, int burst_retries){
		return (((max_wait-min_wait)/(burst_retries-free_retries)*(iteration-free_retries))+min_wait)*4;
	}
}