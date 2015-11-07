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

package org.plazmaforge.framework.uwt.widget.tree;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.data.provider.DataProvider;
import org.plazmaforge.framework.core.data.provider.FlatTreeProvider;
import org.plazmaforge.framework.core.data.provider.TreeProvider;
import org.plazmaforge.framework.uwt.event.EnterListener;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.HasSelection;
import org.plazmaforge.framework.uwt.widget.LabelProvider;
import org.plazmaforge.framework.uwt.widget.Viewer;

public class Tree<T> extends Viewer<T> implements HasSelection<T> {

    
    public static final String PROPERTY_LEAF_ICON = "leafIcon";
    
    public static final String PROPERTY_NODE_ICON = "nodeIcon";
    
    public static final String PROPERTY_OPEN_ICON = "openIcon";
    
    public static final String PROPERTY_CLOSE_ICON = "closeIcon";
    
    
    public static final int DEFAULT_WIDTH = 300;
    
    public static final int DEFAULT_HEIGHT = 300;
    
    
    
    /**
     * Columns of Tree
     */
    private List<TreeColumn> columns;
    
    
    private boolean linesVisible;
    
    private boolean headerVisible;
    
    
    private String displayProperty;
    
    private String displayFormat;
    
    
    private Image leafIcon;
    
    private Image nodeIcon;
    
    private Image openIcon;
    
    private Image closeIcon;
    

    /**
     * Label provider
     * getImage, getText
     */
    private LabelProvider labelProvider;


    public Tree() {
	super();
	
	columns = new ArrayList<TreeColumn>();
	
	// Create default DataStore
	setDataStore(createDefaultDataStore());

	linesVisible = true;
	headerVisible = true;
    }
    
    protected DataProvider<T> createDefaultDataProvider() {
	return createDataListProvider(null);
    }

    protected DataProvider<T> createDataListProvider(List<T> dataList) {
	DataProvider<T> dataProvider = new FlatTreeProvider<T>(dataList);
	return dataProvider;
    }
    
    protected boolean isChildrenProperty(String name) {
	return PROPERTY_COLUMNS.equals(name) || PROPERTY_ITEMS.equals(name) || PROPERTY_CHILDREN.equals(name);
    }
    


    public String getDisplayProperty() {
        return displayProperty;
    }

    public void setDisplayProperty(String displayProperty) {
        this.displayProperty = displayProperty;
        fireChangeProperty(PROPERTY_DISPLAY_PROPERTY, displayProperty);
    }

    public String getDisplayFormat() {
        return displayFormat;
    }

    public void setDisplayFormat(String displayFormat) {
        this.displayFormat = displayFormat;
        fireChangeProperty(PROPERTY_DISPLAY_FORMAT, displayFormat);
    }
    
    public boolean isLinesVisible() {
        return linesVisible;
    }

    public void setLinesVisible(boolean linesVisible) {
        this.linesVisible = linesVisible;
        fireChangeProperty(PROPERTY_LINES_VISIBLE, linesVisible);
    }

    public boolean isHeaderVisible() {
        return headerVisible;
    }

    public void setHeaderVisible(boolean headerVisible) {
        this.headerVisible = headerVisible;
        fireChangeProperty(PROPERTY_HEADER_VISIBLE, headerVisible);
    }
       

    public Image getLeafIcon() {
        return leafIcon;
    }

    public void setLeafIcon(Image leafIcon) {
        this.leafIcon = leafIcon;
        fireChangeProperty(PROPERTY_LEAF_ICON, leafIcon);
    }

    public Image getNodeIcon() {
        return nodeIcon;
    }

    public void setNodeIcon(Image nodeIcon) {
        this.nodeIcon = nodeIcon;
        fireChangeProperty(PROPERTY_NODE_ICON, nodeIcon);
    }

    public Image getOpenIcon() {
        return openIcon;
    }

    public void setOpenIcon(Image openIcon) {
        this.openIcon = openIcon;
        fireChangeProperty(PROPERTY_OPEN_ICON, leafIcon);
    }

    
    public Image getCloseIcon() {
        return closeIcon;
    }

    public void setCloseIcon(Image closeIcon) {
        this.closeIcon = closeIcon;
        fireChangeProperty(PROPERTY_CLOSE_ICON, leafIcon);
    }

    
    public void setColumns(List<TreeColumn> columns) {
        this.columns = columns;
    }

    public void addColumn(TreeColumn column) {
	checkTreeColumn(column);
	columns.add(column);
	column.setParent(this);
	
	fireAddChild(getInitChildren(PROPERTY_COLUMNS), column);
    }


    public void removeColumn(TreeColumn column) {
	checkTreeColumn(column);
	columns.remove(column);
	column.setParent(null);
	
	fireRemoveChild(getInitChildren(PROPERTY_COLUMNS), column);
    }

    public TreeColumn getColumn(int index) {
	return columns.get(index);
    }
    
    
    public int getColumnCount() {
	return columns.size();
    }

    public LabelProvider getLabelProvider() {
        return labelProvider;
    }

    public void setLabelProvider(LabelProvider labelProvider) {
        this.labelProvider = labelProvider;
        fireChangeProperty(PROPERTY_LABEL_PROVIDER, labelProvider);
    }

    protected void checkDataProvider(DataProvider<T> dataProvider) {
	super.checkDataProvider(dataProvider);
	if (!(dataProvider instanceof TreeProvider)) {
	    throw new IllegalArgumentException("DataProvider must be TreeDataProvider");
	}
    }

    protected void checkTreeColumn(TreeColumn column) {
	if (column == null) {
	    throw new IllegalArgumentException("Column must be not null");
	}
    }

//    protected void checkTreeItem(TreeItem item) {
//	if (item == null) {
//	    throw new IllegalArgumentException("Item must be not null");
//	}
//    }
    
    public List<TreeColumn> getColumns() {
	return new ArrayList<TreeColumn>(columns);
    }
    
    
    public boolean hasColumns() {
	// TODO
	return false;
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////
    // WARNING !!! DISABLE 
    /////////////////////////////////////////////////////////////////////////////////
    
    /*
    public void addItem(TreeItem item) {
	checkTreeItemStructure();
	checkTreeItem(item);
	getTreeItemProvider().addItem(item);
	item.setParent(this);
	
	fireAddChild(getInitChildren(PROPERTY_ITEMS), item);
    }
    
    
    public void removeItem(TreeItem item) {
	checkTreeItemStructure();
	checkTreeItem(item);
	getTreeItemProvider().removeItem(item);
	item.setParent(null);
	
	fireRemoveChild(getInitChildren(PROPERTY_ITEMS), item);
    }
    */
    
   
    ////
    
    public void addEnterListener(EnterListener listener) {
	addEnterListener(this, listener);
    }
    
    public void removeEnterListener(EnterListener listener) {
	removeEnterListener(this, listener);
    }


}
