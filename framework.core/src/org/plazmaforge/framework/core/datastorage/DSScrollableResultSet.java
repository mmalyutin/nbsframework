/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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

/**
 * 
 */
package org.plazmaforge.framework.core.datastorage;

import org.plazmaforge.framework.core.exception.DSException;

/**
 * @author ohapon
 *
 */
public interface DSScrollableResultSet extends DSResultSet {

    /**
     * Return true if scroll mode is enabled
     * @return
     */
    boolean canScroll() throws DSException;
    
    /**
     * Move marker to first position.
     * @return
     * @throws DSException
     */
    boolean first() throws DSException;
    
    /**
     * Move marker to the position
     * @param rowIndex
     * @return
     * @throws DSException
     */
    boolean move(int index) throws DSException;
    
    /**
     * Move marker to last position.
     * @return
     * @throws DSException
     */
    boolean last() throws DSException;
    
    
    /**
     * Move marker to the previous position
     * @return
     * @throws DSException
     */
    boolean prev() throws DSException;


    /**
     * Move marker to the before first position
     * @return
     * @throws DSException
     */
    boolean top() throws DSException;

    
    /**
     * Move marker to the after last position
     * @return
     * @throws DSException
     */
    boolean bottom() throws DSException;

}
