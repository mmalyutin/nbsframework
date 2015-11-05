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

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class SQLBaseValueWriter extends AbstractSQLWorker implements SQLValueWriter {

    @Override
    public void setValue(PreparedStatement stm, Object value, int index, int type) throws SQLException {

	if (value == null || type == Types.NULL) {
	    stm.setNull(index, type);
	    return;
	}
	
	switch (type) {

	case Types.BIT:
	case Types.BOOLEAN: {
	    stm.setBoolean(index, (Boolean) value);
	    break;
	}
	case Types.TINYINT: {
	    stm.setByte(index, toByte(value));
	    break;
	}
	case Types.SMALLINT: {
	    stm.setShort(index, toShort(value));
	    break;
	}
	case Types.INTEGER: {
	    stm.setInt(index, toInteger(value));
	    break;
	}
	case Types.BIGINT: {
	    stm.setLong(index, toLong(value));
	    break;
	}
	case Types.REAL:
	case Types.FLOAT: {
	    stm.setFloat(index, toFloat(value));
	    break;
	}
	case Types.DOUBLE: {
	    stm.setDouble(index, toDouble(value));
	    break;
	}
	case Types.DECIMAL:
	case Types.NUMERIC: {
	    stm.setBigDecimal(index, toBigDecimal(value));
	    break;
	}
	case Types.DATE: {
	    stm.setDate(index, toSQLDate(value));
	    break;
	}
	case Types.TIMESTAMP: {
	    stm.setTimestamp(index, toSQLTimestamp(value));
	    break;
	}
	case Types.TIME: {
	    stm.setTime(index, toSQLTime(value));
	    break;
	}
	case Types.CHAR:
	case Types.VARCHAR:
	case Types.NVARCHAR:
	case Types.LONGVARCHAR:
	case Types.ROWID: {
	    stm.setString(index, toString(value));
	    break;
	}
	case Types.BLOB: {
	    stm.setBlob(index, toSQLBlob(value));
	    break;
	}
	case Types.CLOB: {
	    stm.setClob(index, toSQLClob(value));
	    break;
	}

	}
    }    
    

}
