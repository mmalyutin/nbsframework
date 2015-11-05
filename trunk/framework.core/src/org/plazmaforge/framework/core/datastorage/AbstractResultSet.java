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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author ohapon
 *
 */
public abstract class AbstractResultSet implements DSResultSet {

    private List<String> fieldNames;
    
    private Map<String, Integer> fieldIndexes;
    
    public AbstractResultSet() {
    }
    
    public AbstractResultSet(List<String> fieldNames) {
	super();
	setFieldNames(fieldNames);
    }



//    public Object getValue(String name) throws DSException {
//	int index = getFieldIndex(name);
//	if (index < 0) {
//	    return null;
//	}
//	return getValue(index);
//    }
    
    public int getFieldCount() {
	return fieldNames == null ? 0 : fieldNames.size();
    }
    
    public List<String> getFieldNames() {
        return fieldNames;
    }

    protected void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames == null ? null : new ArrayList<String>(fieldNames);
        generateFieldIndexes();
    }
    
    protected void setFieldIndexes(Map<String, Integer> fieldIndexes) {
	this.fieldIndexes = fieldIndexes == null ? null : new HashMap<String, Integer>(fieldIndexes); 
	
    }
    protected int getFieldIndex(String fieldName) {
	if (fieldName == null || fieldIndexes == null) {
	    return -1;
	}
	Integer index = fieldIndexes.get(fieldName);
	return index == null ? -1 : index;
    }
    
    protected void generateFieldIndexes() {
	if (fieldNames == null) {
	    fieldIndexes = null;
	    return;
	}
	fieldIndexes = new HashMap<String, Integer>();
	int count = fieldNames.size();
	for (int i = 0; i < count; i++) {
	    String fieldName = fieldNames.get(i);
	    if (fieldName == null) {
		continue;
	    }
	    fieldIndexes.put(fieldName, i);
	}
    }

    
}
