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

import org.plazmaforge.framework.core.data.HasDataType;

/**
 * @author ohapon
 *
 */
public class DSExpression implements HasDataType, Serializable {

    private static final long serialVersionUID = 6898754150212143073L;

    public static final int EVALUATION_DEFAULT = 0; 
    
    public static final int EVALUATION_OLD = -1;
    
    public static final int EVALUATION_INIT = 1;
    
    
    private String id;

    private String dataType;
    
    private String text;
    
    
    public DSExpression() {
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public boolean hasText() {
	return text != null && !text.isEmpty();
    }
    
    public boolean isEmpty() {
	return !hasText();
    }

    public static boolean hasText(DSExpression expression) {
	return expression != null && expression.hasText();
    }
    
    public static boolean isEmpty(DSExpression expression) {
	return expression == null || expression.isEmpty();
    }

    public String toString() {
	StringBuilder b = new StringBuilder();
	b.append("DSExpression=[");
	boolean flag = false;
	if (id != null) {
	    b.append("id=" + id);
	    flag = true;
	}
	if (dataType != null) {
	    if (flag) {
		b.append(", ");
	    }
	    b.append("dataType=" + dataType);
	    flag = true;
	}
	if (text != null) {
	    if (flag) {
		b.append(", ");
	    }
	    b.append("text=" + text);
	    flag = true;
	}
	b.append("]");
	return b.toString();
    }


    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((dataType == null) ? 0 : dataType.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((text == null) ? 0 : text.hashCode());
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
	DSExpression other = (DSExpression) obj;
	if (dataType == null) {
	    if (other.dataType != null)
		return false;
	} else if (!dataType.equals(other.dataType))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (text == null) {
	    if (other.text != null)
		return false;
	} else if (!text.equals(other.text))
	    return false;
	return true;
    }
    
    
}
