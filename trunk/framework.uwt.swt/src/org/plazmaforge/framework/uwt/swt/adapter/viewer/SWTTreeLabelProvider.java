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

package org.plazmaforge.framework.uwt.swt.adapter.viewer;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.plazmaforge.framework.uwt.swt.adapter.SWTTreeAdapter;
import org.plazmaforge.framework.uwt.util.UWTUtils;
import org.plazmaforge.framework.uwt.widget.Column;
import org.plazmaforge.framework.uwt.widget.tree.Tree;

public class SWTTreeLabelProvider extends SWTLabelProvider implements ITableLabelProvider {

    /**
     * UWT Table
     */
    private Tree tree;

    private org.eclipse.swt.widgets.Tree xTree;;
    private org.eclipse.jface.viewers.TreeViewer xTreeViewer;
    private SWTTreeContentProvider xTreeContentProvider;

    private org.eclipse.swt.graphics.Image leafIcon;
    private org.eclipse.swt.graphics.Image openIcon;
    private org.eclipse.swt.graphics.Image closeIcon;
    
    

    public SWTTreeLabelProvider(Tree tree) {
	this.tree = tree;
	
	xTree = (org.eclipse.swt.widgets.Tree) tree.getDelegate();
	xTreeViewer = (org.eclipse.jface.viewers.TreeViewer) xTree.getData(SWTTreeAdapter.SYS_PROPETY_TREE_VIEWER);
	xTreeContentProvider = (SWTTreeContentProvider) xTreeViewer.getContentProvider();

    }

    private boolean hasColumns() {
	return tree.hasColumns();
    }
    
    public String getText(Object element) {
	return getColumnText(element, 0);
    }
    
    public Image getImage(Object element) {
	return getColumnImage(element, 0);
    }
    
    
    
    public String getColumnText(Object element, int columnIndex) {
	
	if (hasColumns()) {
	    return getText(element, tree, tree.getColumn(columnIndex));
	}
	
	String text = null;
	
	// Get LabelProvider
	org.plazmaforge.framework.uwt.widget.LabelProvider labelProvider = tree.getLabelProvider();
	if (labelProvider != null) {
	    text = getText(element, tree, labelProvider);
	    if (text == null) {
		text = EMPTY_STRING;
	    }
	    return text;
	}
		
	if (element == null) {
	    return EMPTY_STRING;
	}

	
	Object value = UWTUtils.getSimpleValue(element, tree.getDisplayProperty(), tree.getPropertyProvider(), tree);
	text = getTextValue(value, tree);
	
	if (text == null) {
	    text = EMPTY_STRING;
	}

	return text; 
    }

    public Image getColumnImage(Object element, int columnIndex) {

	// Get LabelProvider
	org.plazmaforge.framework.uwt.widget.LabelProvider labelProvider = null;
	if (hasColumns()) {
	    Column column = tree.getColumn(columnIndex);
	    labelProvider = column == null ? null : column.getLabelProvider();
	} else {
	    labelProvider = tree.getLabelProvider();
	}
	
	if (labelProvider != null) {
	    return getImage(element, tree, labelProvider);
	}
	
	if (columnIndex == 0) {
	    if (!hasIcons()){
		return null;
	    }
	    boolean isLeaf = xTreeContentProvider.isLeaf(element);
	    if (isLeaf) {
		return getLeafIcon();
	    }
	    
	    // TODO: Open/Close
	    boolean isExpand = xTreeViewer.getExpandedState(element);
	    return isExpand ? getOpenIcon() : getCloseIcon();
	    
	}
	
	return null;  
    }
    

    public org.eclipse.swt.graphics.Image getLeafIcon() {
        return leafIcon;
    }

    public void setLeafIcon(org.eclipse.swt.graphics.Image leafIcon) {
        this.leafIcon = leafIcon;
    }

 
    public org.eclipse.swt.graphics.Image getOpenIcon() {
        return openIcon;
    }

    public void setOpenIcon(org.eclipse.swt.graphics.Image openIcon) {
        this.openIcon = openIcon;
    }

    public org.eclipse.swt.graphics.Image getCloseIcon() {
        return closeIcon;
    }

    public void setCloseIcon(org.eclipse.swt.graphics.Image closeIcon) {
        this.closeIcon = closeIcon;
    }
 


    private boolean hasIcons() {
	return tree.getLeafIcon() != null 
		|| tree.getNodeIcon() != null
		|| tree.getOpenIcon() != null
		|| tree.getCloseIcon() != null;
    }
  
}
