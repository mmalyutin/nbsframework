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

import org.plazmaforge.framework.uwt.gxt.layout.XLayout;
import org.plazmaforge.framework.uwt.gxt.util.GXTUtils;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Size;
import com.sencha.gxt.widget.core.client.Window;

/**
 * 
 * @author ohapon
 *
 */
public class XWindow extends Window implements HasLayoutContainer {

    private XLayoutContainer content;
    
    public XWindow() {
	super();
	initContent(null, null);
    }

    public XWindow(HasWidgets container, XLayout layout) {
	super();
	initContent(container, layout);
    }

    protected void initContent(HasWidgets container, XLayout layout) {
	content = new XLayoutContainer(container, layout);
	super.add(content);
    }
    
    @Override
    public void add(Widget child) {
	content.add(child);
    }

    @Override
    public boolean remove(Widget child) {
	return content.remove(child);
    }   
    
    public void pack() {
	//GWT.log("PACK-1");
	if (content == null) {
	    return;
	}
	Size frameSize = getFrameSize();
	
	// Compute size of content
	Size computeSize = GXTUtils.computeSize(-1, -1, (Widget) content.getContainer());
	//Size computeSize = GXTUtils.computePreferredSize(content);
	if (computeSize == null) {
	    //GWT.log("PACK-2: computeSize == null");
	    return;
	}
	
	
	// Get frame size
	int frameWidth = frameSize.getWidth();
	int frameHeight = frameSize.getHeight();
	
	// Get compute size
	int computeWidth = computeSize.getWidth()  + frameWidth;
	int computeHeight = computeSize.getHeight()  + frameHeight + 20;

	//GWT.log("PACK-2: frameSize  [" + frameWidth + ", " + frameHeight + "]");
	//GWT.log("PACK-2: computeSize[" + computeWidth + ", " + computeHeight + "]");
	
	int width = 0;
	int height = 0;
	//if (computeWidth > 0 && frameWidth != computeWidth) {
	    width = computeWidth; 
	//}
	//if  ((computeHeight > 0 && frameHeight != computeHeight)) {
	    height = computeHeight; 
	//}
	
	if (width > 0 && height > 0) {
	    GWT.log("PACK-3: pizelSize[" + width + ", " + height + "]");
	    setPixelSize(width, height);
	    //getContainerTarget().setWidth(width);
	    //getContainerTarget().setHeight(height);
	    //forceLayout();
	}
    }
}
