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

package org.plazmaforge.framework.uwt.widget;

public abstract class AbstractField<T> extends Control implements IField<T> {

    /**
     * Property of entity
     */
    private String property;
    
    /**
     * Value of entity 
     */
    private T value;
    
    /**
     * Name of type of value
     * For example: String, Integer, Float, Double, Date, Boolean and etc.
     */
    private String dataType;
    

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public T getValue() {
	if (isReadyInput()) {
	    // Get value from delegate
	    return getSelectionValue();
	}
        return doGetValue();
    }

    public void setValue(T value) {
	doSetValue(value);
	if (isReadyInput()) {
	    // Set value to delegate
	    setSelectionValue(value);
	}
    }
    
    
    public String getDataType() {
        return dataType;
    }


    public void setDataType(String dataType) {
        this.dataType = dataType;
        fireChangeProperty(PROPERTY_DATA_TYPE, dataType);
    }    
    
    protected T doGetValue() {
        return value;
    }

    protected void doSetValue(T value) {
	doSetValue(value, true);
    }

    protected void doSetValue(T value, boolean fireEvent) {
	checkValue(value);
        this.value = value;
        if (!fireEvent) {
            return;
        }
        fireChangeProperty(PROPERTY_VALUE, value);
    }

    protected void checkValue(T value) {

    }

    protected boolean isReadyInput() {
	return isVisible();
    }
    
    /**
     * Get selection value from delegate
     * @return
     */
    protected T getSelectionValue() {
	return (T) getDelegateProperty(PROPERTY_VALUE);
    }
    
    /**
     * Set selection value to delegate
     * @param selectionValue
     */
    protected void setSelectionValue(T selectionValue) {
	setDelegateProperty(PROPERTY_VALUE, selectionValue);
    }
    

    
   
}
