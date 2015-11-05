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

import java.io.Serializable;
import java.util.List;

import org.plazmaforge.framework.core.data.LocalizedIdentifier;

/**
 * 
 * DSDataSource describes DataSet/DataCube source
 * 
 * @author ohapon
 *
 */
public interface DSDataSource extends Serializable, LocalizedIdentifier {

    
    String getType();

    void setType(String type);

    
    boolean isStandalone();
    
    void setStandalone(boolean standalone);
    
    
    DSQuery getQuery();

    void setQuery(DSQuery query);
    
    String getQueryText();
    
    void setQueryText(String text);
    

    String getParentName();
    
    void setParentName(String parentName);

    
    String getDataConnectorName();

    void setDataConnectorName(String dataConnectorName);
    
    
    List<DSParameter> getParameters();

    DSParameter getParameter(String name);
    
    void setParameters(List<DSParameter> parameters);
    
    void addParameter(DSParameter parameter);

    void removeParameter(DSParameter parameter);
    
    boolean hasParameters();

    int getParameterCount();
    
    
    List<DSField> getFields();

    DSField getField(String name);

    void setFields(List<DSField> fields);
    
    void addField(DSField field);

    void removeField(DSField field);
    
    boolean hasFields();

    int getFieldCount();
    
    
    List<DSVariable> getVariables();

    DSVariable getVariable(String name);
    
    void setVariables(List<DSVariable> variables);
    
    void addVariable(DSVariable variable);

    void removeVariable(DSVariable variable);
    
    boolean hasVariables();

    int getVariableCount();
    
    
    List<DSOrder> getOrders();

    void setOrders(List<DSOrder> orders);
    
    void addOrder(DSOrder order);

    void removeOrder(DSOrder order);

    boolean hasOrders();
    
    int getOrderCount();
    
    
    List<DSFilter> getFilters();

    void setFilters(List<DSFilter> filters);

    void addFilter(DSFilter filter);

    void removeFilter(DSFilter filter);
    
    boolean hasFilters();

    int getFilterCount();
    
    
    List<DSGroup> getGroups();

    void setGroups(List<DSGroup> groups);
    
    void addGroup(DSGroup group);

    void removeGroup(DSGroup group);
    
    boolean hasGroups();

    int getGroupCount();
    
    
    List<DSDimension> getDimensions();

    void setDimensions(List<DSDimension> dimensions);
    
    void addDimension(DSDimension dimension);

    void removeGroup(DSDimension dimension);
    
    boolean hasDimensions();

    int getDimensionCount();
}
