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
import java.util.Date;

/**
 * 
 * @author ohapon
 *
 */
public class SQLEnvironment {

    public static Class<?> getClass(int sqlType) {

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
    }
    
    public static String getClassName(int sqlType) {
	Class<?> klass = getClass(sqlType);
	return klass == null ? null : klass.getName();
    }
    
    
    public static int getSQLType(String className) {

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
    }
    
    

}
