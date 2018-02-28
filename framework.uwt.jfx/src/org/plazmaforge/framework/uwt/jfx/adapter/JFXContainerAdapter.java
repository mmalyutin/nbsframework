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

package org.plazmaforge.framework.uwt.jfx.adapter;


import org.plazmaforge.framework.uwt.UIAdapter;
import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.jfx.layout.XLayout;
import org.plazmaforge.framework.uwt.jfx.widget.XLayoutContainer;
import org.plazmaforge.framework.uwt.widget.Container;
import org.plazmaforge.framework.uwt.widget.Layout;

/**
 * 
 * @author ohapon
 *
 */
public class JFXContainerAdapter extends JFXControlAdapter {


    public Object createDelegate(UIElement parent, UIElement element) {
	javafx.scene.Parent xParent = (javafx.scene.Parent) getContent(parent.getDelegate());
	
	Container container = (Container) element;
	Layout layout = container.getLayout();
	
	// Default implementation with special container wrapper
	// Create internal content by layout
	XLayoutContainer xContainer = createLayoutContainer(layout);
	
	addChild(xParent, xContainer, element);
	return xContainer;
    }
    
    protected XLayoutContainer createLayoutContainer(Layout layout) {
 	XLayout xLayout = getXLayout(layout);
 	javafx.scene.layout.Pane container = createContainer(layout, xLayout);
 	return new XLayoutContainer(container, xLayout);
     }
     
     protected javafx.scene.layout.Pane createContainer(Container container) {
 	Layout layout = container.getLayout();
 	XLayout xLayout = getXLayout(layout);
 	return createContainer(layout, xLayout);
     }
     
     protected javafx.scene.layout.Pane createContainer(Layout layout, XLayout xLayout) {
 	if (xLayout == null) {
 	    return null;
 	}
 	
 	// Get UIAdapter for Layout
 	UIAdapter adapter = getAdapter(layout.getClass());
 	if (adapter == null) {
 	    return null;
 	}
 	
 	// Check adapter class
 	if (!(adapter instanceof JFXLayoutAdapter)) {
 	    //TODO: warning
 	    return null;
 	}
 		
 	// Create container by JFXLayoutAdapter
 	javafx.scene.layout.Pane container = ((JFXLayoutAdapter) adapter).createContainer(xLayout);
 	
 	return container;
     }
     
     
    protected XLayout getXLayout(Layout layout) {
	if (layout == null) {
	    return null;
	}
	layout.activateUI();
	return (XLayout) layout.getDelegate();
    }   
    

//
//    public void checkDelegate(UIElement element) {
//	org.eclipse.swt.widgets.Composite composite = asComposite(element.getDelegate());
//	checkLayout(composite);
//    }
//    
//    /**
//     * Check layout.
//     * If layout is null set default layout
//     * @param element
//     */
//    public void checkLayout(org.eclipse.swt.widgets.Composite element) {
//	if (element == null) {
//	    return;
//	}
//	if (element.getLayout() == null) {
//	    element.setLayout(createDefaultCompositeLayout());
//	}
//    }
//
//
//    @Override
//    public void setProperty(UIElement element, String name, Object value) {
//	
//	org.eclipse.swt.widgets.Composite xContainer = getContent(element.getDelegate());
//	if (xContainer == null) {
//	    return;
//	}
//	
//	if (Container.PROPERTY_LAYOUT.equals(name)) {
//	    Layout layout = (Layout) value;
//	    
//	    if (layout == null) {
//		xContainer.setLayout(null);
//		return;
//		
//	    }
//	    
//	    layout.activateUI();
//	    
//	    org.eclipse.swt.widgets.Layout xLayout = (org.eclipse.swt.widgets.Layout) layout.getDelegate();
//	    
//	    // Reset incompatible layout data of children
//	    /*
//	    org.eclipse.swt.widgets.Control[] xControls = composite.getChildren();
//	    for (org.eclipse.swt.widgets.Control xControl: xControls) {
//		Object xLayoutData = xControl.getLayoutData();
//		if (xLayoutData != null) {
//		    if (!SWTLayoutUtils.isCompatible(xLayout, xLayoutData)) {
//			xControl.setLayoutData(null);
//		    }
//		}
//	    }
//	    */
//	    
//	    xContainer.setLayout(xLayout);
//	    return;
//	}
//	
//	super.setProperty(element, name, value);
//    }
//    
//    
//    @Override
//    public Object invoke(UIElement element, String methodName, Object[] args) {
//	org.eclipse.swt.widgets.Composite xContainer = getContent(element.getDelegate());
//	if (xContainer == null) {
//	    return null;
//	}
//
//	if (Container.METHOD_LAYOUT.equals(methodName)) {
//	    xContainer.layout();
//	    return null;
//	}
//	return super.invoke(element, methodName, args);
//    }
//
//    
//    protected int getSplitOrientation(Orientation orientation) {
//	return getOrientation(orientation);
//    }
//
//    /**
//     * Return SWT orientation by SWT orientation
//     * @param orientation
//     * @return
//     */
//    protected int getOrientation(Orientation orientation) {
//	if (orientation == null || orientation.equals(Orientation.HORIZONTAL)) {
//	    return SWT.HORIZONTAL;
//	}
//	return SWT.VERTICAL;
//    }

}
