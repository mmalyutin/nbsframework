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
 * DSDataLevel describes level of DSDataModel
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
public class DSDataLevel {

    // Current node
    private List<DSDataNode> nodes;

    // Prev (Parent) level
    private DSDataLevel prevLevel;
    
    // Next (Child) level
    private DSDataLevel nextLevel;
    
    private int index;

    public DSDataLevel() {
    }

    public List<DSDataNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<DSDataNode> nodes) {
        this.nodes = nodes;
    }

    public DSDataLevel getPrevLevel() {
        return prevLevel;
    }

    public void setPrevLevel(DSDataLevel prevLevel) {
        this.prevLevel = prevLevel;
    }

    public DSDataLevel getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(DSDataLevel nextLevel) {
        this.nextLevel = nextLevel;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    
    
}
