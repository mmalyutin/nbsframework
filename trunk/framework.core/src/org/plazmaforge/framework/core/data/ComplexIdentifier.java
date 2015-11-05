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

package org.plazmaforge.framework.core.data;

import java.util.List;

public class ComplexIdentifier extends BaseIdentifier implements DataHolder, PropertyHolder {

    private DataHolder dataHolder;
    
    private PropertyHolder propertyHolder;

    @Override
    public String getProperty(String property) {
	return getPropertyHolder().getProperty(property);
    }

    @Override
    public void setProperty(String property, String value) {
	getPropertyHolder().setProperty(property, value);
    }

    @Override
    public List<String> getPropertyNames() {
	return getPropertyHolder().getPropertyNames();
    }

    @Override
    public Object getData() {
	return getDataHolder().getData();
    }

    @Override
    public void setData(Object data) {
	getDataHolder().setData(data);
    }

    @Override
    public Object getData(String property) {
	return getDataHolder().getData(property);
    }

    @Override
    public void setData(String property, Object data) {
	getDataHolder().setData(property, data);
    }

    @Override
    public List<String> getDataNames() {
	return getDataHolder().getDataNames();
    }

    ////
    
    public DataHolder getDataHolder() {
	if (dataHolder == null) {
	    dataHolder = new BaseDataHolder();
	}
        return dataHolder;
    }

    public PropertyHolder getPropertyHolder() {
	if (propertyHolder == null) {
	    propertyHolder = new BasePropertyHolder();
	}
        return propertyHolder;
    }
    
    ////
    
    
}
