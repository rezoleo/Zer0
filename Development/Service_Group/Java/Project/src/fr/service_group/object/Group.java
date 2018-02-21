package fr.service_group.object;

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


import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

import fr.webservicecore.object.APIObject;

/**
 * Description of a group
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.1
 */
public class Group implements APIObject 
{
	/**
	 * Id of the group
	 */
	@Expose
	@Since(1.0)
	private String 					_id				= null;

	/**
	 * Name of the group
	 */
	@Expose
	@Since(1.0)
	private String 					name			= null;

	/**
	 * Type of the group
	 */
	@Expose
	@Since(1.0)
	private String 					type			= null;	

	/**
	 * Description of the group
	 */
	@Expose
	@Since(1.0)
	private String 					description		= null;

	/**
	 * Mail of the group
	 */
	@Expose
	@Since(1.0)
	private String 					mail			= null;

	/**
	 * Logo path of the group
	 */
	@Expose
	@Since(1.0)
	private String 					logo			= null;

	/**
	 * Picture path of the group
	 */
	@Expose
	@Since(1.0)
	private String 					picture			= null;

	/**
	 * List of the login of the members of the group 
	 */
	@Expose
	@Since(1.0)
	private SortedSet<String>			members			= new TreeSet<String>();

	/**
	 * List of the login of the responsibles of the group 
	 */
	@Expose
	@Since(1.0)
	private SortedSet<Responsible>	responsibles	= new TreeSet<Responsible>();

	/**
	 * Creator user which created the group
	 */	
	@Expose
	@Since(1.0)
	private String 					creator			= null;

	/**
	 * Creation date of the group
	 */
	@Expose
	@Since(1.0)
	private Date 						created			= null;

	/**
	 * Service which created the group
	 */
	@Expose
	@Since(1.0)
	private String 					creatorService 	= null;

	/**
	 * Updator user which updated the group
	 */
	@Expose
	@Since(1.0)
	private String 					updator			= null;

	/**
	 * Update date of the group
	 */
	@Expose
	@Since(1.0)
	private Date 						updated			= null;

	/**
	 * Service which updated the group
	 */
	@Expose
	@Since(1.0)
	private String 					updatorService 	= null;

	/**
	 * Constructor Group
	 */
	public Group(){
		
	}
	/**
	 * Constructor Group
	 * @param _id Id of the group
	 * @param name Name of the group
	 * @param type Type of the group
	 * @param description Description of the group
	 * @param mail Mail of the group
	 * @param logo Logo path of the group
	 * @param picture Picture path of the group
	 * @param members List of the login of the members of the group
	 * @param responsibles List of the login of the responsibles of the group
	 * @param creator Creator user which created the group
	 * @param created Creation date of the group
	 * @param creatorService Service which created the group
	 * @param updator Updator user which updated the group
	 * @param updated Update date of the group
	 * @param updatorService Service which updated the group
	 */
	public Group(String _id, String name, String type, String description, String mail, String logo, String picture,
				 SortedSet<String> members, SortedSet<Responsible> responsibles,
				 String creator, Date created, String creatorService, 
				 String updator, Date updated, String updatorService){
		this._id = _id;
		this.name = name;
		this.type = type;
		this.description = description;
		this.mail = mail;
		this.logo = logo;
		this.picture = picture;
		this.members = members;
		this.responsibles = responsibles;
		this.creator = creator;
		this.created = created;
		this.creatorService = creatorService;
		this.updator = updator;
		this.updated = updated;
		this.updatorService = updatorService;
	}

	public String get_id(){
		return this._id;
	}
	public void set_id(String _id){
		this._id = _id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public String getMail(){
		return this.mail;
	}
	public void setMail(String mail){
		this.mail = mail;
	}
	public String getLogo(){
		return this.logo;
	}
	public void setLogo(String logo){
		this.logo = logo;
	}
	public String getPicture(){
		return this.picture;
	}
	public void setPicture(String picture){
		this.picture = picture;
	}
	public SortedSet<String> getMembers(){
		return this.members;
	}
	public void setMembers(SortedSet<String> members){
		this.members = members;
	}
	public SortedSet<Responsible> getResponsibles(){
		return this.responsibles;
	}
	public void setResponsibles(SortedSet<Responsible> responsibles){
		this.responsibles = responsibles;
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
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result
				+ ((creatorService == null) ? 0 : creatorService.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((logo == null) ? 0 : logo.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((members == null) ? 0 : members.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result
				+ ((responsibles == null) ? 0 : responsibles.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((updated == null) ? 0 : updated.hashCode());
		result = prime * result + ((updator == null) ? 0 : updator.hashCode());
		result = prime * result
				+ ((updatorService == null) ? 0 : updatorService.hashCode());
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
		Group other = (Group) obj;
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
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (logo == null) {
			if (other.logo != null)
				return false;
		} else if (!logo.equals(other.logo))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (members == null) {
			if (other.members != null)
				return false;
		} else if (!members.equals(other.members))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (responsibles == null) {
			if (other.responsibles != null)
				return false;
		} else if (!responsibles.equals(other.responsibles))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
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

	public boolean isEmpty(){
		return (this._id==null);
	}
}
