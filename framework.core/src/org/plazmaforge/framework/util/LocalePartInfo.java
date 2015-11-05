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
package org.plazmaforge.framework.util;

import java.io.Serializable;


/**
 * 
 * @author ohapon
 *
 */
public class LocalePartInfo implements Comparable, Serializable {

    private static final long serialVersionUID = 3769224135774492714L;

    private String name;
    
    private String displayName;

    public LocalePartInfo(String name, String displayName) {
	if (name == null) {
	    throw new IllegalArgumentException("Name must be not null");
	}
	if (displayName == null) {
	    throw new IllegalArgumentException("Display Name must be not null");
	}
	this.name = name;
	this.displayName = displayName;
    }
    
    public int hashCode() {
        return name.hashCode();
    }
    
    public boolean equals(Object obj) {
        return name.equals(((LocalePartInfo)obj).name);
    }

    public int compareTo(Object o) {
        return name.compareTo(((LocalePartInfo)o).name);
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String toString() {
        if(name.length() == 0) {
            return displayName;
        }
        return name + " - " + displayName;
    }
    
    
    
}
