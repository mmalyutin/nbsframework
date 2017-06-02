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

import java.io.Serializable;

/**
 * @author ohapon
 *
 */
public class DSFieldFilter extends DSFilter implements HasField {

    private static final long serialVersionUID = 5642623600606100517L;
    
    private DSField field;

    private String operator;
    
    private Serializable value;
    
    
    public DSFieldFilter() {
	super();
    }
    
    public DSFieldFilter(DSField field) {
	super();
	this.field = field;
    }

    public DSField getField() {
        return field;
    }

    public void setField(DSField field) {
        this.field = field;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Serializable getValue() {
        return value;
    }

    public void setValue(Serializable value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((field == null) ? 0 : field.hashCode());
	result = prime * result
		+ ((operator == null) ? 0 : operator.hashCode());
	result = prime * result + ((value == null) ? 0 : value.hashCode());
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
	DSFieldFilter other = (DSFieldFilter) obj;
	if (field == null) {
	    if (other.field != null)
		return false;
	} else if (!field.equals(other.field))
	    return false;
	if (operator == null) {
	    if (other.operator != null)
		return false;
	} else if (!operator.equals(other.operator))
	    return false;
	if (value == null) {
	    if (other.value != null)
		return false;
	} else if (!value.equals(other.value))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "DSFieldFilter[field=" + field 
		+ ", operator=" + operator
		+ ", value=" + value + "]";
    }

}
