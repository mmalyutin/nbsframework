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

package org.plazmaforge.framework.uwt.gxt.widget;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.plazmaforge.framework.uwt.gxt.layout.XLayout;
import org.plazmaforge.framework.uwt.gxt.util.GXTUtils;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.ui.HasWidgets;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Size;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;


/**
 * 
 * @author ohapon
 *
 */
public class XLayoutContainer extends SimpleContainer  implements HasComputeSize {
    
    private String id;
    
    private HasWidgets container;
    
    private XLayout layout;
    
    //private int shiftX;
    //private int shiftY;    
    //private boolean absolutePosition;
    
      
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public XLayoutContainer() {
        this(null, null);
    }
   
    public XLayoutContainer(HasWidgets container) {
	this(container, null);
    }
    
    public XLayoutContainer(HasWidgets container, XLayout layout) {
	super();
	
	//Stub solution
	//getElement().getStyle().setOverflow(Overflow.VISIBLE);

	initContainer(container);
	this.layout = layout;
	doLayout();
    }
    
    //public int getShiftX() {
    //    return shiftX;
    //}
    //public void setShiftX(int shiftX) {
    //    this.shiftX = shiftX;
    //}
    //public int getShiftY() {
    //    return shiftY;
    //}
    //public void setShiftY(int shiftY) {
    //    this.shiftY = shiftY;
    //}
    //public boolean isAbsolutePosition() {
    //    return absolutePosition;
    //}

    public void setAbsolutePosition() {
        //this.absolutePosition = absolutePosition;
        // FIX: TabItem.layout - enable absolute position
        getElement().getStyle().setPosition(Position.ABSOLUTE);
    }

    protected void initContainer(HasWidgets container) {
	if (container == null) {
	    container =  createDefaultContainer();
	}
	this.container = container;
	setWidget((Widget) container);
    }
    
    @Override
    public void add(Widget child) {
	container.add(child);
    }

    @Override
    public boolean remove(Widget child) {
	return container.remove(child);
    }
    
    protected HasWidgets createDefaultContainer() {
        return new FlowLayoutContainer();
    }

    public void setContainer(HasWidgets container) {
        List<Widget> children = getContainerChildren();
        
        // Remove children from old container
        resetContainerChildren(children);

        // Set new container 
        initContainer(container);
        
        // Add children to new container
        addContainerChildren(children);
        
        doLayout();
    }
  
    public HasWidgets getContainer() {
        return container;
    }

    public XLayout getLayout() {
        return layout;
    }

    public void setLayout(XLayout layout) {
        this.layout = layout;
    }

    protected List<Widget> getContainerChildren() {
        Iterator<Widget> widgets = container.iterator();
        List<Widget> children = new ArrayList<Widget>();
        while (widgets.hasNext()) {
            children.add(widgets.next());
        }
        return children.isEmpty() ? null : children;
    }
    
    protected void resetContainerChildren(List<Widget> children) {
        if (children == null) {
            return;
        }
        for (Widget child : children) {
            child.setLayoutData(null);
            container.remove(child);
        }       
    }
   
    protected void addContainerChildren(List<Widget> children) {
        if (children == null) {
            return;
        }

        //TODO: ONLY-FOR-TEST
        //if (container instanceof BorderLayoutContainer) {
        //    BorderLayoutContainer b = (BorderLayoutContainer) container;
        //    b.setNorthWidget(testLabel);
        //    b.setCenterWidget(layoutButton);
        //    b.setSouthWidget(layoutLabel);
        //    return;
        //}

        for (Widget child : children) {
            container.add(child);           
        }
    }

    protected Size getOffsetSize() {
	return GXTUtils.getOffsetSize(this);
    }
    
    public Size computeSize(int hWidth, int hHeight, boolean layout) {
	if (container == null || !(container instanceof XGridLayoutContainer)) {
	    return getOffsetSize();
	}
	return ((XGridLayoutContainer) container).computeSize(hWidth, hHeight, false);
    }
    
}