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

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.plazmaforge.framework.core.data.Accessor;
import org.plazmaforge.framework.uwt.util.UWTUtils;

/**
 * Extension implementation of <code>Combo</code>
 * 
 * @author ohapon
 *
 */
public abstract class AbstractExtCombo extends AbstractCombo {


    private final int DEFAULT_VISIBLE_ITEM_COUNT = 5;

    private int visibleItemCount;
    
    private java.util.List dataList = new ArrayList();
    
    private String displayProperty;
    
    
    public AbstractExtCombo(Composite parent, int style, int toolStyle) {
	super(parent, style, toolStyle);
    }

    public AbstractExtCombo(Composite parent, int style) {
	super(parent, style);
    }

    
    ////
    
    protected abstract void doControlAdd(String string);
    
    protected abstract void doControlAdd(String string, int index);
    
    protected abstract void doControlRemove(int index);
    
    protected abstract void doControlRemove(int start, int end);
    
    protected abstract void doControlRemoveAll();

    protected abstract void doControlDeselect(int index);
	
    protected abstract void doControlDeselectAll();

    ////
    
    protected abstract String doControlGetItem(int index);
    
    protected abstract int doControlGetItemCount();
    
    protected abstract int doControlGetItemHeight();
    
    protected abstract String[] doControlGetItems();
    
    protected abstract int doControlGetSelectionIndex();
    
    protected abstract void doControlSetSelectionIndex(int index);
    
    protected abstract void doControlShowSelection();
    
    ////
    
    protected abstract int doControlIndexOf(String string);
    
    ////
    
    protected void doModelAdd(Object value) {
	dataList.add(value);
    }
    
    protected void doModelAdd(Object value, int index) {
	dataList.add(index, value);
    }
    
    
    protected void doModelRemove(int index) {
	dataList.remove(index);
    }
    
    protected void doModelRemove(int start, int end) {
	for (int i = start; i <= end; i++) {
	    doModelRemove(i);
    	}
    }
    
    protected void doModelRemoveAll() {
	dataList.clear();
    }

    // UWT
    protected Object doModelGet(int index) {
	if (index < 0 || index >= dataList.size()) {
	    return null; 
	}
	return dataList.get(index);
    }
    
    public String getDisplayText(Object value) {
	if (value == null) {
	    return getNullTextValue();
	}
	String property = getDisplayProperty();  
	if (property == null || "toString".equals(property)) {
	    return value.toString();
	}
	
	//TODO
	Accessor accessor = (Accessor) getData(UWTUtils.PROPERTY_ACCESSOR);
	if (accessor == null) {
	    accessor = UWTUtils.createAccessor(value.getClass(), property);
	    setData(UWTUtils.PROPERTY_ACCESSOR, accessor);
	}
	Object v = accessor.getValue(value);
	return v == null ? getNullTextValue() : v.toString();
    }
       
    public Object getDisplayValue(Object value) {
	// TODO: Stub
	return value == null ? null : value;
    }
    
    public String getNullTextValue() {
	// TODO: Stub
	return "";
    }
    
    public int indexOfDataValue(Object value) {
	if (value == null) {
	    return -1;
	}
	
	// Get display value from value
	Object displayValue = getDisplayValue(value);
	if (displayValue == null) {
	    return -1;
	}
	
	// Get display text form display value
	String displayText = getDisplayText(displayValue);
	
	// Get index of display text from control
	return doControlIndexOf(displayText);
	
    }
    
    ////
    
    public Object getValue() {
	
	//return doGetValue();
	
	//NonUWT
	return getSelectionValue();
    }

    public void setValue(Object value) {
	
	doSetValue(value);
	
	if (value == null) {
	    clearAll(getNullTextValue());
	    return;
	}
	setSelectionValue(value);
	
	//NonUWT
	//super.setValue(value);
	//if (value == null) {
	//    clearAll(getNullTextValue());
	//    if (getNullTextValue() == null) {
	//	clearAll();
	//    } else {
	//	super.setText(getNullTextValue());
	//    }
	//   return;
	//}
	//setSelectionValue(value);
	//super.setText(getDisplayText(value));
    }

    public Object getValue(int index) {
	
	// TODO: Stub
	//return doControlGetItem(index);
	
	//NonUWN
	//return getModel().getValue(index);
	
	return doModelGet(index);
    }  
    
    
    protected Object getSelectionValue() {
	int index = getSelectionIndex();
	return getValue(index);
    }
    
