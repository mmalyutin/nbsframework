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

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.google.gwt.user.client.Element;

public class XSeparator extends BoxComponent {

    private Orientation orientation = Orientation.HORIZONTAL;
    
    public XSeparator() {
	//setText(".");
	//setStyleAttribute("borderBottom", "1px  solid");
    }
    
    public XSeparator(Orientation orientation) {
	super();
	this.orientation = orientation;
    }

    @Override
    protected void onRender(Element target, int index) {
	//TODO: Must analyze orientation
	//Element span = XDOM.create("<span class=x-menu-sep>&#160;</span>");
	Element span = XDOM.create("<div style=\"font-size: 1px; line-height: 1px; margin: 2px 3px; border-bottom: 1px solid;\">&#160;</div>");
	setElement(span, target, index);
	//fly(target).addStyleName("x-menu-sep-li");
    }
    
    
}
