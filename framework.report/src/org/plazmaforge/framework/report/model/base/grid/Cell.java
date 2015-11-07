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
package org.plazmaforge.framework.report.model.base.grid;

import java.util.List;

import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.datastorage.HasExpression;
import org.plazmaforge.framework.report.model.base.Container;

/**
 * @author ohapon
 *
 */
public class Cell extends Container implements HasExpression {

    private static final long serialVersionUID = 3370115024800401060L;
    

    private int colspan;
    
    private int rowspan;

    private String dataType;
    
    private Object value;
    
    private DSExpression expression;

    private String format;
    
    public Cell() {
	this.colspan = 1;
	this.rowspan = 1;
    }

    public int getColspan() {
        return colspan;
    }

    public void setColspan(int colspan) {
        this.colspan = colspan < 1 ? 1 : colspan;
    }

    public int getRowspan() {
        return rowspan;
    }

    public void setRowspan(int rowspan) {
        this.rowspan = rowspan < 1 ? 1 : rowspan;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
        this.expression = null;
    }

    public DSExpression getExpression() {
        return expression;
    }

    public void setExpression(DSExpression expression) {
        this.expression = expression;
        this.value = null;
    }
    
    public String getExpressionText() {
	return expression == null ? null : expression.getText();
    }
    
    public void setExpressionText(String expressionText) {
	if (expression == null){
	    expression = new DSExpression();
	    value = null;
	}
	expression.setText(expressionText);
    }

    public void setExpression(String expressionText) {
	setExpressionText(expressionText);
    }
	
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String toString() {
	StringBuilder b = new StringBuilder();
	b.append("Cell=[");
	boolean flag = false;
	if (colspan > 1) {
	    b.append("colspan=" + colspan);
	    flag = true;
	}
	if (rowspan > 1) {
	    if (flag) {
		b.append(", ");
	    }
	    b.append("rowspan=" + rowspan);
	    flag = true;
	}
	if (dataType != null) {
	    if (flag) {
		b.append(", ");
	    }
	    b.append("dataType=" + dataType);
	    flag = true;
	}
	if (value != null) {
	    if (flag) {
		b.append(", ");
	    }
	    b.append("value=" + value);
	    flag = true;
	}
	if  (expression != null) {
	    if (flag) {
		b.append(", ");
	    }
	    b.append("expression=" + expression);
	    flag = true;
	}
	b.append("]");
	return b.toString();
    }
    

    @Override
    public void populateExpressions(List<DSExpression> expressions) {
	super.populateExpressions(expressions);
	if (!DSExpression.isEmpty(expression)) {
	    expressions.add(expression);
	}
    }
    
}
