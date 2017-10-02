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

import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.dom.XDOM;
import com.sencha.gxt.widget.core.client.Component;

import org.plazmaforge.framework.uwt.widget.Style.Orientation;

/**
 * 
 * @author ohapon
 *
 */
public class XSeparator extends Component {

    public static final Orientation DEFAULT_ORIENTATION = Orientation.HORIZONTAL;
	    
    private Orientation orientation;
    
    public XSeparator() {
	this(DEFAULT_ORIENTATION);
    }
    
    public XSeparator(Orientation orientation) {
	super();
	this.orientation = orientation == null ? DEFAULT_ORIENTATION : orientation;
	String borderAttribute = orientation == Orientation.HORIZONTAL ? "border-bottom" : "border-left";
	SafeHtmlBuilder builder = new SafeHtmlBuilder();
	builder.appendHtmlConstant("<div style=\"font-size: 1px; line-height: 1px; margin: 2px 3px; " + borderAttribute + ": 1px solid;\">&#160;</div>");
	setElement((Element) XDOM.create(builder.toSafeHtml()));
    }

    public Orientation getOrientation() {
        return orientation;
    }

    
}
