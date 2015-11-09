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

package org.plazmaforge.framework.uwt.swing.adapter;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.WindowConstants;

import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.Notifier;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.Event;
import org.plazmaforge.framework.uwt.widget.Frame;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.Window;
import org.plazmaforge.framework.uwt.widget.menu.MenuBar;

public class SwingWindowAdapter extends SwingCompositeAdapter {

    
    public Object createDelegate(UIObject parent, UIObject element) {
	Object xParent = parent == null ? null : parent.getDelegate();
	Window window = (Window) element;
	if (window.isModal()) {
	    return createDialog(xParent, window);
	}
	return createFrame(xParent, window);
    }

    protected javax.swing.JFrame createFrame(Object xParent, Window window) {
	javax.swing.JFrame xFrame = new javax.swing.JFrame();
	xFrame.setResizable(window.isResizable());
	xFrame.setUndecorated(window.isUndecorated());
	
	//xFrame.removeAllTitleFrameButtons();
	
	//Disable min/max button;
	//xFrame.setMaximumSize(xFrame.getMinimumSize());
	//xFrame.setResizable(false);
	
	
	getContent(xFrame).setLayout(createDefaultCompositeLayout()); // By default
	addNotifierListeners(window, xFrame);
	xFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	return xFrame;
    }

    protected javax.swing.JDialog createDialog(Object xParent, Window window) {
	javax.swing.JDialog xDialog = new javax.swing.JDialog((javax.swing.JFrame) null, window.isModal()); // TODO: Check parent
	xDialog.setResizable(window.isResizable());
	xDialog.setUndecorated(window.isUndecorated());
	getContent(xDialog).setLayout(createDefaultCompositeLayout()); // By default
	addNotifierListeners(window, xDialog);
	xDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	return xDialog;
    }
    
