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
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TypedListener;
import org.plazmaforge.framework.uwt.UWT;


/**
 * Abstract class for combo widgets composed of a <code>Text</code>, a
 * <code>Button</code> and a popup associated to the button.<p>
 * 
 * The creation of text, button and popup content is delegated to abstract
 * methods.<p>
 * 
 * Note that although this class is a subclass of <code>Composite</code>,
 * it does not make sense to add children to it, or set a layout on it.<p>
 * 
 * <dl>
 * <dt><b>Styles:</b>
 * <dd>BORDER, READ_ONLY, FLAT</dd>
 * <dt><b>Events:</b>
 * <dd>Selection</dd>
 * </dl>
 */
public abstract class AbstractCombo extends AbstractTextControl {

    /** Popup shell for the selection */
    protected Shell popup;

    /** Control opening the popup */
    protected Control popupControl;

    /** Content of the popup */
    protected Control popupContent;

    /** Flag indicating if the widget has focus or not */
    protected boolean hasFocus;

    /** The tool style (tool button) **/
    private int toolStyle;

    /** Text control event marker **/
    private final String TEXT_CONTROL_EVENT = "TEXT_CONTROL";

    /** Select event marker **/
    private final String SELECT_EVENT = "SELECT";
    


    // TODO: Stub
    private boolean forceUpdateListener = true;
    
    

    public AbstractCombo(Composite parent, int style) {
	this(parent, style, UWT.POPUP_BUTTON);
    }

    public AbstractCombo(Composite parent, int style, int toolStyle) {
	super(parent, style);
	this.toolStyle = toolStyle;
	initialize(style);
	
    }
    
    private void initialize(int style) {
		
	int textStyle = SWT.SINGLE;
	if ( (style & SWT.READ_ONLY) != 0 ) textStyle |= SWT.READ_ONLY;
	if ( (style & SWT.FLAT) != 0 ) textStyle |= SWT.FLAT;
	
	setEditable(false);
	
	// Create the Text widget
	createControl(textStyle);
	
	// Create the ToolBar
	createToolBar();
			
	if (getControl() != null) {
	    setTabList(new Control[] {getControl()});
	}
	
	// Create the Popup Shell
	popup = new Shell(getShell(), SWT.TOOL | SWT.ON_TOP);
	popup.setLayout(new FillLayout());
	
	// Create the Popup Content (List, Calendar and etc.)
	createPopupContent();
	popup.pack();
	
	setContainerBackground(getControl().getBackground());
	
	initListeners();
	
	////////////////////////////////////////////////
	//
	// TODO: Stub solution. Only for update listener
	//
	////////////////////////////////////////////////
	
	//[MUST-ENABLE]
	/*
	addSelectionListener(new SelectionAdapter() {
	    public void widgetSelected(SelectionEvent e) {
		if (!forceUpdateListener) {
		    return;
		}
		notifyUpdateListener(new UpdateEvent(SELECT_EVENT));
	    }
	});
	*/
    }
    
    
    @Override
    protected void addListeners() {
	addListener(SWT.Dispose, internalListener);
	addListener(SWT.Move, internalListener);
	addListener(SWT.Resize, internalListener);
	text.addListener(SWT.Modify, internalListener);
	
	if (!hasPopupControl()) {
	    return;
	}
	popupControl.addListener(SWT.Selection, internalListener);
	popupControl.addListener(SWT.FocusIn, internalListener);
	
	//[MUST-ENABLE]: This listeners duplicate in initPopupContentListener 
	//popup.addListener(SWT.Deactivate, internalListener);
	//popupContent.addListener(SWT.Selection, internalListener);

	initPopupContentListener();
    }
    
    
    @Override
    protected Listener createInternalListener() {
	Listener listener = new Listener () {
		public void handleEvent (Event event) {
			if ( popup == event.widget ) {
				popupEvent(event);
				return;
			}
			if ( text == event.widget ) {
				textEvent(event);
				return;
			}
			if ( popupContent == event.widget ) {
			    if (event.keyCode == SWT.ESC) { // FIXED OHA
				event.doit = false;
				dropDown(false);
				return;
			    }
			    contentEvent(event);
			    return;
			}
			if ( popupControl == event.widget ) {
				buttonEvent(event);
				return;
			}
			if ( AbstractCombo.this == event.widget ) {
				controlEvent(event);
				return;
			}
			if ( getShell() == event.widget ) {
				handleFocus(SWT.FocusOut);
			}
		}
	};
	return listener;
    }
    
