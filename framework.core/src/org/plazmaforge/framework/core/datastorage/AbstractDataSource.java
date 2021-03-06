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

import java.util.ArrayList;
import java.util.List;

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
    
    private List<DSDimension> dimensions;
    
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
	return query == null ? null : getQuery().getText();
    }
    
    public void setQueryText(String text) {
        getQuery().setText(text);
    }
    
    public boolean hasQuery() {
	return query != null;
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
    
    public List<DSDimension> getDimensions() {
	if (dimensions == null) {
	    dimensions = new ArrayList<DSDimension>();
	}
        return dimensions;
    }

    public void setDimensions(List<DSDimension> dimensions) {
        this.dimensions = dimensions;
    }
    
    public void addDimension(DSDimension dimension) {
        getDimensions().add(dimension);
    }

    public void removeGroup(DSDimension dimension) {
        getDimensions().remove(dimension);
    }    
    
    public boolean hasDimensions() {
	return dimensions != null && !dimensions.isEmpty();
    }

    public int getDimensionCount() {
	return dimensions == null ? 0 : dimensions.size();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime
		* result
		+ ((dataConnectorName == null) ? 0 : dataConnectorName
			.hashCode());
	result = prime * result
		+ ((dimensions == null) ? 0 : dimensions.hashCode());
	result = prime * result
		+ ((parentName == null) ? 0 : parentName.hashCode());
	result = prime * result + ((query == null) ? 0 : query.hashCode());
	result = prime * result + (standalone ? 1231 : 1237);
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	AbstractDataSource other = (AbstractDataSource) obj;
	if (dataConnectorName == null) {
	    if (other.dataConnectorName != null)
		return false;
	} else if (!dataConnectorName.equals(other.dataConnectorName))
	    return false;
	if (dimensions == null) {
	    if (other.dimensions != null)
		return false;
	} else if (!dimensions.equals(other.dimensions))
	    return false;
	if (parentName == null) {
	    if (other.parentName != null)
		return false;
	} else if (!parentName.equals(other.parentName))
	    return false;
	if (query == null) {
	    if (other.query != null)
		return false;
	} else if (!query.equals(other.query))
	    return false;
	if (standalone != other.standalone)
	    return false;
	if (type == null) {
	    if (other.type != null)
		return false;
	} else if (!type.equals(other.type))
	    return false;
	return true;
    }
    
}
