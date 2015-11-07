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

package org.plazmaforge.framework.uwt.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.UWTException;
import org.plazmaforge.framework.uwt.event.EnterEvent;
import org.plazmaforge.framework.uwt.event.EnterListener;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.event.FocusEvent;
import org.plazmaforge.framework.uwt.event.FocusListener;
import org.plazmaforge.framework.uwt.event.KeyEvent;
import org.plazmaforge.framework.uwt.event.KeyListener;
import org.plazmaforge.framework.uwt.event.ListenerData;
import org.plazmaforge.framework.uwt.event.MouseEvent;
import org.plazmaforge.framework.uwt.event.MouseListener;
import org.plazmaforge.framework.uwt.event.MouseMoveListener;
import org.plazmaforge.framework.uwt.event.SelectionEvent;
import org.plazmaforge.framework.uwt.event.SelectionListener;
import org.plazmaforge.framework.uwt.event.TypedEvent;
import org.plazmaforge.framework.uwt.event.TypedListener;
import org.plazmaforge.framework.uwt.event.UWTEventListener;

public class Widget extends UIObject {
    
    

    //////////////////////////////////////////////////////////////////////
    // PROPERTIES
    //////////////////////////////////////////////////////////////////////


    public static final String PROPERTY_TITLE = "title";
    
    public static final String PROPERTY_TEXT = "text";
    
    public static final String PROPERTY_IMAGE = "image";
    
    public static final String PROPERTY_IMAGE_PATH = "imagePath";

    public static final String PROPERTY_ICON = "icon";
    
    public static final String PROPERTY_ICON_PATH = "iconPath";

    public static final String PROPERTY_BACKGROUND = "background";
    
    public static final String PROPERTY_FOREGROUND = "foreground";
    
    public static final String PROPERTY_FONT = "font";
    
    public static final String PROPERTY_LAYOUT = "layout";
    
    public static final String PROPERTY_LAYOUT_DATA = "layoutData";
    
    public static final String PROPERTY_X = "x";
    
    public static final String PROPERTY_Y = "y";
    
    public static final String PROPERTY_WIDTH = "width";
    
    public static final String PROPERTY_HEIGHT = "height";
    
    public static final String PROPERTY_ALIGN = "align";
    
    public static final String PROPERTY_HORIZONTAL_ALIGN = "horizontalAlign";
    
    public static final String PROPERTY_VERTICAL_ALIGN = "verticalAlign";
    
    public static final String PROPERTY_DATA_TYPE = "dataType";
    
    public static final String PROPERTY_PROPERTY = "property";
    
    public static final String PROPERTY_REF_PROPERTY = "refProperty";
    
    public static final String PROPERTY_VALUE = "value";
    
    public static final String PROPERTY_REF_VALUE = "refValue";
    
    public static final String PROPERTY_MIN_VALUE = "minValue";
    
    public static final String PROPERTY_MAX_VALUE = "maxValue";
    
    public static final String PROPERTY_INCREMENT_VALUE = "incrementValue";
    
    public static final String PROPERTY_DATA_LIST = "dataList";
    
    public static final String PROPERTY_LINES_VISIBLE = "linesVisible";
    
    public static final String PROPERTY_HEADER_VISIBLE = "headerVisible";
    
    public static final String PROPERTY_FORMAT = "format";
    
    public static final String PROPERTY_DISPLAY_PROPERTY = "displayProperty";
    
    public static final String PROPERTY_DISPLAY_FORMAT = "displayFormat";
    
    public static final String PROPERTY_LISTENERS = "listeners";
    
    public static final String PROPERTY_EVENTS = "events";
    
    public static final String PROPERTY_HANDLER = "handler";
    
    public static final String PROPERTY_SCRIPT = "script";
    
    public static final String PROPERTY_LANGUAGE = "language";
    
    public static String PROPERTY_LABEL_PROVIDER = "labelProvider";
    
    public static String PROPERTY_CELL_RENDERER = "cellRenderer";
    
    public static String PROPERTY_CELL_EDITOR = "cellEditor";
    
    
    public static final String PROPERTY_VISIBLE = "visible";
    
    public static final String PROPERTY_ENABLED = "enabled";
    
    public static final String PROPERTY_SELECTED = "selected";
    
    public static final String PROPERTY_SELECTION = "selection";
    
    public static final String PROPERTY_SELECTION_INDEX = "selectionIndex";
    
    public static final String PROPERTY_ITEMS = "items";
    
