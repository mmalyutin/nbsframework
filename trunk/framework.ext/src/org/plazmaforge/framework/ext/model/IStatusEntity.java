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

/**
 * 
 * @author ohapon
 *
 */
public interface IStatusEntity {

    float PERCENT_COMPLETED = 100.0f;

    String OPEN_STATUS = "OPEN";

    String CLOSED_STATUS = "CLOSED";

    String CANCELED_STATUS = "CANCELED";

    String NOTSTARTED_STATUS = "NOTSTARTED";

    String COMPLETED_STATUS = "COMPLETED";

    String WORKING_STATUS = "WORKING";

    String PLANNING_STATUS = "PLANNING";
    
    
    String CREATED_STATUS = "CREATED";
    
    String PAID_STATUS = "PAID";    

}