    @Override
    protected Listener createExternalListener() {
	Listener listener = new Listener() {
	    public void handleEvent(Event event) {
		Shell shell = ((Control) event.widget).getShell();
		if (shell == AbstractCombo.this.getShell()) {
		    handleFocus(SWT.FocusOut);
		}
	    }
	};
	return listener;
    }
    
    ////
	
	
    protected boolean hasPopupControl() {
	return (toolStyle & UWT.POPUP_BUTTON) != 0;
    }

    /**
     * Create the ToolBar
     * 
     */
    protected void createToolBar() {

	if (hasPopupControl()) {

	    // Create the Popup control (Button arrow, image and etc.)
	    int buttonStyle = SWT.ARROW | SWT.DOWN;
	    if ((getStyle() & SWT.FLAT) != 0) {
		buttonStyle |= SWT.FLAT;
	    }
	    createPopupControl(buttonStyle);
	}

    }

    /**
     * Adds the listener to the collection of listeners who will be notified
     * when the receiver's text is modified, by sending it one of the messages
     * defined in the <code>ModifyListener</code> interface.
     * 
     * @param lsnr
     *            the listener which should be notified
     * @see ModifyListener
     * @see #removeModifyListener
     */
    public void addModifyListener(ModifyListener lsnr) {
	checkWidget();
	if (lsnr == null)
	    SWT.error(SWT.ERROR_NULL_ARGUMENT);
	TypedListener typedListener = new TypedListener(lsnr);
	addListener(SWT.Modify, typedListener);
    }

    /**
     * Adds the listener to the collection of listeners who will be notified
     * when the receiver's selection changes, by sending it one of the messages
     * defined in the <code>SelectionListener</code> interface.
     * <p>
     * <code>widgetSelected</code> is called when the combo's list selection
     * changes. <code>widgetDefaultSelected</code> is typically called when
     * ENTER is pressed the combo's text area.
     * </p>
     * 
     * @param lsnr
     *            the listener which should be notified
     * @see SelectionListener
     * @see #removeSelectionListener
     */
    public void addSelectionListener(SelectionListener lsnr) {
	checkWidget();
	if (lsnr == null)
	    SWT.error(SWT.ERROR_NULL_ARGUMENT);
	TypedListener typedListener = new TypedListener(lsnr);
	addListener(SWT.Selection, typedListener);
	addListener(SWT.DefaultSelection, typedListener);
    }

    /**
     * Called just before the popup is droppped. Override to execute actions
     * before the apparition of the popup.
     * <p>
     * 
     * By default, do nothing.
     */
    protected void beforeDrop() {
    }

    /**
     * Manages button events.
     * 
     * @param event
     *            event
     */
    protected void buttonEvent(Event event) {
	switch (event.type) {
	case SWT.FocusIn:
	    handleFocus(SWT.FocusIn);
	    break;
	case SWT.Selection:
	    dropDown(!isDropped());
	    break;
	}
    }

    /**
     * Manages global combo events.
     * 
     * @param event event
     */
    @Override
    protected void controlEvent(Event event) {
	switch (event.type) {
	case SWT.Dispose:
	    if (popup != null && !popup.isDisposed()) {
		popupContent.removeListener(SWT.Dispose, internalListener);
		popup.dispose();
	    }
	    Shell shell = getShell();
	    shell.removeListener(SWT.Deactivate, internalListener);
	    Display display = getDisplay();
	    display.removeFilter(SWT.FocusIn, filter);
	    popup = null;
	    text = null;
	    popupContent = null;
	    popupControl = null;
	    break;
	case SWT.Move:
	    dropDown(false);
	    break;
	case SWT.Resize:
	    internalLayout();
	    break;
	}
    }

    /**
     * Manages popup content events. SelectionEvent are notified to all
     * registered SelectionListeners of the combo.
     * 
     * @param event event
     */
    protected void contentEvent(Event event) {
	switch (event.type) {
	case SWT.Selection:
	    Event e = new Event();
	    e.time = event.time;
	    e.stateMask = event.stateMask;
	    e.doit = event.doit;
	    e.data = event.data;
	    notifyListeners(SWT.Selection, e);
	    event.doit = e.doit;
	    break;
	}
    }

    /**
     * Creates the arrow button widget. Override to change appearance or
     * behaviour of the button.
     * 
     * @param style
     *            button style
     */
    protected void createPopupControl(int style) {
	createButtonPopupControl(style);
    }

