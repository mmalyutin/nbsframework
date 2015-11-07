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

package org.plazmaforge.framework.uwt.widget.chart.plot;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.uwt.graphics.Padding;

public class Legend {

    /**
     * Legend padding
     */
    private Padding padding;
    
    /**
     * Horizontal label spacing
     */
    private int horizonatlSpacing;
    
    /**
     * Vertical label spacing
     */
    private int verticalSpacing;
    
    /**
     * List of legend labels
     */
    private List<LegendLabel> labels;
    
    

    public Legend() {
	padding = new Padding(2);
	horizonatlSpacing = 2;
	verticalSpacing = 2;
	labels = new ArrayList<LegendLabel>();
    }
    
    public Padding getPadding() {
        return padding;
    }

    public void setPadding(Padding padding) {
        this.padding = padding;
    }

    public int getHorizonatlSpacing() {
        return horizonatlSpacing;
    }

    public void setHorizonatlSpacing(int horizonatlSpacing) {
        this.horizonatlSpacing = horizonatlSpacing;
    }

    public int getVerticalSpacing() {
        return verticalSpacing;
    }

    public void setVerticalSpacing(int verticalSpacing) {
        this.verticalSpacing = verticalSpacing;
    }

    public void addLabel(LegendLabel label) {
	labels.add(label);
    }
    
    public void removeLabel(LegendLabel label) {
	labels.remove(label);
    }
    
    public int getLabelCount() {
	return labels.size();
    }
    
    public LegendLabel getLabel(int i) {
	return labels.get(i);
    }
    
    public boolean hasLabels() {
	return labels != null && !labels.isEmpty();
    }

}
