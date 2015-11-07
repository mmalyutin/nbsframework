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

package org.plazmaforge.framework.uwt.layout;

import org.plazmaforge.framework.uwt.widget.Layout;

public class CardLayout extends Layout {
    
    public static final String METHOD_FIRST = "first";
    public static final String METHOD_PREV = "prev";
    public static final String METHOD_NEXT = "next";
    public static final String METHOD_LAST = "last";
    
    public static final String[] METHODS_NAVIGATION = {METHOD_FIRST, METHOD_PREV, METHOD_NEXT, METHOD_LAST};
    

    public void first() {
	invokeDelegate(this, METHOD_FIRST, null);
    }

    public void prev() {
	invokeDelegate(this, METHOD_PREV, null);
    }

    public void next() {
	invokeDelegate(this, METHOD_NEXT, null);
    }

    public void last() {
	invokeDelegate(this, METHOD_LAST, null);
    }
    
    public boolean isCompatible(Object layoutData) {
	return true;
    }


}
