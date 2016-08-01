package fr.applicationcore.junit.object;

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
import java.util.Date;

import fr.applicationcore.object.APIObject;

/* 
 * Class 	: ObjectDB
 * Author(s): Zidmann
 * Function : This class describes an objectDB element
 * Version  : 1.0.0
 */
public class ObjectDB implements APIObject
{
	@Expose
	@Since(1.0)
	protected String 	_id  		= null;
	@Expose
	@Since(1.0)
	protected String 	field0		= null;
    @Expose
	@Since(1.0)
	protected String 	field1		= null;
    @Expose
	@Since(1.0)
	protected String 	field2		= null;
    @Expose
	@Since(1.0)
	protected String 	field3		= null;
    @Expose
	@Since(1.0)
	protected String 	field4		= null;
    @Expose
	@Since(1.0)
	protected Date	 	field5		= null;
    @Expose
	@Since(1.0)
	protected boolean 	field6;
    @Expose
	@Since(1.0)
	protected SubField 	field7		= null;

    public ObjectDB(){
	
    }
	public ObjectDB(String _id,
					String field0, String field1, String field2, String field3,
					String field4, Date field5, boolean field6,
					SubField field7){
		this._id    = _id;
		this.field0 = field0;
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
		this.field4 = field4;
		this.field5 = field5;
		this.field6 = field6;
		this.field7 = field7;
	}

	public String get_id(){
		return this._id;
	}
	public void set_id(String _id){
		this._id = _id;
	}
	public String getField0(){
		return this.field0;
	}
	public void setField0(String field0){
		this.field0 = field0;
	}
	public String getField1(){
		return this.field1;
	}
	public void setField1(String field1){
		this.field1 = field1;
	}
	public String getField2(){
		return this.field2;
	}
	public void setField2(String field2){
		this.field2 = field2;
	}
	public String getField3(){
		return this.field3;
	}
	public void setField3(String field3){
		this.field3 = field3;
	}
	public String getField4(){
		return this.field4;
	}
	public void setField4(String field4){
		this.field4 = field4;
	}
	public Date getField5(){
		return this.field5;
	}
	public void setField5(Date field5){
		this.field5 = field5;
	}
	public boolean isField6(){
		return this.field6;
	}
	public void setField6(boolean field6){
		this.field6 = field6;
	}
	public SubField getField7(){
		return this.field7;
	}
	public void setField7(SubField field7){
		this.field7 = field7;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field0 == null) ? 0 : field0.hashCode());
		result = prime * result + ((field1 == null) ? 0 : field1.hashCode());
		result = prime * result + ((field2 == null) ? 0 : field2.hashCode());
		result = prime * result + ((field3 == null) ? 0 : field3.hashCode());
		result = prime * result + ((field4 == null) ? 0 : field4.hashCode());
		result = prime * result + ((field5 == null) ? 0 : field5.hashCode());
		result = prime * result + (field6 ? 1231 : 1237);
		result = prime * result + ((field7 == null) ? 0 : field7.hashCode());
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
		ObjectDB other = (ObjectDB) obj;
		if (field0 == null) {
			if (other.field0 != null)
				return false;
		} else if (!field0.equals(other.field0))
			return false;
		if (field1 == null) {
			if (other.field1 != null)
				return false;
		} else if (!field1.equals(other.field1))
			return false;
		if (field2 == null) {
			if (other.field2 != null)
				return false;
		} else if (!field2.equals(other.field2))
			return false;
		if (field3 == null) {
			if (other.field3 != null)
				return false;
		} else if (!field3.equals(other.field3))
			return false;
		if (field4 == null) {
			if (other.field4 != null)
				return false;
		} else if (!field4.equals(other.field4))
			return false;
		if (field5 == null) {
			if (other.field5 != null)
				return false;
		} else if (!field5.equals(other.field5))
			return false;
		if (field6 != other.field6)
			return false;
		if (field7 == null) {
			if (other.field7 != null)
				return false;
		} else if (!field7.equals(other.field7))
			return false;
		return true;
	}

	@Override
	public boolean isEmpty() {
		return (this._id==null);
	}
}