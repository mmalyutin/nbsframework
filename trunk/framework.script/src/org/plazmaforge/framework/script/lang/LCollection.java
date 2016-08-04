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

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.plazmaforge.framework.script.ScriptUtils;
import org.plazmaforge.framework.script.ValueComparator;

public abstract class LCollection extends LValue {

    protected LCollection(Object value) {
	super(value);
    }

    protected LCollection(Type type, Object value) {
	super(type, value);
    }

    protected abstract LCollection newInstance(Collection<LValue> values);

    protected abstract <T> Collection<T> newCollection();
    
    

    // in
    @Override
    public LValue _in(LValue a, LValue b) {
	LValue result = nullResult(a, "in", b);
	if (result != null) {
	    return result; 
	}
	
	if (!b.isCollection()) {
	    return super._in(a, b);
	}
	
	Collection<LValue> list = b.asCollection();
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
	
	if (!equalsType(a) || (!b.isCollection()) && b != LValue.NULL) {
	    return super._add(a, b);
	}
	
	// Collection + any Collection
	Collection<LValue> list = a.asCollection();
	if (b != LValue.NULL) {
	    list.addAll(b.asCollection());
	}
	return newInstance(list);
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
	
	if (!equalsType(a) || (!b.isCollection()) && b != LValue.NULL) {
	    return super._sub(a, b);
	}
		
	// Collection - any Collection
	Collection<LValue> list = a.asCollection();
	if (b != LValue.NULL) {
	    list.removeAll(b.asCollection());
	}
	return newInstance(list);
    }

    
    // &
    @Override
    public LValue _bitAnd(LValue a, LValue b) {
	
	LValue result = nullResult(a, "&", b);
	if (result != null) {
	    return result; 
	}
	
	// Returns NULL because a=NULL (Collection) is primary parameter
	if (a == LValue.NULL) {
	    return LValue.NULL;
	}
	
	if (!equalsType(a) || (!b.isCollection()) && b != LValue.NULL) {
	    return super._bitAnd(a, b);
	}
	
	// Collection & any Collection : intersection
	if (b == LValue.NULL) {
	    return newInstance(a.asCollection());
	}
	
	Collection<LValue> collection1 = a.asCollection();
	Collection<LValue> collection2 = b.asCollection();
	Collection<LValue> collection = intersection(collection1, collection2);
	
	return newInstance(collection);
    }
    
    
    // |
    @Override
    public LValue _bitOr(LValue a, LValue b) {
	
	LValue result = nullResult(a, "|", b);
	if (result != null) {
	    return result; 
	}
	
	// Returns NULL because a=NULL (Collection) is primary parameter
	if (a == LValue.NULL) {
	    return LValue.NULL;
	}
	
	if (!equalsType(a) || (!b.isCollection()) && b != LValue.NULL) {
	    return super._bitOr(a, b);
	}
	
	// List | List : union
	if (b == LValue.NULL) {
	    return newInstance(a.asCollection());
	}
	
	Collection<LValue> collection1 = a.asCollection();
	Collection<LValue> collection2 = b.asCollection();
	Collection<LValue> collection = union(collection1, collection2);
	
	return newInstance(collection);
    }   
    
    ////
    
    @Override
    public LValue _invoke(String method, List<LValue> parameters) {
	if ("size".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(asCollection().size());
	} else if ("isEmpty".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LBoolean(asCollection().isEmpty());
	} else if ("add".equals(method)) {
	    checkMethod(method, parameters, 1);
	    return new LBoolean(asCollection().add(parameters.get(0)));
	} else if ("remove".equals(method)) {
	    checkMethod(method, parameters, 1);
	    return new LBoolean(asCollection().remove(parameters.get(0)));
	} else if ("clear".equals(method)) {
	    checkMethod(method, parameters, 0);
	    asCollection().clear();
	    return LValue.VOID;
	} else if ("addAll".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    if (!parameter.isCollection()) {
		raiseIllegalMethodParameterTypeException("Collection");
	    }	    
	    return new LBoolean(asCollection().addAll(parameter.asCollection()));
	} else if ("removeAll".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    if (!parameter.isCollection()) {
		raiseIllegalMethodParameterTypeException("Collection");
	    }	    
	    return new LBoolean(asCollection().removeAll(parameter.asCollection()));
	} else if ("contains".equals(method)) {
	    checkMethod(method, parameters, 1);
	    return new LBoolean(asCollection().contains(parameters.get(0)));
	    
	} if ("min".equals(method)) {
	    checkMethod(method, parameters, 0);
	    Comparator<LValue> comparator = createValueComparator();
	    return Collections.min(asCollection(), comparator);	// COMPARATOR
	} else if ("max".equals(method)) {
	    checkMethod(method, parameters, 0);
	    Comparator<LValue> comparator = createValueComparator();
	    return Collections.max(asCollection(), comparator);	// COMPARATOR
	}
	
	
	
	return super._invoke(method, parameters);
    }
    
    
    ////
    
    protected <T> Collection<T> union(Collection<T> collection1, Collection<T> collection2) {
        Set<T> set = ScriptUtils.newSet();

        set.addAll(collection1);
        set.addAll(collection2);

        Collection<T> list = newCollection();
        list.addAll(set);
        return list;
    }
    
    protected <T> Collection<T> intersection(Collection<T> collection1, Collection<T> collection2) {
        Collection<T> collection = newCollection();

        for (T t : collection1) {
            if (collection2.contains(t)) {
                collection.add(t);
            }
        }

        return collection;
    }    
    
    protected Comparator<LValue> createValueComparator() {
	return new ValueComparator();
    }
    
}
