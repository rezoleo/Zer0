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


import org.junit.Test;

import fr.applicationcore.junit.model.TestCase_Model;
import fr.applicationcore.junit.object.Login;
import fr.applicationcore.error.ErrorReferential;
import fr.applicationcore.object.APIException;

/* 
 * Class 	: TestCase_Login
 * Author(s): Zidmann
 * Function : This class contains JUnit tests to check if the login management works  
 * Version  : 1.0.0
 */
public class TestCase_Login extends TestCase_Model
{
	//Testing the login management in a server using ApplicationCore module
	@Test
	public void testLoginManagement() throws Exception{
		initSettings();

		makeCheckWithError();
		makeLogoutWithError();

		makeLogin();
		makeCheck();
		makeLogout();

		makeLogoutWithError();
		makeCheckWithError();
	}

	protected void makeCheck() throws Exception{
		assertEquals(new Login("my_login", "OK"), this.login_client.checkLogin());
	}
	protected void makeCheckWithError() throws Exception{
		try{
			this.login_client.checkLogin();
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("APPCORE.3.1.1"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}

	protected void makeLogin() throws Exception{
		assertEquals(new Login("my_login", "OK"), this.login_client.getLogin());
	}

	protected void makeLogout() throws Exception{
		assertEquals(new Login(null, "OK"), this.login_client.eraseLogin());
	}
	protected void makeLogoutWithError() throws Exception{
		try{
			this.login_client.checkLogin();
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("APPCORE.3.1.1"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
}
