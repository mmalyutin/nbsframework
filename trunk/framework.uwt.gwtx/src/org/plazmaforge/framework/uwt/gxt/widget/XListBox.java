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

package org.plazmaforge.framework.uwt.gxt.widget;

import org.plazmaforge.framework.uwt.gxt.data.Model;

import com.google.gwt.cell.client.Cell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ListView;

/**
 * 
 * @author ohapon
 *
 */
public class XListBox extends ListView<Model, Object> {

    public XListBox(ListStore<Model> store, ValueProvider<Model, Object> valueProvider) {
  	super(store, valueProvider);
    }
    
    public XListBox(ListStore<Model> store, ValueProvider<Model, Object> valueProvider, Cell<Object> cell) {
	super(store, valueProvider, cell);
    }

    public XListBox(ListStore<Model> store, ValueProvider<Model, Object> valueProvider, ListViewAppearance<Model> appearance) {
	super(store, valueProvider, appearance);
    }

    public ValueProvider<Model, Object> getValueProvider() {
	return (ValueProvider<Model, Object>) valueProvider;
    }
    

}
