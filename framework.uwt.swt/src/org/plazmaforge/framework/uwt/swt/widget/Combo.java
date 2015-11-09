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

package org.plazmaforge.framework.uwt.swt.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

public class Combo extends AbstractExtCombo {

    /**
     * The List Control
     */
    private List list;
    
  
    
 
    public Combo(Composite parent, int style) {
	super(parent, style);
    }
    
    

    public Combo(Composite parent, int style, int toolStyle) {
	super(parent, style, toolStyle);
    }



    @Override
    protected void createPopupContent() {
	
	int style = getStyle ();
	int listStyle = SWT.SINGLE | SWT.V_SCROLL;
	if ((style & SWT.FLAT) != 0) listStyle |= SWT.FLAT;
	if ((style & SWT.RIGHT_TO_LEFT) != 0) listStyle |= SWT.RIGHT_TO_LEFT;
	if ((style & SWT.LEFT_TO_RIGHT) != 0) listStyle |= SWT.LEFT_TO_RIGHT;
	
	list = new List (popup, listStyle);
	popupContent = list;
	
	//UWT:FIX
	//initPopupContentListener();

    }
    
    protected void doControlAdd(String string) {
    	list.add(string);
    }
    
    protected void doControlAdd(String string, int index) {
    	list.add(string, index);
    }
    
    protected void doControlRemove(int index) {
	list.remove(index);
    }
    
    protected void doControlRemove(int start, int end) {
    	list.remove (start, end);
    }

    protected void doControlRemoveAll() {
    	list.removeAll();
    }

    protected void doControlDeselect(int index) {
    	list.deselect(index);
    }

    protected void doControlDeselectAll() {
    	list.deselectAll();
    }
    
    protected String doControlGetItem(int index) {
    	return list.getItem(index);
    }
    
    protected int doControlGetItemCount() {
    	return list.getItemCount();
    }
    
    protected int doControlGetItemHeight() {
    	return list.getItemHeight();
    }
    
    protected String [] doControlGetItems () {
    	return list.getItems();
    }
    
    protected int doControlGetSelectionIndex () {
    	return list.getSelectionIndex();
    }
    
    protected void doControlSetSelectionIndex(int index) {
    	list.setSelection(index);
    }
    
    protected void doControlShowSelection() {
	list.showSelection();
    }
    
    protected int doControlIndexOf(String string) {
	return string == null ? -1 : list.indexOf(string);
    }

}
