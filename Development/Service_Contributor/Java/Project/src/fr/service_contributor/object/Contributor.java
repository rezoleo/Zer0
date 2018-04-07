package fr.service_contributor.object;

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


import com.google.gson.annotations.*;
import java.util.Date;

import fr.webservicecore.object.APIObject;

/**
 * Description of a contributor
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.1
 */
public class Contributor implements APIObject
{
	/**
	 * Id of the contributor
	 */
	@Expose
	@Since(1.0)
	private String 	_id				= null;

	/**
	 * Login of the contributor
	 */
	@Expose
	@Since(1.0)
	private String 	login			= null;

	/**
	 * Creator user which created the contributor
	 */	
	@Expose
	@Since(1.0)
	private String 	creator			= null;

	/**
	 * Creation date of the contributor
	 */
	@Expose
	@Since(1.0)
	private Date 		created			= null;

	/**
	 * Service which created the contributor
	 */
	@Expose
	@Since(1.0)
	private String 	creatorService 	= null;

	/**
	 * Constructor Contributor
	 */
	public Contributor(){
		
	}
	/**
	 * Constructor Contributor
	 * @param _id Id of the contributor
	 * @param login Login of the contributor
	 * @param creator Creator user which created the contributor
	 * @param created Creation date of the contributor
	 * @param creatorService Service which created the contributor
	 */
	public Contributor(String _id,     String login,
					   String creator, Date created, String creatorService){
		this._id 			= _id;
		this.login 			= login;
		this.creator 		= creator;
		this.created 		= created;
		this.creatorService = creatorService;
	}

	public String get_id(){
		return this._id;
	}
	public void set_id(String _id){
		this._id = _id;
	}

	public String getLogin(){
		return this.login;
	}
	public void setLogin(String login){
		this.login = login;
	}

	public String getCreator(){
		return this.creator;
	}
	public void setCreator(String creator){
		this.creator = creator;
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
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result
				+ ((creatorService == null) ? 0 : creatorService.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj){
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contributor other = (Contributor) obj;
		if (_id == null){
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (created == null){
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (creator == null){
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (creatorService == null){
			if (other.creatorService != null)
				return false;
		} else if (!creatorService.equals(other.creatorService))
			return false;
		if (login == null){
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	public boolean isEmpty(){
		return (this._id==null);
	}
}
