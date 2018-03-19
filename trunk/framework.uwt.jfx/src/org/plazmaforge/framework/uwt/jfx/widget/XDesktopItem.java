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

package org.plazmaforge.framework.uwt.jfx.widget;

import org.plazmaforge.framework.core.data.Notifier;

import javafx.scene.Node;

/**
 * 
 * @author ohapon
 *
 */
public class XDesktopItem implements HasContent {

    
    private String name;
    
    private String title;
    
    private Object shell;
    
    private Node content;

    private Notifier notifier;
    
 
    private XDesktop desktop;
    
    
    public XDesktopItem(XDesktop desktop) {
	super();
	this.desktop = desktop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        desktop.updateTitle(this);
    }
    
    public Object getShell() {
        return shell;
    }

    public void setShell(Object shell) {
        this.shell = shell;
    }

    @Override
    public Node getContent() {
        return content;
    }

    @Override
    public void setContent(Node content) {
        this.content = content;
    }

    public Notifier getNotifier() {
        return notifier;
    }

    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

    public void layout() {
	if (content == null) {
	    return;
	}
	//TODO
	//JFXUtils.forceLayout(content);
    }
}
