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

package org.plazmaforge.framework.uwt.data.store;

import org.plazmaforge.framework.core.criteria.Operation;
import org.plazmaforge.framework.core.data.PropertyProvider;


/**
 * The Filter for property of the element 
 *  
 * @author ohapon
 *
 */
//[CORE]
public class PropertyFilter<T> extends DataFilter<T> {

    private String property;

    private Object value;
    
    private String operation;
    
    // Only for string value
    private boolean ignoreCase;
    
    
    private PropertyProvider<T> propertyProvider;
    
    

    public PropertyFilter() {
	super();
    }
    
    
    public PropertyFilter(String property, Object value) {
	super();
	this.property = property;
	this.value = value;
    }

    public PropertyFilter(String property, Object value, PropertyProvider<T> propertyProvider) {
	super();
	this.property = property;
	this.value = value;
	this.propertyProvider = propertyProvider;
    }


    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public PropertyProvider<T> getPropertyProvider() {
        return propertyProvider;
    }

    public void setPropertyProvider(PropertyProvider<T> propertyProvider) {
        this.propertyProvider = propertyProvider;
    }
    
    
    public boolean select(T parent, T element) {
	if (!isValid()) {
	    return false;
	}
	Object elementValue = getPropertyProvider().getValue(element, getProperty());
	return isFilter(elementValue);
    }

    /**
     * Return true if filter is valid.
     * We can override the method to detail validation of the filter
     * @return
     */
    protected boolean isValid() {
	return getProperty() != null && getPropertyProvider() != null;
    }

    
    //////////////////////////////////////////////////////////////////////////////////////
    
    
    public boolean isFilter(Object elementValue) {
	if (value == null && elementValue == null) {
	    return true;
	}
	if (value == null) {
	    return false;
	}
	return isFilterByOperation(elementValue);
    }
    
    protected boolean isFilterByOperation(Object elementValue) {
	if (Operation.EQ.equals(operation)) {
	    return isEQ(elementValue);
	    
	} else if (Operation.NE.equals(operation)) {
	    return isNE(elementValue);
	    
	} else if (Operation.LIKE.equals(operation)) {
	    return isLIKE(elementValue);
	    
	} else if (Operation.NOTLIKE.equals(operation)) {
	    return isNOTLIKE(elementValue);
	    
	} else if (Operation.LT.equals(operation)) {
	    return isLT(elementValue);
	    
	} else if (Operation.LE.equals(operation)) {
	    return isLE(elementValue);
	    
	} else if (Operation.GT.equals(operation)) {
	    return isGT(elementValue);
	    
	} else if (Operation.GE.equals(operation)) {
	    return isGE(elementValue);
	}
	
	// Default
	return isEQ(elementValue);
    }
    
    protected boolean isEQ(Object elementValue) {
	return value.equals(elementValue);
    }
    
    protected boolean isNE(Object elementValue) {
	return !isEQ(elementValue);
    }
    
    protected boolean isLIKE(Object elementValue) {
	if (elementValue == null) {
	    return false;
	}
	if (elementValue instanceof String) {
	    return isLIKEString((String) elementValue);
	}
	return isEQ(elementValue);
    }
    
    protected boolean isLIKEString(String elementValue) {
	// TODO
	return isEQ(elementValue);
	//if (likeRegex == null) {
	//    return false;
	//}
	//return StringUtils.likeRegex(elementValue, likeRegex.toString());
    }
    
    

    protected boolean isNOTLIKE(Object elementValue) {
	return !isLIKE(elementValue);
    }
    
    protected boolean isLT(Object elementValue) {
	if (!isComparableValue()) {
	    return false;
	}
	if (!isComparableValue(elementValue)) {
	    return true;
	}
	int result = compareValue(elementValue);
	return result == 1;
    }

    protected boolean isLE(Object elementValue) {
	if (isEQ(elementValue)) {
	    return true;
	}
	return isLT(elementValue);
    }
    
    protected boolean isGT(Object elementValue) {
	if (!isComparableValue()) {
	    return false;
	}
	if (!isComparableValue(elementValue)) {
	    return true;
	}
	int result = compareValue(elementValue);
	return result == -1;
    }

    protected boolean isGE(Object elementValue) {
	if (isEQ(elementValue)) {
	    return true;
	}
	return isGT(elementValue);
    }
    
    
    
    //
    protected boolean isComparableValue() {
	return isComparableValue(getValue());
    }
    
    protected boolean isComparableValue(Object elementValue) {
	return elementValue != null && elementValue instanceof Comparable;
    }
    
    protected Comparable getComparableValue() {
	return (Comparable) getValue();
    }
    
    protected Comparable getComparableValue(Object elementValue) {
	return (Comparable) elementValue;
    }

    protected int compareValue(Object elementValue) {
	return getComparableValue().compareTo(getComparableValue(elementValue));
    }

}
