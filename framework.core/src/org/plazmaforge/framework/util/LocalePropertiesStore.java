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

package org.plazmaforge.framework.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 
 * @author ohapon
 *
 */
public class LocalePropertiesStore implements Serializable {


    private static final long serialVersionUID = -2016268072121624960L;

    private Locale locale;
    
	
    /** Common Title comment lines **/
    private List<String> titleComments;

	    
    /** Common Summary comment lines **/
    private List<String> summaryComments;
    
    

    private Map<String, PropertyValue> propertyMap;

    
    private boolean modify;
    
    public LocalePropertiesStore(Locale locale) {
	super();
	this.locale = locale;
    }


    public Locale getLocale() {
        return locale;
    }


    public List<String> getTitleComments() {
        return titleComments;
    }


    public void setTitleComments(List<String> titleComments) {
        this.titleComments = titleComments;
    }


    public List<String> getSummaryComments() {
        return summaryComments;
    }


    public void setSummaryComments(List<String> summaryComments) {
        this.summaryComments = summaryComments;
    }


    protected Map<String, PropertyValue> getPropertyMap() {
	if (propertyMap == null) {
	    propertyMap = new HashMap<String, PropertyValue>(); 
	}
        return propertyMap;
    }


    public PropertyValue getPropertyValue(String key) {
	PropertyValue propertyValue = getPropertyMap().get(key);
	if (propertyValue == null) {
	    propertyValue = new PropertyValue();
	    getPropertyMap().put(key, propertyValue);
	}
	return propertyValue;
    }
   


    public void setValue(String key, String value, List<String> comments) {
	PropertyValue propertyValue = getPropertyValue(key);
	propertyValue.setValue(value);
	propertyValue.setElementComments(comments);
	
	propertyValue.loadValue();
    }
   
    public void renameKey(String oldKey, String newKey) {
	if (getPropertyMap().get(newKey) != null) {
	    throw new IllegalArgumentException("Duplicate key: " + newKey);
	}
	PropertyValue propertyValue = getPropertyMap().get(oldKey);
	getPropertyMap().remove(oldKey);
	getPropertyMap().put(newKey, propertyValue);
	
    }


    public void setLocale(Locale locale) {
        this.locale = locale;
    }


    public boolean isModify() {
        return modify;
    }


    public void setModify(boolean modify) {
        this.modify = modify;
    }
    
    
}
