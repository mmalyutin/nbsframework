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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * 
 * @author ohapon
 *
 */
public class SQLBaseValueReader extends AbstractSQLWorker implements SQLValueReader {

    
    @Override
    public Object getValue(ResultSet rs, int index, int type)  throws SQLException {
	Object value = null;
	switch (type) {

	case Types.NULL: {
	    break;
	}
	case Types.BIT:
	case Types.BOOLEAN: {
	    value = rs.getBoolean(index);
	    if (rs.wasNull()) {
		value = null;
	    }
	    break;
	}
	case Types.TINYINT: {
	    value = rs.getByte(index);
	    if (rs.wasNull()) {
		value = null;
	    }
	    break;
	}
	case Types.SMALLINT: {
	    value = rs.getShort(index);
	    if (rs.wasNull()) {
		value = null;
	    }
	    break;
	}
	case Types.INTEGER: {
	    value = rs.getInt(index);
	    if (rs.wasNull()) {
		value = null;
	    }
	    break;
	}
	case Types.BIGINT: {
	    value = rs.getLong(index);
	    if (rs.wasNull()) {
		value = null;
	    }
	    break;
	}
	case Types.REAL:
	case Types.FLOAT: {
	    value = rs.getFloat(index);
	    if (rs.wasNull()) {
		value = null;
	    }
	    break;
	}
	case Types.DOUBLE: {
	    value = rs.getDouble(index);
	    if (rs.wasNull()) {
		value = null;
	    }
	    break;
	}

	case Types.DECIMAL:
	case Types.NUMERIC: {
	    value = rs.getBigDecimal(index);
	    if (rs.wasNull()) {
		value = null;
	    }
	    break;
	}
	case Types.DATE: {
	    value = rs.getDate(index);
	    break;
	}
	case Types.TIMESTAMP: {
	    value = rs.getTimestamp(index);
	    break;
	}
	case Types.TIME: {
	    value = rs.getTime(index);
	    break;
	}
	case Types.CHAR:
	case Types.VARCHAR:
	case Types.NVARCHAR:
	case Types.LONGVARCHAR:
	case Types.ROWID: {
	    value = rs.getString(index);
	    break;
	}
	case Types.BLOB: {
	    value = rs.getBlob(index);
	    break;
	}
	case Types.CLOB: {
	    value = rs.getClob(index);
	    break;
	}
	

	default: {
	    value = rs.getObject(index);
	}

	}

	return value;
    }

}
