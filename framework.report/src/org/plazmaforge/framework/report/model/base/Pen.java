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
    
    public static final byte LINE_STYLE_SOLID = 0;

    public static final byte LINE_STYLE_DASHED = 1;

    public static final byte LINE_STYLE_DOTTED = 2;

    public static final byte LINE_STYLE_DOUBLE = 3;
    

    private int lineWidth;
    
    private byte lineStyle;
    
    private Color lineColor;
    

    public Pen() {
	super();
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public byte getLineStyle() {
        return lineStyle;
    }

    public void setLineStyle(byte lineStyle) {
        this.lineStyle = lineStyle;
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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((lineColor == null) ? 0 : lineColor.hashCode());
	result = prime * result + lineStyle;
	result = prime * result + lineWidth;
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
	return "Pen[lineWidth=" + lineWidth + ", lineStyle=" + lineStyle + ", lineColor="  + lineColor + "]";
    }
}
