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
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.gxt.layout.XLayout;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.Window;

import com.sencha.gxt.widget.core.client.container.Container;
import com.google.gwt.resources.client.ImageResource;

/**
 * 
 * @author ohapon
 *
 */
public class GXTWindowAdapter extends GXTCompositeAdapter {
    
    private static final String SYS_PROPERTY_FORCE_CLOSE = "$forceClose"; 
    
    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	Window window = (Window) element;
	Object xParent = parent == null ? null: parent.getDelegate();
	return createWindow(xParent, window);
    }
    
    protected com.sencha.gxt.widget.core.client.Window createWindow(final Object xParent, final Window window) {
	final com.sencha.gxt.widget.core.client.Window xWindow = new com.sencha.gxt.widget.core.client.Window();
	xWindow.setModal(window.isModal());
	xWindow.setResizable(window.isResizable());
	
	// UPDATE DECORATION
	updateDecoration(window, xWindow);
	
	//TODO: DISABLE:MIGRATION
	//addNotifierListener(window, xWindow);
	
	return xWindow;
    }
    
    //TODO: DISABLE:MIGRATION
    /*
    protected void addNotifierListener(final Window window, finalcom.sencha.gxt.widget.core.client.Window xWindow) {
	final Notifier notifier = window.getNotifier(); 
	xWindow.addListener(com.sencha.gxt.ui.client.event.Events.BeforeHide, new com.sencha.gxt.ui.client.event.Listener<com.sencha.gxt.ui.client.event.WindowEvent>() {

	    @Override
	    public void handleEvent(final com.sencha.gxt.ui.client.event.WindowEvent be) {
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
	    
	});

	xWindow.addListener(com.sencha.gxt.ui.client.event.Events.Hide, new com.sencha.gxt.ui.client.event.Listener<com.sencha.gxt.ui.client.event.WindowEvent>() {

	    @Override
	    public void handleEvent(com.sencha.gxt.ui.client.event.WindowEvent be) {
		be.setCancelled(true);
	    }
	    
	});
	
	
    }
    */
    
    
    protected void updateDecoration(Window window,com.sencha.gxt.widget.core.client.Window xWindow) {
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
	com.sencha.gxt.widget.core.client.Window xWindow = (com.sencha.gxt.widget.core.client.Window) delegate;
	if (Window.PROPERTY_TITLE.equals(name)) {
	    xWindow.setHeading(asSafeString(value));
	    return;
	} else if (Window.PROPERTY_ICON.equals(name)) {
	    ImageResource xIcon = createImage(element, asImage(value));
	    if (xIcon != null) {
		//TODO: DISABLE:MIGRATION
		//xWindow.setIcon(xIcon);
	    }
	    return;
	} else if (Window.PROPERTY_ICON_PATH.equals(name)) {
	    ImageResource xIcon = createImage(element, asString(value));
	    if (xIcon != null) {
		//TODO: DISABLE:MIGRATION
		//xWindow.setIcon(xImage);
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
	com.sencha.gxt.widget.core.client.Window xWindow = (com.sencha.gxt.widget.core.client.Window) delegate;
	if (Window.METHOD_OPEN.equals(methodName)) {
	    doOpen(window, xWindow);
	} else if (Window.METHOD_CLOSE.equals(methodName)) {
	    doClose(window, xWindow);
	} else if (Window.METHOD_LAYOUT.equals(methodName)) {
	    
	    //TODO: DISABLE:MIGRATION
	    //doLayout(xWindow);
	    return null;
	    
	} else if (Window.METHOD_PACK.equals(methodName) ) {
	    
	    //TODO: DISABLE:MIGRATION
	    //doPack(xWindow);
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

    //TODO: DISABLE:MIGRATION
    /*
    protected void doLayout(com.sencha.gxt.widget.core.client.Window xWindow) {
	xWindow.layout(true);
    }

    
    protected void doPack(com.sencha.gxt.widget.core.client.Window xWindow) {
	LayoutContainer container = xWindow;
	Layout layout = container.getLayout();
	if (layout == null) {
	    return;
	}
	if (!(layout instanceof XLayout)) {
	    
	    // TODO: Must use XLayout:computePreferredSize()
	    if (!(layout instanceof FitLayout)) {
		return;
	    }
	    com.google.gwt.user.client.ui.Widget parent = container.getItemCount() == 0 ? null : container.getWidget(0);
	    if (parent == null) {
		return;
	    }
	    if (!(parent instanceof LayoutContainer)) {
		return;
	    }
	    container = (LayoutContainer) parent;
	    layout = container.getLayout();
	    if (!(layout instanceof XLayout)) {
		return;
	    }
	}
	
	Size size = ((XLayout) layout).computePreferredSize(container);
	int fixDeltaWidth = 15;  // TODO: Window trim width
	int fixDeltaHeight = 40; // TODO: Window trim height
	xWindow.setSize(size.width + fixDeltaWidth, size.height + fixDeltaHeight);
    }
    */

    protected void doOpen(Window window,com.sencha.gxt.widget.core.client.Window xWindow) {
	xWindow.setVisible(true);
	
	//TODO: DISABLE:MIGRATION
	//doLayout(xWindow);
	
	if (window.isPack()) {
	    
	    //TODO: DISABLE:MIGRATION
	    //doPack(xWindow);
	}
	if (window.isCenter()) {
	    xWindow.center();
	}
    }
    
    protected void doClose(Window window,com.sencha.gxt.widget.core.client.Window xWindow) {
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
    
    //TODO: DISABLE:MIGRATION
    /*
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
    
    
    protected void addOpenWindowListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	component.addListener(com.sencha.gxt.ui.client.event.Events.Show, createListener(widget, listener));
    }
    
    protected void addCloseWindowListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	component.addListener(com.sencha.gxt.ui.client.event.Events.Hide, createListener(widget, listener));
    }

    protected void addActivateWindowListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	component.addListener(com.sencha.gxt.ui.client.event.Events.Activate, createListener(widget, listener));
    }

    protected void addDeactivateWindowListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	component.addListener(com.sencha.gxt.ui.client.event.Events.Deactivate, createListener(widget, listener));
    }

    protected void addIconifyWindowListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	component.addListener(com.sencha.gxt.ui.client.event.Events.Minimize, createListener(widget, listener)); // TODO Minimize = Iconify (?)
    }
    
    protected void addDeiconifyWindowListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	component.addListener(com.sencha.gxt.ui.client.event.Events.Maximize, createListener(widget, listener)); // TODO Maximize = Deiconify (?)
    }
    


    protected void removeOpenWindowListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	component.removeListener(com.sencha.gxt.ui.client.event.Events.Show, getListener(widget, listener));
    }
    
    protected void removeCloseWindowListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	component.removeListener(com.sencha.gxt.ui.client.event.Events.Hide, getListener(widget, listener));
    }

    protected void removeActivateWindowListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	component.removeListener(com.sencha.gxt.ui.client.event.Events.Activate, getListener(widget, listener));
    }

    protected void removeDeactivateWindowListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	component.removeListener(com.sencha.gxt.ui.client.event.Events.Deactivate, getListener(widget, listener));
    }
    
    protected void removeIconifyWindowListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	component.removeListener(com.sencha.gxt.ui.client.event.Events.Minimize, getListener(widget, listener)); // TODO Minimize = Iconify (?)
    }
    
    protected void removeDeiconifyWindowListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	component.removeListener(com.sencha.gxt.ui.client.event.Events.Maximize, getListener(widget, listener)); // TODO Maximize = Deiconify (?)
    }    
    
*/
    
}