    protected void setSelectionValue(Object value) {
	
	// TODO: Stub
	int index = indexOfDataValue(value);
	setSelection(index);
	
	//NonUWT
	//int index = getModel().indexOfDataValue(value);
	//if (index < 0) {
	//    //TODO: Must analyze
	//    //clearAll();
	//    //return;
	//}
	//setSelection(index);
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////
    
    
    /**
     * Adds the argument to the end of the receiver's list.
     *
     * @param string the new item
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_NULL_ARGUMENT - if the string is null</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     *
     * @see #add(String,int)
     */
    public void add(String string) {
	checkWidget();
    	if (string == null) SWT.error (SWT.ERROR_NULL_ARGUMENT);
	doControlAdd(string);
	doModelAdd(string); // UWT
    }
    
    
    public void addValue(Object value) {
	checkWidget();
    	if (value == null) SWT.error (SWT.ERROR_NULL_ARGUMENT);
	doControlAdd(getDisplayText(value));
	doModelAdd(value); // UWT
    }
    
     
    /**
     * Adds the argument to the receiver's list at the given
     * zero-relative index.
     * <p>
     * Note: To add an item at the end of the list, use the
     * result of calling <code>getItemCount()</code> as the
     * index or use <code>add(String)</code>.
     * </p>
     *
     * @param string the new item
     * @param index the index for the item
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_NULL_ARGUMENT - if the string is null</li>
     *    <li>ERROR_INVALID_RANGE - if the index is not between 0 and the number of elements in the list (inclusive)</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     *
     * @see #add(String)
     */
    public void add(String string, int index) {
	checkWidget();
    	if (string == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
    	doControlAdd(string, index);
    	doModelAdd(string, index); // UWT
    }
    
    
    
    public void addValue(Object value, int index) {
	checkWidget();
    	if (value == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
    	String string = getDisplayText(value); 
    	if (string == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
	doControlAdd(string, index);
	doModelAdd(string, index); // UWT
    }
        

    ////
    
    /**
     * Removes the item from the receiver's list at the given
     * zero-relative index.
     *
     * @param index the index for the item
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_INVALID_RANGE - if the index is not between 0 and the number of elements in the list minus 1 (inclusive)</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public void remove(int index) {
	checkWidget();
	doControlRemove(index);
	doModelRemove(index); // UWT
    }
    
    /**
     * Removes the items from the receiver's list which are
     * between the given zero-relative start and end 
     * indices (inclusive).
     *
     * @param start the start of the range
     * @param end the end of the range
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_INVALID_RANGE - if either the start or end are not between 0 and the number of elements in the list minus 1 (inclusive)</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public void remove(int start, int end) {
	checkWidget();
	doControlRemove(start, end);
	doModelRemove(start, end); // UWT
    }
    
    /**
     * Searches the receiver's list starting at the first item
     * until an item is found that is equal to the argument, 
     * and removes that item from the list.
     *
     * @param string the item to remove
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_NULL_ARGUMENT - if the string is null</li>
     *    <li>ERROR_INVALID_ARGUMENT - if the string is not found in the list</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public void remove(String string) {
    	checkWidget();
    	if (string == null) SWT.error (SWT.ERROR_NULL_ARGUMENT);
    	int index = doControlIndexOf(string);
    	if (index < 0) SWT.error (SWT.ERROR_INVALID_ARGUMENT);
    	doControlRemove(index);
    	doModelRemove(index); // UWT
    }

    /**
     * Removes all of the items from the receiver's list and clear the
     * contents of receiver's text field
     * <p>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public void removeAll() {
	removeAll(true);
    }
    
    public void removeAll(boolean isClearText) {
    	checkWidget();
    	
    	if (isClearText) {
    	    // TODO: We only clear text. But value is not changed. May be we must fix it.
    	    doSetText(""); //$NON-NLS-1$

    	}
    	doControlRemoveAll();
	doModelRemoveAll(); // UWT
    }
    

    
    
    /**
     * Sets the selection in the receiver's text field to an empty
     * selection starting just before the first character. If the
     * text field is editable, this has the effect of placing the
     * i-beam at the start of the text.
     * <p>
     * Note: To clear the selected items in the receiver's list, 
     * use <code>deselectAll()</code>.
     * </p>
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     *
     * @see #deselectAll
     */
    public void clearSelection() {
    	checkWidget();
    	text.clearSelection ();
    	doControlDeselectAll();
    }
    
    /**
     * Deselects the item at the given zero-relative index in the receiver's 
     * list.  If the item at the index was already deselected, it remains
     * deselected. Indices that are out of range are ignored.
     *
     * @param index the index of the item to deselect
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public void deselect(int index) {
	checkWidget();
	doControlDeselect(index);
    }

    /**
     * Deselects all selected items in the receiver's list.
     * <p>
     * Note: To clear the selection in the receiver's text field,
     * use <code>clearSelection()</code>.
     * </p>
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     *
     * @see #clearSelection
     */
    public void deselectAll() {
	checkWidget();
	doControlDeselectAll();
    }

    
    /**
     * Returns the item at the given, zero-relative index in the
     * receiver's list. Throws an exception if the index is out
     * of range.
     *
     * @param index the index of the item to return
     * @return the item at the given index
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_INVALID_RANGE - if the index is not between 0 and the number of elements in the list minus 1 (inclusive)</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public String getItem(int index) {
	checkWidget();
	return doControlGetItem(index);
    }
    
    
    /**
     * Returns the number of items contained in the receiver's list.
     *
     * @return the number of items
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public int getItemCount() {
	checkWidget();
	return doControlGetItemCount();
    }
    
    
    /**
     * Returns the height of the area which would be used to
     * display <em>one</em> of the items in the receiver's list.
     *
     * @return the height of one item
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public int getItemHeight() {
	checkWidget();
	return doControlGetItemHeight();
    }
    
    
    /**
     * Returns an array of <code>String</code>s which are the items
     * in the receiver's list. 
     * <p>
     * Note: This is not the actual structure used by the receiver
     * to maintain its list of items, so modifying the array will
     * not affect the receiver. 
     * </p>
     *
     * @return the items in the receiver's list
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public String [] getItems() {
	checkWidget();
	return doControlGetItems();
    }
    
    /**
     * Returns the zero-relative index of the item which is currently
     * selected in the receiver's list, or -1 if no item is selected.
     *
     * @return the index of the selected item
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public int getSelectionIndex() {
	checkWidget();
	return doControlGetSelectionIndex();
    }
    
    
    /**
     * Sets the selection in the receiver's text field to the
     * range specified by the argument whose x coordinate is the
     * start of the selection and whose y coordinate is the end
     * of the selection. 
     *
     * @param selection a point representing the new selection start and end
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_NULL_ARGUMENT - if the point is null</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public void setSelection(Point selection) {
    	checkWidget();
    	if (selection == null) SWT.error (SWT.ERROR_NULL_ARGUMENT);
    	text.setSelection (selection.x, selection.y);
    }

    public void setSelection(int index) {
	checkWidget();
	doControlSetSelectionIndex(index);
    	
    	int i = doControlGetSelectionIndex();
    	if (i > -1) {
    	    String string = doControlGetItem(index);
    	    doSetText(string, true);
    	}
    }
    
    
      
    
    /**
     * Sets the contents of the receiver's text field to the
     * given string.
     * <p>
     * Note: The text field in a <code>Combo</code> is typically
     * only capable of displaying a single line of text. Thus,
     * setting the text to a string containing line breaks or
     * other special characters will probably cause it to 
     * display incorrectly.
     * </p>
     *
     * @param string the new text
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_NULL_ARGUMENT - if the string is null</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public void setText(String string) {
    	checkWidget();
    	if (string == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
    	int index = doControlIndexOf(string);
    	if (index == -1) {
    	    doControlDeselectAll();
    	    doSetText(string);
    	    return;
    	}
    	doSetText(string, true);
    	
    	doControlSetSelectionIndex(index);
    	doControlShowSelection();
    }
    
    protected void doSetText(String string) {
	doSetText(string, false);
    }
    
    protected void doSetText(String string, boolean isSelectAll) {
	text.setText(string == null ? "" : string);
	if (isSelectAll) {
	    text.selectAll();
	}
    }


    /////////////////////////////////////////////////////////////
    
    
    protected void setPopupSize() {
	if (popup == null || text == null) {
	    return;
	}
	Point size = popup.getSize();
	Point textSize = text.getSize();
	int width = textSize.x;
	int height = size.y;
	if (getVisibleItemCount() > DEFAULT_VISIBLE_ITEM_COUNT) {
	    height = doControlGetItemHeight() * getVisibleItemCount();
	}
	popup.setSize(width, height);
    }
    
    /**
     * Gets the number of items that are visible in the drop
     * down portion of the receiver's list.
     *
     * @return the number of items that are visible
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     * 
     */
    public int getVisibleItemCount () {
    	checkWidget ();
    	return visibleItemCount;
    }

    public void setVisibleItemCount(int visibleItemCount) {
        this.visibleItemCount = visibleItemCount;
    }
    
    protected void handleKeyReleased(KeyEvent e) {
	if ((e.keyCode == SWT.ARROW_DOWN || e.keyCode == SWT.ARROW_UP) && e.stateMask == 0 && hasPopupControl()) {
	    
	    int size = getItemCount();
	    if (size < 1) {
		return;
	    }
	    int index = getSelectionIndex();
	    if (e.keyCode == SWT.ARROW_UP) {
		 if (index < 1) {
		     return;
		 }
		 index--;
		 updateSelection(index);
	    } else {
		 if (index >= size - 1) {
		     return;
		 }
		 index++;
		 updateSelection(index);
	    }
	     
	    return;
	}
	
	super.handleKeyReleased(e);
    }
    
    public void updateSelection(int index) {
	setSelection(index);
	Object value = getValue(index);
	super.setText(getDisplayText(value));
	text.selectAll();
	notifySelection();
    }
    
    public void notifySelection() {
	Event ne = new Event();
	notifyListeners(SWT.Selection, ne);
    }
    
    
    /**
     * Clear all
     */
    protected void clearAll() {
	clearAll("");
    }
    
    /**
     * Clear all and set text
     * @param text
     */
    protected void clearAll(String text) {
	clearSelection();
	doSetText(text);
	// UWT:FIX
	//setText("");
    }
    
    private boolean isPressKeyArrowUpDown;
    
    /**
     * Manages popup content events. Extend the selection behaviour, adding the
     * selected date in the <code>data</code> attribute of the event.
     * 
     * @param event event
     * @see org.plazmaforge.framework.client.swt.controls.AbstractCombo#contentEvent(org.eclipse.swt.widgets.Event)
     */
      
    @Override
    protected void contentEvent(Event event) {
	switch (event.type) {
	    case SWT.Selection :
		super.contentEvent(event);
                if ( event.doit && !isPressKeyArrowUpDown) {
                    
                    /*
                    int index = doControlGetSelectionIndex();
                    if (index > -1) {
                        String data = doControlGetItem(index);
                        text.setText(data);
                    }
                    dropDown(false);
                    text.setFocus();
                    */
                    
                    doSelect();
                    
                }
                if (isPressKeyArrowUpDown) {
                    event.doit = false;  
                }
                isPressKeyArrowUpDown = false;
                break;
	    case SWT.MouseUp :
		super.contentEvent(event);
		event.doit = false;
                if ( event.doit ) {
                    if (doControlGetItemCount() == 0) {
                        dropDown(false);
                        text.setFocus();
                    }
                }
                break;
	    case SWT.KeyDown :
		if (event.keyCode == SWT.CR) {
		    keyEnter = event.keyCode;
		    event.keyCode = 0;
		    event.doit = false;		    
		    doSelect();
		    return;
		}
		super.contentEvent(event);
		if (event.keyCode == SWT.ARROW_UP || event.keyCode == SWT.ARROW_DOWN) {
		    isPressKeyArrowUpDown = true;  
		}
		
                break;                
            default :
                super.contentEvent(event);
                break;
        }
    }
    
    protected void doSelect() {
	doSetSelectionText();
	dropDown(false);
	text.setFocus();
    }
   
    protected void doSetSelectionText() {
	 int index = doControlGetSelectionIndex();
         if (index > -1) {
             String data = doControlGetItem(index);
             doSetText(data);
         }
    }
   
   
    
   

    /**
     * Called just before the popup is droppped. The selected date of the
     * 
     * calendar is setted to the current date present in the formatted text.
     * 
     * @see org.plazmaforge.framework.client.swt.controls.AbstractCombo#beforeDrop()
     */
    @Override
    protected void beforeDrop() {
	super.beforeDrop();
	String string = getText();
	if (string == null) {
	    return;
	}
	int index = doControlIndexOf(string);
	if (index < 0) {
	    doControlDeselectAll();
	    return;
	}
	doSetText(string, true);
    	doControlSetSelectionIndex(index);
    	doControlShowSelection();
    }

    public String getDisplayProperty() {
        return displayProperty;
    }

    public void setDisplayProperty(String displayProperty) {
        this.displayProperty = displayProperty;
    }
    
    
}
