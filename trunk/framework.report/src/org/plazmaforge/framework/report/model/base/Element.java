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
package org.plazmaforge.framework.report.model.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.datastorage.HasExpression;
import org.plazmaforge.framework.core.datastorage.HasExpressionBuilder;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;


/**
 * @author ohapon
 *
 */
public class Element implements Serializable, HasExpressionBuilder {

    private static final long serialVersionUID = 5500760734254047263L;


    
    private Point position;
    
    private Size size;
    
    private boolean visible;


    /**
     * Background color
     */
    private Color background;
    
    /**
     * Foreground color
     */
    private Color foreground;

    /**
     * Font
     */
    private Font font;

    
    public Element() {
	visible = true;
    }

    
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
   

    public int getX() {
        return getPosition().getX();
    }

    public void setX(int x) {
        getPosition().setX(x);
    }

    public void setX(Integer x) {
        getPosition().setX(x);
    }

    public int getY() {
        return getPosition().getY();
    }

    public void setY(int y) {
        getPosition().setY(y);
    }

    public void setY(Integer y) {
        getPosition().setY(y);
    }

    public int getWidth() {
        return getSize().getWidth();
    }

    public void setWidth(int width) {
        getSize().setWidth(width);
    }

    public void setWidth(Integer width) {
        getSize().setWidth(width);
    }

    public int getHeight() {
        return getSize().getHeight();
    }

    public void setHeight(int height) {
        getSize().setHeight(height);
    }

    public void setHeight(Integer height) {
        getSize().setHeight(height);
    }

    public Point getPosition() {
	if (position == null) {
	    position = new Point();
	}
	return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Size getSize() {
	if (size == null) {
	    size = new Size();
	}
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
    
    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }
    
    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }


    public String toStringModel() {
	return toString();
    }
    
    protected int intValue(Integer value) {
	return value == null ? 0 : value;
    }
    
    @Override
    public List<DSExpression> buildExpressions() {
	List<DSExpression> expressions = new ArrayList<DSExpression>();
	populateExpressions(expressions);
	return expressions;
    }

    @Override
    public void populateExpressions(List<DSExpression> expressions) {
	// nothing by default
    }
    
    public static void populateExpressions(List<DSExpression> expressions,  Object element) {
	if (element == null) {
	    return;
	}
	if (element instanceof HasExpressionBuilder) {
	    ((HasExpressionBuilder) element).populateExpressions(expressions);
	    return;
	}
	if (element instanceof HasExpression) {
	    HasExpression hasExpression = (HasExpression) element;
	    DSExpression exprsession = hasExpression.getExpression();
	    if (exprsession == null || exprsession.isEmpty()) {
		return;
	    }
	    expressions.add(exprsession);
	}
    }
}
