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
import org.plazmaforge.framework.uwt.widget.Style.Orientation;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;
import org.plazmaforge.framework.uwt.layout.BoxLayout;
import org.plazmaforge.framework.uwt.layout.FitLayout;
import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.layout.HorizontalLayout;
import org.plazmaforge.framework.uwt.layout.VerticalLayout;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.panel.Panel;
import org.plazmaforge.framework.uwt.widget.panel.TabItem;
import org.plazmaforge.framework.uwt.widget.panel.TabPanel;

public class LayoutTab extends AbstractTab {

    public LayoutTab() {
    }

    @Override
    protected void createUI() {
	setLayout(new FitLayout());
	
	TabPanel tabPanel = new TabPanel();
	
	addTabItem(tabPanel, "Box Layout", createBoxLayoutPanel());
	addTabItem(tabPanel, "Horizontal Layout", createHorizontalLayoutPanel());
	addTabItem(tabPanel, "Vertical Layout", createVerticalLayoutPanel());
	addTabItem(tabPanel, "Fit Layout", createFitLayoutPanel());
	addTabItem(tabPanel, "Grid Layout", createGridLayoutPanel());
	
	add(tabPanel);
	
    }

    private void addTabItem(TabPanel tabPanel, String title, Panel panel) {
	TabItem tabItem = new TabItem(title);
	tabItem.setLayout(new FitLayout());
	tabItem.add(panel);
	tabPanel.add(tabItem);
    }
    
    private Panel createBoxLayoutPanel() {
	Panel panel = new Panel();
	
	GridLayout layout  = new GridLayout(2);
	panel.setLayout(layout);
	
	// Add horizontal panel
	Panel hPanel = new Panel();
	hPanel.setLayout(new BoxLayout(Orientation.HORIZONTAL));
	
	Button buttonH1 = new Button();
	buttonH1.setText("Button H1");
	hPanel.add(buttonH1);
	
	Button buttonH2 = new Button();
	buttonH2.setText("Button H2");
	hPanel.add(buttonH2);

	
	Button buttonH3 = new Button();
	buttonH3.setText("Button H3");
	hPanel.add(buttonH3);
	
	panel.add(hPanel);

	
	// Add vertical panel
	Panel vPanel = new Panel();
	vPanel.setLayout(new BoxLayout(Orientation.VERTICAL));
	
	Button buttonV1 = new Button();
	buttonV1.setText("Button V1");
	vPanel.add(buttonV1);
	
	Button buttonV2 = new Button();
	buttonV2.setText("Button V2");
	vPanel.add(buttonV2);

	
	Button buttonV3 = new Button();
	buttonV3.setText("Button H3");
	vPanel.add(buttonV3);
	
	panel.add(vPanel);

	return panel;
    }
    
    private Panel createHorizontalLayoutPanel() {
	Panel panel = new Panel();
	
	panel.setLayout(new HorizontalLayout());
	
	Button button1 = new Button();
	button1.setText("Button H1");
	panel.add(button1);
	
	Button button2 = new Button();
	button2.setText("Button H2");
	panel.add(button2);

	
	Button button3 = new Button();
	button3.setText("Button H3");
	panel.add(button3);

	return panel;
    }
    
    private Panel createVerticalLayoutPanel() {
	Panel panel = new Panel();
	
	panel.setLayout(new VerticalLayout());
	
	Button button1 = new Button();
	button1.setText("Button V1");
	panel.add(button1);
	
	Button button2 = new Button();
	button2.setText("Button V2");
	panel.add(button2);

	
	Button button3 = new Button();
	button3.setText("Button V3");
	panel.add(button3);

	return panel;
    }
   
    private Panel createFitLayoutPanel() {
	Panel panel = new Panel();
	
	panel.setLayout(new FitLayout());
	
	Button button1 = new Button();
	button1.setText("Button");
	panel.add(button1);
	
	return panel;
    }

    private Panel createGridLayoutPanel() {
	Panel panel = new Panel();
	
	panel.setLayout(new GridLayout(4));
	
	// row 1
	Button button1 = new Button();
	button1.setText("Button 1");
	panel.add(button1);
	
	Button button2 = new Button();
	button2.setText("Button 2");
	panel.add(button2);

	Button button3 = new Button();
	button3.setText("Button 3");
	panel.add(button3);

	Button button4 = new Button();
	button4.setText("Button 4");
	panel.add(button4);

	// row 2
	Button button5 = new Button();
	button5.setLayoutData(new GridData(2, 1, HorizontalAlign.FILL, VerticalAlign.MIDDLE));
	button5.setText("Button 5");
	panel.add(button5);

	Button button6 = new Button();
	button6.setText("Button 6");
	panel.add(button6);

	Button button7 = new Button();
	button7.setText("Button 7");
	panel.add(button7);

	// row 3
	Button button8 = new Button();
	button8.setLayoutData(new GridData(4, 1, HorizontalAlign.FILL, VerticalAlign.MIDDLE));
	button8.setText("Button 8");
	panel.add(button8);

	// row 4
	Button button9 = new Button();
	button9.setLayoutData(new GridData(1, 2, HorizontalAlign.CENTER, VerticalAlign.FILL));
	button9.setText("Button 9");
	panel.add(button9);

	Button button10 = new Button();
	button10.setText("Button 10");
	panel.add(button10);
	
	Button button11 = new Button();
	button11.setLayoutData(new GridData(2, 2, HorizontalAlign.FILL, VerticalAlign.FILL, false, /*true*/ false));
	button11.setText("Button 11");
	panel.add(button11);

	// row 5
	Button button12 = new Button();
	button12.setText("Button 12");
	panel.add(button12);
	
	return panel;
    }
}
