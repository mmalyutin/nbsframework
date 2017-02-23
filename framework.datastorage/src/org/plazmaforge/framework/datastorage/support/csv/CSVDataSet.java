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

    public CSVDataSet(List<DSField> fields, Reader reader) {
	
	assert(fields != null);
	assert(reader != null);
	
	setFields(fields);
	List<String> fieldNames = getFieldNames();
	
	this.resultSet = new CSVResultSet(fieldNames, reader);
	
    }

    @Override
    public Object getValue(String fieldName) throws DSException {
	//TODO: Must optimize
	//int index = getFieldIndex(fieldName);
	//String value = getCSVResultSet().getStringValue(index);
	
	DSField field = getField(fieldName);
	String path = getPath(field);
	String value = getCSVResultSet().getStringValue(path);
	return convertString(value, field);
    }
    
    @Override
    public Object getValue(int index) throws DSException {
	//TODO: Must optimize
	DSField field = getField(index);
	//String value = getCSVResultSet().getStringValue(index);
	String path = getPath(field);
	String value = getCSVResultSet().getStringValue(path);
	return convertString(value, field);
    }

    protected CSVResultSet getCSVResultSet() {
	return ((CSVResultSet) resultSet);
    }
    
    public String getColumnDelimiter() {
        return getCSVResultSet().getColumnDelimiter();
    }

    public void setColumnDelimiter(String columnDelimiter) {
	getCSVResultSet().setColumnDelimiter(columnDelimiter);
    }

    public String getRowDelimiter() {
        return getCSVResultSet().getRowDelimiter();
    }

    public void setRowDelimiter(String rowDelimiter) {
	getCSVResultSet().setRowDelimiter(rowDelimiter);
    }

    public boolean isFirstRowHeader() {
        return getCSVResultSet().isFirstRowHeader();
    }

    public void setFirstRowHeader(boolean firstRowHeader) {
	getCSVResultSet().setFirstRowHeader(firstRowHeader);
    }
 
  


}
