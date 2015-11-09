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

/**
 * 
 */
package org.plazmaforge.framework.uwt.swt.adapter;

import org.eclipse.swt.SWT;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.panel.ScrollPanel;

/**
 * @author ohapon
 *
 */
public class SWTScrollPanelAdapter extends SWTPanelAdapter {
    
    public Object createDelegate(UIObject parent, UIObject element) {
	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	org.eclipse.swt.custom.ScrolledComposite xComposite = new org.eclipse.swt.custom.ScrolledComposite(xParent, SWT.NONE | SWT.H_SCROLL | SWT.V_SCROLL);
   	org.eclipse.swt.widgets.Composite xContent = createDefaultContent(xComposite, SWT.BORDER);
   	
   	//xContent.setLayout(null);
   	
   	//xContent.setSize(500, 500); // TODO
   	xComposite.setContent(xContent);

	//xComposite.setBackgroundMode(SWT.INHERIT_DEFAULT);
	addToParent(xParent, xComposite, element);
	return xComposite;
    }

    @Override
    public void setProperty(UIObject element, String name, Object value) {
	Object delegate = element.getDelegate();
	org.eclipse.swt.widgets.Control xControl = getControl(delegate);
	if (xControl == null) {
	    return;
	}
	if (ScrollPanel.PROPERTY_CONTENT_WIDTH.equals(name)) {
	    xControl = getContent(delegate); // CONTENT
	    org.eclipse.swt.graphics.Point point = xControl.getSize();
	    point.x = (Integer) value;
	    xControl.setSize(point);
	    return;
	} else if (ScrollPanel.PROPERTY_CONTENT_HEIGHT.equals(name)) {
	    xControl = getContent(delegate); // CONTENT
	    org.eclipse.swt.graphics.Point point = xControl.getSize();
	    point.y = (Integer) value;
	    xControl.setSize(point);
	    return;
	}
	
	super.setProperty(element, name, value);

    }
}
