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

import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.ValueProvider;
import org.plazmaforge.framework.uwt.UIAdapter;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.UWTException;
import org.plazmaforge.framework.uwt.event.KeyEvent;
import org.plazmaforge.framework.uwt.gxt.adapter.viewer.XLabelProvider;
import org.plazmaforge.framework.uwt.gxt.adapter.viewer.XValueProvider;
import org.plazmaforge.framework.uwt.gxt.data.Model;
import org.plazmaforge.framework.uwt.gxt.widget.XDesktopItem;
import org.plazmaforge.framework.uwt.gxt.widget.XLayoutContainer;
import org.plazmaforge.framework.uwt.gxt.widget.XTabItem;
import org.plazmaforge.framework.uwt.widget.Container;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Event;
import org.plazmaforge.framework.uwt.widget.Layout;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.user.client.ui.HasWidgets;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.event.GridEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

/**
 * 
 * @author ohapon
 *
 */
public abstract class GXTWidgetAdapter extends GXTAbstractAdapter {
    
   
    // Check
    protected void checkNullParent(com.google.gwt.user.client.ui.Widget parent, String title) {
	if (parent == null) {
	    throw new UWTException(title + ". Parent is null");
	}
    }
    
    // Check
    protected void checkContainerParent(com.google.gwt.user.client.ui.Widget parent, String title) {
	if (!(parent instanceof HasWidgets)) {
	    throw new UWTException(title + ". Parent is not container: " + parent.getClass().getName());
	}
    } 
    
    // Throw
    protected void throwUnsupportParent(com.google.gwt.user.client.ui.Widget parent, String title) {
	throw new UWTException(title + ". Parent is not supported: " + parent.getClass().getName());
    }     
    
    // Log
    protected void logUnsupportSetProperty(Object widget, String property) {
	logUnsupportProperty("setProperty", widget, property);
    }

    // Log
    protected void logUnsupportGetProperty(Object widget, String property) {
	logUnsupportProperty("getProperty", widget, property);
    }
          
    // Log
    protected void logUnsupportProperty(String title, Object widget, String property) {
  	GWT.log("UWT: " + title + ": Property '"+  property + "' is not supported. Class=" + widget.getClass().getName());
    }
    
