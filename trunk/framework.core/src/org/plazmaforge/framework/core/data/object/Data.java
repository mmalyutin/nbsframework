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

package org.plazmaforge.framework.core.data.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author ohapon
 *
 */
public class Data implements IData {

    private static final long serialVersionUID = 526645210328404027L;
    
    
    
    private Map<String, Object> values = new HashMap<String, Object>();
    
    
    public Data() {
    }
    
    
    public Data(Map<String, Object> values) {
	this.values = values;
    }



    @Override
    public Object get(String property) {
	if (values == null) {
	    return null;
	}
	return values.get(property);
    }

    @Override
    public void set(String property, Object value) {
	if (values == null) {
	    values = new HashMap<String, Object>();
	}
	values.put(property, value);
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }

    @Override
    public List<String> getPropertyNames() {
	return values == null ? new ArrayList<String>() : new ArrayList<String>(values.keySet());
    }
}
