package fr.applicationcore.junit.object;

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

import fr.applicationcore.object.APIObject;

/**
 * Description of a basic object which represents the gate information on the server
 * @author Zidmann (Emmanuel ZIDEL-CAUFFET)
 * @version 1.1.1
 */
public class Gate implements APIObject
{
	@Expose
	@Since(1.0)
	protected String gate 	= null;
	@Expose
	@Since(1.0)
	protected String system	= null;
    
    public Gate(){
	
    }
	public Gate(String gate, String system) {
		this.gate = gate;
		this.system= system;
	}

	public String getGate() {
		return this.gate;
	}
	public void setGate(String gate) {
		this.gate = gate;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gate == null) ? 0 : gate.hashCode());
		result = prime * result + ((system == null) ? 0 : system.hashCode());
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
		Gate other = (Gate) obj;
		if (gate == null) {
			if (other.gate != null)
				return false;
		} else if (!gate.equals(other.gate))
			return false;
		if (system == null) {
			if (other.system != null)
				return false;
		} else if (!system.equals(other.system))
			return false;
		return true;
	}

	public boolean isEmpty() {
		return (this.system==null);
	}
}
