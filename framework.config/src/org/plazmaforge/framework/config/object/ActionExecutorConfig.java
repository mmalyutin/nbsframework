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


package org.plazmaforge.framework.config.object;


/**
 * 
 * @author ohapon
 *
 */
public class ActionExecutorConfig implements IActionExecutorConfig {

    private static final long serialVersionUID = -7680588035797858036L;
    
    private String id;
    
    private String name;
    
    private String type;

    private String text;

    private String icon;
    
    private String action;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public String getIcon() {
	return icon;
    }

    public void setIcon(String icon) {
	this.icon = icon;
    }
    
    public String getAction() {
	return action;
    }

    public void setAction(String action) {
	this.action = action;
    }

    public boolean equals(Object o) {

	if (o == null) {
	    return false;
	}

	if (this == o) {
	    return true;
	}

	if (!(o instanceof ActionExecutorConfig)) {
	    return false;
	}
	ActionExecutorConfig c = (ActionExecutorConfig) o;
	
	if (!super.equals(c)) {
	    return false;
	}
//	if (!equals(c.getText(), getText())) {
//            return false;
//        }
//	if (!equals(c.getIcon(), getIcon())) {
//            return false;
//        } 
	return true;
    }

    public void setModify(boolean modify) {
        //super.setModify(modify);
        //if (modify && getParent() != null) {
            //getParent().setModify();
        //}
    }

    
    
}
