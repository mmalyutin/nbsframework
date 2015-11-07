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
package plazma.ast;

import java.util.ArrayList;
import java.util.List;

import plazma.lang.LValue;

/**
 * @author ohapon
 *
 */
public class RangeNode implements LNode {

    private LNode lhs;
    private LNode rhs;

    protected boolean exclude;
    
    public RangeNode(LNode lhs, LNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public LValue evaluate() {

        LValue a = lhs.evaluate();
        LValue b = rhs.evaluate();

        // integer + integer
        if (a.isNumber() && b.isNumber()) {
            return generateIntegerRange(a.asInteger(), b.asInteger());
        }

        // string + string
        if (a.isString() && b.isString()) {
            return generateCharRange(a.asString(), b.toString());
        }

        throw new RuntimeException("illegal expression: " + this);
    }

    protected LValue generateIntegerRange(Integer start, Integer end) {
	if (start == null){
	    start = 0;
	}
	if (end == null){
	    start = 0;
	}
	if (start > end) {
	    throw new RuntimeException("illegal expression: " + this + ". Integer range error: start > end.");
	}
	List<LValue> list = new ArrayList<LValue>();
	LValue range = new LValue(list);
	if (start == end) {
	    return range;
	}
	if (exclude) {
	    end--;
	}
	LValue value = null;
	for (int i = start; i <= end; i++) {
	    value = new LValue(i);
	    list.add(value);
	}
	return range;
    }

    protected LValue generateCharRange(String start, String end) {
	char a = 0;
	char b = 0;
	if (start == null || start.length() == 0){
	    a = 0;
	} else {
	    if (start.length() != 1) {
		throw new RuntimeException("illegal expression: " + this + ". Char range error: start size = " + start.length());
	    } else {
		a = start.charAt(0);
	    }
	}
	if (end == null || end.length() == 0){
	    b = 0;
	} else {
	    if (end.length() != 1) {
		throw new RuntimeException("illegal expression: " + this + ". Char range error: end size = " + end.length());
	    } else {
		b = end.charAt(0);
	    }
	}
	if (a > b) {
	    throw new RuntimeException("illegal expression: " + this + ". Char range error: start > end.");
	}
	List<LValue> list = new ArrayList<LValue>();
	LValue range = new LValue(list);
	if (start == end) {
	    return range;
	}
	if (exclude) {
	    b--;
	}
	LValue value = null;
	for (char c = a; c <= b; c++) {
	    value = new LValue(String.valueOf(c));
	    list.add(value);
	}
	return range;
    }

    @Override
    public String toString() {
        return String.format("(%s + %s)", lhs, rhs);
    }


}
