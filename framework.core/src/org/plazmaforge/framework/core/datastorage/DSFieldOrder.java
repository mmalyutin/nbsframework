/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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

/**
 * 
 */
package org.plazmaforge.framework.core.datastorage;

/**
 * @author ohapon
 *
 */
public class DSFieldOrder extends DSOrder implements HasField {

    private static final long serialVersionUID = -6288618096242023912L;
    
    private DSField field;

    public DSFieldOrder() {
	super();
    }
    
    
    public DSFieldOrder(DSField field) {
	super();
	this.field = field;
    }

    public DSField getField() {
        return field;
    }

    public void setField(DSField field) {
        this.field = field;
    }


    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((field == null) ? 0 : field.hashCode());
	return result;
    }


    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	DSFieldOrder other = (DSFieldOrder) obj;
	if (field == null) {
	    if (other.field != null)
		return false;
	} else if (!field.equals(other.field))
	    return false;
	return true;
    }


    @Override
    public String toString() {
	return "DSFieldOrder[field=" + field + ", asc=" + isAsc() + "]";
    }
    
    

}
