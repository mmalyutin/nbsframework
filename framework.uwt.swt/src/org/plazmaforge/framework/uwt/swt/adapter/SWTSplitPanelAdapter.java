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

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.widget.Container;
import org.plazmaforge.framework.uwt.widget.panel.SplitPanel;

public class SWTSplitPanelAdapter extends SWTContainerAdapter {

    @Override
    public Object createDelegate(UIElement parent, UIElement element) {
	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	SplitPanel p = (SplitPanel) element; 
	int style = getSplitOrientation(p.getOrientation());
	org.eclipse.swt.custom.SashForm xSplitPanel = new org.eclipse.swt.custom.SashForm(xParent, style);
	addChild(xParent, xSplitPanel, element);
	return xSplitPanel;
    }
    
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	org.eclipse.swt.widgets.Composite xComposite = (org.eclipse.swt.widgets.Composite) element.getDelegate();
	if (xComposite == null) {
	    return;
	}
	if (Container.PROPERTY_LAYOUT.equals(name)) {
	    // ignore
	    return;
	}
	super.setProperty(element, name, value);
    }
}
