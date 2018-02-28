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


import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.UWT;
import org.plazmaforge.framework.uwt.UWTException;
import org.plazmaforge.framework.uwt.event.KeyEvent;
import org.plazmaforge.framework.uwt.widget.Event;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;

import javafx.beans.value.ObservableValue;


/**
 * 
 * @author ohapon
 *
 */
public abstract class JFXWidgetAdapter extends JFXAbstractAdapter {
    
    
    // Check
    protected void checkNullParent(javafx.scene.Node parent, String title) {
	if (parent == null) {
	    throw new UWTException(title + ". Parent is null");
	}
    }
    
    // Check
    protected void checkContainerParent(javafx.scene.Node parent, String title) {
	if (!(parent instanceof javafx.scene.Parent)) {
	    throw new UWTException(title + ". Parent is not container: " + parent.getClass().getName());
	}
    } 
    
    // Throw
    protected void throwUnsupportParent(javafx.scene.Node parent, String title) {
	throw new UWTException(title + ". Parent is not supported: " + parent.getClass().getName());
    }     
    
    @Override
    protected void log(String message) {
	//JFX implementation
	System.out.println(message);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Cast
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Returns JFX Node
     * @param delegate
     * @return
     */
    protected final javafx.scene.Node asNode(Object delegate) {
	return (javafx.scene.Node) delegate;
    }
    
    /**
     * Returns JFX Parent (Container)
     * @param delegate
     * @return
     */
    protected javafx.scene.Parent asParent(Object delegate) {
   	return (javafx.scene.Parent) delegate;
    }
    
    /**
     * Returns JFX Control
     * @param delegate
     * @return
     */
    protected final javafx.scene.control.Control asControl(Object delegate) {
	return (javafx.scene.control.Control) delegate;
    }
    
    /**
     * Return true if delegate is JFX Node
     * @param delegate
     * @return
     */
    protected final boolean isNode(Object delegate) {
	return delegate instanceof javafx.scene.Node;
    }    
    
    /**
     * Returns true if delegate is JFX Parent
     * @param delegate
     * @return
     */
    protected final boolean isParent(Object delegate) {
	return delegate instanceof javafx.scene.Parent;
    } 
    
    /**
     * Checks delegate
     * @param delegate
     * @param property
     * @return JFX Node
     */
    protected javafx.scene.Node asNodeGetProperty(Object delegate, String property) {
	if (!isNode(delegate)) {
	    logUnsupportGetProperty(delegate, property);
	    return null;
	}
	return asNode(delegate);
    }  
    
    /**
     * Checks delegate
     * @param delegate
     * @param property
     * @return JFX Node
     */
    protected javafx.scene.Node asNodeSetProperty(Object delegate, String property) {
  	if (!isNode(delegate)) {
  	    logUnsupportSetProperty(delegate, property);
  	    return null;
  	}
  	return asNode(delegate);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Content
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Returns real content of UI object 
     * @param delegate
     * @return
     */
    protected final javafx.scene.Parent getContent(Object delegate) {
	// TODO
	return getView(delegate);
    }

    protected final javafx.scene.Parent getView(Object delegate) {
	if (delegate instanceof javafx.scene.Scene) {
	    //////////////////////////////////////////////////////////////
	    // Scene: content is root
	    //////////////////////////////////////////////////////////////
	    return ((javafx.scene.Scene) delegate).getRoot();
	} else if (delegate instanceof javafx.stage.Stage) {
	    
	    //////////////////////////////////////////////////////////////
	    // The Primary Stage: content is root of scene
	    //////////////////////////////////////////////////////////////
	    javafx.stage.Stage stage = (javafx.stage.Stage) delegate;
	    
	    // Get scene
	    javafx.scene.Scene scene = stage.getScene();
	    return scene == null ? null : scene.getRoot();
	    
	} else if (delegate instanceof javafx.scene.control.Tab) {
	    return asParent(((javafx.scene.control.Tab) delegate).getContent());
	}
	//throw new UWTException("getView: Object is not supported: " + delegate.getClass().getName());
	// TODO
	return (javafx.scene.Parent) delegate;
    }
    
    protected final Object getRegion(Object delegate) {
	if (delegate instanceof javafx.scene.Scene) {
	    return ((javafx.scene.Scene) delegate).getRoot();
	} else if (delegate instanceof javafx.stage.Stage) {
	    javafx.stage.Stage stage = (javafx.stage.Stage) delegate;
	    javafx.scene.Scene scene = stage.getScene();
	    return scene == null ? null : scene.getRoot();
	}
	//throw new UWTException("getRegion: Object is not supported: " + delegate.getClass().getName());
	// TODO
	return delegate;
    }
    
    protected void addChild(javafx.scene.Parent xParent, javafx.scene.Node xWidget, UIElement element) {
	//do nothing
    }

    
//    protected void disposeWidget(org.eclipse.swt.widgets.Widget widget) {
//	if (!widget.isDisposed()) {
//	    widget.dispose();
//	}
//    }
//    
//    
//    
//    
    public void disposeDelegate(UIElement parent, UIElement element) {
	//TODO
//	org.eclipse.swt.widgets.Widget widget = asWidget(element.getDelegate());
//	if (!widget.isDisposed()) {
//	    widget.dispose();
//	}
    }
    
    protected void setSize(Object delegate, int width, int height) {
	//Object region = getRegion(delegate);
	if (delegate instanceof javafx.stage.Window) {
	    javafx.stage.Window window = (javafx.stage.Window) delegate;
	    window.setWidth(width);
	    window.setHeight(height);
	} else if (delegate instanceof javafx.scene.layout.Region) {
	    javafx.scene.layout.Region region = (javafx.scene.layout.Region) delegate;
	    region.setPrefSize(width, height);
	}
	//TODO
    }
    
    protected void setWidth(Object delegate, int width) {
 	//Object region = getRegion(delegate);
 	if (delegate instanceof javafx.stage.Window) {
 	    javafx.stage.Window window = (javafx.stage.Window) delegate;
 	    window.setWidth(width);
 	} else if (delegate instanceof javafx.scene.layout.Region) {
 	    javafx.scene.layout.Region region = (javafx.scene.layout.Region) delegate;
 	    region.setPrefWidth(width);
 	}
 	//TODO
     }
   
    protected void setHeight(Object delegate, int height) {
  	//Object region = getRegion(delegate);
  	if (delegate instanceof javafx.stage.Window) {
  	    javafx.stage.Window window = (javafx.stage.Window) delegate;
  	    window.setHeight(height);
  	} else if (delegate instanceof javafx.scene.layout.Region) {
  	    javafx.scene.layout.Region region = (javafx.scene.layout.Region) delegate;
 	    region.setPrefHeight(height);
  	}
  	//TODO
    }   
    
    protected void setVisible(Object delegate, boolean visible) {
	javafx.scene.Node node = asNodeSetProperty(delegate, Widget.PROPERTY_VISIBLE);
	if (node == null) {
	    return;
	}
	node.setVisible(visible);
    }
    
    protected boolean isVisible(Object delegate) {
	javafx.scene.Node node = asNodeSetProperty(delegate, Widget.PROPERTY_VISIBLE);
	if (node == null) {
	    return false;
	}
	return node.isVisible();
    }  
    
    protected void setEnabled(Object delegate, boolean enabled) {
	javafx.scene.Node node = asNodeSetProperty(delegate, Widget.PROPERTY_ENABLED);
	if (node == null) {
	    return;
	}
	node.setDisable(!enabled);
    }

    protected boolean isEnabled(Object delegate) {
	javafx.scene.Node node = asNodeSetProperty(delegate, Widget.PROPERTY_ENABLED);
	if (node == null) {
	    return false;
	}
	return !node.isDisable();
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	Object xElement = element.getDelegate();
	if (!isNode(xElement)) {
	    logUnsupportSetProperty(xElement, name);
	    return;
	}
	
	javafx.scene.Node widget = asNode(element.getDelegate());
	if (widget == null) {
	    return;
	}
	if (Widget.PROPERTY_DATA.equals(name)) {
	    widget.setUserData(value);
	} else if (startsWith(name, Widget.PROPERTY_DATA_PREFIX)) {
	    // WARNING! We use '@' to separate 'data' and 'key' 
	    String key = name.substring(Widget.PROPERTY_DATA_PREFIX.length());
	    
	    //TODO
	    //widget.setUserData(key, value);
	    return;	
	}
	
	super.setProperty(element, name, value);
    }
    
    
    @Override
    public Object getProperty(UIElement element, String name) {
	return super.getProperty(element, name);
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // KEY LISTENERS
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected void addKeyDownListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeKeyDownListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    

    
    protected void addKeyUpListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    
    protected void removeKeyUpListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // MOUSE LISTENERS
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void addMouseDownListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeMouseDownListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    

    protected void addMouseUpListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeMouseUpListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    
    
    protected void addMouseClickListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    
    protected void removeMouseClickListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    
    
    protected void addMouseDoubleClickListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    
    protected void removeMouseDoubleClickListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // MOUSE MOVE LISTENERS
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected void addMouseMoveListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeMouseMoveListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    
    
    protected void addMouseInListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    
    protected void removeMouseInListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    

    protected void addMouseOutListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    
    protected void removeMouseOutListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // FOCUS LISTENERS
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void addFocusInListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeFocusInListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    
    
    protected void addFocusOutListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeFocusOutListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // SELECTION LISTENER
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected void addSelectionListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeSelectionListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // ENTER LISTENER
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void addEnterListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }

    protected void removeEnterListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	// do nothing
    }    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Listeners
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
    @SuppressWarnings("rawtypes")
    protected javafx.event.EventHandler getListener(Widget widget, Listener listener) {
	return (javafx.event.EventHandler) widget.findListenerDelegate(listener);
    }

    @SuppressWarnings("rawtypes")
    protected javafx.event.EventHandler getListener(Widget widget, Listener listener, int index) {
	return (javafx.event.EventHandler) widget.findListenerDelegate(listener, index);
    }
     
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BASE LISTENER
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    protected <T extends javafx.event.Event> javafx.event.EventHandler<T> createListener(Class<T> klass, Widget widget, final Listener listener) {
	javafx.event.EventHandler<T> xListener = new javafx.event.EventHandler<T>() {

	    @Override
	    public void handle(T e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }
    
    
    // KEY
    protected javafx.event.EventHandler<javafx.scene.input.KeyEvent> createKeyListener(Widget widget, final Listener listener) {
	return createListener(javafx.scene.input.KeyEvent.class, widget, listener);
    }

    @SuppressWarnings("unchecked")
    protected javafx.event.EventHandler<javafx.scene.input.KeyEvent> getKeyListener(Widget widget, Listener listener) {
	return (javafx.event.EventHandler<javafx.scene.input.KeyEvent>) getListener(widget, listener);
    }
    

    // MOUSE
    protected javafx.event.EventHandler<javafx.scene.input.MouseEvent> createMouseListener(Widget widget, final Listener listener) {
	return createListener(javafx.scene.input.MouseEvent.class, widget, listener);
    }
    
    @SuppressWarnings("unchecked")
    protected javafx.event.EventHandler<javafx.scene.input.MouseEvent> getMouseListener(Widget widget, Listener listener) {
	return (javafx.event.EventHandler<javafx.scene.input.MouseEvent>) getListener(widget, listener);
    }
    
    
//    // MOUSE DOUBLE CLICK
//    protected org.eclipse.swt.events.MouseListener createMouseDoubleClickListener(Widget widget, final Listener listener) {
//	org.eclipse.swt.events.MouseListener xListener = new org.eclipse.swt.events.MouseAdapter() {
//
//	    @Override
//	    public void mouseDoubleClick(org.eclipse.swt.events.MouseEvent e) {
//		listener.handleEvent(createEvent(e));
//	    }
//	};
//	widget.assignListener(listener, xListener);
//	return xListener;
//    }
//

  
    
    // FOCUS IN
    protected javafx.beans.value.ChangeListener<Boolean> createFocusInListener(Widget widget, final Listener listener) {
	javafx.beans.value.ChangeListener<Boolean> xlistener = new javafx.beans.value.ChangeListener<Boolean>() {

	    @Override
	    public void changed(ObservableValue<? extends Boolean> e, Boolean oldValue, Boolean newValue) {
		// newValue: true - in focus
		if (newValue) {
	            listener.handleEvent(createEvent(e));
	        }
	    }
	};
	widget.assignListener(listener, xlistener);
	return xlistener;
    }
   
    // FOCUS OUT
    protected javafx.beans.value.ChangeListener<Boolean> createFocusOutListener(Widget widget, final Listener listener) {
	javafx.beans.value.ChangeListener<Boolean> xlistener = new javafx.beans.value.ChangeListener<Boolean>() {

	    @Override
	    public void changed(ObservableValue<? extends Boolean> e, Boolean oldValue, Boolean newValue) {
		// newValue: false - out focus
		if (!newValue) {
	            listener.handleEvent(createEvent(e));
	        }
	    }
	};
	widget.assignListener(listener, xlistener);
	return xlistener;
    }
    
    @SuppressWarnings("unchecked")
    protected javafx.beans.value.ChangeListener<Boolean> getFocusListener(Widget widget, Listener listener) {
	return (javafx.beans.value.ChangeListener<Boolean>) getListener(widget, listener);
    }
    
    
//    ////
//
//    protected org.eclipse.swt.events.SelectionListener createSelectionListener(Widget widget, final Listener listener) {
//	org.eclipse.swt.events.SelectionListener xListener = new org.eclipse.swt.events.SelectionAdapter() {
//
//	    @Override
//	    public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
//		listener.handleEvent(createEvent(e));
//	    }
//
//	};
//	widget.assignListener(listener, xListener);
//	return xListener;
//    }
//    
//    protected org.eclipse.swt.events.SelectionListener getSelectionListener(Widget widget, final Listener listener) {
//	return (org.eclipse.swt.events.SelectionListener) getListener(widget, listener);
//    }
//    
//
//    protected org.eclipse.swt.events.SelectionListener createDefaultSelectionListener(Widget widget, final Listener listener) {
//	org.eclipse.swt.events.SelectionListener xListener = new org.eclipse.swt.events.SelectionAdapter() {
//
//	    @Override
//	    public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
//		listener.handleEvent(createEvent(e));
//	    }
//	};
//	widget.assignListener(listener, xListener);
//	return xListener;
//    }
//    
//    
//    
//    // M_ENTER (MOUSE DOUBLE CLICK)
//    protected org.eclipse.swt.events.MouseListener createMEnterListener(Widget widget, final Listener listener) {
//	org.eclipse.swt.events.MouseListener xListener = new org.eclipse.swt.events.MouseAdapter() {
//
//	    @Override
//	    public void mouseDoubleClick(org.eclipse.swt.events.MouseEvent e) {
//		listener.handleEvent(createEvent(e));
//	    }
//	};
//	widget.assignListener(listener, xListener);
//	return xListener;
//    }
//    
//
//    // K_ENTER (KEY ENTER [13])
//    protected org.eclipse.swt.events.KeyListener createKEnterListener(Widget widget, final Listener listener) {
//	org.eclipse.swt.events.KeyListener xListener = new org.eclipse.swt.events.KeyAdapter() {
//
//	    @Override
//	    public void keyReleased(org.eclipse.swt.events.KeyEvent e) {
//		if (e.character != SWT.CR) {
//		    return;
//		}
//		listener.handleEvent(createEvent(e));
//	    }
//	};
//	widget.assignListener(listener, xListener);
//	return xListener;
//    }

    

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Events
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected Event createEvent(javafx.event.Event e) {
	if (e instanceof javafx.scene.input.KeyEvent) {
	    return createEvent((javafx.scene.input.KeyEvent) e);
	} if (e instanceof javafx.scene.input.MouseEvent) {
	    return createEvent((javafx.scene.input.MouseEvent) e);
	}
	
	//TODO
	return null;
    }
    
    /**
     * Create UWT Event by JFX KeyEvent
     * @param e
     * @return
     */
    protected Event createEvent(javafx.scene.input.KeyEvent e) {
        Event event = new Event();
        javafx.scene.input.KeyCode keyCode = e.getCode();
        String string = e.getCharacter();
        
        //TODO
        if (keyCode != null) {
            event.setKeyCode(keyCode.impl_getCode());
        }
        //TODO
        if (string != null && !string.isEmpty()) {
            event.setCharacter(string.toCharArray()[0]);    
        }
        
        event.setStateMask(getStateMask(e)); // Convert state mask form JFX to UWT
        ////
        event.setDevice(UWT.KEYBOARD_DEVICE);
        return event;
    }
    
    /**
     * Create UWT Event by SWT MouseEvent
     * @param e
     * @return
     */
    protected Event createEvent(javafx.scene.input.MouseEvent e) {
        Event event = new Event();
        
        //TODO
        //event.setButton(e.getButton().); // SWT.BUTTON1 = 1, SWT.BUTTON2 = 2, SWT.BUTTON3 = 3
        
        
        event.setX((int) e.getX());
        event.setY((int) e.getY());
	event.setStateMask(getStateMask(e)); // Convert state mask form SWT to UWT
        event.setCount(e.getClickCount());
        ////
        event.setDevice(UWT.MOUSE_DEVICE);
        return event;
    }
    
    /**
     * Convert state mask from JFX to UWT
     * @return
     */
    protected int getStateMask(javafx.scene.input.KeyEvent xStateMask) {
	int stateMask = 0;
	if (xStateMask.isShiftDown()) {
	    stateMask |= KeyEvent.SHIFT_MASK;
	}
	if (xStateMask.isControlDown()) {
	    stateMask |= KeyEvent.CTRL_MASK;
	}
	if (xStateMask.isMetaDown()) {
	    stateMask |= KeyEvent.META_MASK;
	}
	if (xStateMask.isAltDown()) {
	    stateMask |= KeyEvent.ALT_MASK;
	}
	return stateMask;
    }
    
    protected int getStateMask(javafx.scene.input.MouseEvent xStateMask) {
 	int stateMask = 0;
 	if (xStateMask.isShiftDown()) {
 	    stateMask |= KeyEvent.SHIFT_MASK;
 	}
 	if (xStateMask.isControlDown()) {
 	    stateMask |= KeyEvent.CTRL_MASK;
 	}
 	if (xStateMask.isMetaDown()) {
 	    stateMask |= KeyEvent.META_MASK;
 	}
 	if (xStateMask.isAltDown()) {
 	    stateMask |= KeyEvent.ALT_MASK;
 	}
 	return stateMask;
     }    

    /**
     * Create UWT Event by JFX FocusEvent
     * @param e
     * @return
     */
    protected Event createEvent(ObservableValue<? extends Boolean> e) {
        Event event = new Event();
        return event;
    }
    
//    protected Event createEvent(org.eclipse.swt.events.SelectionEvent e) {
//        Event event = new Event();
//        event.setX(e.x);
//        event.setY(e.y);
//        event.setStateMask(e.stateMask);
//        //event.count = e.count;
//        
//        return event;
//    }

    

}
