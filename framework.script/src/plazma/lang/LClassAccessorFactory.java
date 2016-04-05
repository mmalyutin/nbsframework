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

package plazma.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import plazma.ClassAccessor;
import plazma.ValueAdapter;

public class LClassAccessorFactory implements LAccessorFactory {

    private Map<String, LClassAccessor> accessorMap = new HashMap<String, LClassAccessor>();
    
    @Override
    public LAccessor getAccessor(Class<?> klass) {
	if (klass == null) {
	    return null;
	}
	String name = klass.getName();
	LClassAccessor accessor = accessorMap.get(name);
	if (accessor == null) {
	    accessor = new LClassAccessor(klass);
	    accessorMap.put(name, accessor);
	}
	return accessor;
    }

    public static class LClassAccessor implements LAccessor {
	
	private ClassAccessor accessor;

	public LClassAccessor(Class<?> klass) {
	    accessor = new ClassAccessor(klass);
	}

	@Override
	public void set(Object object, String property, LValue value) {
	    Object nativeValue = ValueAdapter.toNativeValue(value);
	    accessor.set(object, property, nativeValue);
	}

	@Override
	public LValue get(Object object, String property) {
	    Object nativeValue = accessor.get(object, property);
	    return ValueAdapter.fromNativeValue(nativeValue);
	}

	@Override
	public LValue invoke(Object object, String method, List<LValue> parameters) {
	    List<Object> nativeParameters = parameters == null ? null : new ArrayList<Object>();
	    
	    // Convert parameters to native values
	    if (parameters != null) {
		Object nativeValue = null;
		for (LValue p: parameters) {
		    nativeValue = ValueAdapter.toNativeValue(p);
		    nativeParameters.add(nativeValue);
		}
	    }
	    
	    // Invoke method
	    Object result =  accessor.invoke(object, method, nativeParameters == null ? null : nativeParameters.toArray(new Object[0]));
	    
	    // Convert result to LValue
	    return ValueAdapter.fromNativeValue(result);
	}
	
	
	
    }
}
