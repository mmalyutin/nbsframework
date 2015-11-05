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

import java.sql.Connection;
import java.sql.SQLException;

import org.plazmaforge.framework.core.datastorage.DSSession;
import org.plazmaforge.framework.core.exception.DSException;

/**
 * @author ohapon
 *
 */
public class SQLSession implements DSSession {

    private Connection connection;
    
    public SQLSession(Connection connection) {
	super();
	this.connection = connection;
    }
    
    public Connection getConnection() {
	return connection;
    }

    @Override
    public void close() throws DSException {
	if (connection == null) {
	    return;
	}
	try {
	    connection.close();
	} catch (SQLException ex) {
	    throw new DSException(ex);
	}
    }

    @Override
    public boolean isClosed() throws DSException {
	if (connection == null) {
	    return true;
	}
	try {
	    return connection.isClosed();
	} catch (SQLException ex) {
	    throw new DSException(ex);
	}
    }

    @Override
    public String getType() {
	return SQLDataProducerFactory.TYPE;
    }
    
    
    
    

}
