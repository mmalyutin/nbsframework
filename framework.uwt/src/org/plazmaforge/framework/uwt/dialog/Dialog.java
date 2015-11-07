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

package org.plazmaforge.framework.uwt.dialog;

import org.plazmaforge.framework.uwt.UIMessages;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;
import org.plazmaforge.framework.uwt.event.SelectionEvent;
import org.plazmaforge.framework.uwt.event.SelectionListener;
import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.CallbackHandler;
import org.plazmaforge.framework.uwt.widget.CallbackResult;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Window;
import org.plazmaforge.framework.uwt.widget.tool.ButtonBar;


/**
 * Base Dialog includes:
 * 
 * - Content
 * - ButtonBar
 * 
 * To handle action use <code>CallbackHandler</code>
 * To populate action result use <code>populateResult(CallbackResult)</code> method.
 * You can set value/values to <code>CallbackResult</code>
 * 
 * 
 * @author ohapon
 *
 */
public class Dialog extends Window implements IDialog {

    
    /**
     * Content container
     */
    private Composite content;
    
    
    /**
     * Button bar (container of buttons)
     */
    private ButtonBar buttonBar;
    

    /**
     * Callback of default (Ok/Finish) action of dialog
     */
    private CallbackHandler handler; 
    
    /**
     * CallbackResult (action, value, values)
     */
    private CallbackResult result;
  
    /**
     * Initialize flag
     */
    private boolean init;

    
    private boolean needFixSize;
    
    private boolean autoHide;

    public Dialog() {
	setAutoHide(true);
	setCenter(true);
	setModal(true);
	setNeedFixSize(true);
    }

    public final void init() {
	
	if (init) {
	    return;
	}
	init = true;
	
	//////////////////////////////
	create();
	/////////////////////////////
	
    }

    public void open() {
	open(null);
    }

    public void open(CallbackHandler handler) {
	this.handler = handler;
	init();
	load();
	super.open();
    }


    public void close() {
	super.close();
    }

    public void dispose() {

    }

    protected void load() {
	/// do nothing: use to load data
    }

    protected void configureSize() {
	if (isNeedFixSize()) {
	    fixSize();
	}
    }
    
    protected void fixSize() {
	int width = getWidth();
	int height = getHeight();
	if (width <= 0) {
	    width = DEFAULT_DIALOG_WIDTH;
	} else {
	    width = width < MIN_DIALOG_WIDTH ? MIN_DIALOG_WIDTH : width; 
	}
	if (height <= 0) {
	    height = DEFAULT_DIALOG_HEIGHT;
	} else {
	    height = height < MIN_DIALOG_HEIGHT ? MIN_DIALOG_HEIGHT : height;
	}
	setSize(width, height);
    }

    protected void createUI() {

	GridLayout layout = new GridLayout();
	layout.resetMargin();
	layout.resetSpacing();
	setLayout(layout);

	// CONTENT
	content = createContent();
	buildContent(content);
	content.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.FILL, true, true));
	add(content);

	// BUTTON BAR (OPTIONAL)
	buttonBar = createButtonBar();
	buildButtonBar(buttonBar);
	buttonBar.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.MIDDLE, true, false));
	add(buttonBar);

    }
    
    protected Composite createContent() {
	Composite content = new Composite();
	GridLayout layout = new GridLayout();
	layout.resetMargin();
	content.setLayout(layout);
	return content;
    }
    
    protected ButtonBar createButtonBar() {
        return ButtonBar.createDailogButtonBar();
    }


    protected void buildContent(Composite parent) {
	// do nothing: use to build content
    }

    protected void buildButtonBar(ButtonBar buttonBar ) {
	buttonBar.addButton(createButton(OK_ACTION, UIMessages.OK));
	buttonBar.addButton(createButton(CANCEL_ACTION, UIMessages.CANCEL));
    }
    
    protected Button createButton(String name, String text) {
	final Button button = new Button();
	button.setName(name);
	button.setText(text);
	button.addSelectionListener(new SelectionListener( ){

	    @Override
	    public void select(SelectionEvent event) {
		doButtonAction(button.getName());
	    }
	    
	});
	return button;
    }
    
    protected void doButtonAction(String action) {
	if (OK_ACTION.equals(action) ) {
	    doOkAction();
	    return;
	} else if (CANCEL_ACTION.equals(action) ){
	    doCancelAction();
	    return;
	}
    }
    
    protected void doOkAction() {
	doResult(OK_ACTION);
	doHandler();
	if (autoHide) {
	    close();
	}
    }

    protected void doCancelAction() {
	doResult(CANCEL_ACTION);
	doHandler();
	if (autoHide) {
	    close();
	}
    }

    public boolean isAutoHide() {
        return autoHide;
    }

    public void setAutoHide(boolean autoHide) {
        this.autoHide = autoHide;
    }

    protected void doHandler() {
	if (handler == null) {
	    return;
	}
	handler.handle(result);
    }

    protected final void doResult(String action) {
	result = new CallbackResult(action);
	processResult(result);
    }
    
    protected void processResult(CallbackResult result) {
	// do nothing: use to process result
    }
    
    public CallbackHandler getHandler() {
        return handler;
    }

    public void setHandler(CallbackHandler handler) {
        this.handler = handler;
    }

    public boolean isNeedFixSize() {
        return needFixSize;
    }

    public void setNeedFixSize(boolean needFixSize) {
        this.needFixSize = needFixSize;
    }

    public Composite getContent() {
	checkCreate();
        return content;
    }

    public ButtonBar getButtonBar() {
	checkCreate();
        return buttonBar;
    }

    /**
     * Return button from <code>ButtonBar</code> by name
     * @param name
     * @return
     */
    public Button getButtonByName(String name) {
	return buttonBar == null ? null : buttonBar.getButtonByName(name); 
    }
    
   
    
}
