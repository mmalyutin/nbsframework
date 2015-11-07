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

import java.util.Date;

import org.plazmaforge.framework.uwt.dialog.Dialog;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.ComboBox;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.DateField;
import org.plazmaforge.framework.uwt.widget.Label;

public class DemoDialog extends Dialog {

    
    
    private DateField startDateField;
    private DateField endDateField;
    private ComboBox<String> currencyField;
	
	
    @Override
    protected void configure() {
	super.configure();
	setTitle("Dialog");
	setResizable(false);
    }
    
    
    @Override
    protected void buildContent(Composite parent) {
	
	GridLayout layout = new GridLayout(2);
	parent.setLayout(layout);
	
	Label label = new Label("Start Date");
	startDateField = new DateField();
	parent.add(label);
	parent.add(startDateField);
	
	label = new Label("End Date");
	endDateField = new DateField();
	parent.add(label);
	parent.add(endDateField);

	label = new Label("Currency");
	currencyField = new ComboBox<String>();
	parent.add(label);
	parent.add(currencyField);

    }
    
    @Override
    protected void load() {
	currencyField.setItems(new String[] {"USD", "EUR", "CAD", "AUD"});
	currencyField.setSelectionFirst();
	startDateField.setValue(new Date());
	endDateField.setValue(new Date());
    }

}
