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

package org.plazmaforge.framework.uwt.gxt.widget;

import org.plazmaforge.framework.uwt.gxt.data.Model;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.Grid;

/**
 * 
 * @author ohapon
 *
 * @param <N>
 */
public class XColumnConfig<N> extends ColumnConfig<Model, N> {

    private Grid<Model> grid;

  
   
    public Grid<Model> getGrid() {
        return grid;
    }

    public XColumnConfig(ValueProvider<Model, N> valueProvider, int width, SafeHtml header) {
	super(valueProvider, width, header);
	init();
    }

    public XColumnConfig(ValueProvider<Model, N> valueProvider, int width, String header) {
	super(valueProvider, width, header);
	init();
    }

    public XColumnConfig(ValueProvider<Model, N> valueProvider, int width) {
	super(valueProvider, width);
	init();
    }

    public XColumnConfig(ValueProvider<Model, N> valueProvider) {
	super(valueProvider);
	init();
    }

    private void init() {
	setMenuDisabled(true);
	setSortable(false);
    }

    public void setGrid(Grid<Model> grid) {
	this.grid = grid;
    }

    
    
    
    
}
