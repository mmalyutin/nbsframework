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

package org.plazmaforge.framework.uwt.demo.tabs;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.uwt.layout.FitLayout;
import org.plazmaforge.framework.uwt.widget.panel.Panel;

public class ListTab extends AbstractTab {

    public ListTab() {
    }

    @Override
    protected void createUI() {
	
	setLayout(new FitLayout());
	
	Panel panel = createListPanel();
	add(panel);
    }
    
    
    private Panel createListPanel() {
	Panel panel = new Panel();
	
	panel.setLayout(new FitLayout());
	
	org.plazmaforge.framework.uwt.widget.ListBox<String> list = new org.plazmaforge.framework.uwt.widget.ListBox<String>();
	list.setToolTip("List tooltip");
	
	List<String> items = new ArrayList<String>();
	items.add("Item 1");
	items.add("Item 2");
	items.add("Item 3");
	items.add("Item 4");
	items.add("Item 5");
	
	list.setItems(items);
	panel.add(list);
	
	return panel;
    }

}
