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

/**
 * 
 */
package org.plazmaforge.framework.uwt.builder.widget;

import java.util.List;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.widget.ComboBox;
import org.plazmaforge.framework.uwt.widget.IField;

/**
 * @author ohapon
 *
 */
public class ComboBoxBuilder extends AbstractViewerFieldBuilder {

    @Override
    protected IField createField(IData data) {
	return new ComboBox();
    }

    @Override
    protected void populate(IData data, UIElement element) {
	super.populate(data, element);
	ComboBox comboBox = (ComboBox) element;
	String property = getString(data, ComboBox.PROPERTY_DISPLAY_PROPERTY);
	if (property != null) {
	    comboBox.setDisplayProperty(property);
	}
	Integer selectionIndex = getInteger(data, ComboBox.PROPERTY_SELECTION_INDEX);
	if  (selectionIndex != null) {
	    comboBox.setSelectionIndex(selectionIndex);
	}
    }   
    
    protected void populateItems(IData data, UIElement element) {
	ComboBox comboBox = (ComboBox) element;
	List<String> items = getItems(String.class, data, element);
	comboBox.setItems(items);
    }


}
