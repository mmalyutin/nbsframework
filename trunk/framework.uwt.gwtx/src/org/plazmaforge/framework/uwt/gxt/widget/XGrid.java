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

import org.plazmaforge.framework.uwt.gxt.data.ModelData;
import org.plazmaforge.framework.uwt.widget.table.Table;

import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;

/**
 * 
 * @author ohapon
 *
 */
public class XGrid extends Grid<ModelData> /*EditorGrid<M>*/ {

    public XGrid(ListStore<ModelData> store, ColumnModel<ModelData> cm) {
	super(store, cm);

	
	// Set default SelectionModel: SINGLE ROW SELECTION
	GridSelectionModel<ModelData> selectionModel = new GridSelectionModel<ModelData>();
	
	//DISABLE:MIGRATION
	//selectionModel.setSelectionMode(Style.SelectionMode.SINGLE);
	
	setSelectionModel(selectionModel);

	setView(new XGridView());
    }
    
    // ONLY FOR UWT
    public void setTable(Table table) {
	((XGridView) getView()).setTable(table);
    }

}
