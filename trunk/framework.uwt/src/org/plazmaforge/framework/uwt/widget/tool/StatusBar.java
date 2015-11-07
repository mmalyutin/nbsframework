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

package org.plazmaforge.framework.uwt.widget.tool;

import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;
import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Label;

public class StatusBar extends Composite {

    private Label statusLabel;

    private Composite startPanel;
    
    private Composite endPanel;
    
    public StatusBar() {
	super();
	GridLayout layout = new GridLayout(3);
	layout.resetMargin();
	setLayout(layout);
	
	startPanel = new Composite();
	startPanel.setLayoutData(new GridData());
	add(startPanel);
	
	statusLabel = new Label();
	statusLabel.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.MIDDLE, true, false));
	add(statusLabel);
	
	endPanel = new Composite();
	endPanel.setLayoutData(new GridData());
	add(endPanel);
    }
    
    public void setText(String text) {
	if (statusLabel == null) {
	    return;
	}
	statusLabel.setText(text);
    }
    
    
}
