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

package org.plazmaforge.framework.config.object;

/**
 * 
 * @author ohapon
 *
 */
public class ActionDescriptorConfig implements IActionDescriptorConfig {

    private static final long serialVersionUID = 5850232177406608586L;

    private String id;
    
    private String name;
    
    private String ref;
    
    // dropdown, item (toolitem, menuitem), link
    private String controlType;
    
    // first, last, before, after (only for item, toolitem, menuitem types)
    private String controlPosition;
    
    
    private String controlRelative;
    

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

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getControlType() {
        return controlType;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    public String getControlPosition() {
        return controlPosition;
    }

    public void setControlPosition(String controlPosition) {
        this.controlPosition = controlPosition;
    }

    public String getControlRelative() {
        return controlRelative;
    }

    public void setControlRelative(String controlRelative) {
        this.controlRelative = controlRelative;
    }
    
    
}
