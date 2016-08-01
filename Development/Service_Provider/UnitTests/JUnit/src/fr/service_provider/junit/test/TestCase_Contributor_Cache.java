package fr.service_provider.junit.test;

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


import org.junit.Test;

import fr.cache.object.Cache;
import fr.service_contributor.object.Contributor;
import fr.service_provider.junit.model.TestCase_Model;

/* 
 * Class 	: TestCase_Contributor_Cache
 * Author(s): Zidmann
 * Function : This class contains the webService client JUnit tests to check if there was no regression in Provider service for Contributor service 
 * Version  : 1.0.0 
 */
public class TestCase_Contributor_Cache extends TestCase_Model
{
	@Test
	public void testContributorCache() throws Exception{		
		initSettings();

		Cache cacheRef = null;
		Cache cache1   = null;
		Cache cache2   = null;
		Cache cache3   = null;
		Cache cache4   = null;

		checkContributorQuantity(0);
		cacheRef=ws_client_contributor.getCacheInformation();

		//## Test Cache after a POST contributor request:
		Contributor contributor = null;
		try{
			contributor =ws_client_contributor.createContributor("login", "creator");
			cache1=ws_client_contributor_proxy.getCacheInformation();
		}
		catch(Exception e)		{	assertNotNull(null);	}

		//## Test Cache after a GET contributor request:
		try{			
			ws_client_contributor.getOneContributorById(contributor.get_id());
			cache2=ws_client_contributor.getCacheInformation();

			ws_client_contributor.getAllContributor();
			cache3=ws_client_contributor_proxy.getCacheInformation();
		}
		catch(Exception e)		{	assertNotNull(null);	}

		//## Test Cache after a DELETE contributor request:
		try{
			ws_client_contributor.deleteOneContributor(contributor.get_id());
			cache4=ws_client_contributor_proxy.getCacheInformation();
		}
		catch(Exception e)		{	assertNotNull(null);	}		

		//Check cache information received after the POST
		assertEquals(true, inferiorDate(cacheRef.getLastUpdate(), cache1.getLastUpdate()));

		assertEquals(true, equalsDate(cache1.getLastUpdate(),         cache1.getLastPostUpdate()));
		assertEquals(true, inferiorDate(cacheRef.getLastPostUpdate(), cache1.getLastPostUpdate()));
		assertEquals(true, equalsDate(cacheRef.getLastDeleteUpdate(), cache1.getLastDeleteUpdate()));
		assertEquals(true, equalsDate(cacheRef.getLastPutUpdate(),    cache1.getLastPutUpdate()));

		//Check cache information received after the GET
		assertEquals(false, inferiorDate(cache1.getLastUpdate(), 	cache2.getLastUpdate()));

		assertEquals(true, equalsDate(cache1.getLastUpdate(),       cache2.getLastUpdate()));
		assertEquals(true, equalsDate(cache1.getLastPostUpdate(),   cache2.getLastPostUpdate()));
		assertEquals(true, equalsDate(cache1.getLastDeleteUpdate(), cache2.getLastDeleteUpdate()));
		assertEquals(true, equalsDate(cache1.getLastPutUpdate(),    cache2.getLastPutUpdate()));

		assertEquals(true, equalsDate(cache2.getLastUpdate(),       cache3.getLastUpdate()));
		assertEquals(true, equalsDate(cache2.getLastPostUpdate(),   cache3.getLastPostUpdate()));
		assertEquals(true, equalsDate(cache2.getLastDeleteUpdate(), cache3.getLastDeleteUpdate()));
		assertEquals(true, equalsDate(cache2.getLastPutUpdate(),    cache3.getLastPutUpdate()));

		//Check cache information received after the DELETE
		assertEquals(true, 	inferiorDate(cache3.getLastUpdate(),		cache4.getLastUpdate()));
		assertEquals(false, equalsDate(cache3.getLastUpdate(),       cache4.getLastUpdate()));
		assertEquals(true, 	equalsDate(cache3.getLastPostUpdate(),   cache4.getLastPostUpdate()));
		assertEquals(true, 	equalsDate(cache3.getLastPutUpdate(),    cache4.getLastPutUpdate()));
	}
}