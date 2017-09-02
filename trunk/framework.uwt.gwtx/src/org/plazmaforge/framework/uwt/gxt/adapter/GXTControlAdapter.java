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

import org.plazmaforge.framework.uwt.UIAdapter;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.UWTException;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.gxt.widget.XLayoutContainer;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Layout;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.menu.Menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * 
 * @author ohapon
 *
 */
public abstract class GXTControlAdapter extends GXTWidgetAdapter {


    /**
     * Add widget to parent
     * @param parent
     * @param widget
     * @param element
     */
    protected void addToParent(com.google.gwt.user.client.ui.Widget parent, com.google.gwt.user.client.ui.Widget widget, UIObject element) {
	if (!(parent instanceof HasWidgets)) {
	    throw new UWTException("Can not add widget to parent. Parent is not container: " + parent.getClass().getName());
	}
	
	//GXT-Container
	if (parent instanceof com.sencha.gxt.widget.core.client.container.Container) {
	    addChild((com.sencha.gxt.widget.core.client.container.Container) parent, widget, element);
	    return;
	}
	
	//GWT-Panel
	if (parent instanceof com.google.gwt.user.client.ui.Panel) {
	    addChild((com.google.gwt.user.client.ui.Panel) parent, widget, element);
	    return;
	}

	throw new UWTException("Can not add widget to parent. Parent is not supported: " + parent.getClass().getName());
    }
    
    //GXT-Container
    protected void addChild(com.sencha.gxt.widget.core.client.container.Container parent, com.google.gwt.user.client.ui.Widget widget, UIObject element) {
	if (parent instanceof XLayoutContainer) {
	    addChild((XLayoutContainer) parent, widget, element);
	    return;
	}
	parent.add(widget);
    }
    
    //GXT-Container: XLayoutContainer
    protected void addChild(XLayoutContainer parent, com.google.gwt.user.client.ui.Widget widget, UIObject element) {
	UIObject p = element.getUIParent();
	Layout layout = null;
	
	// Get layout
	if (p != null && p instanceof Composite) {
	    layout = ((Composite) p).getLayout();
	}
	
	// No layout - default add
	if (layout == null) {
	    parent.add(widget);
	    return;
	}
	
	// Get UIAdapter for Layout
	UIAdapter adapter = getAdapter(layout.getClass());
	if (adapter == null) {
	    //no way
	    parent.add(widget);
	    return;	    
	}
	
	// Check adapter class
	if (!(adapter instanceof GXTLayoutAdapter)) {
	    //TODO: warning
	    parent.add(widget);
	    return;
	}
	
	// Specific add - dependency layout
	((GXTLayoutAdapter) adapter).addChild(parent, widget, element);
    }    
    
    //GWT-Panel
    protected void addChild(com.google.gwt.user.client.ui.Panel parent, com.google.gwt.user.client.ui.Widget widget, UIObject element) {
	 parent.add(widget);
    }    
    
    /**
     * Remove widget form parent
     * @param parent
     * @param widget
     */
    protected void removeFromParent(com.google.gwt.user.client.ui.Widget parent, com.google.gwt.user.client.ui.Widget widget) {
	if (!(parent instanceof HasWidgets)) {
	    throw new UWTException("Can not remove widget to parent. Parent is not container: " + parent.getClass().getName());
	}
	
	//GXT-Container
	if (parent instanceof com.sencha.gxt.widget.core.client.container.Container) {
	    ((com.sencha.gxt.widget.core.client.container.Container) parent).remove(widget);
	    return;
	}
	
	//GWT-Panel
	if (parent instanceof com.google.gwt.user.client.ui.Panel) {
	    ((com.google.gwt.user.client.ui.Panel) parent).remove(widget);
	    return;
	} 
	
	throw new UWTException("Can not remove widget from parent. Parent is not supported: " + parent.getClass().getName());
    }
    

//   com.sencha.gxt.widget.core.client.container.Container layoutContainer = (com.sencha.gxt.widget.core.client.container.Container) parent; 
//	    if (element != null) {
//		UIObject p = element.getUIParent();
//		if (p instanceof SplitPanel) {
//		    SplitPanel splitPanel = (SplitPanel) p;
//		    int count = splitPanel.getChildrenCount();
//		    Orientation orientation = splitPanel.getOrientation();
//		    if (orientation == null) {
//			orientation = Orientation.HORIZONTAL;
//		    }
//		    
//		    //DISABLE:MIGRATION
//		    /*
//		    if (count == 1) {
//			// First element
//			BorderLayoutData ld = new BorderLayoutData(orientation.equals(Orientation.HORIZONTAL) ? LayoutRegion.WEST : LayoutRegion.NORTH);
//			ld.setSplit(true);
//			//ld.setSize(200); // TODO
//			widget.setLayoutData(ld);
//			layoutContainer.add(widget, ld);
//			return;
//		    } else if (count == 2) {
//			// Second element
//			BorderLayoutData ld = new BorderLayoutData(LayoutRegion.CENTER);
//			//ld.setSplit(true);
//			//ld.setSize(200); // TODO
//			widget.setLayoutData(ld);
//			layoutContainer.add(widget, ld);
//			return;
//		    }
//		    */
//		}
//	    }
//	    layoutContainer.add(widget);
     
     
    @Override
    public void disposeDelegate(UIObject parent, UIObject element) {
	com.google.gwt.user.client.ui.Widget  parentDelegate = (com.google.gwt.user.client.ui.Widget) getContent(parent.getDelegate());
	com.google.gwt.user.client.ui.Widget delegate = getWidget(element.getDelegate());
	removeFromParent(parentDelegate, delegate);
    }

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
	    setWidth(xControl, v);
	    
