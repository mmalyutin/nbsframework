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

package org.plazmaforge.framework.uwt.gwt;

import org.plazmaforge.framework.uwt.UIAdapterFactory;
import org.plazmaforge.framework.uwt.gwt.adapter.GWTButtonAdapter;
import org.plazmaforge.framework.uwt.gwt.adapter.GWTCheckBoxAdapter;
import org.plazmaforge.framework.uwt.gwt.adapter.GWTLabelAdapter;
import org.plazmaforge.framework.uwt.gwt.adapter.GWTShellAdapter;
import org.plazmaforge.framework.uwt.gwt.adapter.GWTTableAdapter;
import org.plazmaforge.framework.uwt.gwt.adapter.GWTTableColumnAdapter;
import org.plazmaforge.framework.uwt.gwt.adapter.GWTTableItemAdapter;
import org.plazmaforge.framework.uwt.gwt.adapter.GWTTextFieldAdapter;

public final class UWT_GWT {

    private static boolean init;
    
    public static void init() {
	if (init) {
	    return;
	}
	init = true;
	initAdapters();
    }
    
    private static void initAdapters() {
	
	//UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.Application.class, new GWTApplicationAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Frame.class, new GWTShellAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Label.class, new GWTLabelAdapter());
	//UIAdapterFactory.addAdapter("org.plazmaforge.framework.uwt.widgets.Label", "org.plazmaforge.framework.uwt.gwt.adapters.GWTLabelAdapter");
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Button.class, new GWTButtonAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.TextField.class, new GWTTextFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.CheckBox.class, new GWTCheckBoxAdapter());
	
	//UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.FillLayout.class, new GWTFillLayoutAdapter());
	//UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.RowLayout.class, new GWTRowLayoutAdapter());

	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.table.Table.class, new GWTTableAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.table.TableColumn.class, new GWTTableColumnAdapter());

    }
}
