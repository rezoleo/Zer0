package fr.core.toolbox;

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
 * Useful functions to process string object
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class StringTool
{
	/**
	 * Remove the first backslash in a string
	 * @param str String to change
	 * @return String changed
	 */
	public static String deleteFirstBackslash(String str){
		if(str==null){
			return null;
		}

		int length = str.length();
		int pos    = str.indexOf("/");

		if(length==1 && pos==0){
			return "";
		}
		else if(length>1){
			if(pos==0){
				return str.substring(1, length-1);
			}
			else if(pos==length-1){
				return str.substring(0, length-2);
			}
			else{
				return str.substring(0, pos-1)+str.substring(pos+1, length-1);
			}
		}

		return str;
	}
}
