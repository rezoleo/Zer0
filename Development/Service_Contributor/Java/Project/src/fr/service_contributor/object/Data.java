package fr.service_contributor.object;

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



import com.google.gson.annotations.*;

import fr.webservicecore.object.APIObject;

/* 
 * Class 	: Data
 * Function : This class describes a contributor 
 */
public class Data implements APIObject
{
	@Expose
	@Since(1.0)
	protected String	login = null;
	@Expose
	@Since(1.0)
	protected Boolean	isRegistered = null;

	public Data(){

	}
	public Data(String login, Boolean isRegistered){
		this.login			= login;
		this.isRegistered	= isRegistered;
	}

	public String getLogin(){
		return this.login;
	}
	public void setLogin(String login){
		this.login = login;
	}
	public Boolean getIsRegistered(){
		return this.isRegistered;
	}
	public void setIsRegistered(Boolean isRegistered){
		this.isRegistered = isRegistered;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.isRegistered == null) ? 0 : this.isRegistered.hashCode());
		result = prime * result + ((this.login == null) ? 0 : this.login.hashCode());
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
		Data other = (Data) obj;
		if (this.isRegistered == null) {
			if (other.isRegistered != null)
				return false;
		} else if (!this.isRegistered.equals(other.isRegistered))
			return false;
		if (this.login == null) {
			if (other.login != null)
				return false;
		} else if (!this.login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public boolean isEmpty(){
		return (this.login==null);
	}
}
