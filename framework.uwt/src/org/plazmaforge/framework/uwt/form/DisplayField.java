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

import org.plazmaforge.framework.core.data.ValuePresenter;

/**
 * 
 * Display field info 
 * 
 * 
 * @author ohapon
 *
 */
public class DisplayField {

    /**
     * Name of property
     */
    private String property;
    
    /**
     * Label/Text of filed
     */
    private String label;

    
    /**
     * Type of property
     */
    private String type;

    /**
     * Width of filed
     */
    private int width;
    
    /**
     * ValuePresenter (toString/toValue)
     */
    private ValuePresenter valuePresenter;
    
    /**
     * True if field is sortable
     */
    private boolean sortable;
    
    /**
     * True if field is filterable
     */
    private boolean filterable;
    
    /**
     * True if field is selected
     */
    private boolean selected;
    
    
    
    public DisplayField() {
	super();
    }

    public DisplayField(String property) {
	super();
	this.property = property;
    }

    public DisplayField(String property, String label) {
	super();
	this.property = property;
	this.label = label;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public ValuePresenter getValuePresenter() {
        return valuePresenter;
    }

    public void setValuePresenter(ValuePresenter valuePresenter) {
        this.valuePresenter = valuePresenter;
    }
    
    protected Object toValue(String str) {
	if (str == null) {
	    return null;
	}
	str = str.trim();
	return (valuePresenter == null) ? str: valuePresenter.toValue(str);
    }
    
    

    public boolean isSortable() {
        return sortable;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }

    
    public boolean isFilterable() {
        return filterable;
    }

    public void setFilterable(boolean filterable) {
        this.filterable = filterable;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    
    
    
}