    protected void addNotifierListeners(final Window window, final java.awt.Window xWindow) {
	final Notifier notifier = window.getNotifier();
	xWindow.addWindowListener(new WindowListener() {
	    
	    @Override
	    public void windowOpened(WindowEvent e) {
	    }
	    
	    @Override
	    public void windowIconified(WindowEvent e) {
	    }
	    
	    @Override
	    public void windowDeiconified(WindowEvent e) {
	    }
	    
	    @Override
	    public void windowDeactivated(WindowEvent e) {
	    }
	    
	    @Override
	    public void windowClosing(WindowEvent e) {
		if (notifier == null) {
		    doClose(window, xWindow);
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
	    
	    @Override
	    public void windowClosed(WindowEvent e) {
	    }
	    
	    @Override
	    public void windowActivated(WindowEvent e) {
	    }
	});
    }

    protected void setWindowProperty(java.awt.Window xWindow, UIObject element, String name, Object value) {
	if (xWindow == null) {
	    return;
	}
	
	if (xWindow instanceof javax.swing.JFrame) {
	    setFrameProperty((javax.swing.JFrame) xWindow, element, name, value);
	    return;
	} else if (xWindow instanceof javax.swing.JDialog) {
	    setDialogProperty((javax.swing.JDialog) xWindow, element, name, value);
	    return;
	}
	
	setCommonProperty(xWindow, element, name, value);
    }

    protected void setCommonProperty(java.awt.Window xWindow, UIObject element, String name, Object value) {
	if (xWindow == null) {
	    return;
	}
	
	// Common properties
	if (Window.PROPERTY_ICON.equals(name)) {
	    javax.swing.ImageIcon xImage = createImageIcon(element, (Image) value);
	    if (xImage != null) {
		xWindow.setIconImage(xImage.getImage());
	    }
	    return;
	} else if (Window.PROPERTY_ICON_PATH.equals(name)) {
	    javax.swing.ImageIcon xImage = createImageIcon(element, (String) value);
	    if (xImage != null) {
		xWindow.setIconImage(xImage.getImage());
	    }
	    return;
	}
	 
	super.setProperty(element, name, value);
    }
    
    
    protected void setFrameProperty(javax.swing.JFrame xFrame, UIObject element, String name, Object value) {
	if (xFrame == null) {
	    return;
	}
	if (Frame.PROPERTY_TITLE.equals(name)) {
	    xFrame.setTitle(getSafeString(value));
	    return;
	
	} else if (Frame.PROPERTY_MENU_BAR.equals(name)) {
	    MenuBar menuBar = (MenuBar) value;
	    if (menuBar != null) {
		menuBar.activateUI();
		xFrame.setJMenuBar((javax.swing.JMenuBar) menuBar.getDelegate());
	    }
	    return;
	}
	setCommonProperty(xFrame, element, name, value);
    }

    public void setDialogProperty(javax.swing.JDialog xDialog, UIObject element, String name, Object value) {
	if (xDialog == null) {
	    return;
	}
	if (Frame.PROPERTY_TITLE.equals(name)) {
	    xDialog.setTitle(getSafeString(value));
	    return;
	} else if (Frame.PROPERTY_MENU_BAR.equals(name)) {
	    MenuBar menuBar = (MenuBar) value;
	    if (menuBar != null) {
		menuBar.activateUI();
		xDialog.setJMenuBar((javax.swing.JMenuBar) menuBar.getDelegate());
	    }
	    return;
	}
	setCommonProperty(xDialog, element, name, value);
    }
    
    
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	return invokeWindow((Window) element, (java.awt.Window) element.getDelegate(), methodName, args);
    }


    protected Object invokeWindow(Window window, java.awt.Window xWindow, String methodName, Object[] args) {
	if (window == null || xWindow == null) {
	    return null;
	}
	if (Window.METHOD_OPEN.equals(methodName)) {
	    doOpen(window, xWindow);
	} else if (Window.METHOD_CLOSE.equals(methodName)) {
	    doClose(window, xWindow);
	} else if (Window.METHOD_LAYOUT.equals(methodName)) {
	    xWindow.doLayout();
	    return null;
	} else if (Window.METHOD_PACK.equals(methodName) ) {
	    xWindow.pack();
	    return null;
	} else if (Window.METHOD_CENTER.equals(methodName) ) {
	    xWindow.setLocationRelativeTo(null);
	    return null;
	} else if (Window.METHOD_ACTIVATE.equals(methodName) ) {
	    xWindow.setVisible(true);
	    xWindow.toFront();
	    return null;
	} else if (Window.METHOD_DEACTIVATE.equals(methodName) ) {
	    xWindow.toBack();
	    return null;
	} else if (Window.METHOD_MAXIMIZE.equals(methodName) ) {
	    //TODO
	    if (xWindow instanceof java.awt.Frame) {
		((java.awt.Frame) xWindow).setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	    }
	    return null;
	} else if (Window.METHOD_MINIMIZE.equals(methodName) ) {
	    //TODO
	    if (xWindow instanceof java.awt.Frame) {
		((java.awt.Frame) xWindow).setExtendedState(java.awt.Frame.ICONIFIED);
	    }
	    return null;
	}

	
	return super.invoke(window, methodName, args);
    }
    
    protected void doOpen(Window window, java.awt.Window xWindow) {
	// Order of command is very important
	// because after open modal dialog we can't do layout or pack

	xWindow.doLayout();
	if (window.isPack()) {
	    xWindow.pack();
	}
	if (window.isCenter()) {
	    xWindow.setLocationRelativeTo(null);
	}
	xWindow.setVisible(true);
    }
    
    protected void doClose(Window window, java.awt.Window xWindow) {
	xWindow.setVisible(false);
	if (window.isDisposeWhenClose()) {
	    xWindow.dispose();
	}
    }
    

    @Override
    public void setProperty(UIObject element, String name, Object value) {
	Object delegate = element.getDelegate();
	setWindowProperty((java.awt.Window) delegate, element, name, value);
    }
    
    

    @Override
    public void addListener(UIObject element, String eventType, Listener listener) {
	Window window = (Window) element;
	java.awt.Window xWindow = (java.awt.Window) element.getDelegate();
	if (xWindow == null) {
	    return;
	}
	if (eq(eventType, Events.Open)) {
	    xWindow.addWindowListener(createOpenWindowListener(window, listener));
	    return;
	} else if (eq(eventType, Events.Close)) {
	    xWindow.addWindowListener(createCloseWindowListener(window, listener));
	    return;
	} else if (eq(eventType, Events.Activate)) {
	    xWindow.addWindowListener(createActivateWindowListener(window, listener));
	    return;
	} else if (eq(eventType, Events.Deactivate)) {
	    xWindow.addWindowListener(createDeactivateWindowListener(window, listener));
	    return;
	} else if (eq(eventType, Events.Iconify)) {
	    xWindow.addWindowListener(createIconifyWindowListener(window, listener));
	    return;
	} else if (eq(eventType, Events.Deiconify)) {
	    xWindow.addWindowListener(createDeiconifyWindowListener(window, listener));
	    return;
	} 
 
 	super.addListener(element, eventType, listener);
    }
    
    @Override
    public void removeListener(UIObject element, String eventType, Listener listener) {
	Window window = (Window) element;
	java.awt.Window xWindow = (java.awt.Window) element.getDelegate();
	if (xWindow == null) {
	    return;
	}
	if (in(eventType, new Object[] {Events.Open, Events.Close, Events.Activate, Events.Deactivate, Events.Iconify, Events.Deiconify})) {
	    xWindow.removeWindowListener(getWindowListener(window, listener));
	    return;
	} 
 	
	super.removeListener(element, eventType, listener);
    }
    
    
    protected java.awt.event.WindowListener createOpenWindowListener(Widget widget, final Listener listener) {
	java.awt.event.WindowListener xListener = new java.awt.event.WindowAdapter() {

	    	@Override
	    	public void windowOpened(WindowEvent e) {
	    	    listener.handleEvent(createEvent(e));
	    	}
	    	
	    };
	widget.assignListener(listener, xListener);
	return xListener;
    }
    
    protected java.awt.event.WindowListener createCloseWindowListener(Widget widget, final Listener listener) {
	java.awt.event.WindowListener xListener = new java.awt.event.WindowAdapter() {

	    	@Override
	    	public void windowClosed(WindowEvent e) {
	    	    listener.handleEvent(createEvent(e));
	    	}
	    	
	    };
	widget.assignListener(listener, xListener);
	return xListener;
    }

    protected java.awt.event.WindowListener createActivateWindowListener(Widget widget, final Listener listener) {
	java.awt.event.WindowListener xListener = new java.awt.event.WindowAdapter() {

	    	@Override
	    	public void windowActivated(WindowEvent e) {
	    	    listener.handleEvent(createEvent(e));
	    	}
	    	
	    };
	widget.assignListener(listener, xListener);
	return xListener;
    }
    
    protected java.awt.event.WindowListener createDeactivateWindowListener(Widget widget, final Listener listener) {
	java.awt.event.WindowListener xListener = new java.awt.event.WindowAdapter() {

	    	@Override
	    	public void windowDeactivated(WindowEvent e) {
	    	    listener.handleEvent(createEvent(e));
	    	}
	    	
	    };
	widget.assignListener(listener, xListener);
	return xListener;
    }
    
    protected java.awt.event.WindowListener createIconifyWindowListener(Widget widget, final Listener listener) {
	java.awt.event.WindowListener xListener = new java.awt.event.WindowAdapter() {

	    	@Override
	    	public void windowIconified(WindowEvent e) {
	    	    listener.handleEvent(createEvent(e));
	    	}
	    	
	    };
	widget.assignListener(listener, xListener);
	return xListener;
    }
    
    protected java.awt.event.WindowListener createDeiconifyWindowListener(Widget widget, final Listener listener) {
	java.awt.event.WindowListener xListener = new java.awt.event.WindowAdapter() {

	    	@Override
	    	public void windowDeiconified(WindowEvent e) {
	    	    listener.handleEvent(createEvent(e));
	    	}
	    	
	    };
	widget.assignListener(listener, xListener);
	return xListener;
    }
    
    


    /**
     * Create UWT Event by Swing <code>WindowEvent</code>
     * @param e
     * @return
     */
    protected Event createEvent(java.awt.event.WindowEvent e) {
        Event event = new Event();
        //
        return event;
    }
    
    protected java.awt.event.WindowListener getWindowListener(Widget widget, final Listener listener) {
  	return (java.awt.event.WindowListener) widget.findListenerDelegate(listener);
      }

}
