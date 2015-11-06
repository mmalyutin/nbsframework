/*
 * Copyright (C) 2005-2010 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */
package org.plazmaforge.framework.ext.model;


/**
 * @author ohapon
 */
public class Dictionary extends ComplexEntity implements IDictionary {

    private static final long serialVersionUID = 1758155781139505475L;

    private String code;

    private String name;

    /* English name for international mode */
    private String nameEn;
    
    private String fullName;

    private String description;
    
    private String note;

    
    
    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    
    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getFullName() {
	return fullName;
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
	return note;
    }

    public void setNote(String note) {
	this.note = note;
    }

    public String toString() {
	return getName();
    }

    public String getStringPresentation() {
	return getName();
    }
    
    public String getCodeStringPresentation() {
	if (isEmpty(getCode())) {
	    return getName();
	}
	return getCode().trim() + (isEmpty(getName()) ? "" : (" " + getName()));
	
    }
    
    public boolean equals(Object o) {
	if (!super.equals(o)) {
	    return false;
	}
	if (o instanceof Dictionary) {
	    Dictionary d = (Dictionary) o;
	    if ((getName() == null && d.getName() == null 
		    || (getName() != null && getName().equals(d.getName())))) {
		return true;
	    }
	}
	return false;
    }

    public void populate(Dictionary dictionary) {
	dictionary.setId(getId());
	dictionary.setCode(getCode());
	dictionary.setName(getName());
	dictionary.setFullName(getFullName());
	dictionary.setDescription(getDescription());
	dictionary.setNote(getNote());
    }
    

}
