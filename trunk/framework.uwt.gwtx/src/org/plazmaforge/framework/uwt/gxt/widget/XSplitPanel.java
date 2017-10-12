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
package org.plazmaforge.framework.uwt.gxt.widget;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.Style.Orientation;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;

/**
 * 
 * @author ohapon
 *
 */
public class XSplitPanel extends BorderLayoutContainer {

    private static final double DEFAULT_SIZE = 0.5;
    
    // Panel orientation: horizontal, vertical
    private Orientation orientation;
    
    // Array of regions. Set by orientation
    private LayoutRegion[] regions;
    
    public XSplitPanel() {
	this(Orientation.HORIZONTAL);
    }

    public XSplitPanel(Orientation orientation) {
	super();
	this.orientation = orientation == null ? Orientation.HORIZONTAL : orientation;
	initRegions();
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setRegionWidget(LayoutRegion region, Widget child, BorderLayoutData layoutData) {
	switch (region) {
	case CENTER:
	    setCenterWidget(child, layoutData);
	case NORTH:
	    setNorthWidget(child, layoutData);
	case EAST:
	    setEastWidget(child, layoutData);
	case SOUTH:
	    setSouthWidget(child, layoutData);
	case WEST:
	    setWestWidget(child, layoutData);
	}
    }
    
    protected void initRegions() {
	regions = new LayoutRegion[2];
	if (orientation == Orientation.HORIZONTAL) {
	    regions[0] = LayoutRegion.WEST;
	    regions[1] = /*LayoutRegion.CENTER;*/ LayoutRegion.EAST;
	} else {
	    regions[0] = LayoutRegion.NORTH;
	    regions[1] = LayoutRegion.CENTER; //LayoutRegion.SOUTH;
	}
    }

    protected LayoutRegion[] getRegions() {
	return regions;
    }
    
    protected LayoutRegion getFreeRegion() {
	// Get split regions
	LayoutRegion[] regions = getRegions();

	// Get split children
	Widget child1 = getRegionWidget(regions[0]);
	Widget child2 = getRegionWidget(regions[1]);

	if (child1 != null && child2 != null) {
	    return regions[0]; // region1
	}

	if (child1 == null) {
	    return regions[0]; // region1
	}

	if (child2 == null) {
	    return regions[1]; // region2
	}

	return null;
    }
    
    @Override
    public void add(Widget child) {
	addChild(child);
    }
      
    @Override
    public boolean remove(Widget child) {
	// TODO
	return super.remove(child);
    }

    protected void addChild(Widget child) {
	LayoutRegion region = getFreeRegion();
	
	ContentPanel wrapper = new ContentPanel();
	wrapper.add(child);

	BorderLayoutData d = createBorderLayoutData();
	
	setRegionWidget(region, wrapper, d);
    }

    protected BorderLayoutData createBorderLayoutData() {
	return createBorderLayoutData(DEFAULT_SIZE);
    }
    
    protected BorderLayoutData createBorderLayoutData(double size) {
	BorderLayoutData d = new BorderLayoutData(size);
	d.setMargins(new Margins(5));
	d.setCollapsible(true);
	d.setSplit(true);
	return d;
    }
}
