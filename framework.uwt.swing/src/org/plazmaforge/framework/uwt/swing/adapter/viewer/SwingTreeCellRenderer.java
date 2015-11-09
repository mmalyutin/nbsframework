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

package org.plazmaforge.framework.uwt.swing.adapter.viewer;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.swing.adapter.SwingHelper;
import org.plazmaforge.framework.uwt.util.UWTUtils;
import org.plazmaforge.framework.uwt.widget.LabelProvider;
import org.plazmaforge.framework.uwt.widget.Viewer;
import org.plazmaforge.framework.uwt.widget.tree.Tree;

public class SwingTreeCellRenderer extends DefaultTreeCellRenderer {

    /**
     * UWT Tree
     */
    private Tree viewer;
    
    private Icon cellIcon;
    
    
    
    public SwingTreeCellRenderer(Tree viewer) {
	super();
	this.viewer = viewer;
	
	setLeafIcon(null);
	setOpenIcon(null);
	setClosedIcon(null);

    }

    public Component getTreeCellRendererComponent(JTree tree, Object value,
		  boolean sel,
		  boolean expanded,
		  boolean leaf, int row,
		  boolean hasFocus) {

	
	LabelProvider labelProvider = viewer.getLabelProvider();
	String text = null;
	
	if (labelProvider != null) {
	    
	    // WARNING!!! We use DefaultMutableTreeNode in TreeModel always 
	    DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
	    Object element = node.getUserObject();
	    
	    // Get UWT Image by LabelProvider
	    Image image = labelProvider.getImage(element);
	    
	    // Create Swing Image
	    javax.swing.Icon xImage = SwingHelper.createImageIcon(viewer, image);
	    if (xImage != null) {
		setCellIcon(xImage);
	    }
	    
	    // Get text value by LabelProvider
	    text = labelProvider.getText(element);
	} else {
	    // Get text value by Format
	    text = getTextValue(value, viewer);
	}
	
	if (text == null) {
	    text = "";
	}
	
	Component component = super.getTreeCellRendererComponent(tree, text /*value*/, sel, expanded, leaf, row, hasFocus);
	
	//setText(text);
	
	return component;
	
    }
    
    
    
    public Icon getCellIcon() {
        return cellIcon;
    }

    public void setCellIcon(Icon cellIcon) {
        this.cellIcon = cellIcon;
    }

    public Icon getLeafIcon() {
	return cellIcon != null ? cellIcon : leafIcon;
    }

    public Icon getOpenIcon() {
	return cellIcon != null ? cellIcon : openIcon;
    }

    public Icon getClosedIcon() {
	return cellIcon != null ? cellIcon : closedIcon;
    }
       
       
    protected String getTextValue(Object value, Viewer viewer) {
  	return UWTUtils.getTextValue(value, viewer);
    }
}
