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

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.menu.Menu;
import org.plazmaforge.framework.uwt.widget.panel.SplitPanel;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;


public abstract class GXTControlAdapter extends GXTWidgetAdapter {


    

    protected void addToParent(com.google.gwt.user.client.ui.Widget parent, com.google.gwt.user.client.ui.Widget widget, UIObject element) {
	
	
	
	if (parent instanceof com.extjs.gxt.ui.client.widget.LayoutContainer) {
	    
	    com.extjs.gxt.ui.client.widget.LayoutContainer layoutContainer = (com.extjs.gxt.ui.client.widget.LayoutContainer) parent; 
	    if (element != null) {
		UIObject p = element.getUIParent();
		if (p instanceof SplitPanel) {
		    SplitPanel splitPanel = (SplitPanel) p;
		    int count = splitPanel.getChildrenCount();
		    Orientation orientation = splitPanel.getOrientation();
		    if (orientation == null) {
			orientation = Orientation.HORIZONTAL;
		    }
		    if (count == 1) {
			// First element
			BorderLayoutData ld = new BorderLayoutData(orientation.equals(Orientation.HORIZONTAL) ? LayoutRegion.WEST : LayoutRegion.NORTH);
			ld.setSplit(true);
			//ld.setSize(200); // TODO
			widget.setLayoutData(ld);
			layoutContainer.add(widget, ld);
			return;
		    } else if (count == 2) {
			// Second element
			BorderLayoutData ld = new BorderLayoutData(LayoutRegion.CENTER);
			//ld.setSplit(true);
			//ld.setSize(200); // TODO
			widget.setLayoutData(ld);
			layoutContainer.add(widget, ld);
			return;
		    }
		}
	    }
	    layoutContainer.add(widget);
	} else if (parent instanceof com.google.gwt.user.client.ui.Panel) {
	    ((com.google.gwt.user.client.ui.Panel) parent).add(widget);
	} else {
	    // TODO: Must implement other parent type
	    throw new RuntimeException("Can not add widget to parent. Parent is not container");
	}
	
	
    }
    
    protected void removeFromParent(com.google.gwt.user.client.ui.Widget parent, com.google.gwt.user.client.ui.Widget widget) {
	if (parent instanceof com.extjs.gxt.ui.client.widget.LayoutContainer) {
	    ((com.extjs.gxt.ui.client.widget.LayoutContainer) parent).remove(widget);
	} else if (parent instanceof com.google.gwt.user.client.ui.Panel) {
	    ((com.google.gwt.user.client.ui.Panel) parent).remove(widget);
	} else {    
	    // TODO: Must implement other parent type
	    throw new RuntimeException("Can not remove widget from parent. Parent is not container");
	}
    }
    
    
    @Override
    public void disposeDelegate(UIObject parent, UIObject element) {
	com.google.gwt.user.client.ui.Widget  parentDelegate = (com.google.gwt.user.client.ui.Widget) getContent(parent.getDelegate());
	com.google.gwt.user.client.ui.Widget delegate = getWidget(element.getDelegate());
	removeFromParent(parentDelegate, delegate);
    }

