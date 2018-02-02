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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.swing.layout.XCardLayout;
import org.plazmaforge.framework.uwt.swing.util.SwingLayoutUtils;
import org.plazmaforge.framework.uwt.swing.widget.IXComponent;
import org.plazmaforge.framework.uwt.widget.Container;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.menu.Menu;

/**
 * 
 * @author ohapon
 *
 */
public abstract class SwingControlAdapter extends SwingWidgetAdapter {


    /**
     * Add child to parent
     * @param xParent
     * @param xElement
     * @param element
     */
    protected void addChild(java.awt.Container xParent, java.awt.Component xElement, UIObject element) {
	
	checkNullParent(xParent, MESSAGE_CANT_ADD_WIDGET);
	checkContainerParent(xParent, MESSAGE_CANT_ADD_WIDGET);
	
	LayoutManager xLayout = xParent.getLayout();
	
	/*
	if (xParent instanceof JSplitPane) {
	    JSplitPane splitPanel = (JSplitPane) xParent;
	    int count = splitPanel.getComponentCount();
	    count--; // Fix count (divider is first component)
	    if (count == 0) {
		xParent.add(xElement, JSplitPane.LEFT);
		return;
	    } else if (count == 1) {
		xParent.add(xElement, JSplitPane.RIGHT);
		return;
	    } else {
		//TODO
		return;
	    }
	    
	}
	*/
	
	if (xLayout == null) {
	    xParent.add(xElement);
	    return;
	}
	
	if (xLayout instanceof GridBagLayout) {
	    Container composite = (Container) element.getUIParent();
	    //GridData gd = (GridData) ((Control) element).getLayoutData();
	    xParent.add(xElement);
	    //xParent.add(xElement, gd.getDelegate());
	    SwingLayoutUtils.calculateCell(xParent, composite);
	    return;
	} else if (xLayout instanceof java.awt.CardLayout || xLayout instanceof XCardLayout) {
	    xParent.add(xElement, "");
	    return;
	}
	xParent.add(xElement);
    }
    
    public void disposeDelegate(UIObject parent, UIObject element) {
	java.awt.Container xParent = getContent(parent.getDelegate());
	java.awt.Component xElement = asComponent(element.getDelegate());
	if (xParent != null) {
	    xParent.remove(xElement);
	}
    }

    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	
	
	java.awt.Component xControl = asComponent(element.getDelegate());
	if (xControl == null) {
	    return;
	}
	javax.swing.JComponent xJControl = null;
	
