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
package org.plazmaforge.framework.datastorage.support.xml;

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
public class XMLDataSet extends AbstractWrappedDataSet implements DSDataSet {

    public XMLDataSet(List<DSField> fields, Reader reader) throws DSException {
	
	assert(fields != null);
	assert(reader != null);
	
	setFields(fields);
	//List<String> fieldNames = getFieldNames();
	//TODO
	this.resultSet = new XMLResultSet(reader);
	
    }

    @Override
    public Object getValue(String fieldName) throws DSException {
	//TODO: Must optimize
	DSField field = getField(fieldName);
	String path = getFieldPath(field);
	String value = getXMLResultSet().getStringValue(path);
	return convertString(value, field);
    }
    
    @Override
    public Object getValue(int index) throws DSException {
	//TODO: Must optimize
	DSField field = getField(index);
	String path = getFieldPath(field);
	String value = getXMLResultSet().getStringValue(path);
	return convertString(value, field);
    }
    
    public String getSelectExpression() {
	return getXMLResultSet().getSelectExpression();
    }

    public void setSelectExpression(String selectExpression) {
	getXMLResultSet().setSelectExpression(selectExpression);
    }

    protected XMLResultSet getXMLResultSet() {
	return ((XMLResultSet) resultSet);
    }
    
 
    protected String getFieldPath(DSField field) {
	if (field == null) {
	    return null;
	}
	String path = field.getPath();
	return path == null ? field.getName() : path; 
    }
  


}
