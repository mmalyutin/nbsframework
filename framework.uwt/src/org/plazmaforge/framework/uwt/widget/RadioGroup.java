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

public class RadioGroup<T> extends Composite implements IField<T>, IRefField<T> {

    
    public static final String PROPERTY_RADIO_BUTTONS = "radioButtons";
    
    
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
    
    /**
     * Display property of value of entity
     */
    private String displayProperty;
    
    
    private String refProperty;
    
    private Object refValue;

    @Override
    protected void checkChild(Widget element) {
	if (!(element instanceof RadioButton)) {
	    throw new IllegalArgumentException("Child must be RadioButton");
	}
    }
    
    @Override
    public void setLayout(Layout layout) {
	//ignore
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public T getValue() {
	// TODO: Must use selected radio button
        return value;
    }

    public void setValue(T value) {
	// TODO: Must use selected radio button
        this.value = value;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDisplayProperty() {
        return displayProperty;
    }

    public void setDisplayProperty(String displayProperty) {
        this.displayProperty = displayProperty;
    }

    public String getRefProperty() {
        return refProperty;
    }

    public void setRefProperty(String refProperty) {
        this.refProperty = refProperty;
    }

    public Object getRefValue() {
        return refValue;
    }

    public void setRefValue(Object refValue) {
        this.refValue = refValue;
    }

    

    
}
