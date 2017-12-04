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

package org.plazmaforge.framework.uwt.gxt.adapter;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Label;
import org.plazmaforge.framework.uwt.widget.Link;
import org.plazmaforge.framework.uwt.widget.Listener;

/**
 * 
 * @author ohapon
 *
 */
public class GXTLinkAdapter extends GXTControlAdapter {

    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	com.google.gwt.user.client.ui.Anchor xLink = new com.google.gwt.user.client.ui.Anchor("", false);
	Link link = (Link) element;

	// Get text
	String text = link.getText();
	if (text != null) {
	    xLink.setText(text);
	}
	addChild(getContent(parent.getDelegate()), xLink, element);
	return xLink;
    }

    protected com.google.gwt.user.client.ui.Anchor asLink(Object delegate) {
	return (com.google.gwt.user.client.ui.Anchor) delegate;
    }

    @Override
    public void setProperty(UIObject element, String name, Object value) {
	com.google.gwt.user.client.ui.Anchor xLink = asLink(element.getDelegate());
	if (xLink == null) {
	    return;
	}
	if (Label.PROPERTY_TEXT.equals(name)) {
	    xLink.setText(asSafeString(value));
	    return;
	}
	super.setProperty(element, name, value);
    }

    
    @Override
    public void addListener(UIObject element, String eventType, Listener listener) {
	Control control = (Control) element;
	com.google.gwt.user.client.ui.Anchor xLink = asLink(element.getDelegate());
	if (xLink == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    addMouseClickListener(xLink, control, listener);
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }
    
    @Override
    public void removeListener(UIObject element, String eventType, Listener listener) {
	Control control = (Control) element;
	com.google.gwt.user.client.ui.Anchor xLink = asLink(element.getDelegate());
	if (xLink == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    removeMouseClickListener(xLink, control, listener);
	    return;
	} 
	
	super.removeListener(element, eventType, listener);
    }
}
