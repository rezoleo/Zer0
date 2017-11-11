package fr.core.error;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

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
 * Description of an error returned by a NodeJS server using the Core module for an application or a service
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version  1.1.0
 */
public class Error implements ErrorInterface
{
	/**
	 * Identification number of the error
	 */
	@Expose
	@Since(1.0)
	private String	code 	= "0"; 

	/**
	 * Text message of the error
	 */
	@Expose
	@Since(1.0)
	private String	message = null; 

	/**
	 * Details of the error
	 */
	@Expose
	@Since(1.0)
	private String	stack	= null; 

	/**
	 * Status of the error
	 */
	@Expose
	@Since(1.0)
	private int 	status	= 404; 

	/**
	 * Constructor Error
	 * @param code HTTP code returned by the error
	 * @param message Text message of the error
	 */
	public Error(String code, String message){
		this.code	 = code;
		this.message = message;
	}

	/**
	 * Constructor Error
	 * @param code HTTP code returned by the error
	 * @param message Text message of the error
	 * @param stack Details of the error
	 * @param status Status of the error
	 */
	public Error(String code, String message, String stack, int status){
		this.code 	 = code;
		this.message = message;
		this.stack   = stack;
		this.status  = status;
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
		result = prime * result + ((this.code == null) ? 0 : this.code.hashCode());
		result = prime * result + ((this.message == null) ? 0 : this.message.hashCode());
		result = prime * result + ((this.stack == null) ? 0 : this.stack.hashCode());
		result = prime * result + this.status;
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
		Error other = (Error) obj;
		if (this.code == null) {
			if (other.code != null)
				return false;
		} else if (!this.code.equals(other.code))
			return false;
		if (this.message == null) {
			if (other.message != null)
				return false;
		} else if (!this.message.equals(other.message))
			return false;
		if (this.stack == null) {
			if (other.stack != null)
				return false;
		} else if (!this.stack.equals(other.stack))
			return false;
		if (this.status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Error [code=" + this.code + ", message=" + this.message+ ", stack=" + this.stack + ", status=" + this.status + "]";
	}
}