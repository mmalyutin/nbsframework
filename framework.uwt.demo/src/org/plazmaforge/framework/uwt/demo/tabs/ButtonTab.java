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

import org.plazmaforge.framework.uwt.event.MouseEvent;
import org.plazmaforge.framework.uwt.event.MouseListener;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.RadioButton;
import org.plazmaforge.framework.uwt.widget.RadioGroup;
import org.plazmaforge.framework.uwt.widget.ToggleButton;
import org.plazmaforge.framework.uwt.widget.panel.Panel;

public class ButtonTab extends AbstractTab {

    public ButtonTab() {
    }

    protected void createUI() {
	//VerticalLayout layout = new VerticalLayout();
	setLayout(new GridLayout());
	
	Composite panel = createTextButtonPanel();
	//panel .setBackground(Color.RED);
	//panel.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.MIDDLE, true, false));
	add(panel);
	
	panel = createToggleButtonPanel();
	//panel.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.MIDDLE, true, false));
	add(panel);

	panel = createGroupToggleButtonPanel();
	//panel.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.MIDDLE, true, false));
	add(panel);

	panel = createRadioButtonPanel();
	//panel.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.MIDDLE, true, false));
	add(panel);

    }
    
    
    private Panel createTextButtonPanel() {
	Panel panel = new Panel();
	panel.setToolTip("Push buttons");
	
	GridLayout layout  = new GridLayout(3);
	//layout.setMarginBottom(20);
	panel.setLayout(layout);
	
	Button button1 = new Button();
	button1.setText("Button 1");
	button1.addMouseListener(new MouseListener( ){

	    @Override
	    public void mouseClick(MouseEvent e) {
		System.out.println("MouseClick " + e);
		
	    }

	    @Override
	    public void mouseDoubleClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	    }

	    @Override
	    public void mouseDown(MouseEvent e) {
		// TODO Auto-generated method stub
		
	    }

	    @Override
	    public void mouseUp(MouseEvent e) {
		// TODO Auto-generated method stub
		
	    }
	    
	});
	
	setToolTip(button1);
	panel.add(button1);
	
	Button button2 = new Button();
	button2.setText("Button 2");
	setToolTip(button2);
	panel.add(button2);

	
	Button button3 = new Button();
	button3.setText("Button 3");
	setToolTip(button3);
	panel.add(button3);

	return panel;
    }
    
    
    private Panel createToggleButtonPanel() {
	Panel panel = new Panel();
	panel.setToolTip("Toggle buttons");
	
	panel.setLayout(new GridLayout(3));
	
	ToggleButton button1 = new ToggleButton();
	button1.setText("Toggle 1");
	setToolTip(button1);
	panel.add(button1);
	
	ToggleButton button2 = new ToggleButton();
	button2.setText("Toggle 2");
	setToolTip(button2);
	panel.add(button2);

	
	ToggleButton button3 = new ToggleButton();
	button3.setText("Toggle 3");
	setToolTip(button3);
	panel.add(button3);

	return panel;
    }

    private Panel createGroupToggleButtonPanel() {
	Panel panel = new Panel();
	panel.setToolTip("Toggle Group buttons");
	
	panel.setLayout(new GridLayout(3));
	
	ToggleButton button1 = new ToggleButton();
	button1.setText("Channel 1");
	setToolTip(button1);
	button1.setGroup("group");
	panel.add(button1);
	
	ToggleButton button2 = new ToggleButton();
	button2.setText("Channel 2");
	setToolTip(button2);
	button2.setGroup("group");
	panel.add(button2);

	
	ToggleButton button3 = new ToggleButton();
	button3.setText("Channel 3");
	setToolTip(button3);
	button3.setGroup("group");
	panel.add(button3);

	return panel;
    }

    private Composite createRadioButtonPanel() {
	RadioGroup panel = new RadioGroup();
	panel.setToolTip("Radio Group buttons");
	
	RadioButton button1 = new RadioButton();
	button1.setText("Radio 1");
	setToolTip(button1);
	panel.add(button1);
	
	RadioButton button2 = new RadioButton();
	button2.setText("Radio 2");
	setToolTip(button2);
	panel.add(button2);

	
	RadioButton button3 = new RadioButton();
	button3.setText("Radio 3");
	setToolTip(button3);
	panel.add(button3);

	return panel;
    }
    
   
}
