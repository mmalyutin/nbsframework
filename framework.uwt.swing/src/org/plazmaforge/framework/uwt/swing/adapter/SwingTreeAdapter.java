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

package org.plazmaforge.framework.uwt.swing.adapter;

import java.util.List;

import javax.swing.JScrollPane;

import org.plazmaforge.framework.core.data.provider.TreeProvider;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.swing.adapter.viewer.SwingTreeCellRenderer;
import org.plazmaforge.framework.uwt.swing.adapter.viewer.SwingTreeModel;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.LabelProvider;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.tree.Tree;

public class SwingTreeAdapter extends SwingCompositeAdapter {

    
    public Object createDelegate(UIObject parent, UIObject element) {
	java.awt.Container xParent = getContent(parent.getDelegate());
	Tree tree = (Tree) element;
	
	// Create default tree model 
	SwingTreeModel treeModel = new SwingTreeModel(tree);
	treeModel.setEditable(false);
	
	javax.swing.JTree xTree = new javax.swing.JTree(treeModel);
	
	// Set invisible root node
	xTree.setRootVisible(false);
	
	// Set visible +/- icon around folder nodes
	xTree.setShowsRootHandles(true);
	
	// Set default renderer without icons (leaf/open/closed)
	SwingTreeCellRenderer renderer = new SwingTreeCellRenderer(tree);
	xTree.setCellRenderer(renderer);
	

	// Add scroll wrapper because the JTable has not scrolling 
	JScrollPane scrollpane = new JScrollPane(xTree);
	
	addToParent(xParent, scrollpane, element);	
	return scrollpane; 
    }

    @Override
    protected java.awt.Component getViewComponent(Object delegate) {
   	return (java.awt.Component) getScrollComponent(delegate);
    }
    
    protected void updateIcons(Tree tree, javax.swing.JTree xTree, String icon) {
	SwingTreeCellRenderer renderer = (SwingTreeCellRenderer) xTree.getCellRenderer();
	LabelProvider labelProvider = tree.getLabelProvider();
	
	if (labelProvider != null) {
	    
	    // Reset all icons because LabelProvider produces icon (image and text)
	    renderer.setLeafIcon(null);
	    renderer.setOpenIcon(null);
	    renderer.setClosedIcon(null);
	    
	    return;
	}
	
	
	if (Tree.PROPERTY_NODE_ICON.equals(icon)) {
	    
	    // Node Icon -> Open and Close icon
	    javax.swing.Icon i = createImageIcon(tree, tree.getNodeIcon()); 
	    renderer.setOpenIcon(i);
	    renderer.setClosedIcon(i);
	    return;
	}
	
	if (icon == null || Tree.PROPERTY_LEAF_ICON.equals(icon)) {
	    renderer.setLeafIcon(createImageIcon(tree, tree.getLeafIcon()));
	}
	if (icon == null || Tree.PROPERTY_OPEN_ICON.equals(icon)) {
	    renderer.setOpenIcon(createImageIcon(tree, tree.getOpenIcon()));
	}
	if (icon == null || Tree.PROPERTY_CLOSE_ICON.equals(icon)) {
	    renderer.setClosedIcon(createImageIcon(tree, tree.getCloseIcon()));
	}
    }

    @Override
    public void setProperty(UIObject element, String name, Object value) {

	javax.swing.JTree xTree = getJTree(element.getDelegate());
	Tree tree = (Tree) element;
	if (xTree == null) {
	    return;
	}
	if (Tree.PROPERTY_LAYOUT.equals(name)) {
	    // ignore layout
	    return;
	} else if (Tree.PROPERTY_LINES_VISIBLE.equals(name)) {
	    //tree.setShowGrid(booleanValue(value));
	    return;
	} else if (Tree.PROPERTY_HEADER_VISIBLE.equals(name)) {
	    //boolean visible = booleanValue(value);
	    //tree.getTableHeader().setVisible(visible);
	    //tree.getTableHeader().setPreferredSize(visible ? null : new java.awt.Dimension(-1, 0));
	    return;
	} else if (Tree.PROPERTY_DATA_LIST.equals(name)) {
	    List<Object> dataList = (List<Object>) value;
	    SwingTreeModel treeModel = (SwingTreeModel) xTree.getModel();
	    treeModel.setProvider((TreeProvider) tree.getDataProvider());
	    treeModel.setDataList(dataList);
	    return;
	} else if (Tree.PROPERTY_LABEL_PROVIDER.equals(name)) {
	
	    // Update leaf/open/close icon if need
	    updateIcons(tree, xTree, null);
	    
	    return;
	} else if (Tree.PROPERTY_LEAF_ICON.equals(name)) {
		
	    // Update leaf icon if need
	    updateIcons(tree, xTree, Tree.PROPERTY_LEAF_ICON);
	    
	    return;
	    
	} else if (Tree.PROPERTY_NODE_ICON.equals(name)) {
		
	    // Update node (open/close) icon if need
	    updateIcons(tree, xTree, Tree.PROPERTY_NODE_ICON);
	    
	    return;
	} else if (Tree.PROPERTY_OPEN_ICON.equals(name)) {
		
	    // Update open icon if need
	    updateIcons(tree, xTree, Tree.PROPERTY_OPEN_ICON);
	    
	    return;
	} else if (Tree.PROPERTY_CLOSE_ICON.equals(name)) {
		
	    // Update close icon if need
	    updateIcons(tree, xTree, Tree.PROPERTY_CLOSE_ICON);
	    
	    return;
	}



	super.setProperty(element, name, value);

    }
    
    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	
	Control control = (Control) element;
	javax.swing.JTree xTree = getJTree(element.getDelegate());
	if (xTree == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xTree.getSelectionModel().addTreeSelectionListener(createTreeSelectionListener(control, listener));
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    xTree.addKeyListener(createKEnterListener(control, listener));
	    xTree.addMouseListener(createMEnterListener(control, listener));
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }
    
    
    @Override
    public void removeListener(UIObject element, String eventType, final Listener listener) {
	
	Control control = (Control) element;
	javax.swing.JTree xTree = getJTree(element.getDelegate());
	if (xTree == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xTree.getSelectionModel().removeTreeSelectionListener(getTreeSelectionListener(control, listener));
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    xTree.removeKeyListener(getKeyListener(control, listener, 0));
	    xTree.removeMouseListener(getMouseListener(control, listener, 1));
	    return;
	} 
	
	super.removeListener(element, eventType, listener);
    }
}
