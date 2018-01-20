package fr.service_picture.junit.token;

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
 * List of all the tokens used to test the Picture service
 * <p>
 * The key used for these tokens is : "d2s42dggjfqlry6Jfs9shrgrxjthty"
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TokenReferential{
	public TokenReferential(){
		//Token for test cases (must be in the Mongo database)
		putToken("token_picture",			"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX1BpY3R1cmUiLCJlbmRfZGF0ZSI6IjIwMTYtMTItMDlUMjI6MzI6MDIuMjkyWiIsImFjY2VzcyI6WyJHRVQiLCJQT1NUIiwiREVMRVRFIl0sImV4dHJhIjp7InJlYWQiOlsiSnVuaXRUZXN0cyJdLCJhZGQiOlsiSnVuaXRUZXN0cyJdLCJkZWxldGUiOlsiSnVuaXRUZXN0cyJdfX0.2zsxJW4Ep529Vs0lu9T5dOUH_5eonn5aYpoT65mkL6s");
		putToken("token_picture_no_read",	"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHNfMSIsImRlc3Rfc2VydmljZSI6IlNlcnZpY2VfUGljdHVyZSIsImVuZF9kYXRlIjoiMjAxNi0xMi0wOVQyMjo1ODo0NC4xNDVaIiwiYWNjZXNzIjpbIkdFVCIsIlBPU1QiLCJERUxFVEUiXSwiZXh0cmEiOnsicmVhZCI6WyJYWFgiXSwiYWRkIjpbIkp1bml0VGVzdHMiXSwiZGVsZXRlIjpbIkp1bml0VGVzdHMiXX19.cmWDBok8q0SJqKXC_y8RC00bnG1TXW4UkV50kMdMMCo");
		putToken("token_picture_no_add",	"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHNfMiIsImRlc3Rfc2VydmljZSI6IlNlcnZpY2VfUGljdHVyZSIsImVuZF9kYXRlIjoiMjAxNi0xMi0wOVQyMzowMTozOS41MTBaIiwiYWNjZXNzIjpbIkdFVCIsIlBPU1QiLCJERUxFVEUiXSwiZXh0cmEiOnsicmVhZCI6WyJKdW5pdFRlc3RzIl0sImFkZCI6WyJYWFgiXSwiZGVsZXRlIjpbIkp1bml0VGVzdHMiXX19.BwkZc--7ortgOQSvGds7uM7zBObxk8AZIfIv10P79ow");
		putToken("token_picture_no_delete", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHNfMyIsImRlc3Rfc2VydmljZSI6IlNlcnZpY2VfUGljdHVyZSIsImVuZF9kYXRlIjoiMjAxNi0xMi0wOVQyMzowNjo0OS4zMTlaIiwiYWNjZXNzIjpbIkdFVCIsIlBPU1QiLCJERUxFVEUiXSwiZXh0cmEiOnsicmVhZCI6WyJKdW5pdFRlc3RzIl0sImFkZCI6WyJKdW5pdFRlc3RzIl0sImRlbGV0ZSI6WyJYWFgiXX19.tZN9I2YGZoRbPj1A6f5C6qFX7AX3zoO8EYUs9LtSP-c");
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