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
import org.plazmaforge.framework.uwt.widget.Layout;
import org.plazmaforge.framework.uwt.widget.panel.TabItem;
import org.plazmaforge.framework.uwt.widget.panel.TitlePanel;


/**
 * 
 * @author ohapon
 *
 */
public class JFXTitlePanelAdapter extends JFXPanelAdapter {

    public Object createDelegate(UIElement parent, UIElement element) {
	TitlePanel titlePanel = (TitlePanel) element;
	
	javafx.scene.control.TitledPane xTitlePanel = createTitledPane(titlePanel);
	addChild(getContent(parent.getDelegate()), xTitlePanel, element);
	return xTitlePanel;
    }

    
    protected javafx.scene.control.TitledPane createTitledPane(TitlePanel titlePanel) {
	javafx.scene.control.TitledPane xTitlePanel = new javafx.scene.control.TitledPane();
	xTitlePanel.setCollapsible(false);
	
	// Get text
	if (titlePanel.getTitle() != null) {
	    xTitlePanel.setText(titlePanel.getTitle());
	}
	// Titled interface
	// Get icon
	//javafx.scene.image.ImageView xIcon = createImageView(titlePanel, titlePanel.getIcon());
	//if (xIcon != null) {
	//    xTitlePanel.setGraphic(xIcon);
	//}
	
	Layout layout = titlePanel.getLayout();
	
	// Default implementation with special container wrapper
	// Create internal content by layout
	javafx.scene.Parent content = createLayoutContainer(layout);
	xTitlePanel.setContent(content);
	
	
	titlePanel.resetInitProperty(TabItem.PROPERTY_TITLE);
	//titlePanel.resetInitProperty(TabItem.PROPERTY_ICON);
	//titlePanel.resetInitProperty(TabItem.PROPERTY_ICON_PATH);
	titlePanel.resetInitProperty(TabItem.PROPERTY_LAYOUT);
	
	return xTitlePanel;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	javafx.scene.control.TitledPane  xTitlePanel = (javafx.scene.control.TitledPane ) element.getDelegate();
	if (xTitlePanel == null) {
	    return;
	}
	
	if (TitlePanel.PROPERTY_TITLE.equals(name)) {
	    xTitlePanel.setText(asSafeString(value));
	    return;
	}
	
	super.setProperty(element, name, value);
    }


}
