package fr.applicationcore.error;

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

import fr.applicationcore.error.ErrorInterface;
import fr.core.error.Error;

/** 
 * Basic attributes of an error message coming from a NodeJS server using the module entitled 'ApplicationCore'
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class ErrorMessage extends fr.core.error.ErrorMessage implements ErrorInterface
{
	/**
	 * Name of the application running under the NodeJS server using the Core module
	 */
	@Expose
	@Since(1.0)
	private String application	= null;

	/**
	 * Constructor ErrorMessage
	 * @param application Name of the application
	 * @param version Version of the NodeJS server using the Core module
	 * @param code HTTP code returned by the error
	 * @param message Text message of the error
	 * @param stack Details of the error
	 * @param status Status of the error
	 */
	public ErrorMessage(String application, String version, String code, String message, String stack,  int status){
		super(version, code, message, stack, status);
		this.application = application;
	}

	/**
	 * Constructor ErrorMessage
	 * @param error Error object whose the code and the message will be copied
	 */
	public ErrorMessage(Error error){
		super(error);
	}

	public String getApplication() {
		return this.application;
	}
	public void setApplication(String application) {
		this.application = application;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((super.getCode() == null) ? 0 : super.getCode().hashCode());
		result = prime * result + ((this.application == null) ? 0 : this.application.hashCode());
		result = prime * result + ((super.getStack() == null) ? 0 : super.getStack().hashCode());
		result = prime * result + ((super.getMessage() == null) ? 0 : super.getMessage().hashCode());
		result = prime * result + super.getStatus();
		result = prime * result + ((super.getVersion() == null) ? 0 : super.getVersion().hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (super.getClass() != obj.getClass())
			return false;
		ErrorMessage other = (ErrorMessage) obj;

		if(!super.equals(obj)){
			return false;
		}
		if (super.getVersion() == null) {
			if (other.getVersion() != null)
				return false;
		} else if (!super.getVersion().equals(other.getVersion()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ErrorMessage [application="+ this.application +", version=" + super.getVersion() + ", code=" + super.getCode() + ", message=" + super.getMessage() + ", stack=" + super.getStack() + ", status=" + super.getStatus() + "]";
	}
}