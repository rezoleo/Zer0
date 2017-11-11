package fr.webservicecore.junit.object;

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

/**
 * Description of the 'field7' of an ObjectDB
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.0
 */
public class SubField {
	@Expose
	@Since(1.0)
	protected int	field8;
    @Expose
	@Since(1.0)
	protected int	field9;

    public SubField(){
	
    }
	public SubField(int field8, int field9){
		this.field8 = field8;
		this.field9 = field9;
	}

	public int getField8(){
		return this.field8;
	}
	public void setField8(int field8){
		this.field8 = field8;
	}
	public int getField9(){
		return this.field9;
	}
	public void setField9(int field9){
		this.field9 = field9;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + field8;
		result = prime * result + field9;
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
		SubField other = (SubField) obj;
		if (field8 != other.field8)
			return false;
		if (field9 != other.field9)
			return false;
		return true;
	}
}