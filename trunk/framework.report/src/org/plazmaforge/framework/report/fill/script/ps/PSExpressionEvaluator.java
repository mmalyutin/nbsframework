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

package org.plazmaforge.framework.report.fill.script.ps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.fill.process.ReportContext;
import org.plazmaforge.framework.report.fill.script.AbstractExpressionEvaluator;
import org.plazmaforge.framework.report.fill.script.ExpressionEvaluator;
import org.plazmaforge.framework.report.fill.script.ScriptInfo;

import plazma.EvaluateException;
import plazma.Function;
import plazma.GlobalScope;
import plazma.VariableProvider;
import plazma.ast.LNode;
import plazma.lang.LValue;

/**
 * Plazma Script ExpressionEvaluator
 * 
 * @author ohapon
 *
 */
public class PSExpressionEvaluator extends AbstractExpressionEvaluator implements ExpressionEvaluator {

    private Map<String, Function> globalFunctions;
    
    private Map<String, Function> scriptFunctions;
    
    private GlobalScope globalScope;
    
    private ReportVariableProvider variableProvider;
    
    private plazma.ExpressionEvaluator nativeEvaluator;
    
    
    @Override
    public void init(ScriptInfo scriptInfo) throws RTException {
	try {
	    variableProvider = new ReportVariableProvider();
	    globalFunctions = new HashMap<String, Function>();
	    globalScope = new GlobalScope(variableProvider);

	    // Load Global/Library functions
	    globalFunctions = plazma.ExpressionEvaluator.loadLibraryFunctions();
	    
	    // Load Script functions
	    scriptFunctions = plazma.ExpressionEvaluator.loadFunctions(scriptInfo.getSourceCode());

	} catch (EvaluateException e) {
	    throw new RTException(e);
	}
    }
    
    @Override
    public Object evaluate(ReportContext context, int evaluation, DSExpression expression) throws RTException {
	
	variableProvider.setContext(context);
	variableProvider.setEvaluation(evaluation);
	
	//return evaluate(expression == null ? null : expression.getText());
	return evaluate(expression);
    }

    
    protected Object evaluate(DSExpression expression) throws RTException {
	if (expression == null || scriptFunctions == null) {
	    return null;
	}
	String expressionId = expression.getId();
	if (expressionId == null) {
	    return null;
	}
	String functioName = PSUtils.getExpressionFunctionName(expressionId);
	String functioKey = functioName + "0"; // parameters size = 0
	
	Function originalFunction = scriptFunctions.get(functioKey);
	if (originalFunction == null) {
	    // TODO
	    return null;
	}
	Function function = new Function(originalFunction, globalScope);
	
	List<LNode> params = new ArrayList<LNode>();
	LValue returnValue = function.invoke(params, globalFunctions);
	
	if (returnValue == null || returnValue == LValue.NULL || returnValue == LValue.VOID) {
	    return null;
	}
	return returnValue.getValue();
	
    }
    
    protected Object evaluate(String expression) throws RTException {

	if (expression == null) {
	    return null;
	}
	expression = expression.trim();
	if (expression.isEmpty()) {
	    return null;
	}

	if (nativeEvaluator == null) {
	    nativeEvaluator = new plazma.ExpressionEvaluator(globalFunctions, globalScope);
	}

	try {
	    return nativeEvaluator.evaluate(expression);
	} catch (EvaluateException e) {
	    throw new RTException(e);
	}
    }
    
    class ReportVariableProvider implements VariableProvider {

	private ReportContext context;
	
	private int evaluation;
	
	@Override
	public boolean existsVariable(String var) {
	    // TODO 
	    return true;
	}

	@Override
	public Object getVariableValue(String var) {
	    return evaluateAtomExpression(context, evaluation, var);
	}

	@Override
	public void setVariableValue(String var, Object value) {
	    // TODO
	    // Ignore set value
	}

	public ReportContext getContext() {
	    return context;
	}

	public void setContext(ReportContext context) {
	    this.context = context;
	}

	public int getEvaluation() {
	    return evaluation;
	}

	public void setEvaluation(int evaluation) {
	    this.evaluation = evaluation;
	}
	
    }

    @Override
    public void resetScope() {
	if (globalScope == null) {
	    return;
	}
	globalScope.reset();
    }


    
}
