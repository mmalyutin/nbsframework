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

package org.plazmaforge.framework.core.sql.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * 
 * @author ohapon
 *
 */
public class YNBooleanType extends AbstractType {

    public Object get(ResultSet rs, int index) throws SQLException {
	return toBoolean(rs.getString(index));
    }
    
    public void set(PreparedStatement stm, Object value, int index) throws SQLException {
	stm.setString(index, toString((Boolean) value));
    }

    public int getSqlType() {
	return Types.CHAR;
    }

    public Class getJavaType() {
	return String.class;
    }

    protected Boolean toBoolean(String value) {
	if (value != null && 
		(value.equalsIgnoreCase("y") || value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("true") || value.equalsIgnoreCase("1"))) {
	    return Boolean.TRUE;
	}
	return Boolean.FALSE;
    }
    
    protected String toString(Boolean value) {
	String ynString;
	if (value != null) {
	    if (value.booleanValue()) {
		ynString = "Y";
	    } else {
		ynString = "N";
	    }
	} else {
	    ynString = "N";
	}
	return ynString;
    }
}
