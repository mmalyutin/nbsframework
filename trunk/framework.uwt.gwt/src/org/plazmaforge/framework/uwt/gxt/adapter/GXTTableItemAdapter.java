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

package org.plazmaforge.framework.uwt.gxt.adapter;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.table.TableItem;

import com.extjs.gxt.ui.client.data.ModelData;

@Deprecated
public class GXTTableItemAdapter extends GXTWidgetAdapter {

    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	// DISABLE
	/*
	TableItem row = (TableItem) element;
	com.extjs.gxt.ui.client.widget.grid.Grid<ModelData> xParent = (com.extjs.gxt.ui.client.widget.grid.Grid<ModelData>) parent.getDelegate();
	com.extjs.gxt.ui.client.store.ListStore<ModelData> store = xParent.getStore();
	List<ModelData> list = new ArrayList<ModelData>(1); // TODO: Bad solution
	GXTTableItemModel model = new GXTTableItemModel(row);
	list.add(model);
	store.add(list);
	xParent.reconfigure(store, xParent.getColumnModel());
	return row;
	*/
	return null;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	// Do nothing because we use native element
    }

}
