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

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.jfx.widget.XGroupPanel;
import org.plazmaforge.framework.uwt.widget.Layout;
import org.plazmaforge.framework.uwt.widget.panel.GroupPanel;

/**
 * 
 * @author ohapon
 *
 */
public class JFXGroupPanelAdapter extends JFXPanelAdapter {

    public Object createDelegate(UIElement parent, UIElement element) {
	GroupPanel groupPanel = (GroupPanel) element;
	XGroupPanel xGroupPanel = new XGroupPanel();
	
	xGroupPanel.setText(asSafeString(groupPanel.getTitle()));
	
	Layout layout = groupPanel.getLayout();
	
	// Default implementation with special container wrapper
	// Create internal content by layout
	javafx.scene.Parent content = createLayoutContainer(layout);
	xGroupPanel.setContent(content);
		
	addChild(getContent(parent.getDelegate()), xGroupPanel, element);
	return xGroupPanel;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	XGroupPanel xGroupPanel = (XGroupPanel) element.getDelegate();
	if (xGroupPanel == null) {
	    return;
	}
	
	if (GroupPanel.PROPERTY_TITLE.equals(name)) {
	    xGroupPanel.setText(asSafeString(value));
	    return;
	}
	
	super.setProperty(element, name, value);
    }


}
