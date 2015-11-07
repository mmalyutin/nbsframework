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

package org.plazmaforge.framework.uwt.widget;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.util.CoreUtils;


public class Composite extends Control {
    
    //////////////////////////////////////////////////////////////////////
    // PROPERTIES
    //////////////////////////////////////////////////////////////////////

    public static final String PROPERTY_CHILDREN = "children";
    
    public static final String PROPERTY_CONTENT = "content";

    
    //////////////////////////////////////////////////////////////////////
    // METHODS
    //////////////////////////////////////////////////////////////////////

    public static final String METHOD_LAYOUT = "layout";
    
    
    
    
    private Layout layout;
    
    private List<Widget> children = new ArrayList<Widget>();

    
    protected List<Widget> getInitChildren(String key) {
	
	List<Widget> children = (List<Widget>) getInitProperty(key);
	if (children == null){
	    children = new ArrayList<Widget>();
	    setInitProperty(key, children);
	}
	return children;
    }
    
    protected boolean isChildrenProperty(String name) {
	return PROPERTY_CHILDREN.equals(name);
    }
    
 
    public void add(Widget element) {
	if (element == null) {
	    // TODO: Exception
	    return;
	}
	
	checkAddChild(element);
	
	children.add(element);
	element.setParent(this);
	
	fireAddChild(getInitChildren(PROPERTY_CHILDREN), element);
    }

    public void add(Widget element, Object layoutData) {
	if (element == null) {
	    // TODO: Exception
	    return;
	}
	
	checkAddChild(element);
		
	if (element instanceof Control) {
	    ((Control) element).setLayoutData(layoutData);
	}
	
	children.add(element);
	element.setParent(this);
	
	fireAddChild(getInitChildren(PROPERTY_CHILDREN), element);
    }

    public void remove(Widget element) {
	if (element == null) {
	    // TODO: Exception
	    return;
	}
	
	checkRemoveChild(element);
	
	children.remove(element);
	element.setParent(null);
	
	fireRemoveChild(getInitChildren(PROPERTY_CHILDREN), element);
    }

    ////
    
    public void removeAll() {
	// REMOVE ALL CHILDREN
	// TODO: Must implements
    }
    
    protected void checkAddChild(Widget element) {
	checkChild(element);
    }
    
    protected void checkRemoveChild(Widget element) {
	checkChild(element);
    }

    protected void checkChild(Widget element) {
	
    }
    
    protected void fireAddChild(List<Widget> initChildren, Widget element) {
	if (!hasDelegate()) {
	    initChildren.add(element);
	    return;
	}
	addDelegateChild(element);
    }


    protected void fireRemoveChild(List<Widget> initChildren, Widget element) {
	if (!hasDelegate()) {
	    initChildren.remove(element);
	    return;
	}
	removeDelegateChild(element);
    }

    ////
    protected void initDelegate() {
	
	// We use priority by time
	initDelegateProperties();
    }

    protected void initDelegateProperty(String name, Object value) {
	if (isChildrenProperty(name) ) {
	    List<Widget> initChildren = (List<Widget>) getInitProperty(name);
	    initDelegateChildren(initChildren);
	    return;
	}
	super.initDelegateProperty(name, value); // 2012-11-23 MF
    }
    
    protected void initDelegateChildren(List<Widget> initChildren) {
	if (!hasDelegate() || initChildren.isEmpty()) {
	    return;
	}
	for (Widget element : initChildren) {
	    addDelegateChild(element);
	}
	initChildren.clear();
    }
    
    protected void addDelegateChild(Widget element) {
	if (element == null) {
	    return;
	}
	element.activateUI();
    }

    protected void removeDelegateChild(Widget element) {
	if (element == null) {
	    return;
	}
	element.deactivateUI();
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
	if (this.layout != null) {
	    this.layout.setOwner(null); // RESET OWNER
	}
        this.layout = layout;
        this.layout.setOwner(this); // SET OWNER
        
        // Reset incompatible layout data of children
        /*
        if (layout != null && hasChildren()) {
            for (Widget child: children) {
        	if (child instanceof Control) {
        	    Control control = (Control) child;
        	    Object layoutData = control.getLayoutData();
        	    if (layoutData != null) {
        		if (!layout.isCompatible(layoutData)) {
        		    control.setLayoutData(null);
        		}
        	    }
        	}
            }
        }
        */
        
        fireChangeProperty(PROPERTY_LAYOUT, layout);
    }
    
    public int getChildrenCount() {
	return children == null ? 0 : children.size();
    }

    public int indexOf(Widget widget) {
	return (widget == null || children == null) ? -1 : children.indexOf(widget);
    }
    
    protected boolean isValidChildIndex(int index) {
	return children != null && index > -1 && index < children.size(); 
    }
    
    public List<Object> getChildrenLayoutData() {
	if  (children == null || children.isEmpty()) { 
	    return new ArrayList<Object>();
	}
	List<Object> data = new ArrayList<Object>();
	for (int i = 0; i < children.size(); i++) {
	    Widget w = children.get(i);
	    if (w instanceof Control) {
		data.add(((Control) w).getLayoutData());
	    }
	}
	return data;
    }

    public List<Widget> getChildren() {
	return CoreUtils.cloneList(children);
    }

    public boolean hasChildren() {
	return children != null && !children.isEmpty();
    }
    
    /**
     * Layout container (Update Layout Manager)
     */
    public void layout() {
	if (getAdapter() == null) {
	    return;
	}
	invokeDelegate(this, METHOD_LAYOUT, null);
    }

    /**
     * Return child element by id
     * @param id
     * @return
     */
    public Widget getElementById(String id) {
	if (id == null || !hasChildren()) {
	    return null;
	}
	for (Widget element: children) {
	    if (id.equals(element.getId())) {
		return element; 
	    }
	}
	return null;
    }

    /**
     * Return child element by name
     * @param name
     * @return
     */
    public Widget getElementByName(String name) {
	if (name == null || !hasChildren()) {
	    return null;
	}
	for (Widget element: children) {
	    if (name.equals(element.getName())) {
		return element; 
	    }
	}
	return null;
    }
    
    /**
     * Return child element by index
     * @param index
     * @return
     */
    public Widget getElementByIndex(int index) {
	if (!hasChildren()) {
	    return null;
	}
	return children.get(index); 
    }


}
