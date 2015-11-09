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
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;

public abstract class AbstractTextControl extends AbstractField {

    public static final int DEFAULT_TEXT_LIMIT = 255;
    
    
    protected Text text;
    
    private boolean trimText = true;
    
    private boolean isEditableText = true;

    public AbstractTextControl(Composite parent) {
	super(parent);
    }

    public AbstractTextControl(Composite parent, int style) {
	super(parent, style);
    }
    
    protected void createControl(int style) {
   	text = new Text(this, style);
   	text.setTextLimit(DEFAULT_TEXT_LIMIT);
	//setContainerBackground(text.getBackground());
    }
    
    @Override
    protected void addListeners() {
	super.addListeners();
	text.addListener(SWT.Modify, internalListener);
    }
    
    public Control getControl() {
	return text;
    }

    public Text getTextControl() {
	return (Text) getControl();
    }

    public void setEchoChar(char ch)  {
	text.setEchoChar(ch);
    }
    
    public char getEchoChar() {
	return text.getEchoChar();
    }
    
    protected void disposeControl() {
	text = null;
    }
    
    public void setText(String str) {
	if (str == null) {
	    text.setText("");
	    return;
	}
	text.setText(!trimText ? str : str.trim());
    }

    public void setTextLimit(int limit) {
	text.setTextLimit(limit);
    }
    
    public int getTextLimit() {
	return text.getTextLimit();
    }
    
    public boolean isTrimText() {
        return trimText;
    }

    public void setTrimText(boolean trimText) {
        this.trimText = trimText;
    }
    
    

    public boolean getEditable() {
	checkWidget();
	return this.isEditableText;
    }

    public void setEditable(boolean editable) {
	checkWidget();
	if (text != null) text.setEditable(editable);
	this.isEditableText = editable;
    }    

    public Point getSelection() {
        checkWidget();
        return text.getSelection();
    }

    public void setSelection(Point selection) {
        checkWidget();
        if ( selection == null ) SWT.error (SWT.ERROR_NULL_ARGUMENT);
        text.setSelection(selection.x, selection.y);
    }
    
    
    /**
     * Returns a string containing a copy of the contents of the receiver's text
     * field.
     *
     * @return the receiver's text
     */
    public String getText() {
	checkWidget();
	String str = text.getText(); 
        return str == null || !trimText ? str : str.trim();
    }

    /**
      * Returns the height of the receivers's text field.
      *
      * @return the text height
      */
    public int getTextHeight() {
         checkWidget();
         return text.getLineHeight();
    }
    
     
    public void addVerifyListener(VerifyListener listener) {
	text.addVerifyListener(listener);
    }

    public void removeVerifyListener(VerifyListener listener) {
	text.removeVerifyListener(listener);
    }
    
    public void setValue(Object value) {
	setText((String) value);
    }
    
    public Object getValue() {
	return getText();
    }
 
    protected void initModifyListener() {
	text.addModifyListener(new ModifyListener() {
	    public void modifyText(ModifyEvent e) {
		//[MUST-ENABLE]
		//setModify(true);
            }
	});		
    }

    protected void initFocusListener() {
	text.addFocusListener(new FocusAdapter() {
	    public void focusGained(FocusEvent e) {
		handleFocusGained(e);
            }
	});		
    }
    
    protected void handleFocusGained(FocusEvent e) {
	//[MUST-ENABLE]
	//if (Environment.isSelectValueWhenFocus()) {
	//    ((Text) e.getSource()).selectAll();
	//}	
    }
    
    protected void initKeyListener() {
	text.addKeyListener(new KeyListener() {
	    public void keyPressed(KeyEvent e) {
		handleKeyPressed(e);
	    }
	    public void keyReleased(KeyEvent e) {
		handleKeyReleased(e);
	    }
	});	
    }
    
    protected void handleKeyPressed(KeyEvent e) {
	if (isEditableText) {
	    return;
	}
	e.doit = false;
    }
	
    //protected void handleKeyPressed(KeyEvent e) {
	/*
	String string = "";
	switch (e.character) {
	    case 0: string += " '\\0'"; break;
	    case SWT.BS: string += " '\\b'"; break;
	    case SWT.CR: string += " '\\r'"; break;
	    case SWT.DEL: string += " DEL"; break;
	    case SWT.ESC: string += " ESC"; break;
	    case SWT.LF: string += " '\\n'"; break;
	    default: string += " '" + e.character +"'";
	    break;
	}
	System.out.println (string);
	*/
    //}
    
    protected void handleKeyReleased(KeyEvent e) {

    }

    

    public void notifyTextListeners(int eventType, Event event) {
	if (text == null) {
	    return;
	}
	text.notifyListeners(eventType, event);
    }
      
}
