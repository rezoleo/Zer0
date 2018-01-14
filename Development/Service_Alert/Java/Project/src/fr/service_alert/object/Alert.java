package fr.service_alert.object;

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

import java.util.Date;

import fr.webservicecore.object.APIObject;

/**
 * Description of an alert
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.0.0
 */
public class Alert implements APIObject
{
	/**
	 * Id of the alert
	 */
	@Expose
	@Since(1.0)
	private String 	_id			= null;

	/**
	 * Message of the alert
	 */
	@Expose
	@Since(1.0)
	private String 	message		= null;

	/**
	 * Level of the alert
	 */
	@Expose
	@Since(1.0)
	private String 	level		= null;

	/**
	 * Creation date of the alert
	 */
	@Expose
	@Since(1.0)
	private Date 	created		= null;

	/**
	 * Service which created the alert
	 */
	@Expose
	@Since(1.0)
	private String 	creatorService = null;

	/**
	 * Constructor Alert
	 */
	public Alert(){
		
	}
	/**
	 * Constructor Alert
	 * @param _id Id of the alert
	 * @param message Message of the alert
	 * @param level Level of the alert
	 * @param created Creation date of the alert
	 * @param creatorService Service which created the alert
	 */
	public Alert(String _id, String message, String level,
			     Date created, String creatorService){
		this._id 		    = _id;
		this.message 	    = message;
		this.level 		    = level;
		this.created       	= created;
		this.creatorService	= creatorService;
	}

	public String get_id(){
		return this._id;
	}
	public void set_id(String _id){
		this._id = _id;
	}
	public String getMessage(){
		return this.message;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public String getLevel(){
		return this.level;
	}
	public void setLevel(String level){
		this.level = level;
	}
	public Date getCreated(){
		return this.created;
	}
	public void setCreated(Date created){
		this.created = created;
	}
	public String getCreatorService(){
		return this.creatorService;
	}
	public void setCreatorService(String creatorService){
		this.creatorService = creatorService;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result
				+ ((creatorService == null) ? 0 : creatorService.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		Alert other = (Alert) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (creatorService == null) {
			if (other.creatorService != null)
				return false;
		} else if (!creatorService.equals(other.creatorService))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
	@Override
	public boolean isEmpty() {
		return (this._id==null);
	}
}