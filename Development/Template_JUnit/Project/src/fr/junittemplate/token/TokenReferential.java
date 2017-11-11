package fr.junittemplate.token;

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


import java.util.HashMap;

/**
 * This class is used to manage tokens in JUnit projects to test NodeJS service using 'ApplicationCore' or 'WebServiceCore' module
 * <p>
 * This class implements a singleton pattern
 * </p>
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class TokenReferential{
	/**
	 * Unique instance since TokenReferential class uses singleton pattern
	 */
	private static volatile TokenReferential instance  = null;

	/**
	 * Hashmap which associates the keys to the tokens
	 */
	private static HashMap<String, String> tokenList = new HashMap<String, String>();

	/**
	 * Constructor TokenReferential
	 */
	private TokenReferential(){
		
	}

	/**
	 * Get the unique instance since TokenReferential class uses singleton pattern
	 * @return TokenReferential instance
	 */
	public final static TokenReferential getInstance(){
		if (TokenReferential.instance == null){
			synchronized(TokenReferential.class){
				if (TokenReferential.instance == null){
					TokenReferential.instance = new TokenReferential();
				}
			}
		}
		return TokenReferential.instance;
	}

	/**
	 * Add one token in the list
	 * @param key Key to refer to the token
	 * @param value String which defines the token
	 */
	public static void putToken(String key, String value){
		if(key!=null && value!=null && !tokenList.containsKey(key)){
			tokenList.put(key, value);
		}
	}

	/**
	 * Extract a specific token in all the list
	 * @param key Key to refer to the token
	 * @return  String of the token associated to the key
	 */
	public static String getToken(String key){
		if(tokenList.containsKey(key)){
			return tokenList.get(key);
		}
		return null; 
	}
}