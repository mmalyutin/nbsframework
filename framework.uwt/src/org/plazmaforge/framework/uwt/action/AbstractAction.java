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

package org.plazmaforge.framework.uwt.action;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.util.PropertyChangeEvent;
import org.plazmaforge.framework.uwt.util.PropertyChangeListener;

public abstract class AbstractAction implements Action {

    private String id;
    
    private String name;
    
    private String text;
    
    private String description;
        
    private Image icon;

    private boolean enabled;
    
    
    private List<PropertyChangeListener> listeners;
    
    
    
    public AbstractAction() {
	super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
	Object oldValue = this.enabled;
	Object newValue = enabled;
        this.enabled = enabled;
        firePropertyChange(PROPERTY_ENABLED, oldValue, newValue);
    }

    protected List<PropertyChangeListener> getListeners() {
	if (listeners == null) {
	    listeners = new ArrayList<PropertyChangeListener>();
	}
        return listeners;
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
	PropertyChangeEvent event = new PropertyChangeEvent(this, propertyName, oldValue, newValue); 
	for (PropertyChangeListener listener: getListeners()) {
	    listener.propertyChange(event);
	}
    }

    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
	getListeners().add(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
	getListeners().remove(listener);
    }

    
    
}
