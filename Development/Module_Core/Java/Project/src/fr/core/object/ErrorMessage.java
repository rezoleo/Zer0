package fr.core.object;

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

import fr.core.error.Error;
import fr.core.error.ErrorInterface;

/* 
 * Class 	: ErrorMessage
 * Author(s): Zidmann
 * Function : This class describes basic attributes from an error message coming from a NodeJS server using the Core module for a service or an application
 * Version  : 1.0.0
 */
public class ErrorMessage implements ErrorInterface
{
	@Expose
	@Since(1.0)
	protected String version	= null;
	@Expose
	@Since(1.0)
	protected String code		= "0";
	@Expose
	@Since(1.0)
	protected String message	= null;
	@Expose
	@Since(1.0)
	protected String stack		= null;
	@Expose
	@Since(1.0)
	protected int 	 status		= 404;

	public ErrorMessage(){
	
	}
	public ErrorMessage(String version, String code, String message,
				   String stack,  int status){
		this.version = version;
		this.code 	 = code;
		this.message = message;
		this.stack   = stack;
		this.status  = status;
	}
	public ErrorMessage(Error error){
		if(error!=null){
			this.code 	 = error.code;
			this.message = error.message;
		}
		this.status  = 0;
	}

	public String getVersion(){
		return this.version;
	}
	public void setVersion(String version){
		this.version = version;
	}
	public String getCode(){
		return this.code;
	}
	public void setCode(String code){
		this.code = code;
	}
	public String getMessage(){
		return this.message;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public String getStack(){
		return this.stack;
	}
	public void setStack(String stack){
		this.stack = stack;
	}
	public int getStatus(){
		return this.status;
	}
	public void setStatus(int status){
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.code == null)    ? 0 : this.code.hashCode());
		result = prime * result + ((this.stack == null)   ? 0 : this.stack.hashCode());
		result = prime * result + ((this.message == null) ? 0 : this.message.hashCode());
		result = prime * result + this.status;
		result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;
		ErrorMessage other = (ErrorMessage) obj;
		if(this.code != other.code)
			return false;
		if(this.stack == null) {
			if(other.stack != null)
				return false;
		} else if(!this.stack.equals(other.stack))
			return false;
		if(this.message == null) {
			if(other.message != null)
				return false;
		} else if(!this.message.equals(other.message))
			return false;
		if(this.status != other.status)
			return false;
		if(this.version == null) {
			if(other.version != null)
				return false;
		} else if(!this.version.equals(other.version))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ErrorMessage [version=" + this.version + ", code=" + this.code + ", message="
				+ this.message + ", stack=" + this.stack + ", status=" + this.status + "]";
	}
}