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

package org.plazmaforge.framework.report.model.base;

import java.io.Serializable;

import org.plazmaforge.framework.uwt.graphics.Color;

/**
 * 
 * @author ohapon
 *
 */
public class Pen implements Serializable {

    private static final long serialVersionUID = -5276236458876261037L;
    
    public static final Pen NONE = new NonePen();
    
    public static final byte LINE_STYLE_SOLID = 0;

    public static final byte LINE_STYLE_DASHED = 1;

    public static final byte LINE_STYLE_DOTTED = 2;

    public static final byte LINE_STYLE_DOUBLE = 3;
    

    private float lineWidth;
    
    private byte lineStyle;
    
    private Color lineColor;
    

    public Pen() {
	super();
    }

    public Pen(float lineWidth, byte lineStyle, Color lineColor) {
	super();
	this.lineWidth = normalizeWidth(lineWidth);
	this.lineStyle = normalizeStyle(lineStyle);
	this.lineColor = lineColor;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = normalizeWidth(lineWidth);
    }

    public byte getLineStyle() {
        return lineStyle;
    }

    public void setLineStyle(byte lineStyle) {
        this.lineStyle = normalizeStyle(lineStyle);
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }
    
    public boolean isEmpty() {
	return lineWidth == 0 && lineStyle == 0 && lineColor == null;
    }

    public boolean hasValue() {
	if (this == Pen.NONE) {
	    return true;
	}
	return !isEmpty();
    }
    
    public boolean isIncomplete() {
	return lineWidth == 0 || lineStyle == 0 || lineColor == null;
    }
    
    public boolean isComplete() {
	return !isIncomplete();
    }
    
    private float normalizeWidth(float width) {
  	return width < 0 ? 0 : roundWidth(width);
    }

    private float roundWidth(float width) {
	// TODO: Temp solution. Fix after float 'Size' and 'Point'
	return Math.round(width);
    }
    
    private byte normalizeStyle(byte style) {
  	return (style < LINE_STYLE_SOLID  || style > LINE_STYLE_DOUBLE) ? LINE_STYLE_SOLID : style;
    }

    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((lineColor == null) ? 0 : lineColor.hashCode());
	result = prime * result + lineStyle;
	result = prime * result + (int) lineWidth;
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
	Pen other = (Pen) obj;
	if (lineColor == null) {
	    if (other.lineColor != null)
		return false;
	} else if (!lineColor.equals(other.lineColor))
	    return false;
	if (lineStyle != other.lineStyle)
	    return false;
	if (lineWidth != other.lineWidth)
	    return false;
	return true;
    }
    
    @Override
    public String toString() {
	return "Pen[" + lineWidth + ", " + lineStyle + ", "  + lineColor + "]";
    }
    
    @Override
    public Pen clone() {
	return new Pen(lineWidth, lineStyle, lineColor);
    }
    
    private static final class NonePen extends Pen {

	private static final long serialVersionUID = 4901311596839022327L;
	
	private NonePen() {
	    super();
	}

	@Override
	public float getLineWidth() {
	    return 0;
	}

	@Override
	public void setLineWidth(float lineWidth) {}

	@Override
	public byte getLineStyle() {
	    return 0;
	}

	@Override
	public void setLineStyle(byte lineStyle) {}

	@Override
	public Color getLineColor() {
	    return null;
	}

	@Override
	public void setLineColor(Color lineColor) {}

	@Override
	public boolean isEmpty() {
	    return true;
	}
	
	@Override
	public Pen clone() {
	    return this;
	}	

	@Override
	public String toString() {
	    return "Pen[none]";
	}
    }
}
