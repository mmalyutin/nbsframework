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

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.text.Document;


public class XTextField extends JTextField implements IXField {

    
    /**
     * True if trim text
     */
    private boolean textTrim;

    /**
     * Text limit (max length of text)
     */
    private int textLimit;

    /**
     * Preferred width
     */
    private int preferredWidth;
    
    /**
     * Preferred height
     */
    private int preferredHeight;
    
    
    
    
    public XTextField() {
	super();
	initialize();
    }

    public XTextField(String str) {
	super(str);
	initialize();
    }

    public XTextField(int columns) {
	super(columns);
	initialize();
    }

    public XTextField(String text, int columns) {
	super(text, columns);
	initialize();
    }

    public XTextField(Document doc, String text, int columns) {
	super(doc, text, columns);
	initialize();
    }

    private void initialize() {
	setPreferredWidth(DEFAULT_FIELD_WIDTH);
	textTrim = true;
	addFocusListener(new FocusListener() {
	    public void focusGained(FocusEvent evt) {
		// selectAll();
	    }

	    public void focusLost(FocusEvent evt) {
		// setText(getText());
		// select(0, 0);
		// setCaretPosition(0);
	    }
	});

    }

    public int getTextLimit() {
        return textLimit;
    }

    public void setTextLimit(int textLimit) {
        this.textLimit = textLimit;
        Document doc = getDocument();
        if (doc == null) {
            //Crazy case
            return;
        }
        // Transfer textLimit to Document
        if (doc instanceof TextDocument){
            ((TextDocument) doc).setTextLimit(textLimit); 
        }
    }

    public boolean isTextTrim() {
	return textTrim;
    }

    public void setTextTrim(boolean textTrim) {
	this.textTrim = textTrim;
    }


    public void setText(String text) {
	if (textTrim && text != null) {
	    super.setText(text.trim());
	} else {
	    super.setText(text);
	}
	setCaretPosition(0);
    }

    public String getText() {
	if (textTrim) {
	    return super.getText().trim();
	}
	return super.getText();
    }

    protected Document createDefaultModel() {
	return new TextDocument(textLimit);
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
	Dimension size = super.getPreferredSize();
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

    @Override
    public Object getFieldValue() {
	return getText();
    }

    @Override
    public void setFieldValue(Object value) {
	setText((String) value);
    }
    
    

}
