
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

import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;
import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.Label;
import org.plazmaforge.framework.uwt.widget.panel.Panel;

public class LabelTab extends AbstractTab {

    public LabelTab() {
    }

    @Override
    protected void createUI() {
	//VerticalLayout layout = new VerticalLayout();
	setLayout(new GridLayout());
	
	Panel panel = createTextLabelPanel();
	add(panel);
    }
    
    
    private Panel createTextLabelPanel() {
	Panel panel = new Panel();
	panel.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.FILL, true, true));
	
	panel.setLayout(new GridLayout(3));
	
	Label label1 = new Label();
	label1.setText("Label 1");
	setToolTip(label1);
	label1.setLayoutData(new GridData());
	panel.add(label1);
	
	Label label2 = new Label();
	label2.setText("Label 2");
	setToolTip(label2);
	panel.add(label2);

	
	Label label3 = new Label();
	label3.setText("Label 3");
	setToolTip(label3);
	panel.add(label3);

	return panel;
    }

}
