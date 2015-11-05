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
package org.plazmaforge.framework.core.datastorage;


import java.util.Properties;

import org.plazmaforge.framework.core.data.ParameterValue;
import org.plazmaforge.framework.core.exception.DSException;

/**
 * @author ohapon
 *
 */
public interface DataProducer {

    DSSession openSession(DSDataConnector dataConnector) throws DSException;
    
    //sql::driver=, url=, username=, password=
    DSSession openSession(String connectionString) throws DSException;

    DSSession openSession(String connectionString, Properties properties) throws DSException;
    
    DSSession openSession(String connectionString, String username, String password) throws DSException;    
    
    DSSession openSession(Properties properties) throws DSException;
    
    DSSession openWrapSession(Object data) throws DSException;
    
    
    DSResultSet openResultSet(DSSession session, DSDataSource dataSource) throws DSException;
    
    DSResultSet openResultSet(DSSession session, DSDataSource dataSource, Object[] parameters) throws DSException;
    
    DSResultSet openResultSet(DSSession session, String query) throws DSException;
    
    DSResultSet openResultSet(DSSession session, String query, ParameterValue[] parameters) throws DSException;
    
    
    DSDataSet openDataSet(DSSession session, DSDataSource dataSource) throws DSException;

}
