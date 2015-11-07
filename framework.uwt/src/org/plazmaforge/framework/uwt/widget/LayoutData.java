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

package org.plazmaforge.framework.uwt.widget;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.UWT;

public class LayoutData  extends UIObject {


    //////////////////////////////////////////////////////////////////////
    // PROPERTIES
    //////////////////////////////////////////////////////////////////////

    public static final String PROPERTY_HORIZONTAL_ALIGN = "horizontalAlign";

    public static final String PROPERTY_VERTICAL_ALIGN = "verticalAlign";
    
    public static final String PROPERTY_HORIZONTAL_FLEX = "horizontalFlex";
    
    public static final String PROPERTY_VERTICAL_FLEX = "verticalFlex";
    
    public static final String PROPERTY_COLUMN_SPAN = "columnSpan";

    public static final String PROPERTY_ROW_SPAN = "rowSpan";
    
    
    
    private int preferredWidth;
    
    private int preferredHeight;

    
    
    public LayoutData() {
	preferredWidth = UWT.DEFAULT;
	preferredHeight = UWT.DEFAULT;
    }

    public int getPreferredWidth() {
        return preferredWidth;
    }

    public void setPreferredWidth(int preferredWidth) {
        this.preferredWidth = preferredWidth;
    }

    public int getPreferredHeight() {
        return preferredHeight;
    }

    public void setPreferredHeight(int preferredHeight) {
        this.preferredHeight = preferredHeight;
    }
    
    
}