    public static final String PROPERTY_COLUMNS = "columns";

    
    public static final String PROPERTY_SORTABLE = "sortable";
    
    public static final String PROPERTY_FILTERABLE = "filterable";
    
    public static final String PROPERTY_RESIZABLE = "resizable";
    
    public static final String PROPERTY_MOVEABLE = "moveable";
    
    public static final String PROPERTY_EDITABLE = "editable";
    
    public static final String PROPERTY_CLOSABLE = "closable";
    
    public static final String PROPERTY_MINIMIZABLE = "minimizable";
    
    public static final String PROPERTY_MAXIMIZABLE = "maximizable";
    
    
    public static final String PROPERTY_MENU = "menu";
    
    public static final String PROPERTY_MENU_BAR = "menuBar";
    
    public static final String PROPERTY_MENU_ITEM = "menuItem";
    
    public static final String PROPERTY_MENU_ITEMS = "menuItems";
    
    public static final String PROPERTY_CONTEXT_MENU = "contextMenu";
    

    public static final String PROPERTY_TOOL_BARS = "toolBars";
    
    public static final String PROPERTY_TOOL_BAR = "toolBar";
    
    public static final String PROPERTY_TOOL_ITEM = "toolItem";
    
    public static final String PROPERTY_TOOL_ITEMS = "toolItems";

    public static final String PROPERTY_TOOL_TIP = "toolTip";
    

    public static final String PROPERTY_GROUP = "group";
    
    public static final String PROPERTY_ORIENTATION = "orientation";    
    
    //////////////////////////////////////////////////////////////////////
    // METHODS
    //////////////////////////////////////////////////////////////////////

    
    public static final String METHOD_GET_SELECTION = "getSelection";
    
    public static final String METHOD_SET_SELECTION = "setSelection";
    
    public static final String METHOD_GET_SELECTION_INDEX = "getSelectionIndex";
    
    public static final String METHOD_SET_SELECTION_INDEX = "setSelectionIndex";

    
    
    /**
     * Listeners of widget (use only for delegate)
     */
    private List<ListenerData> listeners;
    
    
    /**
     * Internal listeners
     */
    private Map<String, List<Listener>> internalListeners;
    
    
    
    public Widget() {
	super();
    }

    public Widget getParent() {
        return (Widget) getUIParent();
    }


    public void setParent(Widget parent) {
	setUIParent(parent);
    }
    
    public void setUIParent(UIObject parent) {
	if (!(parent instanceof Widget)) {
	    throw new IllegalArgumentException("Parent must be Widget");
	}
	super.setUIParent(parent);
    }
    
    
    protected List<ListenerData> doGetListeners() {
	if (listeners == null) {
	    listeners = new ArrayList<ListenerData>();
	}
	return listeners;
    }
    
    protected List<ListenerData> getInitListeners() {
	List<ListenerData> listeners = (List<ListenerData>) getInitProperty(PROPERTY_LISTENERS);
	if (listeners == null) {
	    listeners = new ArrayList<ListenerData>();
	    setInitProperty(PROPERTY_LISTENERS, listeners);
	}
	return listeners;
    }
    
    protected boolean isListenersProperty(String name) {
 	return PROPERTY_LISTENERS.equals(name);
    }
    
    protected void initDelegateProperty(String name, Object value) {
	if (isListenersProperty(name) ) {
	    List<ListenerData> initChildren = (List<ListenerData>) getInitProperty(name);
	    initDelegateListeners(initChildren);
	    return;
	}
	super.initDelegateProperty(name, value);
    }
    
    protected void initDelegateListeners(List<ListenerData> initChildren) {
	if (!hasDelegate() || initChildren.isEmpty()) {
	    return;
	}
	for (ListenerData element : initChildren) {
	    ListenerData w = (ListenerData) element;
	    addDelegateListener(w.getEventType(), w.getEventListener());
	}
	initChildren.clear();
    }
    
    /**
     * Add listener to widget
     * @param eventType
     * @param listener
     */
    protected void addListener(String eventType, Listener listener) {
	if (listener == null) {
	    // TODO: May be error
	    return;
	}
	ListenerData listenerData = new ListenerData(eventType, listener);
	
	// Add to listeners
	doGetListeners().add(listenerData);
	
	// Fire
	fireAddListener(getInitListeners(), eventType, listenerData);
    }

