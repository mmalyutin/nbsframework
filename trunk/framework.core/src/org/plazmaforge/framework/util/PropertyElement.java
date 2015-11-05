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
import java.util.List;
import java.util.Locale;

import org.plazmaforge.framework.util.BundleUtils;

/**
 * The class describes an property element (key and value)
 * 
 * @author ohapon
 *  
 */
public class PropertyElement implements Serializable {

    private static final long serialVersionUID = 2603661689496354648L;

    private IPropertiesStore store;
    
    /** Key **/
    private String key;
    
    /** Presentation of key in national language **/ // ??? Why ???
    private String displayKey;
	
    /** **/
    private PropertyValueStore propertyValueStore;
    
    
    public PropertyElement(IPropertiesStore store, String key) {
	super();
	this.store = store;
	this.propertyValueStore = new PropertyValueStore(this);
	this.key = key;
	loadKey();
    }

    public IPropertiesStore getStore() {
	return store;
    }

    public String getKey() {
	return key;
    }

    public void setKey(String key) {
	this.key = key;
    }

    public PropertyValueStore getPropertyValueStore() {
	return propertyValueStore;
    }

    //// PropertyValue
    
    public PropertyValue getPropertyValue(int index) {
	return getPropertyValueStore().getPropertyValue(index);
    }

    public PropertyValue getPropertyValue(Locale locale) {
	return getPropertyValueStore().getPropertyValue(locale);
    }

    //// Value

    public String getValue(int index) {
	return getPropertyValue(index).getValue();
    }

    public void setValue(int index, String value) {
	getPropertyValue(index).setValue(value);
    }

    
    public String getValue(Locale locale) {
	return getPropertyValue(locale).getValue();
    }

    public void setValue(Locale locale, String value) {
	getPropertyValue(locale).setValue(value);
    }
    
    //// DisplayValue

    public String getDisplayValue(int index) {
	return getPropertyValue(index).getDisplayValue();
    }

    
    public void setDisplayValue(int index, String displayValue) {
	getPropertyValue(index).setDisplayValue(displayValue);
    }
    

    
    public String getDisplayValue(Locale locale) {
	return getPropertyValue(locale).getDisplayValue();
    }

    
    public void setDisplayValue(Locale locale, String displayValue) {
	getPropertyValue(locale).setDisplayValue(displayValue);
    }
    
    
    //// Comments
    
    public List<String> getElementComments(int index) {
	return getPropertyValue(index).getElementComments();
    }

    public void setElementComments(int index, List<String> elementComments) {
	getPropertyValue(index).setElementComments(elementComments);
    }

    public List<String> getElementComments(Locale locale) {
	return getPropertyValue(locale).getElementComments();
    }

    public void setElementComments(Locale locale, List<String> elementComments) {
	getPropertyValue(locale).setElementComments(elementComments);
    }
    
    ////

    // TODO: STUB: Return first element
    /*
    public PropertyValue getPropertyValue() {
	return getPropertyValueStore().getPropertyValue(0);
    }

    public String getValue() {
	return getPropertyValue().getValue();
    }

    public void setValue(String value) {
	getPropertyValue().setValue(value);
    }
    */

    public String getDisplayKey() {
	return displayKey;
    }

    public void setDisplayKey(String displayKey) {
	this.displayKey = displayKey;
    }

    /*
    public String getDisplayValue() {
	return getPropertyValue().getDisplayValue();
    }

    public void setDisplayValue(String displayValue) {
	getPropertyValue().setDisplayValue(displayValue);
    }

    public List<String> getElementComments() {
	return getPropertyValue().getElementComments();
    }

    public void setElementComments(List<String> elementComments) {
	getPropertyValue().setElementComments(elementComments);
    }
    */

    ////
    
    public void loadKey() {
	this.displayKey = BundleUtils.loadConvert(key);
    }

    public void saveKey() {
	this.key = convertKey(displayKey);
    }

    ////

    public void loadValue(int index) {
	setDisplayValue(index, BundleUtils.loadConvert(getValue(index)));
    }

    public void loadValue(Locale locale) {
	setDisplayValue(locale, BundleUtils.loadConvert(getValue(locale)));
    }

    public void saveValue(int index) {
	setValue(index, BundleUtils.saveConvert(getDisplayValue(index), false));
    }
    
    public void saveValue(Locale locale) {
	setValue(locale, BundleUtils.saveConvert(getDisplayValue(locale), false));
    }
    
    ////

    /*
    public void load() {
	this.displayKey = PropertiesUtils.loadConvert(key);
	setDisplayValue(PropertiesUtils.loadConvert(getValue()));
    }

    public void save() {
	this.key = convertKey(displayKey);
	setValue(PropertiesUtils.saveConvert(getDisplayValue(), false));
    }
    */

    public String convertKey(String key) {
	return BundleUtils.saveConvert(key, true);
    }

    public String getPropertyText(Locale locale) {
	String value = getValue(locale);
	return getPropertyText(key, value);
    }
    
    public String getPropertyText(int index) {
	String value = getValue(index);
	return getPropertyText(key, value);
    }
    
    public String getPropertyDisplayText(Locale locale) {
	String value = getDisplayValue(locale);
	return getPropertyText(key, value);
    }
    
    public String getPropertyDisplayText(int index) {
	String value = getDisplayValue(index);
	return getPropertyText(key, value);
    }


    public String getPropertyText(String key, String value) {
	String p = key;
	if (p != null) {
	    p = p.trim();
	}
	return (p == null ? value : (p + " = " + (value == null ? "" : value)));
    }

    
    public boolean isValid() {
	if (key == null || getValue(0) == null) {
	    return false;
	}
	if (key.trim().length() == 0 || getValue(0).trim().length() == 0) {
	    return false;
	}
	return true;
    }
	
	
	
}
