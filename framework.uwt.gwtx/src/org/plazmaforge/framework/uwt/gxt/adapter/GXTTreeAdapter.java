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

import org.plazmaforge.framework.core.data.provider.TreeProvider;
import org.plazmaforge.framework.util.CoreUtils;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.gwt.GWTUtils;
import org.plazmaforge.framework.uwt.gxt.adapter.viewer.XValueProvider;
import org.plazmaforge.framework.uwt.gxt.data.ModelData;
//import org.plazmaforge.framework.uwt.gxt.adapter.viewer.GXTTreeCellRenderer;
import org.plazmaforge.framework.uwt.gxt.widget.XColumnConfig;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.LabelProvider;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.tree.Tree;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.loader.ChildTreeStoreBinding;
import com.sencha.gxt.data.shared.loader.TreeLoader;
import com.sencha.gxt.widget.core.client.tree.TreeStyle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.rpc.AsyncCallback;

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
	XColumnConfig<?> xColumn = new XColumnConfig(createXValueProvider(tree.getDisplayProperty(), tree.getPropertyProvider(), null), 100, "");
	//xColumn.setRenderer(new TreeGridCellRenderer<ModelData>());
	columns.add(xColumn);
	
	
	com.sencha.gxt.widget.core.client.grid.ColumnModel<ModelData> cm = new com.sencha.gxt.widget.core.client.grid.ColumnModel<ModelData>(columns);
	com.sencha.gxt.data.shared.TreeStore<ModelData> store = new com.sencha.gxt.data.shared.TreeStore<ModelData>(GXTHelper.createXDefaultModelKeyProvider());
	final com.sencha.gxt.widget.core.client.treegrid.TreeGrid<ModelData> xTree = new com.sencha.gxt.widget.core.client.treegrid.TreeGrid<ModelData>(store, cm, xColumn);

	//DISABLE:MIGRATION
	//xTree.setColumnLines(false);
	xTree.setHideHeaders(true);
	    
        xTree.setWidth(Table.DEFAULT_WIDTH); // TODO
	xTree.setHeight(150 /*Table.DEFAULT_HEIGHT*/); // TODO
	
	//tree.setAutoWidth(true);
	//tree.setAutoHeight(true);
	
        xTree.getView().setForceFit(true);	
	
	TreeStyle treeStyle = xTree.getStyle();
	
	//treeStyle.setLeafIcon(null);
	//treeStyle.setNodeOpenIcon(null);
	//treeStyle.setNodeCloseIcon(null);
	
	//treeStyle.setJointCloseIcon(null);
	//treeStyle.setJointOpenIcon(null);
	      
	//DISABLE:MIGRATION
	ImageResource plusImage = createImage(element, "widget/plus.gif");
	ImageResource minusImage = createImage(element, "widget/minus.gif");
	if (plusImage != null && minusImage != null) {
	    //treeStyle.setJointCloseIcon(plusImage);
	    //treeStyle.setJointOpenIcon(minusImage);
	}
	    
	// Assign first column
	xColumn.setGrid(xTree);
	
	//DISABLE:MIGRATION
	//xColumn.setRenderer(new GXTTreeCellRenderer<ModelData>(tree));

        ////
        TreeLoader loader = null;
        if (tree.getDataProvider() != null && tree.getDataProvider() instanceof TreeProvider) {
            loader = createTreeLoader((TreeProvider) tree.getDataProvider(), store);
            if (loader != null) {
        	xTree.setTreeLoader(loader);
            }
        }
        ////
        
	addChild(getContent(parent.getDelegate()), xTree, element); // Add to parent
	return xTree;
    }

    protected void updateIcons(Tree<?> tree, com.sencha.gxt.widget.core.client.treegrid.TreeGrid<ModelData> xTree, String icon) {
	
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
      	    ImageResource image = createImage(tree, tree.getNodeIcon());
      	    treeStyle.setNodeOpenIcon(image);
      	    treeStyle.setNodeCloseIcon(image);
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
    
    protected com.sencha.gxt.widget.core.client.treegrid.TreeGrid<ModelData> asTree(Object delegate) {
	return (com.sencha.gxt.widget.core.client.treegrid.TreeGrid<ModelData>) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	com.sencha.gxt.widget.core.client.treegrid.TreeGrid<ModelData> xTree = asTree(element.getDelegate());
	Tree<?> tree = (Tree<?>) element;
	
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
	    List<?> dataList = (List<?>) value;
	    
	    // Populate TreeStore by flat DataList
	    com.sencha.gxt.data.shared.TreeStore<ModelData> store = xTree.getTreeStore();
	    store.clear();
	    
	    populateTreeStore2(tree, dataList, store);
	    
	    return;
	} else if (Tree.PROPERTY_DISPLAY_PROPERTY.equals(name)) {
	    
	    // Get first emulate column
	    XColumnConfig<?> xColumn = getFantomColumn(xTree);
	    XValueProvider xValueProvider = (XValueProvider) xColumn.getValueProvider();
	    xValueProvider.setProperty((String) value);
	    
	    return;
	} else if (Tree.PROPERTY_DISPLAY_FORMAT.equals(name)) {

	    // Get first emulate column
	    XColumnConfig<?> xColumn = getFantomColumn(xTree);
	    
	    // Get pattern
	    String pattern = (String) value;
	    
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
    protected XColumnConfig<?> getFantomColumn(com.sencha.gxt.widget.core.client.treegrid.TreeGrid<ModelData> xTree) {
	com.sencha.gxt.widget.core.client.grid.ColumnModel<?> cm = xTree.getColumnModel();
	
	// Get first emulate column
	XColumnConfig<?> xColumn = (XColumnConfig<?>) cm.getColumn(0);
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
    */

    
    @Override
    protected void addSelectionListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// GWT Selection (item)
	asTree(xWidget).getSelectionModel().addSelectionHandler(createModelSelectionListener(widget, listener));
    }
    
    @Override
    protected void removeSelectionListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//getTree(xWidget).getSelectionModel().removeListener(com.sencha.gxt.ui.client.event.Events.SelectionChange,// getListener(widget, listener)); //TODO
    }    
    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	Control control = (Control) element;
	com.sencha.gxt.widget.core.client.treegrid.TreeGrid<ModelData> xTree = asTree(element.getDelegate());
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

    private TreeLoader<ModelData> createTreeLoader(final TreeProvider provider, com.sencha.gxt.data.shared.TreeStore<ModelData> store) {
	if (provider == null) {
	    return null;
	}
	RpcProxy<ModelData, List<ModelData>> proxy = new RpcProxy<ModelData, List<ModelData>>() {
	    

	    @Override
	    public void load(ModelData loadConfig, AsyncCallback<List<ModelData>> callback) {
		
		Object parent = GXTHelper.getBean(loadConfig);
		List<?> children =  parent == null ? provider.getList() : provider.getChildren(parent);
	
		List<ModelData> result = new ArrayList<ModelData>();
		if (children == null) {
		    callback.onSuccess(result);
		    return;
		}
		
		for (Object data : children) {
		    // Create wrap of data
		    ModelData model = createModel(data);
		    result.add(model);
		}
		
		callback.onSuccess(result);
	    }
	};

	TreeLoader<ModelData> loader = new TreeLoader<ModelData>(proxy) {
	    
	    @Override
	    public boolean hasChildren(ModelData parent) {
		Object p = GXTHelper.getBean(parent);
		return p == null ? false : provider.hasChildren(p);
	    }
	};
	
	// IMPORTANT! Initialize Load Handler. Very critical line!
	loader.addLoadHandler(new ChildTreeStoreBinding<ModelData>(store));
	
	
	return loader;
	

    }
}
