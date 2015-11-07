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

package org.plazmaforge.framework.uwt.view;

import org.plazmaforge.framework.core.data.Customizer;
import org.plazmaforge.framework.core.data.Notifier;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.IPresenter;

/**
 * 
 * View structure:
 * 
 * |-----------------------
 * | Title
 * |-----------------------
 * | MenuBar	(optional)
 * |-----------------------
 * | ToolBar	(optional)
 * |-----------------------
 * | Header	(optional)
 * |-----------------------
 * | Content
 * |-----------------------
 * | Footer	(optional)
 * |-----------------------
 * | ButtonBar	(optional)
 * |-----------------------
 * | StatusBar	(optional)
 * |-----------------------
 * 
 * 
 * @author ohapon
 *
 */
public interface IView extends IPresenter, Notifier {

    /**
     * Create view
     */
    void create();
    
    /**
     * Return parent view
     * @return
     */
    IView getParentView();

    /**
     * Return main container (body)
     * @return
     */
    Composite getContainer();
    
    /**
     * Return content (part of body) 
     * @return
     */
    Composite getContent();
  
    /**
     * Return general control 
     * @return
     */
    Control getControl();

    /**
     * Set general control
     * @param control
     */
    void setControl(Control control);
    
    
    boolean hasControl();
    
    
    Customizer getPreCustomizer();

    void setPreCustomizer(Customizer preCustomizer);

    Customizer getCustomizer();

    void setCustomizer(Customizer customizer);

    Customizer getPostCustomizer();

    void setPostCustomizer(Customizer postCustomizer);
 
    
    Object getData();

    void setData(Object data);
    

    Object getData(String key);

    void setData(String key, Object data);
	
	
    /**
     * Execute action
     * @param action
     */
    void executeAction(String action);
    
    
    
}
