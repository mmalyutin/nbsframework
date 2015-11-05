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
package org.plazmaforge.framework.datastorage.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.datastorage.AbstractResultSet;
import org.plazmaforge.framework.core.datastorage.DSScrollableResultSet;
import org.plazmaforge.framework.core.datastorage.DSStructuredResultSet;
import org.plazmaforge.framework.core.exception.DSException;

/**
 * @author ohapon
 *
 */
public class SQLResultSet extends AbstractResultSet implements DSScrollableResultSet, DSStructuredResultSet {

    private ResultSet rs;

    private int retrieveRecordCount;
    
    private int position = -1;
    
    private boolean bottom;

    public SQLResultSet(List<String> fieldNames, ResultSet rs) {
	super(fieldNames);
	this.rs = rs;

	try {
	    loadSQLFields(fieldNames, rs);
	} catch (SQLException ex) {
	    logError(ex);
	}
    }

    public SQLResultSet(ResultSet rs) {
	this(null, rs);
    }
    
    protected void loadSQLFields(List<String> fieldNames, ResultSet rs) throws SQLException {

	// Load ResultSetMetaData columns
	ResultSetMetaData meta = rs.getMetaData();
	int columnCount = meta.getColumnCount();
	List<String> columns = new ArrayList<String>();
	
	for (int column = 1; column <= columnCount; column++) {
	    
	    columns.add(meta.getColumnLabel(column)); // ???
	}
	
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
    
    @Override
    public boolean canScroll() throws DSException {
	if (isInvalid()) {
	    return false; 
	}
	try {
	    return rs.getType() == ResultSet.TYPE_SCROLL_INSENSITIVE || rs.getType() == ResultSet.TYPE_SCROLL_INSENSITIVE;
	} catch (SQLException ex) {
	    handleSQLException(ex);
	}
	return false;
    }


    
    @Override
    public boolean first() throws DSException {
	if (isInvalid()) {
	    return false;
	}
	try {
	    boolean flag = rs.first();
	    if (flag) {
		position = 0;
		firePosition();
	    }
	    return flag;
	} catch (SQLException ex) {
	    handleSQLException(ex);
	}
	return false;
    }

    @Override
    public boolean move(int index) throws DSException {
	if (isInvalid()) {
	    return false;
	}
	try {
	    boolean flag = rs.absolute(index + 1);
	    if (flag) {
		position = index;
		firePosition();
	    }
	    return flag;
	} catch (SQLException ex) {
	    handleSQLException(ex);
	}
	return false;
    }

    @Override
    public boolean last() throws DSException {
	if (isInvalid()) {
	    return false;
	}
	try {
	    boolean flag = rs.last();
	    if (flag) {
		position = rs.getRow() - 1;
		firePosition();
	    }
	    return flag;
	} catch (SQLException ex) {
	    handleSQLException(ex);
	}
	return false;
    }

    @Override
    public boolean next() throws DSException {
	if (isInvalid()){
	    return false;
	}
	try {
	    boolean flag = rs.next();
	    if (flag) {
		position++;
		firePosition();
	    }
	    return flag;
	} catch (SQLException ex) {
	    handleSQLException(ex);
	}
	return false;
    }

    @Override
    public boolean prev() throws DSException {
	if (isInvalid()){
	    return false;
	}
	try {
	    boolean flag = rs.previous();
	    if (flag) {
		if (bottom) {
		    bottom = false;
		    position = rs.getRow() - 1;
		} else {
		    position--;
		}
		firePosition();
	    }
	    return flag;
	} catch (SQLException ex) {
	    handleSQLException(ex);
	}
	return false;
    }
    
    @Override
    public boolean top() throws DSException {
	if (isInvalid()){
	    return false;
	}
	try {
	    rs.beforeFirst();
	    boolean flag = rs.isBeforeFirst();
	    if (flag) {
		position = -1;
		firePosition();
	    }
	    return flag;
	} catch (SQLException ex) {
	    handleSQLException(ex);
	}
	return false;
    }

    @Override
    public boolean bottom() throws DSException {
	if (isInvalid()){
	    return false;
	}
	try {
	    rs.afterLast();
	    boolean flag = rs.isAfterLast();
	    if (flag) {
		position = -1;
		firePosition();
		bottom = true;
	    }
	    return flag;
	} catch (SQLException ex) {
	    handleSQLException(ex);
	}
	return false;	
    }
    
    
    @Override
    public Object getValue(String name) throws DSException {
	int index = getFieldIndex(name);
	if (index < 0) {
	    return null;
	}
	return getValue(index);
    }
  
    //@Override
    public Object getValue(int index) throws DSException {
	try {
	    return rs.getObject(index + 1);
	} catch (SQLException ex) {
	    handleSQLException(ex);
	}
	return null;
    }

    @Override
    public boolean isEmpty() throws DSException {
	if (isClosed()) {
	    return true;
	}
	try {
	    return !rs.isBeforeFirst() && !rs.isAfterLast() && rs.getRow() == 0;
	} catch (SQLFeatureNotSupportedException ex) {
	    // Not supported
	    logError(ex);
	} catch (SQLException ex) {
	    handleSQLException(ex);
	}
	return false;
    }

    @Override
    public boolean isClosed() throws DSException {
   	if (rs == null) {
   	    return true;
   	}
   	try {
   	    return rs.isClosed();
   	} catch (AbstractMethodError ex) {
   	    // Not supported
   	    logError(ex);
   	} catch (SQLException ex) {
   	    handleSQLException(ex);
   	}
   	return false;
   	
    }

    @Override
    public void close() throws DSException {
   	if (rs == null) {
   	    return;
   	}
   	try {
   	    rs.close();
   	    rs = null;
   	} catch (SQLException ex) {
   	    handleSQLException(ex);
   	}
    }

    //@Override
    public int getRetrieveRecordCount() {
	return retrieveRecordCount;
    }
    
    protected boolean isInvalid() {
	return rs == null;
    }

    protected void firePosition() {
	int count = position + 1; 
	if (count > retrieveRecordCount) {
	    retrieveRecordCount = count;
	}
	//invalidateRecord = true;
	bottom = false;
    }
   
    protected void handleSQLException(SQLException ex) throws DSException {
	if (ex == null) {
	    return;
	}
	throw new DSException(ex);
    }

    protected void logError(Throwable error) {
	System.err.println(error);	
    }
   

}
