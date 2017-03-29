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
package org.plazmaforge.framework.datastorage.support.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.plazmaforge.framework.core.data.converter.Converter;
import org.plazmaforge.framework.core.data.converter.ConverterManager;
import org.plazmaforge.framework.core.datastorage.AbstractWrappedDataSet;
import org.plazmaforge.framework.core.datastorage.DSDataSet;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.core.sql.SQLBaseValueReader;
import org.plazmaforge.framework.core.sql.SQLEnvironment;
import org.plazmaforge.framework.core.sql.SQLValueReader;
import org.plazmaforge.framework.datastorage.support.sql.SQLResultSet.SQLColumn;

/**
 * @author ohapon
 *
 */
public class SQLDataSet extends AbstractWrappedDataSet implements DSDataSet {

    private SQLValueReader valueReader; 
    
    public SQLDataSet(List<DSField> fields, ResultSet rs) {
	assert(fields != null);
	assert(rs != null);
	
	setFields(fields);
	List<String> fieldNames = getFieldNames();
	this.resultSet = new SQLResultSet(fieldNames, rs);
	
    }

    public SQLValueReader getValueReader() {
	if (valueReader == null) {
	    valueReader = new SQLBaseValueReader();
	}
        return valueReader;
    }

    public void setValueReader(SQLValueReader valueReader) {
        this.valueReader = valueReader;
    }

    @Override
    public Object getValue(String fieldName) throws DSException {
	int index = getFieldIndex(fieldName);
	DSField field = getField(fieldName);
	return getFieldValue(field, index);
    }
    
    @Override
    public Object getValue(int index) throws DSException {
	DSField field = getField(index);
	return getFieldValue(field, index);
    }
    
    protected Object getFieldValue(DSField field, int index) throws DSException {
	
	// Get field data type
	String dataType = field.getDataType();
	
	// Get SQL type by field data type
	int toType = SQLEnvironment.getSQLType(dataType);
	int fromType = toType; 
	
	
	SQLResultSet rs = getInternalResultSet();
	
	// Get real SQL type by column of 'ResultSet'
	SQLColumn column = rs.getSQLColumn(index);
	if (column != null) {
	    fromType = column.getType();
	}
	boolean needConvert = false;
	String sourceType = null;
	String targetType = null;
	int type = -1;
	
	// Analize compatible types (fromType, toType)
	// Find SQL type for 'get' methods of 'ResultSet'
	if (fromType == toType) {
	    type = toType;
	} else if (SQLEnvironment.isSQLSoftCompatibleType(fromType, toType)) {
	    type = toType;
	    needConvert = true;
	} else {
	    type = fromType;
	    needConvert = true;
	}
	
	if (needConvert) {
	    Class<?> fromClass = SQLEnvironment.getClass(fromType);
	    if (fromClass != null) {
		sourceType = ConverterManager.getSimpleName(fromClass);
		targetType = dataType;
	    }
	}
	
	Object value = getValue(rs.getNativeResultSet(), index + 1, type); // shift index (+1) for java.sql.ResultSet
	if (needConvert) {
	    if (sourceType != null && targetType != null) {
		
		Converter converter = getConverter(sourceType, targetType, field.getFormat());
		Object result = converter == null ? value : converter.convert(value);
		value = result;
	    }
	}
	
	return value;
    }
 
    protected SQLResultSet getInternalResultSet() {
	return ((SQLResultSet) resultSet);
    }

    protected Object getValue(ResultSet rs, int index, int type) throws DSException {
	try {
	    return getValueReader().getValue(rs, index, type);	    
	} catch (SQLException e) {
	    throw new DSException(e);
	}
    }
    
}
