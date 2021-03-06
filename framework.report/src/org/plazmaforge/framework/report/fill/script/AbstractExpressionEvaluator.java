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

package org.plazmaforge.framework.report.fill.script;

import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.datastorage.data.Scope;
import org.plazmaforge.framework.core.exception.DSEvaluateException;

/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractExpressionEvaluator implements ExpressionEvaluator {

    private Scope scope;

    protected void setScope(Scope scope) {
        this.scope = scope;
    }

    protected Scope getScope() {
        return scope;
    }
    
    /**
     * Get value from scope (PARAMETER, FIELD, VARIABLE...)
     * 
     * @param valueContext
     * @param valueName
     * @param evaluation
     * @return
     */
    protected Object getScopeValue(String valueContext, String valueName, int evaluation) {
 	return getScope().getScopeValue(valueContext, valueName, evaluation);
    }
    
    @Override
    public Object evaluate(DSExpression expression) throws DSEvaluateException {
	// Evaluate expression with default evaluation 
	return evaluate(DSExpression.EVALUATION_DEFAULT, expression);
    }

    protected Object evaluateAtomExpression(int evaluation, String expression) {
	
   	if (expression == null) {
   	    return null;
   	}
   	expression = expression.trim();
   	if (expression.isEmpty()) {
   	    return null;
   	}
   	
   	// PARAMETER
   	if (matchAtomName(expression, "$P{", "}")) {
   	    String parameterName = getAtomName(expression, "$P{", "}");
   	    if (parameterName == null) {
   		return null;
   	    }
   	    return getScopeValue(Scope.PARAMETER, parameterName, evaluation);
   	}
   	
   	// FIELD
   	if (matchAtomName(expression, "$F{", "}")) {
   	    String fieldName = getAtomName(expression, "$F{", "}");
   	    if (/*context.getMainData() == null || */fieldName == null) {
   		return null;
   	    }
   	    return getScopeValue(Scope.FIELD, fieldName, evaluation);
   	}
   	
   	// VARIABLE
   	if (matchAtomName(expression, "$V{", "}")) {
   	    String variableName = getAtomName(expression, "$V{", "}");
   	    if (variableName == null) {
   		return null;
   	    }
   	    return getScopeValue(Scope.VARIABLE, variableName, evaluation);
   	}
   	
   	return null;
   }
    
    protected boolean matchAtomName(String expression, String prefix, String suffix) {
	if (expression == null || prefix == null || suffix == null) {
	    return false;
	}
	return expression.startsWith(prefix) && expression.endsWith(suffix);
    }
    
    protected String getAtomName(String expression, String prefix, String suffix) {
	if (expression == null || prefix == null || suffix == null) {
	    return null;
	}
	int len = prefix.length() + suffix.length();
	if (expression.length() <= len) {
	    return null;
	}
	return expression.substring(prefix.length(), expression.length() - suffix.length());
    }

    
}
