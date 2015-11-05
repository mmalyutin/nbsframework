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
package org.plazmaforge.framework.core.datastorage.model;

import java.util.List;

import org.plazmaforge.framework.core.datastorage.DSDataConnector;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSResultSet;
import org.plazmaforge.framework.core.datastorage.DSSession;

/**
 * @author ohapon
 *
 */
public class DSDataItem {

    private DSDataConnector dataConnector;
    
    private DSDataSource dataSource;
    
    private DSResultSet resultSet;
    
    private DSSession session;
    
    
    private List<DSField> fields;
    
    /**
     * True if data is initialized
     */
    private boolean initData;
    
    /**
     * True if data embedded from outside environment 
     */
    private boolean embeddedData;
    
    /**
     * True if data parent data.
     * The data source has query parameters. 
     */
    private boolean dependencyData;
    
    
    /**
     * True if data is invalidate
     */
    private boolean invalidData;
    
    /**
     * true id all data is fetched
     */
    private boolean fetchedData;
    
    
    public DSDataItem() {
    }
    

    public DSDataItem(DSDataConnector dataConnector, DSDataSource dataSource,  DSResultSet resultSet) {
	this.dataConnector = dataConnector;
	this.dataSource = dataSource;
	this.resultSet = resultSet;
    }

    public DSDataItem(DSDataConnector dataConnector, DSDataSource dataSource) {
	this(dataConnector, dataSource, null);
    }
    public DSDataItem(DSDataSource dataSource) {
	this(null, dataSource, null);
    }


    public DSDataConnector getDataConnector() {
        return dataConnector;
    }

    public void setDataConnector(DSDataConnector dataConnector) {
        this.dataConnector = dataConnector;
    }

    public DSDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DSDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DSResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(DSResultSet resultSet) {
        this.resultSet = resultSet;
    }


    public DSSession getSession() {
        return session;
    }


    public void setSession(DSSession session) {
        this.session = session;
    }

   

    public List<DSField> getFields() {
        return fields;
    }


    public void setFields(List<DSField> fields) {
        this.fields = fields;
    }


    public boolean isInitData() {
        return initData;
    }


    public void setInitData(boolean initData) {
        this.initData = initData;
    }


    public boolean isEmbeddedData() {
        return embeddedData;
    }


    public void setEmbeddedData(boolean embeddedData) {
        this.embeddedData = embeddedData;
    }


    public boolean isDependencyData() {
        return dependencyData;
    }


    public void setDependencyData(boolean dependencyData) {
        this.dependencyData = dependencyData;
    }


    public boolean isInvalidData() {
        return invalidData;
    }


    public void setInvalidData(boolean invalidData) {
        this.invalidData = invalidData;
    }


    public boolean isFetchedData() {
        return fetchedData;
    }


    public void setFetchedData(boolean fetchedData) {
        this.fetchedData = fetchedData;
    }
    

    
    ////
    
    
}
