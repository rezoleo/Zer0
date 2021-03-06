package fr.service_provider.junit.token;

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
 * List of all the tokens used to test the Alert, Authentification, Card, Contributor, Group, People, Picture and Provider services
 * <p>
 * The key used for these tokens is : "d2s42dggjfqlry6Jfs9shrgrxjthty"
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TokenReferential{
	public TokenReferential(){
		//Tokens for test cases (must be in the Mongo database)
		putToken("token_alert",				"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX0FsZXJ0IiwiZW5kX2RhdGUiOiIyMDE4LTExLTA0VDE2OjAzOjM4LjYzOFoiLCJhY2Nlc3MiOlsiR0VUIiwiR0VUaWQiLCJQT1NUIl0sImV4dHJhIjpudWxsfQ.RIsjb7UtdmqhJzdFYIpEyQghA2-mWEXQ0yXMEf16Vmg");
		putToken("token_authentification",	"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX0F1dGhlbnRpZmljYXRpb24iLCJlbmRfZGF0ZSI6IjIwMTUtMDItMTFUMjM6MDY6MzMuNDc3WiIsInJhbmRvbSI6MC4xMDI4OTgxMTA0MDI3NDc5OSwiYWNjZXNzIjpbIkdFVCIsIkdFVE9uZSIsIlBPU1QiLCJQT1NUbG9naW4iLCJQVVQiLCJERUxFVEUiXX0.0z9YGk62lczd_tl2AZD-VT85PBpPG3SAcxSDKxAnNpo");
		putToken("token_card",				"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX0NhcmQiLCJlbmRfZGF0ZSI6IjIwMTQtMTEtMTBUMjM6MDc6MzQuMzQwWiIsInJhbmRvbSI6MC41MzYwNzgwMzA5NDIwMDc5LCJhY2Nlc3MiOlsiR0VUIiwiR0VUaWQiLCJHRVRjYXJkIiwiUE9TVCIsIlBVVCIsIkRFTEVURSJdfQ.TN7Zm5AdtqD8tNDD2fvstjyJLxYhwXtEkCYjlaLy7X0");
		putToken("token_contributor",		"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX0NvbnRyaWJ1dG9yIiwiZW5kX2RhdGUiOiIyMDE1LTA1LTAyVDIwOjI1OjAyLjkwMFoiLCJhY2Nlc3MiOlsiR0VUIiwiR0VUaWQiLCJHRVRsb2dpbiIsIlBPU1QiLCJERUxFVEUiXSwiZXh0cmEiOm51bGx9.TmJBcAHDJBmLgzjoLd9a1C9G3khM8XHKVp_U95mfEU8");
		putToken("token_group",				"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX0dyb3VwIiwiZW5kX2RhdGUiOiIyMDE1LTA0LTE4VDE0OjI2OjAyLjY4N1oiLCJyYW5kb20iOjAuNTE5NDg5NDIzNjA0NjgyMSwiYWNjZXNzIjpbIkdFVCIsIkdFVGlkIiwiR0VUbmFtZSIsIkdFVHNlYXJjaCIsIlBPU1QiLCJQT1NUbWVtYmVyIiwiUE9TVHJlc3BvbnNhYmlsaXR5IiwiUFVUIiwiREVMRVRFIiwiREVMRVRFbWVtYmVyIiwiREVMRVRFcmVzcG9uc2FiaWxpdHkiXX0.4-3ZgABTbGNbr3rezqA10Z6CNYj278KG6Jab7HFGDxQ");
		putToken("token_people",			"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX1Blb3BsZSIsImVuZF9kYXRlIjoiMjAxNC0xMS0xOVQwMTozMTo0My45NzdaIiwicmFuZG9tIjowLjQxMzgyODU5NDE0NDQzMzc0LCJhY2Nlc3MiOlsiR0VUIiwiR0VUaWQiLCJHRVRsb2dpbiIsIkdFVG1haWwiLCJQT1NUIiwiUE9TVHRhZyIsIlBVVCIsIkRFTEVURSIsIkRFTEVURXRhZyJdfQ.T05Hxx4EPyifKg_yIKtkcU1VP5OsEZ8cg4Thv_Gq3qk");
		putToken("token_picture", 			"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX1BpY3R1cmUiLCJlbmRfZGF0ZSI6IjIwMTYtMTItMDlUMjI6MzI6MDIuMjkyWiIsImFjY2VzcyI6WyJHRVQiLCJQT1NUIiwiREVMRVRFIl0sImV4dHJhIjp7InJlYWQiOlsiSnVuaXRUZXN0cyJdLCJhZGQiOlsiSnVuaXRUZXN0cyJdLCJkZWxldGUiOlsiSnVuaXRUZXN0cyJdfX0.2zsxJW4Ep529Vs0lu9T5dOUH_5eonn5aYpoT65mkL6s");

		putToken("token_provider",		 	"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzb3VyY2VfaXAiOiIxMjcuMC4wLjEiLCJzb3VyY2Vfc2VydmljZSI6Ikp1bml0VGVzdHMiLCJkZXN0X3NlcnZpY2UiOiJTZXJ2aWNlX1Byb3ZpZGVyIiwiZW5kX2RhdGUiOiIyMDE3LTAxLTE1VDIzOjEwOjM4LjQxOFoiLCJhY2Nlc3MiOlsiR0VUQXV0aCIsIlBPU1RBdXRoTG9naW4iLCJHRVRDYXJkIiwiR0VUQ2FyZElkIiwiR0VUQ2FyZENvZGUiLCJHRVRDb250cmlidXRvciIsIkdFVENvbnRyaWJ1dG9ySWQiLCJHRVRDb250cmlidXRvckxvZ2luIiwiR0VUUGVvcGxlIiwiR0VUUGVvcGxlSWQiLCJHRVRQZW9wbGVMb2dpbiIsIkdFVFBlb3BsZU1haWwiLCJHRVRQaWN0dXJlIiwiR0VUR3JvdXAiLCJHRVRHcm91cElkIiwiR0VUR3JvdXBOYW1lIiwiR0VUR3JvdXBTZWFyY2giXSwiZXh0cmEiOnsicmVhZCI6WyJKdW5pdFRlc3RzIiwiZGlyZWN0b3J5IiwiZGlyZWN0b3J5w6kiLCJkaXJlY3RvcnnDoCIsImRpcmVjdG9yeSYiLCJkaXJlY3RvcnkrIiwiZGlyZWN0b3J5PSIsImRpcmVjdG9yeToiLCJkaXJlY3RvcnkoIiwiZGlyZWN0b3J5JTIwIl19fQ.e2uZvzqcR866iX7FZik1th4z_MfzdNTg78riXhBo7UA");
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