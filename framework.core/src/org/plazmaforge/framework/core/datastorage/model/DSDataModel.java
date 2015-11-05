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
package org.plazmaforge.framework.core.datastorage.model;

import java.util.List;


/**
 *
 * General DSDataModel:
 * 
 *         +-----------+
 *         |DSDataNode |           DSDataLevel 1
 *         +-----------+                | previous
 *           |       |                  |
 *           |       |                  |
 *           |       |                  |
 * +-----------+   +-----------+        | next
 * |DSDataNode |   | DSDataNode|   DSDataLevel 2
 * +-----------+   +-----------+
 * 
 * 
 * @author ohapon
 *
 */
public class DSDataModel {

    private DSDataLevel rootLevel;
    
    private List<DSDataLevel> levels;
    
    public DSDataModel() {
	super();
    }

    public DSDataLevel getRootLevel() {
        return rootLevel;
    }

    public void setRootLevel(DSDataLevel rootLevel) {
        this.rootLevel = rootLevel;
    }

    public List<DSDataLevel> getLevels() {
        return levels;
    }

    public void setLevels(List<DSDataLevel> levels) {
        this.levels = levels;
    }
    
    public int getLevelCount() {
	return levels == null ? 0 : levels.size();
    }
    
}