    @Override
    public void setProperty(UIObject element, String name, Object value) {
	com.extjs.gxt.ui.client.widget.BoxComponent xControl = (com.extjs.gxt.ui.client.widget.BoxComponent) getComponent(element.getDelegate());
	if (xControl == null) {
	    return;
	}
	if (Control.PROPERTY_VISIBLE.equals(name)) {
	    xControl.setVisible(getBoolean(value));
	} else if (Control.PROPERTY_ENABLED.equals(name)) {
	    xControl.setEnabled(getBoolean(value));
	} else if (Control.PROPERTY_LAYOUT_DATA.equals(name)) {
	    UIObject layoutData = (UIObject) value;
	    layoutData.activateUI();
	    xControl.setLayoutData(layoutData.getDelegate());
	    return;
	} else if (Control.PROPERTY_WIDTH.equals(name)) {
	    Integer v = (Integer) value;
	    xControl.setWidth(v == null ? 0 : (v));
	    //xWidget.setStyleAttribute("width", v == null ? "0" : (v.toString() + ""));
	    return;
	} else if (Control.PROPERTY_HEIGHT.equals(name)) {
	    Integer v = (Integer) value;
	    xControl.setHeight(v == null ? "0" : (v.toString() + ""));
	    //xWidget.setStyleAttribute("height", v == null ? "0" : (v.toString() + ""));
	    return;
	} else if (Composite.PROPERTY_BACKGROUND.equals(name)) {
	    Color color = (Color) value;
	    String colorString = getColorString(color);
	    
	    
	    if (xControl instanceof com.extjs.gxt.ui.client.widget.ContentPanel) {
		
		//Special hard code to set style to content panel
		//TODO: Must optimize code
		com.extjs.gxt.ui.client.widget.ContentPanel contentPanel = (com.extjs.gxt.ui.client.widget.ContentPanel) xControl;
		String baseStyle = contentPanel.getBodyStyle();
		contentPanel.setBodyStyle(applyStyle(baseStyle, "background", colorString));
		
		return;
	    }
	    
	    xControl.setStyleAttribute("background", colorString);
	    return;
	} else if (Composite.PROPERTY_FOREGROUND.equals(name)) {
	    Color color = (Color) value;
	    String colorString = getColorString(color);
	    
	    
	    if (xControl instanceof com.extjs.gxt.ui.client.widget.ContentPanel) {
		
		//Special hard code to set style to content panel
		//TODO: Must optimize code
		com.extjs.gxt.ui.client.widget.ContentPanel contentPanel = (com.extjs.gxt.ui.client.widget.ContentPanel) xControl;
		String baseStyle = contentPanel.getBodyStyle();
		contentPanel.setBodyStyle(applyStyle(baseStyle, "color", colorString));
		
		return;
	    }
	    
	    xControl.setStyleAttribute("color", colorString);
	    return;	    
	} else if (Composite.PROPERTY_FONT.equals(name)) {
	    Font font = (Font) value;
	    String fontString = getFontString(font);
	    
	    
	    if (xControl instanceof com.extjs.gxt.ui.client.widget.ContentPanel) {
		
		//Special hard code to set style to content panel
		//TODO: Must optimize code
		com.extjs.gxt.ui.client.widget.ContentPanel contentPanel = (com.extjs.gxt.ui.client.widget.ContentPanel) xControl;
		String baseStyle = contentPanel.getBodyStyle();
		contentPanel.setBodyStyle(applyStyle(baseStyle, "font", fontString));
		
		return;
	    }
	    
	    xControl.setStyleAttribute("font", fontString);
	    return;	    
	} else if (Composite.PROPERTY_TOOL_TIP.equals(name)) {
	    xControl.setToolTip(getSafeString(value));
	    return;
	}  else if (Control.PROPERTY_CONTEXT_MENU.equals(name)) {
	    Menu menu = (Menu) value;
	    menu.activateUI();
	    xControl.setContextMenu((com.extjs.gxt.ui.client.widget.menu.Menu) menu.getDelegate());
	    return;
	}
	
	super.setProperty(element, name, value);
    }

   
    
    @Override
    public Object getProperty(UIObject element, String name) {
	
	com.extjs.gxt.ui.client.widget.BoxComponent xControl = (com.extjs.gxt.ui.client.widget.BoxComponent) getComponent(element.getDelegate());
	if (xControl == null) {
	    return null;
	}

	if (Control.PROPERTY_VISIBLE.equals(name)) {
	    return xControl.isVisible();
	} else if (Control.PROPERTY_ENABLED.equals(name)) {
	    return xControl.isEnabled();
	}
	
	return super.getProperty(element, name);
    }

