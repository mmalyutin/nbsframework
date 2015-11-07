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

package org.plazmaforge.framework.uwt.widget.tool;

import org.plazmaforge.framework.uwt.widget.Style.Orientation;
import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.layout.HorizontalLayout;
import org.plazmaforge.framework.uwt.layout.VerticalLayout;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Label;
import org.plazmaforge.framework.uwt.widget.Layout;
import org.plazmaforge.framework.uwt.widget.Widget;

public class ButtonBar extends Composite {

    public static final int BUTTON_WIDTH = 70;
    
    public enum ButtonAlign {
	LEFT, RIGHT, TOP, BOTTOM;
    }
    
    public enum ButtonWidth {
	AUTO, FIXED, MAX
    }

    
    private Orientation orientation = Orientation.HORIZONTAL;
    
    private ButtonAlign buttonAlign = ButtonAlign.LEFT;
    
    private ButtonWidth buttonWidth = ButtonWidth.FIXED;
    
    
    private boolean useGridLayout = true;
    
    
    public ButtonBar(Orientation orientation, ButtonAlign buttonAlign) {
	initialize(orientation, buttonAlign);
    }


    public ButtonBar() {
	initialize(Orientation.HORIZONTAL, ButtonAlign.LEFT); // By default orientation = HORIZONTAL and button align = LEFT
    }
    
    public ButtonBar(Orientation orientation) {
	initialize(orientation, null);
    }

    private void initialize(Orientation orientation, ButtonAlign buttonAlign) {
	this.orientation = orientation == null ? Orientation.HORIZONTAL : orientation;
	this.buttonAlign = buttonAlign == null ? ButtonAlign.LEFT : buttonAlign;
	Layout layout = createLayout(); 
	super.setLayout(layout);
	createStruts(); // Create struts if need
    }

    /**
     * Create ButtonBar for Dialog
     * @return
     */
    public static ButtonBar createDailogButtonBar() {
	ButtonBar buttonBar = new ButtonBar(Orientation.HORIZONTAL, ButtonBar.ButtonAlign.RIGHT);
	return buttonBar;
    }
    
    public Orientation getOrientation() {
        return orientation;
    }

    public ButtonAlign getButtonAlign() {
        return buttonAlign;
    }

    public ButtonWidth getButtonWidth() {
        return buttonWidth;
    }


    public void setButtonWidth(ButtonWidth buttonWidth) {
        this.buttonWidth = buttonWidth;
    }
    
    private Layout createLayout() {
	return useGridLayout ? createGridLayout() : createBoxLayout();
    }
    
    private Layout createGridLayout() {
	return new GridLayout();
    }

    private Layout createBoxLayout() {
	return Orientation.VERTICAL.equals(orientation) ? new VerticalLayout() : new HorizontalLayout();
    }

    private void createStruts() {
	if (!useGridLayout) {
	    return;
	}
	if ((orientation == Orientation.HORIZONTAL && buttonAlign == ButtonAlign.RIGHT) 
	    || (orientation == Orientation.VERTICAL && buttonAlign == ButtonAlign.BOTTOM)) {
	    Label label = new Label("");
	    GridData layoutData = new GridData();
	    if (orientation == Orientation.HORIZONTAL) {
		layoutData.setHorizontalFlex(true);
	    } else {
		layoutData.setVerticalFlex(true);
	    }
	    label.setLayoutData(layoutData);
	    add(label);
	}
	
    }
    
    public void addButton(Button button) {
	add(button);
    }
    

    public void removeButton(Button button) {
	remove(button);
    }

    public void add(Widget element) {
	super.add(element);
	if (element instanceof Button) {
	    configureButton((Button) element);
	}
	relayout();
    }
    
    public void remove(Widget element) {
	super.remove(element);
	relayout();
    }
    
    public void setLayout(Layout layout) {
	//ignore
    }




    protected void configureButton(Button button) {
	if (button == null) {
	    return;
	}
	if (ButtonWidth.FIXED.equals(buttonWidth)) { 
	    button.setWidth(BUTTON_WIDTH);
	}
    }
    
    protected void relayout() {
	if (!useGridLayout) {
	    return;
	}
	// Vertical bar has only one column count 
	if (orientation == Orientation.VERTICAL) {
	    return;
	}
	// Horizontal bar has column count = children count
	GridLayout layout = getGridLayout();
	int count = getChildrenCount();
	layout.setColumnCount(count);
    }
    
    protected GridLayout getGridLayout() {
	return (GridLayout) getLayout();
    }
    
    public Button getButtonByName(String name) {
	Widget element = getElementByName(name);
	if (element instanceof Button) {
	    return (Button) element;
	}
	return null;
    }
}