    protected void createButtonPopupControl(int style) {
	// style &= ~(SWT.ARROW | SWT.DOWN);
	popupControl = new Button(this, style | SWT.FLAT);
	// popupControl = new Button(this, SWT.ARROW);
    }

    /**
     * Creates the popup content. The popup is dependent of each implementation.
     * Content can be a <code>List</code>, a <code>Table</code> or every other
     * control.<br>
     * The <code>popupContent</code> attribute must be setted with the created
     * control. The parent must be the <code>shell</code> attribute, that is
     * initialized by default with a <code>FillLayout</code>.
     */
    protected abstract void createPopupContent();

    protected void initPopupContentListener() {
	int[] popupEvents = { SWT.Close, SWT.Paint, SWT.Deactivate };
	for (int i = 0; i < popupEvents.length; i++) {
	    popup.addListener(popupEvents[i], internalListener);
	}
	
	int[] listEvents = { SWT.MouseUp, SWT.Selection, SWT.Traverse,	SWT.KeyDown, SWT.KeyUp, SWT.FocusIn, SWT.Dispose };
	for (int i = 0; i < listEvents.length; i++) {
	    popupContent.addListener(listEvents[i], internalListener);
	}
	
    }

    protected int keyEnter;

    protected void handleKeyReleased(KeyEvent e) {

	// Call the popup window
	if ((e.keyCode == SWT.ARROW_DOWN || e.keyCode == SWT.ARROW_RIGHT)
		&& e.stateMask == SWT.ALT && hasPopupControl()) {
	    popupShow();
	    return;
	}

	if (isPushKeyCode(e.keyCode)) {
	    pushButton();
	}
    }

    protected boolean pushButton() {
	return popupShow();
    }

    protected boolean popupShow() {
	if (hasPopupControl()) {
	    dropDown(true);
	    return true;
	}
	return false;
    }

    protected boolean isEnterKeyCode(int keyCode) {
	return keyCode == SWT.CR;
    }

    protected boolean isPushKeyCode(int keyCode) {
	// Ignore key when editable mode
	if (getEditable()) {
	    return false;
	}
	return keyCode == 32;
    }

    /**
     * Manages drop down of the popup.
     * 
     * @param drop
     *            <code>true</code> to drop the popup, <code>false</code> to
     *            close
     */
    protected void dropDown(boolean drop) {
	if (drop == isDropped())
	    return;
	if (!drop) {
	    popup.setVisible(false);
	    if (!isDisposed() && isPopupControlFocus()) {
		text.setFocus();
	    }
	    return;
	}

	setPopupLocation();
	setPopupSize();
	beforeDrop();
	popup.setVisible(true);
	popupContent.setFocus();
    }

    @Override
    public int getStyle() {
	int style = super.getStyle();
	style &= ~SWT.READ_ONLY;
	if (!text.getEditable())
	    style |= SWT.READ_ONLY;
	return style;
    }

    /**
     * Manages the focus on the combo.
     * 
     * @param type SWT.FocusIn or SWT.FocusOut
     */
    protected void handleFocus(int type) {
	if (isDisposed())
	    return;
	switch (type) {
	case SWT.FocusIn: {
	    if (hasFocus)
		return;
	    if (getEditable())
		text.selectAll();
	    hasFocus = true;
	    Shell shell = getShell();
	    shell.removeListener(SWT.Deactivate, internalListener);
	    shell.addListener(SWT.Deactivate, internalListener);
	    Display display = getDisplay();
	    display.removeFilter(SWT.FocusIn, filter);
	    display.addFilter(SWT.FocusIn, filter);
	    Event e = new Event();
	    notifyListeners(SWT.FocusIn, e);
	    break;
	}
	case SWT.FocusOut: {
	    if (!hasFocus)
		return;
	    Control focusControl = getDisplay().getFocusControl();
	    if (focusControl == popupControl || focusControl == popupContent
		    || focusControl == text)
		return;
	    hasFocus = false;
	    Shell shell = getShell();
	    shell.removeListener(SWT.Deactivate, internalListener);
	    Display display = getDisplay();
	    display.removeFilter(SWT.FocusIn, filter);
	    Event e = new Event();
	    notifyListeners(SWT.FocusOut, e);
	    break;
	}
	}
    }

