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
package org.plazmaforge.framework.uwt.jfx.adapter;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionModel;

/**
 * 
 * @author ohapon
 *
 */
public abstract class JFXViewerAdapter extends JFXControlAdapter {

    protected <T> ObservableList<T> toFXList(List<T> dataList) {
	return FXCollections.observableArrayList(dataList);
    }
    
    protected <T> void setSelectedIndex(SelectionModel<T> selectionModel, int index) {
	selectionModel.select(index);
    }
    
    protected <T> int getSelectedIndex(SelectionModel<T> selectionModel) {
	return selectionModel.getSelectedIndex();
    }   
    
    protected <T> void setSelectedItem(SelectionModel<T> selectionModel, T item) {
	selectionModel.select(item);
    }

    protected <T> T getSelectedItem(SelectionModel<T> selectionModel) {
	return selectionModel.getSelectedItem();
    }  
    
    ////
    protected SelectionModel getSelectionModel(javafx.scene.control.Control xControl) {
	if (xControl instanceof javafx.scene.control.ComboBox) {
	    return ((javafx.scene.control.ComboBox) xControl).getSelectionModel();
	} else if (xControl instanceof javafx.scene.control.ListView) {
	    return ((javafx.scene.control.ListView) xControl).getSelectionModel();
	} else if (xControl instanceof javafx.scene.control.TableView) {
	    return ((javafx.scene.control.TableView) xControl).getSelectionModel();
	}
	return null;
    }
    
    protected void setSelectedIndex(javafx.scene.control.Control xControl, int index) {
	setSelectedIndex(getSelectionModel(xControl), index);
    }

    protected int getSelectedIndex(javafx.scene.control.Control xControl) {
	return getSelectedIndex(getSelectionModel(xControl));
    }

    protected void setSelectedItem(javafx.scene.control.Control xControl, Object item) {
	setSelectedItem(getSelectionModel(xControl), item);
    }

    protected Object getSelectedItem(javafx.scene.control.Control xControl) {
	return getSelectedItem(getSelectionModel(xControl));
    }      
}
