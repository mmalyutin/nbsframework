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
    private String resetValue;
    
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

    public DSExpression getInitExpression() {
        return initExpression;
    }

    public void setInitExpression(DSExpression initExpression) {
        this.initExpression = initExpression;
    }

    public String getResetType() {
        return resetType;
    }

    public void setResetType(String resetType) {
        this.resetType = resetType;
    }

    public String getResetValue() {
        return resetValue;
    }

    public void setResetValue(String resetValue) {
        this.resetValue = resetValue;
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

    
}
