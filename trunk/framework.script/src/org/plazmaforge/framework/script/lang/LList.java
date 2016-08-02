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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.plazmaforge.framework.script.ValueComparator;



/**
 * @author ohapon
 *
 */
public class LList extends LValue {

    /**
     * @param value
     */
    public LList(List<?> value) {
	super(Type.LIST, value);
	
    }
    
    // in
    @Override
    public LValue _in(LValue a, LValue b) {
	LValue result = nullResult(a, "in", b);
	if (result != null) {
	    return result; 
	}
	
	if (!b.isList()) {
	    return super._in(a, b);
	}
	
        List<LValue> list = b.asList();
        for (LValue val : list) {
            result = a._eq(a, val);
            if (result.isBoolean() && result.asBoolean()) {
                return LBoolean.TRUE;
            }
        }
        return LBoolean.FALSE;
    }
    

    // +
    @Override
    public LValue _add(LValue a, LValue b) {
	
	LValue result = nullResult(a, "+", b);
	if (result != null) {
	    return result; 
	}
	
	// Returns NULL because a=NULL (List) is primary parameter
	if (a == LValue.NULL) {
	    return LValue.NULL;
	}
	
	if (!a.isList() || (!b.isList()) && b != LValue.NULL) {
	    return super._add(a, b);
	}
	
	// List + List
	List<LValue> list = a.asList();
	if (b != LValue.NULL) {
	    list.addAll(b.asList());
	}
	return new LList(list);
    }
    
    // -
    @Override
    public LValue _sub(LValue a, LValue b) {
	
	LValue result = nullResult(a, "-", b);
	if (result != null) {
	    return result; 
	}
	
	// Returns NULL because a=NULL (List) is primary parameter
	if (a == LValue.NULL) {
	    return LValue.NULL;
	}
	
	if (!a.isList() || (!b.isList()) && b != LValue.NULL) {
	    return super._sub(a, b);
	}
		
	// List - List
	List<LValue> list = a.asList();
	if (b != LValue.NULL) {
	    list.removeAll(b.asList());
	}
        return new LList(list);
    }
    
    // &
    @Override
    public LValue _bitAnd(LValue a, LValue b) {
	
	LValue result = nullResult(a, "&", b);
	if (result != null) {
	    return result; 
	}
	
	// Returns NULL because a=NULL (List) is primary parameter
	if (a == LValue.NULL) {
	    return LValue.NULL;
	}
	
	if (!a.isList() || (!b.isList()) && b != LValue.NULL) {
	    return super._bitAnd(a, b);
	}
	
	// List & List : intersection
	if (b == LValue.NULL) {
	    return new LList(a.asList());
	}
	
	List<LValue> list1 = a.asList();
	List<LValue> list2 = b.asList();
	List<LValue> list = intersection(list1, list2);
	
	return new LList(list);
    }
    
    
    // |
    @Override
    public LValue _bitOr(LValue a, LValue b) {
	
	LValue result = nullResult(a, "|", b);
	if (result != null) {
	    return result; 
	}
	
	// Returns NULL because a=NULL (List) is primary parameter
	if (a == LValue.NULL) {
	    return LValue.NULL;
	}
	
	if (!a.isList() || (!b.isList()) && b != LValue.NULL) {
	    return super._bitOr(a, b);
	}
	
	// List | List : union
	if (b == LValue.NULL) {
	    return new LList(a.asList());
	}
	
	List<LValue> list1 = a.asList();
	List<LValue> list2 = b.asList();
	List<LValue> list = union(list1, list2);
	
	return new LList(list);
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
	List<LValue> total = new ArrayList<LValue>();
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
	if ("size".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(asList().size());
	} else if ("isEmpty".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LBoolean(asList().isEmpty());
	} else if ("indexOf".equals(method)) {
	    checkMethod(method, parameters, 1);
	    return new LNumber(asList().indexOf(parameters.get(0)));
	} else if ("add".equals(method)) {
	    checkMethod(method, parameters, 1);
	    return new LBoolean(asList().add(parameters.get(0)));
	} else if ("remove".equals(method)) {
	    checkMethod(method, parameters, 1);
	    return new LBoolean(asList().remove(parameters.get(0)));
	} else if ("clear".equals(method)) {
	    checkMethod(method, parameters, 0);
	    asList().clear();
	    return LValue.VOID;
	} else if ("addAll".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    if (!parameter.isList()) {
		raiseIllegalMethodParameterTypeException("List");
	    }	    
	    return new LBoolean(asList().addAll(parameter.asList()));
	} else if ("removeAll".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    if (!parameter.isList()) {
		raiseIllegalMethodParameterTypeException("List");
	    }	    
	    return new LBoolean(asList().removeAll(parameter.asList()));
	} else if ("contains".equals(method)) {
	    checkMethod(method, parameters, 1);
	    return new LBoolean(asList().contains(parameters.get(0)));
	} else if ("sort".equals(method)) {
	    checkMethod(method, parameters, 0);
	    Comparator<LValue> comparator = createValueComparator();
	    Collections.sort(asList(), comparator);		// COMPARATOR
	    return LValue.VOID;
	} else if ("min".equals(method)) {
	    checkMethod(method, parameters, 0);
	    Comparator<LValue> comparator = createValueComparator();
	    return Collections.min(asList(), comparator);	// COMPARATOR
	} else if ("max".equals(method)) {
	    checkMethod(method, parameters, 0);
	    Comparator<LValue> comparator = createValueComparator();
	    return Collections.max(asList(), comparator);	// COMPARATOR
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
    
    ////
    
    protected <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new LinkedHashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }
    
    protected <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            if (list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }    
    
    protected Comparator<LValue> createValueComparator() {
	return new ValueComparator();
    }
}
