package fr.applicationcore.junit.test;

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

import fr.applicationcore.error.ErrorReferential;
import fr.applicationcore.junit.model.TestCase_Model;
import fr.applicationcore.junit.object.Gate;
import fr.applicationcore.error.APIException;

/**
 * This class contains JUnit tests to check if the gate management works
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TestCase_Gate extends TestCase_Model
{
	/**
	 * Testing the gate management in a server using ApplicationCore module
	 * @throws Exception Exception returned by the system
	 */
	@Test
	public void testGateManagement() throws Exception{
		initSettings();

		makeCheckWithError();
		makeGateoutWithError();

		makeGatein();
		makeCheck();
		makeGateout();

		makeGateoutWithError();
		makeCheckWithError();		
	}

	protected void makeCheck() throws Exception{
		assertEquals(new Gate("my_gate", "OK"), this.gate_client.checkGate());		
	}
	protected void makeCheckWithError() throws Exception{
		try{
			this.gate_client.checkGate();
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("APPCORE.3.1.2"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}

	protected void makeGatein() throws Exception{
		assertEquals(new Gate("my_gate", "OK"), this.gate_client.getGate());		
	}

	protected void makeGateout() throws Exception{
		assertEquals(new Gate(null, "OK"), this.gate_client.eraseGate());	
	}
	protected void makeGateoutWithError() throws Exception{
		try{
			this.gate_client.eraseGate();
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("APPCORE.3.1.2"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
}
