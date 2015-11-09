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

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import org.plazmaforge.framework.core.data.Accessor;
import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.provider.TreeProvider;
import org.plazmaforge.framework.uwt.util.UWTUtils;
import org.plazmaforge.framework.uwt.widget.Viewer;
import org.plazmaforge.framework.uwt.widget.tree.Tree;
import org.plazmaforge.framework.uwt.widget.tree.TreeColumn;


public class SwingTreeModel extends DefaultTreeModel {

    private static final String EMPTY_STRING = "";
    
    private boolean editable;
    
    private TreeProvider provider;
    
    /**
     *  UWT Tree
     */
    private Tree tree;

    public SwingTreeModel(Tree tree) {
	super(new DefaultMutableTreeNode());
	this.tree = tree;
    }

    public SwingTreeModel(Tree tree, TreeProvider provider) {
	this(tree);
	this.provider = provider;
    }
    
    public void setProvider(TreeProvider provider) {
        this.provider = provider;
    }

    /**
     * Return UWT Tree
     * @return
     */
    public Tree getTree() {
        return tree;
    }

    private boolean hasColumns() {
   	return tree.hasColumns();
    }
       
    ////
    
    public boolean isCellEditable(int row, int column) {
	// TODO Use column configuration
        return isEditable();
    }

    ////
    
    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public void setDataList(List<Object> dataList) {

	DefaultMutableTreeNode root = getRootNode();
	root.removeAllChildren();
	
	populateNode(root, dataList);
	
        fireTreeStructureChanged(this, root.getPath(), new int[] {}, null);
    }
    
    protected XDefaultMutableTreeNode createNode(Object element) {
	
        // Get value of element
        Object value = getValue(element, 0);
        
        // Create node
        XDefaultMutableTreeNode node = new XDefaultMutableTreeNode(element);
        node.setValue(value);
        
        return node;
	
    }
    
    protected void populateNode(DefaultMutableTreeNode node, List<Object> dataList) {
	for (Object element : dataList) {
            
            // Create child node
            XDefaultMutableTreeNode child = createNode(element);
            node.add(child);
        }
    }

    protected void populateNode(XDefaultMutableTreeNode node, TreeProvider provider) {
	Object element = getElementOfNode(node); 
	if (!provider.hasChildren(element)) {
	    node.setLoad(true);
	    return;
	}
	node.setLoad(true);
	List<Object> dataList = provider.getChildren(element);
	populateNode(node, dataList);
    }

    protected DefaultMutableTreeNode getTreeNode(Object node) {
	return (DefaultMutableTreeNode) node;
    }

    protected DefaultMutableTreeNode getRootNode() {
	return getTreeNode(getRoot());
    }

    public void addNode(MutableTreeNode node) {
	DefaultMutableTreeNode root = (DefaultMutableTreeNode) getRoot();
	addNode(root, node);
    }

    public void addNode(MutableTreeNode parent, MutableTreeNode node) {
	DefaultMutableTreeNode parentNode = getTreeNode(parent == null ? getRoot() : parent);
	parentNode.add(node);
	fireTreeStructureChanged(this, parentNode.getPath(), new int[] {}, null);
    }

    protected Object getValue(Object element, int columnIndex) {
	if (element == null) {
	     return EMPTY_STRING;
	}
	
	Object value = null;
	String text = null;
	
	if (!hasColumns()) {
	    
	    // Get property
	    String property = tree.getDisplayProperty();

	    // Get PropertyProvider
	    PropertyProvider propertyProvider = tree.getPropertyProvider();
	    if (propertyProvider != null) {
		// By PropertyProvider
		value = propertyProvider.getValue(element, property);
	    } else {
		// By Accessor
		Accessor accessor = getAccessor(element, property, tree);
		value = getValue(element, accessor);
	    }

	    if (value != null) {
		text = value.toString();
	    }

	}

	if (text == null) {
	    text = EMPTY_STRING;
	}
		
	// TODO: 
	// Must analyze CellRenderer
	
	return text; 
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected Object getElementOfNode(Object nodeObj) {
	DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodeObj;
	return node.getUserObject();
    }
    
    protected void checkLoad(Object nodeObj) {
	
	// Dynamic load use only for TreeDataProvider mode
	if (provider == null) {
	    return;
	}
	
	// Dynamic load use only for XDefaultMutableTreeNode
	if (!(nodeObj instanceof XDefaultMutableTreeNode)) {
	    return;
	}
	// If children are loaded then return
	XDefaultMutableTreeNode xNode = (XDefaultMutableTreeNode) nodeObj;
	if (xNode.isLoad()) {
	    return;
	}
	
	// Dynamic populate 
	populateNode(xNode, provider);

    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public Object getChild(Object parent, int index) {
	return super.getChild(parent, index);
    }

    public int getChildCount(Object parent) {
	return super.getChildCount(parent);
    }

    public boolean isLeaf(Object node) {
	checkLoad(node);
	return super.isLeaf(node);
    }

    public void valueForPathChanged(TreePath path, Object newValue) {
	super.valueForPathChanged(path, newValue);
    }

    public int getIndexOfChild(Object parent, Object child) {
	return super.getIndexOfChild(parent, child);
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected Accessor getAccessor(Object obj, TreeColumn column) {
	return UWTUtils.getAccessor(obj, column);
    }
    
    protected Accessor getAccessor(Object obj, String property) {
   	return UWTUtils.getAccessor(obj, property);
    }

    protected Accessor getAccessor(Object obj, String property, Viewer viewer) {
 	return UWTUtils.getAccessor(obj, property, viewer);
    }    
    
    protected Accessor createAccessor(Class entityClass, String property) {
	return Accessor.getAccessor(entityClass, property);
    }
    
    protected Object getValue(Object obj, Accessor accessor) {
	return UWTUtils.getValue(obj, accessor);
    }
    
    protected String getTextValue(Object value, TreeColumn column) {
	return UWTUtils.getTextValue(value, column);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    class XDefaultMutableTreeNode extends DefaultMutableTreeNode {

	// Load flag
	private boolean load;
	
	// Value of element of tree item
	private Object value;

	
	public XDefaultMutableTreeNode() {
	    super();
	}

	public XDefaultMutableTreeNode(Object userObject, boolean allowsChildren) {
	    super(userObject, allowsChildren);
	}

	public XDefaultMutableTreeNode(Object userObject) {
	    super(userObject);
	}

	public Object getValue() {
	    return value;
	}

	public void setValue(Object value) {
	    this.value = value;
	}

	public String toString() {
	    if (value == null) {
		return null;
	    } else {
		return value.toString();
	    }
	}

	public boolean isLoad() {
	    return load;
	}

	public void setLoad(boolean load) {
	    this.load = load;
	}

	
    }
}
