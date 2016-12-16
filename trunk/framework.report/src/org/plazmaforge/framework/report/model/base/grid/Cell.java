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
import org.plazmaforge.framework.report.model.base.Padding;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;

/**
 * @author ohapon
 *
 */
public class Cell extends Container implements HasExpression {

    private static final long serialVersionUID = 3370115024800401060L;
    
    public static final int DEFAULT_PADDING = 2;

    private int colspan;
    
    private int rowspan;

    private HorizontalAlign horizontalAlign;
    
    private VerticalAlign verticalAlign;
    
    private String dataType;
    
    private Object value;
    
    private DSExpression expression;

    private String format;
    
    
    public Cell() {
	this.colspan = 1;
	this.rowspan = 1;
	setPadding(new Padding(DEFAULT_PADDING));
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

    public HorizontalAlign getHorizontalAlign() {
        return horizontalAlign;
    }

    public void setHorizontalAlign(HorizontalAlign horizontalAlign) {
        this.horizontalAlign = horizontalAlign;
    }

    public VerticalAlign getVerticalAlign() {
        return verticalAlign;
    }

    public void setVerticalAlign(VerticalAlign verticalAlign) {
        this.verticalAlign = verticalAlign;
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
	b.append("Cell[");
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
	if (horizontalAlign != null) {
	    if (flag) {
		b.append(", ");
	    }
	    b.append("horizontalAlign=" + horizontalAlign);
	    flag = true;
	}
	if (verticalAlign != null) {
	    if (flag) {
		b.append(", ");
	    }
	    b.append("verticalAlign=" + verticalAlign);
	    flag = true;
	}
	if (hasBorder()) {
	    if (flag) {
		b.append(", ");
	    }
	    b.append("border=" + getBorder());
	    flag = true;
	}
	if (hasPadding()) {
	    if (flag) {
		b.append(", ");
	    }
	    b.append("padding=" + getPadding());
	    flag = true;
	}
	
	if (hasBackground()) {
	    if (flag) {
		b.append(", ");
	    }
	    b.append("background=" + getBackground());
	    flag = true;
	}	
	if (hasForeground()) {
	    if (flag) {
		b.append(", ");
	    }
	    b.append("foreground=" + getForeground());
	    flag = true;
	}	
	if (hasFont()) {
	    if (flag) {
		b.append(", ");
	    }
	    b.append("font=" + getFont());
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
	if (format != null) {
	    if (flag) {
		b.append(", ");
	    }
	    b.append("format=" + format);
	    flag = true;
	}
	if (expression != null) {
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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + colspan;
	result = prime * result
		+ ((dataType == null) ? 0 : dataType.hashCode());
	result = prime * result
		+ ((expression == null) ? 0 : expression.hashCode());
	result = prime * result + ((format == null) ? 0 : format.hashCode());
	result = prime * result
		+ ((horizontalAlign == null) ? 0 : horizontalAlign.hashCode());
	result = prime * result + rowspan;
	result = prime * result + ((value == null) ? 0 : value.hashCode());
	result = prime * result
		+ ((verticalAlign == null) ? 0 : verticalAlign.hashCode());
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
	Cell other = (Cell) obj;
	if (colspan != other.colspan)
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
	if (format == null) {
	    if (other.format != null)
		return false;
	} else if (!format.equals(other.format))
	    return false;
	if (horizontalAlign != other.horizontalAlign)
	    return false;
	if (rowspan != other.rowspan)
	    return false;
	if (value == null) {
	    if (other.value != null)
		return false;
	} else if (!value.equals(other.value))
	    return false;
	if (verticalAlign != other.verticalAlign)
	    return false;
	return true;
    }

    public static boolean isDefaultCellPadding(Padding padding) {
	if (padding == null) {
	    return false;
	}
	Integer top = padding.getTop();
	Integer right = padding.getRight();
	Integer bottom = padding.getBottom();
	Integer left = padding.getLeft();
	
	if (top == null || right == null || bottom == null || left == null) {
	    return false;
	}
	
	if (top == DEFAULT_PADDING && right == DEFAULT_PADDING && bottom == DEFAULT_PADDING && left == DEFAULT_PADDING) {
	    return true;
	}
	
	return false;
    }
}
