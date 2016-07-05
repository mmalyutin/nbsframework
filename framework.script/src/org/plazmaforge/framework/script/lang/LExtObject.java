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

package org.plazmaforge.framework.script.lang;

import java.util.List;


public class LExtObject extends LValue {

    private LAccessor accessor;
    
    public static LAccessorFactory accessorFactory = new LClassAccessorFactory();   
    
    public LExtObject(Object value) {
	super(Type.EXT_OBJ, value);
    }

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public LValue _get(LValue index) {
	if (index.isString() ){
	    return _get(index.asString());
	}
	raiseIllegalMethodException("get");
	return null;
    }

    @Override
    public void _set(LValue index, LValue value) {
	if (index.isString() ){
	    _set(index.asString(), value);
	    return;
	}
	raiseIllegalMethodException("set");
    }

    ////
    
    @Override
    public LValue _get(String property) {
	return getAccessor().get(getInstance(), property);
    }
    
    @Override
    public void _set(String property, LValue value) {
	getAccessor().set(getInstance(), property, value);
    }
    
    public LValue _invoke(String method, List<LValue> parameters) {
	return getAccessor().invoke(getInstance(), method, parameters);
    }

    protected Object getInstance() {
	return getValue();
    }

    protected Class<?> getInstanceClass() {
	Object instance = getInstance();
	return instance == null ? null : instance.getClass();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected LAccessor getAccessor() {
	if (accessor == null) {
	    accessor = accessorFactory.getAccessor(getInstanceClass());
	}
	return accessor;
    }

}
