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

import com.google.gwt.user.client.ui.HasEnabled;


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
	
	// Get real view widget
	com.google.gwt.user.client.ui.Widget xWidget = getView(xElement);
	
	//if (!(xElement instanceof com.google.gwt.user.client.ui.Widget)) {
	//    logUnsupportSetProperty(xElement, name);
	//    return;
	//}	
	//com.google.gwt.user.client.ui.Widget xWidget = asWidget(xElement);
	
	// GWT-All
	if (Control.PROPERTY_VISIBLE.equals(name)) {
	    xWidget.setVisible(asBoolean(value));
	    return;
	} else if (Control.PROPERTY_ENABLED.equals(name)) {
	    if (xWidget instanceof  HasEnabled) {
		((HasEnabled) xWidget).setEnabled(asBoolean(value));
	    } else {
		logUnsupportSetProperty(xWidget, name);
	    }
	    return;
	} else if (Control.PROPERTY_LAYOUT_DATA.equals(name)) {
	    UIObject layoutData = (UIObject) value;
	    if (layoutData == null) {
		xWidget.setLayoutData(null);
		return;
	    }
	    layoutData.activateUI();
	    xWidget.setLayoutData(layoutData.getDelegate());
	    return;
	} else if (Control.PROPERTY_WIDTH.equals(name)) {
	    setWidth(xWidget, asInteger(value));
	    return;
	} else if (Control.PROPERTY_HEIGHT.equals(name)) {
	    setHeight(xWidget, asInteger(value));
	    return;
	} else if (Composite.PROPERTY_BACKGROUND.equals(name)) {
	    setColorAttribute(xWidget, "background", asColor(value));
	    return;
	} else if (Composite.PROPERTY_FOREGROUND.equals(name)) {
	    setColorAttribute(xWidget, "color", asColor(value));
	    return;	    
	} else if (Composite.PROPERTY_FONT.equals(name)) {
	    setFontAttribute(xWidget, "font", asFont(value));
	    return;	    
	}
	
	if (xWidget instanceof com.sencha.gxt.widget.core.client.Component) {

	    // GXT-Component
	    com.sencha.gxt.widget.core.client.Component xComponent = asComponent(xWidget);
	    if (Composite.PROPERTY_TOOL_TIP.equals(name)) {
		xComponent.setToolTip(asSafeString(value));
		return;
	    } else if (Control.PROPERTY_CONTEXT_MENU.equals(name)) {
		Menu menu = (Menu) value;
		menu.activateUI();
		xComponent.setContextMenu((com.sencha.gxt.widget.core.client.menu.Menu) menu.getDelegate());
		return;
	    }

	} else {
	    
	    // GWT-Widget
	    if (Control.PROPERTY_TOOL_TIP.equals(name) || Control.PROPERTY_CONTEXT_MENU.equals(name)) {
		logUnsupportSetProperty(xWidget, name);
		return;
	    }

	}
	
	super.setProperty(element, name, value);
    }

    protected void setColorAttribute(com.google.gwt.user.client.ui.Widget xControl, String attribute, Color color) {
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
	getStyle(xControl).setProperty(attribute, value);
    }
    
    protected void setFontAttribute(com.google.gwt.user.client.ui.Widget xControl, String attribute, Font font) {
	String value = getFontString(font);
	String style = attribute + ": " + value;
	if (xControl instanceof com.sencha.gxt.widget.core.client.ContentPanel) {

	    // Special hard code to set style to content panel
	    // TODO: Must optimize code
	    com.sencha.gxt.widget.core.client.ContentPanel contentPanel = (com.sencha.gxt.widget.core.client.ContentPanel) xControl;
	    contentPanel.setBodyStyle(style);

	    return;
	}
	getStyle(xControl).setProperty(attribute, value);
    }
    
    @Override
    public Object getProperty(UIObject element, String name) {
	if (element == null) {
	    return null;
	}
	Object xElement = element.getDelegate();
	if (!(xElement instanceof com.google.gwt.user.client.ui.Widget)) {
	    logUnsupportGetProperty(xElement, name);
	    return null;
	}	
	com.google.gwt.user.client.ui.Widget xWidget = asWidget(xElement);
	
	// GWT-All
	if (Control.PROPERTY_VISIBLE.equals(name)) {
	    return xWidget.isVisible();
	} else if (Control.PROPERTY_ENABLED.equals(name)) {
	    if (xWidget instanceof  HasEnabled) {
		return ((HasEnabled) xWidget).isEnabled();
	    } else {
		logUnsupportGetProperty(xWidget, name);
		return null;
	    }
	}
	
	return super.getProperty(element, name);
    }
    
    protected int toDimension(Integer value) {
	return value == null ? -1: value;
    }

    protected void setWidth(com.google.gwt.user.client.ui.Widget xControl, Integer width) {
	if (xControl instanceof com.sencha.gxt.widget.core.client.Component) {
	    asComponent(xControl).setWidth(toDimension(width));
	} else {
	    getStyle(xControl).setProperty("width", toDimension(width) + "px");
	}
    }
  
    protected void setHeight(com.google.gwt.user.client.ui.Widget xControl, Integer height) {
	if (xControl instanceof com.sencha.gxt.widget.core.client.Component) {
	    asComponent(xControl).setHeight(toDimension(height));
	} else {
	    getStyle(xControl).setProperty("height", toDimension(height) + "px");
	}
    }
    
    protected void setSize(com.sencha.gxt.widget.core.client.Component xControl, Integer width, Integer height) {
	xControl.setPixelSize(toDimension(width), toDimension(height));
    }
    
    
    protected com.google.gwt.dom.client.Style getStyle(com.google.gwt.user.client.ui.Widget xWidget) {
	if (xWidget == null) {
	    return null;
	}
	return xWidget.getElement().getStyle();
    }
    
    
    
    // KEY DOWN
    @Override
    protected void addKeyDownListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	xWidget.addDomHandler(createKeyDownListener(widget, listener), com.google.gwt.event.dom.client.KeyDownEvent.getType());
    }    
    
    @Override
    protected void removeKeyDownListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.OnKeyDown, getListener(widget, listener)); //TODO
    }
    

    // KEY UP
    @Override
    protected void addKeyUpListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	xWidget.addDomHandler(createKeyUpListener(widget, listener), com.google.gwt.event.dom.client.KeyUpEvent.getType());
    }

    @Override
    protected void removeKeyUpListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.OnKeyUp, getListener(widget, listener)); //TODO
    }

        
    // MOUSE DOWN
    @Override
    protected void addMouseDownListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	xWidget.addDomHandler(createMouseDownListener(widget, listener), com.google.gwt.event.dom.client.MouseDownEvent.getType());
    }

    @Override
    protected void removeMouseDownListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.OnMouseDown, getListener(widget, listener));
    }
    
    
    // MOUSE UP
    @Override
    protected void addMouseUpListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	xWidget.addDomHandler(createMouseUpListener(widget, listener), com.google.gwt.event.dom.client.MouseUpEvent.getType());
    }
    
    @Override
    protected void removeMouseUpListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.OnMouseUp, getListener(widget, listener));
    }

    
    // MOUSE CLICK
    @Override
    protected void addMouseClickListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	xWidget.addDomHandler(createMouseClickListener(widget, listener), com.google.gwt.event.dom.client.ClickEvent.getType());
    }

    @Override
    protected void removeMouseClickListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.OnClick, getListener(widget, listener));
    }
        
    
    // MOUSE DOUBLE CLICK
    @Override
    protected void addMouseDoubleClickListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	xWidget.addDomHandler(createMouseDoubleClickListener(widget, listener), com.google.gwt.event.dom.client.DoubleClickEvent.getType());
    }

    @Override
    protected void removeMouseDoubleClickListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.OnDoubleClick, getListener(widget, listener));
    }

    
    // MOUSE MOVE
    @Override
    protected void addMouseMoveListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	xWidget.addDomHandler(createMouseMoveListener(widget, listener), com.google.gwt.event.dom.client.MouseMoveEvent.getType());
    }

    @Override
    protected void removeMouseMoveListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.OnMouseMove, getListener(widget, listener));
    }
        

    // MOUSE IN
    @Override
    protected void addMouseInListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	xWidget.addDomHandler(createMouseInListener(widget, listener), com.google.gwt.event.dom.client.MouseOverEvent.getType());
    }

    @Override
    protected void removeMouseInListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.OnMouseOver, getListener(widget, listener));
    }
    
    
    // MOUSE OUT
    @Override
    protected void addMouseOutListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	xWidget.addDomHandler(createMouseOutListener(widget, listener), com.google.gwt.event.dom.client.MouseOutEvent.getType());
    }
    
    @Override
    protected void removeMouseOutListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.OnMouseOut, getListener(widget, listener));
    }

   
    // FOCUS IN
    @Override
    protected void addFocusInListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	xWidget.addDomHandler(createFocusInListener(widget, listener), com.google.gwt.event.dom.client.FocusEvent.getType());
    }
    
    @Override
    protected void removeFocusInListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.OnFocus, getListener(widget, listener));
    }
     

    // FOCUS OUT
    @Override
    protected void addFocusOutListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	xWidget.addDomHandler(createFocusOutListener(widget, listener), com.google.gwt.event.dom.client.BlurEvent.getType());
    }

    @Override
    protected void removeFocusOutListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.OnBlur, getListener(widget, listener));
    }
    

    // KEY ENTER
    @Override
    protected void addEnterListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	xWidget.addDomHandler(createKEnterListener(widget, listener), com.google.gwt.event.dom.client.KeyUpEvent.getType());
	xWidget.addDomHandler(createMouseDoubleClickListener(widget, listener), com.google.gwt.event.dom.client.DoubleClickEvent.getType());
    }
    
    protected void removeEnterListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
 	// TODO
    }

    
    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	
	Control control = (Control) element;
	com.google.gwt.user.client.ui.Widget xWidget = asWidget(element.getDelegate());
	if (xWidget == null) {
	    return;
	}


	if (eq(Events.KeyDown, eventType)) {
	    addKeyDownListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.KeyUp, eventType)) {
	    addKeyUpListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseDown, eventType)) {
	    addMouseDownListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseUp, eventType)) {
	    addMouseUpListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseClick, eventType)) {
	    addMouseClickListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseDoubleClick, eventType)) {
	    addMouseDoubleClickListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseMove, eventType)) {
	    addMouseMoveListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseIn, eventType)) {
	    addMouseInListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseOut, eventType)) {
	    addMouseOutListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.FocusIn, eventType)) {
	    addFocusInListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.FocusOut, eventType)) {
	    addFocusOutListener(xWidget, control, listener);
	    return;
	}
	
	super.addListener(element, eventType, listener);
	
    }
    
    
    
    @Override
    public void removeListener(UIObject element, String eventType, final Listener listener) {
	
	Control control = (Control) element;
	com.google.gwt.user.client.ui.Widget xWidget = asWidget(element.getDelegate());
	if (xWidget == null) {
	    return;
	}

	if (eq(Events.KeyDown, eventType)) {
	    removeKeyDownListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.KeyUp, eventType)) {
	    removeKeyUpListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseDown, eventType)) {
	    removeMouseDownListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseUp, eventType)) {
	    removeMouseUpListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseClick, eventType)) {
	    removeMouseClickListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseDoubleClick, eventType)) {
	    removeMouseDoubleClickListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseMove, eventType)) {
	    removeMouseMoveListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseIn, eventType)) {
	    removeMouseInListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseOut, eventType)) {
	    removeMouseOutListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.FocusIn, eventType)) {
	    removeFocusInListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.FocusOut, eventType)) {
	    removeFocusOutListener(xWidget, control, listener);
	    return;
	}
	
	super.removeListener(element, eventType, listener);
	
    }
    
    
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	if (Control.METHOD_REPAINT.equals(methodName)) {
	   com.sencha.gxt.widget.core.client.Component xControl = asComponent(element.getDelegate());
	    if (xControl == null) {
		return null;
	    }
	  //DISABLE:MIGRATION
	    //xControl.repaint();
	}
	return super.invoke(element, methodName, args);
    }
    
    
  

}
