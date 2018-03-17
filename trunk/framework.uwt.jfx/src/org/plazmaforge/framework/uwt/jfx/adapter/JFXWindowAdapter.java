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

import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.Notifier;
import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.UWT;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.jfx.util.JFXUtils;
import org.plazmaforge.framework.uwt.widget.Event;
import org.plazmaforge.framework.uwt.widget.Frame;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.Window;

import javafx.stage.Modality;
import javafx.stage.StageStyle;



/**
 * 
 * @author ohapon
 *
 */
public class JFXWindowAdapter extends JFXContainerAdapter {

    public Object createDelegate(UIElement parent, UIElement element) {
	
	Object xParent = null;
	if (parent != null) {
	    xParent = parent.getDelegate();
	}
	Window window = (Window) element;
	javafx.stage.Stage xWindow = createWindow(xParent, window);
	//addNotifierListener(window, xWindow);
	return xWindow;
    }
    
    protected javafx.stage.Stage createWindow(Object xParent, Window window) {
	javafx.stage.Stage xWindow = new javafx.stage.Stage();
	
	// Set position (left, top)
	xWindow.setX(0);
	xWindow.setY(0);
	
	// Set title
	String title = asSafeString(window.getTitle());
	xWindow.setTitle(title);

	// Update decoration
	updateDecoration(window, xWindow);
	
	javafx.scene.Scene xScene = JFXUtils.createScene();
	xWindow.setScene(xScene);
	
	if (window.isModal()) {
	    xWindow.initOwner(getPrimaryStage(window));
	    xWindow.initModality(Modality.WINDOW_MODAL);
	}
	
	return xWindow;
    }
  

    
    protected void updateDecoration(Window window, javafx.stage.Stage xWindow) {
	
	// Set style
	xWindow.setResizable(window.isResizable());
	if (window.isUndecorated()) {
	    // No decoration:
	    //  title - no,
	    //  min - no,
	    //  max - no,
	    //  close - no
	    xWindow.initStyle(StageStyle.UNDECORATED);
	} else {
	    // Utility decoration:
	    //  title - yes,
	    //  min - no,
	    //  max - no,
	    //  close - yes
	    if (!window.isMinimizable() && !window.isMaximizable()) {
		xWindow.initStyle(StageStyle.UTILITY);
	    }

	    // TODO: Not implemented in JFX
	    //  min - no/yes,
	    //  max - no/yes,
	    //  close - no/yes

	}	
    }
    
//    protected void addNotifierListener(final Window window, final org.eclipse.swt.widgets.Shell xWindow) {
//	final Notifier notifier = window.getNotifier();
//	xWindow.addShellListener(new org.eclipse.swt.events.ShellListener() {
//
//	    @Override
//	    public void shellActivated(org.eclipse.swt.events.ShellEvent event) {
//	    }
//
//	    @Override
//	    public void shellClosed(org.eclipse.swt.events.ShellEvent event) {
//		if (notifier == null) {
//		    return;
//		}
//		event.doit = false;
//		notifier.canNotify(Window.METHOD_CLOSE, new Callback<Boolean>() {
//
//		    @Override
//		    public void onFailure(Throwable error) {
//			// do nothing
//		    }
//
//		    @Override
//		    public void onSuccess(Boolean result) {
//			boolean can = result == null ? false: result;
//			if (!can) {
//			    return;
//			}
//			notifier.notify(Window.METHOD_CLOSE);
//			// Force close
//			doClose(window, xWindow);
//		    }
//		    
//		});
//		
//	    }
//
//	    @Override
//	    public void shellDeactivated(org.eclipse.swt.events.ShellEvent event) {
//	    }
//
//	    @Override
//	    public void shellDeiconified(org.eclipse.swt.events.ShellEvent event) {
//	    }
//
//	    @Override
//	    public void shellIconified(org.eclipse.swt.events.ShellEvent event) {
//	    }
//	    
//	});
//
//    }
   


