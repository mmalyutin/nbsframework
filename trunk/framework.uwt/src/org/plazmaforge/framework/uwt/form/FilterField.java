/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.uwt.form;

public class FilterField extends DisplayField {

    private Object value;
    
    private String stringValue;
    
    private String operation;
    
    // Only for string value
    private boolean caseInsensitive;

    
    public FilterField() {
	super();
    }

    public FilterField(String property, String label) {
	super(property, label);
    }

    public FilterField(String property) {
	super(property);
    }


    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public boolean isCaseInsensitive() {
        return caseInsensitive;
    }

    public void setCaseInsensitive(boolean caseInsensitive) {
        this.caseInsensitive = caseInsensitive;
    }

    
    /**
     * Parse string and set value 
     */
    public void loadValue() {
	value = toValue(stringValue);
    }
    
    
    
}
