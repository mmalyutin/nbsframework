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

package org.plazmaforge.framework.uwt.gxt.adapter.viewer;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTHelper;
import org.plazmaforge.framework.uwt.widget.LabelProvider;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGrid;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGridCellRenderer;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class GXTTreeCellRenderer<M extends ModelData> extends TreeGridCellRenderer<M> {

    private UIObject viewer;
    
    /**
     * UWT LabelProvider
     */
    private LabelProvider labelProvider;
    
    
    public GXTTreeCellRenderer(UIObject viewer) {
	super();
	this.viewer = viewer;
    }

    public LabelProvider getLabelProvider() {
        return labelProvider;
    }


    public void setLabelProvider(LabelProvider labelProvider) {
        this.labelProvider = labelProvider;
    }

    protected String getText(TreeGrid<M> grid, M model, String property, int rowIndex, int colIndex) {
	if (labelProvider == null) {
	    return super.getText(grid, model, property, rowIndex, colIndex);
	}
	Object element = getElement(model);
	String text = labelProvider.getText(element);
	if (text == null) {
	    text = "";
	}
	return text;
    }
    
    protected AbstractImagePrototype calculateIconStyle(TreeGrid<M> grid, M model, String property, int rowIndex, int colIndex) {
	if (labelProvider == null) {
	    return super.calculateIconStyle(grid, model, property, rowIndex, colIndex);
	}
	
	Object element = getElement(model);
	Image image = labelProvider.getImage(element);

	AbstractImagePrototype xImage = createImage(viewer, image);
	if (xImage != null) {
	    return xImage;
	}

	return null;
    }
    
    protected Object getElement(M model) {
  	return GXTHelper.getBean(model);
    }
    
    /**
     * Return value of model by property
     * @param model
     * @param property
     * @return
     */
    protected Object getValue(M model, String property) {
	return GXTHelper.getValue(model, property);
    }
    
    
    protected AbstractImagePrototype createImage(UIObject element, Image image) {
	return GXTHelper.createImage(element, image);
    }
}
