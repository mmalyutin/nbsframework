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
import org.eclipse.swt.widgets.Display;
import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.UWT;
import org.plazmaforge.framework.uwt.UWTException;
import org.plazmaforge.framework.uwt.event.KeyEvent;
import org.plazmaforge.framework.uwt.swt.widget.XDesktopItem;
import org.plazmaforge.framework.uwt.widget.Event;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;


/**
 * 
 * @author ohapon
 *
 */
public abstract class SWTWidgetAdapter extends SWTAbstractAdapter {
    
    // Check
    protected void checkNullParent(org.eclipse.swt.widgets.Widget parent, String title) {
	if (parent == null) {
	    throw new UWTException(title + ". Parent is null");
	}
    }
    
    // Check
    protected void checkContainerParent(org.eclipse.swt.widgets.Widget parent, String title) {
	if (!(parent instanceof org.eclipse.swt.widgets.Composite)) {
	    throw new UWTException(title + ". Parent is not container: " + parent.getClass().getName());
	}
    } 
    
    // Throw
    protected void throwUnsupportParent(org.eclipse.swt.widgets.Widget parent, String title) {
	throw new UWTException(title + ". Parent is not supported: " + parent.getClass().getName());
    }     
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Cast
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Returns SWT Widget
     * @param delegate
     * @return
     */
    protected final org.eclipse.swt.widgets.Widget asWidget(Object delegate) {
	return (org.eclipse.swt.widgets.Widget) delegate;
    }
    
    /**
     * Returns SWT Control
     * @param delegate
     * @return
     */
    protected final org.eclipse.swt.widgets.Control asControl(Object delegate) {
	return (org.eclipse.swt.widgets.Control) delegate;
    }
    
