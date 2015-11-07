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

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.tree.Tree;
import org.plazmaforge.framework.uwt.widget.tree.TreeItem;

import com.extjs.gxt.ui.client.data.ModelData;

@Deprecated
public class GXTTreeItemAdapter extends GXTWidgetAdapter {

    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	
	/*
	TreeItem item  = (TreeItem) element;
	TreeItem parentItem = item.getParentItem();
	Tree tree = item.getTree();
	
	GXTTreeItemModel parentModel = null;
	
	if (parentItem != null) {
	    parentModel = (GXTTreeItemModel) parentItem.getDelegate();
	}
	
	// Get GXT Tree
	com.extjs.gxt.ui.client.widget.treegrid.TreeGrid<ModelData> xTree = (com.extjs.gxt.ui.client.widget.treegrid.TreeGrid<ModelData>) tree.getDelegate();
	
	// Get GXT TreeStore
	com.extjs.gxt.ui.client.store.TreeStore<ModelData> store = xTree.getTreeStore();
	
	// Create node
	GXTTreeItemModel model = new GXTTreeItemModel(item);
	if (parentModel == null) {
	    store.add(model, false);
	} else {
	    store.insert(parentModel, model, parentModel.getChildCount(), false);
	}
	
	xTree.reconfigure(store, xTree.getColumnModel());
	return model;
	*/
	return null;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	// Do nothing because we use native element
    }


}
