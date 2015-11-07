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

/**
 * 
 */
package org.plazmaforge.framework.report.fill;

import java.text.DecimalFormat;

import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.type.TypeUtils;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.fill.process.ReportContext;
import org.plazmaforge.framework.report.fill.script.ExpressionEvaluator;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.design.Band;

/**
 * @author ohapon
 *
 */
public class AbstractFiller {

    
    protected boolean evaluatePrintExpression(ReportContext context, int evaluation, Band band) {
	if (band == null) {
	    return false;
	}
	// TODO: Evaluate print expression
	return true;
    }
    
    protected Object evaluateExpression(ReportContext context, int evaluation, DSExpression expression) {
	if (expression == null || expression.isEmpty()) {
	    return null;
	}
	ExpressionEvaluator evaluator = context.getExpressionEvaluator();
	if (evaluator == null) {
	    return null;
	}
	try {
	    return evaluator.evaluate(context, evaluation, expression);
	} catch (RTException e) {
	    // TODO
	    return null;
	}
	
    }
    
    protected String formatCellValue(Object value, Cell cell) {
	String text = formatValue(value, cell.getDataType(), cell.getFormat());
	return text;
    }
    
    protected String formatValue(Object value, String dataType, String format) {
	if (value == null) {
	    return "";
	}
	
	if (TypeUtils.isLikeDecimalType(dataType)) {
	    if (format == null) {
		format = "#.0";
	    }
	    DecimalFormat decimalFormat = new DecimalFormat(format);
	    return decimalFormat.format(value);
	}
	return value.toString();
    }
    

}
