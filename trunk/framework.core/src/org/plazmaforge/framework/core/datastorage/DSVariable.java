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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.data.ComplexLocalizedIdentifier;
import org.plazmaforge.framework.core.data.HasDataType;

/**
 * @author ohapon
 *
 */
public class DSVariable extends ComplexLocalizedIdentifier implements HasDataType, HasExpression, HasExpressionBuilder, Serializable {

    private static final long serialVersionUID = 2809735916585458477L;
    
    
    private String dataType;
    
    private DSExpression expression;
    
    private DSExpression initExpression;

    // Template/Page/Group
    private String resetType;
    
    // Group/Template name
    private String resetName;
    
    // COUNT, SUM, AVG
    private String aggregation;
    
    public DSVariable() {
    }
    
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public DSExpression getExpression() {
        return expression;
    }

    public void setExpression(DSExpression expression) {
        this.expression = expression;
    }

    public String getExpressionText() {
	return expression == null ? null : expression.getText();
    }
    
    public void setExpressionText(String expressionText) {
	if (expression == null) {
	    expression = new DSExpression();
	}
	expression.setText(expressionText);
    }
    
    public boolean hasExpressionText() {
   	return DSExpression.hasText(expression);
    }    
    
    public DSExpression getInitExpression() {
        return initExpression;
    }

    public void setInitExpression(DSExpression initExpression) {
        this.initExpression = initExpression;
    }

    public String getInitExpressionText() {
	return initExpression == null ? null : initExpression.getText();
    }
    
    public void setInitExpressionText(String expressionText) {
	if (initExpression == null) {
	    initExpression = new DSExpression();
	}
	initExpression.setText(expressionText);
    }    
    
    public boolean hasInitExpressionText() {
   	return DSExpression.hasText(initExpression);
    }    
    
    public String getResetType() {
        return resetType;
    }

    public void setResetType(String resetType) {
        this.resetType = resetType;
    }

    public String getResetName() {
        return resetName;
    }

    public void setResetName(String resetName) {
        this.resetName = resetName;
    }

    public String getAggregation() {
        return aggregation;
    }

    public void setAggregation(String aggregation) {
        this.aggregation = aggregation;
    }

    @Override
    public List<DSExpression> buildExpressions() {
	List<DSExpression> expressions = new ArrayList<DSExpression>();
	populateExpressions(expressions);
	return expressions;
    }

    @Override
    public void populateExpressions(List<DSExpression> expressions) {
	if (!DSExpression.isEmpty(expression)) {
	    expressions.add(expression);
	}
	if (!DSExpression.isEmpty(initExpression)) {
	    expressions.add(initExpression);
	}
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result
		+ ((aggregation == null) ? 0 : aggregation.hashCode());
	result = prime * result
		+ ((dataType == null) ? 0 : dataType.hashCode());
	result = prime * result
		+ ((expression == null) ? 0 : expression.hashCode());
	result = prime * result
		+ ((initExpression == null) ? 0 : initExpression.hashCode());
	result = prime * result
		+ ((resetName == null) ? 0 : resetName.hashCode());
	result = prime * result
		+ ((resetType == null) ? 0 : resetType.hashCode());
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
	DSVariable other = (DSVariable) obj;
	if (aggregation == null) {
	    if (other.aggregation != null)
		return false;
	} else if (!aggregation.equals(other.aggregation))
	    return false;
	if (dataType == null) {
	    if (other.dataType != null)
		return false;
	} else if (!dataType.equals(other.dataType))
	    return false;
	if (expression == null) {
	    if (other.expression != null)
		return false;
	} else if (!expression.equals(other.expression))
	    return false;
	if (initExpression == null) {
	    if (other.initExpression != null)
		return false;
	} else if (!initExpression.equals(other.initExpression))
	    return false;
	if (resetName == null) {
	    if (other.resetName != null)
		return false;
	} else if (!resetName.equals(other.resetName))
	    return false;
	if (resetType == null) {
	    if (other.resetType != null)
		return false;
	} else if (!resetType.equals(other.resetType))
	    return false;
	return true;
    }

    public String toString() {
	return "DSVariable[name=" + getName() 
		+ ", dataType=" + dataType 
		+ ", resetType=" + resetType 
		+ ", resetName=" + resetName
		+ ", aggregation=" + aggregation
		+ ", expression=" + expression
		+ ", initExpression=" + initExpression
		+ "]";
    }
}
