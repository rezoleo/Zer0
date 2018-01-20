package fr.service_authentification.object;

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
 * Description of an access
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class Authentification implements APIObject
{
	/**
	 * Id of the access
	 */
	@Expose
	@Since(1.0)
	private String 	_id			= null;

	/**
	 * Login of the access
	 */
	@Expose
	@Since(1.0)
	private String 	login		= null;

	/**
	 * Mail of the access
	 */	
	@Expose
	@Since(1.0)
	private String 	mail		= null;

	/**
	 * Status of the access
	 */	
	@Expose
	@Since(1.0)
	private String 	status		= null;

	/**
	 * Creator user which created the access
	 */	
	@Expose
	@Since(1.0)
	private String 	creator		= null;

	/**
	 * Creation date of the access
	 */
	@Expose
	@Since(1.0)
	private Date 		created		= null;

	/**
	 * Service which created the access
	 */
	@Expose
	@Since(1.0)
	private String 	creatorService = null;

	/**
	 * Updator user which updated the access
	 */	
	@Expose
	@Since(1.0)
	private String 	updator		= null;

	/**
	 * Update date of the access
	 */
	@Expose
	@Since(1.0)
	private Date 		updated		= null;

	/**
	 * Service which updated the access
	 */
	@Expose
	@Since(1.0)
	private String 	updatorService = null;

	/**
	 * Constructor Authentification
	 */
	public Authentification(){
		
	}
	/**
	 * Constructor Authentification
	 * @param _id Id of the access
	 * @param login Login of the access
	 * @param mail Mail of the access
	 * @param status Status of the access
	 * @param creator Creator user which created the access
	 * @param created Creation date of the access
	 * @param creatorService Service which created the access
	 * @param updator Updator user which updated the access
	 * @param updated Update date of the access
	 * @param updatorService Service which updated the access
	 */
	public Authentification(String _id, String login, String mail,
				            String status,
					        String creator, Date created, String creatorService, 
					        String updator, Date updated, String updatorService){
		this._id 		    = _id;
		this.login 		    = login;
		this.mail 		    = mail;
		this.status			= status;
		this.creator      	= creator;
		this.created       	= created;
		this.creatorService	= creatorService;
		this.updator 	    = updator;
		this.updated 	    = updated;
		this.updatorService	= updatorService;
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
	public String getMail(){
		return this.mail;
	}
	public void setMail(String mail){
		this.mail = mail;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getUpdator(){
		return this.updator;
	}
	public void setUpdator(String updator){
		this.updator = updator;
	}
	public Date getUpdated(){
		return this.updated;
	}
	public void setUpdated(Date updated){
		this.updated = updated;
	}
	public String getUpdatorService(){
		return this.updatorService;
	}
	public void setUpdatorService(String updatorService){
		this.updatorService = updatorService;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result
				+ ((creatorService == null) ? 0 : creatorService.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((updated == null) ? 0 : updated.hashCode());
		result = prime * result + ((updator == null) ? 0 : updator.hashCode());
		result = prime * result
				+ ((updatorService == null) ? 0 : updatorService.hashCode());
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
		Authentification other = (Authentification) obj;
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
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (creatorService == null) {
			if (other.creatorService != null)
				return false;
		} else if (!creatorService.equals(other.creatorService))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (updated == null) {
			if (other.updated != null)
				return false;
		} else if (!updated.equals(other.updated))
			return false;
		if (updator == null) {
			if (other.updator != null)
				return false;
		} else if (!updator.equals(other.updator))
			return false;
		if (updatorService == null) {
			if (other.updatorService != null)
				return false;
		} else if (!updatorService.equals(other.updatorService))
			return false;
		return true;
	}
	@Override
	public boolean isEmpty() {
		return (this._id==null);
	}
}