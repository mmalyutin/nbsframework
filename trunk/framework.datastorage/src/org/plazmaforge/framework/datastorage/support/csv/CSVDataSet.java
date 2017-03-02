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
package org.plazmaforge.framework.datastorage.support.csv;

import java.io.Reader;
import java.util.List;

import org.plazmaforge.framework.core.datastorage.AbstractWrappedDataSet;
import org.plazmaforge.framework.core.datastorage.DSDataSet;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.exception.DSException;

/**
 * @author ohapon
 *
 */
public class CSVDataSet extends AbstractWrappedDataSet implements DSDataSet {

    public CSVDataSet(final List<DSField> fields, Reader reader) {
	assert(fields != null);
	assert(reader != null);
	
	setFields(fields);
	this.resultSet = new CSVResultSet(null, reader) {
	    
	    @Override
	    protected void loadFields(List<String> columns) {
		initFieldsExt(fields, columns);
	    }
	};
    }

    @Override
    public Object getValue(String fieldName) throws DSException {
	int index = getFieldIndex(fieldName);
	DSField field = getField(index);
	return getFieldValue(field, index);
    }
    
    @Override
    public Object getValue(int index) throws DSException {
	DSField field = getField(index);
	return getFieldValue(field, index);
    }
    
    protected Object getFieldValue(DSField field, int index) throws DSException {
	CSVResultSet rs = getInternalResultSet();
	index = rs.getInternalIndex(index); // convert index : [external] to [internal]
	String value = rs.getStringValue(index);
	return convertString(value, field);
    }

    protected CSVResultSet getInternalResultSet() {
	return ((CSVResultSet) resultSet);
    }
    
    public String getColumnDelimiter() {
        return getInternalResultSet().getColumnDelimiter();
    }

    public void setColumnDelimiter(String columnDelimiter) {
	getInternalResultSet().setColumnDelimiter(columnDelimiter);
    }

    public String getRowDelimiter() {
        return getInternalResultSet().getRowDelimiter();
    }

    public void setRowDelimiter(String rowDelimiter) {
	getInternalResultSet().setRowDelimiter(rowDelimiter);
    }

    public boolean isFirstRowHeader() {
        return getInternalResultSet().isFirstRowHeader();
    }

    public void setFirstRowHeader(boolean firstRowHeader) {
	getInternalResultSet().setFirstRowHeader(firstRowHeader);
    }
 
  


}
