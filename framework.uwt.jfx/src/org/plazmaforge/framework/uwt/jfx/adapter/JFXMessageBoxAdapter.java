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

package org.plazmaforge.framework.uwt.jfx.adapter;

import java.util.Optional;

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.widget.CallbackHandler;
import org.plazmaforge.framework.uwt.widget.CallbackResult;
import org.plazmaforge.framework.uwt.widget.MessageBox;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

/**
 * 
 * @author ohapon
 *
 */
public class JFXMessageBoxAdapter extends JFXWidgetAdapter {

    
    public Object createDelegate(UIElement parent, UIElement element) {
	//TODO:
	return null;
    }
    
    @Override
    public Object invoke(UIElement element, String methodName, Object[] args) {
	
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
	    Alert box = createMessageBox(AlertType.ERROR, title, message);
	    show(box, handler);
 	} else if (MessageBox.METHOD_OPEN_INFORMATION.equals(methodName)) {
 	    Alert box = createMessageBox(AlertType.INFORMATION, title, message);
 	    show(box, handler);
	} else if (MessageBox.METHOD_OPEN_WARNING.equals(methodName)) {
	    Alert box = createMessageBox(AlertType.WARNING, title, message);
	    show(box, handler);
	} else if (MessageBox.METHOD_OPEN_QUESTION.equals(methodName)) {
	    Alert box = createMessageBox(AlertType.CONFIRMATION, title, message);
	    box.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
	    show(box, handler);
	} else if (MessageBox.METHOD_OPEN_CONFIRM.equals(methodName)) {
	    Alert box = createMessageBox(AlertType.CONFIRMATION, title, message);
	    show(box, handler);
	} else if (MessageBox.METHOD_OPEN_PROMPT.equals(methodName)) {
	    TextInputDialog box = createPromptBox(title, message);
	    show(box, handler);
	}
	    
	return null;
    }
    
    protected Alert createMessageBox(AlertType type, String title, String message) {
	Alert box = new Alert(type);
	box.setTitle(title);
	box.setHeaderText(null); // TODO
	box.setContentText(message);
	return box;
    }
    
    protected TextInputDialog createPromptBox(String title, String message) {
	TextInputDialog box = new TextInputDialog();
 	box.setTitle(title);
 	box.setHeaderText(null); // TODO
 	box.setContentText(message);
 	return box;
     }    
    
    protected void show(Alert box, CallbackHandler handler) {
	Optional<ButtonType> buttonResult = box.showAndWait();
	if (handler == null) {
	    return;
	}
	ButtonType button = getButtonType(buttonResult);
	String action = button == null ? null : CallbackResult.normalizeConfirmAction(getButtonId(button)); // Get buttonId like Action
	CallbackResult result = new CallbackResult(action);
	handler.handle(result);
    }
    
    protected void show(TextInputDialog box, CallbackHandler handler) {
	Optional<String> buttonResult = box.showAndWait();
	if (handler == null) {
	    return;
	}
	CallbackResult result = null;
	if (buttonResult.isPresent()) {
	    result = new CallbackResult("OK");
	    result.setValue(buttonResult.get());
	} else {
	    result = new CallbackResult("CANCEL");
	}

	handler.handle(result);
    }

    protected ButtonType getButtonType(Optional<ButtonType> result) {
	return result == null ? null : result.get();
    }
    
    protected String getButtonId(ButtonType button) {
	if (button == null) {
	    return null;
	}
	//TODO
	if (button == ButtonType.OK)  {
	    return "OK";
	} else if (button == ButtonType.APPLY) {
	    return "APPLY";
	} else if (button == ButtonType.YES) {
	    return "YES";
	} else if (button == ButtonType.NO) {
	    return "NO";
	} else if (button == ButtonType.CANCEL) {
	    return "CANCEL";
	}
	return null;
    }
    
      
}
