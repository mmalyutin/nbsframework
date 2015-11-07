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

import org.plazmaforge.framework.uwt.UWT;
import org.plazmaforge.framework.uwt.UWTException;
import org.plazmaforge.framework.uwt.event.FocusListener;
import org.plazmaforge.framework.uwt.event.KeyListener;
import org.plazmaforge.framework.uwt.event.MouseListener;
import org.plazmaforge.framework.uwt.event.MouseMoveListener;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.graphics.Point;
import org.plazmaforge.framework.uwt.graphics.Rectangle;
import org.plazmaforge.framework.uwt.graphics.Size;
import org.plazmaforge.framework.uwt.widget.menu.Menu;

public class Control extends Widget {

    
    //////////////////////////////////////////////////////////////////////
    // METHODS
    //////////////////////////////////////////////////////////////////////

    
    public static final String METHOD_REPAINT = "repaint";

    
    /**
     * Background color of control
     */
    private Color background;
    
    /**
     * Foreground color of control
     */
    private Color foreground;
  
    /**
     * Font of control
     */
    private Font font;
    
    /**
     * Special layout data to use in <code>Layout</code>
     *
     */
    private Object layoutData;
    
    /**
     * Location of control (x, y)
     */
    private Point location;
    
    /**
     * Size of control (width, height)
     */
    private Size size;
    
    /**
     * Preferred size of control (preferred width, preferred height)
     */
    private Size preferredSize;
    

    /**
     * Context/popup menu
     */
    private Menu contextMenu;
    
    /**
     * ToolTip of control
     */
    private String toolTip;
    

    /**
     * True if control is visible
     */
    private boolean visible;
    
    
    /**
     * True if control is enabled
     */
    private boolean enabled;
    
    
    public Control() {
	visible = true;
	enabled = true;
    }

    public final boolean isVisible() {
	if (isActiveUI()) {
	    return getBooleanDelegateProperty(PROPERTY_VISIBLE);
	}
	return visible;
    }

    public final void setVisible(boolean visible) {
        this.visible = visible;
        fireChangeProperty(PROPERTY_VISIBLE, visible);
    }

