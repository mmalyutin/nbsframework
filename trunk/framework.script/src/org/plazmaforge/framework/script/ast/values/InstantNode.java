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

package org.plazmaforge.framework.script.ast.values;

import java.util.Date;
import java.util.List;

import org.plazmaforge.framework.script.ScriptUtils;
import org.plazmaforge.framework.script.ast.LNode;
import org.plazmaforge.framework.script.lang.LValue;
import org.plazmaforge.framework.script.util.CommonUtils;

public abstract class InstantNode extends ValueNode {

    protected List<LNode> expressionNodes;
    
    public InstantNode(List<LNode> nodes) {
	expressionNodes = ScriptUtils.getSafeList(nodes);
    }
    
    protected Date createDate(List<LNode> params) {
	
	// no parameters
	if (params == null || params.isEmpty()) {
	    return CommonUtils.newDate();
	}
	
	// one parameter
	if (params.size() == 1) {
	    Long value = getLongParameter(params, 0);
	    return CommonUtils.getDate(value);
	}
	
	if (params.size() < 3) {
	    throw new RuntimeException("Can't parse date: invalid parameter count");
	}
	
	Integer value1 = getIntegerParameter(params, 0);
	Integer value2 = getIntegerParameter(params, 1);
	Integer value3 = getIntegerParameter(params, 2);
	
	Integer[] values = new Integer[] {value1, value2, value3};
	return createDate(values);
    }

    protected Date createDateTime(List<LNode> params) {
	
	// no parameters
	if (params == null || params.isEmpty()) {
	    return CommonUtils.newDateTime();
	}
	
	// one parameter
	int size = params.size(); 
	if (size == 1) {
	    Long value = getLongParameter(params, 0);
	    return CommonUtils.getDateTime(value);
	}
	
	if (size < 3) {
	    throw new RuntimeException("Can't parse date: invalid parameter count");
	}
	
	Integer value1 = getIntegerParameter(params, 0);
	Integer value2 = getIntegerParameter(params, 1);
	Integer value3 = getIntegerParameter(params, 2);
	Integer value4 = null;
	Integer value5 = null;
	Integer value6 = null;
	Integer value7 = null;
	
	if (size > 3) {
	    value4 = getIntegerParameter(params, 3);
	}
	if (size > 4) {
	    value5 = getIntegerParameter(params, 4);
	}
	if (size > 5) {
	    value6 = getIntegerParameter(params, 5);
	}
	if (size > 6) {
	    value7 = getIntegerParameter(params, 6);
	}
	
	Integer[] values = new Integer[] {value1, value2, value3, value4, value5, value6, value7};
	return createDateTime(values);
    }
    
    protected Date createTime(List<LNode> params) {
	
	// no parameters
	if (params == null || params.isEmpty()) {
	    return CommonUtils.newTime();
	}
	
	// one parameter
	int size = params.size(); 
	if (size == 1) {
	    Long value = getLongParameter(params, 0);
	    return CommonUtils.getTime(value);
	}

	Integer value1 = null;
	Integer value2 = null;
	Integer value3 = null;
	Integer value4 = null;
	
	if (size > 0) {
	    value1 = getIntegerParameter(params, 0);
	}
	if (size > 1) {
	    value2 = getIntegerParameter(params, 1);
	}
	if (size > 2) {
	    value3 = getIntegerParameter(params, 2);
	}
	if (size > 3) {
	    value4 = getIntegerParameter(params, 3);
	}
	
	Integer[] values = new Integer[] {value1, value2, value3, value4};
	return createTime(values);
    }
    
    
    protected int toInt(Integer value) {
	return value == null ? 0: value.intValue();
    }
    
    protected Integer getIntegerParameter(List<LNode> params, int index) {
	LValue p = params.get(index).evaluate();
	if (!p.isNumber()) {
	    throw new RuntimeException("Can't parse date: parameter" + (index == 0 ? "" : index) + " must be integer");
	}
	return p.asInteger();
    }

    protected Long getLongParameter(List<LNode> params, int index) {
	LValue p = params.get(index).evaluate();
	if (!p.isNumber()) {
	    throw new RuntimeException("Can't parse date: parameter" + (index == 0 ? "" : index) + " must be long");
	}
	return p.asLong();
    }
    
    protected Date createDate(Integer[] array) {
    	if (array == null) {
    	    return null;
    	}
    	if (array == null || array.length < 3) {
    	    return null;
    	}
    	
    	int year = array[0];
    	int month = array[1] - 1;
    	int day = array[2];

    	return CommonUtils.getDate(year, month, day);
    }
    
    protected Date createDateTime(Integer[] array) {
    	if (array == null) {
    	    return null;
    	}
    	if (array == null || array.length < 3) {
    	    return null;
    	}
    	
    	int year = array[0];
    	int month = array[1] - 1;
    	int day = array[2];
    	
    	int h = toInt(array[3]);
    	int m = toInt(array[4]);
    	int s = toInt(array[5]);
    	int ms = toInt(array[6]);
    	
    	return CommonUtils.getDateTime(year, month, day, h, m, s, ms);
    }

    protected Date createTime(Integer[] array) {
    	if (array == null) {
    	    return null;
    	}
    	if (array == null || array.length < 4) {
    	    return null;
    	}
    	
    	int h = toInt(array[0]);
    	int m = toInt(array[1]);
    	int s = toInt(array[2]);
    	int ms = toInt(array[3]);
    	
    	return CommonUtils.getTime(h, m, s, ms);
    }
    
    
}
