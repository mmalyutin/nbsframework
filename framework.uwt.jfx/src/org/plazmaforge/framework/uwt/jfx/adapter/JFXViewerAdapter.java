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
}
