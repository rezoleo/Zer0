package fr.service_picture.object;

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

import fr.webservicecore.object.APIObject;

/**
 * Description of one picture
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.1
 */
public class PictureInformation implements APIObject
{
	/**
	 * File path of the picture
	 */
	@Expose
	@Since(1.0)
	private String 	file_path	= null;

	/**
	 * Checksum of the picture
	 */
	@Expose
	@Since(1.0)
	private String 	checksum	= null;

	/**
	 * Constructor PictureInformation
	 */
	public PictureInformation() {

	}

	/**
	 * Constructor PictureInformation
	 * @param file_path File path of the picture
	 * @param checksum Checksum of the picture
	 */
	public PictureInformation(String file_path, String checksum) {
		this.file_path = file_path;
		this.checksum  = checksum;
	}

	public String getFile_path() {
		return this.file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getChecksum() {
		return this.checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((checksum == null) ? 0 : checksum.hashCode());
		result = prime * result
				+ ((file_path == null) ? 0 : file_path.hashCode());
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
		PictureInformation other = (PictureInformation) obj;
		if (checksum == null) {
			if (other.checksum != null)
				return false;
		} else if (!checksum.equals(other.checksum))
			return false;
		if (file_path == null) {
			if (other.file_path != null)
				return false;
		} else if (!file_path.equals(other.file_path))
			return false;
		return true;
	}

	public boolean isEmpty() {
		return this.equals(new PictureInformation());
	}
}
