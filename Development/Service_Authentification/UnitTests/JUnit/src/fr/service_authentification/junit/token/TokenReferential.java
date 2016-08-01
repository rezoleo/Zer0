package fr.service_authentification.junit.token;

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
 * Function : This class contains all the tokens used to test the authorization service
 * Version  : 1.0.0
 * Note		: The key used for these tokens is : "d2s42dggjfqlry6Jfs9shrgrxjthty"
 */
public class TokenReferential{
	public TokenReferential(){
		//Token for test cases (must be in the Mongo database)
		putToken("token_authentification",	"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX0F1dGhlbnRpZmljYXRpb24iLCJlbmRfZGF0ZSI6IjIwMTUtMDItMTFUMjM6MDY6MzMuNDc3WiIsInJhbmRvbSI6MC4xMDI4OTgxMTA0MDI3NDc5OSwiYWNjZXNzIjpbIkdFVCIsIkdFVE9uZSIsIlBPU1QiLCJQT1NUbG9naW4iLCJQVVQiLCJERUxFVEUiXX0.0z9YGk62lczd_tl2AZD-VT85PBpPG3SAcxSDKxAnNpo");		
	}

	public static void putToken(String key, String value){
		fr.junittemplate.token.TokenReferential.putToken(key, value);
	}

	//Function to extract a specific token in all the list
	public static String getToken(String key){
		return fr.junittemplate.token.TokenReferential.getToken(key);
	}
}