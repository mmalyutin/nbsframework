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

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Text Document with limit of text
 * 
 * @author ohapon
 *
 */
public class TextDocument extends PlainDocument {

    /**
     * Text limit (max length of text)
     */
    private int textLimit; 
    
    public TextDocument() {
	super();
    }

    public TextDocument(Content c) {
	super(c);
    }

    public TextDocument(int textLimit) {
	super();
	this.textLimit = textLimit;
    }
    
    public int getTextLimit() {
        return textLimit;
    }

    public void setTextLimit(int textLimit) {
        this.textLimit = textLimit;
    }

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
	if (str == null) {
	    // Default insert
	    super.insertString(offs, str, a);
	    return;
	}
	String currentText = getText(0, getLength());
	String beforeOffset = currentText.substring(0, offs);
	String afterOffset = currentText.substring(offs, currentText.length());
	String proposedResult = beforeOffset + str + afterOffset;
	
	// Check text limit (max length of text)
	if ((textLimit > 0) && (proposedResult.length() > textLimit)) {
	    super.remove(0, currentText.length());
	    super.insertString(0, proposedResult.substring(0, textLimit), a);
	    return;
	}
	
	// Default insert
	super.insertString(offs, str, a);
    }
}
