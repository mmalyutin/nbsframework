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

    /**
     * Margin
     */
    private Margin margin;

    /**
     * Border
     */
    private Border border;
    
    
    
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
        return position == null ? 0 : position.getX();
    }

    public void setX(int x) {
        getPosition().setX(x);
    }

    public void setX(Integer x) {
        getPosition().setX(x);
    }
    
    public boolean hasX() {
	return hasPosition() && getPosition().hasX();
    }

    public int getY() {
        return position == null ? 0 : position.getY();
    }

    public void setY(int y) {
        getPosition().setY(y);
    }

    public void setY(Integer y) {
        getPosition().setY(y);
    }

    public boolean hasY() {
	return hasPosition() && getPosition().hasY();
    }
    
    public int getWidth() {
        return size == null ? 0 : size.getWidth();
    }

    public void setWidth(int width) {
        getSize().setWidth(width);
    }

    public void setWidth(Integer width) {
        getSize().setWidth(width);
    }

    public boolean hasWidth() {
  	return hasSize() && getSize().hasWidth();
    }
    
    public int getHeight() {
        return size == null ? 0: size.getHeight();
    }

    public void setHeight(int height) {
        getSize().setHeight(height);
    }

    public void setHeight(Integer height) {
        getSize().setHeight(height);
    }

    public boolean hasHeight() {
	return hasSize() && getSize().hasHeight();
    }    
    
    public Point getPosition() {
	if (position == null) {
	    position = new Point();
	}
	return position;
    }

    public boolean hasPosition() {
	return position != null;
    }

    public boolean isEmptyPosition() {
	return position == null || position.isEmpty();
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

    public boolean hasSize() {
	return size != null;
    }

    public boolean isEmptySize() {
	return size == null || size.isEmpty();
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

    public boolean hasBackground() {
        return background != null;
    }
    
    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    public boolean hasForeground() {
        return foreground != null;
    }
    
    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public boolean hasFont() {
        return font != null;
    }
    
    public Margin getMargin() {
	if (margin == null) {
	    margin = new Margin();
	}
	return margin;
    }

    public void setMargin(Margin margin) {
	this.margin = margin;
    }

    public boolean hasMargin() {
	return margin != null;
    }

    public boolean isEmptyMargin() {
	return margin == null || margin.isEmpty();
    }    

    public Border getBorder() {
	if (border == null) {
	    border = new Border();
	}
        return border;
    }

    public void setBorder(Border border) {
        this.border = border;
    }

    public boolean hasBorder() {
	return border != null;
    }

    public boolean isEmptyBorder() {
	return border == null || border.isEmpty();
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

    @Override
    public String toString() {
	return "Element[" + toValuesString() + "]";
    }

    public String toValuesString() {
	return "visible=" + visible 
		+ ", background=" + background
		+ ", foreground=" + foreground 
		+ ", font=" + font 
		+ ", margin=" + margin
		+ ", border=" + border		
		+ ", x=" + (position == null ? null : getX()) 
		+ ", y=" + (position == null ? null : getY())
		+ ", width=" + (size == null ? null : getWidth())
		+ ", height=" + (size == null ? null : getHeight()); 
    }


    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((background == null) ? 0 : background.hashCode());
	result = prime * result + ((border == null) ? 0 : border.hashCode());
	result = prime * result + ((font == null) ? 0 : font.hashCode());
	result = prime * result
		+ ((foreground == null) ? 0 : foreground.hashCode());
	result = prime * result + ((margin == null) ? 0 : margin.hashCode());
	result = prime * result
		+ ((position == null) ? 0 : position.hashCode());
	result = prime * result + ((size == null) ? 0 : size.hashCode());
	result = prime * result + (visible ? 1231 : 1237);
	return result;
    }


    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Element other = (Element) obj;
	if (background == null) {
	    if (other.background != null)
		return false;
	} else if (!background.equals(other.background))
	    return false;
	if (border == null) {
	    if (other.border != null)
		return false;
	} else if (!border.equals(other.border))
	    return false;
	if (font == null) {
	    if (other.font != null)
		return false;
	} else if (!font.equals(other.font))
	    return false;
	if (foreground == null) {
	    if (other.foreground != null)
		return false;
	} else if (!foreground.equals(other.foreground))
	    return false;
	if (margin == null) {
	    if (other.margin != null)
		return false;
	} else if (!margin.equals(other.margin))
	    return false;
	if (position == null) {
	    if (other.position != null)
		return false;
	} else if (!position.equals(other.position))
	    return false;
	if (size == null) {
	    if (other.size != null)
		return false;
	} else if (!size.equals(other.size))
	    return false;
	if (visible != other.visible)
	    return false;
	return true;
    }



    
    
}