	    //xControl.setWidth(v == null ? 0 : (v));
	    //xWidget.setStyleAttribute("width", v == null ? "0" : (v.toString() + ""));
	    
	    return;
	} else if (Control.PROPERTY_HEIGHT.equals(name)) {
	    Integer v = (Integer) value;
	    setHeight(xControl, v);
	    
	    //xControl.setHeight(v == null ? "0" : (v.toString() + ""));
	    //xWidget.setStyleAttribute("height", v == null ? "0" : (v.toString() + ""));
	    
	    return;
	} else if (Composite.PROPERTY_BACKGROUND.equals(name)) {
	    Color color = (Color) value;
	    String colorString = getColorString(color);
	    
	    //DISABLE:MIGRATION
	    /*
	    if (xControl instanceof com.sencha.gxt.widget.core.client.ContentPanel) {
		
		//Special hard code to set style to content panel
		//TODO: Must optimize code
		com.sencha.gxt.widget.core.client.ContentPanel contentPanel = (com.sencha.gxt.widget.core.client.ContentPanel) xControl;
		String baseStyle = contentPanel.getBodyStyle();
		contentPanel.setBodyStyle(applyStyle(baseStyle, "background", colorString));
		
		return;
	    }
	    
	    xControl.setStyleAttribute("background", colorString);
	    */
	    
	    return;
	} else if (Composite.PROPERTY_FOREGROUND.equals(name)) {
	    Color color = (Color) value;
	    String colorString = getColorString(color);
	    
	    //DISABLE:MIGRATION
	    /*
	    if (xControl instanceof com.sencha.gxt.widget.core.client.ContentPanel) {
		
		//Special hard code to set style to content panel
		//TODO: Must optimize code
		com.sencha.gxt.widget.core.client.ContentPanel contentPanel = (com.sencha.gxt.widget.core.client.ContentPanel) xControl;
		String baseStyle = contentPanel.getBodyStyle();
		contentPanel.setBodyStyle(applyStyle(baseStyle, "color", colorString));
		
		return;
	    }
	    
	    xControl.setStyleAttribute("color", colorString);
	    */
	    
	    
	    return;	    
	} else if (Composite.PROPERTY_FONT.equals(name)) {
	    Font font = (Font) value;
	    String fontString = getFontString(font);
	    
	  //DISABLE:MIGRATION
	    /*
	    if (xControl instanceof com.sencha.gxt.widget.core.client.ContentPanel) {
		
		//Special hard code to set style to content panel
		//TODO: Must optimize code
		com.sencha.gxt.widget.core.client.ContentPanel contentPanel = (com.sencha.gxt.widget.core.client.ContentPanel) xControl;
		String baseStyle = contentPanel.getBodyStyle();
		contentPanel.setBodyStyle(applyStyle(baseStyle, "font", fontString));
		
		return;
	    }
	    
	    xControl.setStyleAttribute("font", fontString);
	    */
	    
	    return;	    
	} else if (Composite.PROPERTY_TOOL_TIP.equals(name)) {
	    xControl.setToolTip(getSafeString(value));
	    return;
	}  else if (Control.PROPERTY_CONTEXT_MENU.equals(name)) {
	    Menu menu = (Menu) value;
	    menu.activateUI();
	    xControl.setContextMenu((com.sencha.gxt.widget.core.client.menu.Menu) menu.getDelegate());
	    return;
	}
	
	super.setProperty(element, name, value);
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
    
    // DISABLE:MIGRATION
//    // KEY DOWN
//    @Override
//    protected void addKeyDownListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.addListener(com.sencha.gxt.ui.client.event.Events.OnKeyDown, createListener(widget, listener));
//    }
//
//    @Override
//    protected void removeKeyDownListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.removeListener(com.sencha.gxt.ui.client.event.Events.OnKeyDown, getListener(widget, listener));
//    }
//
//    
//    
//    // KEY UP
//    @Override
//    protected void addKeyUpListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.addListener(com.sencha.gxt.ui.client.event.Events.OnKeyUp, createListener(widget, listener));
//    }
//
//    @Override
//    protected void removeKeyUpListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.removeListener(com.sencha.gxt.ui.client.event.Events.OnKeyUp, getListener(widget, listener));
//    }
//
//    
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
