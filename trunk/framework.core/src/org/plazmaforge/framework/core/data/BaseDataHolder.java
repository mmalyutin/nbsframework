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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.util.CoreUtils;

public class BaseDataHolder implements DataHolder {

    private Object data;
    
    private Map<String, Object> dataMap;
    
    @Override
    public Object getData() {
	return data;
    }

    @Override
    public void setData(Object data) {
	this.data = data;
    }

    @Override
    public Object getData(String property) {
	return getDataMap().get(property);
    }

    @Override
    public void setData(String property, Object data) {
	getDataMap().put(property, data);
    }

    @Override
    public List<String> getDataNames() {
	return CoreUtils.toKeyList(getDataMap());
    }

    private Map<String, Object> getDataMap() {
	if (dataMap == null) {
	    dataMap = new HashMap<String, Object>();
	}
	return dataMap;
    }

}