    /**
     * Add widget to parent
     * @param parent
     * @param widget
     * @param element
     */
    protected void addChild(com.google.gwt.user.client.ui.Widget parent, com.google.gwt.user.client.ui.Widget widget, UIObject element) {
	
	checkNullParent(parent, MESSAGE_CANT_ADD_WIDGET);
	checkContainerParent(parent, MESSAGE_CANT_ADD_WIDGET);
	
	// Force activate LayoutData
	if (element instanceof Control) {
	    Control control = (Control) element;
	    Object layoutData = control.getLayoutData();
	    if (layoutData != null && layoutData instanceof UIObject) {
		UIObject uiLayoutData = (UIObject) layoutData;
		uiLayoutData.activateUI();

		// Set LayoutData to xControl
		widget.setLayoutData(uiLayoutData.getDelegate());
		
		control.resetInitProperty(Control.PROPERTY_LAYOUT_DATA);
		
	    }
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

	throwUnsupportParent(parent, MESSAGE_CANT_ADD_WIDGET);

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
	if (p != null && p instanceof Container) {
	    layout = ((Container) p).getLayout();
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
    protected void removeChild(com.google.gwt.user.client.ui.Widget parent, com.google.gwt.user.client.ui.Widget widget) {
	
	checkNullParent(parent, MESSAGE_CANT_REMOVE_WIDGET);
	checkContainerParent(parent, MESSAGE_CANT_REMOVE_WIDGET);
	
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
	
	throwUnsupportParent(parent, MESSAGE_CANT_REMOVE_WIDGET);

    }
    

    @Override
    public void disposeDelegate(UIObject parent, UIObject element) {
	com.google.gwt.user.client.ui.Widget  parentDelegate = getContent(parent.getDelegate());
	com.google.gwt.user.client.ui.Widget delegate = asWidget(element.getDelegate());
	removeChild(parentDelegate, delegate);
    }



    /**
     * Return GWT Widget
     * @param delegate
     * @return
     */
    protected final com.google.gwt.user.client.ui.Widget asWidget(Object delegate) {
	return (com.google.gwt.user.client.ui.Widget) delegate;
    }

    /**
     * Return GXT Component
     * @param delegate
     * @return
     */
    protected final com.sencha.gxt.widget.core.client.Component asComponent(Object delegate) {
	return (com.sencha.gxt.widget.core.client.Component) delegate;
    }

    
    /**
     * Return GXT Container
     * @param delegate
     * @return
     */
    protected final com.sencha.gxt.widget.core.client.container.Container asContainer(Object delegate) {
	return (com.sencha.gxt.widget.core.client.container.Container) delegate;
    }
    

    /**
     * Returns general content of the UI object.
     * @param delegate
     * @return
     */
    protected final com.google.gwt.user.client.ui.Widget getContent(Object delegate) {
	return getView(delegate);
    }

    /**
     * Returns view widget of UI object.
     * UI object can be not widget, but UI object can wrap widget. 
     * @param delegate
     * @return
     */
    protected final com.google.gwt.user.client.ui.Widget getView(Object delegate) {
	if (delegate instanceof XTabItem) {
	    // XTabItem is not widget
	    return  ((XTabItem) delegate).getWidget();
	}
	if (delegate instanceof XDesktopItem) {
	    // XDesktopItem is not widget
	    return ((XDesktopItem) delegate).getContent();
	}
	return (com.google.gwt.user.client.ui.Widget) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	if (element == null) {
	    return;
	}	
	Object xElement = element.getDelegate();
	if (!(xElement instanceof com.google.gwt.user.client.ui.Widget)) {
	    logUnsupportSetProperty(xElement, name);
	    return;
	}
	com.google.gwt.user.client.ui.Widget xWidget = asWidget(xElement);
	
	if (xWidget instanceof com.sencha.gxt.widget.core.client.Component) {
	    
	    //GXT-Component
	    com.sencha.gxt.widget.core.client.Component xComponent = asComponent(xWidget);
	    if (Control.PROPERTY_DATA.equals(name)) {
		// WARNING! BoxComponent has not method setData() without parameters
		// We use key 'uwt:data'
		xComponent.setData("uwt:data", value);
		return;
	    } else if (startsWith(name, Control.PROPERTY_DATA_PREFIX)) {
		// WARNING! We use '@' to separate 'data' and 'key'
		String key = name.substring(Control.PROPERTY_DATA_PREFIX.length());
		xComponent.setData(key, value);
		return;
	    }
	} else {
	    
	    //GWT-Widget
	    if (Control.PROPERTY_DATA.equals(name) || startsWith(name, Control.PROPERTY_DATA_PREFIX)) {
		logUnsupportSetProperty(xWidget, name);
		return;
	    }
	    
	}
	super.setProperty(element, name, value);
    }
    
    @Override
    public Object getProperty(UIObject element, String name) {
	return super.getProperty(element, name);
    }

    protected String applyStyle(String baseStyle, String attribute, String value) {
	return GXTHelper.applyStyle(baseStyle, attribute, value);
    }

    protected String applyStyle(String baseStyle, String style) {
	return GXTHelper.applyStyle(baseStyle, style);
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // KEY LISTENERS
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected void addKeyDownListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeKeyDownListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    

    
    protected void addKeyUpListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    
    protected void removeKeyUpListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // MOUSE LISTENERS
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void addMouseDownListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeMouseDownListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    

    protected void addMouseUpListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeMouseUpListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    
    
    protected void addMouseClickListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    
    protected void removeMouseClickListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    
    
    protected void addMouseDoubleClickListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    
    protected void removeMouseDoubleClickListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // MOUSE MOVE LISTENERS
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected void addMouseMoveListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeMouseMoveListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    
    
    protected void addMouseInListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    
    protected void removeMouseInListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    

    protected void addMouseOutListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    
    protected void removeMouseOutListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // FOCUS LISTENERS
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void addFocusInListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeFocusInListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    
    
    protected void addFocusOutListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeFocusOutListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // SELECTION LISTENER
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected void addSelectionListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeSelectionListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // ENTER LISTENER
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void addEnterListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeEnterListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    
    
    
    // KEY DOWN
    protected com.google.gwt.event.dom.client.KeyDownHandler createKeyDownListener(Widget widget, final Listener listener) {
	com.google.gwt.event.dom.client.KeyDownHandler xListener = new com.google.gwt.event.dom.client.KeyDownHandler() {

	    @Override
	    public void onKeyDown(com.google.gwt.event.dom.client.KeyDownEvent e) {
		listener.handleEvent(createEvent(e));
		
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }
    
    
    // KEY UP
    protected com.google.gwt.event.dom.client.KeyUpHandler createKeyUpListener(Widget widget, final Listener listener) {
	com.google.gwt.event.dom.client.KeyUpHandler xListener = new com.google.gwt.event.dom.client.KeyUpHandler() {

	    @Override
	    public void onKeyUp(com.google.gwt.event.dom.client.KeyUpEvent e) {
		listener.handleEvent(createEvent(e));
		
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    } 
    
    
    // MOUSE DOWN
    protected com.google.gwt.event.dom.client.MouseDownHandler createMouseDownListener(Widget widget, final Listener listener) {
	com.google.gwt.event.dom.client.MouseDownHandler xListener = new com.google.gwt.event.dom.client.MouseDownHandler() {

	    @Override
	    public void onMouseDown(com.google.gwt.event.dom.client.MouseDownEvent e) {
		listener.handleEvent(createEvent(e));
		
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }
    
    // MOUSE UP
    protected com.google.gwt.event.dom.client.MouseUpHandler createMouseUpListener(Widget widget, final Listener listener) {
	com.google.gwt.event.dom.client.MouseUpHandler xListener = new com.google.gwt.event.dom.client.MouseUpHandler() {

	    @Override
	    public void onMouseUp(com.google.gwt.event.dom.client.MouseUpEvent e) {
		listener.handleEvent(createEvent(e));
		
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }  
    
    
    // MOUSE CLICK
    protected com.google.gwt.event.dom.client.ClickHandler createMouseClickListener(Widget widget, final Listener listener) {
	com.google.gwt.event.dom.client.ClickHandler xListener = new com.google.gwt.event.dom.client.ClickHandler() {

	    @Override
	    public void onClick(com.google.gwt.event.dom.client.ClickEvent e) {
		listener.handleEvent(createEvent(e));
		
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }      
    
    // MOUSE DOUBLE CLICK
    protected com.google.gwt.event.dom.client.DoubleClickHandler createMouseDoubleClickListener(Widget widget, final Listener listener) {
	com.google.gwt.event.dom.client.DoubleClickHandler xListener = new com.google.gwt.event.dom.client.DoubleClickHandler() {

	    @Override
	    public void onDoubleClick(com.google.gwt.event.dom.client.DoubleClickEvent e) {
		listener.handleEvent(createEvent(e));
		
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }       
    
    // MOUSE MOVE
    protected com.google.gwt.event.dom.client.MouseMoveHandler createMouseMoveListener(Widget widget, final Listener listener) {
	com.google.gwt.event.dom.client.MouseMoveHandler xListener = new com.google.gwt.event.dom.client.MouseMoveHandler() {

	    @Override
	    public void onMouseMove(com.google.gwt.event.dom.client.MouseMoveEvent e) {
		listener.handleEvent(createEvent(e));
		
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }      
    
    // MOUSE IN
    protected com.google.gwt.event.dom.client.MouseOverHandler createMouseInListener(Widget widget, final Listener listener) {
	com.google.gwt.event.dom.client.MouseOverHandler xListener = new com.google.gwt.event.dom.client.MouseOverHandler() {

	    @Override
	    public void onMouseOver(com.google.gwt.event.dom.client.MouseOverEvent e) {
		listener.handleEvent(createEvent(e));
		
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }       
    
    // MOUSE OUT
    protected com.google.gwt.event.dom.client.MouseOutHandler createMouseOutListener(Widget widget, final Listener listener) {
	com.google.gwt.event.dom.client.MouseOutHandler xListener = new com.google.gwt.event.dom.client.MouseOutHandler() {

	    @Override
	    public void onMouseOut(com.google.gwt.event.dom.client.MouseOutEvent e) {
		listener.handleEvent(createEvent(e));
		
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }    
    
    
    // FOCUS IN
    protected com.google.gwt.event.dom.client.FocusHandler createFocusInListener(Widget widget, final Listener listener) {
	com.google.gwt.event.dom.client.FocusHandler xListener = new com.google.gwt.event.dom.client.FocusHandler() {

	    @Override
	    public void onFocus(com.google.gwt.event.dom.client.FocusEvent e) {
		listener.handleEvent(createEvent(e));
		
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }          
    
    // FOCUS OUT
    protected com.google.gwt.event.dom.client.BlurHandler createFocusOutListener(Widget widget, final Listener listener) {
	com.google.gwt.event.dom.client.BlurHandler xListener = new com.google.gwt.event.dom.client.BlurHandler() {

	    @Override
	    public void onBlur(com.google.gwt.event.dom.client.BlurEvent e) {
		listener.handleEvent(createEvent(e));
		
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }      
    
    
    // SELECTION-GXT (context: Button, ToolItem)
    protected com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler createSelectionListener(Widget widget, final Listener listener) {
	com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler xListener = new com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler() {

	    @Override
	    public void onSelect(SelectEvent e) {
		listener.handleEvent(createEvent(e));
		
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    } 
    
    // SELECTION-GWT (item: MenuItem (!))
    protected  <T> com.google.gwt.event.logical.shared.SelectionHandler<T> createSelectionListener(Class<T> klass, Widget widget, final Listener listener) {
	com.google.gwt.event.logical.shared.SelectionHandler<T> xListener = new com.google.gwt.event.logical.shared.SelectionHandler<T>() {

	

	    @Override
	    public void onSelection(com.google.gwt.event.logical.shared.SelectionEvent<T> e) {
		listener.handleEvent(createEvent(e));
		
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }     
    
    // KEY ENTER
    protected com.google.gwt.event.dom.client.KeyUpHandler createKEnterListener(Widget widget, final Listener listener) {
	com.google.gwt.event.dom.client.KeyUpHandler xListener = new com.google.gwt.event.dom.client.KeyUpHandler() {

	    @Override
	    public void onKeyUp(com.google.gwt.event.dom.client.KeyUpEvent e) {
		com.google.gwt.dom.client.NativeEvent nativeEvent = e.getNativeEvent();
		if (nativeEvent == null) {
		    return;
		}
		int keyCode = nativeEvent.getKeyCode();
		if (keyCode != com.google.gwt.event.dom.client.KeyCodes.KEY_ENTER) {
		    return;
		}
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }     
    
 
    ////
    
    // SHOW
    protected com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler createShowListener(Widget widget, final Listener listener) {
	com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler xListener = new com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler() {

	    @Override
	    public void onShow(com.sencha.gxt.widget.core.client.event.ShowEvent e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }    
    
    // HIDE
    protected com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler createHideListener(Widget widget, final Listener listener) {
	com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler xListener = new com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler() {

	    @Override
	    public void onHide(com.sencha.gxt.widget.core.client.event.HideEvent e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }       
     
    // ACTIVATE
    protected <T> com.sencha.gxt.widget.core.client.event.ActivateEvent.ActivateHandler<T> createActivateListener(Class<T> klass, Widget widget, final Listener listener) {
	com.sencha.gxt.widget.core.client.event.ActivateEvent.ActivateHandler<T> xListener = new com.sencha.gxt.widget.core.client.event.ActivateEvent.ActivateHandler<T>() {

	    @Override
	    public void onActivate(com.sencha.gxt.widget.core.client.event.ActivateEvent<T> e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }

    // DEACTIVATE
    protected <T> com.sencha.gxt.widget.core.client.event.DeactivateEvent.DeactivateHandler<T> createDeactivateListener(Class<T> klass, Widget widget, final Listener listener) {
	com.sencha.gxt.widget.core.client.event.DeactivateEvent.DeactivateHandler<T> xListener = new com.sencha.gxt.widget.core.client.event.DeactivateEvent.DeactivateHandler<T>() {

	    @Override
	    public void onDeactivate(com.sencha.gxt.widget.core.client.event.DeactivateEvent<T> e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    } 
    
    // MINIMIZE
    protected com.sencha.gxt.widget.core.client.event.MinimizeEvent.MinimizeHandler createMinimizeListener(Widget widget, final Listener listener) {
	com.sencha.gxt.widget.core.client.event.MinimizeEvent.MinimizeHandler xListener = new com.sencha.gxt.widget.core.client.event.MinimizeEvent.MinimizeHandler() {

	    @Override
	    public void onMinimize(com.sencha.gxt.widget.core.client.event.MinimizeEvent e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }      
    
    // MAXIMIZE
    protected com.sencha.gxt.widget.core.client.event.MaximizeEvent.MaximizeHandler createMaximizeListener(Widget widget, final Listener listener) {
	com.sencha.gxt.widget.core.client.event.MaximizeEvent.MaximizeHandler xListener = new com.sencha.gxt.widget.core.client.event.MaximizeEvent.MaximizeHandler() {

	    @Override
	    public void onMaximize(com.sencha.gxt.widget.core.client.event.MaximizeEvent e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Events
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////


    protected Event createEvent(com.google.gwt.event.dom.client.DomEvent<?> e) {
  	Event event = new Event();
  	com.google.gwt.dom.client.NativeEvent nativeEvent = e.getNativeEvent();
  	if (nativeEvent == null) {
  	    return event;
  	}
  	
	event.setKeyCode(nativeEvent.getKeyCode());
	event.setCharacter((char) nativeEvent.getCharCode()); // TODO: Must analyze int -> char (Unicode) ???
	

  	int nativeButton = nativeEvent.getButton();
  	int button = 0;
  	if (nativeButton == NativeEvent.BUTTON_LEFT) {
  	    button = 1;
  	} else if (nativeButton == NativeEvent.BUTTON_MIDDLE) {
  	    button = 2;
  	} else if (nativeButton == NativeEvent.BUTTON_RIGHT) {
  	    button = 3;
  	}
  	
  	event.setButton(button);
  	event.setX(nativeEvent.getClientX());
  	event.setY(nativeEvent.getClientY());

  	int stateMask = 0;
  	if (nativeEvent.getShiftKey()) {
  	    stateMask |= KeyEvent.SHIFT_MASK;
  	}
  	if (nativeEvent.getCtrlKey()) {
  	    stateMask |= KeyEvent.CTRL_MASK;
  	}
  	if (nativeEvent.getMetaKey()) {
  	    stateMask |= KeyEvent.META_MASK;
  	}
  	if (nativeEvent.getAltKey()) {
  	    stateMask |= KeyEvent.ALT_MASK;
  	}
  	event.setStateMask(stateMask);

  	// TODO: No info ?
  	// event.setCount(count)

  	return event;
    }    
    
    protected Event createEvent(com.sencha.gxt.widget.core.client.event.SelectEvent  e) {
	 Event event = new Event();
	 Context context = e.getContext();
	 if (context != null) {
	     event.setIndex(context.getIndex());    
	 }
	 return event;
    }
    
    protected Event createEvent(com.google.gwt.event.logical.shared.SelectionEvent<?> e) {
	Event event = new Event();
	//event.setItem(item); // TODO
	return event;
    }
    
    protected Event createEvent(com.google.gwt.event.shared.GwtEvent<?> e) {
  	Event event = new Event();
  	//event.setItem(e.getSource()); // TODO
  	return event;
    }    

    // DISABLE:MIGRATION
    /**
     * Create GXT Listener by UWT Listener
     * @param listener
     * @return
     */
//    protected <E extends BaseEvent> com.sencha.gxt.ui.client.event.Listener<E> createListener(Widget widget, final Listener listener) {
//	com.sencha.gxt.ui.client.event.Listener<E> xListener = new com.sencha.gxt.ui.client.event.Listener<E>() {
//	    public void handleEvent(E e) {
//		listener.handleEvent(createEvent(e));
//	    }
//	};
//	widget.assignListener(listener, xListener);
//	return xListener;
//    }
//
//    protected <E extends BaseEvent> com.sencha.gxt.ui.client.event.Listener<E> getListener(Widget widget, Listener listener) {
//	return (com.sencha.gxt.ui.client.event.Listener<E>) widget.findListenerDelegate(listener);
//    }
//    
//    protected <E extends BaseEvent> com.sencha.gxt.ui.client.event.Listener<E> getListener(Widget widget, Listener listener, int index) {
//	return (com.sencha.gxt.ui.client.event.Listener<E>) widget.findListenerDelegate(listener, index);
//    }
//
//
//    protected <E extends BaseEvent> com.sencha.gxt.ui.client.event.Listener<E> createKEnterListener(final Listener listener) {
//	return new com.sencha.gxt.ui.client.event.Listener<E>() {
//	    public void handleEvent(E e) {
//		if (e instanceof com.sencha.gxt.ui.client.event.DomEvent) {
//		    com.sencha.gxt.ui.client.event.DomEvent domEvent = (com.sencha.gxt.ui.client.event.DomEvent) e;
//		    int keyCode = domEvent.getKeyCode();
//		    if (keyCode != com.google.gwt.event.dom.client.KeyCodes.KEY_ENTER) {
//			return;
//		    }
//		}
//		listener.handleEvent(createEvent(e));
//	    }
//	};
//    }

    
    /**
     * Create UWT Event by GXT Event
     * @param e
     * @return
     */
//    protected Event createEvent(BaseEvent e) {
//        Event event = new Event();
//        if (e instanceof DomEvent) {
//            populateEvent(event, (DomEvent) e);
//        }
//        return event;
//    }

    /**
     * Populate by DomEvent
     * @param event
     * @param e
     */
//    protected void populateEvent(Event event, DomEvent e) {
//	com.google.gwt.user.client.Event nativeEvent = e.getEvent();
//	if (nativeEvent != null) {
//	    event.setKeyCode(nativeEvent.getKeyCode());
//	    event.setCharacter((char) nativeEvent.getCharCode()); // TODO: Must analyze int -> char (Unicode) ???
//	    int nativeButton = nativeEvent.getButton(); 
//	    int button = 0;
//	    if (nativeButton == NativeEvent.BUTTON_LEFT) {
//		button = 1;
//	    } else if (nativeButton == NativeEvent.BUTTON_MIDDLE) {
//		button = 2;
//	    } else if (nativeButton == NativeEvent.BUTTON_RIGHT) {
//		button = 3;
//	    }
//	    event.setButton(button);
//	    event.setX(nativeEvent.getClientX());
//	    event.setY(nativeEvent.getClientY());
//	    
//	    int stateMask = 0;
//	    if (nativeEvent.getShiftKey()) {
//		stateMask |= KeyEvent.SHIFT_MASK;
//	    }
//	    if (nativeEvent.getCtrlKey()) {
//		stateMask |= KeyEvent.CTRL_MASK;
//	    }
//	    if (nativeEvent.getMetaKey()) {
//		stateMask |= KeyEvent.META_MASK;
//	    }
//	    if (nativeEvent.getAltKey()) {
//		stateMask |= KeyEvent.ALT_MASK;
//	    }
//	    event.setStateMask(stateMask);
//
//	    // TODO: No info ?
//	    //event.setCount(count) 
//	    
//	}
//	
//        //event.stateMask = e.stateMask;
//        //event.count = e.count;
//
//        if (e instanceof ComponentEvent) {
//            populateEvent(event, (ComponentEvent) e);
//        }
//    }
//    
//    protected void populateEvent(Event event, ComponentEvent e) {
//	event.setDelegate(e.getComponent());
//        if (e instanceof BoxComponentEvent) {
//            populateEvent(event, (BoxComponentEvent) e);
//        }
//    }
//    
//    protected void populateEvent(Event event, BoxComponentEvent e) {
//        
//        if (e instanceof GridEvent) {
//            populateEvent(event, (GridEvent) e);
//        }
//        
//    }
    
    protected void populateEvent(Event event, GridEvent e) {
	// do nothing
    }    
    
    //// GXT ////
        
 
    
    protected ListStore<Model> createXDefaultListStore() {
	return GXTHelper.createXDefaultListStore();
    }
        
    
    //
    // XLabelProvider
    //    
    protected XLabelProvider createXLabelProvider(String property) {
	return GXTHelper.createXLabelProvider(property);
    }
    
    protected XLabelProvider createXLabelProvider(String property, PropertyProvider propertyProvider) {
	return GXTHelper.createXLabelProvider(property, propertyProvider);
    }

    protected XLabelProvider createXLabelProvider(String property, PropertyProvider propertyProvider, ValueProvider valueProvider) {
	return GXTHelper.createXLabelProvider(property, propertyProvider, valueProvider);
    }
    
    //
    // XValueProvider
    //    
    protected XValueProvider createXValueProvider(String property) {
	return GXTHelper.createXValueProvider(property);
    }

    protected XValueProvider createXValueProvider(String property, PropertyProvider propertyProvider) {
 	return GXTHelper.createXValueProvider(property, propertyProvider);
    }
    
    protected XValueProvider createXValueProvider(String property, PropertyProvider propertyProvider, ValueProvider valueProvider) {
  	return GXTHelper.createXValueProvider(property, propertyProvider, valueProvider);
    }
}
