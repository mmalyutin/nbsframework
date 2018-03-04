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
package org.plazmaforge.framework.uwt.jfx.widget;



import org.plazmaforge.framework.uwt.jfx.layout.XLayout;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 * 
 * @author ohapon
 *
 */
public class XLayoutContainer extends XFitPanel {
    
    private XContainerWrapper containerWrapper;
    
    private XLayout layout;

    public XLayoutContainer() {
        this(null, null);
    }
   
    public XLayoutContainer(Pane container) {
	this(container, null);
    }
    
    public XLayoutContainer(Pane container, XLayout layout) {
	super();
	
	initContainer(container);
	this.layout = layout;
	//doLayout();
    }

    protected void initContainer(Pane container) {
	if (container == null) {
	    container = createDefaultContainer();
	}
	containerWrapper = new XContainerWrapper(container);
	
	// Add container
	super.addChild(container);
    }

    public ObservableList<Node> getContainerChildren() {
        return containerWrapper.getChildren();
    }
    
    public XLayout getLayout() {
        return layout;
    }
   

    //////////////////////////////////////////////////////////////////////////////////
    // CONTAINER METHODS
    //////////////////////////////////////////////////////////////////////////////////
    

    @Override
    public void addChild(Node child) {
	containerWrapper.addChild(child);
	containerWrapper.getContainer().layout();
    }
 
    @Override
    public void removeChild(Node child) {
	containerWrapper.removeChild(child);
    }
 
    @Override
    public void removeAll() {
	containerWrapper.removeAll();
    }
 
    @Override
    public boolean hasChildren() {
	return containerWrapper.hasChildren();
    }
 
    @Override
    public int getChildrenCount() {
	return containerWrapper.getChildrenCount();
    }
 
    @Override
    public Node getChild(int index) {
	return containerWrapper.getChild(index);
    }   
    
    //////////////////////////////////////////////////////////////////////////////////
    
    
    protected Pane createDefaultContainer() {
	return new FlowPane();
	//return new VBox();
    }

}
