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

package org.plazmaforge.framework.uwt.event;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.uwt.widget.Listener;


/**
 * The class represents information about Listener.
 * 
 * @author ohapon
 *
 */
public class ListenerData {

    /**
     * Listener
     */
    private Listener eventListener;
    
    /**
     * Type
     */
    private String eventType;
    
    /**
     * Delegate of listener
     */
    private Object delegate;
    
    /**
     * Delegates of listener.
     * Only for multi delegates mode. 
     */
    private List<Object> delegates;
    

    public ListenerData(String eventType, Listener eventListener) {
	this.eventType = eventType;
	this.eventListener = eventListener;
    }

    public String getEventType() {
        return eventType;
    }

    public Listener getEventListener() {
        return eventListener;
    }

    public Object getDelegate() {
        return delegate;
    }

    public Object getDelegate(int index) {
	if (index == 0) {
	    return delegate;
	}
	if (this.delegates == null || this.delegates.isEmpty()) {
	    return null;
	}
	return this.delegates.get(index);
    }

    public void assignDelegate(Object delegate) {
	if (this.delegate == null) {
	    this.delegate = delegate;
	    return;
	}
	if (this.delegates == null) {
	    this.delegates = new ArrayList<Object>();
	    this.delegates.add(this.delegate); // Add first
	}
	this.delegates.add(delegate); // Add current
    }
    
    public void resetDelegates() {
	this.delegate = null;
	this.delegates.clear();
	this.delegates = null;
    }

    
    
}
