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

import java.util.HashMap;
import java.util.Map;

import org.plazmaforge.framework.core.exception.DSEvaluateException;

/**
 * @author ohapon
 *
 */
public class AggregationCalculator {
    
    private Map<String, AggregationFunction> functions = new HashMap<String, AggregationFunction>();
    
    private static final ValueComparator valueComparator = new ValueComparator();
    

    public AggregationCalculator() {
	super();
    }

    public void registerFunction(String name, AggregationFunction function) {
	functions.put(name, function);
    }
    
    public AggregationFunction getFunction(String name) {
  	if (name == null) {
  	    return null;
  	}
  	return functions.get(name.toLowerCase());
    }

    public void registerDefaultFunctions() {
	registerFunction("sum", new SUMFunction());
	registerFunction("count", new COUNTFunction());
	registerFunction("avg", new AVGFunction());
	registerFunction("max", new MAXFunction());
	registerFunction("min", new MINFunction());
    }
    
    protected String normalize(String name)  {
	if (name == null || name.isEmpty()) {
	    return null;
	}
	return name.isEmpty() ? null : name;
    }
    
    public Object calculateValue(Scope scope, String variable, String aggregation, Object value) {
   	
   	try {
   	    
	    aggregation = normalize(aggregation);
	    if (aggregation == null) {
		return value;
	    }

	    AggregationFunction function = getFunction(aggregation);
	    if (function == null) {
		throw new DSEvaluateException("Aggregation functon '" + aggregation + "' not found");
	    }

	    AggregationValue aggregationValue = scope.getVariableAggregationValue(variable);
	    if (aggregationValue == null) {
		aggregationValue = new AggregationValue();
		scope.setVariableAggregationValue(variable, aggregationValue);
	    }
   	    
   	    return function.calculate(aggregationValue, value);
   	} catch (DSEvaluateException e) {
   	    // TODO: safe value
   	    return null;
   	}
   	
    }
    
    ////
    
    private static Integer getCastInteger(Object value) {
	return getCastInteger(value, null);
    }

    private static Integer getCastInteger(Object value, Integer def) {
	return value == null || !(value instanceof Number) ? def : ((Number) value).intValue();

    }

    private static Double getCastDouble(Object value) {
	return getCastDouble(value, null);
    }

    private static Double getCastDouble(Object value, Double def) {
	return value == null || !(value instanceof Number) ? def: ((Number) value).doubleValue();
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    // SUM
    public static class SUMFunction implements AggregationFunction {

	@Override
	public Object calculate(AggregationValue aggregationValue, Object value) throws DSEvaluateException {
   	    Double sumValue = getCastDouble(aggregationValue.getSumValue(), 0.0);
   	    Double currValue =  getCastDouble(value, 0.0);
   	    
   	    sumValue = sumValue + currValue;
   	    aggregationValue.setSumValue(sumValue);
   	    
   	    return sumValue;	    
	}
	
    }
    
    
    // COUNT
    public static class COUNTFunction implements AggregationFunction {

	@Override
	public Object calculate(AggregationValue aggregationValue, Object value) throws DSEvaluateException {
   	    Integer countValue = getCastInteger(aggregationValue.getCountValue(), 0);
   	    //if (value != null) {
   		countValue = countValue + 1;
   	    //}
   	    aggregationValue.setCountValue(countValue);
   	    
   	    return countValue;
	}
	
    }    

    
    // AVG
    public static class AVGFunction implements AggregationFunction {

	@Override
	public Object calculate(AggregationValue aggregationValue, Object value) throws DSEvaluateException {
   	    Integer countValue = getCastInteger(aggregationValue.getCountValue(), 0);
   	    if (value != null) {
   		countValue = countValue + 1;
   	    }
   	    aggregationValue.setCountValue(countValue);

   	    Double sumValue = getCastDouble(aggregationValue.getSumValue(), 0.0);
   	    Double currValue =  getCastDouble(value, 0.0);
   	    
   	    sumValue = sumValue + currValue;
   	    aggregationValue.setSumValue(sumValue);
   	    
   	    if (countValue == 0) {
   		return new Double(0);
   	    }
   	    
   	    return sumValue / countValue;
	}
	
    }

    
    // MAX
    public static class MAXFunction implements AggregationFunction {

	@Override
	public Object calculate(AggregationValue aggregationValue, Object value) throws DSEvaluateException {
   	    Object oldValue = aggregationValue.getOldValue();
   	    Object newValue = value;
   	    Integer compareValue = valueComparator.compareValue(oldValue, newValue);
   	    if (compareValue != null) {
   		if (compareValue < 0) {
   		    aggregationValue.setOldValue(newValue);
   		}
   	    }
   	    return aggregationValue.getOldValue();
	}
	
    }

    // MIN
    public static class MINFunction implements AggregationFunction {

	@Override
	public Object calculate(AggregationValue aggregationValue, Object value) throws DSEvaluateException {
   	    Object oldValue = aggregationValue.getOldValue();
   	    Object newValue = value;
   	    Integer compareValue = valueComparator.compareValue(oldValue, newValue);
   	    if (compareValue != null) {
   		if (compareValue > 0) {
   		    aggregationValue.setOldValue(newValue);
   		}
   	    }
   	    return aggregationValue.getOldValue();
	}
	
    }
    
}
