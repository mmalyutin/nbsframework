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

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.datastorage.HasExpressionBuilder;
import org.plazmaforge.framework.report.model.base.Element;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;

/**
 * @author ohapon
 *
 */
public class Row implements HasExpressionBuilder {

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
    
    
    private int height;
    
    private List<Cell> cells;

    public Row() {
    }
    
    public Row(int height) {
	this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Cell> getCells() {
	if (cells == null) {
	    cells = new ArrayList<Cell>();
	}
        return cells;
    }
    
    public Cell getCell(int index) {
	return getCells().get(index);
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
    
    public void addCell(Cell cell) {
	getCells().add(cell);
    }

    public void removeCell(Cell cell) {
	getCells().remove(cell);
    }
    
    public boolean hasCells() {
	return cells != null && !cells.isEmpty();
    }

    public int getCellCount() {
	return cells == null ? 0 : cells.size();
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

    public String toString( ){
	StringBuffer buf = new StringBuffer();
	buf.append("Row[height=" + height);
	List<Cell> cells = getCells();
	buf.append(", cells=[");
	boolean first = true;
	for (Cell cell: cells) {
	    if (!first) {
		buf.append(", ");
	    }
	    first = false;
	    buf.append(cell.toString());
	}
	buf.append("]");
	return buf.toString();
    }
    
    @Override
    public List<DSExpression> buildExpressions() {
	List<DSExpression> expressions = new ArrayList<DSExpression>();
	populateExpressions(expressions);
	return expressions;
    }

    @Override
    public void populateExpressions(List<DSExpression> expressions) {
	if (hasCells()) {
	    for (Cell row : getCells()) {
		Element.populateExpressions(expressions, row);
	    }
	}
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((background == null) ? 0 : background.hashCode());
	result = prime * result + ((cells == null) ? 0 : cells.hashCode());
	result = prime * result + ((font == null) ? 0 : font.hashCode());
	result = prime * result
		+ ((foreground == null) ? 0 : foreground.hashCode());
	result = prime * result + height;
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
	Row other = (Row) obj;
	if (background == null) {
	    if (other.background != null)
		return false;
	} else if (!background.equals(other.background))
	    return false;
	if (cells == null) {
	    if (other.cells != null)
		return false;
	} else if (!cells.equals(other.cells))
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
	if (height != other.height)
	    return false;
	return true;
    }
    
}
