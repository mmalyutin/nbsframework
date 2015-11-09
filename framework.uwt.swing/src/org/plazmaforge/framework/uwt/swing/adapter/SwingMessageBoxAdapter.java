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

package org.plazmaforge.framework.uwt.swing.adapter;

import javax.swing.JOptionPane;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.CallbackHandler;
import org.plazmaforge.framework.uwt.widget.CallbackResult;
import org.plazmaforge.framework.uwt.widget.MessageBox;

public class SwingMessageBoxAdapter extends SwingWidgetAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	//TODO:
	return null;
    }
    
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	
	//TODO: 
	int i = 0;
	
	String title = null;
	String message = null;
	CallbackHandler handler = null;
	
	for (Object arg: args) {
	    if (i == 0) {
		title = (String) arg;
	    } else if (i == 1) {
		message = (String) arg;
	    } else if (i == 2) {
		handler = (CallbackHandler) arg;
	    }
	    i++;
	}
	
	////
	
	if (MessageBox.METHOD_OPEN_ERROR.equals(methodName)) {
	    JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	    if (handler != null) {
		CallbackResult result = new CallbackResult(true);
		handler.handle(result);
	    }
	} else if (MessageBox.METHOD_OPEN_INFORMATION.equals(methodName)) {
	    JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	    if (handler != null) {
		CallbackResult result = new CallbackResult(true);
		handler.handle(result);
	    }
	} else if (MessageBox.METHOD_OPEN_WARNING.equals(methodName)) {
	    JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
	    if (handler != null) {
		CallbackResult result = new CallbackResult(true);
		handler.handle(result);
	    }
	} else if (MessageBox.METHOD_OPEN_QUESTION.equals(methodName)) {
	    int option = JOptionPane.showOptionDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
	    boolean confirm = isConfirm(option);
	    if (handler != null) {
		CallbackResult result = new CallbackResult(confirm);
		handler.handle(result);
	    }
	} else if (MessageBox.METHOD_OPEN_CONFIRM.equals(methodName)) {
	    int option = JOptionPane.showOptionDialog(null, message, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
	    boolean confirm = isConfirm(option);
	    if (handler != null) {
		CallbackResult result = new CallbackResult(confirm);
		handler.handle(result);
	    }
	} else if (MessageBox.METHOD_OPEN_PROMPT.equals(methodName)) {
	    String value = JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
	    boolean confirm = value != null;
	    if (handler != null) {
		CallbackResult result = new CallbackResult(confirm);
		result.setValue(value);
		handler.handle(result);
	    }
	}


	return null;
    }

    private boolean isConfirm(int option) {
	return option == JOptionPane.OK_OPTION || option == JOptionPane.YES_OPTION;
    }
}
