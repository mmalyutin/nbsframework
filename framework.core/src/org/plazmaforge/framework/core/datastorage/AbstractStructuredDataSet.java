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

package org.plazmaforge.framework.core.datastorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractStructuredDataSet extends AbstractDataSet {

    
    private List<DSField> fields;
    
    private Map<String, Integer> fieldIndexes;


    public List<DSField> getFields() {
	if (fields == null) {
	    fields = new ArrayList<DSField>();
	}
        return fields;
    }

    public void setFields(List<DSField> fields) {
        this.fields = fields == null ? null : new ArrayList<DSField>(fields);
        generateFieldIndexes();
    }

    
    public DSField getField(int index) {
	if (fields == null || fields.size() == 0 || index < 0 || index > fields.size() - 1) {
	    return null;
	}
	return fields.get(index);
    }

    public DSField getField(String name) {
	if (name == null || fields == null || fields.isEmpty()) {
	    return null;
	}
	for (DSField field: fields) {
	    if (name.equals(field.getName())) {
		return field;
	    }
	}
	return null;
    }

    public String getFieldName(int index) {
	DSField field = getField(index);
	if (field == null) {
	    return null;
	}
	return field.getName();
    }

    
    public String getFieldType(int index) {
	DSField field = getField(index);
	if (field == null) {
	    return null;
	}
	return field.getDataType();
    }
    
    public List<String> getFieldNames() {
	List<String> fieldNames = new ArrayList<String>();
	if (fields == null) {
	    return fieldNames;
	}
	for (DSField field: fields) {
	    fieldNames.add(field.getName());
	}
        return fieldNames;
    }
    
    public int getFieldCount() {
	return fields == null ? 0 : fields.size();
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
	if (fields == null) {
	    fieldIndexes = null;
	    return;
	}
	fieldIndexes = new HashMap<String, Integer>();
	int count = fields.size();
	for (int i = 0; i < count; i++) {
	    DSField field = fields.get(i);
	    String fieldName = field == null ? null : field.getName();
	    if (fieldName == null) {
		continue;
	    }
	    fieldIndexes.put(fieldName, i);
	}
    }    



}
