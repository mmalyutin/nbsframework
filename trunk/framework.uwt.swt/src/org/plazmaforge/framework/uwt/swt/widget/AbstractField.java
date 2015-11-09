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
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;

public abstract class AbstractField extends Composite implements IXField {

    private Object value;
    
    /** Listener for all internal events */
    protected Listener internalListener;
	
    
    /** Listener for external events */
    protected Listener filter;
	

    /**
     * Preferred width
     */
    private int preferredWidth;
    
    /**
     * Preferred height
     */
    private int preferredHeight;
    
    
    
    
    public AbstractField(Composite parent) {
   	super(parent, SWT.BORDER);
   	initialize();
    }
    
    public AbstractField(Composite parent, int style) {
	super(parent, style);
	initialize();
    }
    
    
    private void initialize() {
	setPreferredWidth(DEFAULT_FIELD_WIDTH);
    }

    public Object getValue() {
        return doGetValue();
    }

    public void setValue(Object value) {
	doSetValue(value);
    }
    

    protected Object doGetValue() {
        return value;
    }

    protected void doSetValue(Object value) {
        this.value = value;
    }

    /**
     * Create control
     * @param style
     */
    protected abstract void createControl(int style);
    
    /**
     * Return real control
     * @return
     */
    public abstract Control getControl();
    
    /**
     * Dispose control
     */
    protected abstract void disposeControl();
    
    
    protected boolean hasControl() {
	return getControl() != null;
    }
    
    
    @Override
    public boolean setFocus() {
	checkWidget();
	if (!hasControl()) {
	    return super.setFocus();
	}
        return getControl().setFocus();
    }
    
    @Override
    public void setToolTipText(String string) {
	checkWidget();
	if (!hasControl()) {
	    super.setToolTipText(string);
	}
	getControl().setToolTipText(string);
    }

    
    @Override
    public String getToolTipText() {
	checkWidget();
	if (!hasControl()) {
	    super.getToolTipText();
	}
	return getControl().getToolTipText();
    }

    
    public void addFocusListener(FocusListener listener) {
	if (!hasControl()) {
	    super.addFocusListener(listener);
	}
	getControl().addFocusListener(listener);
    }

    public void removeFocusListener(FocusListener listener) {
	if (!hasControl()) {
	    super.removeFocusListener(listener);
	}
	getControl().removeFocusListener(listener);
    }
    
    
    @Override
    public void setBackground(Color color) {
	super.setBackground(color);
	if (hasControl())
	    getControl().setBackground(color);
    }

    @Override
    public void setEnabled(boolean enabled) {
	super.setEnabled(enabled);
	if (hasControl()) {
	    getControl().setEnabled(enabled);
	}
    }

    @Override
    public void setFont(Font font) {
	super.setFont(font);
	if (hasControl()) {
	    getControl().setFont(font);
	}
	
    }

    @Override
    public void setForeground(Color color) {
	super.setForeground(color);
	if (hasControl()) {
	    getControl().setForeground(color);
	}
    }
    
    public void setLayout(Layout layout) {
   	checkWidget();
   	// Ignore layout
    }
       
    
    @Override
    public Point computeSize(int wHint, int hHint, boolean changed) {
  	checkWidget();
  	if (!hasControl()) {
  	    return super.computeSize(wHint, hHint, changed);
  	}
  	int borderWidth = getBorderWidth();
  	Point textSize = getControl().computeSize(/*wHint*/-1, -1, changed); // TODO: wHint was disabled because it has problem with compute width
  	int height = Math.max(hHint, textSize.y + 2 * borderWidth);
  	int width = Math.max(wHint, textSize.x + 2 * borderWidth);
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
    
    /**
     * Layout components
     */
    protected void internalLayout() {
	if (!hasControl()) {
	    return;
	}
	Rectangle rect = getClientArea();
	//int borderWidth = getBorderWidth();
	//getControl().setBounds(rect.x - borderWidth, rect.y - borderWidth, rect.width + borderWidth * 2, rect.height + borderWidth * 2);
	getControl().setBounds(rect.x, rect.y, rect.width, rect.height);
    } 
    
    /**
     * Initialize all listeners
     */
    protected void initListeners() {
	internalListener = createInternalListener();
	filter = createExternalListener();
	
	// Add all listeners
	addListeners();
    }
    
    protected void addListeners() {
   	addListener(SWT.Dispose, internalListener);
	addListener(SWT.Resize, internalListener);
    }
    
    /**
     * Create internal listener
     * @return
     */
    protected Listener createInternalListener() {
	Listener listener = new Listener() {
	    public void handleEvent(Event event) {
		if (AbstractField.this == event.widget) {
		    controlEvent(event);
		    return;
		}
	    }
	};
	return listener;
    }
    
    /**
     * Create external listener
     * @return
     */
    protected Listener createExternalListener() {
	return null;
    }
    
    /**
     * General control event
     * @param event
     */
    protected void controlEvent(Event event) {
	switch (event.type) {
	case SWT.Dispose :
	    disposeControl();
	case SWT.Resize :
	    internalLayout();
	}
    }
    
    protected void setContainerBackground(Color color) {
  	super.setBackground(color);
    }
    
    protected int getFixHeight() {
	return 15; // TODO: STUB
    }

    public int getPreferredWidth() {
        return preferredWidth;
    }

    public void setPreferredWidth(int preferredWidth) {
        this.preferredWidth = preferredWidth;
    }

    public int getPreferredHeight() {
        return preferredHeight;
    }

    public void setPreferredHeight(int fixedHeight) {
        this.preferredHeight = fixedHeight;
    }	

}
