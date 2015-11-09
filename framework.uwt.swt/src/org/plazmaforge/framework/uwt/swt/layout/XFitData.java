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

package org.plazmaforge.framework.uwt.swt.layout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;

public class XFitData {

    int defaultWidth = -1, defaultHeight = -1;
    int currentWhint, currentHhint, currentWidth = -1, currentHeight = -1;

    
    Point computeSize(Control control, int wHint, int hHint, boolean flushCache) {
	
	if (flushCache) {
	    flushCache();
	}
	
	if (wHint == SWT.DEFAULT && hHint == SWT.DEFAULT) {
	    if (defaultWidth == -1 || defaultHeight == -1) {
		Point size = control.computeSize(wHint, hHint, flushCache);
		defaultWidth = size.x;
		defaultHeight = size.y;
	    }
	    return new Point(defaultWidth, defaultHeight);
	}
	
	if (currentWidth == -1 || currentHeight == -1 || wHint != currentWhint || hHint != currentHhint) {
	    Point size = control.computeSize(wHint, hHint, flushCache);
	    currentWhint = wHint;
	    currentHhint = hHint;
	    currentWidth = size.x;
	    currentHeight = size.y;
	}
	
	return new Point(currentWidth, currentHeight);
    }

    void flushCache() {
	defaultWidth = defaultHeight = -1;
	currentWidth = currentHeight = -1;
    }

}
