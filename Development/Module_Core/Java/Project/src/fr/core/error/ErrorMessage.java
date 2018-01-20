package fr.core.error;

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


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/** 
 * Basic attributes of an error message coming from a NodeJS server using the Core module for an application or a service
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class ErrorMessage extends Error implements ErrorInterface
{
	/**
	 * Version of the NodeJS server using the Core module for an application or a service
	 */
	@Expose
	@Since(1.0)
	private String version	= null;

	/**
	 * Constructor ErrorMessage
	 */
	public ErrorMessage(){
		super("0", null);
	}

	/**
	 * Constructor ErrorMessage
	 * @param version Version of the NodeJS server using the Core module
	 * @param code HTTP code returned by the error
	 * @param message Text message of the error
	 * @param stack Details of the error
	 * @param status Status of the error
	 */
	public ErrorMessage(String version, String code, String message, String stack, int status){
		super(code, message, stack, status);
		this.version = version;
	}

	/**
	 * Constructor ErrorMessage
	 * @param error Error object whose the code and the message will be copied
	 */
	public ErrorMessage(Error error){
		super("0", null);
		if(error!=null){
			this.setCode(error.getCode());
			this.setMessage(error.getMessage());
		}
	}

	public String getVersion(){
		return this.version;
	}
	public void setVersion(String version){
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorMessage other = (ErrorMessage) obj;
		if (this.version == null) {
			if (other.version != null)
				return false;
		} else if (!this.version.equals(other.version))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ErrorMessage [version=" + this.version + ", code=" + super.getCode() + ", message="+ super.getMessage() + ", stack=" + super.getStack() + ", status=" + super.getStatus() + "]";
	}
}