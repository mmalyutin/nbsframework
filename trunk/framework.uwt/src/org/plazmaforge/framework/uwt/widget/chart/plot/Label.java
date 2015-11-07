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

package org.plazmaforge.framework.uwt.widget.chart.plot;

import org.plazmaforge.framework.uwt.graphics.Size;

public class Label {

    private String text;
    
    private String displayText;

    private Size textSize;
    
    private Size displayTextSize;

    public Label() {
    }

    public Label(String text) {
	this.text = text;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }
    
    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public Size getTextSize() {
        return textSize;
    }

    public void setTextSize(Size textSize) {
        this.textSize = textSize;
    }

    public Size getDisplayTextSize() {
        return displayTextSize;
    }

    public void setDisplayTextSize(Size displayTextSize) {
        this.displayTextSize = displayTextSize;
    }
    
    

}
