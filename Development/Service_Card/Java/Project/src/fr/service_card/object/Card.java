package fr.service_card.object;

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
 * Description of a card
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class Card implements APIObject
{
	/**
	 * Id of the card
	 */
	@Expose
	@Since(1.0)
	private String 	_id				= null;

	/**
	 * Login of the owner of the card
	 */
	@Expose
	@Since(1.0)
	private String 	owner			= null;

	/**
	 * Code of the card
	 */
	@Expose
	@Since(1.0)
	private String 	code			= null;

	/**
	 * Status of the card
	 */
	@Expose
	@Since(1.0)
	private String 	status			= null;

	/**
	 * Creator user which created the access
	 */	
	@Expose
	@Since(1.0)
	private String 	creator			= null;

	/**
	 * Creation date of the access
	 */
	@Expose
	@Since(1.0)
	private Date 		created			= null;

	/**
	 * Service which created the access
	 */
	@Expose
	@Since(1.0)
	private String 	creatorService 	= null;

	/**
	 * Updator user which updated the access
	 */
	@Expose
	@Since(1.0)
	private String 	updator			= null;

	/**
	 * Update date of the access
	 */
	@Expose
	@Since(1.0)
	private Date 		updated			= null;

	/**
	 * Service which updated the access
	 */
	@Expose
	@Since(1.0)
	private String 	updatorService	= null;

	/**
	 * Constructor Card
	 */
	public Card(){
		
	}
	/**
	 * Constructor Card
	 * @param _id Id of the card
	 * @param owner Login of the owner of the card
	 * @param code Code of the card
	 * @param status Status of the card
	 * @param creator Creator user which created the access
	 * @param created Creation date of the access
	 * @param creatorService Service which created the access
	 * @param updator Updator user which updated the access
	 * @param updated Update date of the access
	 * @param updatorService Service which updated the access
	 */
	public Card(String _id,     String owner, String code,    String status,
				String creator, Date created, String creatorService, 
				String updator, Date updated, String updatorService){
		this._id 			= _id;
		this.owner 			= owner;
		this.code 			= code;
		this.status 		= status;
		this.creator 		= creator;
		this.created 		= created;
		this.creatorService = creatorService;
		this.updator 		= updator;
		this.updated 		= updated;
		this.updatorService = updatorService;
	}

	public String get_id(){
		return this._id;
	}
	public void set_id(String _id){
		this._id = _id;
	}
	public String getOwner(){
		return this.owner;
	}
	public void setOwner(String owner){
		this.owner = owner;
	}
	public String getCode(){
		return this.code;
	}
	public void setCode(String code){
		this.code = code;
	}
	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
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
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result
				+ ((creatorService == null) ? 0 : creatorService.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		Card other = (Card) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
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
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
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