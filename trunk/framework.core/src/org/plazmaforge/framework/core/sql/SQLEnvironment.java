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

package org.plazmaforge.framework.core.sql;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author ohapon
 *
 */


// http://docs.oracle.com/javase/1.5.0/docs/guide/jdbc/getstart/mapping.html
// http://www.hsqldb.org/doc/guide/ch09.html#datatypes-section

public class SQLEnvironment {

    
    private static Map<Integer, Class<?>> TYPE_TO_CLASS;
    private static Map<String, Integer> CLASS_NAME_TO_TYPE;
    
    static {
	registerTypeToClass();
	registerClassNameToType();
    }
    
    private static void registerTypeToClass() {
	
	// Create Map: (SQL type -> Java Class)
	TYPE_TO_CLASS = new HashMap<Integer, Class<?>>();
	
	// Boolean
	TYPE_TO_CLASS.put(Types.BIT, Boolean.class);
	TYPE_TO_CLASS.put(Types.BOOLEAN, Boolean.class);
	
	// Number: Integer
	TYPE_TO_CLASS.put(Types.TINYINT, Byte.class);			// Integer-8
	TYPE_TO_CLASS.put(Types.SMALLINT, Short.class);			// Integer-16
	TYPE_TO_CLASS.put(Types.INTEGER, Integer.class);		// Integer-32
	TYPE_TO_CLASS.put(Types.BIGINT, Long.class);			// Integer-64
	
	// Number: Float
	TYPE_TO_CLASS.put(Types.REAL, Float.class);			// Float-32
	TYPE_TO_CLASS.put(Types.FLOAT, Float.class);			// Float-32
	TYPE_TO_CLASS.put(Types.DOUBLE, Double.class);			// Float-64
	
	// Number: Decimal
	TYPE_TO_CLASS.put(Types.NUMERIC, BigDecimal.class);
	TYPE_TO_CLASS.put(Types.DECIMAL, BigDecimal.class);

	// Date
	TYPE_TO_CLASS.put(Types.DATE, java.sql.Date.class);		// Date
	TYPE_TO_CLASS.put(Types.TIME, java.sql.Time.class);		// Time
	TYPE_TO_CLASS.put(Types.TIMESTAMP, java.sql.Timestamp.class);	// DateTime
	
	// String
	TYPE_TO_CLASS.put(Types.CHAR, String.class);
	TYPE_TO_CLASS.put(Types.VARCHAR, String.class);
	TYPE_TO_CLASS.put(Types.LONGVARCHAR, String.class);
	TYPE_TO_CLASS.put(Types.NVARCHAR, String.class);
	TYPE_TO_CLASS.put(Types.ROWID, String.class);
	
    }
    
    private static void registerClassNameToType() {
	
	// Create Map: (Java Class Name -> SQL type)
	CLASS_NAME_TO_TYPE = new HashMap<String, Integer>();
	
	// Boolean
	CLASS_NAME_TO_TYPE.put("java.lang.Boolean", Types.BIT);
	CLASS_NAME_TO_TYPE.put("Boolean", Types.BIT);
	
	// Number: Integer
	CLASS_NAME_TO_TYPE.put("java.lang.Byte", Types.TINYINT);	// Integer-8
	CLASS_NAME_TO_TYPE.put("Byte", Types.TINYINT);
	CLASS_NAME_TO_TYPE.put("java.lang.Short", Types.SMALLINT);	// Integer-16
	CLASS_NAME_TO_TYPE.put("Short", Types.SMALLINT);
	CLASS_NAME_TO_TYPE.put("java.lang.Integer", Types.INTEGER);	// Integer-32
	CLASS_NAME_TO_TYPE.put("Integer", Types.INTEGER);
	CLASS_NAME_TO_TYPE.put("java.lang.Long", Types.BIGINT);		// Integer-64
	CLASS_NAME_TO_TYPE.put("Long", Types.BIGINT);

	// Number: Float
	CLASS_NAME_TO_TYPE.put("java.lang.Float", Types.FLOAT);		// Float-32
	CLASS_NAME_TO_TYPE.put("Float", Types.FLOAT);
	CLASS_NAME_TO_TYPE.put("java.lang.Double", Types.DOUBLE);	// Float-64
	CLASS_NAME_TO_TYPE.put("Double", Types.DOUBLE);

	// Number: Decimal
	CLASS_NAME_TO_TYPE.put("java.math.BigDecimal", Types.DECIMAL);
	CLASS_NAME_TO_TYPE.put("BigDecimal", Types.DECIMAL);

	// Date
	CLASS_NAME_TO_TYPE.put("java.util.Date", Types.DATE);		// Date
	CLASS_NAME_TO_TYPE.put("java.sql.Date", Types.DATE);	
	CLASS_NAME_TO_TYPE.put("Date", Types.DATE);
	CLASS_NAME_TO_TYPE.put("java.sql.Time", Types.TIME);		// Time
	CLASS_NAME_TO_TYPE.put("Time", Types.TIME);
	CLASS_NAME_TO_TYPE.put("java.sql.Timestamp", Types.TIMESTAMP);	// DateTime
	CLASS_NAME_TO_TYPE.put("Timestamp", Types.TIMESTAMP);
	CLASS_NAME_TO_TYPE.put("DateTime", Types.TIMESTAMP);

	// String
	CLASS_NAME_TO_TYPE.put("java.lang.String", Types.VARCHAR);	// String
	CLASS_NAME_TO_TYPE.put("String", Types.VARCHAR);
	
    }
    
