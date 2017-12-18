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

import org.plazmaforge.framework.uwt.gxt.widget.XCoolBar;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.util.Size;
import com.sencha.gxt.core.client.util.Util;
import com.sencha.gxt.widget.core.client.TabPanel;
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
	if (widget == null) {
	    return new Size(0, 0);
	}

	int styleWidth = GXTUtils.getStyleWidth(widget);
	int styleHeight = GXTUtils.getStyleHeight(widget);

	int offsetWidth = GXTUtils.getOffsetWidth(widget);
	int offsetHeight = GXTUtils.getOffsetHeight(widget);

	int width = styleWidth;
	int height = styleHeight;

	if (width == -1) {
	    width = GXTUtils.computeMinWidth(widget);
	    if (width == -1) {
		width = offsetWidth;
	    }
	}
	if (height == -1) {
	    height = GXTUtils.computeMinHeight(widget);
	    if (height == -1) {
		height = offsetHeight;
	    }
	}

	return new Size(width, height);
    }
    
    public static int getOffsetWidth(Widget widget) {
	return widget.getOffsetWidth();
    }

    public static int getOffsetHeight(Widget widget) {
	if (widget instanceof XCoolBar) {
	    // TODO: Magic
	    // Maybe widget.getOffsetHeight() - 63
	    return 26;
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
	if (widget instanceof Label) {
	    return widget.getOffsetWidth() + 1;
	}
	if (widget instanceof Grid) {
	    Grid<?> grid = (Grid<?>) widget;
	    return grid.getColumnModel().getTotalWidth();
	}
	if (widget instanceof ToolBar) {
	    return widget.getOffsetWidth() + 10;
	}
	if (widget instanceof TabPanel) {
	    TabPanel tabPanel = (TabPanel) widget;
	    return tabPanel.getTabWidth();
	}
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
	if (widget instanceof TabPanel) {
	    // TabPanel tabPanel = (TabPanel) widget;
	    return widget.getOffsetHeight() + 10;
	}
	return -1;
    }
}
