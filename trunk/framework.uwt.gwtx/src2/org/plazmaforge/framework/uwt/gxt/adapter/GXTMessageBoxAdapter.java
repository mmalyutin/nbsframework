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
import org.plazmaforge.framework.uwt.widget.CallbackResult;
import org.plazmaforge.framework.uwt.widget.MessageBox;

import com.sencha.gxt.ui.client.event.Listener;
import com.sencha.gxt.ui.client.event.MessageBoxEvent;
import com.sencha.gxt.widget.core.client.button.Button;

//import com.google.gwt.user.client.Timer;

public class GXTMessageBoxAdapter extends GXTWidgetAdapter {

    
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
	
	// Convert message to HTML
	message = toHtml(message);
	
	////
	
	if (MessageBox.METHOD_OPEN_ERROR.equals(methodName)) {
	   com.sencha.gxt.widget.core.client.MessageBox box = newcom.sencha.gxt.widget.core.client.MessageBox();
	    box.setModal(true);
	    box.setTitle(title);
	    box.setMessage(message);
	    box.setButtons(com.sencha.gxt.widget.core.client.MessageBox.OK);
	    box.setIcon(com.sencha.gxt.widget.core.client.MessageBox.ERROR);
	    
//	    final WaitObject wo = new WaitObject();
//	    box.addCallback(new Listener<MessageBoxEvent>( ) {
//		public void handleEvent(MessageBoxEvent be) {
//		    wo.cancel();
//		}
//	    });
	    if (handler != null) {
		box.addCallback(createCallback(handler, false));
	    }
	    box.show();
//	    wo.waitProcess();
	    
	} else if (MessageBox.METHOD_OPEN_INFORMATION.equals(methodName)) {
	    //com.sencha.gxt.widget.core.client.MessageBox.info(title, message, null);
	    
	   com.sencha.gxt.widget.core.client.MessageBox box = newcom.sencha.gxt.widget.core.client.MessageBox();
	    box.setModal(true);
	    box.setTitle(title);
	    box.setMessage(message);
	    box.setButtons(com.sencha.gxt.widget.core.client.MessageBox.OK);
	    box.setIcon(com.sencha.gxt.widget.core.client.MessageBox.INFO);
	    if (handler != null) {
		box.addCallback(createCallback(handler, false));
	    }
	    box.show();

	} else if (MessageBox.METHOD_OPEN_WARNING.equals(methodName)) {
	    //com.sencha.gxt.widget.core.client.MessageBox.alert(title, message, null);
	    
	   com.sencha.gxt.widget.core.client.MessageBox box = newcom.sencha.gxt.widget.core.client.MessageBox();
	    box.setModal(true);
	    box.setTitle(title);
	    box.setMessage(message);
	    box.setButtons(com.sencha.gxt.widget.core.client.MessageBox.OK);
	    box.setIcon(com.sencha.gxt.widget.core.client.MessageBox.WARNING);
	    if (handler != null) {
		box.addCallback(createCallback(handler, false));
	    }	    
	    box.show();

	    
	} else if (MessageBox.METHOD_OPEN_QUESTION.equals(methodName)) {
	    //com.sencha.gxt.widget.core.client.MessageBox.confirm(title, message, null);
	    
	   com.sencha.gxt.widget.core.client.MessageBox box = newcom.sencha.gxt.widget.core.client.MessageBox();
	    box.setModal(true);
	    box.setTitle(title);
	    box.setMessage(message);
	    box.setButtons(com.sencha.gxt.widget.core.client.MessageBox.YESNO);
	    box.setIcon(com.sencha.gxt.widget.core.client.MessageBox.QUESTION);
	    if (handler != null) {
		box.addCallback(createCallback(handler, false));
	    }
	    box.show();

	} else if (MessageBox.METHOD_OPEN_CONFIRM.equals(methodName)) {
	    
	   com.sencha.gxt.widget.core.client.MessageBox box = newcom.sencha.gxt.widget.core.client.MessageBox();
	    box.setModal(true);
	    box.setTitle(title);
	    box.setMessage(message);
	    box.setButtons(com.sencha.gxt.widget.core.client.MessageBox.OKCANCEL);
	    box.setIcon(com.sencha.gxt.widget.core.client.MessageBox.QUESTION);
	    if (handler != null) {
		box.addCallback(createCallback(handler, false));
	    }
	    box.show();

	} else if (MessageBox.METHOD_OPEN_PROMPT.equals(methodName)) {
	    
	   com.sencha.gxt.widget.core.client.MessageBox box = newcom.sencha.gxt.widget.core.client.MessageBox();
	    box.setModal(true);
	    box.setTitle(title);
	    box.setMessage(message);
	    box.setType(com.sencha.gxt.widget.core.client.MessageBox.MessageBoxType.PROMPT);
	    box.setButtons(com.sencha.gxt.widget.core.client.MessageBox.OKCANCEL);
	    //box.setIcon(com.sencha.gxt.widget.core.client.MessageBox.QUESTION);
	    if (handler != null) {
		box.addCallback(createCallback(handler, true));
	    }
	    box.show();

	}

	    
	return null;
    }
    
    
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
    
 
    
}
