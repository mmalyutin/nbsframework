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
package org.plazmaforge.framework.uwt.gxt.widget;

import java.util.logging.Logger;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.GXTLogConfiguration;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.resources.CommonStyles;
import com.sencha.gxt.core.client.util.Size;
import com.sencha.gxt.core.client.util.Util;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.InsertResizeContainer;

/**
 * 
 * @author ohapon
 *
 */
public class XGridLayoutContainer2 extends InsertResizeContainer {
    
    private static Logger logger = Logger.getLogger(XGridLayoutContainer2.class.getName());

    public XGridLayoutContainer2() {
	super();
	setElement(Document.get().createDivElement());
	//getContainerTarget().makePositionable(false);
    }

    protected XElement getLayoutContainer() {
	return getElement();
    }

    @Override
    protected void doLayout() {
	XElement container = getLayoutContainer();
	
	// Get size of container (style or computed)
	Size size = container.getStyleSize();

	if (GXTLogConfiguration.loggingIsEnabled()) {
	    logger.finest(getId() + " doLayout  size: " + size);
	}
	
	int w = size.getWidth(); // - getScrollOffset();
	int h = size.getHeight();

	int styleHeight = Util.parseInt(container.getStyle().getProperty("height"), Style.DEFAULT);
	int styleWidth = Util.parseInt(container.getStyle().getProperty("width"), Style.DEFAULT);

	boolean findWidth = styleWidth == -1;
	boolean findHeight = styleHeight == -1;

	if (GXTLogConfiguration.loggingIsEnabled()) {
	    logger.finest(getId() + " findWidth: " + findWidth + " findHeight: " + findHeight);
	}
	
	int count = getWidgetCount();
	int offsetY = 0;
	for (int i = 0; i < count; i++) {

	    Widget widget = getWidget(i);
	    if (!widget.isVisible()) {
		continue;
	    }
	    widget.addStyleName(CommonStyles.get().positionable());
	    widget.getElement().getStyle().setMargin(0, Unit.PX);

	    boolean component = widget instanceof Component;
	    Component c = null;
	    if (component) {
		c = (Component) widget;
	    }

	    int x = 5;
	    int y = offsetY;
	    if (component) {
		c.setPosition(x, y);
	    } else {
		XElement.as(widget.getElement()).setLeftTop(x, y);
	    }
	    
	    offsetY += (widget.getOffsetHeight() + 5);

	}
	
    }
}
