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

package org.plazmaforge.framework.uwt;

import org.plazmaforge.framework.uwt.widget.Listener;

/**
 * 
 * @author ohapon
 *
 * General UI Adapter
 *
 */
public interface UIAdapter {

    /**
     * Set property of delegate
     * @param element
     * @param name
     * @param value
     */
    void setProperty(UIObject element, String name, Object value);
    
    /**
     *  Get property of delegate
     * @param delegate
     * @param name
     * @return
     */
    Object getProperty(UIObject element, String name);
    
    /**
     * Invoke method of delegate
     * @param delegate
     * @param methodName
     * @param args
     * @return
     */
    Object invoke(UIObject element, String methodName, Object[] args);
    
    /**
     * Create delegate
     * @param parent
     * @param element
     * @return
     */
    Object createDelegate(UIObject parent, UIObject element);
    
    /**
     * Dispose delegate
     * @param parent
     * @param element
     */
    void disposeDelegate(UIObject parent, UIObject element);
    
    /**
     * Check delegate
     * @param element
     */
    void checkDelegate(UIObject element);
    
    /**
     * Add listener to delegate
     * @param eventType
     * @param listener
     */
    void addListener(UIObject element, String eventType, Listener listener);
    
    /**
     * Remove listener from delegate
     * @param eventType
     * @param listener
     */
    void removeListener(UIObject element, String eventType, Listener listener);
    
}
