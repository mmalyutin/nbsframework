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
package org.plazmaforge.framework.uwt.gwt.adapter;

import org.plazmaforge.framework.uwt.UIObject;

public abstract class GWTControlAdapter extends GWTWidgetAdapter {

    
    protected com.google.gwt.user.client.ui.Panel getPanel(Object delegate) {
	return (com.google.gwt.user.client.ui.Panel) delegate;
    }

    protected com.google.gwt.user.client.ui.Widget getWidget(Object delegate) {
	return (com.google.gwt.user.client.ui.Widget) delegate;
    }

    protected com.google.gwt.user.client.ui.Panel getParentContent(Object delegate) {
	if (delegate == null) {
	    return null;
	}
	//if (delegate instanceof Dialog) {
	// return ((Dialog) delegate).getContent();    
	//}
	return (com.google.gwt.user.client.ui.Panel) delegate;
    }
    
    
    @Override
    public void disposeDelegate(UIObject parent, UIObject element) {
	com.google.gwt.user.client.ui.Panel parentDelegate = (com.google.gwt.user.client.ui.Panel) getPanel(parent.getDelegate());
	com.google.gwt.user.client.ui.Widget delegate = getWidget(element.getDelegate());
	if (parentDelegate != null) {
	    parentDelegate.remove(delegate); // Remove from parent
	}
    }

    
    
}
