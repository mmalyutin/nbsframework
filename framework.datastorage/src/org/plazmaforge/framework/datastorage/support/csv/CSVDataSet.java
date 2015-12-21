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
	
	CSVResultSet csvResultSet = new CSVResultSet(fieldNames, reader);
	
	this.resultSet = csvResultSet;
	
    }

    @Override
    public Object getValue(String fieldName) throws DSException {
	//TODO: Must optimize
	int index = getFieldIndex(fieldName);
	DSField field = getField(fieldName);
	String dataType = field.getDataType();
	
	String value = ((CSVResultSet) resultSet).getStringValue(index);
	return convert(value, dataType);
    }
    
    @Override
    public Object getValue(int index) throws DSException {
	//TODO: Must optimize
	DSField field = getField(index);
	String dataType = field.getDataType();
	
	String value = ((CSVResultSet) resultSet).getStringValue(index);
	return convert(value, dataType);
    }

    public String getColumnDelimiter() {
        return ((CSVResultSet) resultSet).getColumnDelimiter();
    }

    public void setColumnDelimiter(String columnDelimiter) {
        ((CSVResultSet) resultSet).setColumnDelimiter(columnDelimiter);
    }

    public String getRowDelimiter() {
        return ((CSVResultSet) resultSet).getRowDelimiter();
    }

    public void setRowDelimiter(String rowDelimiter) {
        ((CSVResultSet) resultSet).setRowDelimiter(rowDelimiter);
    }

    public boolean isFirstRowHeader() {
        return ((CSVResultSet) resultSet).isFirstRowHeader();
    }

    public void setFirstRowHeader(boolean firstRowHeader) {
        ((CSVResultSet) resultSet).setFirstRowHeader(firstRowHeader);
    }
 
  


}
