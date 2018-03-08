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
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.jfx.widget.XContainer;
import org.plazmaforge.framework.uwt.jfx.widget.XLayoutContainer;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;

import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * 
 * @author ohapon
 *
 */
public abstract class JFXControlAdapter extends JFXWidgetAdapter {


    /**
     * Add element to parent
     * @param parent
     * @param widget
     */
    protected void addChild(javafx.scene.Parent xParent, javafx.scene.Node xControl, UIElement element) {
	
//	checkNullParent(xParent, MESSAGE_CANT_ADD_WIDGET);
//	checkContainerParent(xParent, MESSAGE_CANT_ADD_WIDGET);
//	
//	org.eclipse.swt.widgets.Layout xLayout = xParent.getLayout();
//	if (xLayout == null) {
//	    return;
//	}
	
	
	// Initialize LayoutData	
	if (element instanceof Control) {
	    Control control = (Control) element;
	    Object layoutData = control.getLayoutData();
	    if (layoutData != null && layoutData instanceof UIElement) {
		((UIElement) layoutData).activateUI();
		setLayoutData(xControl, ((UIElement) layoutData).getDelegate());
		control.resetInitProperties(Control.PROPERTY_LAYOUT_DATA);
	    }
	}

	addChild(xParent, xControl);
    }
    
    protected void addChild(javafx.scene.Parent xParent, javafx.scene.Node xChild) {
	
	// XContainer: special add method
	if (xParent instanceof XContainer) {
	    ((XContainer) xParent).addChild(xChild);
	    return;
	}
	
	ObservableList<Node> children = getChildren(xParent);
	if (children == null) {
	    return;
	}
	children.add(xChild);
    }
    
    protected ObservableList<Node> getChildren(javafx.scene.Parent xParent) {
	if (xParent == null) {
	    return null;
	}
	if (xParent instanceof XLayoutContainer) {
	    return ((XLayoutContainer) xParent).getContainerChildren();
	} else if (xParent instanceof javafx.scene.Group) {
	    return ((javafx.scene.Group) xParent).getChildren();
	} else if (xParent instanceof javafx.scene.layout.Pane) {
	    return ((javafx.scene.layout.Pane) xParent).getChildren();
	}
	//TODO
        return null;
    }

  
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	Object delegate = element.getDelegate();
	if (delegate == null) {
	    return;
	}
	//javafx.scene.Node xControl = asControl(delegate);
	//if (xControl == null) {
	//    return;
	//}
	
	if (Control.PROPERTY_VISIBLE.equals(name)) {
	    setVisible(delegate, asBoolean(value));
	} if (Control.PROPERTY_ENABLED.equals(name)) {
	    setEnabled(delegate, asBoolean(value));    
	} else if (Control.PROPERTY_WIDTH.equals(name)) {
	    setWidth(delegate, asInteger(value));
	    return;
	} else if (Control.PROPERTY_HEIGHT.equals(name)) {
	    setHeight(delegate, asInteger(value));
	    return;
	} else if (Control.PROPERTY_LAYOUT_DATA.equals(name)) {
	    UIElement layoutData = (UIElement) value;
	    if (layoutData == null) {
		setLayoutData(delegate, null);
		return;
	    }
	    layoutData.activateUI();
	    setLayoutData(delegate, layoutData.getDelegate());
	    return;
	}
	
	/*
	} else if (Control.PROPERTY_TOOL_TIP.equals(name)) {
	    xControl.setToolTipText(asSafeString(value));
	    return;
	}
	*/
	
	 else if (Control.PROPERTY_BACKGROUND.equals(name)) {
	    setBackground(delegate, getColor(asColor(value)));
	    return;
	} else if (Control.PROPERTY_FOREGROUND.equals(name)) {
	    setForeground(delegate, getColor(asColor(value)));
	    return;
	} else if (Control.PROPERTY_FONT.equals(name)) {
	    setFont(delegate, getFont(asFont(value)));
	    return;
	}
	
	/*else if (Control.PROPERTY_CONTEXT_MENU.equals(name)) {
	    Menu menu = (Menu) value;
	    menu.activateUI();
	    xControl.setMenu((org.eclipse.swt.widgets.Menu) menu.getDelegate());
	    return;
	}
	*/
	super.setProperty(element, name, value);
	
    }
    
    
    @Override
    public Object getProperty(UIElement element, String name) {
	Object delegate = element.getDelegate();
	if (delegate == null) {
	    return null;
	}
	//org.eclipse.swt.widgets.Control xControl = asControl(delegate);
	//if (xControl == null) {
	    //return null;
	//}
	if (Control.PROPERTY_VISIBLE.equals(name)) {
	    return isVisible(delegate);
	} else if (Control.PROPERTY_ENABLED.equals(name)) {
	    return isEnabled(delegate);
	}

	return super.getProperty(element, name);
    }

    
