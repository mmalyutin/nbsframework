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

package org.plazmaforge.framework.uwt.swt.adapter;

import java.util.List;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.swt.adapter.viewer.SWTTreeContentProvider;
import org.plazmaforge.framework.uwt.swt.adapter.viewer.SWTTreeLabelProvider;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.LabelProvider;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.tree.Tree;

public class SWTTreeAdapter extends SWTCompositeAdapter {

    public static final String SYS_PROPETY_TREE_VIEWER = "$treeViewer";
    
    
    public Object createDelegate(UIObject parent, UIObject element) {
	
	final Tree tree = (Tree) element;
	
	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	final org.eclipse.swt.widgets.Tree xTree = new org.eclipse.swt.widgets.Tree(xParent, SWT.BORDER | SWT.FULL_SELECTION);
	
	//xTree.setHeaderVisible(true); // Header visible by default
	//xTree.setLinesVisible(true); // Lines visible by default
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// WARNING !!!
	// Lazy TreeViewer initialization because we have problem with rendering tree nodes (children nodes are not rendering)
	// DON'T REMOVE IT
	// 
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//org.eclipse.jface.viewers.TreeViewer xTreeViewer = new org.eclipse.jface.viewers.TreeViewer(delegate);
	//xTree.setData(SYS_PROPETY_TREE_VIEWER, xTreeViewer);
	
	xTree.addTreeListener(new TreeListener() {

	    @Override
	    public void treeCollapsed(TreeEvent event) {
		org.eclipse.swt.widgets.TreeItem treeItem  = (org.eclipse.swt.widgets.TreeItem) event.item;
		updateIcon(tree, xTree, treeItem, false);
	    }

	    @Override
	    public void treeExpanded(TreeEvent event) {
		org.eclipse.swt.widgets.TreeItem treeItem  = (org.eclipse.swt.widgets.TreeItem) event.item;
		updateIcon(tree, xTree, treeItem, true);
	    }
	    
	});
	
	addToParent(xParent, xTree, element);
	return xTree;
    }
    
    @Override
    public void checkDelegate(UIObject element) {
	// clear super method
    }
    
    protected void updateIcons(Tree tree, org.eclipse.swt.widgets.Tree xTree, String icon) {
	org.eclipse.jface.viewers.TreeViewer xTreeViewer = getViewer(xTree);
	SWTTreeLabelProvider xLabelProvider = (SWTTreeLabelProvider) xTreeViewer.getLabelProvider();
   	LabelProvider labelProvider = tree.getLabelProvider();
   	
   	if (labelProvider != null) {
   	    
   	    // Reset all icons because LabelProvider produces icon (image and text)
   	    xLabelProvider.setLeafIcon(null);
   	    xLabelProvider.setOpenIcon(null);
   	    xLabelProvider.setCloseIcon(null);
   	    
   	    xTreeViewer.refresh(); // TODO: Must analyze performance: Need call LabelProvider only (getImage)
   	    return;
   	}
   	
   	
   	if (Tree.PROPERTY_NODE_ICON.equals(icon)) {
   	    
   	    // Node Icon -> Open and Close icon
   	    org.eclipse.swt.graphics.Image i = createImage(tree, tree.getNodeIcon());
   	    xLabelProvider.setOpenIcon(i);
   	    xLabelProvider.setCloseIcon(i);
   	    xTreeViewer.refresh(); // TODO: Must analyze performance: Need call LabelProvider only (getImage)
   	    return;
   	}
   	
   	if (icon == null || Tree.PROPERTY_LEAF_ICON.equals(icon)) {
   	    xLabelProvider.setLeafIcon(createImage(tree, tree.getLeafIcon()));
   	}
   	if (icon == null || Tree.PROPERTY_OPEN_ICON.equals(icon)) {
   	    xLabelProvider.setOpenIcon(createImage(tree, tree.getOpenIcon()));
   	}
   	if (icon == null || Tree.PROPERTY_CLOSE_ICON.equals(icon)) {
   	    xLabelProvider.setCloseIcon(createImage(tree, tree.getCloseIcon()));
   	}
   	
   	xTreeViewer.refresh(); // TODO: Must analyze performance: Need call LabelProvider only (getImage)
    }    
    
