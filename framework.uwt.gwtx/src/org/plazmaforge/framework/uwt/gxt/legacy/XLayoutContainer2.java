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

package org.plazmaforge.framework.uwt.gxt.legacy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.ui.HasWidgets;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * 
 * @author ohapon
 *
 */
public class XLayoutContainer2 extends SimpleContainer  {
    
    public static final String DEFAULT_LAYOUT_TYPE = "flow";

    public static final String[] LAYOUT_TYPES = new String[] {"flow", "horizontal", "vertical", "border"};

    private HasWidgets container;
    private String layoutType;
    
    //ONLY-FOR-TEST
    private Label testLabel;
    private Label layoutLabel;
    private TextButton layoutButton;
    
    public XLayoutContainer2() {
        super();
        init(DEFAULT_LAYOUT_TYPE);
    }
   
    public XLayoutContainer2(String layoutType) {
	super();
	init(layoutType);
    }

    private void init(String layoutType) {
        this.layoutType = layoutType;
        
        initContainer();

        //buildContainer();
        //updateState();
        doLayout();
    }
    
    protected void initContainer() {
	container = createContainer();
	setWidget((Widget) container);
    }
    
    public void setLayout(String layoutType) {
	this.layoutType = layoutType;
	rebuildContainer();
	doLayout();
    }

    @Override
    public void add(Widget child) {
	container.add(child);
    }

    @Override
    public boolean remove(Widget child) {
	return container.remove(child);
    }
    
    protected HasWidgets createContainer() {
        if ("flow".equals(layoutType)) {
            return new FlowLayoutContainer();
        } else if ("horizontal".equals(layoutType)) {
            //return new HorizontalLayoutContainer();
            return new HBoxLayoutContainer();
        } else if ("vertical".equals(layoutType)) {
            //return new VerticalLayoutContainer();
            return new VBoxLayoutContainer();
        } else if ("border".equals(layoutType)) {
            return new BorderLayoutContainer();
        }

        // by default
        return new FlowLayoutContainer();
    }

   
    protected void rebuildContainer() {
        List<Widget> children = getContainerChildren();
        resetContainerChildren(children);

        initContainer();
        addContainerChildren(children);
    }

    protected List<Widget> getContainerChildren() {
        Iterator<Widget> widgets = container.iterator();
        List<Widget> children = new ArrayList<Widget>();
        while (widgets.hasNext()) {
            children.add(widgets.next());
        }
        return children.isEmpty() ? null : children;
    }

    
    protected void resetContainerChildren(List<Widget> children) {
        if (children == null) {
            return;
        }
        for (Widget child : children) {
            child.setLayoutData(null);
            container.remove(child);
        }       
    }

   
    protected void addContainerChildren(List<Widget> children) {
        if (children == null) {
            return;
        }

        //TODO: ONLY-FOR-TEST
        //if (container instanceof BorderLayoutContainer) {
        //    BorderLayoutContainer b = (BorderLayoutContainer) container;
        //    b.setNorthWidget(testLabel);
        //    b.setCenterWidget(layoutButton);
        //    b.setSouthWidget(layoutLabel);
        //    return;
        //}

        for (Widget child : children) {
            container.add(child);           
        }
    }
    

    //ONLY-FOR-TEST
    protected void buildContainer() {
	testLabel = new Label("Test Label-1");
	layoutLabel = new Label("Layout: ");
	layoutButton = new TextButton("Change Layout");
	layoutButton.addSelectHandler(new SelectHandler() {

	    @Override
	    public void onSelect(SelectEvent event) {
		doChangeLayout();
	    }

	});

	container.add(testLabel);
	container.add(layoutLabel);
	container.add(layoutButton);
    }
    

    //ONLY-FOR-TEST
    protected void doChangeLayout() {
	int index = -1;
	
	// Find index of current layout
	for (int i = 0; i < LAYOUT_TYPES.length; i++) {
	    if (layoutType.equals(LAYOUT_TYPES[i])) {
		index = i;
		break;
	    }
	}
	
	// Set next index (+1)
	if (index < 0 || index == LAYOUT_TYPES.length - 1) {
	    index = 0;
	} else {
	    index++;
	}
	
	layoutType = LAYOUT_TYPES[index];
	rebuildContainer();
	updateState();
	doLayout();
    }
    
    //ONLY-FOR-TEST
    protected void updateState() {
        layoutLabel.setText("Layout: " + layoutType);
    }

    
}