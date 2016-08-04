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
package org.plazmaforge.framework.script.lang;

import java.util.List;
import java.util.Map;


/**
 * @author ohapon
 *
 */
public class LMap extends LValue {

    /**
     * @param value
     */
    public LMap(Map<?, ?> value) {
	super(Type.MAP, value);
    }

    ////
    
    @Override
    public LValue _get(LValue index) {
	return getMapValue(index);
    }

    @Override
    public void _set(LValue index, LValue value) {
	setMapValue(index, value);
    }

    @Override
    public LValue _invoke(String method, List<LValue> parameters) {
	if ("size".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(asMap().size());
	} else if ("isEmpty".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LBoolean(asMap().isEmpty());
	} else if ("set".equals(method)) {
	    checkMethod(method, parameters, 2);
	    setMapValue(parameters.get(0), parameters.get(1));
	    return LValue.VOID;
	} else if ("get".equals(method)) {
	    checkMethod(method, parameters, 1);
	    return getMapValue(parameters.get(0));
	} else if ("remove".equals(method)) {
	    checkMethod(method, parameters, 1);
	    return asMap().remove(parameters.get(0));
	} else if ("clear".equals(method)) {
	    checkMethod(method, parameters, 0);
	    asMap().clear();
	    return LValue.VOID;	    
	} else if ("containsKey".equals(method)) {
	    checkMethod(method, parameters, 1);
	    return new LBoolean(asMap().containsKey(parameters.get(0)));
	} else if ("containsValue".equals(method)) {
	    checkMethod(method, parameters, 1);
	    return new LBoolean(asMap().containsValue(parameters.get(0)));
	}
	return super._invoke(method, parameters);
    }
    
    ////
    
    protected LValue getMapValue(LValue index) {
    	return asMap().get(index);
    }

    protected void setMapValue(LValue index, LValue value) {
    	asMap().put(index, value);
    }    
}
