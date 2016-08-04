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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.plazmaforge.framework.script.ScriptUtils;


/**
 * @author ohapon
 *
 */
public class LList extends LCollection {

    /**
     * @param value
     */
    public LList(List<?> value) {
	super(Type.LIST, value);
	
    }

    
    protected LCollection newInstance(Collection<LValue> values) {
	return new LList((List<LValue>) values);
    }

    protected <T> Collection<T> newCollection() {
	return ScriptUtils.newList();
    }
    
     
    // *
    @Override
    public LValue _mul(LValue a, LValue b) {
	
	LValue result = nullResult(a, "*", b);
	if (result != null) {
	    return result; 
	}

	// Returns NULL because a=NULL (List) is primary parameter
	if (a == LValue.NULL) {
	    return LValue.NULL;
	}
	
	if (b == LValue.NULL) {
	    raiseIllegalMethodException("" + a + " * " + b + ". Right argument must be >= 1");
	}
	
	if (!b.isNumber()) {
	    return super._mul(a, b);
	}
	
	int stop = b.asInteger();
	if (stop < 1) {
	    raiseIllegalMethodException("" + a + " * " + b + ". Right argument must be >= 1");
	}
	List<LValue> element = a.asList();
	List<LValue> total = ScriptUtils.newList();
	for (int i = 0; i < stop; i++) { 
	    total.addAll(element);
	}
	return new LList(total);
    }

    ////

    @Override
    public LValue _get(LValue index) {
	return getListValue(index);
    }

    @Override
    public void _set(LValue index, LValue value) {
	setListValue(index, value);
    }

    @Override
    public LValue _invoke(String method, List<LValue> parameters) {
	if ("indexOf".equals(method)) {
	    checkMethod(method, parameters, 1);
	    return new LNumber(asList().indexOf(parameters.get(0)));
	} else if ("set".equals(method)) {
	    checkMethod(method, parameters, 2);
	    LValue indexParameter = parameters.get(0);
	    if (!indexParameter.isNumber()) {
		raiseIllegalMethodParameterTypeException("Number");
	    }
	    LValue valueParameter = parameters.get(1);
	    setListValue(indexParameter, valueParameter);
	    return LValue.VOID;
	} else if ("get".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue indexParameter = parameters.get(0);
	    if (!indexParameter.isNumber()) {
		raiseIllegalMethodParameterTypeException("Number");
	    }
	    return getListValue(indexParameter);
	    
	} if ("sort".equals(method)) {
	    checkMethod(method, parameters, 0);
	    Comparator<LValue> comparator = createValueComparator();
	    Collections.sort(asList(), comparator);		// COMPARATOR
	    return LValue.VOID;
	} else if ("fill".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    Collections.fill(asList(), parameter);
	    return LValue.VOID;
	} else if ("reverse".equals(method)) {
	    // [1, -2, 3, 100] -> [100, 3, -2, 1]
	    checkMethod(method, parameters, 0);
	    Collections.reverse(asList());
	    return LValue.VOID;
	} else if ("shuffle".equals(method)) {
	    // random filling
	    checkMethod(method, parameters, 0);
	    Collections.shuffle(asList());
	    return LValue.VOID;
	} else if ("shift".equals(method)) {
	    // shift([3, 7, 1, 9], 1)  -> [9, 3, 7, 1]
	    // shift([3, 7, 1, 9], -1) -> [7, 1, 9, 3]
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    if (!parameter.isNumber()) {
		raiseIllegalMethodParameterTypeException("Number");
	    }
	    Integer distance = parameter.asInteger();
	    Collections.rotate(asList(), distance);
	    return LValue.VOID;
	} else if ("clone".equals(method)) {
	    checkMethod(method, parameters, 0);
	    List<LValue> list = ScriptUtils.cloneList(asList());
	    LList result = new LList(list);
	    return result;
	}
	
	return super._invoke(method, parameters);
    }
    
    protected LValue getListValue(LValue index) {
        int idx = getIndexValue(index);
        List<LValue> list = asList();
        
        // TODO: Check index range. Get safe value
        if (idx < 0) {
            throw new RuntimeException("Invalid index value: " + idx);
        }
        if (idx > list.size() - 1) {
            return null;
        }
        
        return list.get(idx);
    }
    
    protected void setListValue(LValue index, LValue value) {
        int idx = getIndexValue(index);
        List<LValue> list = asList();

        // TODO: Check index range. Get safe value
        if (idx < 0) {
            throw new RuntimeException("Invalid index value: " + idx);
        }
        
        if (idx > list.size() - 1) {
            
            // Auto increase List
            
            // 1. Add only one element
            if (idx == list.size()) {
        	
        	list.add(value);
        	return;
            }
            
            // 2. Add more element 
            int addSize = idx - (list.size() - 1); 
            LValue[] addArray = new LValue[addSize];
            
            // Fill NULL value
            Arrays.fill(addArray, LValue.NULL);
            
            // Set value (last element)
            addArray[addSize - 1] = value;
            
            list.addAll(Arrays.asList(addArray));
            return;
        }
        
        asList().set(idx, value);
    }    
    
}
