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

package org.plazmaforge.framework.uwt.wizard;

import org.plazmaforge.framework.uwt.UIMessages;
import org.plazmaforge.framework.uwt.dialog.Dialog;
import org.plazmaforge.framework.uwt.layout.FitLayout;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.tool.ButtonBar;

public class WizardDialog extends Dialog {


    
    private Wizard wizard;
    
    private boolean canFinish;
    
    private boolean finishOnLast;
    
    
    public WizardDialog() {
	super();
	wizard = new Wizard();
	canFinish = true;
	finishOnLast = true;
    }


    public IWizard getWizard() {
        return wizard;
    }


    @Override
    protected void configure() {
	super.configure();
	setTitle("Wizard");
	setWidth(400);
	//setPack(true);
    }
    
    
    @Override
    protected void buildContent(Composite parent) {

	FitLayout layout = new FitLayout();
	parent.setLayout(layout);
	parent.add(wizard);
    }
    
    
    protected void buildButtonBar(ButtonBar buttonBar ) {
  	buttonBar.addButton(createButton(BACK_ACTION, UIMessages.WIZARD_BACK));
  	buttonBar.addButton(createButton(NEXT_ACTION, UIMessages.WIZARD_NEXT));
  	buttonBar.addButton(createButton(OK_ACTION, UIMessages.FINISH));
  	buttonBar.addButton(createButton(CANCEL_ACTION, UIMessages.CANCEL));
    }
    
    @Override
    protected void createUI() {
	super.createUI();
	updateState();
	
    }
    
    protected void doButtonAction(String action) {
	if (BACK_ACTION.equals(action) ) {
	    doBack();
	    return;
	} else if (NEXT_ACTION.equals(action)) {
	    doNext();
	    return;
	} else if (OK_ACTION.equals(action) ) {
	    doOkAction();
	    return;
	} else if (CANCEL_ACTION.equals(action) ){
	    doCancelAction();
	    return;
	}
    }
    
    protected void doBack() {
	if (wizard.moveBack()) {
	    updateState();
	}
    }

    protected void doNext() {
	if (wizard.moveNext()) {
	    updateState();
	}
    }

    protected void updateState() {
	// Back
	Button button = getButtonByName(BACK_ACTION);
	button.setEnabled(canBack());
	
	// Next
	button = getButtonByName(NEXT_ACTION);
	button.setEnabled(canNext());

	if (finishOnLast) {
	    // Enable finish button only for last page
	    canFinish = wizard.isLastPage();
	}
	
	// Finish
	button = getButtonByName(OK_ACTION);
	button.setEnabled(canFinish());
    }
    
    
    public boolean isFinishOnLast() {
        return finishOnLast;
    }


    public void setFinishOnLast(boolean finishOnLast) {
        this.finishOnLast = finishOnLast;
    }


    protected boolean canBack() {
	return wizard.canBack();
    }
    
    protected boolean canNext() {
	return wizard.canNext();
    }

    protected boolean canFinish() {
	return canFinish;
    }

}
