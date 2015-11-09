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

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.menu.Menu;

public abstract class SWTControlAdapter extends SWTWidgetAdapter {


    /**
     * Add element to parent
     * @param parent
     * @param widget
     */
    protected void addToParent(org.eclipse.swt.widgets.Composite xParent, org.eclipse.swt.widgets.Control xControl, UIObject element) {
	org.eclipse.swt.widgets.Layout xLayout = xParent.getLayout();
	if (xLayout == null) {
	    return;
	}
	Control control = (Control) element;
	Object xLayoutData = xControl.getLayoutData();
	
	if (xLayoutData != null && SWTLayoutUtils.isCompatible(xLayout, xLayoutData)) {
	    SWTLayoutUtils.prepare(xParent, xControl, xLayout, xLayoutData);
	    return;
	}
	
	xLayoutData = SWTLayoutUtils.createDefaultLayoutData(xLayout, control);
	if (xLayoutData != null) {
	    xControl.setLayoutData(xLayoutData);
	}
	
	SWTLayoutUtils.prepare(xParent, xControl, xLayout, xLayoutData);
    }
    
  
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	Object delegate = element.getDelegate();
	org.eclipse.swt.widgets.Control xControl = getControl(delegate);
	if (xControl == null) {
	    return;
	}
	if (Control.PROPERTY_VISIBLE.equals(name)) {
	    xControl.setVisible(getBoolean(value));
	} if (Control.PROPERTY_ENABLED.equals(name)) {
	    xControl.setEnabled(getBoolean(value));    
	} else if (Control.PROPERTY_WIDTH.equals(name)) {
	    org.eclipse.swt.graphics.Point point = xControl.getSize();
	    point.x = (Integer) value;
	    xControl.setSize(point);
	    return;
	} else if (Control.PROPERTY_HEIGHT.equals(name)) {
	    org.eclipse.swt.graphics.Point point = xControl.getSize();
	    point.y = (Integer) value;
	    xControl.setSize(point);
	    return;
	} else if (Control.PROPERTY_LAYOUT_DATA.equals(name)) {
	    UIObject layoutData = (UIObject) value;
	    if (layoutData == null) {
		xControl.setLayoutData(null);
		return;
	    }
	    layoutData.activateUI();
	    xControl.setLayoutData(layoutData.getDelegate());
	    return;
	} else if (Control.PROPERTY_TOOL_TIP.equals(name)) {
	    xControl.setToolTipText(getSafeString(value));
	    return;
	} else if (Control.PROPERTY_BACKGROUND.equals(name)) {
	    Color color = (Color) value;
	    xControl.setBackground(getColor(color));
	    return;
	} else if (Control.PROPERTY_FOREGROUND.equals(name)) {
	    Color color = (Color) value;
	    xControl.setForeground(getColor(color));
	    return;
	} else if (Control.PROPERTY_FONT.equals(name)) {
	    Font font = (Font) value;
	    xControl.setFont(getFont(font));
	    return;
	} else if (Control.PROPERTY_CONTEXT_MENU.equals(name)) {
	    Menu menu = (Menu) value;
	    menu.activateUI();
	    xControl.setMenu((org.eclipse.swt.widgets.Menu) menu.getDelegate());
	    return;
	}
	
	
	super.setProperty(element, name, value);
	
    }
    
    
    @Override
    public Object getProperty(UIObject element, String name) {
	Object delegate = element.getDelegate();
	org.eclipse.swt.widgets.Control xControl = getControl(delegate);
	if (xControl == null) {
	    return null;
	}
	if (Control.PROPERTY_VISIBLE.equals(name)) {
	    return xControl.getVisible();
	} else if (Control.PROPERTY_ENABLED.equals(name)) {
	    return xControl.getEnabled();
	}

	return super.getProperty(element, name);
    }

    
    @Override
    public void addListener(UIObject element, String eventType, Listener listener) {
	
	Control control = (Control) element;
	org.eclipse.swt.widgets.Control xControl = (org.eclipse.swt.widgets.Control) element.getDelegate();
	if (xControl == null) {
	    return;
	}
	
	if (eq(Events.KeyDown, eventType)) {
	    xControl.addKeyListener(createKeyDownListener(control, listener));
	    return;
	} else if (eq(Events.KeyUp, eventType)) {
	    xControl.addKeyListener(createKeyUpListener(control, listener));
	    return;
	} else if (eq(Events.MouseDown, eventType)) {
	    xControl.addMouseListener(createMouseDownListener(control, listener));
	    return;
	} else if (eq(Events.MouseUp, eventType)) {
	    xControl.addMouseListener(createMouseUpListener(control, listener));
	    return;
	} else if (eq(Events.MouseClick, eventType)) {
	    xControl.addMouseListener(createMouseClickListener(control, listener));
	    return;
	} else if (eq(Events.MouseDoubleClick, eventType)) {
	    xControl.addMouseListener(createMouseDoubleClickListener(control, listener));
	    return;
	} else if (eq(Events.MouseMove, eventType)) {
	    xControl.addMouseMoveListener(createMouseMoveListener(control, listener));
	    return;
	} else if (eq(Events.MouseIn, eventType)) {
	    xControl.addMouseTrackListener(createMouseInListener(control, listener));
	    return;
	} else if (eq(Events.MouseOut, eventType)) {
	    xControl.addMouseTrackListener(createMouseOutListener(control, listener));
	    return;
	} else if (eq(Events.FocusIn, eventType)) {
	    xControl.addFocusListener(createFocusInListener(control, listener));
	    return;
	} else if (eq( Events.FocusOut, eventType)) {
	    xControl.addFocusListener(createFocusOutListener(control, listener));
	    return;
	}
	
	super.addListener(element, eventType, listener);
	
    }
    
    
    @Override
    public void removeListener(UIObject element, String eventType, Listener listener) {
	
	Control control = (Control) element;
	org.eclipse.swt.widgets.Control xControl = (org.eclipse.swt.widgets.Control) element.getDelegate();
	if (xControl == null) {
	    return;
	}
	
	if (eq(Events.KeyDown, eventType)) {
	    xControl.removeKeyListener(getKeyListener(control, listener));
	    return;
	} else if (eq(Events.KeyUp, eventType)) {
	    xControl.removeKeyListener(getKeyListener(control, listener));
	    return;
	} else if (eq(Events.MouseDown, eventType)) {
	    xControl.removeMouseListener(getMouseListener(control, listener));
	    return;
	} else if (eq(Events.MouseUp, eventType)) {
	    xControl.removeMouseListener(getMouseListener(control, listener));
	    return;
	} else if (eq(Events.MouseClick, eventType)) {
	    xControl.removeMouseListener(getMouseListener(control, listener));
	    return;
	} else if (eq(Events.MouseDoubleClick, eventType)) {
	    xControl.removeMouseListener(getMouseListener(control, listener));
	    return;
	} else if (eq(Events.MouseMove, eventType)) {
	    xControl.removeMouseMoveListener(getMouseMoveListener(control, listener));
	    return;
	} else if (eq(Events.MouseIn, eventType)) {
	    xControl.removeMouseTrackListener(getMouseTrackListener(control, listener));
	    return;
	} else if (eq(Events.MouseOut, eventType)) {
	    xControl.removeMouseTrackListener(getMouseTrackListener(control, listener));
	    return;
	} else if (eq(Events.FocusIn, eventType)) {
	    xControl.removeFocusListener(getFocusListener(control, listener));
	    return;
	} else if (eq(Events.FocusOut, eventType)) {
	    xControl.removeFocusListener(getFocusListener(control, listener));
	    return;
	}
	
	super.removeListener(element, eventType, listener);
	
    }
    
    
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	if (Control.METHOD_REPAINT.equals(methodName)) {
	    org.eclipse.swt.widgets.Control xControl = (org.eclipse.swt.widgets.Control) element.getDelegate();
	    if (xControl == null) {
		return null;
	    }
	    xControl.redraw();
	}
	return super.invoke(element, methodName, args);
    }
    
    /**
     * Do layout parent of control if control is visible 
     * @param control
     */
    protected void layout(org.eclipse.swt.widgets.Control control) {
	if (control == null || !control.isVisible()) {
	    return;
	}
	org.eclipse.swt.widgets.Composite parent = control.getParent();
	parent.layout();
    }
}