//  
//  @Override
//  public Object invoke(UIElement element, String methodName, Object[] args) {
//	if (Control.METHOD_REPAINT.equals(methodName)) {
//	    org.eclipse.swt.widgets.Control xControl = (org.eclipse.swt.widgets.Control) element.getDelegate();
//	    if (xControl == null) {
//		return null;
//	    }
//	    xControl.redraw();
//	}
//	return super.invoke(element, methodName, args);
//  }
//  
    
    @Override
    public void addListener(UIElement element, String eventType, final Listener listener) {
	
	Control control = (Control) element;
	javafx.scene.Node xWidget = asControl(element.getDelegate());
	if (xWidget == null) {
	    return;
	}

	if (eq(Events.KeyDown, eventType)) {
	    addKeyDownListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.KeyUp, eventType)) {
	    addKeyUpListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseDown, eventType)) {
	    addMouseDownListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseUp, eventType)) {
	    addMouseUpListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseClick, eventType)) {
	    addMouseClickListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseDoubleClick, eventType)) {
	    addMouseDoubleClickListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseMove, eventType)) {
	    addMouseMoveListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseIn, eventType)) {
	    addMouseInListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseOut, eventType)) {
	    addMouseOutListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.FocusIn, eventType)) {
	    addFocusInListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.FocusOut, eventType)) {
	    addFocusOutListener(xWidget, control, listener);
	    return;
	}
	
	super.addListener(element, eventType, listener);
	
    }
    
   
    @Override
    public void removeListener(UIElement element, String eventType, final Listener listener) {
	
	Control control = (Control) element;
	javafx.scene.Node xWidget = asControl(element.getDelegate());
	if (xWidget == null) {
	    return;
	}

	if (eq(Events.KeyDown, eventType)) {
	    removeKeyDownListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.KeyUp, eventType)) {
	    removeKeyUpListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseDown, eventType)) {
	    removeMouseDownListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseUp, eventType)) {
	    removeMouseUpListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseClick, eventType)) {
	    removeMouseClickListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseDoubleClick, eventType)) {
	    removeMouseDoubleClickListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseMove, eventType)) {
	    removeMouseMoveListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseIn, eventType)) {
	    removeMouseInListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.MouseOut, eventType)) {
	    removeMouseOutListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.FocusIn, eventType)) {
	    removeFocusInListener(xWidget, control, listener);
	    return;
	} else if (eq(Events.FocusOut, eventType)) {
	    removeFocusOutListener(xWidget, control, listener);
	    return;
	}
	
	super.removeListener(element, eventType, listener);
	
    }    

    // KEY DOWN
    @Override
    protected void addKeyDownListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.addEventHandler(javafx.scene.input.KeyEvent.KEY_PRESSED, createKeyListener(widget, listener));
    }    
    
    @Override
    protected void removeKeyDownListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.removeEventHandler(javafx.scene.input.KeyEvent.KEY_PRESSED, getKeyListener(widget, listener));
    }
    

    // KEY UP
    @Override
    protected void addKeyUpListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.addEventHandler(javafx.scene.input.KeyEvent.KEY_RELEASED, createKeyListener(widget, listener));
    }

    @Override
    protected void removeKeyUpListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.removeEventHandler(javafx.scene.input.KeyEvent.KEY_RELEASED, getKeyListener(widget, listener));
    }

        
    // MOUSE DOWN
    @Override
    protected void addMouseDownListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_PRESSED, createMouseListener(widget, listener));
    }

    @Override
    protected void removeMouseDownListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.removeEventHandler(javafx.scene.input.MouseEvent.MOUSE_PRESSED, getMouseListener(widget, listener));
    }
    
    
    // MOUSE UP
    @Override
    protected void addMouseUpListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_RELEASED, createMouseListener(widget, listener));
    }
    
    @Override
    protected void removeMouseUpListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.removeEventHandler(javafx.scene.input.MouseEvent.MOUSE_RELEASED, getMouseListener(widget, listener));
    }

    
    // MOUSE CLICK
    @Override
    protected void addMouseClickListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, createMouseListener(widget, listener));
    }

    @Override
    protected void removeMouseClickListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.removeEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, getMouseListener(widget, listener));
    }
        
    
    // MOUSE DOUBLE CLICK
    @Override
    protected void addMouseDoubleClickListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	//TODO: Need count click
	//xWidget.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, createMouseListener(widget, listener));
    }    
    
    @Override
    protected void removeMouseDoubleClickListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	//TODO
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.OnDoubleClick, getListener(widget, listener));
    }

    
    // MOUSE MOVE
    @Override
    protected void addMouseMoveListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_MOVED, createMouseListener(widget, listener));
    }

    @Override
    protected void removeMouseMoveListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.removeEventHandler(javafx.scene.input.MouseEvent.MOUSE_MOVED, getMouseListener(widget, listener));
    }
        

    // MOUSE IN
    @Override
    protected void addMouseInListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, createMouseListener(widget, listener));
    }

    @Override
    protected void removeMouseInListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.removeEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, getMouseListener(widget, listener));
    }
    
    
    // MOUSE OUT
    @Override
    protected void addMouseOutListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, createMouseListener(widget, listener));
    }
    
    @Override
    protected void removeMouseOutListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.removeEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, getMouseListener(widget, listener));
    }

   
    // FOCUS IN
    @Override
    protected void addFocusInListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.focusedProperty().addListener(createFocusInListener(widget, listener));
    }
    
    @Override
    protected void removeFocusInListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.focusedProperty().removeListener(getFocusListener(widget, listener));
    }
     

    // FOCUS OUT
    @Override
    protected void addFocusOutListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.focusedProperty().addListener(createFocusOutListener(widget, listener));
    }

    @Override
    protected void removeFocusOutListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	xWidget.focusedProperty().removeListener(getFocusListener(widget, listener));
    }
    

    // KEY ENTER
    @Override
    protected void addEnterListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
	//TODO
    }
    
    protected void removeEnterListener(javafx.scene.Node xWidget, Widget widget, Listener listener) {
 	//TODO
    }
    
    
 
//    /**
//     * Do layout parent of control if control is visible 
//     * @param control
//     */
//    protected void layout(org.eclipse.swt.widgets.Control control) {
//	if (control == null || !control.isVisible()) {
//	    return;
//	}
//	org.eclipse.swt.widgets.Composite parent = control.getParent();
//	parent.layout();
//    }
    
    
    
    
}
