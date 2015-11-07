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

public class MouseEvent extends TypedEvent {

    private int button;
 
    private int stateMask;
    
    private int x;
    
    private int y;
    
    private int count;

    public MouseEvent(Object source) {
	super(source);
    }

    public int getButton() {
        return button;
    }

    public void setButton(int button) {
        this.button = button;
    }

    public int getStateMask() {
        return stateMask;
    }

    public void setStateMask(int stateMask) {
        this.stateMask = stateMask;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
    public String toString() {
	StringBuffer buf = new StringBuffer();
	buf.append("MouseEvent: [");
	buf.append("button=" + button + ", x=" + x + ", y=" + y +", count=" + count);
	buf.append(", stateMask='" + getStateMaskString(stateMask) + "'");
	buf.append("]");
	return buf.toString();
    }    

}