    @Override
    public void setProperty(UIElement element, String name, Object value) {
	javafx.stage.Stage xWindow = (javafx.stage.Stage) element.getDelegate();
	if (xWindow == null) {
	    return;
	}
	if (Window.PROPERTY_TITLE.equals(name)) {
	    xWindow.setTitle(asSafeString(value));
	    return;
	} else if (Window.PROPERTY_ICON.equals(name)) {
	    javafx.scene.image.Image xIcon = createImage(element, asImage(value));
	    if (xIcon != null) {
		xWindow.getIcons().add(xIcon);
	    }
	    return;
	} else if (Window.PROPERTY_ICON_PATH.equals(name)) {
	    javafx.scene.image.Image xIcon = createImage(element, asString(value));
	    if (xIcon != null) {
		xWindow.getIcons().add(xIcon);
	    }
	    return;
	}
	super.setProperty(element, name, value);
    }
   
    @Override
    public Object invoke(UIElement element, String methodName, Object[] args) {
	javafx.stage.Stage xWindow = (javafx.stage.Stage) element.getDelegate();
	if (xWindow == null) {
	    return null;
	}
	Window window = (Window) element; 
	if (Window.METHOD_OPEN.equals(methodName)) {
	    doOpen(window, xWindow);
	} else if (Window.METHOD_CLOSE.equals(methodName)) {
	    doClose(window, xWindow);
	} else if (Window.METHOD_LAYOUT.equals(methodName)) {
	    //TODO
	    //xWindow.layout();
	    return null;
	} else if (Window.METHOD_PACK.equals(methodName)) {
	    doPack(xWindow);
	    return null;
	} else if (Window.METHOD_CENTER.equals(methodName)) {
	    doCenter(xWindow);
	    return null;
	} else if (Window.METHOD_ACTIVATE.equals(methodName)) {
	    xWindow.toFront();
	    return null;
	} else if (Window.METHOD_DEACTIVATE.equals(methodName)) {
	    xWindow.toBack();
	    return null;
	} else if (Window.METHOD_MAXIMIZE.equals(methodName)) {
	    xWindow.setMaximized(true);
	    return null;
	} else if (Window.METHOD_MINIMIZE.equals(methodName)) {
	    xWindow.setMaximized(false);
	    return null;
	}
	
	return super.invoke(element, methodName, args);
    }

    /**
     * Open window
     * @param window
     * @param xWindow
     */
    protected void doOpen(Window window, javafx.stage.Stage xWindow) {
	if  (xWindow == null) {
	    return;
	}
	//TODO
	//xWindow.layout();
	if (window.isPack()) {
	    doPack(xWindow);
	}
	if (window.isCenter()) {
	    doCenter(xWindow);
	}
	
	// Open window
	xWindow.show();
    }
    
    /**
     * Close window
     * @param window
     * @param xWindow
     */
    protected void doClose(Window window, javafx.stage.Stage xWindow) {
	if  (xWindow == null) {
	    return;
	}
	
	// Close window
	xWindow.hide();
    }

    protected void doPack(javafx.stage.Stage xWindow) {
	// TODO: Need pack size to preferred children area
	// but sizeToScene() pack to window size
	xWindow.sizeToScene();
    }
    
