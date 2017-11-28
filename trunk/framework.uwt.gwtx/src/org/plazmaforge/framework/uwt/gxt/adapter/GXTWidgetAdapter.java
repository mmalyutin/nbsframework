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
import org.plazmaforge.framework.uwt.gxt.data.ModelData;
import org.plazmaforge.framework.uwt.gxt.widget.XLayoutContainer;
import org.plazmaforge.framework.uwt.gxt.widget.XTabItem;
import org.plazmaforge.framework.uwt.widget.Composite;
//import org.plazmaforge.framework.uwt.gxt.widget.XDesktopItem;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Event;
import org.plazmaforge.framework.uwt.widget.Layout;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;

import com.google.gwt.core.client.GWT;

//import com.sencha.gxt.ui.client.event.BaseEvent;
//import com.sencha.gxt.ui.client.event.BoxComponentEvent;
//import com.sencha.gxt.ui.client.event.ComponentEvent;
//import com.sencha.gxt.ui.client.event.DomEvent;
//import com.sencha.gxt.ui.client.event.GridEvent;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.user.client.ui.HasWidgets;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.event.GridEvent;

public abstract class GXTWidgetAdapter extends GXTAbstractAdapter {

    /**
     * Add widget to parent
     * @param parent
     * @param widget
     * @param element
     */
    protected void addToParent(com.google.gwt.user.client.ui.Widget parent, com.google.gwt.user.client.ui.Widget widget, UIObject element) {
	
	if (parent == null) {
	    throw new UWTException("Can not add widget to parent. Parent is null");
	}
	
	if (!(parent instanceof HasWidgets)) {
	    throw new UWTException("Can not add widget to parent. Parent is not container: " + parent.getClass().getName());
	}
	
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



    /**
     * Return GWT Widget
     * @param delegate
     * @return
     */
    protected com.google.gwt.user.client.ui.Widget getWidget(Object delegate) {
	return (com.google.gwt.user.client.ui.Widget) delegate;
    }

    /**
     * Return GXT Component
     * @param delegate
     * @return
     */
    protected com.sencha.gxt.widget.core.client.Component getComponent(Object delegate) {
	return (com.sencha.gxt.widget.core.client.Component) delegate;
    }

    
    /**
     * Return GXT Container
     * @param delegate
     * @return
     */
    protected com.sencha.gxt.widget.core.client.container.Container getContainer(Object delegate) {
	return (com.sencha.gxt.widget.core.client.container.Container) delegate;
    }
    
    /**
     * Return GXT Layout Container
     * @param delegate
     * @return
     */
//    protected com.sencha.gxt.widget.core.client.container.Container getLayoutContainer(Object delegate) {
//	return (com.sencha.gxt.widget.core.client.container.Container) delegate;
//    }
    

    /**
     * Return general content of the delegate
     * @param delegate
     * @return
     */
    protected com.google.gwt.user.client.ui.Widget getContent(Object delegate) {
	if (delegate instanceof XTabItem) {
	    return  ((XTabItem) delegate).getWidget();
	}
	//DISABLE:MIGRATION
	//if (delegate instanceof XDesktopItem) {
	//    return ((XDesktopItem) delegate).getContent();
	//}
	return (com.google.gwt.user.client.ui.Widget) delegate;
    }

    
    
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	com.sencha.gxt.widget.core.client.Component xWidget = (com.sencha.gxt.widget.core.client.Component) getComponent(element.getDelegate());
	if (xWidget == null) {
	    return;
	}
	if (Control.PROPERTY_DATA.equals(name)) {
	    // WARNING! BoxComponent has not method setData() without parameters
	    // We use key 'uwt:data'
	    xWidget.setData("uwt:data", value);
	    return;
	} else if (startsWith(name, Control.PROPERTY_DATA_PREFIX)) {
	    // WARNING! We use '@' to separate 'data' and 'key' 
	    String key = name.substring(Control.PROPERTY_DATA_PREFIX.length());
	    xWidget.setData(key, value);
	    return;	    
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
    
    protected void addKeyDownListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeKeyDownListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }
    

    
    protected void addKeyUpListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }
    
    protected void removeKeyUpListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }
    

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // MOUSE LISTENERS
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void addMouseDownListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeMouseDownListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }
    

    protected void addMouseUpListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeMouseUpListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }

    
    
    protected void addMouseClickListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }
    
    protected void removeMouseClickListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }

    
    
    protected void addMouseDoubleClickListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }
    
    protected void removeMouseDoubleClickListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // MOUSE MOVE LISTENERS
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected void addMouseMoveListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeMouseMoveListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }

    
    
    protected void addMouseInListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }
    
    protected void removeMouseInListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }

    

    protected void addMouseOutListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }
    
    protected void removeMouseOutListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // FOCUS LISTENERS
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void addFocusInListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeFocusInListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }

    
    
    protected void addFocusOutListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeFocusOutListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // SELECTION LISTENER
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected void addSelectionListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeSelectionListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // ENTER LISTENER
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void addEnterListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeEnterListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	// do nothing
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
        
 
    
    protected ListStore<ModelData> createXDefaultListStore() {
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
