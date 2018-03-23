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

package org.plazmaforge.framework.uwt.jfx.adapter;

import java.util.List;

import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.ValueProvider;
import org.plazmaforge.framework.core.data.provider.TreeProvider;
import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.jfx.adapter.viewer.JFXTreeItem;
import org.plazmaforge.framework.uwt.jfx.widget.cell.XBaseTreeCellFactory;
import org.plazmaforge.framework.uwt.jfx.widget.cell.XPropertyValueFactory;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.tree.Tree;

/**
 * 
 * @author ohapon
 *
 */
public class JFXTreeAdapter extends JFXViewerAdapter {

    
    public Object createDelegate(UIElement parent, UIElement element) {
	Tree tree = (Tree) element;
	javafx.scene.Parent xParent = getContent(parent.getDelegate());
	javafx.scene.control.TreeView<?> xTable = new javafx.scene.control.TreeView();
	
	addChild(xParent, xTable, element);
	return xTable;
    }
    
    protected void updateIcons(Tree<?> tree, javafx.scene.control.TreeView<?>  xTree, String icon) {
	//TODO
    }
    
    protected void updateIcons(Tree<?> tree, javafx.scene.control.TreeView<?> xTree, javafx.scene.image.ImageView xIcon) {
	if (xIcon != null) {
	    JFXTreeItem root = (JFXTreeItem) xTree.getRoot();
	    if (root == null) {
		root = new JFXTreeItem();
		xTree.setRoot(root);
	    }
	    root.setGraphic(xIcon);
	}
    }
    
    @Override
    public void checkDelegate(UIElement element) {
	// clear super method
    }

    protected javafx.scene.control.TreeView<?> asTree(Object delegate) {
	return (javafx.scene.control.TreeView<?>) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	javafx.scene.control.TreeView xTree = asTree(element.getDelegate());
	if (xTree == null) {
	    return;
	}
	Tree tree = (Tree) element;
	if (Tree.PROPERTY_LAYOUT.equals(name)) {
	    // ignore layout
	    return;
	} else if (Tree.PROPERTY_LINES_VISIBLE.equals(name)) {
	    // TODO
	    return;
	} else if (Tree.PROPERTY_HEADER_VISIBLE.equals(name)) {
	    // TODO
	    return;
	} else if (Tree.PROPERTY_DATA_LIST.equals(name)) {
	    List<?> dataList = (List<?>) value;
	    
	    //TreeItem<?> root = toTreeItem(dataList);
	    //root.setExpanded(true);
	    
	    JFXTreeItem root2 = (JFXTreeItem) xTree.getRoot();

	    JFXTreeItem root = new JFXTreeItem();
	    if (root2 != null && root2.getGraphic() != null) {
		root.setGraphic(root2.getGraphic());
	    }
	    root.setTreeProvider((TreeProvider) tree.getDataProvider());
	    JFXTreeItem.populateItem(root, dataList);
	    root.setExpanded(true);
	    xTree.setRoot(root);
	    return;
	    
	    
	} else if (Tree.PROPERTY_DISPLAY_PROPERTY.equals(name)) {
	    
	    PropertyProvider<?> propertyProvider = tree.getPropertyProvider();
	    ValueProvider<?> valueProvider = null; //tree.getValueProvider(); //TODO
	    xTree.setCellFactory(new XBaseTreeCellFactory<Object, Object>(asString(value), propertyProvider, valueProvider));
	    
	    
	    return;
	} else if (Tree.PROPERTY_DISPLAY_FORMAT.equals(name)) {

	    
	    //DISABLE:MIGRATION
	    // Number format
//	    NumberFormat numberFormat = GWTUtils.createNumberFormat(pattern);
//	    if (numberFormat != null ) {
//		xColumn.setNumberFormat(numberFormat);
//		return;
//	    }
//	    
//	    // Date format
//	    DateTimeFormat dateTimeFormat = GWTUtils.createDateTimeFormat(pattern);
//	    if (dateTimeFormat != null ) {
//		xColumn.setDateTimeFormat(dateTimeFormat);
//		return;
//	    }
	    
	    return;
	    
	} else if (Tree.PROPERTY_LABEL_PROVIDER.equals(name)) {
	    
	    //DISABLE:MIGRATION
	    //GXTTreeCellRenderer renderer = getTreeCellRenderer(tree, xTree);
	    //renderer.setLabelProvider((LabelProvider) value);
	    
	    // Update leaf/open/close icon if need
	    //updateIcons(tree, xTree, null);
	    
	    return;
	    
	} else if (Tree.PROPERTY_LEAF_ICON.equals(name)) {
		
	    // Update leaf icon if need
	    updateIcons(tree, xTree, Tree.PROPERTY_LEAF_ICON);
	    //updateIcons(tree, xTree, createImageView(element, asImage(value)));
	    
	    return;
	    
	} else if (Tree.PROPERTY_NODE_ICON.equals(name)) {
		
	    // Update node (open/close) icon if need
	    updateIcons(tree, xTree, Tree.PROPERTY_NODE_ICON);
	    //updateIcons(tree, xTree, createImageView(element, asImage(value)));

	    
	    return;
	} else if (Tree.PROPERTY_OPEN_ICON.equals(name)) {
		
	    // Update open icon if need
	    updateIcons(tree, xTree, Tree.PROPERTY_OPEN_ICON);
	    updateIcons(tree, xTree, createImageView(element, asImage(value)));
	    
	    return;
	} else if (Tree.PROPERTY_CLOSE_ICON.equals(name)) {
		
	    // Update close icon if need
	    updateIcons(tree, xTree, Tree.PROPERTY_CLOSE_ICON);
	    //updateIcons(tree, xTree, createImageView(element, asImage(value)));
	    
	    return;
		    
	} 
	
	super.setProperty(element, name, value);
    }
    

    @Override
    public void addListener(UIElement element, String eventType, final Listener listener) {
	
	Control control = (Control) element;
	javafx.scene.control.TreeView<?> xTree = asTree(element.getDelegate());
	if (xTree == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    // TODO
	    //xTable.addSelectionListener(createSelectionListener(control, listener));
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    // TODO
	    //xTable.addKeyListener(createKEnterListener(control, listener));
	    //xTable.addMouseListener(createMEnterListener(control, listener));
	    return;
	} 

	
	super.addListener(element, eventType, listener);
    }

    @Override
    public void removeListener(UIElement element, String eventType, final Listener listener) {
	
	Control control = (Control) element;
	javafx.scene.control.TreeView<?> xTreee = asTree(element.getDelegate());
	if (xTreee == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    // TODO
	    //xTable.removeSelectionListener(getSelectionListener(control, listener));
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    // TODO
	    //xTable.removeKeyListener(getKeyListener(control, listener, 0));
	    //xTable.removeMouseListener(getMouseListener(control, listener, 1));
	    return;
	} 

	super.removeListener(element, eventType, listener);
    }

 
  
    
}
