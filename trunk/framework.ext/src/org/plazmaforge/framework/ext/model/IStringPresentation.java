/*
 * Copyright (C) 2005-2010 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.ext.model;

import java.io.Serializable;

public interface IStringPresentation extends Serializable {

    
    String STRING_PRESENTATION_PROPERTY = "stringPresentation";
    
    String CODE_STRING_PRESENTATION_PROPERTY = "codeStringPresentation";
    
	
    /**
     * Return simple string presentation of the object
     * For example: name of the object
     * @return
     */
    String getStringPresentation();
    
    
    /**
     * Return code string presentation of the object
     * For example: code + name of the object
     * @return
     */
    String getCodeStringPresentation();
    
}
