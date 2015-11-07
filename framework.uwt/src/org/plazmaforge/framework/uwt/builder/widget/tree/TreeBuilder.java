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

package org.plazmaforge.framework.uwt.builder.widget.tree;

import java.util.List;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.builder.widget.ViewerBuilder;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.Viewer;
import org.plazmaforge.framework.uwt.widget.tree.Tree;
import org.plazmaforge.framework.uwt.widget.tree.TreeColumn;
import org.plazmaforge.framework.uwt.widget.tree.TreeItem;
import org.plazmaforge.framework.uwt.widget.tree.TreeItemDataProvider;
import org.plazmaforge.framework.uwt.widget.tree.TreeItemPropertyProvider;

public class TreeBuilder extends ViewerBuilder {

    @Override
    protected Viewer<?> createViewer() {
	return new Tree();
    }
    
    @Override
    protected void populate(IData data, UIObject element) {
	if (data == null) {
	    return;
	}
	super.populate(data, element);
	
	Tree tree = (Tree) element;
	
	Boolean linesVisible = getBoolean(data, Tree.PROPERTY_LINES_VISIBLE);
	if (linesVisible != null) {
	    tree.setLinesVisible(linesVisible);
	}
	
	Boolean headerVisible = getBoolean(data, Tree.PROPERTY_HEADER_VISIBLE);
	if (headerVisible != null) {
	    tree.setHeaderVisible(headerVisible);
	}

	String displayProperty = getString(data, Tree.PROPERTY_DISPLAY_PROPERTY);
	if (displayProperty != null) {
	    tree.setDisplayProperty(displayProperty);
	}
	
	String displayFormat = getString(data, Tree.PROPERTY_DISPLAY_FORMAT);
	if (displayFormat != null) {
	    tree.setDisplayFormat(displayFormat);
	}
	
	////
	
	String leafIconPath = getImagePath(data, Tree.PROPERTY_LEAF_ICON);
	if  (leafIconPath != null) {
	    tree.setLeafIcon(new Image(leafIconPath));
	}

	String nodeIconPath = getImagePath(data, Tree.PROPERTY_NODE_ICON);
	if  (nodeIconPath != null) {
	    tree.setNodeIcon(new Image(nodeIconPath));
	}
	
	String openIconPath = getImagePath(data, Tree.PROPERTY_OPEN_ICON);
	if  (openIconPath != null) {
	    tree.setOpenIcon(new Image(openIconPath));
	}

	String closeIconPath = getImagePath(data, Tree.PROPERTY_CLOSE_ICON);
	if  (closeIconPath != null) {
	    tree.setCloseIcon(new Image(closeIconPath));
	}
	
	//TODO:
	// - LabelProvider
	
	//populateColumns(data, element);
	populateItems(data, element);
    }
    
    protected void populateColumns(IData data, UIObject element) {
	Tree<?> tree = (Tree<?>) element;
	
	List<IData> columns = getChildrenOfNode(data, Tree.PROPERTY_COLUMNS);
	if (columns == null || columns.isEmpty()) {
	    return;
	}
	TreeColumn column = null;
	for (IData columnData: columns ) {
	    column = createTreeColumn(columnData);
	    tree.addColumn(column);
	}
	
    }

    
    protected TreeColumn createTreeColumn(IData data) {
	if (data == null) {
	    return null;
	}
	
	TreeColumn column = new TreeColumn();
	populateColumn(data, column);
	return column;
    }
    
    
    
    protected void populateItems(IData data, UIObject element) {
	if (data == null) {
	    return;
	}
	Tree tree = (Tree) element;
	int columnCount = tree.getColumnCount();
	int length = columnCount == 0 ? 1 : columnCount;
	
	Class[] types = new Class[length];
	
	for (int i = 0; i < length; i++) {
	    types[i] = String.class; // TODO Use column data type
	}
	List<TreeItem> items = getTreeItems(types, data);
	if (items == null || items.isEmpty()) {
	    return;
	}
	
	// Auto setting PropertyProvider
	if (tree.getPropertyProvider() == null) {
	    tree.setPropertyProvider(new TreeItemPropertyProvider());
	    
	    if (columnCount > 0) {
		for (int i = 0; i < columnCount; i++) {
		    TreeColumn column = tree.getColumn(i);
		    if (column.getProperty() == null) {
			column.setProperty("" + i);
		    }
		}
	    } else {
		if (tree.getDisplayProperty() == null) {
		    tree.setDisplayProperty(TreeItemPropertyProvider.PROPERTY_VALUE);
		}
	    }
	}

	
	// Auto setting DataProvider
	tree.setDataProvider(new TreeItemDataProvider());
	
	tree.setItems(items);
    }
    


}
