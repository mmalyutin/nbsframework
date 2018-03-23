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
package org.plazmaforge.framework.uwt.jfx.adapter.viewer;

import java.util.List;
import org.plazmaforge.framework.core.data.provider.TreeProvider;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;

/**
 * 
 * @author ohapon
 *
 * @param <T>
 */
public class JFXTreeItem<T> extends TreeItem<T> {

    private boolean load;
	
    private boolean isLeaf;
    
    /**
     * UWT TreeProvider
     */
    private TreeProvider treeProvider;
    
    
    public JFXTreeItem() {
	super();
    }

    public JFXTreeItem(T value, Node graphic) {
	super(value, graphic);
    }

    public JFXTreeItem(T value) {
	super(value);
    }
    
    //[UTIL]
    /**
     * Populates parent item
     * @param parent
     * @param dataList
     */
    public static <T> void populateItem(JFXTreeItem<T> parent, List<T> dataList) {
	if (dataList == null) {
	    return;
	}
	JFXTreeItem<T> item = null;
	for (T e : dataList) {
	    item = new JFXTreeItem<T>(e);
	    parent.addItem(item);
	}
    }
    
    //[UTIL]
    /**
     * Returns root of item
     * @param item
     * @return
     */
    public static <T> TreeItem<T> getRoot(TreeItem<T> item) {
	if (item == null) {
	    return null;
	}
	TreeItem<T> parent = item.getParent();
	if (parent == null) {
	    return item;
	}
	return getRoot(parent);
    }
    
    /**
     * Return root of this item
     * @return
     */
    public TreeItem<T> getRoot() {
	return getRoot(this);
    }    
    
    /**
     * Sets UWT TreeProvider
     * @param provider
     */
    public void setTreeProvider(TreeProvider provider) {
        this.treeProvider = provider;
    }

    /**
     * Gets UWT TreeProvider (own or root)
     * @return
     */
    protected TreeProvider getTreeProvider() {
	if (treeProvider != null) {
	    return treeProvider;
	}
	TreeItem<T> root = getRoot();
	if (root == null) {
	    return null;
	}
	if (root instanceof JFXTreeItem) {
	    return ((JFXTreeItem) root).getOwnTreeProvider();
	}
	return null;
    }
    
    /**
     * Gets own UWT TreeProvider
     * @return
     */
    protected TreeProvider getOwnTreeProvider() {
	return treeProvider;
    }
    
    /**
     * Loads children
     */
    protected void loadChildren() {
	loadChildren(getTreeProvider());
    }
    
    /**
     * Loads children by UWT TreeProvider
     * @param provider
     */
    protected void loadChildren(TreeProvider provider) {
	load = true;
	if (provider == null) {
	    return;
	}
	T element = this.getValue(); 
	if (!provider.hasChildren(element)) {
	    return;
	}
	List<T> dataList = provider.getChildren(element);
	populateItem(this, dataList);
    }
    
    protected ObservableList<TreeItem<T>> doGetChildren() {
	return super.getChildren();
    }
    
    protected boolean hasChildren() {
	return !doGetChildren().isEmpty();
    }
    
    protected boolean isLoad() {
	return load || hasChildren();
    }
    
    ////
    
    public void addItem(TreeItem<T> item) {
	doGetChildren().add(item);
    }
    
    public void removeItem(TreeItem<T> item) {
	doGetChildren().remove(item);
    }
    
    ////
    
    @Override
    public ObservableList<TreeItem<T>> getChildren() {
	if (!isLoad()) {
	    loadChildren();
	}
	return super.getChildren();
    }

    @Override
    public boolean isLeaf() {
	if (!isLoad()) {
	    loadChildren();
	}
	return super.isLeaf();
   }

}
