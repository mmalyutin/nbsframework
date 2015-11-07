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

package org.plazmaforge.framework.uwt.view;


import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.IdentifierStore;
import org.plazmaforge.framework.uwt.action.AbstractAction;
import org.plazmaforge.framework.uwt.action.Action;
import org.plazmaforge.framework.uwt.action.ActionHelper;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Presenter;
import org.plazmaforge.framework.uwt.widget.Frame;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.menu.Menu;
import org.plazmaforge.framework.uwt.widget.menu.MenuBar;
import org.plazmaforge.framework.uwt.widget.tool.ButtonBar;
import org.plazmaforge.framework.uwt.widget.tool.ToolBar;


/**
 * Base View
 * 
 * @author ohapon
 *
 */
public abstract class View extends Presenter implements IView {

    
    /**
     * Main control (table/tree/panel)
     */
    private Control control;
    
    /**
     * Store of actions
     */
    private IdentifierStore<Action> actionStore = new IdentifierStore<Action>(); 
    

    

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
	checkControl(control);
        this.control = control;
    }
    
    protected void checkControl(Control control) {
	
    }
    
    public boolean hasControl() {
 	return control != null;
    }
    
    @Override
    public Composite getContainer() {
	return this;
    }
    
    
    
    public IView getParentView() {
	Widget parent = getParent();
	if (parent instanceof IView) { 
	    return (IView) parent;
	}
	return null;
    }

    public Frame getParentFrame() {
	Widget parent = getParent();
	if (parent instanceof Frame) { 
	    return (Frame) parent;
	}
	return null;
    }

    public void setMenuBar(MenuBar menuBar) {
	
	if (isSupportMenuBar() && isEmulateMenuBar()) {
	    super.setMenuBar(menuBar);
	    return;
	}
	
	Frame frame = getParentFrame();
	if (frame != null) {
	    frame.setMenuBar(menuBar);
	}
	
	//super.setMenuBar(menuBar);
    }

    public MenuBar getMenuBar() {

	if (isSupportMenuBar() && isEmulateMenuBar()) {
	    return super.getMenuBar();
	}

	Frame frame = getParentFrame();
	if (frame != null) {
	    return frame.getMenuBar();
	}
	
	return null; //super.getMenuBar();
	
    }

    public void canNotify(String message, final Callback<Boolean> callback) {
	if (callback == null) {
	    return;
	}
	// Always do it by default
	callback.onSuccess(true);
    }

    public void notify(String message) {
	//do nothing
    }
 
    public void executeAction(String action) {
	doAction(action);
    }

    protected void doAction(String action) {
	//do nothing
    }
    
    public Action getActionById(String id) {
	return actionStore.findById(id);
    }
    
    public Action getActionByName(String name) {
	return actionStore.findByName(name);
    }
    
    public Action getAction(String name) {
	return getActionByName(name);
    }

    /**
     * Register global action
     * @param action
     */
    public void registerAction(Action action) {
	actionStore.add(action);
    }

    /**
     * Add action to Menu
     * @param menu
     * @param action
     * @return
     */
    protected boolean addAction(Menu menu, Action action) {
	return ActionHelper.addAction(menu, action);
    }

    /**
     * Add action to ToolBar
     * @param toolBar
     * @param action
     * @return
     */
    protected boolean addAction(ToolBar toolBar, Action action) {
	return ActionHelper.addAction(toolBar, action);
    }

    /**
     * Add action to ButonBar
     * @param buttonBar
     * @param action
     * @return
     */
    protected boolean addAction(ButtonBar buttonBar, Action action) {
	return ActionHelper.addAction(buttonBar, action);
    }
  
  
    /**
     * Create internal action (uses action switcher of the form)
     * @param name
     * @param text
     * @param icon
     * @return
     */
    protected Action createAction(String name, String text, Image icon) {
	text = ActionHelper.normalizeText(text);
	icon = ActionHelper.normalizeIcon(icon);
	if (text == null && icon == null) {
	    return null;
	}
	Action action = new InnerAction();
	action.setName(name);
	if (text != null) {
	    action.setText(text);
	}
	if (icon != null) {
	    action.setIcon(icon);
	}
	return action;
    }
    
    /**
     * Create internal action (uses action switcher of the form)
     * @param name
     * @param text
     * @return
     */
    protected Action createAction(String name, String text) {
	return createAction(name, text, (Image) null);
    }
    
    /**
     * Internal action (uses action switcher of the form) 
     */
    protected class InnerAction extends AbstractAction {

	@Override
	public void execute() {
	    executeAction(getName());
	}
	
    }
    
    protected void handleError(Throwable e) {
	getErrorHandler().handleError(e);
    }
}
