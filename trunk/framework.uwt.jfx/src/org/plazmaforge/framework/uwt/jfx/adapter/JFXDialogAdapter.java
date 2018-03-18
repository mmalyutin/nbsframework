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

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.jfx.util.JFXUtils;
import org.plazmaforge.framework.uwt.widget.Window;

import javafx.stage.Modality;
import javafx.stage.StageStyle;

/**
 * 
 * @author ohapon
 *
 */
public class JFXDialogAdapter extends /*JFXWindowAdapter*/ JFXContainerAdapter {

    public Object createDelegate(UIElement parent, UIElement element) {

	Object xParent = null;
	if (parent != null) {
	    xParent = parent.getDelegate();
	}
	Window window = (Window) element;
	javafx.scene.control.Dialog<?> xWindow = createWindow(xParent, window);

	javafx.stage.Window internalWindow = getInternalWindow(xWindow);
	internalWindow.setOnCloseRequest(event -> internalWindow.hide());

	// addNotifierListener(window, xWindow);
	return xWindow;
    }
      
    protected javafx.scene.control.Dialog<?> createWindow(Object xParent, Window window) {
	javafx.scene.control.Dialog<?> xWindow = new javafx.scene.control.Dialog();

	// Set position (left, top)
	xWindow.setX(0);
	xWindow.setY(0);

	// Set title
	String title = asSafeString(window.getTitle());
	xWindow.setTitle(title);

	// Update decoration
	updateDecoration(window, xWindow);

	// TODO:FX
	javafx.scene.Parent xContainer = JFXUtils.createContainer();
	xWindow.getDialogPane().setContent(xContainer);

	if (window.isModal()) {
	    xWindow.initOwner(getPrimaryStage(window));
	    xWindow.initModality(Modality.WINDOW_MODAL);
	}

	return xWindow;
    }
    
    protected javafx.stage.Window getInternalWindow(javafx.scene.control.Dialog<?> xWindow) {
	return xWindow.getDialogPane().getScene().getWindow();
    }
    
    protected javafx.stage.Stage getInternalStage(javafx.scene.control.Dialog<?> xWindow) {
	return (javafx.stage.Stage) getInternalWindow(xWindow);
    }    
    
    protected javafx.scene.control.Dialog<?> asDialog(Object delegate) {
	return (javafx.scene.control.Dialog<?>) delegate;
    }     
    
    protected void updateDecoration(Window window, javafx.scene.control.Dialog<?> xWindow) {

	// Set style
	xWindow.setResizable(window.isResizable());
	if (window.isUndecorated()) {
	    // No decoration:
	    // title - no,
	    // min - no,
	    // max - no,
	    // close - no
	    xWindow.initStyle(StageStyle.UNDECORATED);
	} else {
	    // Utility decoration:
	    // title - yes,
	    // min - no,
	    // max - no,
	    // close - yes
	    if (!window.isMinimizable() && !window.isMaximizable() && window.isResizable()) {
		xWindow.initStyle(StageStyle.UTILITY);
	    }

	    // TODO: Not implemented in JFX
	    // min - no/yes,
	    // max - no/yes,
	    // close - no/yes

	}
    }
      
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	javafx.scene.control.Dialog<?> xWindow = asDialog(element.getDelegate());
	if (xWindow == null) {
	    return;
	}
	if (Window.PROPERTY_TITLE.equals(name)) {
	    xWindow.setTitle(asSafeString(value));
	    return;
	} else if (Window.PROPERTY_ICON.equals(name)) {
	    javafx.scene.image.Image xIcon = createImage(element, asImage(value));
	    if (xIcon != null) {
		// TODO:FX
		getInternalStage(xWindow).getIcons().add(xIcon);
	    }
	    return;
	} else if (Window.PROPERTY_ICON_PATH.equals(name)) {
	    javafx.scene.image.Image xIcon = createImage(element, asString(value));
	    if (xIcon != null) {
		// TODO:FX
		getInternalStage(xWindow).getIcons().add(xIcon);
	    }
	    return;
	}
	super.setProperty(element, name, value);
    }

    @Override
    public Object invoke(UIElement element, String methodName, Object[] args) {
	javafx.scene.control.Dialog<?> xWindow = asDialog(element.getDelegate());
	if (xWindow == null) {
	    return null;
	}
	Window window = (Window) element;
	if (Window.METHOD_OPEN.equals(methodName)) {
	    doOpen(window, xWindow);
	} else if (Window.METHOD_CLOSE.equals(methodName)) {
	    doClose(window, xWindow);
	} else if (Window.METHOD_LAYOUT.equals(methodName)) {
	    // TODO
	    // xWindow.layout();
	    return null;
	} else if (Window.METHOD_PACK.equals(methodName)) {
	    doPack(xWindow);
	    return null;
	} else if (Window.METHOD_CENTER.equals(methodName)) {
	    doCenter(xWindow);
	    return null;
	} else if (Window.METHOD_ACTIVATE.equals(methodName)) {
	    // TODO:FX
	    getInternalStage(xWindow).toFront();
	    return null;
	} else if (Window.METHOD_DEACTIVATE.equals(methodName)) {
	    // TODO:FX
	    getInternalStage(xWindow).toBack();
	    return null;
	} else if (Window.METHOD_MAXIMIZE.equals(methodName)) {
	    // TODO:FX
	    getInternalStage(xWindow).setMaximized(true);
	    return null;
	} else if (Window.METHOD_MINIMIZE.equals(methodName)) {
	    // TODO:FX
	    getInternalStage(xWindow).setMaximized(false);
	    return null;
	}

	return super.invoke(element, methodName, args);
    }

    /**
     * Open window
     * 
     * @param window
     * @param xWindow
     */
    protected void doOpen(Window window, javafx.scene.control.Dialog<?> xWindow) {
	if (xWindow == null) {
	    return;
	}
	// TODO
	// xWindow.layout();
	if (window.isPack()) {
	    doPack(xWindow);
	}
	if (window.isCenter()) {
	    doCenter(xWindow);
	}

	// Open window
	xWindow.show();
    }

    /**
     * Close window
     * 
     * @param window
     * @param xWindow
     */
    protected void doClose(Window window, javafx.scene.control.Dialog<?> xWindow) {
	if (xWindow == null) {
	    return;
	}

	// Close window
	xWindow.hide();
    }

    protected void doPack(javafx.scene.control.Dialog<?> xWindow) {
	// TODO: Need pack size to preferred children area
	// but sizeToScene() pack to window size
	getInternalStage(xWindow).sizeToScene();
    }

    protected void doCenter(javafx.scene.control.Dialog<?> xWindow) {
	getInternalWindow(xWindow).centerOnScreen();
    }
    
}
