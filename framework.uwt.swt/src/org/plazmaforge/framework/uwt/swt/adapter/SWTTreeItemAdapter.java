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

package org.plazmaforge.framework.uwt.swt.adapter;

import org.eclipse.swt.SWT;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.tree.Tree;

@Deprecated
public class SWTTreeItemAdapter extends SWTWidgetAdapter {

    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	if (parent instanceof Tree) {
	    org.eclipse.swt.widgets.Tree xParent = (org.eclipse.swt.widgets.Tree) parent.getDelegate();
	    org.eclipse.swt.widgets.TreeItem xTreeItem = new org.eclipse.swt.widgets.TreeItem(xParent, SWT.NONE);
	    return xTreeItem;
	}
	
	org.eclipse.swt.widgets.TreeItem xParent = (org.eclipse.swt.widgets.TreeItem) parent.getDelegate();
	org.eclipse.swt.widgets.TreeItem xTreeItem = new org.eclipse.swt.widgets.TreeItem(xParent, SWT.NONE);
	return xTreeItem;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	if (name == null) {
	    return;
	}
	org.eclipse.swt.widgets.TreeItem xTreeItem = (org.eclipse.swt.widgets.TreeItem) element.getDelegate();
	if (xTreeItem == null) {
	    return;
	}
	if (Control.PROPERTY_TEXT.equals(name)) {
	    xTreeItem.setText((String) value);
	    return;
	} else if (name.startsWith(Control.PROPERTY_TEXT + "@")) {
	    int index = Integer.valueOf(name.substring(name.indexOf("@") + 1));
	    xTreeItem.setText(index, (String) value);
	    return;
	}
	
    }

}
