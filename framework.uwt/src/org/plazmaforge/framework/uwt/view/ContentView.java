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

package org.plazmaforge.framework.uwt.view;

import org.plazmaforge.framework.uwt.layout.FitLayout;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Layout;


/**
 * Simple content view
 * 
 * @author ohapon
 *
 */
public class ContentView<T> extends View implements IContentView<T> {

    @Override
    protected Composite createContent() {
	Composite content = super.createContent();
	content.setLayout(createContentLayout());
	
	Control control = getControl();
	if (control == null) {
	    control = createControl();
	    setControl(control);
	}
	addControlToContent(content, control);
	return content;
    }

    protected Control createControl() {
	return null;
    }
    
    protected void addControlToContent(Composite content, Control control) {
	if (control == null) {
	    return;
	}
	content.add(control);
    }
    
    protected Layout createContentLayout() {
	return new FitLayout();
    }

    
}
