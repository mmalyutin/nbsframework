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


public class KeyEvent extends TypedEvent {
    
    
    private char keyChar;

    private int keyCode;
   
    private int stateMask;
    
    private boolean doit;

    public KeyEvent(Object source) {
	super(source);
    }

    public char getKeyChar() {
        return keyChar;
    }

    public void setKeyChar(char keyChar) {
        this.keyChar = keyChar;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getStateMask() {
        return stateMask;
    }

    public void setStateMask(int stateMask) {
        this.stateMask = stateMask;
    }

    public boolean isDoit() {
        return doit;
    }

    public void setDoit(boolean doit) {
        this.doit = doit;
    }
    
    public String toString() {
	StringBuffer buf = new StringBuffer();
	buf.append("KeyEvent: [");
	buf.append("keyCode=" + keyCode + ", keyChar='" + keyChar + "'");
	buf.append(", stateMask='" + getStateMaskString(stateMask) + "'");
	buf.append("]");
	return buf.toString();
    }
    
    
}
