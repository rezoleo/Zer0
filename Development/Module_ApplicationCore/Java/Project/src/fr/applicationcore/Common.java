package fr.applicationcore;

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
 * Default settings for running a Java client which connects itself to an application
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class Common 
{
	/**
	 * Default URL of an application
	 */
	public static String URL   			  = "https://localhost:9000";

	/**
	 * Adress of the proxy, undefined by default
	 */
	public static String ProxyAdress	  = null;
	/**
	 * Port of the proxy, undefined by default
	 */
	public static int    ProxyPort		  = -1;

	/**
	 * By default interacting with an	 application is not secure and doesn't require a keystore
	 */
	public static String keyStorePath	  = null;
	/**
	 * By default interacting with an application is not secure and doesn't require a keystore
	 */
	public static String keyStorePassword = null;
}