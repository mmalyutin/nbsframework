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

import org.eclipse.swt.widgets.Composite;

public class XNumberField extends XFormattedField {

    public XNumberField(Composite parent, int style) {
	super(parent, style);
    }
    
    protected String getDefaultPattern() {
	//TODO
	return getAdaptPattern("-#,###,##0.00"/*PlatformEnvironment.getNumberPattern()*/, true);
    }
    
    protected TextFormatter createFormatter(String pattern) {
	NumberFormatter formatter = new NumberFormatter(pattern);
	formatter.setDecimalSeparatorAlwaysShown(false);
	formatter.setFixedLengths(true, true);
	return formatter;
    }
    
    protected String getAdaptPattern(String pattern, boolean isExtend) {
	if (pattern == null) {
	    return null;
	}
	String newPattern = pattern.trim();
	if (newPattern.length() < 2) {
	    return newPattern;
	}
	if (newPattern.startsWith("-")) {
	    return newPattern;
	}
	if (!isExtend) {
	    return "-" + newPattern;
	}
	
	// Extend
	int digitCount = 0;
	boolean isGroup = false;
	for (int i = 0 ; i < newPattern.length(); i++) {
	    char ch = newPattern.charAt(i);
	    if (ch ==  '.') {
		break;
	    }
	    if (ch ==  ',') {
		isGroup = true;
	    }
	    if (ch ==  '#' || ch ==  '0') {
		digitCount++;
	    }

	}
	// TODO: STUB
	int okSize = 7;
	if (digitCount >= okSize) {
	    return "-" + newPattern;
	}
	StringBuffer preffix = new StringBuffer();
	int restSize = okSize - digitCount;
	// 1 and 4
	for (int i = 0; i < restSize; i++) {
	    if (isGroup && (i == 1 || i == 4)) {
		preffix.append(",");
	    }
	    preffix.append("#");
	}
	newPattern = "-" + preffix.toString() + newPattern;
	return newPattern;
    }
}
