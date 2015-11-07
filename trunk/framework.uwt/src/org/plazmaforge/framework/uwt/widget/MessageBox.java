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

package org.plazmaforge.framework.uwt.widget;

import org.plazmaforge.framework.uwt.UIAdapter;
import org.plazmaforge.framework.uwt.UIAdapterFactory;
import org.plazmaforge.framework.uwt.UIObject;

/**
 * Instances of this class are used to inform or warn the user.
 * 
 * Functions: 
 * - Error
 * - Info
 * - Question (Yes/No)
 * - Warning
 * - Confirm (Ok/Cancel)
 * - Propmt (Ok/Cancel)
 *  
 * @author ohapon
 *
 */
public class MessageBox extends UIObject {

    ////
    
    public static final int ERROR = 1;
    
    public static final int INFORMATION = 2;
    
    public static final int WARNING = 3;
    
    public static final int QUESTION = 4; // Yes/No
    
    public static final int CONFIRM = 5; // Ok/Cancel
    
    public static final int PROMPT = 6; // Ok/Cancel
    
    
    ////
    
    public static final String METHOD_OPEN_ERROR = "openError";
    
    public static final String METHOD_OPEN_INFORMATION = "openInformation";

    public static final String METHOD_OPEN_WARNING = "openWarning";
    
    public static final String METHOD_OPEN_QUESTION = "openQuestion";
    
    public static final String METHOD_OPEN_CONFIRM = "openConfirm";
    
    public static final String METHOD_OPEN_PROMPT = "openPrompt";
    
    
    ////
    
    
    /**
     * Title of the message box
     */
    private String title;
    
    /**
     * Message of the box
     */
    private String message;
    
    
    /**
     * Type of message (ERROR, INFORMATION, WARNING, QUESTION, PROMPT)
     */
    private int messageType;
    

    ////

    private static UIAdapter staticAdapter;
    
    ////
    
    public MessageBox() {
	super();
    }

    public MessageBox(int messageType) {
	super();
	this.messageType = messageType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void error(String title, String message, CallbackHandler handler) {
	openStatic(ERROR, title, message, handler);
    }

    public static void error(String title, String message) {
	openStatic(ERROR, title, message);
    }

    public static void error(String message) {
	openStatic(ERROR, null, message);
    }

    
    public static void information(String title, String message, CallbackHandler handler) {
  	openStatic(INFORMATION, title, message, handler);
    }
    
    public static void information(String title, String message) {
	openStatic(INFORMATION, title, message);
    }
    
    public static void information(String message) {
	openStatic(INFORMATION, null, message);
    }
    

    public static void warning(String title, String message, CallbackHandler handler) {
	openStatic(WARNING, title, message, handler);
    }
    
    public static void warning(String title, String message) {
	openStatic(WARNING, title, message);
    }

    public static void warning(String message) {
	openStatic(WARNING, null, message);
    }

    

    public static void question(String title, String message, CallbackHandler handler) {
	openStatic(QUESTION, title, message, handler);
    }

    public static void question(String title, String message) {
	openStatic(QUESTION, title, message);
    }

    public static void question(String message) {
	openStatic(QUESTION, null, message);
    }


    public static void confirm(String title, String message, CallbackHandler handler) {
	openStatic(CONFIRM, title, message, handler);
    }

    
    public static void confirm(String title, String message) {
	openStatic(CONFIRM, title, message);
    }
    
    public static void confirm(String message) {
	openStatic(CONFIRM, null, message);
    }
    

    
    public static void prompt(String title, String message, CallbackHandler handler) {
	openStatic(PROMPT, title, message, handler);
    }
    
    public static void prompt(String title, String message) {
	openStatic(PROMPT, title, message);
    }
    
    public static void prompt(String message) {
	openStatic(PROMPT, null, message);
    }
    
    
    private static UIAdapter getStaticAdapter() {
	if (staticAdapter == null) {
	    staticAdapter = UIAdapterFactory.getAdapter(MessageBox.class);
	}
	return staticAdapter;
    }
    
    private static Object openStatic(int type, String title, String message, CallbackHandler handler) {
	return openStatic(getStaticAdapter(), type, title, message, handler);
    }
    
    private static Object openStatic(int type, String title, String message) {
	return openStatic(getStaticAdapter(), type, title, message, null);
    }

    ////
    
    
    private static Object openStatic(UIAdapter adapter, int type, String title, String message, CallbackHandler handler) {
	String method = null;
	if (ERROR == type) {
	    method = METHOD_OPEN_ERROR;
	} else if (INFORMATION == type) {
	    method = METHOD_OPEN_INFORMATION;
	} else if (WARNING == type) {
	    method = METHOD_OPEN_WARNING;
	} else if (QUESTION == type) {
	    method = METHOD_OPEN_QUESTION;
	} else if (CONFIRM == type) {
	    method = METHOD_OPEN_CONFIRM;
	} else if (PROMPT == type) {
	    method = METHOD_OPEN_PROMPT;
	} else {
	    method = METHOD_OPEN_INFORMATION;
	}
	Object result = adapter.invoke(null, method, new Object[] {title, message, handler});
	return result;
    }
}
