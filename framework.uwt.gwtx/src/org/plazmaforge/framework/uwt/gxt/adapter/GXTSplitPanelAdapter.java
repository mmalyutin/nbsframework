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

package org.plazmaforge.framework.uwt.gxt.adapter;

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.gxt.widget.XSplitPanel;
import org.plazmaforge.framework.uwt.widget.panel.SplitPanel;

import com.sencha.gxt.widget.core.client.container.Container;

/**
 * 
 * @author ohapon
 *
 */
public class GXTSplitPanelAdapter extends GXTContainerAdapter {

    public Object createDelegate(UIElement parent, UIElement element) {
	XSplitPanel xSplitPanel = new XSplitPanel();

	// TODO: STUB
	if (parent == null) {
	    return xSplitPanel;
	}

	addChild(getContent(parent.getDelegate()), xSplitPanel, element);
	return xSplitPanel;
    }

  
//    
//    @Override
//    public void setProperty(UIObject element, String name, Object value) {
//	
//	Object delegate = element.getDelegate();
//	if (delegate == null) {
//	    return;
//	}
//	
//	if (delegate instanceof LayoutContainer) {
//	    LayoutContainer container = (LayoutContainer) delegate;
//	    if (SplitPanel.PROPERTY_LAYOUT.equals(name)) {
//		//ignore
//		return;
//	    }
//	}
//	
//	super.setProperty(element, name, value);
//    }
}