    // KEY DOWN
    @Override
    protected void addKeyDownListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.addListener(com.extjs.gxt.ui.client.event.Events.OnKeyDown, createListener(widget, listener));
    }

    @Override
    protected void removeKeyDownListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.removeListener(com.extjs.gxt.ui.client.event.Events.OnKeyDown, getListener(widget, listener));
    }

    
    
    // KEY UP
    @Override
    protected void addKeyUpListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.addListener(com.extjs.gxt.ui.client.event.Events.OnKeyUp, createListener(widget, listener));
    }

    @Override
    protected void removeKeyUpListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.removeListener(com.extjs.gxt.ui.client.event.Events.OnKeyUp, getListener(widget, listener));
    }

    
    
    // MOUSE DOWN
    @Override
    protected void addMouseDownListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.addListener(com.extjs.gxt.ui.client.event.Events.OnMouseDown, createListener(widget, listener));
    }

    @Override
    protected void removeMouseDownListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.removeListener(com.extjs.gxt.ui.client.event.Events.OnMouseDown, getListener(widget, listener));
    }

    
    
    // MOUSE UP
    @Override
    protected void addMouseUpListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.addListener(com.extjs.gxt.ui.client.event.Events.OnMouseUp, createListener(widget, listener));
    }
    
    @Override
    protected void removeMouseUpListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.removeListener(com.extjs.gxt.ui.client.event.Events.OnMouseUp, getListener(widget, listener));
    }
    


    // MOUSE CLICK
    @Override
    protected void addMouseClickListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.addListener(com.extjs.gxt.ui.client.event.Events.OnClick, createListener(widget, listener));
    }

    @Override
    protected void removeMouseClickListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.removeListener(com.extjs.gxt.ui.client.event.Events.OnClick, getListener(widget, listener));
    }
    
    
    
    // MOUSE DOUBLE CLICK
    @Override
    protected void addMouseDoubleClickListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.addListener(com.extjs.gxt.ui.client.event.Events.OnDoubleClick, createListener(widget, listener));
    }

    @Override
    protected void removeMouseDoubleClickListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.removeListener(com.extjs.gxt.ui.client.event.Events.OnDoubleClick, getListener(widget, listener));
    }
    
    
    
    // MOUSE MOVE
    @Override
    protected void addMouseMoveListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.addListener(com.extjs.gxt.ui.client.event.Events.OnMouseMove, createListener(widget, listener));
    }

    @Override
    protected void removeMouseMoveListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.removeListener(com.extjs.gxt.ui.client.event.Events.OnMouseMove, getListener(widget, listener));
    }
    
    

    // MOUSE IN
    @Override
    protected void addMouseInListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.addListener(com.extjs.gxt.ui.client.event.Events.OnMouseOver, createListener(widget, listener));
    }

    @Override
    protected void removeMouseInListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.removeListener(com.extjs.gxt.ui.client.event.Events.OnMouseOver, getListener(widget, listener));
    }

    
    
    // MOUSE OUT
    @Override
    protected void addMouseOutListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.addListener(com.extjs.gxt.ui.client.event.Events.OnMouseOut, createListener(widget, listener));
    }
    
    @Override
    protected void removeMouseOutListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.removeListener(com.extjs.gxt.ui.client.event.Events.OnMouseOut, getListener(widget, listener));
    }

    
    
    // FOCUS IN
    @Override
    protected void addFocusInListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.addListener(com.extjs.gxt.ui.client.event.Events.OnFocus, createListener(widget, listener));
    }
    
    @Override
    protected void removeFocusInListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.removeListener(com.extjs.gxt.ui.client.event.Events.OnFocus, getListener(widget, listener));
    }
    
    

    // FOCUS OUT
    @Override
    protected void addFocusOutListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.addListener(com.extjs.gxt.ui.client.event.Events.OnBlur, createListener(widget, listener));
    }

    @Override
    protected void removeFocusOutListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.removeListener(com.extjs.gxt.ui.client.event.Events.OnBlur, getListener(widget, listener));
    }
    
    
    
    // ENTER
    @Override
    protected void addEnterListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.addListener(com.extjs.gxt.ui.client.event.Events.OnKeyPress, createKEnterListener(listener));
	component.addListener(com.extjs.gxt.ui.client.event.Events.OnDoubleClick, createListener(widget, listener));
    }

    @Override
    protected void removeEnterListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.removeListener(com.extjs.gxt.ui.client.event.Events.OnKeyPress, getListener(widget, listener, 0));
	component.removeListener(com.extjs.gxt.ui.client.event.Events.OnDoubleClick, getListener(widget, listener, 1));
    }

    
    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	
	Control control = (Control) element;
	com.extjs.gxt.ui.client.widget.BoxComponent xControl = (com.extjs.gxt.ui.client.widget.BoxComponent) getComponent(element.getDelegate());
	if (xControl == null) {
	    return;
	}


	if (eq(Events.KeyDown, eventType)) {
	    addKeyDownListener(xControl, control, listener);
	    return;
	} else if (eq(Events.KeyUp, eventType)) {
	    addKeyUpListener(xControl, control, listener);
	    return;
	} else if (eq(Events.MouseDown, eventType)) {
	    addMouseDownListener(xControl, control, listener);
	    return;
	} else if (eq(Events.MouseUp, eventType)) {
	    addMouseUpListener(xControl, control, listener);
	    return;
	} else if (eq(Events.MouseClick, eventType)) {
	    addMouseClickListener(xControl, control, listener);
	    return;
	} else if (eq(Events.MouseDoubleClick, eventType)) {
	    addMouseDoubleClickListener(xControl, control, listener);
	    return;
	} else if (eq(Events.MouseMove, eventType)) {
	    addMouseMoveListener(xControl, control, listener);
	    return;
	} else if (eq(Events.MouseIn, eventType)) {
	    addMouseInListener(xControl, control, listener);
	    return;
	} else if (eq(Events.MouseOut, eventType)) {
	    addMouseOutListener(xControl, control, listener);
	    return;
	} else if (eq(Events.FocusIn, eventType)) {
	    addFocusInListener(xControl, control, listener);
	    return;
	} else if (eq(Events.FocusOut, eventType)) {
	    addFocusOutListener(xControl, control, listener);
	    return;
	}
	
	super.addListener(element, eventType, listener);
	
    }
    
    
    
    @Override
    public void removeListener(UIObject element, String eventType, final Listener listener) {
	
	Control control = (Control) element;
	com.extjs.gxt.ui.client.widget.BoxComponent xControl = (com.extjs.gxt.ui.client.widget.BoxComponent) getComponent(element.getDelegate());
	if (xControl == null) {
	    return;
	}

	if (eq(Events.KeyDown, eventType)) {
	    removeKeyDownListener(xControl, control, listener);
	    return;
	} else if (eq(Events.KeyUp, eventType)) {
	    removeKeyUpListener(xControl, control, listener);
	    return;
	} else if (eq(Events.MouseDown, eventType)) {
	    removeMouseDownListener(xControl, control, listener);
	    return;
	} else if (eq(Events.MouseUp, eventType)) {
	    removeMouseUpListener(xControl, control, listener);
	    return;
	} else if (eq(Events.MouseClick, eventType)) {
	    removeMouseClickListener(xControl, control, listener);
	    return;
	} else if (eq(Events.MouseDoubleClick, eventType)) {
	    removeMouseDoubleClickListener(xControl, control, listener);
	    return;
	} else if (eq(Events.MouseMove, eventType)) {
	    removeMouseMoveListener(xControl, control, listener);
	    return;
	} else if (eq(Events.MouseIn, eventType)) {
	    removeMouseInListener(xControl, control, listener);
	    return;
	} else if (eq(Events.MouseOut, eventType)) {
	    removeMouseOutListener(xControl, control, listener);
	    return;
	} else if (eq(Events.FocusIn, eventType)) {
	    removeFocusInListener(xControl, control, listener);
	    return;
	} else if (eq(Events.FocusOut, eventType)) {
	    removeFocusOutListener(xControl, control, listener);
	    return;
	}
	
	super.removeListener(element, eventType, listener);
	
    }
    
    
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	if (Control.METHOD_REPAINT.equals(methodName)) {
	    com.extjs.gxt.ui.client.widget.BoxComponent xControl = (com.extjs.gxt.ui.client.widget.BoxComponent) getComponent(element.getDelegate());
	    if (xControl == null) {
		return null;
	    }
	    xControl.repaint();
	}
	return super.invoke(element, methodName, args);
    }

}
