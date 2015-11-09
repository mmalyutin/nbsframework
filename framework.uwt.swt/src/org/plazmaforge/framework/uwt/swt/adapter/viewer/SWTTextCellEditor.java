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

package org.plazmaforge.framework.uwt.swt.adapter.viewer;

import java.text.MessageFormat;

import org.eclipse.jface.util.Assert;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class SWTTextCellEditor extends CellEditor {

    protected Control control;
    
    protected Text textControl;
    
    private ModifyListener modifyListener;

    private boolean isSelection = false;
    private boolean isDeleteable = false;
    private boolean isSelectable = false;

    private static final int defaultStyle = SWT.SINGLE;

    public SWTTextCellEditor() {
	setStyle(defaultStyle);
    }

    public SWTTextCellEditor(Composite parent) {
	this(parent, defaultStyle);
    }

    public SWTTextCellEditor(Composite parent, int style) {
	super(parent, style);
    }

    private void checkDeleteable() {
	boolean oldIsDeleteable = isDeleteable;
	isDeleteable = isDeleteEnabled();

	if (oldIsDeleteable != isDeleteable) {
	    fireEnablementChanged(DELETE);
	}
    }

    /**
     * Checks to see if the "selectable" state (can select) has changed and if
     * so fire an enablement changed notification.
     */

    private void checkSelectable() {
	boolean oldIsSelectable = isSelectable;
	isSelectable = isSelectAllEnabled();
	if (oldIsSelectable != isSelectable) {
	    fireEnablementChanged(SELECT_ALL);
	}
    }

    /**
     * Checks to see if the selection state (selection / no selection) has
     * changed and if so fire an enablement changed notification.
     */
    private void checkSelection() {
	boolean oldIsSelection = isSelection;
	isSelection = textControl.getSelectionCount() > 0;
	if (oldIsSelection != isSelection) {
	    fireEnablementChanged(COPY);
	    fireEnablementChanged(CUT);
	}
    }

    protected Control createControl(Composite parent) {
	textControl = new Text(parent, getStyle());
	control = textControl;
	initTextControl(parent);
	return control;
    }
    
    protected void initTextControl(Composite parent) {
	textControl.addSelectionListener(new SelectionAdapter() {
	    public void widgetDefaultSelected(SelectionEvent e) {
		handleDefaultSelection(e);
	    }
	});

	textControl.addKeyListener(new KeyAdapter() {

	    public void keyPressed(KeyEvent e) {
		keyReleaseOccured(e);
		// as a result of processing the above call, clients may have
		// disposed this cell editor
		if ((getControl() == null) || getControl().isDisposed()) {
		    return;
		}
		checkSelection(); // see explanation below
		checkDeleteable();
		checkSelectable();
	    }
	});
	textControl.addTraverseListener(new TraverseListener() {

	    public void keyTraversed(TraverseEvent e) {

		if (e.detail == SWT.TRAVERSE_ESCAPE
			|| e.detail == SWT.TRAVERSE_RETURN) {
		    e.doit = false;
		}
	    }
	});

	// We really want a selection listener but it is not supported so we
	// use a key listener and a mouse listener to know when selection
	// changes
	// may have occurred
	textControl.addMouseListener(new MouseAdapter() {
	    public void mouseUp(MouseEvent e) {
		checkSelection();
		checkDeleteable();
		checkSelectable();
	    }

	});

	textControl.addFocusListener(new FocusAdapter() {

	    public void focusLost(FocusEvent e) {
		SWTTextCellEditor.this.focusLost();
	    }
	});

	textControl.setFont(parent.getFont());
	textControl.setBackground(parent.getBackground());
	textControl.setText("");//$NON-NLS-1$
	textControl.addModifyListener(getModifyListener());
	
    }

    protected Object doGetValue() {
	return textControl.getText();
    }

    protected void doSetFocus() {
	if (textControl != null) {
	    textControl.selectAll();
	    textControl.setFocus();
	    checkSelection();
	    checkDeleteable();
	    checkSelectable();
	}
    }

    protected void doSetValue(Object value) {
	Assert.isTrue(textControl != null && (value instanceof String));
	textControl.removeModifyListener(getModifyListener());
	textControl.setText((String) value);
	textControl.addModifyListener(getModifyListener());
    }

    /**
     * Processes a modify event that occurred in this text cell editor. This
     * framework method performs validation and sets the error message
     * accordingly, and then reports a change via fireEditorValueChanged.
     * Subclasses should call this method at appropriate times. Subclasses may
     * extend or reimplement.
     * 
     * @param e
     */
    protected void editOccured(ModifyEvent e) {
	String value = textControl.getText();
	if (value == null) {
	    value = "";//$NON-NLS-1$
	}
	Object typedValue = value;
	boolean oldValidState = isValueValid();
	boolean newValidState = isCorrect(typedValue);
	if (!newValidState) {

	    // try to insert the current value into the error message.
	    setErrorMessage(MessageFormat.format(getErrorMessage(),
		    new Object[] { value }));
	}
	valueChanged(oldValidState, newValidState);
    }

    /**
     * Since a text editor field is scrollable we don't set a minimumSize.
     */
    public LayoutData getLayoutData() {
	LayoutData data = new LayoutData();
	data.minimumWidth = 0;
	return data;
    }

    /**
     * Return the modify listener.
     */
    protected ModifyListener getModifyListener() {
	if (modifyListener == null) {
	    modifyListener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
		    editOccured(e);
		}
	    };
	}
	return modifyListener;
    }

    /**
     * Handles a default selection event from the text control by applying the
     * editor value and deactivating this cell editor.
     * 
     * @param event
     */
    protected void handleDefaultSelection(SelectionEvent event) {
	// same with enter-key handling code in keyReleaseOccured(e);
	fireApplyEditorValue();
	deactivate();
    }

    /**
     * The TextCellEditor implementation of this CellEditor method returns true
     * if the current selection is not empty.
     */
    public boolean isCopyEnabled() {
	if (textControl == null || textControl.isDisposed()) {
	    return false;
	}
	return textControl.getSelectionCount() > 0;
    }

    /**
     * The TextCellEditor implementation of this CellEditor method returns true
     * if the current selection is not empty.
     */
    public boolean isCutEnabled() {
	if (textControl == null || textControl.isDisposed()) {
	    return false;
	}
	return textControl.getSelectionCount() > 0;
    }

    /**
     * The TextCellEditor implementation of this CellEditor method returns true
     * if there is a selection or if the caret is not positioned at the end of
     * the text.
     */
    public boolean isDeleteEnabled() {
	if (textControl == null || textControl.isDisposed()) {
	    return false;
	}
	return textControl.getSelectionCount() > 0
		|| textControl.getCaretPosition() < textControl.getCharCount();
    }

    /**
     * The TextCellEditor implementation of this CellEditor method always
     * returns true.
     */
    public boolean isPasteEnabled() {
	if (textControl == null || textControl.isDisposed()) {
	    return false;
	}
	return true;
    }

    /**
     * Check if save all is enabled
     * 
     * @return
     */
    public boolean isSaveAllEnabled() {
	if (textControl == null || textControl.isDisposed()) {
	    return false;
	}
	return true;
    }

    /**
     * Returns true if this cell editor is able to perform the select all
     * action. This default implementation always returns false. Subclasses may
     * override
     */
    public boolean isSelectAllEnabled() {
	if (textControl == null || textControl.isDisposed()) {
	    return false;
	}
	return textControl.getCharCount() > 0;
    }

    /**
     * Processes a key release event that occurred in this cell editor. The
     * TextCellEditor implementation of this framework method ignores when the
     * RETURN key is pressed since this is handled in handleDefaultSelection. An
     * exception is made for Ctrl+Enter for multi-line texts, since a default
     * selection event is not sent in this case.
     */
    protected void keyReleaseOccured(KeyEvent keyEvent) {
	if (keyEvent.character == '\r') { // Return key
	    // Enter is handled in handleDefaultSelection.
	    // Do not apply the editor value in response to an Enter key event
	    // since this can be received from the IME when the intent is -not-
	    // to apply the value.
	    //
	    // An exception is made for Ctrl+Enter for multi-line texts, since
	    // a default selection event is not sent in this case.
	    if (textControl != null && !textControl.isDisposed()
		    && (textControl.getStyle() & SWT.MULTI) != 0) {
		if ((keyEvent.stateMask & SWT.CTRL) != 0) {
		    super.keyReleaseOccured(keyEvent);
		}
	    }
	    return;
	}
	super.keyReleaseOccured(keyEvent);
    }

    /**
     * The TextCellEditor implementation of this CellEditor method copies the
     * current selection to the clipboard.
     */
    public void performCopy() {
	textControl.copy();
    }

    /**
     * The TextCellEditor implementation of this CellEditor method cuts the
     * current selection to the clipboard.
     */
    public void performCut() {
	textControl.cut();
	checkSelection();
	checkDeleteable();
	checkSelectable();
    }

    /**
     * The TextCellEditor implementation of this CellEditor method deletes the
     * current selection or, if there is no selection, the character next
     * character from the current position.
     */
    public void performDelete() {
	if (textControl.getSelectionCount() > 0) {
	    // remove the contents of the current selection
	    textControl.insert(""); //$NON-NLS-1$
	} else {
	    // remove the next character
	    int pos = textControl.getCaretPosition();
	    if (pos < textControl.getCharCount()) {
		textControl.setSelection(pos, pos + 1);
		textControl.insert(""); //$NON-NLS-1$
	    }
	}

	checkSelection();
	checkDeleteable();
	checkSelectable();
    }

    /**
     * The TextCellEditor implementation of this CellEditor method pastes the
     * the clipboard contents over the current selection.
     */
    public void performPaste() {
	textControl.paste();
	checkSelection();
	checkDeleteable();
	checkSelectable();
    }

    /**
     * The TextCellEditor implementation of this CellEditor method selects all
     * of the current text.
     */
    public void performSelectAll() {
	textControl.selectAll();
	checkSelection();
	checkDeleteable();
    }

    /**
     * This implementation of CellEditor.dependsOnExternalFocusListener()
     * returns false if the current instance's class is TextCellEditor, and true
     * otherwise. Subclasses that hook their own focus listener should override
     * this method and return false.
     * 
     * @return
     */
    protected boolean dependsOnExternalFocusListener() {
	return getClass() != SWTTextCellEditor.class;
    }

}
