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

package org.plazmaforge.framework.uwt.swing.widget;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class XScrollPane extends JScrollPane implements  IXComponent  {

    
    /**
     * Preferred width
     */
    private int preferredWidth;
    
    /**
     * Preferred height
     */
    private int preferredHeight;
    
    
    
    public XScrollPane() {
	super();
    }

    public XScrollPane(Component view) {
	super(view);
    }

    
    public int getPreferredWidth() {
        return preferredWidth;
    }

    public void setPreferredWidth(int preferredWidth) {
	if (preferredWidth < 0) {
	    throw new IllegalArgumentException("PreferredWidth must be >= 0");
	}
        this.preferredWidth = preferredWidth;
    }

    public int getPreferredHeight() {
        return preferredHeight;
    }

    public void setPreferredHeight(int preferredHeight) {
	if (preferredHeight < 0) {
	    throw new IllegalArgumentException("PreferredHeight must be >= 0");
	}
        this.preferredHeight = preferredHeight;
    }

    public Dimension getPreferredSize() {
	Dimension size =  null;
	JViewport viewport = getViewport();
	if (viewport != null) {
	    Component view = viewport.getView();
	    if (view != null) {
		size = view.getPreferredSize();
	    }
	}
	if (size  == null) {
	    size = super.getPreferredSize();
	}
	if (preferredWidth > 0) {
	    size.width = preferredWidth;
	}
	if (preferredHeight > 0) {
	    size.height = preferredHeight;
	}
	return size;
    }
    
    @Override
    public Dimension getMinimumSize() {
	return getPreferredSize();
    }

}
