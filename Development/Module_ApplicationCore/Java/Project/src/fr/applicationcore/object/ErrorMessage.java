package fr.applicationcore.object;

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

import fr.applicationcore.error.ErrorInterface;
import fr.core.error.Error;

/* 
 * Class 	: ErrorMessage
 * Author(s): Zidmann
 * Function : This class describes an error message coming from a NodeJS server using the module entitled 'ApplicationCore'
 * Version  : 1.0.0
 */
public class ErrorMessage extends fr.core.object.ErrorMessage implements ErrorInterface
{
	@Expose
	@Since(1.0)
	protected String application	= null;

	public ErrorMessage(String application, String version, String code, String message,
				   String stack,  int status){
		super(version, code, message, stack, status);
		this.application = application;
	}
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
		result = prime * result + ((super.code == null) ? 0 : super.code.hashCode());
		result = prime * result + ((this.application == null) ? 0 : this.application.hashCode());
		result = prime * result + ((super.stack == null) ? 0 : super.stack.hashCode());
		result = prime * result + ((super.message == null) ? 0 : super.message.hashCode());
		result = prime * result + super.status;
		result = prime * result + ((super.version == null) ? 0 : super.version.hashCode());
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
		if (super.version == null) {
			if (other.version != null)
				return false;
		} else if (!super.version.equals(other.version))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ErrorMessage [application="+ this.application +", version=" + super.version + ", code=" + super.code + ", message="
				+ super.message + ", stack=" + super.stack + ", status=" + super.status + "]";
	}
}