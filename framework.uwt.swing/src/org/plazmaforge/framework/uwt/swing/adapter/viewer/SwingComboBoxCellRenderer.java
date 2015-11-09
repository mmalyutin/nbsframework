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

package org.plazmaforge.framework.uwt.swing.adapter.viewer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import org.plazmaforge.framework.uwt.util.UWTUtils;
import org.plazmaforge.framework.uwt.widget.ComboBox;

public class SwingComboBoxCellRenderer extends DefaultListCellRenderer {

    private ComboBox comboBox;
    
    
    public SwingComboBoxCellRenderer(ComboBox comboBox) {
	super();
	this.comboBox = comboBox;
    }


    @Override
    public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
	Object value = UWTUtils.getSimpleValue(arg1, comboBox.getDisplayProperty(), comboBox.getPropertyProvider(), comboBox);
	String text = value == null ? "" : value.toString(); 
	return super.getListCellRendererComponent(arg0, text, arg2, arg3, arg4);
    }

}