    public static Class<?> getClass(int sqlType) {

	Class<?> klass = TYPE_TO_CLASS.get(sqlType);
	return klass == null ? Object.class : klass; 
	
	/*
	switch (sqlType) {
	
	case Types.BIT:
	case Types.BOOLEAN:	    
	    return Boolean.class;

	case Types.TINYINT:
	    return Byte.class;
	case Types.SMALLINT:
	    return Short.class;
	case Types.INTEGER:
	    return Integer.class;
	case Types.BIGINT:
	    return Long.class;

	case Types.NUMERIC:
	case Types.DECIMAL:
	    return BigDecimal.class;

	case Types.REAL:
	case Types.FLOAT:
	    return Float.class;
	case Types.DOUBLE:
	    return Double.class;

	case Types.DATE:
	    return java.sql.Date.class;
	case Types.TIME:
	    return java.sql.Time.class;
	case Types.TIMESTAMP:
	    return java.sql.Timestamp.class;

	case Types.CHAR:
	case Types.VARCHAR:
	case Types.LONGVARCHAR:
	case Types.NVARCHAR:
	case Types.ROWID:
	    return String.class;
	    
	default:
	    return Object.class;
	}
	*/
    }
    
    public static String getClassName(int sqlType) {
	Class<?> klass = getClass(sqlType);
	return klass == null ? null : klass.getName();
    }

    public static boolean isSQLBooleanType(int sqlType) {
	return sqlType == Types.BIT 
		|| sqlType == Types.BOOLEAN; 
    }
    
    public static boolean isSQLNumberType(int sqlType) {
	return sqlType == Types.TINYINT 
		|| sqlType == Types.SMALLINT 
		|| sqlType == Types.INTEGER 
		|| sqlType == Types.BIGINT 
		|| sqlType == Types.NUMERIC 
		|| sqlType == Types.DECIMAL 
		|| sqlType == Types.REAL 
		|| sqlType == Types.FLOAT
		|| sqlType == Types.DOUBLE;
    }

    public static boolean isSQLDateType(int sqlType) {
	return sqlType == Types.DATE 
		|| sqlType == Types.TIME 
		|| sqlType == Types.TIMESTAMP; 
    }
    
    public static boolean isSQLStringType(int sqlType) {
	return sqlType == Types.CHAR 
		|| sqlType == Types.VARCHAR 
		|| sqlType == Types.LONGVARCHAR 
		|| sqlType == Types.NVARCHAR 
		|| sqlType == Types.ROWID; 
    }
    
    
    public static boolean isSQLSoftCompatibleType(int fromType, int toType) {
	if (fromType == toType) {
	    return true;
	}
	if (isSQLBooleanType(fromType) && isSQLBooleanType(toType)) {
	    return true;
	}
	if (isSQLNumberType(fromType) && isSQLNumberType(toType)) {
	    return true;
	}
	if (isSQLDateType(fromType) && isSQLDateType(toType)) {
	    return true;
	}
	if (isSQLStringType(fromType) && isSQLStringType(toType)) {
	    return true;
	}
	return false;
    }
    
    public static int getSQLType(String className) {
	Integer sqlType = className == null ? null : CLASS_NAME_TO_TYPE.get(className);
	return sqlType == null ? Types.OTHER : sqlType;
	
	/*
	if (className == null) {
	    return Types.OTHER; // TODO
	}
	    
	if (Boolean.class.getName().equals(className) || Boolean.class.getSimpleName().equals(className)) {
	    return Types.BIT;
    	}
	if (Byte.class.getName().equals(className) || Byte.class.getSimpleName().equals(className)) {
	    return Types.TINYINT;
    	}
	if (Short.class.getName().equals(className) || Short.class.getSimpleName().equals(className)) {
	    return Types.SMALLINT;
    	}
	if (Integer.class.getName().equals(className) || Integer.class.getSimpleName().equals(className)) {
	    return Types.INTEGER;
    	}
	if (Long.class.getName().equals(className) || Long.class.getSimpleName().equals(className)) {
	    return Types.BIGINT;
    	}
	if (Float.class.getName().equals(className) || Float.class.getSimpleName().equals(className)) {
	    return Types.FLOAT;
    	}
	if (Double.class.getName().equals(className) || Double.class.getSimpleName().equals(className)) {
	    return Types.DOUBLE;
    	}
	if (BigDecimal.class.getName().equals(className) || BigDecimal.class.getSimpleName().equals(className)) {
	    return Types.DECIMAL;
    	}
	if (Date.class.getName().equals(className) || Date.class.getSimpleName().equals(className) 
		|| java.sql.Date.class.getName().equals(className) || java.sql.Date.class.getSimpleName().equals(className)) {
	    return Types.DATE;
    	}
	if (java.sql.Time.class.getName().equals(className) || java.sql.Time.class.getSimpleName().equals(className)) {
	    return Types.TIME;
    	}
	if (java.sql.Timestamp.class.getName().equals(className) || java.sql.Timestamp.class.getSimpleName().equals(className)) {
	    return Types.TIMESTAMP;
    	}
	if (String.class.getName().equals(className) || String.class.getSimpleName().equals(className)) {
	    return Types.VARCHAR;
    	}
	
	return Types.OTHER; // TODO
	*/
	
    }
    
    

}
