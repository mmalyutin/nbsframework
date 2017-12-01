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
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.menu.Menu;

import com.google.gwt.core.client.GWT;


/**
 * 
 * @author ohapon
 *
 */
public abstract class GXTControlAdapter extends GXTWidgetAdapter {


  
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	if (element == null) {
	    return;
	}
	Object xElement = element.getDelegate();
	if (!(xElement instanceof com.sencha.gxt.widget.core.client.Component)) {
	    GWT.log("UWT: setProperty '"+  name + "' ignore. Element is not GXT Component. Class=" + xElement.getClass());
	    return;
	    
	}
	com.sencha.gxt.widget.core.client.Component xControl = (com.sencha.gxt.widget.core.client.Component) getComponent(element.getDelegate());
	if (xControl == null) {
	    return;
	}
	if (Control.PROPERTY_VISIBLE.equals(name)) {
	    xControl.setVisible(asBoolean(value));
	} else if (Control.PROPERTY_ENABLED.equals(name)) {
	    xControl.setEnabled(asBoolean(value));
	} else if (Control.PROPERTY_LAYOUT_DATA.equals(name)) {
	    UIObject layoutData = (UIObject) value;
	    if (layoutData == null) {
		xControl.setLayoutData(null);
		return;
	    }
	    layoutData.activateUI();
	    xControl.setLayoutData(layoutData.getDelegate());
	    return;
	} else if (Control.PROPERTY_WIDTH.equals(name)) {
	    setWidth(xControl, asInteger(value));
	    return;
	} else if (Control.PROPERTY_HEIGHT.equals(name)) {
	    setHeight(xControl, asInteger(value));
	    return;
	} else if (Composite.PROPERTY_BACKGROUND.equals(name)) {
	    setColorAttribute(xControl, "background", asColor(value));
	    return;
	} else if (Composite.PROPERTY_FOREGROUND.equals(name)) {
	    setColorAttribute(xControl, "color", asColor(value));
	    return;	    
	} else if (Composite.PROPERTY_FONT.equals(name)) {
	    setFontAttribute(xControl, "font", asFont(value));
	    return;	    
	} else if (Composite.PROPERTY_TOOL_TIP.equals(name)) {
	    xControl.setToolTip(asSafeString(value));
	    return;
	}  else if (Control.PROPERTY_CONTEXT_MENU.equals(name)) {
	    Menu menu = (Menu) value;
	    menu.activateUI();
	    xControl.setContextMenu((com.sencha.gxt.widget.core.client.menu.Menu) menu.getDelegate());
	    return;
	}
	
	super.setProperty(element, name, value);
    }

    protected void setColorAttribute(com.sencha.gxt.widget.core.client.Component xControl, String attribute, Color color) {
	String value = getColorString(color);
	String style = attribute + ": " + value;
	if (xControl instanceof com.sencha.gxt.widget.core.client.ContentPanel) {

	    // Special hard code to set style to content panel
	    // TODO: Must optimize code
	    com.sencha.gxt.widget.core.client.ContentPanel contentPanel = (com.sencha.gxt.widget.core.client.ContentPanel) xControl;
	    contentPanel.setBodyStyle(style);

	    return;
	}
	if (!attribute.equalsIgnoreCase("color")) {
	    attribute = attribute + "Color";
	}
	xControl.getElement().getStyle().setProperty(attribute, value);
    }
    
    protected void setFontAttribute(com.sencha.gxt.widget.core.client.Component xControl, String attribute, Font font) {
	String value = getFontString(font);
	String style = attribute + ": " + value;
	if (xControl instanceof com.sencha.gxt.widget.core.client.ContentPanel) {

	    // Special hard code to set style to content panel
	    // TODO: Must optimize code
	    com.sencha.gxt.widget.core.client.ContentPanel contentPanel = (com.sencha.gxt.widget.core.client.ContentPanel) xControl;
	    contentPanel.setBodyStyle(style);

	    return;
	}
	xControl.getElement().getStyle().setProperty(attribute, value);
    }
    
    @Override
    public Object getProperty(UIObject element, String name) {
	if (element == null) {
	    return null;
	}
	Object xElement = element.getDelegate();
	if (!(xElement instanceof com.sencha.gxt.widget.core.client.Component)) {
	    GWT.log("UWT: getProperty '"+  name + "' ignore. Element is not GXT Component. Class=" + xElement.getClass());
	    return null;
	}
	
	com.sencha.gxt.widget.core.client.Component xControl = (com.sencha.gxt.widget.core.client.Component) getComponent(element.getDelegate());
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
    
    protected int toDimension(Integer value) {
	return value == null ? -1: value;
    }

    protected void setWidth(com.sencha.gxt.widget.core.client.Component xControl, Integer width) {
	xControl.setWidth(toDimension(width));
    }
  
    protected void setHeight(com.sencha.gxt.widget.core.client.Component xControl, Integer height) {
	xControl.setHeight(toDimension(height));
    }
    
    protected void setSize(com.sencha.gxt.widget.core.client.Component xControl, Integer width, Integer height) {
	xControl.setPixelSize(toDimension(width), toDimension(height));
    }
    
    
    
    
    // KEY DOWN
    @Override
    protected void addKeyDownListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	component.addDomHandler(createKeyDownListener(widget, listener), com.google.gwt.event.dom.client.KeyDownEvent.getType());
    }    
    
    @Override
    protected void removeKeyDownListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	//component.removeListener(com.sencha.gxt.ui.client.event.Events.OnKeyDown, getListener(widget, listener)); //TODO
    }
    

    // KEY UP
    @Override
    protected void addKeyUpListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	component.addDomHandler(createKeyUpListener(widget, listener), com.google.gwt.event.dom.client.KeyUpEvent.getType());
    }

    @Override
    protected void removeKeyUpListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	//component.removeListener(com.sencha.gxt.ui.client.event.Events.OnKeyUp, getListener(widget, listener)); //TODO
    }

    
    
    // DISABLE:MIGRATION
