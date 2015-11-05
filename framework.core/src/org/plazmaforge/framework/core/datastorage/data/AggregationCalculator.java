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
package org.plazmaforge.framework.core.datastorage.data;

/**
 * @author ohapon
 *
 */
public class AggregationCalculator {

    public Object calculateValue(Scope scope, String variable, String aggregation, Object value) {
   	if (aggregation == null){
   	    return value;
   	}
   	aggregation = aggregation.trim();
   	if (aggregation.isEmpty()) {
   	    return value;
   	}
   	AggregationValue aggregationValue = scope.getVariableAggregationValue(variable);
   	if (aggregationValue == null){
   	    aggregationValue = new AggregationValue();
   	    scope.setVariableAggregationValue(variable, aggregationValue);
   	}
   	
   	aggregation = aggregation.toUpperCase();
   	
   	// TODO
   	if  ("SUM".equals(aggregation)) {
   	    
   	    Double sumValue = getCastDouble(aggregationValue.getSumValue(), 0.0);
   	    Double currValue =  getCastDouble(value, 0.0);
   	    
   	    sumValue = sumValue + currValue;
   	    aggregationValue.setSumValue(sumValue);
   	    
   	    return sumValue;
   	}

   	if  ("COUNT".equals(aggregation)) {
   	    Integer countValue = getCastInteger(aggregationValue.getCountValue(), 0);
   	    if (value != null) {
   		countValue = countValue + 1;
   	    }
   	    aggregationValue.setCountValue(countValue);
   	    
   	    return countValue;
   	}

   	return value;
       }
    
    protected Integer getCastInteger(Object value) {
	return getCastInteger(value, null);
    }

    protected Integer getCastInteger(Object value, Integer def) {
	return value == null || !(value instanceof Number) ? def : ((Number) value).intValue();

    }

    protected Double getCastDouble(Object value) {
	return getCastDouble(value, null);
    }

    protected Double getCastDouble(Object value, Double def) {
	return value == null || !(value instanceof Number) ? def: ((Number) value).doubleValue();

    }
}
