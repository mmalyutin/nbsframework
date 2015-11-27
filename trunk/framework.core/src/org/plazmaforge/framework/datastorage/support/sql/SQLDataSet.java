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

import org.plazmaforge.framework.core.datastorage.AbstractWrappedDataSet;
import org.plazmaforge.framework.core.datastorage.DSDataSet;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.core.sql.SQLBaseValueReader;
import org.plazmaforge.framework.core.sql.SQLEnvironment;
import org.plazmaforge.framework.core.sql.SQLValueReader;

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
	//TODO: Must optimize
	int index = getFieldIndex(fieldName);
	DSField field = getField(fieldName);
	String dataType = field.getDataType();
	int type = SQLEnvironment.getSQLType(dataType);
	return getValue(((SQLResultSet) resultSet).getNativeResultSet(), index, type);
    }
    
    @Override
    public Object getValue(int index) throws DSException {
	//TODO: Must optimize
	DSField field = getField(index);
	String dataType = field.getDataType();
	int type = SQLEnvironment.getSQLType(dataType);
	return getValue(((SQLResultSet) resultSet).getNativeResultSet(), index, type);
    }
 
    protected Object getValue(ResultSet rs, int index, int type) throws DSException {
	try {
	    return getValueReader().getValue(rs, index, type);	    
	} catch (SQLException e) {
	    throw new DSException(e);
	}
    }
    
}
