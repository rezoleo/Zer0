package fr.service_people.object;

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
 * Description of one person
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class Person implements APIObject 
{
	/**
	 * Id of the person
	 */
	@Expose
	@Since(1.0)
	private String 			_id			= null;

	/**
	 * Login of the person
	 */
	@Expose
	@Since(1.0)
	private String 			login		= null;

	/**
	 * Lastname of the person
	 */
	@Expose
	@Since(1.0)
	private String 			lastname	= null;

	/**
	 * Firstname of the person
	 */
	@Expose
	@Since(1.0)
	private String 			firstname	= null;

	/**
	 * Sex of the person
	 */
	@Expose
	@Since(1.0)
	private String 			sex			= null;

	/**
	 * Birthdate of the person
	 */
	@Expose
	@Since(1.0)
	private Date 				birthdate	= null;

	/**
	 * Flag to define if the person is aged 18 or more
	 */
	@Expose
	@Since(1.0)
	private boolean 			major;

	/**
	 * Mail of the person
	 */
	@Expose
	@Since(1.0)
	private String 			mail		= null;

	/**
	 * Phone number of the person
	 */	@Expose
	@Since(1.0)
	private String 			tel			= null;

	/**
	 * Picture path of the person
	 */
	@Expose
	@Since(1.0)
	private String 			picture		= null;

	/**
	 * List of the tags of the person 
	 */
	@Expose
	@Since(1.0)
	private SortedSet<String>	tags		= new TreeSet<String>();

	/**
	 * Creator user which created the person
	 */	
	@Expose
	@Since(1.0)
	private String 			creator		= null;

	/**
	 * Creation date of the person
	 */
	@Expose
	@Since(1.0)
	private Date 				created		= null;

	/**
	 * Service which created the person
	 */
	@Expose
	@Since(1.0)
	private String 			creatorService = null;

	/**
	 * Updator user which updated the person
	 */
	@Expose
	@Since(1.0)
	private String 			updator		= null;

	/**
	 * Update date of the person
	 */
	@Expose
	@Since(1.0)
	private Date 				updated		= null;

	/**
	 * Service which updated the person
	 */
	@Expose
	@Since(1.0)
	private String 			updatorService = null;

	/**
	 * Constructor Person
	 */
	public Person(){
		
	}

	/**
	 * Constructor Person
	 * @param _id Id of the person
	 * @param login Login of the person
	 * @param lastname Lastname of the person
	 * @param firstname Firstname of the person
	 * @param sex Sex of the person
	 * @param birthdate Birthdate of the person
	 * @param major Flag to define if the person is aged 18 or more
	 * @param mail Mail of the person
	 * @param tel Phone number of the person
	 * @param picture Picture path of the person
	 * @param tags List of the tags of the person
	 * @param creator Creator user which created the person
	 * @param created Creation date of the person
	 * @param creatorService Service which created the person
	 * @param updator Updator user which updated the person
	 * @param updated Update date of the person
	 * @param updatorService Service which updated the person
	 */
	public Person(String _id, String login, String lastname, String firstname,
				  String sex, Date birthdate, boolean major, String mail, String tel,
				  String picture, SortedSet<String> tags, String creator, Date created,
				  String creatorService, String updator, Date updated,
				  String updatorService) {
		this._id = _id;
		this.login = login;
		this.lastname = lastname;
		this.firstname = firstname;
		this.sex = sex;
		this.birthdate = birthdate;
		this.major=major;
		this.mail = mail;
		this.tel = tel;
		this.picture = picture;
		this.tags = tags;
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
	public String getLogin(){
		return this.login;
	}
	public void setLogin(String login){
		this.login = login;
	}
	public String getLastname(){
		return this.lastname;
	}
	public void setLastname(String lastname){
		this.lastname = lastname;
	}
	public String getFirstname(){
		return this.firstname;
	}
	public void setFirstname(String firstname){
		this.firstname = firstname;
	}
	public String getSex(){
		return this.sex;
	}
	public void setSex(String sex){
		this.sex = sex;
	}
	public Date getBirthdate(){
		return this.birthdate;
	}
	public void setBirthdate(Date birthdate){
		this.birthdate = birthdate;
	}
	public boolean getMajor(){
		return this.major;
	}
	public void setMajor(boolean major){
		this.major = major;
	}
	public String getMail(){
		return this.mail;
	}
	public void setMail(String mail){
		this.mail = mail;
	}
	public String getTel(){
		return this.tel;
	}
	public void setTel(String tel){
		this.tel = tel;
	}
	public String getPicture(){
		return this.picture;
	}
	public void setPicture(String picture){
		this.picture = picture;
	}
	public SortedSet<String> getTags(){
		return this.tags;
	}
	public void setTags(SortedSet<String> tags){
		this.tags = tags;
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
		result = prime * result
				+ ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result
				+ ((creatorService == null) ? 0 : creatorService.hashCode());
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result
				+ ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + (major ? 1231 : 1237);
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
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
		Person other = (Person) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
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
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
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
		if (major != other.major)
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
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