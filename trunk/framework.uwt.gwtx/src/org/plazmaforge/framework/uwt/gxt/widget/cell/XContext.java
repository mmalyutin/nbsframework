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
package org.plazmaforge.framework.uwt.gxt.widget.cell;

import org.plazmaforge.framework.uwt.gxt.data.Model;

import com.google.gwt.cell.client.Cell;

/**
 * 
 * @author ohapon
 *
 */
public class XContext extends Cell.Context {

    private Model model;
    
    private String cellStyle;
    
    public XContext(int index, int column, Object key, int subindex) {
	super(index, column, key, subindex);
    }

    public XContext(int index, int column, Object key) {
	super(index, column, key);
    }

    public XContext(int index, int column, Object key, int subindex, Model model) {
	super(index, column, key, subindex);
	this.model = model;
    }

    public XContext(int index, int column, Object key, Model model) {
	super(index, column, key);
	this.model = model;
    }

    public Model getModel() {
	return model;
    }

    public String getCellStyle() {
        return cellStyle;
    }

    public void setCellStyle(String cellStyle) {
        this.cellStyle = cellStyle;
    }

    
}
