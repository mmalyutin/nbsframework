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
package org.plazmaforge.framework.uwt.gxt.util;

import org.plazmaforge.framework.uwt.gxt.widget.HasComputeSize;
import org.plazmaforge.framework.uwt.gxt.widget.XCoolBar;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.util.Size;
import com.sencha.gxt.core.client.util.TextMetrics;
import com.sencha.gxt.core.client.util.Util;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.HasLayout;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

/**
 * 
 * @author ohapon
 *
 */
public class GXTUtils {

    private GXTUtils() {
    }

    public static int getStyleWidth(Widget widget) {
	return getStyleWidth(widget.getElement());
    }

    public static int getStyleHeight(Widget widget) {
	return getStyleHeight(widget.getElement());
    }

    public static int getStyleWidth(Element element) {
	return getPropertyInt(element, "width", Style.DEFAULT);
    }

    public static int getStyleHeight(Element element) {
	return getPropertyInt(element, "height", Style.DEFAULT);
    }

    public static int getPropertyInt(Element element, String property, int def) {
	return Util.parseInt(element.getStyle().getProperty(property), def);
    }
    
    /**
     * Compute preferred size of widget
     * @param widget
     * @return
     */
    public static Size computePreferredSize(Widget widget) {
	
	// Style size
	int styleWidth = GXTUtils.getStyleWidth(widget); 	
	int styleHeight = GXTUtils.getStyleHeight(widget);
	
	// Offset size
	//int offsetWidth = GXTUtils.getOffsetWidth(widget);
	//int offsetHeight = GXTUtils.getOffsetHeight(widget);	
	
	// Compute size
	int computeWidth = -1;
	int computeHeight = -1;	
	
	// Set size. Style size is priority
	int width = styleWidth;
	int height = styleHeight;
	
	//if (width == -1) {
	//    width = GXTUtils.computeMinWidth(widget);
	//}
	    
	//if (height == -1) {
	//    height = GXTUtils.computeMinHeight(widget);
	//}
	
	// If style width/height is not setting then compute size
	Size computeSize = null;
	if (width == -1 || height == -1) {
	    
	    // Compute size of widget
	    computeSize = GXTUtils.computeSize(styleWidth, styleHeight, widget);
	    if (computeSize != null) {
		computeWidth = computeSize.getWidth();
		computeHeight = computeSize.getHeight();
		if (width == -1) {
		    width = computeWidth;
		}
		if (height == -1) {
		    height = computeHeight;
		}
	    }

	}
	
	if (width == -1) {
	    width = GXTUtils.computeMinWidth(widget);
	}
	    
	if (height == -1) {
	    height = GXTUtils.computeMinHeight(widget);
	}
	
	return new Size(width, height);
    }
    
   
    public static Size computeSize(int hWidth, int hHeight, Widget widget) {
	
	if (widget == null) {
	    return new Size(0, 0);
	}
	
	// Fixed size of Label
	// When we use ' ' in text methods getOffsetWidth/Height return incorrect values
	if (widget instanceof Label) {
	    Label label = (Label) widget;
	    String text = label.getText();
	    if (text == null) {
		text = "";
	    }
	    TextMetrics metrics = TextMetrics.get();
	    metrics.bind(widget.getElement());
	    Size size = metrics.getSize(text);
	    size.setWidth(size.getWidth() + 1);
	    
	    // TODO: +2 w/h
	    return size;
	}
	
	if (widget instanceof HasComputeSize) {
	    return ((HasComputeSize) widget).computeSize(hWidth, hHeight, false);
	}
	
	return getOffsetSize(widget);
    }
    
    public static Size getOffsetSize(Widget widget) {
	if (widget == null) {
	    return new Size(0, 0);
	}
	if (widget instanceof XCoolBar) {
	    // TODO: Magic
	    // Maybe widget.getOffsetHeight() - 63
	    return new Size(widget.getOffsetWidth(), XCoolBar.MAGIC_HEIGHT);
	}
	
	//if (widget instanceof Component) {
	//    return ((Component) widget).getElement().getSize();
	//}
	
	return new Size(widget.getOffsetWidth(), widget.getOffsetHeight());
    }
    
    
    public static int getOffsetWidth(Widget widget) {
	return widget.getOffsetWidth();
    }

    public static int getOffsetHeight(Widget widget) {
	if (widget instanceof XCoolBar) {
	    // TODO: Magic
	    // Maybe widget.getOffsetHeight() - 63
	    return XCoolBar.MAGIC_HEIGHT;
	}
	return widget.getOffsetHeight();
    }
        
    /**
     * Compute max size of widget
     * @param widget
     * @return
     */
    public static int computeMinWidth(Widget widget) {
	if (widget == null) {
	    return -1;
	}
	//if (widget instanceof Label) {
	//    return widget.getOffsetWidth() + 1;
	//}
	if (widget instanceof Grid) {
	    Grid<?> grid = (Grid<?>) widget;
	    return grid.getColumnModel().getTotalWidth();
	}
	if (widget instanceof ToolBar) {
	    return widget.getOffsetWidth() + 10;
	}
	//if (widget instanceof TabPanel) {
	//    TabPanel tabPanel = (TabPanel) widget;
	//    return tabPanel.getTabWidth();
	//}
	return -1;
    }

    /**
     * Compute min size of widget
     * @param widget
     * @return
     */
    public static int computeMinHeight(Widget widget) {
	if (widget == null) {
	    return -1;
	}
	//if (widget instanceof TabPanel) {
	    // TabPanel tabPanel = (TabPanel) widget;
	//    return widget.getOffsetHeight() + 10;
	//}
	return -1;
    }
    
    public static void forceLayout(Widget widget) {
	if (widget == null) {
	    return;
	}
	if (widget instanceof HasLayout) {
	    ((HasLayout) widget).forceLayout();
	}
    }
    
    public static void forceParentLayout(Widget widget) {
	if (widget == null) {
	    return;
	}
	forceLayout(widget.getParent());
    }
  
}
