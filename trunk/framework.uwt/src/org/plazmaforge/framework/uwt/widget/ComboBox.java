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

package org.plazmaforge.framework.uwt.widget;

import org.plazmaforge.framework.core.data.provider.ArrayProvider;
import org.plazmaforge.framework.core.data.provider.DataProvider;

public class ComboBox<T> extends AbstractViewerField<T> implements HasSelectionIndex<T>, IRefField<T> {

    private String displayProperty;
    
    private String refProperty;
    
    private Object refValue;

    private int selectionIndex = -1;
    
    public ComboBox() {
	super();
	setDataStore(createDefaultDataStore());
    }

    @Override
    protected DataProvider<T> createDefaultDataProvider() {
	DataProvider<T> dataProvider = new ArrayProvider<T>();
	return dataProvider;
    }

    @Override
    protected DataProvider<T> createDataListProvider(java.util.List<T> dataList) {
	DataProvider<T> dataProvider = new ArrayProvider<T>(dataList);
	return dataProvider;
    }
    
    public String getDisplayProperty() {
        return displayProperty;
    }

    public void setDisplayProperty(String displayProperty) {
        this.displayProperty = displayProperty;
        fireChangeProperty(PROPERTY_DISPLAY_PROPERTY, displayProperty);
    }
    
    ////
    
 
    
    public int getSelectionIndex() {
	if (isReadyInput()) {
	    return (Integer) getDelegateProperty(PROPERTY_SELECTION_INDEX);
	}
	return selectionIndex;
    }

    
    public void setSelectionIndex(int selectionIndex) {
	this.selectionIndex = selectionIndex;
	 fireChangeProperty(PROPERTY_SELECTION_INDEX, selectionIndex);
	if (isReadyInput()) {
	    setDelegateProperty(PROPERTY_SELECTION_INDEX, selectionIndex);
	    return;
	}
    }

    public void setSelectionFirst() {
	setSelectionIndex(0);
    }

    public String getRefProperty() {
        return refProperty;
    }

    public void setRefProperty(String refProperty) {
        this.refProperty = refProperty;
    }

    public Object getRefValue() {
        return refValue;
    }

    public void setRefValue(Object refValue) {
        this.refValue = refValue;
    }


    

}
