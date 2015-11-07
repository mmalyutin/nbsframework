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


import org.plazmaforge.framework.uwt.demo.DemoDialog;
import org.plazmaforge.framework.uwt.demo.DemoFrame;
import org.plazmaforge.framework.uwt.demo.DemoUtils;
import org.plazmaforge.framework.uwt.demo.DemoWindow;
import org.plazmaforge.framework.uwt.demo.DemoWizardDialog;
import org.plazmaforge.framework.uwt.event.SelectionAdapter;
import org.plazmaforge.framework.uwt.event.SelectionEvent;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.CallbackHandler;
import org.plazmaforge.framework.uwt.widget.CallbackResult;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.MessageBox;
import org.plazmaforge.framework.uwt.widget.panel.Panel;

public class WindowTab extends AbstractTab {

    private int windowIndex;
    
    private int frameIndex;
    
    
    public WindowTab() {
    }

    @Override
    protected void createUI() {
	setLayout(new GridLayout());
	
	Composite panel = createButtonPanel();
	add(panel);
	
    }
    
    
    private Panel createButtonPanel() {
	Panel panel = new Panel();
	
	panel.setLayout(new GridLayout(4));
	

	// WINDOW
	Button windowButton = new Button();
	windowButton.setText("Window");
	windowButton.setToolTip("Window");
	windowButton.addSelectionListener(new SelectionAdapter() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		openWindow();
	    }

	});
	panel.add(windowButton);
	

	// FRAME
	Button frameButton = new Button();
	frameButton.setText("Frame");
	frameButton.setToolTip("Frame");
	frameButton.addSelectionListener(new SelectionAdapter() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		openFrame();
	    }

	});
	panel.add(frameButton);
	
	
	// DIALOG
	Button dialogButton = new Button();
	dialogButton.setText("Dialog");
	dialogButton.setToolTip("Dialog");
	dialogButton.addSelectionListener(new SelectionAdapter() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		openDialog();
	    }

	});
	panel.add(dialogButton);

	
	// WIZARD
	Button wizardButton = new Button();
	wizardButton.setText("Wizard");
	wizardButton.setToolTip("Wizard");
	wizardButton.addSelectionListener(new SelectionAdapter() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		openWiazard();
	    }

	});	
	panel.add(wizardButton);

	
	
	
	return panel;
    }
    
    private void openWindow() {
	DemoWindow window = new DemoWindow();
	window.setIndex(++windowIndex);
	window.open();
    }

    private void openFrame() {
	DemoFrame frame = new DemoFrame();
	frame.setIndex(++frameIndex);
	frame.open();
    }
    
    private void openDialog() {
	DemoDialog dialog = new DemoDialog();
	dialog.open(new CallbackHandler() {
	    
	    @Override
	    public void handle(CallbackResult result) {
		//TODO
		//if (!isOK(result)) {
		//    return;
		//}
		//do it
	    }
	});	
    }
    
    
    private void openWiazard() {
	final DemoWizardDialog wizard = new DemoWizardDialog();
	wizard.open(new CallbackHandler() {
	    
	    @Override
	    public void handle(CallbackResult result) {
		if (!result.isConfirm()) {
		    return;
		}
		

		StringBuffer buf = new StringBuffer();
		DemoUtils.addValueInfo(buf, "First Name", wizard.getFirstName());
		DemoUtils.addValueInfo(buf, "Middle Name", wizard.getMiddleName());
		DemoUtils.addValueInfo(buf, "Last Name", wizard.getLastName());
		DemoUtils.addValueInfo(buf, "Job Title", wizard.getJobTitle());
		DemoUtils.addValueInfo(buf, "Company", wizard.getCompany());
		buf.append("\n");
		DemoUtils.addValueInfo(buf, "Phone", wizard.getPhone());
		DemoUtils.addValueInfo(buf, "Email", wizard.getEmail());
		DemoUtils.addValueInfo(buf, "Address", wizard.getAddress());
		
		result.setValue(buf.toString());

		MessageBox.information("Get Values", result.getValue().toString());
	    }
	});	
    }


}
