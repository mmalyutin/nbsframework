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
package plazma.lang;

import java.util.ArrayList;
import java.util.List;



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

    // +
    @Override
    public LValue _add(LValue a, LValue b) {
	
	LValue result = nullResult(a, "+", b);
	if (result != null) {
	    return result; 
	}
	
	// Returns NULL because a=NULL (Date) is primary parameter
	if (a == LValue.NULL) {
	    return LValue.NULL;
	}
	
	if (!a.isList()) {
	    return super._add(a, b);
	}
	List<LValue> list = a.asList();
	
	//if (b != LValue.NULL) {
	    list.add(b);
	//}
	return new LList(list);
    }
    
    // -
    @Override
    public LValue _sub(LValue a, LValue b) {
	
	LValue result = nullResult(a, "-", b);
	if (result != null) {
	    return result; 
	}
	
	// Returns NULL because a=NULL (Date) is primary parameter
	if (a == LValue.NULL) {
	    return LValue.NULL;
	}
	
	List<LValue> list = a.asList();
	//if (b != LValue.NULL) {
	    list.remove(b);
	//}
        return new LList(list);
    }
    
    // *
    @Override
    public LValue _mul(LValue a, LValue b) {
	
	LValue result = nullResult(a, "*", b);
	if (result != null) {
	    return result; 
	}

	// Returns NULL because a=NULL (Date) is primary parameter
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
	} else if ("indexOf".equals(method)) {
	    checkMethod(method, parameters, 1);
	    return new LNumber(asList().indexOf(parameters.get(0)));
	} else if ("add".equals(method)) {
	    checkMethod(method, parameters, 1);
	    return new LBoolean(asList().add(parameters.get(0)));
	} else if ("remove".equals(method)) {
	    checkMethod(method, parameters, 1);
	    return new LBoolean(asList().remove(parameters.get(0)));
	}


	return super._invoke(method, parameters);
    }
    
    protected LValue getListValue(LValue index) {
        int idx = getIndexValue(index);
        // TODO: Check index range
        return asList().get(idx);
    }
    
    protected void setListValue(LValue index, LValue value) {
        int idx = getIndexValue(index);
        // TODO: Check index range
        asList().set(idx, value);
    }    
    
}
