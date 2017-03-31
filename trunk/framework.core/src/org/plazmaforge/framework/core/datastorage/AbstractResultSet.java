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
 * Abstract simple result set
 * 
 * - fieldNames
 * 
 * 
 * 
 * @author ohapon
 *
 */
public abstract class AbstractResultSet implements DSResultSet {

    /**
     * Field names
     */
    private List<String> fieldNames;
    
    /**
     * Field map (FieldName [external/internal] -> FieldIndex [internal])
     */
    private Map<String, Integer> fieldIndexes;
    
    /**
     * Index map (index [external] - > index [internal])
     */
    private Map<Integer, Integer> externalIndexes;
    
    
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

    public int getFieldCount() {
	return fieldNames == null ? 0 : fieldNames.size();
    }
    
    public List<String> getFieldNames() {
        return fieldNames;
    }

    protected void setFieldNames(List<String> fieldNames) {
	initFields(fieldNames);
    }
    
    public boolean hasFields() {
        return fieldNames != null && !fieldNames.isEmpty();
    }
    
    protected void setFieldIndexes(Map<String, Integer> fieldIndexes) {
	this.fieldIndexes = fieldIndexes == null ? null : new HashMap<String, Integer>(fieldIndexes); 
	
    }
    
    public int getFieldIndex(String fieldName) {
	if (fieldName == null || fieldIndexes == null) {
	    return -1;
	}
	Integer index = fieldIndexes.get(fieldName);
	return index == null ? -1 : index;
    }

    public int getInternalIndex(int index) {
	if (externalIndexes == null) {
	    return -1;
	}
	Integer internalIndex = externalIndexes.get(index);
	return internalIndex == null ? -1 : internalIndex;
    }
    
    protected void initFields(List<String> fieldNames) {
	initFields(fieldNames, null);
    }
    
    /**
     * Set new fields and generate indexes
     * 
     * @param fieldNames
     * @param columns
     */
    protected void initFields(List<String> fieldNames, List<String> columns) {
	
	if (fieldNames == null || fieldNames.isEmpty()) {
	    // If fields is empty then columns are fields
	    fieldNames = columns == null ? new ArrayList<String>() : new ArrayList<String>(columns);
	}
	
	this.fieldNames = fieldNames;
	fieldIndexes = new HashMap<String, Integer>();
	externalIndexes = new HashMap<Integer, Integer>();
	
	boolean physical = (columns == null);
	int fieldCount = fieldNames.size();
	int index = 0;
	String fieldName = null;
	for (int i = 0; i < fieldCount; i++) {
	    fieldName = fieldNames.get(i);
	    if (fieldName == null) {
		continue;
	    }
	    if (physical) {
		// Physical index
		index = i;
	    } else {
		// Find field in column list
		//index = columns.indexOf(fieldName);
		index = columnIndexByName(columns, i, fieldName);
	    }
	    if (index < 0) {
		continue;
	    }
	    fieldIndexes.put(fieldName, index);
	    externalIndexes.put(i, index);
	}
    }

    protected void initFieldsExt(List<DSField> fields, List<String> columns) {
	
	if (fields == null || fields.isEmpty()) {
	    // If fields is empty then columns are fields
	    initFields(null, columns);
	    return;
	}
	
	fieldNames = new ArrayList<String>();
	fieldIndexes = new HashMap<String, Integer>();
	externalIndexes = new HashMap<Integer, Integer>();
	
	boolean physical = (columns == null);
	int fieldCount = fields.size();
	int index = 0;
	DSField field = null;
	String fieldName = null;
	String path = null;
	for (int i = 0; i < fieldCount; i++) {
	    field = fields.get(i);
	    fieldName = field.getName();
	    if (fieldName == null) {
		continue;
	    }
	    if (physical) {
		// Physical index
		index = i;
	    } else {
		// Find field in column list
		path = field.getPath();
		if (path == null) {
		    path = fieldName;
		}
		index = columnIndexByPath(columns, i, path);
	    }
	    if (index < 0) {
		continue;
	    }
	    fieldIndexes.put(fieldName, index);
	    externalIndexes.put(i, index);
	}
    }

    protected int columnIndexByName(List<String> columns, int index, String name) {
	if (columns == null || name == null) {
	    return -1;
	}
	//TODO
	return columns.indexOf(name);
    }
    
    protected int columnIndexByPath(List<String> columns, int index, String path) {
	if (columns == null || path == null) {
	    return -1;
	}
	Integer number = parseIntegerExpression(path);
	if (number != null) {
	    // Decrement because position start with 1 but index start with 0;
	    number--;
	    return number;
	}
	return columns.indexOf(path);
    }

    
    protected Integer parseIntegerExpression(String expression) {
	return parseIntegerExpression(expression, null);
    }

    protected Integer parseIntegerExpression(String expression, Integer def) {
	if (expression == null) {
	    return def;
	}
	expression = expression.trim();
	if (expression.isEmpty()) {
	    return def;
	}
	Integer result = parseInteger(expression);
	if (result == null) {
	    if (expression.startsWith("[") && expression.endsWith("]")) {
		if (expression.length() > 2) {
		    expression = expression.substring(1, expression.length() - 1);
		    result = parseInteger(expression); // '[123]': number
		}
	    }
	}
	if (result == null) {
	    result = def;
	}
	return result;
    }

    protected Integer parseInteger(String str) {
	if (str == null) {
	    return null;
	}
	try {
	    return Integer.parseInt(str);
	} catch (NumberFormatException e) {
	    // Ignore
	}
	return null;
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