    /**
     * Remove listener from widget
     * @param eventType
     * @param listener
     */
    protected void removeListener(String eventType, Listener listener) {
	if (listener == null) {
	    // TODO: Error: Listener must be not null
	    return;
	}
	ListenerData listenerData = findListenerData(listener);
	if (listenerData == null) {
	    // TODO: Listener not found
	    return;
	}
	removeListener(eventType, listenerData);
    }

    protected void removeListener(String eventType, ListenerData listenerData) {
	if (listenerData == null) {
	    // TODO: Error: Listener must be not null
	    return;
	}
	
	// TODO: WARNING !!! We fire 'RemoveListener' before remove listener form widget 
	// because in Adapter we need use listeners to find ListenerData
	// This is temp solution. May be we can assign Delegate to Listener
	
	// Fire
	fireRemoveListener(getInitListeners(), eventType, listenerData);
	
	// Remove from listeners
	doGetListeners().remove(listenerData);

    }

    protected void removeListener(String eventType, UWTEventListener eventListener) {
	if (eventListener == null) {
	    // TODO: Error: Listener must be not null
	    return;
	}
	ListenerData listenerData = findListenerData(eventType, eventListener);
	if (listenerData == null) {
	    // TODO: Error: Listener not found
	    return;
	}
	removeListener(eventType, listenerData);
    }
    
    /**
     * Find <code>ListenerData</code> by <code>Listener</code>
     * @param listener
     * @return
     */
    protected ListenerData findListenerData(Listener listener) {
	if (listener == null) {
	    return null;
	}
	List<ListenerData> listeners =  doGetListeners();
	if (listeners == null || listeners.isEmpty()) {
	    return null;
	}
	ListenerData listenerData = null;
	for (ListenerData l : listeners) {
	    if (listener == l.getEventListener()) {
		listenerData = l;
		break;
	    }
	}
	return listenerData;
    }
    
    public Object findListenerDelegate(Listener listener) {
	if (listener == null) {
	    return null;
	}
	ListenerData listenerData = findListenerData(listener);
	if (listenerData == null) {
	    return null;
	}
	return listenerData.getDelegate();
    }

    public Object findListenerDelegate(Listener listener, int index) {
	if (listener == null) {
	    return null;
	}
	ListenerData listenerData = findListenerData(listener);
	if (listenerData == null) {
	    return null;
	}
	return listenerData.getDelegate(index);
    }

    /**
     * Find <code>ListenerData</code> by eventType and <code>UWTEventListener</code>
     * @param eventType
     * @param eventListener
     * @return
     */
    protected ListenerData findListenerData(String eventType, UWTEventListener eventListener) {
	if (eventType == null || eventListener == null) {
	    return null;
	}
	List<ListenerData> listeners =  doGetListeners();
	if (listeners == null || listeners.isEmpty()) {
	    return null;
	}
	ListenerData listenerData = null;
	for (ListenerData l : listeners) {
	    Listener listener = l.getEventListener();
	    if (!(listener instanceof TypedListener)) {
		continue;
	    }
	    TypedListener typedListener = (TypedListener) listener;
	    if (eventListener == typedListener.getEventListener() && eventType.equals(l.getEventType())) {
		listenerData = l;
		break;
	    }
	}
	return listenerData;
    }
    
    /**
     * Assign delegate to <code>Listener</code>
     * @param listener
     * @param delegate
     */
    public void assignListener(Listener listener, Object delegate) {
	if (listener == null) {
	    // TODO: May be error
	    return;
	}
	ListenerData listenerData = findListenerData(listener);
	if (listenerData == null) {
	    // TODO: May be error
	    return;
	}
	listenerData.assignDelegate(delegate);
    }
    
    protected void fireAddListener(List<ListenerData> initListeners, String eventType, ListenerData listenerData) {
	if (!hasDelegate()) {
	    getInitListeners().add(listenerData);
	    return;
	}
	addDelegateListener(eventType, listenerData.getEventListener());
    }


    protected void fireRemoveListener(List<ListenerData> initListeners, String eventType, ListenerData listenerData) {
	if (!hasDelegate()) {
	    getInitListeners().remove(listenerData);
	    return;
	}
	removeDelegateListener(eventType, listenerData.getEventListener());
    }
    
    
    protected void addDelegateListener(String eventType, Listener listener) {
	if (listener == null) {
	    return;
	}
	if (getAdapter() == null) {
	    throw new UWTException("Adapter is empty");
	}
	getAdapter().addListener(this, eventType, listener);
    }

