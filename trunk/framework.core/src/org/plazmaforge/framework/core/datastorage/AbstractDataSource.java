/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.core.datastorage;

import org.plazmaforge.framework.core.data.BaseLocalizedIdentifier;

/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractDataSource extends BaseLocalizedIdentifier {

    private String type;
    
    private boolean standalone;
    
    
    private DSQuery query;

    private String parentName;
    
    private String dataConnectorName;
    
    public AbstractDataSource() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStandalone() {
        return standalone;
    }

    public void setStandalone(boolean standalone) {
        this.standalone = standalone;
    }

    public DSQuery getQuery() {
	if (query == null){
	    query = new DSQuery();
	}
        return query;
    }

    public void setQuery(DSQuery query) {
        this.query = query;
    }
    
    public String getQueryText() {
	return getQuery().getText();
    }
    
    public void setQueryText(String text) {
        getQuery().setText(text);
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getDataConnectorName() {
        return dataConnectorName;
    }

    public void setDataConnectorName(String dataConnectorName) {
        this.dataConnectorName = dataConnectorName;
    }
    
    
    
}
