/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.uwt.widget.chart.util;

public class ChartDataUtils {

    private ChartDataUtils() {
    }

    public static Double max(Number[] values) {
	if (values == null || values.length == 0){
	    return null;
	}
	Double max = null;	
	for (Number value: values) {
	    if (value == null) {
		continue;
	    }
	    if (max == null) {
		max = value.doubleValue();
		continue;
	    }
	    max = Math.max(max, value.doubleValue());
	}
	return max;
    }
    
    public static Double min(Number[] values) {
	if (values == null || values.length == 0) {
	    return null;
	}
	Double min = null;
	for (Number value : values) {
	    if (value == null) {
		continue;
	    }
	    if (min == null) {
		min = value.doubleValue();
		continue;
	    }
	    min = Math.min(min, value.doubleValue());
	}
	return min;
    }
    
    public static Number[] toNumber(Object[] values) {
	if (values == null) {
	    return null;
	}
	Number[] result = new Number[values.length];
	Object value = null;
	for (int i = 0; i < values.length; i++) {
	    value = values[i];
	    if (value == null || !(value instanceof Number)) {
		continue;
	    }
	    result[i] = (Number) value;
	}
	return result;
    }
    
    
}
