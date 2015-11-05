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
public class TimeType extends AbstractDateType {

    public static final String NAME = "Time";
    
    public Object get(ResultSet rs, int index) throws SQLException {
	return rs.getTime(index);
    }
    
    public void set(PreparedStatement stm, Object value, int index) throws SQLException {
	stm.setTime(index, toSQLTime(value));
    }
    
    public int getSqlType() {
	return Types.TIME;
    }
    
    public Class getJavaType() {
	return java.sql.Time.class;
    }

    public Object toValue(Object value) {
	return toSQLTime(value);
    }

}
