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
package org.plazmaforge.framework.core.datastorage;

/**
 * @author ohapon
 *
 */
public class DSExpressionFilter extends DSFilter implements HasExpression {
    
    private static final long serialVersionUID = 7097356662636840016L;
    
    private DSExpression expression;

    public DSExpressionFilter() {
	super();
    }

    public DSExpressionFilter(DSExpression expression) {
	super();
	this.expression = expression;
    }

    public DSExpression getExpression() {
	if (expression == null) {
	    expression = new DSExpression();
	}
        return expression;
    }

    public void setExpression(DSExpression expression) {
        this.expression = expression;
    }
    
    public String getExpressionText() {
	return getExpression().getText();
    }
    
    public void setExpressionText(String text) {
	getExpression().setText(text);
    }

}
