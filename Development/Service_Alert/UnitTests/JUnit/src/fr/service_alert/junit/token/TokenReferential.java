package fr.service_alert.junit.token;

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


/**
 * List of all the tokens used to test the Alert service
 * <p>
 * The key used for these tokens is : "d2s42dggjfqlry6Jfs9shrgrxjthty"
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TokenReferential{
	public TokenReferential(){
		//Token for the test cases (must be in the Mongo database)
		putToken("token_alert",	"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX0FsZXJ0IiwiZW5kX2RhdGUiOiIyMDE4LTExLTA0VDE2OjAzOjM4LjYzOFoiLCJhY2Nlc3MiOlsiR0VUIiwiR0VUaWQiLCJQT1NUIl0sImV4dHJhIjpudWxsfQ.RIsjb7UtdmqhJzdFYIpEyQghA2-mWEXQ0yXMEf16Vmg");		
	}

	/**
	 * Insert into the list of token one token
	 * @param key The key to refer to the token
	 * @param value The token
	 */
	public static void putToken(String key, String value){
		fr.junittemplate.token.TokenReferential.putToken(key, value);
	}

	/**
	 * Extract a specific token in all the list
	 * @param key The key to refer to the token
	 * @return The expected token
	 */
	public static String getToken(String key){
		return fr.junittemplate.token.TokenReferential.getToken(key);
	}
}