    /**
     * Returns SWT Composite
     * @param delegate
     * @return
     */
    protected org.eclipse.swt.widgets.Composite asComposite(Object delegate) {
   	return (org.eclipse.swt.widgets.Composite) delegate;
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
    protected final org.eclipse.swt.widgets.Composite getContent(Object delegate) {
	
//	if (delegate instanceof org.eclipse.swt.widgets.TabItem) {
//	    return (org.eclipse.swt.widgets.Composite) ((org.eclipse.swt.widgets.TabItem) delegate).getControl();    
//	} else if (delegate instanceof XDesktopItem) {
//	    return (org.eclipse.swt.widgets.Composite) ((XDesktopItem) delegate).getContent();
//	} if (delegate instanceof org.eclipse.swt.custom.ScrolledComposite) {
//	    return (org.eclipse.swt.widgets.Composite) ((org.eclipse.swt.custom.ScrolledComposite) delegate).getContent();
//	}
//	return (org.eclipse.swt.widgets.Composite) delegate;
	
	if (delegate instanceof org.eclipse.swt.custom.ScrolledComposite) {
	    return (org.eclipse.swt.widgets.Composite) ((org.eclipse.swt.custom.ScrolledComposite) delegate).getContent();
	}
	return getView(delegate);
    }

    protected final org.eclipse.swt.widgets.Composite getView(Object delegate) {
	if (delegate instanceof org.eclipse.swt.widgets.TabItem) {
	    return (org.eclipse.swt.widgets.Composite) ((org.eclipse.swt.widgets.TabItem) delegate).getControl();    
	} else if (delegate instanceof XDesktopItem) {
	    return (org.eclipse.swt.widgets.Composite) ((XDesktopItem) delegate).getContent();
	}
	return (org.eclipse.swt.widgets.Composite) delegate;
    }
    
    protected void addChild(org.eclipse.swt.widgets.Composite xParent, org.eclipse.swt.widgets.Widget xWidget, UIElement element) {
	//do nothing
    }

    
    
    protected void disposeWidget(org.eclipse.swt.widgets.Widget widget) {
	if (!widget.isDisposed()) {
	    widget.dispose();
	}
    }
    
    
    
    
    public void disposeDelegate(UIElement parent, UIElement element) {
	org.eclipse.swt.widgets.Widget widget = asWidget(element.getDelegate());
	if (!widget.isDisposed()) {
	    widget.dispose();
	}
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	org.eclipse.swt.widgets.Widget widget = asWidget(element.getDelegate());
	if (widget == null) {
	    return;
	}
	if (Widget.PROPERTY_DATA.equals(name)) {
	    widget.setData(value);
	} else if (startsWith(name, Widget.PROPERTY_DATA_PREFIX)) {
	    // WARNING! We use '@' to separate 'data' and 'key' 
	    String key = name.substring(Widget.PROPERTY_DATA_PREFIX.length());
	    widget.setData(key, value);
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
    // Listeners
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected org.eclipse.swt.internal.SWTEventListener getListener(Widget widget, Listener listener) {
	return (org.eclipse.swt.internal.SWTEventListener) widget.findListenerDelegate(listener);
    }

    protected org.eclipse.swt.internal.SWTEventListener getListener(Widget widget, Listener listener, int index) {
	return (org.eclipse.swt.internal.SWTEventListener) widget.findListenerDelegate(listener, index);
    }

    
    // KEY DOWN
    protected org.eclipse.swt.events.KeyListener createKeyDownListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.KeyListener xListener = new org.eclipse.swt.events.KeyAdapter() {

	    @Override
	    public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }

    // KEY UP
    protected org.eclipse.swt.events.KeyListener createKeyUpListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.KeyListener xListener = new org.eclipse.swt.events.KeyAdapter() {

	    @Override
	    public void keyReleased(org.eclipse.swt.events.KeyEvent e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }

    
    protected org.eclipse.swt.events.KeyListener getKeyListener(Widget widget, Listener listener) {
	return (org.eclipse.swt.events.KeyListener) getListener(widget, listener);
    }

    protected org.eclipse.swt.events.KeyListener getKeyListener(Widget widget, Listener listener, int index) {
	return (org.eclipse.swt.events.KeyListener) getListener(widget, listener, index);
    }

    
    
    // MOUSE DOWN
    protected org.eclipse.swt.events.MouseListener createMouseDownListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.MouseListener xListener = new org.eclipse.swt.events.MouseAdapter() {

	    @Override
	    public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }

    // MOUSE UP
    protected org.eclipse.swt.events.MouseListener createMouseUpListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.MouseListener xListener = new org.eclipse.swt.events.MouseAdapter() {

	    @Override
	    public void mouseUp(org.eclipse.swt.events.MouseEvent e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }

    // MOUSE CLICK
    protected org.eclipse.swt.events.MouseListener createMouseClickListener(Widget widget, final Listener listener) {
	
	// Emulate single MouseClick in SWT by MouseDown 
	org.eclipse.swt.events.MouseListener xListener =  new org.eclipse.swt.events.MouseAdapter() {

	    private boolean doubleClick;

	    @Override
	    public void mouseDoubleClick(org.eclipse.swt.events.MouseEvent e) {
		doubleClick = true;
	    }

	    @Override
	    public void mouseDown(final org.eclipse.swt.events.MouseEvent e) {
		doubleClick = false;
		Display.getDefault().timerExec(
			Display.getDefault().getDoubleClickTime(),
			new Runnable() {
			    public void run() {
				if (!doubleClick) {
				    listener.handleEvent(createEvent(e));
				}
			    }
			});
	    }

	};
	widget.assignListener(listener, xListener);
	return xListener;
    }

    // MOUSE DOUBLE CLICK
    protected org.eclipse.swt.events.MouseListener createMouseDoubleClickListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.MouseListener xListener = new org.eclipse.swt.events.MouseAdapter() {

	    @Override
	    public void mouseDoubleClick(org.eclipse.swt.events.MouseEvent e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }

    
    protected org.eclipse.swt.events.MouseListener getMouseListener(Widget widget, Listener listener) {
	return (org.eclipse.swt.events.MouseListener) getListener(widget, listener);
    }

    protected org.eclipse.swt.events.MouseListener getMouseListener(Widget widget, Listener listener, int index) {
	return (org.eclipse.swt.events.MouseListener) getListener(widget, listener, index);
    }

    
    // MOUSE MOVE
    protected org.eclipse.swt.events.MouseMoveListener createMouseMoveListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.MouseMoveListener xListener = new org.eclipse.swt.events.MouseMoveListener() {

	    @Override
	    public void mouseMove(org.eclipse.swt.events.MouseEvent e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }
    
    
    protected org.eclipse.swt.events.MouseMoveListener getMouseMoveListener(Widget widget, Listener listener) {
	return (org.eclipse.swt.events.MouseMoveListener) getListener(widget, listener);
    }

    protected org.eclipse.swt.events.MouseMoveListener getMouseMoveListener(Widget widget, Listener listener, int index) {
	return (org.eclipse.swt.events.MouseMoveListener) getListener(widget, listener, index);
    }

    
    
    // MOUSE IN
    protected org.eclipse.swt.events.MouseTrackListener createMouseInListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.MouseTrackListener xListener = new org.eclipse.swt.events.MouseTrackAdapter() {

	    @Override
	    public void mouseEnter(org.eclipse.swt.events.MouseEvent e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }
   
    // MOUSE OUT
    protected org.eclipse.swt.events.MouseTrackListener createMouseOutListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.MouseTrackListener xListener = new org.eclipse.swt.events.MouseTrackAdapter() {

	    @Override
	    public void mouseExit(org.eclipse.swt.events.MouseEvent e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }
    
    protected org.eclipse.swt.events.MouseTrackListener getMouseTrackListener(Widget widget, Listener listener) {
	return (org.eclipse.swt.events.MouseTrackListener) getListener(widget, listener);
    }

    protected org.eclipse.swt.events.MouseTrackListener getMouseTrackListener(Widget widget, Listener listener, int index) {
	return (org.eclipse.swt.events.MouseTrackListener) getListener(widget, listener, index);
    }

    
    
    // FOCUS IN
    protected org.eclipse.swt.events.FocusListener createFocusInListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.FocusListener xlistener = new org.eclipse.swt.events.FocusAdapter() {

	    @Override
	    public void focusGained(org.eclipse.swt.events.FocusEvent e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xlistener);
	return xlistener;
    }
   
    // FOCUS OUT
    protected org.eclipse.swt.events.FocusListener createFocusOutListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.FocusListener xListener = new org.eclipse.swt.events.FocusAdapter() {

	    @Override
	    public void focusLost(org.eclipse.swt.events.FocusEvent e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }
    
    protected org.eclipse.swt.events.FocusListener getFocusListener(Widget widget, Listener listener) {
	return (org.eclipse.swt.events.FocusListener) getListener(widget, listener);
    }

    protected org.eclipse.swt.events.FocusListener getFocusListener(Widget widget, Listener listener, int index) {
	return (org.eclipse.swt.events.FocusListener) getListener(widget, listener, index);
    }

    
    
    ////

    protected org.eclipse.swt.events.SelectionListener createSelectionListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.SelectionListener xListener = new org.eclipse.swt.events.SelectionAdapter() {

	    @Override
	    public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
		listener.handleEvent(createEvent(e));
	    }

	};
	widget.assignListener(listener, xListener);
	return xListener;
    }
    
    protected org.eclipse.swt.events.SelectionListener getSelectionListener(Widget widget, final Listener listener) {
	return (org.eclipse.swt.events.SelectionListener) getListener(widget, listener);
    }
    

    protected org.eclipse.swt.events.SelectionListener createDefaultSelectionListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.SelectionListener xListener = new org.eclipse.swt.events.SelectionAdapter() {

	    @Override
	    public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }
    
    
    
    // M_ENTER (MOUSE DOUBLE CLICK)
    protected org.eclipse.swt.events.MouseListener createMEnterListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.MouseListener xListener = new org.eclipse.swt.events.MouseAdapter() {

	    @Override
	    public void mouseDoubleClick(org.eclipse.swt.events.MouseEvent e) {
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }
    

    // K_ENTER (KEY ENTER [13])
    protected org.eclipse.swt.events.KeyListener createKEnterListener(Widget widget, final Listener listener) {
	org.eclipse.swt.events.KeyListener xListener = new org.eclipse.swt.events.KeyAdapter() {

	    @Override
	    public void keyReleased(org.eclipse.swt.events.KeyEvent e) {
		if (e.character != SWT.CR) {
		    return;
		}
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }

    

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Events
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Create UWT Event by SWT KeyEvent
     * @param e
     * @return
     */
    protected Event createEvent(org.eclipse.swt.events.KeyEvent e) {
        Event event = new Event();
        event.setKeyCode(e.keyCode);
        event.setCharacter(e.character);
        event.setStateMask(getStateMask(e.stateMask)); // Convert state mask form SWT to UWT
        ////
        event.setDevice(UWT.KEYBOARD_DEVICE);
        return event;
    }
    
    /**
     * Create UWT Event by SWT MouseEvent
     * @param e
     * @return
     */
    protected Event createEvent(org.eclipse.swt.events.MouseEvent e) {
        Event event = new Event();
        event.setButton(e.button); // SWT.BUTTON1 = 1, SWT.BUTTON2 = 2, SWT.BUTTON3 = 3
        event.setX(e.x);
        event.setY(e.y);
	event.setStateMask(getStateMask(e.stateMask)); // Convert state mask form SWT to UWT
        event.setCount(e.count);
        ////
        event.setDevice(UWT.MOUSE_DEVICE);
        return event;
    }
    
    /**
     * Convert state mask from SWT to UWT
     * @return
     */
    protected int getStateMask(int xStateMask) {
	int stateMask = 0;
	if ((xStateMask & SWT.SHIFT) != 0) {
	    stateMask |= KeyEvent.SHIFT_MASK;
	}
	if ((xStateMask & SWT.CONTROL) != 0) {
	    stateMask |= KeyEvent.CTRL_MASK;
	}
	// TODO: META_MAST not implemented
	//if ((xStateMask & SWT.MOD1) != 0) {
	//    modifiers |= KeyEvent.META_MASK;
	//}
	if ((xStateMask & SWT.ALT) != 0) {
	    stateMask |= KeyEvent.ALT_MASK;
	}
	return stateMask;
    }

    /**
     * Create UWT Event by SWT FocusEvent
     * @param e
     * @return
     */
    protected Event createEvent(org.eclipse.swt.events.FocusEvent e) {
        Event event = new Event();
        return event;
    }
    
    protected Event createEvent(org.eclipse.swt.events.SelectionEvent e) {
        Event event = new Event();
        event.setX(e.x);
        event.setY(e.y);
        event.setStateMask(e.stateMask);
        //event.count = e.count;
        
        return event;
    }

    

}
