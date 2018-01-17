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

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;

/**
 * 
 * @author ohapon
 *
 */
public class XContentPanel extends ContentPanel implements HasLayoutContainer {

    private XLayoutContainer content;

    public XContentPanel() {
	super();
	initContent(null, null);
    }
    
    public XContentPanel(HasWidgets container, XLayout layout) {
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

}
