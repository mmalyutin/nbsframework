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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Scrollable;
import org.plazmaforge.framework.util.StringUtils;


/**
 * Text scrolling area (box)
 * 
 * @author ohapon
 *
 */
public class XTextArea extends XTextField {

    

    public XTextArea(Composite parent) {
	super(parent);
	initialize(SWT.BORDER);
    }

    public XTextArea(Composite parent, int style) {
	super(parent, style);
	initialize(style);
    }

    private void initialize(int style) {
	if ((style & SWT.H_SCROLL) == 0) {
	    style |= SWT.H_SCROLL;
	}
	if ((style & SWT.V_SCROLL) == 0) {
	    style |= SWT.V_SCROLL;
	}
	createControl(style);
	initListeners();
	initTextScrollBars();
	//setPreferredWidth(100);
    }
    
    private void initTextScrollBars() {
	if (text == null) {
	    return;
	}
	text.getHorizontalBar().setVisible(false);
	text.getVerticalBar().setVisible(false);
	
	text.addListener(SWT.Resize, new Listener() {
	    public void handleEvent (Event event)  {
		changeScrollBarsState(text);
	    }
	});
	text.addListener(SWT.Modify, new Listener() {
	    public void handleEvent (Event event)  {
		changeScrollBarsState(text);
	    }
	});
    }
    
    private void changeScrollBarsState(Scrollable scrollable) {
	Rectangle clientRec = scrollable.getClientArea();
	
	ScrollBar hBar = scrollable.getHorizontalBar();
	ScrollBar vBar = scrollable.getVerticalBar();
	
	Point hBarSize = hBar.getSize();
	Point vBarSize = vBar.getSize();
	
	Point p = scrollable.computeSize(SWT.DEFAULT, SWT.DEFAULT);
	
	// WARNING !!! Must analyze
	//Point p = super.computeSize(SWT.DEFAULT, SWT.DEFAULT);
	
	int borderWidth = scrollable.getBorderWidth() * 2;
	
	int hBarWidh = clientRec.width + vBarSize.x + borderWidth + magicWidth; // WARNING !!! We use magicWidth ONLY FOR HorizontalBar
	int vBarWidh = clientRec.height + hBarSize.y + borderWidth;
	
	hBar.setVisible(p.x > hBarWidh);
	vBar.setVisible(p.y > vBarWidh);
    }

    private int magicWidth = 3; // TODO: Must analyze. Why 3px
    
    @Override
    public Point computeSize(int wHint, int hHint, boolean changed) {
	checkWidget();
	Point size = super.computeSize(wHint, hHint, changed);
	
	ScrollBar hBar = text.getHorizontalBar();
	ScrollBar vBar = text.getVerticalBar();
	
	
	// START POINT 2. WARNIING !!! It work only with POINT 1.
	Point hBarSize = hBar.getSize();
	Point vBarSize = vBar.getSize();
	int borderWidth = getBorderWidth();
	if (!hBar.isVisible()) {
	    size.y -= (hBarSize.y + borderWidth * 2);
	}
	if (!vBar.isVisible()) {
	    size.x -= (vBarSize.x + borderWidth * 2 + magicWidth);
	}
	// END POINT 2 ///////////////////////////////////////////
	
	if (wHint == -1 && getPreferredWidth() > 0) {
	    size.x = getPreferredWidth();
	}
	
	if (hHint == -1 && getPreferredHeight() > 0) {
	    size.y = getPreferredHeight(); 
	} else {
	    size.y *= 3;
	}
	
	return size;
    }
    
    /**
     * Layout components
     */
    @Override
    protected void internalLayout() {
	// WARNIING !! Must analyze
	
	if (!hasControl()) {
	    return;
	}
	Rectangle rect = getClientArea();
	
	
	// START POINT 1
	int borderWidth = getBorderWidth();
	getControl().setBounds(rect.x - borderWidth, rect.y - borderWidth, rect.width + borderWidth * 2, rect.height + borderWidth * 2);
	// END POINT 1 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// WARNING !!! If you disable POINT 1 you must disable POINT 2
	
	//getControl().setBounds(rect.x, rect.y, rect.width, rect.height);
    } 

    @Override
    public String getText() {
	String text = super.getText();
	// Normalize string
	// TODO: What about MacOS :)
	text = StringUtils.replaceAll(text, "\r", "");
	return text;
    }
}