    protected void removeDelegateListener(String eventType, Listener listener) {
	if (listener == null) {
	    return;
	}
	if (getAdapter() == null) {
	    throw new UWTException("Adapter is empty");
	}
	getAdapter().removeListener(this, eventType, listener);
    }
    
    protected KeyEvent createKeyEvent(Event event) {
	KeyEvent e = new KeyEvent(getEventSource());
	populateEvent(e, event);
	
	e.setKeyCode(event.getKeyCode());
	e.setKeyChar(event.getCharacter());
	e.setStateMask(event.getStateMask());
	
	//TODO: Must analyze
	//e.setDoit(event.isDoit());
	return e;
    }
     
    protected MouseEvent createMouseEvent(Event event) {
	MouseEvent e = new MouseEvent(getEventSource());
	populateEvent(e, event);
	
	e.setButton(event.getButton());
	e.setStateMask(event.getStateMask());
	e.setX(event.getX());
	e.setY(event.getY());
	e.setCount(event.getCount()); // Click count
	return e;
    }
    
    protected FocusEvent createFocusEvent(Event event) {
	FocusEvent e = new FocusEvent(getEventSource());
	populateEvent(e, event);
	return e;
    }
    
    protected SelectionEvent createSelectionEvent(Event event) {
	SelectionEvent e = new SelectionEvent(getEventSource());
	populateEvent(e, event);
	return e;
    }

    protected EnterEvent createEnterEvent(Event event) {
	EnterEvent e = new EnterEvent(getEventSource());
	populateEvent(e, event);
	
	e.setDevice(event.getDevice());
	return e;
    }

    
    protected Object getEventSource() {
	return this;
    }
    
    protected void populateEvent(TypedEvent te, Event event) {
	te.setData(event.getData());
	te.setTime(event.getTime());
	te.setWidget(this);
    }
    
    
    
    /** 
     * Add <code>KeyListener</code> to <code>Widget</code>
     * @param widget
     * @param listener
     */
    protected void addKeyListener(Widget widget, final KeyListener listener) {
	
   	widget.addListener(Events.KeyDown, new Listener() {
   	    public  void handleEvent(Event event) {
   		listener.keyDown(createKeyEvent(event));
   	    }
   	});
   	
   	widget.addListener(Events.KeyUp, new Listener() {
   	    public  void handleEvent(Event event) {
   		listener.keyUp(createKeyEvent(event));
   	    }
   	});
   	
    }    


    /** 
     * Remove <code>KeyListener</code> to <code>Widget</code>
     * @param widget
     * @param listener
     */
    protected void removeKeyListener(Widget widget, final KeyListener listener) {
   	widget.removeListener(Events.KeyDown, listener);
   	widget.removeListener(Events.KeyUp, listener);
    }
    

    
    
    /** 
     * Add <code>MouseListener</code> to <code>Widget</code>
     * @param widget
     * @param listener
     */
    protected void addMouseListener(Widget widget, final MouseListener listener) {
	
   	widget.addListener(Events.MouseClick, new Listener() {
   	    public  void handleEvent(Event event) {
   		listener.mouseClick(createMouseEvent(event));
   	    }
   	});
   	
   	widget.addListener(Events.MouseDoubleClick, new Listener() {
   	    public  void handleEvent(Event event) {
   		listener.mouseDoubleClick(createMouseEvent(event));
   	    }
   	});
   	
   	widget.addListener(Events.MouseDown, new Listener() {
   	    public  void handleEvent(Event event) {
   		listener.mouseDown(createMouseEvent(event));
   	    }
   	});

   	widget.addListener(Events.MouseUp, new Listener() {
   	    public  void handleEvent(Event event) {
   		listener.mouseUp(createMouseEvent(event));
   	    }
   	});
   	    
    }


    /** 
     * Remove <code>MouseListener</code> to <code>Widget</code>
     * @param widget
     * @param listener
     */
    protected void removeMouseListener(Widget widget, MouseListener listener) {
   	widget.removeListener(Events.MouseClick, listener);
   	widget.removeListener(Events.MouseDoubleClick, listener);
   	widget.removeListener(Events.MouseDown, listener);
   	widget.removeListener(Events.MouseUp, listener);
    }

