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


import java.util.Vector;

import org.junit.Test;

import fr.applicationcore.error.ErrorReferential;
import fr.applicationcore.junit.model.TestCase_Model;
import fr.applicationcore.junit.object.Roles;
import fr.applicationcore.object.APIException;

/* 
 * Class 	: TestCase_Roles
 * Function : This class contains JUnit tests to check if the role management works 
 */
public class TestCase_Roles extends TestCase_Model
{
	Vector<String> roles = new Vector<String>();

	//Testing the role management in a server using ApplicationCore module
	@Test
	public void testRoleManagement() throws Exception{
		initSettings();

		roles.add("my_role");
		roles.add("my_role_bis");

		makeCheckWithError();
		makeRoleoutWithError();

		makeRolein();
		makeCheck();
		makeRoleout();

		makeRoleoutWithError();
		makeCheckWithError();			
	}

	protected void makeCheck() throws Exception{
		assertEquals(new Roles(roles, "OK"), this.role_client.checkRoles());
	}
	protected void makeCheckWithError() throws Exception{
		try{
			this.role_client.checkRoles();
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("APPCORE.3.1.3"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}

	protected void makeRolein() throws Exception{
		assertEquals(new Roles(roles, "OK"), this.role_client.getRoles());
	}

	protected void makeRoleout() throws Exception{
		assertEquals(new Roles(new Vector<String>(), "OK"), this.role_client.eraseRoles());		
	}
	protected void makeRoleoutWithError() throws Exception{
		try{
			this.role_client.eraseRoles();
			assertNotNull(null);
		}
		catch(APIException e){
			checkMessage(ErrorReferential.getErrorByCode("APPCORE.3.1.3"), e.getMsg());
		}
		catch(Exception e) {	assertNotNull(null);	}
	}
}
