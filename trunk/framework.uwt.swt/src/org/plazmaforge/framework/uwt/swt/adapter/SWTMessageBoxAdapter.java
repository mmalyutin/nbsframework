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

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.CallbackHandler;
import org.plazmaforge.framework.uwt.widget.CallbackResult;
import org.plazmaforge.framework.uwt.widget.MessageBox;

public class SWTMessageBoxAdapter extends SWTWidgetAdapter {

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
	    MessageDialog.openError(null, title, message);
	    if (handler != null) {
		CallbackResult result = new CallbackResult(true);
		handler.handle(result);
	    }
	} else if (MessageBox.METHOD_OPEN_INFORMATION.equals(methodName)) {
	    MessageDialog.openInformation(null, title, message);
	    if (handler != null) {
		CallbackResult result = new CallbackResult(true);
		handler.handle(result);
	    }
	} else if (MessageBox.METHOD_OPEN_WARNING.equals(methodName)) {
	    MessageDialog.openWarning(null, title, message);
	    if (handler != null) {
		CallbackResult result = new CallbackResult(true);
		handler.handle(result);
	    }
	} else if (MessageBox.METHOD_OPEN_QUESTION.equals(methodName)) {
	    boolean flag = MessageDialog.openQuestion(null, title, message);
	    if (handler != null) {
		CallbackResult result = new CallbackResult(flag);
		handler.handle(result);
	    }
	} else if (MessageBox.METHOD_OPEN_CONFIRM.equals(methodName)) {
	    boolean flag = MessageDialog.openConfirm(null, title, message);
	    if (handler != null) {
		CallbackResult result = new CallbackResult(flag);
		handler.handle(result);
	    }
	} else if (MessageBox.METHOD_OPEN_PROMPT.equals(methodName)) {
	    InputDialog dialog = new InputDialog(null, title, message, null, null);
	    boolean flag = false;
	    if (dialog.open() == Window.OK) {
		flag = true;
	    }
	    if (handler != null) {
		CallbackResult result = new CallbackResult(flag);
		result.setValue(dialog.getValue());
		handler.handle(result);
	    }
	}

	
	return null;
    }

}