//    
//    // MOUSE DOWN
//    @Override
//    protected void addMouseDownListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.addListener(com.sencha.gxt.ui.client.event.Events.OnMouseDown, createListener(widget, listener));
//    }
//
//    @Override
//    protected void removeMouseDownListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.removeListener(com.sencha.gxt.ui.client.event.Events.OnMouseDown, getListener(widget, listener));
//    }
//
//    
//    
//    // MOUSE UP
//    @Override
//    protected void addMouseUpListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.addListener(com.sencha.gxt.ui.client.event.Events.OnMouseUp, createListener(widget, listener));
//    }
//    
//    @Override
//    protected void removeMouseUpListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.removeListener(com.sencha.gxt.ui.client.event.Events.OnMouseUp, getListener(widget, listener));
//    }
//    
//
//
//    // MOUSE CLICK
//    @Override
//    protected void addMouseClickListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.addListener(com.sencha.gxt.ui.client.event.Events.OnClick, createListener(widget, listener));
//    }
//
//    @Override
//    protected void removeMouseClickListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.removeListener(com.sencha.gxt.ui.client.event.Events.OnClick, getListener(widget, listener));
//    }
//    
//    
//    
//    // MOUSE DOUBLE CLICK
//    @Override
//    protected void addMouseDoubleClickListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.addListener(com.sencha.gxt.ui.client.event.Events.OnDoubleClick, createListener(widget, listener));
//    }
//
//    @Override
//    protected void removeMouseDoubleClickListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.removeListener(com.sencha.gxt.ui.client.event.Events.OnDoubleClick, getListener(widget, listener));
//    }
//    
//    
//    
//    // MOUSE MOVE
//    @Override
//    protected void addMouseMoveListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.addListener(com.sencha.gxt.ui.client.event.Events.OnMouseMove, createListener(widget, listener));
//    }
//
//    @Override
//    protected void removeMouseMoveListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.removeListener(com.sencha.gxt.ui.client.event.Events.OnMouseMove, getListener(widget, listener));
//    }
//    
//    
//
//    // MOUSE IN
//    @Override
//    protected void addMouseInListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.addListener(com.sencha.gxt.ui.client.event.Events.OnMouseOver, createListener(widget, listener));
//    }
//
//    @Override
//    protected void removeMouseInListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.removeListener(com.sencha.gxt.ui.client.event.Events.OnMouseOver, getListener(widget, listener));
//    }
//
//    
//    
//    // MOUSE OUT
//    @Override
//    protected void addMouseOutListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.addListener(com.sencha.gxt.ui.client.event.Events.OnMouseOut, createListener(widget, listener));
//    }
//    
//    @Override
//    protected void removeMouseOutListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.removeListener(com.sencha.gxt.ui.client.event.Events.OnMouseOut, getListener(widget, listener));
//    }
//
//    
//    
//    // FOCUS IN
//    @Override
//    protected void addFocusInListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.addListener(com.sencha.gxt.ui.client.event.Events.OnFocus, createListener(widget, listener));
//    }
//    
//    @Override
//    protected void removeFocusInListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.removeListener(com.sencha.gxt.ui.client.event.Events.OnFocus, getListener(widget, listener));
//    }
//    
//    
//
//    // FOCUS OUT
//    @Override
//    protected void addFocusOutListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.addListener(com.sencha.gxt.ui.client.event.Events.OnBlur, createListener(widget, listener));
//    }
//
//    @Override
//    protected void removeFocusOutListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.removeListener(com.sencha.gxt.ui.client.event.Events.OnBlur, getListener(widget, listener));
//    }
//    
//    
//    
//    // ENTER
//    @Override
//    protected void addEnterListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.addListener(com.sencha.gxt.ui.client.event.Events.OnKeyPress, createKEnterListener(listener));
//	component.addListener(com.sencha.gxt.ui.client.event.Events.OnDoubleClick, createListener(widget, listener));
//    }
//
//    @Override
//    protected void removeEnterListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.removeListener(com.sencha.gxt.ui.client.event.Events.OnKeyPress, getListener(widget, listener, 0));
//	component.removeListener(com.sencha.gxt.ui.client.event.Events.OnDoubleClick, getListener(widget, listener, 1));
//    }

    
    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	
	Control control = (Control) element;
	com.sencha.gxt.widget.core.client.Component xControl = (com.sencha.gxt.widget.core.client.Component) getComponent(element.getDelegate());
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
	com.sencha.gxt.widget.core.client.Component xControl = (com.sencha.gxt.widget.core.client.Component) getComponent(element.getDelegate());
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
	   com.sencha.gxt.widget.core.client.Component xControl = (com.sencha.gxt.widget.core.client.Component) getComponent(element.getDelegate());
	    if (xControl == null) {
		return null;
	    }
	  //DISABLE:MIGRATION
	    //xControl.repaint();
	}
	return super.invoke(element, methodName, args);
    }
    
    
  

}
