/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General private
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General private License for more details.
 * 
 * You should have received a copy of the GNU Lesser General private
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

package org.plazmaforge.framework.uwt.widget;

import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.graphics.Image;


/**
 * 
 * Describe cell context
 * 
 * @author ohapon
 *
 */
public class CellContext {

    /**
     * Index of row
     */
    private int row;
    
    /**
     * Index of column
     */
    private int column;
    
    /**
     * Control
     */
    private Control control;
    
    /**
     * Cell value
     */
    private Object value;

    /**
     * Cell text
     */
    private String text;

    
    /**
     * Cell image
     */
    private Image image;
    
    
    /**
     * Row data (entity)
     */
    private Object data;
    
    
    /**
     * Background color of cell
     */
    private Color background;
    
    /**
     * Foreground color of cell
     */
    private Color foreground;
  
    /**
     * Font of cell
     */
    private Font font;    
    
    /**
     * True if row selected
     */
    private boolean selected;
    
    /**
     * True if cell in focus
     */
    private boolean focus;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }
    
    
    
    
}
