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

/**
 * 
 */
package org.plazmaforge.framework.uwt.widget.panel;

import org.plazmaforge.framework.uwt.graphics.Size;


/**
 * @author ohapon
 *
 */
public class ScrollPanel extends Panel {

    public static final String PROPERTY_CONTENT_WIDTH = "contentWidth";
    
    public static final String PROPERTY_CONTENT_HEIGHT = "contentHeight";

    
    private Size contentSize;
    
    
    public ScrollPanel() {
    }

    public int getContentWidth() {
	return doGetContentSize().getWidth();
    }

    public void setContentWidth(int contentWidth) {
	doGetContentSize().setWidth(contentWidth);
	fireChangeProperty(PROPERTY_CONTENT_WIDTH, contentWidth);

    }

    public int getContentHeight() {
        return doGetContentSize().getHeight();
    }

    public void setContentHeight(int contentHeight) {
	doGetContentSize().setHeight(contentHeight);
	fireChangeProperty(PROPERTY_CONTENT_HEIGHT, contentHeight);

    }

    protected Size doGetContentSize() {
	if (contentSize == null) {
	    contentSize = new Size(0, 0);
	}
	return contentSize;
    }

}