    /** 
     * Add <code>MouseMoveListener</code> to <code>Widget</code>
     * @param widget
     * @param listener
     */
    protected void addMouseMoveListener(Widget widget, final MouseMoveListener listener) {
	
   	widget.addListener(Events.MouseMove, new Listener() {
   	    public  void handleEvent(Event event) {
   		listener.mouseMove(createMouseEvent(event));
   	    }
   	});
   	
   	widget.addListener(Events.MouseIn, new Listener() {
   	    public  void handleEvent(Event event) {
   		listener.mouseIn(createMouseEvent(event));
   	    }
   	});
   	
   	widget.addListener(Events.MouseOut, new Listener() {
   	    public  void handleEvent(Event event) {
   		listener.mouseOut(createMouseEvent(event));
   	    }
   	});
   	
    }

    /**
     * Remove <code>MouseMoveListener</code> from <code>Widget</code>
     * @param widget
     * @param listener
     */
    protected void removeMouseMoveListener(Widget widget, MouseMoveListener listener) {
   	widget.removeListener(Events.MouseMove, listener);
   	widget.removeListener(Events.MouseIn, listener);
   	widget.removeListener(Events.MouseOut, listener);
    }

    
    
    
    /** 
     * Add <code>FocusListener</code> to <code>Widget</code>
     * @param widget
     * @param listener
     */
    protected void addFocusListener(Widget widget, final FocusListener listener) {
	
   	widget.addListener(Events.FocusIn, new Listener() {
   	    public  void handleEvent(Event event) {
   		listener.focusIn(createFocusEvent(event));
   	    }
   	});
   	
   	widget.addListener(Events.FocusOut, new Listener() {
   	    public  void handleEvent(Event event) {
   		listener.focusOut(createFocusEvent(event));
   	    }
   	});
    }
    

    /**
     * Remove <code>FocusListener</code> from <code>Widget</code>
     * @param widget
     * @param listener
     */
    protected void removeFocusListener(Widget widget, FocusListener listener) {
   	widget.removeListener(Events.FocusIn, listener);
   	widget.removeListener(Events.FocusOut, listener);
    }


    
    
    /**
     * Add <code>SelectionListener</code> to <code>Widget</code>
     * @param widget
     * @param listener
     */
    protected void addSelectionListener(Widget widget, final SelectionListener listener) {
   	widget.addListener(Events.Selection, new TypedListener(listener) {
   	    public  void handleEvent(Event event) {
   		listener.select(createSelectionEvent(event));
   	    }
   	});
    }
    
    /**
     * Remove <code>SelectionListener</code>from <code>Widget</code>
     * @param widget
     * @param listener
     */
    protected void removeSelectionListener(Widget widget, SelectionListener listener) {
   	widget.removeListener(Events.Selection, listener);
    }

    
    
    
    
    /**
     * Add <code>EnterListener</code> to <code>Widget</code>
     * @param widget
     * @param listener
     */
    protected void addEnterListener(Widget widget, final EnterListener listener) {
   	widget.addListener(Events.Enter, new Listener() {
   	    public  void handleEvent(Event event) {
   		listener.enter(createEnterEvent(event));
   	    }
   	});
    }

    /**
     * Remove <code>EnterListener</code> from <code>Widget</code>
     * @param widget
     * @param listener
     */
    protected void removeEnterListener(Widget widget, EnterListener listener) {
   	widget.removeListener(Events.Enter, listener);
    }


    ////////////////////////////////////////////////////////////////////////////////////
    // INTERNAL LISTENERS
    ////////////////////////////////////////////////////////////////////////////////////
    
    protected Map<String, List<Listener>> getInternalListeners() {
	if (internalListeners == null) {
	    internalListeners = new HashMap<String, List<Listener>>();
	}
	return internalListeners;
    }
    
    public void addInternalListener(String type, Listener listener) {
	if (type == null || listener == null) {
	    return;
	}
	List<Listener> listeners = getInternalListeners().get(type);
	if (listeners == null) {
	    listeners = new ArrayList<Listener>();
	    getInternalListeners().put(type, listeners);
	}
	listeners.add(listener);
    }
    
    public void removeInternalListener(String type, Listener listener) {
	if (type == null || listener == null) {
	    return;
	}
	List<Listener> listeners = getInternalListeners().get(type);
	if (listeners == null) {
	    // No listeners by type
	    return;
	}
	listeners.remove(listener);
    }
    
    
    protected void fireInternalEvent(Event event) {
	if (event == null) {
	    return;
	}
	String type = event.getType();
	if (type == null) {
	    return;
	}
	List<Listener> listeners = getInternalListeners().get(type);
	if (listeners == null) {
	    return;
	}
	for (Listener listener: listeners) {
	    listener.handleEvent(event);
	}
    }

}