    /**
     * Returns <code>true</code> if popup is dropped (visible), else
     * <code>false</code>.
     * 
     * @return boolean indicating if popup is dropped
     */
    protected boolean isDropped() {
	return popup.getVisible();
    }

    @Override
    public boolean isFocusControl() {
	checkWidget();
	// if ( text.isFocusControl() || popupControl.isFocusControl()
	if (text.isFocusControl() || isPopupControlFocus()
		|| (popupContent != null && popupContent.isFocusControl())
		|| (popup != null && popup.isFocusControl())) {
	    return true;
	}
	return super.isFocusControl();
    }

    /**
     * Manages popup shell events.
     * 
     * @param event event
     */
    protected void popupEvent(Event event) {
	switch (event.type) {
	case SWT.Deactivate:
	    dropDown(false);
	    break;
	}
    }

    /**
     * Removes the listener from the collection of listeners who will be
     * notified when the receiver's text is modified.
     * 
     * @param lsnr the listener which should no longer be notified
     * @see ModifyListener
     * @see #addModifyListener
     */
    public void removeModifyListener(ModifyListener lsnr) {
	checkWidget();
	if (lsnr == null)
	    SWT.error(SWT.ERROR_NULL_ARGUMENT);
	removeListener(SWT.Modify, lsnr);
    }

    /**
     * Removes the listener from the collection of listeners who will be
     * notified when the receiver's selection changes.
     * 
     * @param lsnr  the listener which should no longer be notified
     * @see SelectionListener
     * @see #addSelectionListener
     */
    public void removeSelectionListener(SelectionListener lsnr) {
	checkWidget();
	if (lsnr == null)
	    SWT.error(SWT.ERROR_NULL_ARGUMENT);
	removeListener(SWT.Selection, lsnr);
	removeListener(SWT.DefaultSelection, lsnr);
    }

    @Override
    public void setBackground(Color color) {
	super.setBackground(color);
	if (popupContent != null)
	    popupContent.setBackground(color);
    }

    @Override
    public void setEnabled(boolean enabled) {
	super.setEnabled(enabled);
	if (popup != null)
	    popup.setVisible(false);
	if (popupControl != null)
	    setPopupControlEnabled(enabled);
    }

    @Override
    public void setFont(Font font) {
	super.setFont(font);
	popupContent.setFont(font);
	pack();
	popup.pack();
    }

    @Override
    public void setForeground(Color color) {
	super.setForeground(color);
	if (popupContent != null)
	    popupContent.setForeground(color);
    }

    /**
     * Sets the layout which is associated with the receiver to be the argument
     * which may be null.
     * <p>
     * Note : No Layout can be set on this Control because it already manages
     * the size and position of its children.
     * </p>
     * 
     * @param layout the receiver's new layout or null
     */
    @Override
    public void setLayout(Layout layout) {
	checkWidget();
	return;
    }

    /**
     * Calculates and returns the location of popup.<br>
     * Called just before than the popup is dropped.
     */
    protected void setPopupLocation() {
	Rectangle r = getBounds();
	Point p = Display.getCurrent().map(this, null, 0, r.height);
	popup.setLocation(p.x, p.y);
    }

    protected void setPopupSize() {

    }

    @Override
    public void setVisible(boolean visible) {
	super.setVisible(visible);
	if (!visible && popup != null && !popup.isDisposed()) {
	    popup.setVisible(false);
	}
	    
    }

    /**
     * Manages text widget events. ModifyEvent are notified to all registered
     * ModifyListener of the combo.
     * 
     * @param event event
     */
    protected void textEvent(Event event) {
	switch (event.type) {
	case SWT.Modify:
	    Event e = new Event();
	    e.time = event.time;
	    e.data = TEXT_CONTROL_EVENT;
	    notifyListeners(SWT.Modify, e);
	    break;
	}
    }

    /**
     * Returns true if <code>TypedEvent</code> is marked by TEXT
     * 
     * @param event
     * @return
     */
    public boolean isTextControlEvent(TypedEvent event) {
	if (event == null) {
	    return false;
	}
	return TEXT_CONTROL_EVENT.equals(event.data);
    }

