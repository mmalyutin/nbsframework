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

package org.plazmaforge.framework.report.fill.script.ns;

import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.exception.DSEvaluateException;
import org.plazmaforge.framework.report.fill.process.ReportContext;
import org.plazmaforge.framework.report.fill.script.AbstractExpressionEvaluator;
import org.plazmaforge.framework.report.fill.script.ExpressionEvaluator;
import org.plazmaforge.framework.report.fill.script.ScriptInfo;

/**
 * 
 * No Script Expression Evaluator (only atom value)
 * 
 * @author ohapon
 *
 */
public class NSExpressionEvaluator extends AbstractExpressionEvaluator implements ExpressionEvaluator {

    @Override
    public void init(ScriptInfo scriptInfo) {
	
    }

    @Override
    public Object evaluate(ReportContext context, int evaluation, DSExpression expression) throws DSEvaluateException {
	return evaluateAtomExpression(context, evaluation, expression == null ? null : expression.getText());
    }
    
    @Override
    public Object evaluate(DSExpression expression) throws DSEvaluateException {
	return evaluateAtomExpression(null, 0, expression == null ? null : expression.getText()); // TODO: !!!
    }

    @Override
    public void resetScope() {
    }

    
}
