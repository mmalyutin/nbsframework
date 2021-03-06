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

import org.plazmaforge.framework.core.data.ComplexLocalizedIdentifier;
import org.plazmaforge.framework.core.data.HasDataType;

/**
 * @author ohapon
 *
 */
public class DSParameter extends ComplexLocalizedIdentifier implements HasDataType, Serializable {

    private static final long serialVersionUID = 6198276815816959375L;
    

    private String dataType;
    
    private Object defaultValue;

    
    
    public DSParameter() {
	super();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }
    
    

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result
		+ ((dataType == null) ? 0 : dataType.hashCode());
	result = prime * result
		+ ((defaultValue == null) ? 0 : defaultValue.hashCode());
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
	DSParameter other = (DSParameter) obj;
	if (dataType == null) {
	    if (other.dataType != null)
		return false;
	} else if (!dataType.equals(other.dataType))
	    return false;
	if (defaultValue == null) {
	    if (other.defaultValue != null)
		return false;
	} else if (!defaultValue.equals(other.defaultValue))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "DSParameter[dataType=" + dataType 
		+ ", defaultValue=" + defaultValue + "]";
    }
    
}
