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

import org.plazmaforge.framework.uwt.widget.Widget;

public class TypedEvent extends UWTEventObject {


    /* Modifier constants */

    /**
     * This flag indicates that the Shift key was down when the event
     * occurred.
     */
    public static final int SHIFT_MASK		= 1 << 0;

    /**
     * This flag indicates that the Control key was down when the event
     * occurred.
     */
    public static final int CTRL_MASK		= 1 << 1;

    /**
     * This flag indicates that the Meta key was down when the event
     * occurred. For mouse events, this flag indicates that the right
     * button was pressed or released.
     */
    public static final int META_MASK		= 1 << 2;

    /**
     * This flag indicates that the Alt key was down when
     * the event occurred. For mouse events, this flag indicates that the
     * middle mouse button was pressed or released.
     */
    public static final int ALT_MASK		= 1 << 3;
    
    
    private Widget widget;

    private int time;

    private Object data;

    public TypedEvent(Object source) {
	super(source);
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    protected String getStateMaskString(int stateMask) {
	StringBuffer buf = new StringBuffer();
	boolean mask = false;
	if ((stateMask & META_MASK) != 0) {
            buf.append("Meta");
            mask = true;
        }
        if ((stateMask & CTRL_MASK) != 0) {
            if (mask) buf.append(", ");
            buf.append("Ctrl");
            mask = true;
        }
        if ((stateMask & ALT_MASK) != 0) {
            if (mask) buf.append(", ");
            buf.append("Alt");
            mask = true;
        }
        if ((stateMask & SHIFT_MASK) != 0) {
            if (mask) buf.append(", ");
            buf.append("Shift");
        }
        return buf.toString();
    }


}
