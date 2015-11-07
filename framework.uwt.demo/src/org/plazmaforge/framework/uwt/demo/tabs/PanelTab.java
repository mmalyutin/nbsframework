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



import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.Label;
import org.plazmaforge.framework.uwt.widget.panel.CollapsePanel;
import org.plazmaforge.framework.uwt.widget.panel.GroupPanel;
import org.plazmaforge.framework.uwt.widget.panel.Panel;
import org.plazmaforge.framework.uwt.widget.panel.TitlePanel;

public class PanelTab extends AbstractTab {

    public PanelTab() {
    }

    @Override
    protected void createUI() {
	
	GridLayout layout = new GridLayout(4);
	setLayout(layout);
	
	Panel panel = createSimplePanel();
	add(panel);

	panel = createGroupPanel();
	add(panel);

	panel = createTitlePanel();
	add(panel);

	panel = createCollapsePanel();
	add(panel);

    }

    
    private Panel createSimplePanel() {
	Panel panel = new Panel();
	populatePanel(panel, "Simple Panel");
	//panel.setBackground(Color.GREEN);
	return panel;
    }

    private Panel createGroupPanel() {
	GroupPanel panel = new GroupPanel();
	panel.setTitle("Group");
	panel.setFont(new Font("Courier New", 12));
	populatePanel(panel, "Group Panel");
	//panel.setBackground(Color.BLUE);
	return panel;
    }

    private Panel createTitlePanel() {
	TitlePanel panel = new TitlePanel();
	panel.setTitle("Title");
	populatePanel(panel, "Title Panel");
	//panel.setBackground(Color.RED);
	return panel;
    }

    private Panel createCollapsePanel() {
	CollapsePanel panel = new CollapsePanel();
	panel.setTitle("Collapse");
	panel.setFont(new Font("Courier New", 12));
	populatePanel(panel, "Collapse Panel");
	//panel.setBackground(Color.ORANGE);
	return panel;
    }

    
    /////
    
    private void populatePanel(Panel panel, String text) {
	panel.setToolTip(text + " tooltip");
	GridLayout layout = new GridLayout(2); 
	panel.setLayout(layout);
	Label label = new Label(text);
	label.setToolTip("Label tooltip");
	label.setForeground(Color.MAGENTA);
	label.setFont(new Font("Courier New", 12));
	panel.add(label);
	Button button = new Button("Content");
	button.setToolTip("Button tooltip");
	panel.add(button);
    }

    
}
