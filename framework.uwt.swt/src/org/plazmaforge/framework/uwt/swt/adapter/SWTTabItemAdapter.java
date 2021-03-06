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
import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.widget.panel.TabItem;

/**
 * 
 * @author ohapon
 *
 */

public class SWTTabItemAdapter extends SWTContainerAdapter {

    public Object createDelegate(UIElement parent, UIElement element) {
   	org.eclipse.swt.widgets.TabFolder xParent = (org.eclipse.swt.widgets.TabFolder) getContent(parent.getDelegate());
   	org.eclipse.swt.widgets.TabItem xTabItem = new org.eclipse.swt.widgets.TabItem(xParent, SWT.BORDER);
   	TabItem tabItem = (TabItem) element;
   	
	// Get text
	String text = tabItem.getTitle();
	if (text != null) {
	    xTabItem.setText(text);
	}

	// Get icon
	org.eclipse.swt.graphics.Image xImage = createImage(element, tabItem.getIcon());
	if (xImage != null) {
	    xTabItem.setImage(xImage);
	}
   		
   	org.eclipse.swt.widgets.Composite xContent = createDefaultContent(xParent, SWT.BORDER);
    	//org.eclipse.swt.widgets.Composite xContent = new org.eclipse.swt.widgets.Composite(xParent, SWT.BORDER);
   	//xContent.setLayout(createDefaultCompositeLayout());
   	
   	xTabItem.setControl(xContent);
   	
   	//addToParent(xParent, xTabItem, element);
   	return xTabItem;
   }
    
    
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	org.eclipse.swt.widgets.TabItem xTabItem = (org.eclipse.swt.widgets.TabItem) element.getDelegate();
	if (xTabItem == null) {
	    return;
	}
	if (TabItem.PROPERTY_TITLE.equals(name)) {
	    xTabItem.setText(asString(value)); 
	    return;
	} else 	if (TabItem.PROPERTY_ICON.equals(name)) {
	    org.eclipse.swt.graphics.Image xImage = createImage(element, asImage(value));
	    if (xImage != null) {
		xTabItem.setImage(xImage);
	    }
	    return;
	} else if (TabItem.PROPERTY_ICON_PATH.equals(name)) {
	    org.eclipse.swt.graphics.Image xImage = createImage(element, asString(value));
	    if (xImage != null) {
		xTabItem.setImage(xImage);
	    }
	    return;
	}	
	super.setProperty(element, name, value);
    }
    
    
    @Override
    public void checkDelegate(UIElement element) {
 	// clear super method
    }

}
