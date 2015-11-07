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

import org.plazmaforge.framework.core.data.HasDataType;
import org.plazmaforge.framework.core.data.HasProperty;
import org.plazmaforge.framework.core.data.ValuePresenter;
import org.plazmaforge.framework.core.data.ValueProvider;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;

/**
 * Base column
 * 
 * @author ohapon
 *
 */
public class Column extends Item implements HasProperty, HasDataType {


    public static final int DEFAULT_WIDTH = 100;
    
    /**
     * Width of column
     */
    private int width;
    
    /**
     * Name of property
     */
    private String property;

    /**
     * Name of type of value
     * For example: String, Integer, Float, Double, Date, Boolean and etc.
     */
    private String dataType;

    
    /**
     * Format of value
     */
    private String format;
    
    
    private HorizontalAlign align;
    

    /**
     * True if column can be sorted by clicking on the header or other sort mode
     */
    private boolean sortable;
    
    /**
     * True if column can be filtered in filter mode
     */
    private boolean filterable;
    
    
    /**
     * True if column is resizable (can resize column)
     */
    private boolean resizable;

    
    /**
     * True if column is moveable (can move column)
     */
    private boolean moveable;
    
    
    /**
     * True if column is editable (can edit cells of column)
     * To edit use <code>CellEditor</code>
     */
    private boolean editable;

    /**
     * Renderer of cell
     */
    private CellRenderer cellRenderer;
    
    /**
     * Editor of cell
     */
    private CellEditor cellEditor;
    
    
    
    /**
     * Value presenter 
     * toString, toValue
     */
    private ValuePresenter valuePresenter;
    
    
    /**
     * Value provider
     * getValue, setValue
     */
    private ValueProvider valueProvider;
    
    /**
     * Label provider
     * getImage, getText
     */
    private LabelProvider labelProvider;
    
    
    public Column() {
	super();
	setWidth(DEFAULT_WIDTH);
	sortable = true;
	filterable = true;
	resizable = true;
	editable = false;
    }


    @Override
    protected void configure() {
	super.configure();
	configureFormat();
    }
  
    protected void configureFormat() {
	if (getFormat() != null) {
	    return;
	}
	
	String format = getConfigFormat(dataType);
	if (format != null) {
	    setFormat(format);
	}
    }
    
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        fireChangeProperty(PROPERTY_WIDTH, width);
    }


    public String getProperty() {
        return property;
    }


    public void setProperty(String property) {
        this.property = property;
        fireChangeProperty(PROPERTY_PROPERTY, property);
    }


    public String getDataType() {
        return dataType;
    }


    public void setDataType(String dataType) {
        this.dataType = dataType;
        fireChangeProperty(PROPERTY_DATA_TYPE, dataType);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
        fireChangeProperty(PROPERTY_FORMAT, format);
    }

    
    public HorizontalAlign getAlign() {
        return align;
    }

    public void setAlign(HorizontalAlign align) {
        this.align = align;
        fireChangeProperty(PROPERTY_ALIGN, align);
    }


    public boolean isSortable() {
        return sortable;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
        fireChangeProperty(PROPERTY_SORTABLE, sortable);
    }

    public boolean isFilterable() {
        return filterable;
    }


    public void setFilterable(boolean filterable) {
        this.filterable = filterable;
        fireChangeProperty(PROPERTY_FILTERABLE, filterable);
    }


    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
        fireChangeProperty(PROPERTY_RESIZABLE, resizable);
    }

    
    public boolean isMoveable() {
        return moveable;
    }

    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
        fireChangeProperty(PROPERTY_MOVEABLE, moveable);
    }


    public boolean isEditable() {
	
	//TODO
	if (cellEditor == null) {
	    return false;
	}
	
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
        fireChangeProperty(PROPERTY_EDITABLE, editable);
    }

    public CellRenderer getCellRenderer() {
        return cellRenderer;
    }

    public void setCellRenderer(CellRenderer cellRenderer) {
        this.cellRenderer = cellRenderer;
        fireChangeProperty(PROPERTY_CELL_RENDERER, cellRenderer);
        
    }

    public CellEditor getCellEditor() {
        return cellEditor;
    }

    public void setCellEditor(CellEditor cellEditor) {
        this.cellEditor = cellEditor;
        fireChangeProperty(PROPERTY_CELL_EDITOR, cellEditor);
        
        //TODO
        if (cellEditor != null) {
            setEditable(true);
        }

    }


    ////

    public ValuePresenter getValuePresenter() {
        return valuePresenter;
    }


    public void setValuePresenter(ValuePresenter valuePresenter) {
        this.valuePresenter = valuePresenter;
    }


    public ValueProvider getValueProvider() {
        return valueProvider;
    }


    public void setValueProvider(ValueProvider valueProvider) {
        this.valueProvider = valueProvider;
    }


    public LabelProvider getLabelProvider() {
        return labelProvider;
    }

    public void setLabelProvider(LabelProvider labelProvider) {
        this.labelProvider = labelProvider;
        fireChangeProperty(PROPERTY_LABEL_PROVIDER, labelProvider);
    }

    
}
