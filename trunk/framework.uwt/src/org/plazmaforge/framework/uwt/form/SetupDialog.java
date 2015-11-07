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

package org.plazmaforge.framework.uwt.form;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.uwt.dialog.Dialog;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.CheckBox;
import org.plazmaforge.framework.uwt.widget.Composite;

public class SetupDialog extends Dialog {

    
    private List<DisplayField> displayFields;
    
    private List<SetupControl> controls;
    
    @Override
    protected void configure() {
	super.configure();
	setTitle("Setup Dialog");
    }
    
    @Override
    protected void buildContent(Composite parent) {
	GridLayout layout = new GridLayout(1); 
	parent.setLayout(layout);
	List<DisplayField> displayFields = getDisplayFields();
	controls = new ArrayList<SetupControl>();
	for (DisplayField displayField : displayFields) {
	    SetupControl control = createSetupControl(displayField);
	    controls.add(control);
	    parent.add(control.checkBox);
	}
    }

    public List<DisplayField> getDisplayFields() {
	if (displayFields == null) {
	    displayFields = new ArrayList<DisplayField>();
	}
        return displayFields;
    }

    public void setDisplayFields(List<DisplayField> displayFields) {
        this.displayFields = displayFields;
    }


    protected SetupControl createSetupControl(DisplayField displayField) {
	SetupControl filterControl = new SetupControl();
	filterControl.filed = displayField;
	filterControl.checkBox = new CheckBox();
	filterControl.checkBox.setText(displayField.getLabel());
	return filterControl;
    }
    
    
    class SetupControl {
	
	DisplayField filed;
	
	CheckBox checkBox; 
	
	
    }
}
