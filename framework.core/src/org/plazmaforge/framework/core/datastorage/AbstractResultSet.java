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

import org.plazmaforge.framework.core.data.converter.Converter;
import org.plazmaforge.framework.core.data.converter.ConverterManager;
import org.plazmaforge.framework.core.data.converter.STConverterManager;
import org.plazmaforge.framework.util.StringUtils;


/**
 * @author ohapon
 *
 */
public abstract class AbstractResultSet implements DSResultSet {

    /**
     * Field names
     */
    private List<String> fieldNames;
    
    /**
     * Field index map (FieldName -> FieldIndex [internal])
     */
    private Map<String, Integer> fieldIndexes;
    
    protected boolean processing;


    
    
    private String dateFormat;

    private String numberFormat;
    
    
    private ConverterManager converterManager;
    
    
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
    
    public boolean hasFields() {
        return fieldNames != null && !fieldNames.isEmpty();
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
    
    /**
     * Generate physical field indexes (by default)
     */
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

    /**
     * Generate field indexes by real (native internal) columns
     * @param fieldNames
     * @param columns
     */
    protected void generateFieldIndexes(List<String> fieldNames, List<String> columns) {
	
	if (fieldNames == null || fieldNames.isEmpty()) {
	    // If fields is empty then columns are fields
	    setFieldNames(columns);
	    return;
	}
	
	int fieldCount = fieldNames.size();
	Map<String, Integer> fieldIndexes = new HashMap<String, Integer>();
	for (int i = 0; i < fieldCount; i++) {
	    String fieldName = fieldNames.get(i);
	    if (fieldName == null) {
		continue;
	    }
	    // Find field in column list
	    int index = columns.indexOf(fieldName);
	    if (index < 0) {
		continue;
	    }
	    fieldIndexes.put(fieldName, index);
	}
	setFieldIndexes(fieldIndexes);
    }
    
    
    protected boolean isEmpty(String str) {
	return StringUtils.isEmpty(str);
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
	checkProcessing();
        this.dateFormat = dateFormat;
    }

    public String getNumberFormat() {
        return numberFormat;
    }

    public void setNumberFormat(String numberFormat) {
	checkProcessing();
        this.numberFormat = numberFormat;
    }
    
    protected void checkProcessing() {
	if (processing) {
	    handlePropertyModifyException();
	}
    }
    
    protected void handlePropertyModifyException() {
	String message = "Cannot modify '" + getClass().getSimpleName()	+ "' properties after data processing has started";
	throw new RuntimeException(message);
    }

    
    protected Converter<?, ?> getConverter(String sourceType, String targetType, String format) {
	if (sourceType == null || targetType == null) {
	    return null;
	}
	String name = ConverterManager.getConverterSimpleName(sourceType, targetType);
	if (name == null) {
	    return null;
	}
	return getConverterByName(name, format);
    }
    
    protected Converter<?, ?> getConverterByName(String name, String format) {
	return getConverterManager().getConverter(name, format);
    }
    
    public ConverterManager getConverterManager() {
	if (converterManager == null) {
	    converterManager = new STConverterManager(true);
	    converterManager.init();
	}
	return converterManager;
    }
           
}