    protected void updateIcon(Tree tree, org.eclipse.swt.widgets.Tree xTree, org.eclipse.swt.widgets.TreeItem treeItem, boolean isExpanded) {
	LabelProvider labelProvider = tree.getLabelProvider();
   	
   	if (labelProvider != null) {
   	    return;
   	}
   	
	org.eclipse.jface.viewers.TreeViewer viewer = getViewer(xTree);
	SWTTreeLabelProvider xLabelProvider = (SWTTreeLabelProvider) viewer.getLabelProvider();

	treeItem.setImage(isExpanded ? xLabelProvider.getOpenIcon() : xLabelProvider.getCloseIcon());
	
	
    }
    
    protected org.eclipse.jface.viewers.TreeViewer getViewer(org.eclipse.swt.widgets.Tree xTree) {
	org.eclipse.jface.viewers.TreeViewer xTreeViewer = (org.eclipse.jface.viewers.TreeViewer) xTree.getData(SYS_PROPETY_TREE_VIEWER);
	if (xTreeViewer == null) {
	    xTreeViewer = new org.eclipse.jface.viewers.TreeViewer(xTree);
	    xTree.setData(SYS_PROPETY_TREE_VIEWER, xTreeViewer);
	}
	return  xTreeViewer;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	org.eclipse.swt.widgets.Tree xTree = (org.eclipse.swt.widgets.Tree) element.getDelegate();
	if (xTree == null) {
	    return;
	}
	
	//org.eclipse.jface.viewers.TreeViewer xTreeViewer = (org.eclipse.jface.viewers.TreeViewer) xTree.getData(SYS_PROPETY_TREE_VIEWER);
	
	org.plazmaforge.framework.uwt.widget.tree.Tree tree = (org.plazmaforge.framework.uwt.widget.tree.Tree) element;
	
	if (Table.PROPERTY_LAYOUT.equals(name)) {
	    // ignore layout
	    return;
	} else if (Tree.PROPERTY_LINES_VISIBLE.equals(name)) {
	    xTree.setLinesVisible(booleanValue(value));
	    return;
	} else if (Tree.PROPERTY_HEADER_VISIBLE.equals(name)) {
	    xTree.setHeaderVisible(booleanValue(value));
	    return;
	} else if (Tree.PROPERTY_DATA_LIST.equals(name)) {
	    
	    org.eclipse.jface.viewers.TreeViewer xTreeViewer = getViewer(xTree);
	    List<Object> dataList = (List<Object>) value;
	    
	    IContentProvider contentProvider = xTreeViewer.getContentProvider();
	    if (contentProvider != null) {
		contentProvider.dispose();
	    }
	    
	    SWTTreeContentProvider newContentProvider = new SWTTreeContentProvider(tree.getDataProvider());
	    newContentProvider.setDataList(dataList);
	    
	    xTreeViewer.setContentProvider(newContentProvider);
	    xTreeViewer.setLabelProvider(new SWTTreeLabelProvider(tree));
	    xTreeViewer.setInput("");
	    
	    return;
	} else if (Tree.PROPERTY_DISPLAY_PROPERTY.equals(name)) {
	    // Do nothing because SWT ContentProvider use UWT Tree to get 'displayProperty'
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
	org.eclipse.swt.widgets.Tree xTree = (org.eclipse.swt.widgets.Tree) element.getDelegate();
	if (xTree == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xTree.addSelectionListener(createSelectionListener(control, listener));
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
	org.eclipse.swt.widgets.Tree xTree = (org.eclipse.swt.widgets.Tree) element.getDelegate();
	if (xTree == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xTree.removeSelectionListener(getSelectionListener(control, listener));
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    xTree.removeKeyListener(getKeyListener(control, listener, 0));
	    xTree.removeMouseListener(getMouseListener(control, listener, 1));
	    return;
	} 
	
	super.removeListener(element, eventType, listener);
    }

   
}