	if (Control.PROPERTY_VISIBLE.equals(name)) {
	    xControl.setVisible(asBoolean(value));
	} else if (Control.PROPERTY_ENABLED.equals(name)) {
	    xControl.setEnabled(asBoolean(value));
	} else if (Control.PROPERTY_WIDTH.equals(name)) {
	    int width = (Integer) value;
	    
	    if (xControl instanceof IXComponent) {
		((IXComponent) xControl).setPreferredWidth(width);
		return;
	    }
	    
	    // TODO Why getSize. May be getPrefferdSize if use javax.swing.JComponent
	    java.awt.Dimension size = xControl.getSize();  
	    size.width = width;
	    
	    if (xControl instanceof javax.swing.JComponent) {
		xControl.setPreferredSize(size);
	    } else {
		xControl.setSize(size);
	    }
		  
	    
	    return;
	} else if (Control.PROPERTY_HEIGHT.equals(name)) {
	    int height = (Integer) value;

	    if (xControl instanceof IXComponent) {
		((IXComponent) xControl).setPreferredHeight(height);
		return;
	    }

	    // TODO Why getSize. May be getPrefferdSize if use javax.swing.JComponent
	    java.awt.Dimension size = xControl.getSize();
	    size.height = (Integer) value;
	    
	    
	    if (xControl instanceof javax.swing.JComponent) {
		xControl.setPreferredSize(size);
	    } else {
		xControl.setSize(size);
	    }
	    
	    return;
	} else if (Control.PROPERTY_LAYOUT_DATA.equals(name)) {
	    UIObject layoutData = (UIObject) value;
	    
	    Object xLayoutData = null;
	    if (layoutData != null) {
		layoutData.activateUI();
		xLayoutData = layoutData.getDelegate();
	    }
	    
	    java.awt.Container xParent = xControl.getParent();
	    if (xParent == null) {
		return;
	    }
	    
	    LayoutManager xLayout = xParent.getLayout();
	    if (xLayout == null) {
		return;
	    }
	    if (xLayout instanceof GridBagLayout) {
		GridBagLayout xGridLayout = (GridBagLayout) xLayout;
		GridBagConstraints xConstraints = (GridBagConstraints) xLayoutData;
		xGridLayout.setConstraints(xControl, xConstraints);
		Control control = (Control) element;
		Container parent = (Container) control.getParent();
		SwingLayoutUtils.calculateCell(xParent, parent);
	    }
	    return;
	} else if (Control.PROPERTY_TOOL_TIP.equals(name)) {
	    xControl = getViewContent(xControl);
	    if (xControl instanceof javax.swing.JComponent) {
		xJControl = (javax.swing.JComponent) xControl;
		xJControl.setToolTipText(asSafeString(value));
	    }
	    return;
	} else if (Container.PROPERTY_BACKGROUND.equals(name)) {
	    xControl = getViewContent(xControl);
	    xControl.setBackground(getColor(asColor(value)));
	    return;
	} else if (Container.PROPERTY_FOREGROUND.equals(name)) {
	    xControl = getViewContent(xControl);
	    xControl.setForeground(getColor(asColor(value)));
	    return;
	} else if (Control.PROPERTY_FONT.equals(name)) {
	    xControl.setFont(getFont(asFont(value)));
	    return;
	} else if (Control.PROPERTY_CONTEXT_MENU.equals(name)) {
	    xControl = getViewContent(xControl);
	    if (xControl instanceof javax.swing.JComponent) {
		xJControl = (javax.swing.JComponent) xControl;
		Menu menu = (Menu) value;
		menu.activateUI();
		xJControl.setComponentPopupMenu((javax.swing.JPopupMenu) menu.getDelegate());
	    }
	    return;
	}

	
	super.setProperty(element, name, value);
	 
	
    }
    
    
    @Override
    public Object getProperty(UIObject element, String name) {
	
	java.awt.Component xControl = asComponent(element.getDelegate());
	if (xControl == null) {
	    return null;
	}
	if (Control.PROPERTY_VISIBLE.equals(name)) {
	    return xControl.isVisible();
	} else if (Control.PROPERTY_ENABLED.equals(name)) {
	    return xControl.isEnabled();
	}
	
	return super.getProperty(element, name);
    }
    
    
 
    
    @Override
    public void addListener(UIObject element, String eventType, Listener listener) {
	
	Control control = (Control) element;
	java.awt.Component xControl = getViewComponent(element.getDelegate());
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
	    xControl.addMouseListener(createMouseMoveListener(control, listener));
	    return;
	} else if (eq(Events.MouseIn, eventType)) {
	    xControl.addMouseListener(createMouseInListener(control, listener));
	    return;
	} else if (eq(Events.MouseOut, eventType)) {
	    xControl.addMouseListener(createMouseOutListener(control, listener));
	    return;
	} else if (eq(Events.FocusIn, eventType)) {
	    xControl.addFocusListener(createFocusInListener(control, listener));
	    return;
	} else if (eq(Events.FocusOut, eventType)) {
	    xControl.addFocusListener(createFocusOutListener(control, listener));
	    return;
	}
	
	super.addListener(element, eventType, listener);
    }
    
    @Override
    public void removeListener(UIObject element, String eventType, Listener listener) {
	
	Control control = (Control) element;
	java.awt.Component xControl = getViewComponent(element.getDelegate());
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
	    xControl.removeMouseListener(getMouseListener(control, listener));
	    return;
	} else if (eq(Events.MouseIn, eventType)) {
	    xControl.removeMouseListener(getMouseListener(control, listener));
	    return;
	} else if (eq(Events.MouseOut, eventType)) {
	    xControl.removeMouseListener(getMouseListener(control, listener));
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
	    java.awt.Component xControl = getViewComponent(element.getDelegate());
	    if (xControl == null) {
		return null;
	    }
	    xControl.repaint();
	}
	return super.invoke(element, methodName, args);
    }
   
}
