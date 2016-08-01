package fr.service_people.junit.token;

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


/* 
 * Class 	: TokenReferential
 * Author(s): Zidmann
 * Function : This class contains all the tokens used to test the people service
 * Version  : 1.0.0
 * Note		: The key used for these tokens is : "d2s42dggjfqlry6Jfs9shrgrxjthty"
 */
public class TokenReferential{
	public TokenReferential(){
		//Token for test cases (must be in the Mongo database)
		putToken("token_people",	"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX1Blb3BsZSIsImVuZF9kYXRlIjoiMjAxNC0xMS0xOVQwMTozMTo0My45NzdaIiwicmFuZG9tIjowLjQxMzgyODU5NDE0NDQzMzc0LCJhY2Nlc3MiOlsiR0VUIiwiR0VUaWQiLCJHRVRsb2dpbiIsIkdFVG1haWwiLCJQT1NUIiwiUE9TVHRhZyIsIlBVVCIsIkRFTEVURSIsIkRFTEVURXRhZyJdfQ.T05Hxx4EPyifKg_yIKtkcU1VP5OsEZ8cg4Thv_Gq3qk");		
	}
	
	public static void putToken(String key, String value){
		fr.junittemplate.token.TokenReferential.putToken(key, value);
	}

	//Function to extract a specific token in all the list
	public static String getToken(String key){
		return fr.junittemplate.token.TokenReferential.getToken(key);
	}
}