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

import org.plazmaforge.framework.uwt.event.SelectionAdapter;
import org.plazmaforge.framework.uwt.event.SelectionEvent;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.CallbackHandler;
import org.plazmaforge.framework.uwt.widget.CallbackResult;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.MessageBox;
import org.plazmaforge.framework.uwt.widget.panel.Panel;

public class MessageTab extends AbstractTab {

    public MessageTab() {
    }

    @Override
    protected void createUI() {
	//VerticalLayout layout = new VerticalLayout();
	setLayout(new GridLayout());
	
	Composite panel = createTextButtonPanel();
	add(panel);
	
    }
    
    
    private Panel createTextButtonPanel() {
	Panel panel = new Panel();
	
	panel.setLayout(new GridLayout(6));
	
	// ERROR
	Button errorButton = new Button();
	errorButton.setText("Error");
	errorButton.setToolTip("Error Message");
	errorButton.addSelectionListener(new SelectionAdapter() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		MessageBox.error("Error", "Error message");
	    }

	});
	panel.add(errorButton);

	
	// INFORMATION
	Button informationButton = new Button();
	informationButton.setText("Information");
	informationButton.setToolTip("Information Message");
	informationButton.addSelectionListener(new SelectionAdapter() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		MessageBox.information("Information", "Information message");
	    }

	});	
	panel.add(informationButton);

	
	// WARNING
	Button warningButton = new Button();
	warningButton.setText("Warning");
	warningButton.setToolTip("Warning Message");
	warningButton.addSelectionListener(new SelectionAdapter() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		MessageBox.warning("Warning", "Warning message");
	    }

	});		
	panel.add(warningButton);

	
	// QUESTION
	Button questionButton = new Button();
	questionButton.setText("Question");
	questionButton.setToolTip("Question Message");
	questionButton.addSelectionListener(new SelectionAdapter() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		MessageBox.question("Question", "Question message", createCallbackHandler(false));
	    }

	});		
	panel.add(questionButton);

	// CONFIRM
	Button confirmButton = new Button();
	confirmButton.setText("Confirm");
	confirmButton.setToolTip("Confirm Message");
	confirmButton.addSelectionListener(new SelectionAdapter() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		MessageBox.confirm("Confirm", "Confirm message", createCallbackHandler(false));
	    }

	});		
	panel.add(confirmButton);

	
	// PROMPT
	Button promptButton = new Button();
	promptButton.setText("Prompt");
	promptButton.setToolTip("Prompt Message");
	promptButton.addSelectionListener(new SelectionAdapter() {

	    @Override
	    public void select(SelectionEvent event) {
		MessageBox.prompt("Prompt", "Prompt message", createCallbackHandler(true));
	    }

	});
	panel.add(promptButton);
	
	
	return panel;
    }
    
    private CallbackHandler createCallbackHandler(final boolean prompt) {
	CallbackHandler handler = new CallbackHandler() {
	    public void handle(CallbackResult result) {
		MessageBox.information("Result", "You selected action \"" + result.getAction() + "\"" + (prompt && result.isConfirm() ? (" and value \""  + result.getValue() + "\"") : ""));
	    }
	};
	return handler;
    }
    
   
}
