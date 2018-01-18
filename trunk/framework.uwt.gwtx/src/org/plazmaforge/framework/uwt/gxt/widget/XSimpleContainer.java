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

import org.plazmaforge.framework.uwt.gxt.util.GXTUtils;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Size;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

/**
 * 
 * @author ohapon
 *
 */
public class XSimpleContainer extends SimpleContainer implements HasComputeSize {

    public XSimpleContainer() {
	super();
    }

    public XSimpleContainer(boolean deferElement) {
	super(deferElement);
    }

    @Override
    protected void doLayout() {
	super.doLayout();
    }
    
    protected Size getOffsetSize() {
 	return GXTUtils.getOffsetSize(this);
     }
     
    public Size computeSize(int hWidth, int hHeight, boolean layout) {
	Widget container = getWidget();
	if (container == null || !(container instanceof HasComputeSize)) {
	    return getOffsetSize();
	}
	return ((HasComputeSize) container).computeSize(hWidth, hHeight, false);
    }
}
