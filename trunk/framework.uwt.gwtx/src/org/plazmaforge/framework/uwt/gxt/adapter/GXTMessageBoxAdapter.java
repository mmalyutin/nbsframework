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

package org.plazmaforge.framework.uwt.gxt.adapter;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.CallbackHandler;
import org.plazmaforge.framework.uwt.widget.MessageBox;

import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.MessageBox.MessageBoxIcons;

/**
 * 
 * @author ohapon
 *
 */
public class GXTMessageBoxAdapter extends GXTWidgetAdapter {

    public static final MessageBoxIcons ICONS = com.sencha.gxt.widget.core.client.box.MessageBox.ICONS;
    
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
	title = toSafeString(title);
	message = toSafeString(message);
	
	// Convert message to HTML
	//message = toHtml(message);
	
	////
	
	if (MessageBox.METHOD_OPEN_ERROR.equals(methodName)) {
	    com.sencha.gxt.widget.core.client.box.MessageBox box = createMessageBox(title, message, handler);
            box.setPredefinedButtons(PredefinedButton.OK, PredefinedButton.CANCEL);
	    box.setIcon(ICONS.error());
	    box.show();
 	} else if (MessageBox.METHOD_OPEN_INFORMATION.equals(methodName)) {
	    com.sencha.gxt.widget.core.client.box.MessageBox box = createMessageBox(title, message, handler);
	    box.setPredefinedButtons(PredefinedButton.OK);
	    box.setIcon(ICONS.info());
	    box.show();
	} else if (MessageBox.METHOD_OPEN_WARNING.equals(methodName)) {
	    com.sencha.gxt.widget.core.client.box.MessageBox box = createMessageBox(title, message, handler);
	    box.setPredefinedButtons(PredefinedButton.OK);
	    box.setIcon(ICONS.warning());
	    box.show();
	} else if (MessageBox.METHOD_OPEN_QUESTION.equals(methodName)) {
	    com.sencha.gxt.widget.core.client.box.MessageBox box = createMessageBox(title, message, handler);
	    box.setPredefinedButtons(PredefinedButton.YES, PredefinedButton.NO);
	    box.setIcon(ICONS.question());
	    box.show();
	} else if (MessageBox.METHOD_OPEN_CONFIRM.equals(methodName)) {
	    com.sencha.gxt.widget.core.client.box.MessageBox box = createMessageBox(title, message, handler);
	    box.setPredefinedButtons(PredefinedButton.OK, PredefinedButton.CANCEL);
	    box.setIcon(ICONS.question());
	    box.show();
	} else if (MessageBox.METHOD_OPEN_PROMPT.equals(methodName)) {
	    com.sencha.gxt.widget.core.client.box.MessageBox box = createMessageBox(title, message, handler);
	    box.setPredefinedButtons(PredefinedButton.OK);
	    //box.setIcon(ICONS.question());
	    box.show();
	}
	    
	return null;
    }
    
    protected com.sencha.gxt.widget.core.client.box.MessageBox createMessageBox(String title, String message, CallbackHandler handler) {
	com.sencha.gxt.widget.core.client.box.MessageBox box = new com.sencha.gxt.widget.core.client.box.MessageBox(title, message);
	box.setModal(true);
	box.setClosable(true);
	
	//TODO: DISABLE:MIGRATION
	//if (handler != null) {
	//    box.addCallback(createCallback(handler, false));
	//}
	return box;
    }
    
    //TODO: DISABLE:MIGRATION
    /*
    private Listener<MessageBoxEvent> createCallback(final CallbackHandler handler, final boolean prompt) {
	Listener<MessageBoxEvent> callback = new Listener<MessageBoxEvent>() {
	    public void handleEvent(MessageBoxEvent e) {
		Button button = e.getButtonClicked();
		String action = button == null ? null : CallbackResult.normalizeConfirmAction(button.getItemId()); // Get ItemId like Action
		CallbackResult result = new CallbackResult(action);
		if (prompt) {
		    result.setValue(e.getValue());
		}
		handler.handle(result);
	    }
	};
	return callback;
    }
    */
    
 
    
}