    protected void doCenter(javafx.stage.Stage xWindow) {
	xWindow.centerOnScreen();
    }
        

//    @Override
//    public void addListener(UIElement element, String eventType, final Listener listener) {
//	
//	Window window = (Window) element;
//	org.eclipse.swt.widgets.Shell xWindow = (org.eclipse.swt.widgets.Shell) element.getDelegate();
//	if (xWindow == null) {
//	    return;
//	}
//	
//	if (eq(eventType, Events.Open)) {
//	    xWindow.addShellListener(createOpenWindowListener(window, listener));
//	    return;
//	} else if (eq(eventType, Events.Close)) { 
//	    xWindow.addShellListener(createCloseWindowListener(window, listener));
//	    return;
//	} else if (eq(eventType, Events.Activate)) { 
//	    xWindow.addShellListener(createActivateWindowListener(window, listener));
//	    return;
//	} else if (eq(eventType, Events.Deactivate)) { 
//	    xWindow.addShellListener(createDeactivateWindowListener(window, listener));
//	    return;
//	} else if (eq(eventType, Events.Iconify)) { 
//	    xWindow.addShellListener(createIconifyWindowListener(window, listener));
//	    return;
//	} else if (eq(eventType, Events.Deiconify)) { 
//	    xWindow.addShellListener(createDeiconifyWindowListener(window, listener));
//	    return;
//	}
//	
// 	super.addListener(element, eventType, listener);
//    }
//    
//    @Override
//    public void removeListener(UIElement element, String eventType, Listener listener) {
//	
//	Window window = (Window) element;
//	org.eclipse.swt.widgets.Shell xWindow = (org.eclipse.swt.widgets.Shell) element.getDelegate();
//	if (xWindow == null) {
//	    return;
//	}
//
//	if (in(eventType, new Object[] {Events.Open, Events.Close, Events.Activate, Events.Deactivate, Events.Iconify, Events.Deiconify})) {
//	    xWindow.removeShellListener(getWindowListener(window, listener));
//	    return;
//	}
// 	
//	super.removeListener(element, eventType, listener);
//    }
//
//    ////
//    
//    
//    protected org.eclipse.swt.events.ShellListener createOpenWindowListener(Widget widget, final Listener listener) {
//	org.eclipse.swt.events.ShellListener xListener = new org.eclipse.swt.events.ShellAdapter() {
//
//	    @Override
//	    public void shellActivated(org.eclipse.swt.events.ShellEvent e) {
//		// Check first activation because the event is 'OpenWindow'
//		boolean active = (e.widget != null && "true".equals(e.widget.getData("$active")));
//		if (active) {
//		    return;
//		}
//		if (e.widget != null) {
//		    e.widget.setData("$active", true);
//		}
//		listener.handleEvent(createEvent(e));
//	    }
//
//	};
//	widget.assignListener(listener, xListener);
//	return xListener;
//    }
//
//    protected org.eclipse.swt.events.ShellListener createCloseWindowListener(Widget widget, final Listener listener) {
//	org.eclipse.swt.events.ShellListener xListener = new org.eclipse.swt.events.ShellAdapter() {
//
//	    @Override
//	    public void  shellClosed(org.eclipse.swt.events.ShellEvent e) {
//		listener.handleEvent(createEvent(e));
//	    }
//
//   	};
//   	widget.assignListener(listener, xListener);
//   	return xListener;
//   }
//
//    protected org.eclipse.swt.events.ShellListener createActivateWindowListener(Widget widget, final Listener listener) {
//	org.eclipse.swt.events.ShellListener xListener = new org.eclipse.swt.events.ShellAdapter() {
//
//	    @Override
//	    public void shellActivated(org.eclipse.swt.events.ShellEvent e) {
//		listener.handleEvent(createEvent(e));
//	    }
//
//	};
//	widget.assignListener(listener, xListener);
//	return xListener;
//    }
//    
//    protected org.eclipse.swt.events.ShellListener createDeactivateWindowListener(Widget widget, final Listener listener) {
//	org.eclipse.swt.events.ShellListener xListener = new org.eclipse.swt.events.ShellAdapter() {
//
//	    @Override
//	    public void shellDeactivated(org.eclipse.swt.events.ShellEvent e) {
//		listener.handleEvent(createEvent(e));
//	    }
//
//	};
//	widget.assignListener(listener, xListener);
//	return xListener;
//    }
//    
//    
//    protected org.eclipse.swt.events.ShellListener createIconifyWindowListener(Widget widget, final Listener listener) {
//	org.eclipse.swt.events.ShellListener xListener = new org.eclipse.swt.events.ShellAdapter() {
//
//	    @Override
//	    public void shellIconified(org.eclipse.swt.events.ShellEvent e) {
//		listener.handleEvent(createEvent(e));
//	    }
//
//	};
//	widget.assignListener(listener, xListener);
//	return xListener;
//    }
//
//    protected org.eclipse.swt.events.ShellListener createDeiconifyWindowListener(Widget widget, final Listener listener) {
//	org.eclipse.swt.events.ShellListener xListener = new org.eclipse.swt.events.ShellAdapter() {
//
//	    @Override
//	    public void shellDeiconified(org.eclipse.swt.events.ShellEvent e) {
//		listener.handleEvent(createEvent(e));
//	    }
//
//	};
//	widget.assignListener(listener, xListener);
//	return xListener;
//    }
//
//
//    
//    /**
//     * Create UWT Event by SWT ShellEvent
//     * @param e
//     * @return
//     */
//    protected Event createEvent(org.eclipse.swt.events.ShellEvent e) {
//        Event event = new Event();
//        ////
//        event.setDevice(UWT.KEYBOARD_DEVICE);
//        return event;
//    }
//    
//    protected org.eclipse.swt.events.ShellListener getWindowListener(Widget widget, final Listener listener) {
//	return (org.eclipse.swt.events.ShellListener) getListener(widget, listener);
//    }

}
