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

package org.plazmaforge.framework.uwt.action;

import org.plazmaforge.framework.core.data.Identifier;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.util.PropertyChangeListener;
import org.plazmaforge.framework.uwt.widget.Widget;

public interface Action extends Identifier {

    
    String PROPERTY_TEXT = Widget.PROPERTY_TEXT;
    
    String PROPERTY_ICON = Widget.PROPERTY_ICON;
    
    String PROPERTY_ENABLED = Widget.PROPERTY_ENABLED;
    
    
    
    String getId();

    void setId(String id);

    String getName();

    void setName(String name);

    String getText();

    void setText(String text);

    String getDescription();

    void setDescription(String description);

    Image getIcon();

    void setIcon(Image icon);

    boolean isEnabled();
    
    void setEnabled(boolean enabled);
    
    void addPropertyChangeListener(PropertyChangeListener listener);

    void removePropertyChangeListener(PropertyChangeListener listener);
    

    void execute();
    
}
