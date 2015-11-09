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

package org.plazmaforge.framework.uwt.swt.adapter;

import org.eclipse.swt.SWT;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Layout;

public class SWTCompositeAdapter extends SWTControlAdapter {


    public Object createDelegate(UIObject parent, UIObject element) {
	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	org.eclipse.swt.widgets.Composite xComposite = new org.eclipse.swt.widgets.Composite(xParent, SWT.NONE);
	//xComposite.setBackgroundMode(SWT.INHERIT_DEFAULT);
	addToParent(xParent, xComposite, element);
	return xComposite;
    }

    /**
     * Return SWT Composite
     * @param delegate
     * @return
     */
    protected org.eclipse.swt.widgets.Composite getComposite(Object delegate) {
   	return (org.eclipse.swt.widgets.Composite) delegate;
    }
    
    /**
     * Create default layout of composite
     * @return
     */
    protected org.eclipse.swt.widgets.Layout createDefaultCompositeLayout() {
	return new org.eclipse.swt.layout.RowLayout();
    }
    
    /**
     * Create default content
     * @param xParent
     * @return
     */
    protected org.eclipse.swt.widgets.Composite createDefaultContent(org.eclipse.swt.widgets.Composite xParent, int style) {
	org.eclipse.swt.widgets.Composite xContent = new org.eclipse.swt.widgets.Composite(xParent, style);
	xContent.setLayout(createDefaultCompositeLayout());
	return xContent;
    }

    public void checkDelegate(UIObject element) {
	org.eclipse.swt.widgets.Composite composite = getComposite(element.getDelegate());
	checkLayout(composite);
    }
    
    /**
     * Check layout.
     * If layout is null set default layout
     * @param element
     */
    public void checkLayout(org.eclipse.swt.widgets.Composite element) {
	if (element == null) {
	    return;
	}
	if (element.getLayout() == null) {
	    element.setLayout(createDefaultCompositeLayout());
	}
    }


    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	org.eclipse.swt.widgets.Composite composite = getContent(element.getDelegate());
	if (composite == null) {
	    return;
	}
	
	if (Composite.PROPERTY_LAYOUT.equals(name)) {
	    Layout layout = (Layout) value;
	    
	    if (layout == null) {
		composite.setLayout(null);
		return;
		
	    }
	    
	    layout.activateUI();
	    
	    org.eclipse.swt.widgets.Layout xLayout = (org.eclipse.swt.widgets.Layout) layout.getDelegate();
	    
	    // Reset incompatible layout data of children
	    /*
	    org.eclipse.swt.widgets.Control[] xControls = composite.getChildren();
	    for (org.eclipse.swt.widgets.Control xControl: xControls) {
		Object xLayoutData = xControl.getLayoutData();
		if (xLayoutData != null) {
		    if (!SWTLayoutUtils.isCompatible(xLayout, xLayoutData)) {
			xControl.setLayoutData(null);
		    }
		}
	    }
	    */
	    
	    composite.setLayout(xLayout);
	    return;
	}
	
	super.setProperty(element, name, value);
    }
    
    
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	org.eclipse.swt.widgets.Composite composite = getContent(element.getDelegate());
	if (composite == null) {
	    return null;
	}

	if (Composite.METHOD_LAYOUT.equals(methodName)) {
	    composite.layout();
	    return null;
	}
	return super.invoke(element, methodName, args);
    }

    
    protected int getSplitOrientation(Orientation orientation) {
	return getOrientation(orientation);
    }

    /**
     * Return SWT orientation by SWT orientation
     * @param orientation
     * @return
     */
    protected int getOrientation(Orientation orientation) {
	if (orientation == null || orientation.equals(Orientation.HORIZONTAL)) {
	    return SWT.HORIZONTAL;
	}
	return SWT.VERTICAL;
    }

}
