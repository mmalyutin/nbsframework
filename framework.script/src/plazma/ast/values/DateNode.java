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

package plazma.ast.values;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


import plazma.ast.LNode;
import plazma.lang.LDate;
import plazma.lang.LValue;

public class DateNode extends ValueNode {

    public DateNode(String value) {
	this.value = value == null ? LValue.NULL : new LDate(splitDate(value));
    }

    public DateNode(List<LNode> params) {
	this.value = new LDate(createDate(params));
    }
    
    // TODO: temp solution
    protected Date splitDate(String value) {
	if (value == null) {
	    return null;
	}
	
	// TODO
	String[] values = value.split(",");
	return parseDate(values); 
	
    }
    
    protected Date createDate(List<LNode> params) {
	if (params == null || params.isEmpty()) {
	    return new Date();
	}
	if (params.size() < 3) {
	    throw new RuntimeException("Can't parse date: invalid parameter count");
	}
	LValue p1 = params.get(0).evaluate();
	LValue p2 = params.get(1).evaluate();
	LValue p3 = params.get(2).evaluate();
	
	if (!p1.isNumber() ) {
	    throw new RuntimeException("Can't parse date: parameter1 must be integer");
	}
	if (!p2.isNumber() ) {
	    throw new RuntimeException("Can't parse date: parameter2 must be integer");
	}
	if (!p3.isNumber() ) {
	    throw new RuntimeException("Can't parse date: parameter3 must be integer");
	}
	
	Integer i1 = p1.asInteger();
	Integer i2 = p2.asInteger();
	Integer i3 = p3.asInteger();
	
	String[] values = new String[] {i1.toString(), i2.toString(), i3.toString()};
	return parseDate(values);
    }

    
    
    public static Date parseDate(String str) {
    	if (str == null) {
    		return null;
    	}
    	int start = "#Date{".length();
    	if (str.length() <= start + 1){
    		return null;
    	}
    	
    	String val = str.substring(start, str.length() - 1);
    	String[] array = val.split("-");
    	return parseDate(array);
    }
    
    public static Date parseDate(String[] array) {
    	if (array == null) {
    	    return null;
    	}
    	if (array == null || array.length < 3) {
    	    return null;
    	}
    	
    	int year = Integer.parseInt(array[0]);
    	int month = Integer.parseInt(array[1]) - 1;
    	int day = Integer.parseInt(array[2]);
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.YEAR, year);
    	calendar.set(Calendar.MONTH, month);
    	calendar.set(Calendar.DAY_OF_MONTH, day);
    	
    	return calendar.getTime();
    	
    }
    
}

