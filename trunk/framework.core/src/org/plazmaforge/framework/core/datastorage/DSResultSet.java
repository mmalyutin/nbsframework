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


package org.plazmaforge.framework.core.datastorage;

import org.plazmaforge.framework.core.exception.DSException;


public interface DSResultSet  {

    /**
     * Move marker to next position.
     * @return
     * @throws DSException 
     */
    boolean next() throws DSException;
    

    /**
     * Return value of current record by field name
     * @param name
     * @return
     * @throws DSException 
     */
    Object getValue(String name) throws DSException;
    

    /**
     * Return true if DSResultSet is empty
     * @return
     */
    boolean isEmpty() throws DSException;
    
    /**
     * Return true if DSResultSet is closed
     * @return
     */
    boolean isClosed() throws DSException;
    
    
    /**
     * Close DSResultSet
     * @throws DSException
     */
    void close() throws DSException;
    
   


}
