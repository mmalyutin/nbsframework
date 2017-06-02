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
public class DSExpressionGroup extends DSGroup implements HasExpression {

    private static final long serialVersionUID = 3819255684816035796L;
    
    private DSExpression expression;

    public DSExpressionGroup() {
	super();
    }

    public DSExpressionGroup(DSExpression expression) {
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
    
    public boolean hasExpressionText() {
	return DSExpression.hasText(expression);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result
		+ ((expression == null) ? 0 : expression.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	DSExpressionGroup other = (DSExpressionGroup) obj;
	if (expression == null) {
	    if (other.expression != null)
		return false;
	} else if (!expression.equals(other.expression))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "DSExpressionGroup[name=" + getName() + ", expression=" + expression + "]";
    }

}
