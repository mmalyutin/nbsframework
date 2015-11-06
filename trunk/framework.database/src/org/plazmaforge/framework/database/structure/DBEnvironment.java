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

package org.plazmaforge.framework.database.structure;

import java.util.ArrayList;
import java.util.List;

/** 
 * @author ohapon
 */

public class DBEnvironment {

    public static DataType EMPTY_DATA_TYPE = new DataType("", false, false);
	
    private static List<DataType> dataTypes;
    
    private static List<DataType> createDataTypes() {
	
	// SEE org.plazmaforge.framework.dao.JDBCEnvironment
	// USE JDBCEnvironment.getJdbcTypeList()
	// USE JDBCEnvironment.isFixedType(jdbcType)
	
	List<DataType> dataTypes = new ArrayList<DataType>();
	
	dataTypes = new ArrayList<DataType>();
	dataTypes.add(new DataType("INTEGER"));
	dataTypes.add(new DataType("SMALLINT"));	
	dataTypes.add(new DataType("FLOAT"));
	dataTypes.add(new DataType("DOUBLE"));
	dataTypes.add(new DataType("NUMERIC", true, true)); // USE JDBCEnvironment.isFixedType(jdbcType)
	dataTypes.add(new DataType("DECIMAL", true, true)); // USE JDBCEnvironment.isFixedType(jdbcType)
	dataTypes.add(new DataType("CHAR", true, false)); // USE JDBCEnvironment.isFixedType(jdbcType)
	dataTypes.add(new DataType("VARCHAR", true, false)); // USE JDBCEnvironment.isFixedType(jdbcType)
	dataTypes.add(new DataType("DATE"));
	dataTypes.add(new DataType("TIME"));
	dataTypes.add(new DataType("TIMESTAMP"));
	dataTypes.add(new DataType("BLOB"));
	
	return dataTypes;
    }
    
    
    public static List<DataType> getDataTypes() {
	if (dataTypes == null) {
	    dataTypes = createDataTypes();
	}
        return dataTypes;
    }
    
    public static boolean isSupportTypeSize(String type) {
	DataType dataType = getDataType(type);
	if (dataType == null) {
	    return true;
	}
	return dataType.isSupportSize();
    }
    
    public static DataType getDataType(String type) {
	if (type == null) {
	    return null;
	}
	type = type.trim();
	if (type.length() == 0) {
	    return null;
	}
	type = type.toUpperCase();
	
	List<DataType> dataTypes = getDataTypes();
	if (dataTypes == null) {
	    return null;
	}
	for (DataType dataType : dataTypes) {
	    if (type.equals(dataType.getName())) {
		return dataType;
	    }
	}
	return null;
    }
}
