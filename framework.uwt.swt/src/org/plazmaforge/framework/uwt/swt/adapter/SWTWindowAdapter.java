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

package org.plazmaforge.framework.uwt.swt.adapter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.Notifier;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.UWT;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.swt.util.SWTUtils;
import org.plazmaforge.framework.uwt.widget.Event;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.Window;



////////////////////////////////////////////////////////////
// SWT Style
////////////////////////////////////////////////////////////
//
// SHELL_TRIM = CLOSE | TITLE | MIN | MAX | RESIZE;
// DIALOG_TRIM = TITLE | CLOSE | BORDER;
//
////////////////////////////////////////////////////////////

public class SWTWindowAdapter extends SWTCompositeAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	
	Object xParent = null;
	if (parent != null) {
	    xParent = parent.getDelegate();
	}
	Window window = (Window) element;
	org.eclipse.swt.widgets.Shell xWindow = createShell(xParent, window);
	addNotifierListener(window, xWindow);
	return xWindow;
    }
  
    protected Shell createShell(Object xParent, Window window) {
	
	// Display and other Shell can be parent of Shell
	org.eclipse.swt.widgets.Shell xShell = null;
	org.eclipse.swt.widgets.Display xDisplay = null;

	if (xParent instanceof org.eclipse.swt.widgets.Shell) {
	    xShell = (org.eclipse.swt.widgets.Shell) xParent;
	} else if (xParent instanceof org.eclipse.swt.widgets.Display) {
	    xDisplay = (org.eclipse.swt.widgets.Display) xParent;
	}

	
	int style = getShellStyle(window);
	
	org.eclipse.swt.widgets.Shell xWindow = null;
	if (xShell != null) {
	    //if (style == SWT.NONE) {
		//xWindow = new org.eclipse.swt.widgets.Shell(xShell);
	    //} else {
		xWindow = new org.eclipse.swt.widgets.Shell(xShell, style);
	    //}
	} else {
	    //if (style == SWT.NONE) {
		//xWindow = new org.eclipse.swt.widgets.Shell(xDisplay);
	    //} else {
		xWindow = new org.eclipse.swt.widgets.Shell(xDisplay, style);
	    //}
	}
	
	xWindow.setLocation(0, 0); // FIX: reset location
	xWindow.setSize(0, 0); // FIX: reset size
	return xWindow;
    }
    
    protected void addNotifierListener(final Window window, final org.eclipse.swt.widgets.Shell xWindow) {
	final Notifier notifier = window.getNotifier();
	xWindow.addShellListener(new org.eclipse.swt.events.ShellListener() {

	    @Override
	    public void shellActivated(org.eclipse.swt.events.ShellEvent event) {
	    }

	    @Override
	    public void shellClosed(org.eclipse.swt.events.ShellEvent event) {
		if (notifier == null) {
		    return;
		}
		event.doit = false;
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

	    @Override
	    public void shellDeactivated(org.eclipse.swt.events.ShellEvent event) {
	    }

	    @Override
	    public void shellDeiconified(org.eclipse.swt.events.ShellEvent event) {
	    }

	    @Override
	    public void shellIconified(org.eclipse.swt.events.ShellEvent event) {
	    }
	    
	});

    }

    
    protected int getShellStyle(Window window) {
	int style = SWT.NONE;
	
	// SHELL_TRIM = CLOSE | TITLE | MIN | MAX | RESIZE;
	// DIALOG_TRIM = TITLE | CLOSE | BORDER;
	
	
	style = updateModalStyle(window, style);
	style = updateResizeStyle(window, style);
	style = updateTitleStyle(window, style);
	style = updateButtonsStyle(window, style);
	
	return style;
    }

    protected int updateModalStyle(Window window, int style) {
	if (window.isModal() ) { 
	    style |= SWT.APPLICATION_MODAL; // Modal mode
	}
	return style;
    }
    
    protected int updateResizeStyle(Window window, int style) {
	if (window.isResizable()) {
	    style |= SWT.RESIZE; // Resizible mode
	}
	return style;
    }
    
    
    protected int updateTitleStyle(Window window, int style) {
	if (!window.isUndecorated()) {
	    style |= SWT.TITLE;
	}
	return style;
    }
    
    protected int updateButtonsStyle(Window window, int style) {
	if (window.isUndecorated()) {
	    // NO DECORATION - NO BUTTONS
	    return style;
	}
	if (window.isClosable()) {
	    style |= SWT.CLOSE;	// Close button
	}
	if (window.isMinimizable() ) {
	    style |= SWT.MIN;	// Iconified button
	}
	if (window.isMaximizable() ) {
	    style |= SWT.MAX;	// Min/Max button
	}
	return style;
    }


    
    protected void centerShell(Shell shell) {
	SWTUtils.centerShell(shell);
    }

    @Override
    public void setProperty(UIObject element, String name, Object value) {
	org.eclipse.swt.widgets.Shell xWindow = (org.eclipse.swt.widgets.Shell) element.getDelegate();
	if (xWindow == null) {
	    return;
	}
	if (Window.PROPERTY_TITLE.equals(name)) {
	    xWindow.setText(getSafeString(value));
	    return;
	} else if (Window.PROPERTY_ICON.equals(name)) {
	    org.eclipse.swt.graphics.Image xImage = createImage(element, (Image) value);
	    if (xImage != null) {
		xWindow.setImage(xImage);
	    }
	    return;
	} else if (Window.PROPERTY_ICON_PATH.equals(name)) {
	    org.eclipse.swt.graphics.Image xImage = createImage(element, (String) value);
	    if (xImage != null) {
		xWindow.setImage(xImage);
	    }
	    return;
	} else if (Window.PROPERTY_VISIBLE.equals(name)) {
	    super.setProperty(element, name, value);
	    if (booleanValue(value)) {
		// Auto activate (set focus) if window is visible
		xWindow.setActive();
	    }
	    return;
	} 

	super.setProperty(element, name, value);
    }
    
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	org.eclipse.swt.widgets.Shell xWindow = (org.eclipse.swt.widgets.Shell) element.getDelegate();
	if (xWindow == null) {
	    return null;
	}
	Window window = (Window) element; 
	if (Window.METHOD_OPEN.equals(methodName)) {
	    doOpen(window, xWindow);
	} else if (Window.METHOD_CLOSE.equals(methodName)) {
	    doClose(window, xWindow);
	} else if (Window.METHOD_LAYOUT.equals(methodName)) {
	    xWindow.layout();
	    return null;
	} else if (Window.METHOD_PACK.equals(methodName)) {
	    xWindow.pack();
	    return null;
	} else if (Window.METHOD_CENTER.equals(methodName)) {
	    centerShell(xWindow);
	    return null;
	} else if (Window.METHOD_ACTIVATE.equals(methodName)) {
	    xWindow.setActive();
	    return null;
	} else if (Window.METHOD_DEACTIVATE.equals(methodName)) {
	    //TODO
	    return null;
	} else if (Window.METHOD_MAXIMIZE.equals(methodName)) {
	    xWindow.setMaximized(true);
	    return null;
	} else if (Window.METHOD_MINIMIZE.equals(methodName)) {
	    xWindow.setMinimized(true);
	    return null;
	}
	
	return super.invoke(element, methodName, args);
    }

    /**
     * Open window
     * @param window
     * @param xWindow
     */
    protected void doOpen(Window window, org.eclipse.swt.widgets.Shell xWindow) {
	if  (xWindow == null || xWindow.isDisposed()) {
	    return;
	}
	xWindow.layout();
	if (window.isPack()) {
	    xWindow.pack();
	}
	if (window.isCenter()) {
	    centerShell(xWindow);
	}
	xWindow.setVisible(true);
	
	// Auto activate (set focus)
	xWindow.setActive();
    }
    
    /**
     * Close window
     * @param window
     * @param xWindow
     */
    protected void doClose(Window window, org.eclipse.swt.widgets.Shell xWindow) {
	if  (xWindow == null || xWindow.isDisposed()) {
	    return;
	}
	xWindow.setVisible(false);
	if (window.isDisposeWhenClose()) {
	    xWindow.dispose();
	}
    }
    
    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	
	Window window = (Window) element;
	org.eclipse.swt.widgets.Shell xWindow = (org.eclipse.swt.widgets.Shell) element.getDelegate();
	if (xWindow == null) {
	    return;
	}
	
	if (eq(eventType, Events.Open)) {
	    xWindow.addShellListener(createOpenWindowListener(window, listener));
	    return;
	} else if (eq(eventType, Events.Close)) { 
	    xWindow.addShellListener(createCloseWindowListener(window, listener));
	    return;
	} else if (eq(eventType, Events.Activate)) { 
	    xWindow.addShellListener(createActivateWindowListener(window, listener));
	    return;
	} else if (eq(eventType, Events.Deactivate)) { 
	    xWindow.addShellListener(createDeactivateWindowListener(window, listener));
	    return;
	} else if (eq(eventType, Events.Iconify)) { 
	    xWindow.addShellListener(createIconifyWindowListener(window, listener));
	    return;
	} else if (eq(eventType, Events.Deiconify)) { 
	    xWindow.addShellListener(createDeiconifyWindowListener(window, listener));
	    return;
	}
	
 	super.addListener(element, eventType, listener);
    }
    
    @Override
    public void removeListener(UIObject element, String eventType, Listener listener) {
	
	Window window = (Window) element;
	org.eclipse.swt.widgets.Shell xWindow = (org.eclipse.swt.widgets.Shell) element.getDelegate();
	if (xWindow == null) {
	    return;
	}

	if (in(eventType, new Object[] {Events.Open, Events.Close, Events.Activate, Events.Deactivate, Events.Iconify, Events.Deiconify})) {
	    xWindow.removeShellListener(getWindowListener(window, listener));
	    return;
	}
 	
	super.removeListener(element, eventType, listener);
    }

    ////
    
    
    protected org.eclipse.swt.events.ShellListener createOpenWindowListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.ShellListener xListener = new org.eclipse.swt.events.ShellAdapter() {

	    @Override
	    public void shellActivated(org.eclipse.swt.events.ShellEvent e) {
		// Check first activation because the event is 'OpenWindow'
		boolean active = (e.widget != null && "true".equals(e.widget.getData("$active")));
		if (active) {
		    return;
		}
		if (e.widget != null) {
		    e.widget.setData("$active", true);
		}
		listener.handleEvent(createEvent(e));
	    }

	};
	widget.assignListener(listener, xListener);
	return xListener;
    }

    protected org.eclipse.swt.events.ShellListener createCloseWindowListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.ShellListener xListener = new org.eclipse.swt.events.ShellAdapter() {

	    @Override
	    public void  shellClosed(org.eclipse.swt.events.ShellEvent e) {
		listener.handleEvent(createEvent(e));
	    }

   	};
   	widget.assignListener(listener, xListener);
   	return xListener;
   }

    protected org.eclipse.swt.events.ShellListener createActivateWindowListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.ShellListener xListener = new org.eclipse.swt.events.ShellAdapter() {

	    @Override
	    public void shellActivated(org.eclipse.swt.events.ShellEvent e) {
		listener.handleEvent(createEvent(e));
	    }

	};
	widget.assignListener(listener, xListener);
	return xListener;
    }
    
    protected org.eclipse.swt.events.ShellListener createDeactivateWindowListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.ShellListener xListener = new org.eclipse.swt.events.ShellAdapter() {

	    @Override
	    public void shellDeactivated(org.eclipse.swt.events.ShellEvent e) {
		listener.handleEvent(createEvent(e));
	    }

	};
	widget.assignListener(listener, xListener);
	return xListener;
    }
    
    
    protected org.eclipse.swt.events.ShellListener createIconifyWindowListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.ShellListener xListener = new org.eclipse.swt.events.ShellAdapter() {

	    @Override
	    public void shellIconified(org.eclipse.swt.events.ShellEvent e) {
		listener.handleEvent(createEvent(e));
	    }

	};
	widget.assignListener(listener, xListener);
	return xListener;
    }

    protected org.eclipse.swt.events.ShellListener createDeiconifyWindowListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.ShellListener xListener = new org.eclipse.swt.events.ShellAdapter() {

	    @Override
	    public void shellDeiconified(org.eclipse.swt.events.ShellEvent e) {
		listener.handleEvent(createEvent(e));
	    }

	};
	widget.assignListener(listener, xListener);
	return xListener;
    }


    
    /**
     * Create UWT Event by SWT ShellEvent
     * @param e
     * @return
     */
    protected Event createEvent(org.eclipse.swt.events.ShellEvent e) {
        Event event = new Event();
        ////
        event.setDevice(UWT.KEYBOARD_DEVICE);
        return event;
    }
    
    protected org.eclipse.swt.events.ShellListener getWindowListener(Widget widget, final Listener listener) {
	return (org.eclipse.swt.events.ShellListener) getListener(widget, listener);
    }

}
