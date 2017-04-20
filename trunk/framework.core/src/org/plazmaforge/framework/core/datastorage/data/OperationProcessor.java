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

package org.plazmaforge.framework.core.datastorage.data;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @author ohapon
 *
 */
public class OperationProcessor {

    private Map<String, OperationEvaluator> evaluators = new HashMap<String, OperationEvaluator>();
    
    public OperationProcessor() {
	super();
    }

    public void registerEvaluator(String name, OperationEvaluator evaluator) {
	evaluators.put(name, evaluator);
    }
    
    public Boolean evaluate(Object leftValue, String operation, Object rightValue) {
	if (operation == null) {
	    operation = "eq";
	}
	OperationEvaluator evaluator = getEvaluator(operation);
	if (evaluator == null) {
	    return null;
	}
	return evaluator.evaluate(leftValue, rightValue);
	
    }
    
    public OperationEvaluator getEvaluator(String name) {
	if (name == null) {
	    return null;
	}
	return evaluators.get(name.toLowerCase());
    }

    public void registerDefaultEvaluators() {
	registerEvaluator("eq", new EQEvaluator());
	registerEvaluator("ne", new NEEvaluator());
    }
    
    ////
    
    public static class EQEvaluator implements OperationEvaluator {

	@Override
	public Boolean evaluate(Object leftValue, Object rightValue) {
	    if (leftValue == null || rightValue == null) {
		return false;
	    }
	    return leftValue.equals(rightValue);
	}
	
    }
    
    public static class NEEvaluator extends EQEvaluator {

	@Override
	public Boolean evaluate(Object leftValue, Object rightValue) {
	    return !super.evaluate(leftValue, rightValue);
	}
	
    }
    
}
