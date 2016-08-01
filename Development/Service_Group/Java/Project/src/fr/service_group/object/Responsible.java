package fr.service_group.object;

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


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/* 
 * Class 	: Group
 * Function : This class describes information of one responsible
 */
public class Responsible implements Comparable<Responsible> 
{
	@Expose
	@Since(1.0)
	protected String 			login			= null;
	@Expose
	@Since(1.0)
	protected String 			responsability	= null;

	public Responsible(){
		
	}
	public Responsible(String login, String responsability){
		this.login			= login;
		this.responsability = responsability;
	}

	public String getLogin(){
		return this.login;
	}
	public String getResponsability(){
		return this.responsability;
	}

	public void setLogin(String login){
		this.login = login;
	}
	public void setResponsability(String responsability){
		this.responsability = responsability;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((responsability == null) ? 0 : responsability.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Responsible other = (Responsible) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (responsability == null) {
			if (other.responsability != null)
				return false;
		} else if (!responsability.equals(other.responsability))
			return false;
		return true;
	}

	@Override
	public int compareTo(Responsible arg0) {
		if(arg0==null){
			return +1;
		}
		else if(arg0==this){
			return 0;
		}
		else if(this.login!=null && arg0.login==null){
			return +1;
		}
		else if(this.login==null && arg0.login!=null){
			return -1;
		}
		else if(!this.login.equals(arg0.login)){
			return this.login.compareTo(arg0.login);
		}
		else if(this.responsability!=null && arg0.responsability==null){
			return +1;
		}
		else if(this.responsability==null && arg0.responsability!=null){
			return -1;
		}
		else if(!this.responsability.equals(arg0.responsability)){
			return this.responsability.compareTo(arg0.responsability);
		}
		return 0;
	}
}