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
    
    /**
     * Open DSResultSet by connection string.
     * Only for single DSSession/DSDataConnector (one DSSession/DSDataConnector - one DSResultSet: CSV, XML, XLS..)
     * @param connectionString
     * @return
     * @throws DSException
     */
    DSResultSet openResultSet(String connectionString) throws DSException;

    /**
     * Open DSResultSet by DSSession.
     * Only for single DSSession/DSDataConnector (one DSSession/DSDataConnector - one DSResultSet: CSV, XML, XLS..)
     * @param session
     * @return
     * @throws DSException
     */
    DSResultSet openResultSet(DSSession session) throws DSException;
    
    /**
     * Open DSResultSet by DSSession and DSDataSource
     * @param session
     * @param dataSource
     * @return
     * @throws DSException
     */
    DSResultSet openResultSet(DSSession session, DSDataSource dataSource) throws DSException;
    
    /**
     * Open DSResultSet by DSSession and DSDataSource with parameters
     * @param session
     * @param dataSource
     * @param parameters
     * @return
     * @throws DSException
     */
    DSResultSet openResultSet(DSSession session, DSDataSource dataSource, Object[] parameters) throws DSException;
    
    /**
     * Open DSResultSet by DSSession and query
     * @param session
     * @param query
     * @return
     * @throws DSException
     */
    DSResultSet openResultSet(DSSession session, String query) throws DSException;
    
    /**
     * Open DSResultSet by DSSession and query with parameters
     * @param session
     * @param query
     * @param parameters
     * @return
     * @throws DSException
     */
    DSResultSet openResultSet(DSSession session, String query, ParameterValue[] parameters) throws DSException;
    
    
    /**
     * Open DSDataSet by DSSession and DSDataSource
     * @param session
     * @param dataSource
     * @return
     * @throws DSException
     */
    DSDataSet openDataSet(DSSession session, DSDataSource dataSource) throws DSException;
    
    /**
     * Create empty DSDataConnector
     * @return
     */
    DSDataConnector createDataConnector();
    
    /**
     * Returns true if DSDataProducer supports mode with single DSDataSource (CSV, XML, XLS..)
     * @return
     */
    boolean supportsSingleDataSource();

}
