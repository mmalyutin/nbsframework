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

import org.plazmaforge.framework.util.CoreUtils;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.gwt.GWTUtils;
import org.plazmaforge.framework.uwt.gxt.data.ModelData;
//import org.plazmaforge.framework.uwt.gxt.adapter.viewer.GXTTreeCellRenderer;
import org.plazmaforge.framework.uwt.gxt.widget.XColumnConfig;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.LabelProvider;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.tree.Tree;

import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.tree.TreeStyle;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;

/**
 * 
 * @author ohapon
 *
 */
public class GXTTreeAdapter extends GXTViewerAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	
	Tree<?> tree =  (Tree<?>) element;
	
	// Create first column to emulate column header
	List<com.sencha.gxt.widget.core.client.grid.ColumnConfig<ModelData, ?>> columns = new ArrayList<com.sencha.gxt.widget.core.client.grid.ColumnConfig<ModelData, ?>>();
	XColumnConfig<?> xColumn = new XColumnConfig(createXValueProvider("toString", tree.getPropertyProvider(), null), 100, ""); //TODO
	//xColumn.setId("0"); // By default ID is index of column
	//xColumn.setRenderer(new TreeGridCellRenderer<ModelData>());
	columns.add(xColumn);
	
	
	com.sencha.gxt.widget.core.client.grid.ColumnModel<ModelData> cm = new com.sencha.gxt.widget.core.client.grid.ColumnModel<ModelData>(columns);
	com.sencha.gxt.data.shared.TreeStore<ModelData> store = new com.sencha.gxt.data.shared.TreeStore<ModelData>(GXTHelper.createXDefaultModelKeyProvider());
	com.sencha.gxt.widget.core.client.treegrid.TreeGrid<ModelData> xTree = new com.sencha.gxt.widget.core.client.treegrid.TreeGrid<ModelData>(store, cm, xColumn);

	//DISABLE:MIGRATION
	//xTree.setColumnLines(false);
	xTree.setHideHeaders(true);
	
	TreeStyle treeStyle = xTree.getStyle();
	
	treeStyle.setLeafIcon(null);
	treeStyle.setNodeOpenIcon(null);
	treeStyle.setNodeCloseIcon(null);
	
	//DISABLE:MIGRATION
	//AbstractImagePrototype plusImage = createImage(element,	"widget/plus.gif");
	//AbstractImagePrototype minusImage = createImage(element, "widget/minus.gif");
	//if (plusImage != null && minusImage != null) {
	//    treeStyle.setJointCollapsedIcon(plusImage);
	//    treeStyle.setJointExpandedIcon(minusImage);
	//}
	    
	// Assign first column
	xColumn.setGrid(xTree);
	
	//DISABLE:MIGRATION
	//xColumn.setRenderer(new GXTTreeCellRenderer<ModelData>(tree));

	xTree.setWidth(Table.DEFAULT_WIDTH); // TODO
	xTree.setHeight(150 /*Table.DEFAULT_HEIGHT*/); // TODO
	
	//tree.setAutoWidth(true);
	//tree.setAutoHeight(true);
	
        xTree.getView().setForceFit(true);
        
	addToParent(getContent(parent.getDelegate()), xTree, element); // Add to parent
	
	return xTree;
    }

    protected void updateIcons(Tree tree,com.sencha.gxt.widget.core.client.treegrid.TreeGrid<ModelData> xTree, String icon) {
	
	TreeStyle treeStyle = xTree.getStyle();
      	LabelProvider labelProvider = tree.getLabelProvider();
      	
      	if (labelProvider != null) {
      	    
      	    // Reset all icons because LabelProvider produces icon (image and text)
      	    treeStyle.setLeafIcon(null);
      	    treeStyle.setNodeOpenIcon(null);
      	    treeStyle.setNodeCloseIcon(null);
      	    
      	    return;
      	}
      	
      	
      	if (Tree.PROPERTY_NODE_ICON.equals(icon)) {
      	    
      	    // Node Icon -> Open and Close icon
      	    //DISABLE:MIGRATION
      	    //AbstractImagePrototype i = createImage(tree, tree.getNodeIcon());
      	    //treeStyle.setNodeOpenIcon(i);
      	    //treeStyle.setNodeCloseIcon(i);
      	    return;
      	}
      	
      	if (icon == null || Tree.PROPERTY_LEAF_ICON.equals(icon)) {
      	    treeStyle.setLeafIcon(createImage(tree, tree.getLeafIcon()));
      	}
      	if (icon == null || Tree.PROPERTY_OPEN_ICON.equals(icon)) {
      	    treeStyle.setNodeOpenIcon(createImage(tree, tree.getOpenIcon()));
      	}
      	if (icon == null || Tree.PROPERTY_CLOSE_ICON.equals(icon)) {
      	    treeStyle.setNodeCloseIcon(createImage(tree, tree.getCloseIcon()));
      	}
      	
    }    
    
    protected com.sencha.gxt.widget.core.client.treegrid.TreeGrid<ModelData> getTree(Object delegate) {
	return (com.sencha.gxt.widget.core.client.treegrid.TreeGrid<ModelData>) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	com.sencha.gxt.widget.core.client.treegrid.TreeGrid<ModelData> xTree = getTree(element.getDelegate());
	Tree tree = (Tree) element;
	
	if (xTree == null) {
	    return;
	}
	if (Table.PROPERTY_LAYOUT.equals(name)) {
	    // ignore layout
	    return;
	} else if (Table.PROPERTY_LINES_VISIBLE.equals(name)) {
	    // TODO: Only column lines. Must implement row lines
	    //DISABLE:MIGRATION
	    //xTree.setColumnLines(booleanValue(value));
	    return;
	} else if (Table.PROPERTY_HEADER_VISIBLE.equals(name)) {
	    xTree.setHideHeaders(!booleanValue(value)); // inverse value
	    return;
	} else if (Table.PROPERTY_DATA_LIST.equals(name)) {
	    
	    // Get DataList
	    List dataList = (List) value;
	    
	    // Populate TreeStore by flat DataList
	    // TODO We need use TreeDataProvider to populate TreeStore
	    com.sencha.gxt.data.shared.TreeStore<ModelData> store = xTree.getTreeStore();
	    store.clear();
	    
	    populateTreeStore2(tree, dataList, store);
	    
//	    List<ModelData> models = new ArrayList<ModelData>();
//	    if  (dataList != null) {
//		for (Object data: dataList) {
//		    
//		    // Create wrap of data
//		    ModelData model = createModel(data);
//		    GXTTreeDataModel treeModel = new GXTTreeDataModel(model, store, (TreeProvider) tree.getDataProvider()); 
//		    models.add(treeModel);
//		}
//	    }
//	    store.add(models, true);
	    
	    //xTree.reconfigure(store, xTree.getColumnModel(), null);
	    
	    return;
	} else if (Tree.PROPERTY_DISPLAY_PROPERTY.equals(name)) {
	    
	    //DISABLE:MIGRATION
	    // Get first emulate column
	    //XColumnConfig xColumn = getFantomColumn(xTree);
	    //xColumn.setId((String) value);
	    return;
	} else if (Tree.PROPERTY_DISPLAY_FORMAT.equals(name)) {
	    
	    // Get pattern
	    String pattern = (String) value;
	    // Get first emulate column
	    XColumnConfig xColumn = getFantomColumn(xTree);
	    
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
    
    /**
     * Return fantom (first) column of tree
     * @param xTree
     * @return
     */
    protected XColumnConfig getFantomColumn(com.sencha.gxt.widget.core.client.treegrid.TreeGrid<ModelData> xTree) {
	com.sencha.gxt.widget.core.client.grid.ColumnModel cm = xTree.getColumnModel();
	
	// Get first emulate column
	XColumnConfig xColumn = (XColumnConfig) cm.getColumn(0);
	return xColumn;
    }
    

    //DISABLE:MIGRATION
//    protected GXTTreeCellRenderer getTreeCellRenderer(Tree tree,com.sencha.gxt.widget.core.client.treegrid.TreeGrid<ModelData> xTree) {
//	XColumnConfig xColumn = getFantomColumn(xTree);
//
//	GXTTreeCellRenderer renderer = (GXTTreeCellRenderer) xColumn.getRenderer();
//	if (renderer == null) {
//	    renderer = new GXTTreeCellRenderer(tree);
//	    xColumn.setRenderer(renderer);
//	}
//	
//	return renderer;
//    }
    
    
    
    /*
    @Override
    protected void addMouseDownListener(com.sencha.gxt.widget.core.client.Component component, final Listener listener) {
	component.addListener(Events.CellMouseDown, createListener(listener));
    }

    @Override
    protected void addMouseUpListener(com.sencha.gxt.widget.core.client.Component component, final Listener listener) {
	component.addListener(Events.CellMouseUp, createListener(listener));
    }
    
    @Override
    protected void addMouseClickListener(com.sencha.gxt.widget.core.client.Component component, final Listener listener) {
	component.addListener(Events.CellClick, createListener(listener));
    }

    @Override
    protected void addMouseDoubleClickListener(com.sencha.gxt.widget.core.client.Component component, final Listener listener) {
	component.addListener(Events.CellDoubleClick, createListener(listener));
    }

    @Override
    protected void addSelectionListener(com.sencha.gxt.widget.core.client.Component component, final Listener listener) {
	((com.sencha.gxt.widget.core.client.treegrid.TreeGrid<ModelData>) component).getSelectionModel().addListener(Events.SelectionChange, createListener(listener));
    }
    */

    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	Control control = (Control) element;
	com.sencha.gxt.widget.core.client.treegrid.TreeGrid<ModelData> xTree = getTree(element.getDelegate());
	if (xTree == null) {
	    return;
	}
	
	if (eq(Events.Selection, eventType)) {
	    addSelectionListener(xTree, control, listener);
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    addEnterListener(xTree, control, listener);
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }

}