    public final boolean isEnabled() {
	if (isActiveUI()) {
	    return getBooleanDelegateProperty(PROPERTY_ENABLED);
	}
	return enabled;
    }

    
    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
        fireChangeProperty(PROPERTY_ENABLED, enabled);
    }



    /**
     * Return true if the widget is rendered
     * @return
     */
    public final boolean isRendered() {
	return isActiveUI();
	//if (!isActiveUI()) {
	//    return false;
	//}
	//return getBooleanDelegateProperty(PROPERTY_VISIBLE);
    }

    public final void repaint() {
	if (!isRendered()) {
	    return;
	}
	invokeDelegate(this, METHOD_REPAINT);
    }
    
    protected Point doGetLocation() {
  	if (location == null) {
  	    location = new Point(0, 0);
  	}
  	return location;
    }
    
    public Point getLocation() {
  	return new Point(getX(), getY());
    }
    
    public int getX() {
        return doGetLocation().getX();
    }

    public void setX(int x) {
	doGetLocation().setX(x);
        fireChangeProperty(PROPERTY_X, x);
    }

    public int getY() {
        return doGetLocation().getY();
    }

    public void setY(int y) {
	doGetLocation().setY(y);
        fireChangeProperty(PROPERTY_Y, y);
    }

    protected Size doGetSize() {
  	if (size == null) {
  	    size = new Size(0, 0);
  	}
  	return size;
    }
    
    
    public int getWidth() {
        return doGetSize().getWidth();
    }

    public void setWidth(int width) {
	doGetSize().setWidth(width);
	if (!isActiveUI() && (preferredSize == null || preferredSize.getWidth() == UWT.DEFAULT)) {
	    setPreferredWidth(width);
	}
	fireChangeProperty(PROPERTY_WIDTH, width);
    }

    public int getHeight() {
        return doGetSize().getHeight();
    }

    public void setHeight(int height) {
	doGetSize().setHeight(height);
	if (!isActiveUI() && (preferredSize == null || preferredSize.getHeight() == UWT.DEFAULT)) {
	    setPreferredHeight(height);
	}
	fireChangeProperty(PROPERTY_HEIGHT, height);
    }
    
    public void setSize(Size size) {
	int width = size == null ? 0 : size.getWidth();
	int height = size == null ? 0 : size.getHeight();
	setSize(width, height);
    }
    
    public void setSize(int width, int height) {
	setWidth(width);
	setHeight(height);
    }

    public Size getSize() {
	return new Size(getWidth(), getHeight());
    }
    
    
    protected Size doPreferredSize() {
 	if (preferredSize == null) {
 	    preferredSize = createPreferredSize();
 	}
 	return preferredSize;
     }

     protected Size createPreferredSize() {
         return new Size(UWT.DEFAULT, UWT.DEFAULT);
     }
    
    public Size getPreferredSize() {
        return new Size(preferredSize == null ? UWT.DEFAULT : preferredSize.getWidth()
        	, preferredSize == null ? UWT.DEFAULT : preferredSize.getHeight());
    }

    public void setPreferredSize(Size preferredSize) {
	int preferredWidth = preferredSize == null ? UWT.DEFAULT : preferredSize.getWidth();
	int preferredHeight = preferredSize == null ? UWT.DEFAULT : preferredSize.getHeight();
	setPreferredWidth(preferredWidth);
	setPreferredHeight(preferredHeight);
	
        //this.preferredSize = preferredSize;
        //TODO fireEvent
    }

    public void setPreferredWidth(int preferredWidth) {
        doPreferredSize().setWidth(preferredWidth);
        if (layoutData != null && layoutData instanceof LayoutData) {
            LayoutData ld = (LayoutData) layoutData;
            ld.setPreferredWidth(getPreferredWidth());
        }

        //TODO fireEvent
    }
   
    public int getPreferredWidth() {
	return preferredSize == null ? UWT.DEFAULT : preferredSize.getWidth();
    }

    public void setPreferredHeight(int preferredHeight) {
        doPreferredSize().setHeight(preferredHeight);
        if (layoutData != null && layoutData instanceof LayoutData) {
            LayoutData ld = (LayoutData) layoutData;
            ld.setPreferredHeight(getPreferredHeight());
        }

        //TODO fireEvent
    }

    public int getPreferredHeight() {
	return preferredSize == null ? UWT.DEFAULT : preferredSize.getHeight();
    }

    
 

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
        fireChangeProperty(PROPERTY_BACKGROUND, background);
    }

    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
        fireChangeProperty(PROPERTY_FOREGROUND, foreground);
    }

    public Object getLayoutData() {
        return layoutData;
    }

    public void setLayoutData(Object layoutData) {
        doSetLayoutData(layoutData);
        fireChangeProperty(PROPERTY_LAYOUT_DATA, layoutData);
    }

    protected void doSetLayoutData(Object layoutData) {
        this.layoutData = layoutData;
        if (layoutData != null && layoutData instanceof LayoutData) {
            LayoutData ld = (LayoutData) layoutData;
            ld.setPreferredWidth(getPreferredWidth());
            ld.setPreferredHeight(getPreferredHeight());
        }
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
        fireChangeProperty(PROPERTY_FONT, font);
    }

    /**
     * Rectangle around control
     * @return
     */
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public Menu getContextMenu() {
        return contextMenu;
    }
    

    public void setContextMenu(Menu contextMenu) {
        if (contextMenu != null) {
            // This control is parent of context menu
            contextMenu.setParent(this);
        }
        this.contextMenu = contextMenu;
        fireChangeProperty(PROPERTY_CONTEXT_MENU, contextMenu);
    }

    
    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
        fireChangeProperty(PROPERTY_TOOL_TIP, toolTip);
    }

    public void addKeyListener(KeyListener listener) {
  	addKeyListener(this, listener);
    }

    public void removeKeyListener(KeyListener listener) {
  	removeKeyListener(this, listener);
    }
    
    
    
    public void addMouseListener(MouseListener listener) {
  	addMouseListener(this, listener);
    }

    public void removeMouseListener(MouseListener listener) {
  	removeMouseListener(this, listener);
    }

    
    
    public void addMouseMoveListener(MouseMoveListener listener) {
   	addMouseMoveListener(this, listener);
    }
    
    public void removeMouseMoveListener(MouseMoveListener listener) {
	removeMouseMoveListener(this, listener);
    }
    
 
    
    public void addFocusListener(FocusListener listener) {
   	addFocusListener(this, listener);
    }
    
    public void removeFocusListener(FocusListener listener) {
   	removeFocusListener(this, listener);
    }

    
    protected void checkNonRenderedProperty(String property) {
	if (isRendered()) {
	    throw new UWTException("Can't change non rendered property '" + property + "'");
	}
    }

}