    protected int getToolStyle() {
	return toolStyle;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Popup Control methods
    // -----------------------------------------------------------------------------------------------------------------

    protected boolean isPopupControlFocus() {
	if (popupControl == null) {
	    return false;
	}
	return popupControl.isFocusControl();
    }

    protected void setPopupControlEnabled(boolean enabled) {
	if (popupControl == null) {
	    return;
	}
	popupControl.setEnabled(enabled);
    }

    protected Control[] getToolButtons() {
	return new Control[] { popupControl };
    }

    protected Point computeToolButtonSize(int wHint, int hHint, boolean changed) {
	int h = hHint;
	// TODO: STUB
	if (h < getFixHeight() + 2) {
	    h = getFixHeight() + 2;
	}
	return new Point(h, h);
    }

    @Override
    public Point computeSize(int wHint, int hHint, boolean changed) {
	checkWidget();
	int width = 0;
	int height = 0;
	int borderWidth = getBorderWidth();
	int borderDouble = 2 * borderWidth;

	Point textSize = getControl().computeSize(wHint, -1, changed);
	

	Control[] buttons = getToolButtons();
	int buttonCount = buttons == null ? 0 : buttons.length;

	int toolBarHeight = 0;
	int toolBarWidth = 0;

	if (buttonCount > 0) {
	    for (int i = 0; i < buttonCount; i++) {
		Control button = buttons[i];
		if (button == null || button.isDisposed()) {
		    continue;
		}
		// Point buttonSize = button.computeSize(-1, -1, changed);
		Point buttonSize = computeToolButtonSize(wHint, hHint, changed);
		toolBarHeight = Math.max(toolBarHeight, buttonSize.y);
		toolBarWidth += buttonSize.x;
	    }
	}

	
	height = Math.max(textSize.y, toolBarHeight) + borderDouble;
	width = wHint > 0 ? textSize.x : textSize.x + toolBarWidth + borderDouble; // (?)
	
	height = Math.max(hHint, height);
	width = Math.max(wHint, width);
	
	Point size = new Point(width, height);
	
	// PREFERRED SIZE
	if (wHint == -1 && getPreferredWidth() > 0) {
  	    size.x = getPreferredWidth();
  	}
  	
  	if (hHint == -1 && getPreferredHeight() > 0) {
  	    size.y = getPreferredHeight();
  	}
  	return size;
    }

    @Override
    protected void internalLayout() {
	if (isDropped()) {
	    dropDown(false);
	}
	Rectangle rect = getClientArea();
	int width = rect.width;
	int height = rect.height;
	Control[] buttons = getToolButtons();
	int buttonCount = buttons == null ? 0 : buttons.length;
	Point[] buttonSizes = null;
	int toolBarWidth = 0;

	if (buttonCount > 0) {
	    buttonSizes = new Point[buttonCount];
	    for (int i = 0; i < buttonCount; i++) {
		Control button = buttons[i];
		if (button == null || button.isDisposed()) {
		    continue;
		}
		// buttonSizes[i] = button.computeSize(-1, height);
		buttonSizes[i] = computeToolButtonSize(height, height, false);
		toolBarWidth += buttonSizes[i].x;
	    }
	}

	getControl().setBounds(rect.x, rect.y, width - toolBarWidth, height);

	if (buttonSizes == null) {
	    return;
	}
	int len = toolBarWidth;
	for (int i = 0; i < buttonCount; i++) {
	    if (buttonSizes[i] == null) {
		continue;
	    }
	    buttons[i].setBounds((rect.x + width) - len, rect.y, buttonSizes[i].x, buttonSizes[i].y);
	    len -= buttonSizes[i].x;
	}
    }

    protected void setToolControlImage(Control control, Image image) {
	if (control == null || image == null) {
	    return;
	}
	if (control instanceof Label) {
	    ((Label) control).setImage(image);
	} else if (control instanceof Button) {
	    ((Button) control).setImage(image);
	}
	// [MUST-ENABLE]
	// else if (control instanceof ToolButton) {
	// ((ToolButton) control).setImage(image);
	// }

    }
    
    
    //[MUST-ENABLE]
    /*
    public String getDisplayText(Object value) {
	return getModel().getDisplayText(value);
    }

    public String getFormatText(Object value) {
	return getModel().getFormatText(value);
    }

    public Object getDisplayValue(Object value) {
	return getModel().getDisplayValue(value);
    }

    public java.util.List<DataFilter> getDataFilters() {
	if (dataFilters == null) {
	    dataFilters = new ArrayList<DataFilter>();
	}
	return dataFilters;
    }

    public void addDataFilter(DataFilter dataFilter) {
	getDataFilters().add(dataFilter);
    }

    public boolean hasDataFilters() {
	return dataFilters != null && !dataFilters.isEmpty();
    }
    */
    
    
    
}
