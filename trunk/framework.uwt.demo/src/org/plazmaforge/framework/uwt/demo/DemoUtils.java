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

package org.plazmaforge.framework.uwt.demo;

import org.plazmaforge.framework.core.data.ValuePresenter;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.CheckBox;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Label;

public class DemoUtils {

    public static void setToolTip(Label label) {
	setToolTip(label, label.getText());
    }

    public static void setToolTip(Button button) {
	setToolTip(button, button.getText());
    }

    public static void setToolTip(CheckBox checkbox) {
	setToolTip(checkbox, checkbox.getText());
    }

    public static void setToolTip(Control control, String tooltip) {
	if (control == null) {
	    return;
	}
	tooltip = tooltip == null ? "Tooltip" : (tooltip.trim() + " tooltip");
	control.setToolTip(tooltip);
    }
    
    public static void addValueInfo(StringBuffer buf, String type, Object value) {
	addValueInfo(buf, type, value, null);
    }

    public static void addValueInfo(StringBuffer buf, String type, Object value, ValuePresenter presenter) {
	String strValue = presenter != null ? presenter.toString(value) : ("" + value);
	buf.append(type + " = '" + strValue + "'");
	buf.append("\n");
    }
    
    
}
