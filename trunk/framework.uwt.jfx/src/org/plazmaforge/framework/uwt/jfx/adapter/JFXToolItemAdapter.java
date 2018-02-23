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
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.tool.ToolItem;


/**
 * 
 * @author ohapon
 *
 */
public class JFXToolItemAdapter extends JFXControlAdapter {

    public Object createDelegate(UIElement parent, UIElement element) {
	
	
	javafx.scene.control.ToolBar xParent = (javafx.scene.control.ToolBar) parent.getDelegate();
	
	//TODO: ToolItem can be not only Button
	javafx.scene.control.Button xButton = new javafx.scene.control.Button();
	
	ToolItem toolItem = (ToolItem) element;
	
	// Get text
	String text = toolItem.getText();
	if (text != null) {
	    xButton.setText(text);
	}
	
	// Get icon
	javafx.scene.image.ImageView xIcon = createImageView(toolItem, toolItem.getIcon());
	if (xIcon != null) {
	    xButton.setGraphic(xIcon);
	}
	xParent.getItems().add(xButton); // Add to parent
	return xButton;
    }

    

    ////
    
    protected javafx.scene.control.ButtonBase asButton(Object delegate) {
	return (javafx.scene.control.ButtonBase) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	//TODO: ToolItem can be not only Button
	
	javafx.scene.control.ButtonBase xButton = asButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}
	if (eq(name, ToolItem.PROPERTY_TEXT)) {
	    xButton.setText(asSafeString(value));
	    return;
	} else if (eq(name, ToolItem.PROPERTY_ICON)) {
	    javafx.scene.image.ImageView xIcon = createImageView(element, asImage(value));
	    if (xIcon != null) {
		xButton.setGraphic(xIcon);
	    }
	    return;
	} else if (eq(name, ToolItem.PROPERTY_ICON_PATH)) {
	    javafx.scene.image.ImageView xIcon = createImageView(element, asString(value));
	    if (xIcon != null) {
		xButton.setGraphic(xIcon);
	    }
	    return;
	} else if (eq(name, ToolItem.PROPERTY_TOOL_TIP)) {
	    //TODO
	    //xButton.setToolTip(asSafeString(value));
	    return;
	}

	
	super.setProperty(element, name, value);
	
    }

    
    @Override
    protected void addSelectionListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	//TODO
    }

    @Override
    protected void removeSelectionListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	//TODO
    }
    
    
    @Override
    public void addListener(UIElement element, String eventType, Listener listener) {
	Widget widget = (Widget) element;
	//TODO: ToolItem can be not only Button
	javafx.scene.control.ButtonBase xButton = asButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    addSelectionListener(xButton, widget, listener);
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }

    @Override
    public void removeListener(UIElement element, String eventType, Listener listener) {
	Widget widget = (Widget) element;
	//TODO: ToolItem can be not only Button
	javafx.scene.control.ButtonBase xButton = asButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    removeSelectionListener(xButton, widget, listener);
	    return;
	} 
	
	super.removeListener(element, eventType, listener);
    }

}
