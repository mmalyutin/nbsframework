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

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabItemConfig;

/**
 * 
 * @author ohapon
 *
 */
public class XTabItem extends TabItemConfig {

    private XTabPanel parent;
    
    public XTabItem() {
	super();
    }

    public XTabItem(String text, boolean close) {
	super(text, close);
    }

    public XTabItem(String text) {
	super(text);
    }

    public XTabPanel getParent() {
        return parent;
    }

    protected void setParent(XTabPanel parent) {
        this.parent = parent;
    }
    
    public Widget getWidget() {
	if (parent == null) {
	    return null;
	}
	return parent.getWidget(this);
    }
    
    @Override
    public void setContent(String text) {
	super.setContent(text);
	update();
    }
    
    public void update() {
	if (parent == null) {
	    return;
	}
	Widget widget = getWidget();
	if (widget == null) {
	    // TODO
	    return;
	}
	parent.update(widget, this);
    }

}
