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
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.util.BundleUtils;

/**
 * 
 * @author ohapon
 *
 */
public class PropertyValue implements Serializable {

    private static final long serialVersionUID = -6589233616976933835L;

    private String value = "";

    /** Presentation of value in national language **/
    private String displayValue = "";
    
    
    private List<String> elementComments = new ArrayList<String>();

    
    
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public List<String> getElementComments() {
        return elementComments;
    }

    public void setElementComments(List<String> elementComments) {
        this.elementComments = elementComments;
    }
    
    
    public void loadValue() {
	setDisplayValue(BundleUtils.loadConvert(getValue()));
    }


    public void saveValue() {
	setValue(BundleUtils.saveConvert(getDisplayValue(), false));
    }
    
}
