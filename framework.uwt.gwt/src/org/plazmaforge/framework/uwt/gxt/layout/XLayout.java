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

package org.plazmaforge.framework.uwt.gxt.layout;

import org.plazmaforge.framework.uwt.gxt.widget.XContentPanel;

import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.util.Point;
import com.extjs.gxt.ui.client.util.Size;
import com.extjs.gxt.ui.client.util.TextMetrics;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.Container;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.Layout;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;

public abstract class XLayout extends Layout {


    public Size computePreferredSize(Container<?> container) {
	return layoutContainer(container, -1, -1);
    }

    
    public Size computeSize(Container<?> container, int hWidth, int hHeight) {
	return layoutContainer(container, hWidth, hHeight);
    }

    
    protected Size layoutContainer(Container<?> container, El target) {
	Size size = target.getStyleSize();
	return layoutContainer(container, size.width, size.height);
    }
    
    protected abstract Size layoutContainer(Container<?> container, int hWidth, int hHeight);
    
    
    protected Size computeComponentSize(BoxComponent component, int hWidth, int hHeight) {
	
	int width = component.getWidth();
	int height = component.getHeight();
	
	if (width < 0) {
	    width = 0;
	}
	if (height < 0) {
	    height = 0;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	// SPECIAL FIX FOR Label
	///////////////////////////////////////////////////////////////////////////
	if (component instanceof Label) {
	    El el = component.el();
	    String html = el.getInnerHtml();
	    TextMetrics metrics = TextMetrics.get();
	    metrics.bind(el.dom);
	    int textWidth = metrics.getWidth(html);
	    textWidth += 1; //SPECIAL FIX FOR IE
	    int textHeight = metrics.getHeight(html);
	    if (textWidth > width) {
		width = textWidth;
		height = textHeight;
	    }
	} else if (component instanceof Button) {
	    
	    // SPECIAL FIX FOR IE
	    //width += 1;
	    //height += 1;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	Size size = new Size(width, height);
	
	// If component is not container the return current size
	if (!(component instanceof Container)) {
	    return size;
	}
	
	Container container = (Container) component;
	
	// WARNING!!! Compute Size of container
	if (container instanceof LayoutContainer) {
	    Layout layout = ((LayoutContainer) container).getLayout();
	    if (layout != null && layout instanceof XLayout) {
		XLayout xLayout = (XLayout) layout;
		int w = hWidth;
		int h = hHeight;
		if (w == -1 && h == -1) {
		    return xLayout.computeSize(container, w, h);
		}
		El el = container.getLayoutTarget();
		if (el != null) {
		    Size s = el.getStyleSize();
		    if (s != null) {
			if (hWidth != -1) {
			    w = s.width;
			}
			if (hHeight != -1) {
			    h = s.height;
			}
		    }
		}
		return xLayout.computeSize(container, w, h);
	    }
	}
	
	// TODO: Temp solution
	if (size.width > 0 && size.height > 0 && size.width < 7000) { // TODO: 7002px is magic width of GXT TabPane
	    return size;
	}
	
	if (width >= 7000){
	    width = 0; //WTF
	    height = 0;
	}
	
	// TODO: Only for TabPanel
	if (hWidth == -1) {
	    width = -1;
	}
	if (hHeight == -1) {
	    height = -1;
	}

	return computeDefaultContainerSize(container, width, height);
	
    }
    
    
    protected Size computeTrim(Container container, int width, int height) {
	int trimWidth = 0;
	int trimHeight = 0;

	if (container instanceof XContentPanel) {
	    
	    // HARD CODE
	    XContentPanel contentPanel = (XContentPanel) container;
	    Size trimSize = contentPanel.getTrimSize();
	    trimWidth = trimSize.width;
	    trimHeight = trimSize.height;
	    if (contentPanel.isCollapsed()) {
		
		// Reset height because children are not visible
		// Total height is only header bar
		height = 0;
	    }
	} else if (container instanceof TabPanel) {
	    TabPanel tabPanel = ((TabPanel) container);
	    if (tabPanel.getItemCount() > 0) {
		TabItem tabItem = tabPanel.getItem(0);
		if (tabItem.getHeader() != null) {
		    try {
			trimHeight = 30; //15; //Integer.valueOf(DOM.getStyleAttribute(tabItem.getHeader().getElement(), "height"));
		    } catch (Exception e) {
			
		    }
		}
	    }
	    trimWidth = 15; // WHY?
	}
	
	width += trimWidth;
	height += trimHeight;
	
	Size size = new Size(width, height);
	return size;
    }

    
    protected Size computeDefaultContainerSize(Container container, int hWidth, int hHeight) {
	
	boolean needWidth = hWidth <= 0;
	boolean needHeight = hHeight <= 0;

	int width = hWidth;
	int height = hHeight;
	
	// Fix size if need
	if (width < 0) {
	    width = 0;
	}
	if (height < 0) {
	    height = 0;
	}
	
	
	int count = container.getItemCount();
	for (int i = 0; i < count; i++) {
	    BoxComponent child = (BoxComponent) container.getItem(i);
	    if (!child.isRendered()) {
		// Magic error in child.getPosition(true): Method must be called after the component is rendered
		// Reproduce when use CardLayout. May be error when we layout not active item in CardLayout
		continue;
	    }
	    Point p = child.getPosition(true);
	    Size childSize = computeComponentSize(child, hWidth, hHeight);
	    if (needWidth) {
		int w = p.x + childSize.width;
		width = Math.max(width, w);
	    }
	    if (needHeight) {
		int h = p.y + childSize.height;
		height = Math.max(height, h);
	    }
	}
	
	// Fix size if need
	if (width < 0) {
	    width = 0;
	}
	if (height < 0) {
	    height = 0;
	}
	
	Size size = computeTrim(container, width, height); //new Size(width, height);
	return size;
    }

    
    protected XLayoutData getXLayoutData(BoxComponent component) {
	if (component == null) {
	    return null;
	}
	Object layoutData = component.getLayoutData();
	if (layoutData == null) {
	    return null;
	}
	if (!(layoutData instanceof XLayoutData)) {
	    return null;
	}
	return (XLayoutData) layoutData;
    }


}
