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

import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.Notifier;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.gxt.layout.XLayout;
import org.plazmaforge.framework.uwt.gxt.widget.XWindow;
import org.plazmaforge.framework.uwt.widget.Layout;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.Window;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HasWidgets;


/**
 * 
 * @author ohapon
 *
 */
public class GXTWindowAdapter extends GXTContainerAdapter {
    
    private static final String SYS_PROPERTY_FORCE_CLOSE = "$forceClose"; 
    
    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	Window window = (Window) element;
	Object xParent = parent == null ? null: parent.getDelegate();
	return createWindow(xParent, window);
    }
    
    protected com.sencha.gxt.widget.core.client.Window createWindow(final Object xParent, final Window window) {
	
	Layout layout = window.getLayout();
	XLayout xLayout = getXLayout(layout);
	
	// WARNING!
	// The com.sencha.gxt.widget.core.client.Window is a ContentPanel
	// The content of the Window is a SimpleContainer
	// The SimpleContainer has ONLY ONE child !
	// To resolve this problems we use own implementation of com.sencha.gxt.widget.core.client.Window
	
	HasWidgets container = createContainer(layout, xLayout);
	XWindow xWindow = new XWindow(container, xLayout);
	
	xWindow.setModal(window.isModal());
	xWindow.setResizable(window.isResizable());
	
	// UPDATE DECORATION
	updateDecoration(window, xWindow);
	
	// Get icon
	ImageResource xIcon = createImage(window, window.getIcon());
	if (xIcon != null) {
	    xWindow.setIcon(xIcon);
	}
	
	addNotifierListener(window, xWindow);
	
	return xWindow;
    }
    

    protected void addNotifierListener(final Window window, final XWindow xWindow) {
	final Notifier notifier = window.getNotifier(); 
	xWindow.addHandler(new com.sencha.gxt.widget.core.client.event.BeforeHideEvent.BeforeHideHandler() {

	    @Override
	    public void onBeforeHide(com.sencha.gxt.widget.core.client.event.BeforeHideEvent be) {
		if (isForceClose(window)) {
		    return;
		}
		if (notifier == null) {
		    return;
		}
		
		notifier.canNotify(Window.METHOD_CLOSE, new Callback<Boolean>() {

		    @Override
		    public void onFailure(Throwable error) {
			// do nothing
		    }

		    @Override
		    public void onSuccess(Boolean result) {
			boolean can = result == null ? false: result;
			if (!can) {
			    return;
			}
			notifier.notify(Window.METHOD_CLOSE);
			// Force close
			doClose(window, xWindow);
		    }
		    
		});
	    }
	    
	}, com.sencha.gxt.widget.core.client.event.BeforeHideEvent.getType());

	//TODO: DISABLE:MIGRATION
	/*
	xWindow.addHandler(new com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler() {

	    @Override
	    public void onHide(com.sencha.gxt.widget.core.client.event.HideEvent be) {
		be.setCancelled(true);
	    }
	    
	}, com.sencha.gxt.widget.core.client.event.HideEvent.getType());
	*/
	
    }
    
    protected void updateDecoration(Window window, XWindow xWindow) {
	boolean closable = window.isUndecorated() ? false : window.isClosable();  
	boolean minimizable = window.isUndecorated() ? false : window.isMinimizable();
	boolean maximizable = window.isUndecorated() ? false : window.isMaximizable();
	
	xWindow.setClosable(closable);	// Close button
	xWindow.setMinimizable(minimizable); // Iconified button
	xWindow.setMaximizable(maximizable); // Min/Max button

    }
    
   
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	Object delegate = element.getDelegate();
	if (delegate == null || !(delegate instanceof com.sencha.gxt.widget.core.client.Window)) {
	    return;
	}
	XWindow xWindow = (XWindow) delegate;
	if (Window.PROPERTY_TITLE.equals(name)) {
	    xWindow.setHeading(asSafeString(value));
	    return;
	} else if (Window.PROPERTY_ICON.equals(name)) {
	    ImageResource xIcon = createImage(element, asImage(value));
	    if (xIcon != null) {
		xWindow.setIcon(xIcon);
	    }
	    return;
	} else if (Window.PROPERTY_ICON_PATH.equals(name)) {
	    ImageResource xIcon = createImage(element, asString(value));
	    if (xIcon != null) {
		xWindow.setIcon(xIcon);
	    }
	    return;
	} else if (Window.PROPERTY_WIDTH.equals(name)) {
	    xWindow.setWidth(intValue(value));
	    return;
	} else if (Window.PROPERTY_WIDTH.equals(name)) {
	    xWindow.setHeight(intValue(value));
	    return;
	}

	super.setProperty(element, name, value);
    }
    
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	Object delegate = element.getDelegate();
	if (delegate == null || !(delegate instanceof com.sencha.gxt.widget.core.client.Window)) {
	    return null;
	}
	Window window = (Window) element;
	XWindow xWindow = (XWindow) delegate;
	if (Window.METHOD_OPEN.equals(methodName)) {
	    doOpen(window, xWindow);
	} else if (Window.METHOD_CLOSE.equals(methodName)) {
	    doClose(window, xWindow);
	} else if (Window.METHOD_LAYOUT.equals(methodName)) {
	    doLayout(xWindow);
	    return null;
	} else if (Window.METHOD_PACK.equals(methodName) ) {
	    doPack(xWindow);
	    return null;
	} else if (Window.METHOD_CENTER.equals(methodName) ) {
	    xWindow.center();
	    return null;
	} else if (Window.METHOD_ACTIVATE.equals(methodName) ) {
	    xWindow.setVisible(true);
	    xWindow.setActive(true);
	    return null;
	} else if (Window.METHOD_DEACTIVATE.equals(methodName) ) {
	    xWindow.setActive(false);
	    return null;
	} else if (Window.METHOD_MAXIMIZE.equals(methodName) ) {
	    xWindow.maximize();
	    return null;
	} else if (Window.METHOD_MINIMIZE.equals(methodName) ) {
	    xWindow.minimize();
	    return null;
	}

	return super.invoke(element, methodName, args);
    }

    protected void doLayout(XWindow xWindow) {
	xWindow.forceLayout();
    }

    protected void doPack(XWindow xWindow) {
	xWindow.pack();
    }

    protected void doOpen(Window window, XWindow xWindow) {
	xWindow.setVisible(true);
	doLayout(xWindow);
	
	if (window.isPack()) {
	    doPack(xWindow);
	}
	if (window.isCenter()) {
	    xWindow.center();
	}
    }
    
    protected void doClose(Window window, XWindow xWindow) {
	setForceClose(window, true); // SET FORCE CLOSE
	xWindow.setVisible(false);
	if (window.isDisposeWhenClose()) {
	    // xWindow.dispose();
	}
    }
    
    protected boolean isForceClose(Window window) {
	if (window == null) {
	    return false;
	}
	Boolean forceClose = (Boolean) window.getData(SYS_PROPERTY_FORCE_CLOSE);
	return forceClose == null ? false : forceClose;
    }
    
    protected void setForceClose(Window window, boolean forceClose) {
	if (window == null) {
	    return;
	}
	window.setData(SYS_PROPERTY_FORCE_CLOSE, forceClose);
    }
    

    @Override
    public void addListener(UIObject element, String eventType, Listener listener) {
	Window window = (Window) element;
	com.sencha.gxt.widget.core.client.Window xWindow = (com.sencha.gxt.widget.core.client.Window) element.getDelegate();
	if (xWindow == null) {
	    return;
	}

	if (eq(eventType, Events.Open)) {
	    addOpenWindowListener(xWindow, window, listener);
	    return;
	} else if (eq(eventType, Events.Close)) {
	    addCloseWindowListener(xWindow, window, listener);
	    return;
	} else if (eq(eventType, Events.Activate)) {
	    addActivateWindowListener(xWindow, window, listener);
	    return;
	} else if (eq(eventType, Events.Deactivate)) {
	    addDeactivateWindowListener(xWindow, window, listener);
	    return;
	} else if (eq(eventType, Events.Iconify)) {
	    addIconifyWindowListener(xWindow, window, listener);
	    return;
	} else if (eq(eventType, Events.Deiconify)) {
	    addDeiconifyWindowListener(xWindow, window, listener);
	    return;
	} 
 
	super.addListener(element, eventType, listener);
    }
    
    @Override
    public void removeListener(UIObject element, String eventType, Listener listener) {
	Window window = (Window) element;
	com.sencha.gxt.widget.core.client.Window xWindow = (com.sencha.gxt.widget.core.client.Window) element.getDelegate();
	if (xWindow == null) {
	    return;
	}
	
	if (eq(eventType, Events.Open)) {
	    removeOpenWindowListener(xWindow, window, listener);
	    return;
	} else if (eq(eventType, Events.Close)) {
	    removeCloseWindowListener(xWindow, window, listener);
	    return;
	} else if (eq(eventType, Events.Activate)) {
	    removeActivateWindowListener(xWindow, window, listener);
	    return;
	} else if (eq(eventType, Events.Deactivate)) {
	    removeDeactivateWindowListener(xWindow, window, listener);
	    return;
	} else if (eq(eventType, Events.Iconify)) {
	    removeIconifyWindowListener(xWindow, window, listener);
	    return;
	} else if (eq(eventType, Events.Deiconify)) {
	    removeDeiconifyWindowListener(xWindow, window, listener);
	    return;
	} 
	
	super.removeListener(element, eventType, listener);
    }
    
    
    protected void addOpenWindowListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	xWidget.addHandler(createShowListener(widget, listener), com.sencha.gxt.widget.core.client.event.ShowEvent.getType());
    }
    
    protected void addCloseWindowListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	xWidget.addHandler(createHideListener(widget, listener), com.sencha.gxt.widget.core.client.event.HideEvent.getType());
    }

    protected void addActivateWindowListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	xWidget.addHandler(createActivateListener(com.sencha.gxt.widget.core.client.Window.class, widget, listener), com.sencha.gxt.widget.core.client.event.ActivateEvent.getType());
    }
  
    protected void addDeactivateWindowListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	xWidget.addHandler(createDeactivateListener(com.sencha.gxt.widget.core.client.Window.class, widget, listener), com.sencha.gxt.widget.core.client.event.DeactivateEvent.getType());
    }

    protected void addIconifyWindowListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	xWidget.addHandler(createMinimizeListener(widget, listener), com.sencha.gxt.widget.core.client.event.MinimizeEvent.getType());  // TODO Minimize = Iconify (?)
    }
    
    protected void addDeiconifyWindowListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	xWidget.addHandler(createMaximizeListener(widget, listener), com.sencha.gxt.widget.core.client.event.MaximizeEvent.getType()); // TODO Maximize = Deiconify (?)
    }
    


    protected void removeOpenWindowListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//TODO
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.Show, getListener(widget, listener));
    }
    
    protected void removeCloseWindowListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//TODO
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.Hide, getListener(widget, listener));
    }

    protected void removeActivateWindowListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//TODO
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.Activate, getListener(widget, listener));
    }

    protected void removeDeactivateWindowListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//TODO
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.Deactivate, getListener(widget, listener));
    }
    
    protected void removeIconifyWindowListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//TODO
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.Minimize, getListener(widget, listener)); // TODO Minimize = Iconify (?)
    }
    
    protected void removeDeiconifyWindowListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//TODO
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.Maximize, getListener(widget, listener)); // TODO Maximize = Deiconify (?)
    }    
    
  
}
