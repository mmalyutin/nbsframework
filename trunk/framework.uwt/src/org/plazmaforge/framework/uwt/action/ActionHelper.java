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

package org.plazmaforge.framework.uwt.action;

import org.plazmaforge.framework.uwt.event.SelectionEvent;
import org.plazmaforge.framework.uwt.event.SelectionListener;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.util.PropertyChangeEvent;
import org.plazmaforge.framework.uwt.util.PropertyChangeListener;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.menu.Menu;
import org.plazmaforge.framework.uwt.widget.menu.MenuItem;
import org.plazmaforge.framework.uwt.widget.tool.ButtonBar;
import org.plazmaforge.framework.uwt.widget.tool.ToolBar;
import org.plazmaforge.framework.uwt.widget.tool.ToolItem;

/**
 * Action helper
 * 
 * @author ohapon
 *
 */
public class ActionHelper {

    private ActionHelper() {
    }

    /**
     * Add action to Menu  and return true if action was added
     * @param menu
     * @param action
     * @return
     */
    public static boolean addAction(Menu menu, final Action action) {
	if (menu == null || action == null) {
	    return false;
	}
	String text = normalizeText(action.getText());
	Image icon = normalizeIcon(action.getIcon());
	if (text == null && icon == null) {
	    return false;
	}
	MenuItem item = new MenuItem();
	if (text != null) {
	    item.setText(text);
	}
	if (icon != null) {
	    item.setIcon(icon);
	}
	
	setAction(item, action);
	
	menu.addItem(item);
	return true;
    }
    
    /**
     * Add action to ToolBar and return true if action was added
     * @param toolBar
     * @param action
     * @param useText
     * @return
     */
    public static boolean addAction(ToolBar toolBar, final Action action, boolean useText) {
	if (toolBar == null || action == null) {
	    return false;
	}
	String text = normalizeText(action.getText());
	Image icon = normalizeIcon(action.getIcon());
	if (text == null && icon == null) {
	    return false;
	}
	ToolItem item = new ToolItem();
	if (text != null && useText) {
	    item.setText(text);
	}
	if (icon != null) {
	    item.setIcon(icon);
	}
	
	//TODO
	//action.getToolTip();
	String toolTip = text;
	if (toolTip != null) {
	    item.setToolTip(toolTip);
	}
	
	setAction(item, action);
	
	toolBar.addItem(item);
	
	return true;
    }
    
    /**
     * Add action to ToolBar and return true if action was added
     * @param toolBar
     * @param action
     * @return
     */
    public static boolean addAction(ToolBar toolBar, Action action) {
	return addAction(toolBar, action, false);
    }
    
    /**
     * Add action to ButtonBar and return true if action was added
     * @param buttonBar
     * @param action
     * @return
     */
    public static boolean addAction(ButtonBar buttonBar, final Action action) {
	if (buttonBar == null || action == null) {
	    return false;
	}
	String text = normalizeText(action.getText());
	Image icon = normalizeIcon(action.getIcon());
	if (text == null && icon == null) {
	    return false;
	}
	Button button = new Button();
	if (text != null) {
	    button.setText(text);
	}
	if (icon != null) {
	    button.setIcon(icon);
	}
	
	setAction(button, action);
	
	buttonBar.add(button);
	return true;
    }
    
    /**
     * Return true f action is ready to use
     * @param action
     * @return
     */
    public static boolean isReady(Action action) {
	if (action == null) {
	    return false;
	}
	if (isEmptyText(action.getText()) && isEmptyIcon(action.getIcon())) {
	    return false;
	}
	return true;
    }
    
    public static Action createEmptyAction() {
	EmptyAction action = new EmptyAction();
	return action;
    }

    public static Action createEmptyActionById(String id) {
	Action action = createEmptyAction();
	action.setId(id);
	return action;
    }

    public static Action createEmptyActionByName(String name) {
	Action action = createEmptyAction();
	action.setName(name);
	return action;
    }

    public static String normalizeText(String text) {
	if (text == null) {
	    return null;
	}
	text = text.trim();
	return text.isEmpty() ? null : text;
    }

    public static Image normalizeIcon(Image icon) {
	if (icon == null) {
	    return null;
	}
	return icon.isEmpty() ? null : icon;
    }

    protected static boolean isEmptyText(String text) {
	return text == null || normalizeText(text) == null;
    }
    
    protected static boolean isEmptyIcon(Image icon) {
	return icon == null || icon.isEmpty();
    }

    
    private static void addActionListener(final ActionItem actionItem, final Action action) {
	action.addPropertyChangeListener(new PropertyChangeListener() {
	    
	    @Override
	    public void propertyChange(PropertyChangeEvent event) {
		//TODO
		if (Action.PROPERTY_ENABLED.equals(event.getPropertyName())) {
		    boolean enabled = (Boolean) event.getNewValue();
		    actionItem.setEnabled(enabled);
		}
	    }
	});	
    }
    
    public static void setAction(ActionItem actionItem, Action action) {
	setAction(actionItem, action, false);
    }
    
    public static void setAction(final ActionItem actionItem, final Action action, boolean contribute) {

	// If contribute is true then auto populate text and icon
	if (contribute) {
	    String text = normalizeText(action.getText());
	    Image icon = normalizeIcon(action.getIcon());
	    if (text == null && icon == null) {
		//TODO
		return;
	    }
	    if (text != null) {
		actionItem.setText(text);
	    }
	    if (icon != null) {
		actionItem.setIcon(icon);
	    }
	}
	
	actionItem.addSelectionListener(new SelectionListener() {

	    @Override
	    public void select(SelectionEvent event) {
		action.execute();
	    }
	    
	});
	
	addActionListener(actionItem, action);
	
	//TODO: Must store action to actionItem (actionItem.setAction())
    }
}
