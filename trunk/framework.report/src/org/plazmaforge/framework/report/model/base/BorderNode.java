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

package org.plazmaforge.framework.report.model.base;

import java.io.Serializable;

/**
 * 
 * @author ohapon
 *
 */
public class BorderNode implements Serializable  {

    private static final long serialVersionUID = -5401329918130048093L;
    

    private Pen beforePen;
    
    private Pen afterPen;

    public BorderNode() {
	super();
    }

    public Pen getBeforePen() {
	if (beforePen == null) {
	    beforePen = new Pen();
	}
        return beforePen;
    }

    public void setBeforePen(Pen beforePen) {
        this.beforePen = beforePen;
    }

    public Pen getAfterPen() {
	if (afterPen == null) {
	    afterPen = new Pen();
	}
        return afterPen;
    }

    public void setAfterPen(Pen afterPen) {
        this.afterPen = afterPen;
    }

    ////
    
    protected boolean hasPen(Pen pen) {
	return !isEmpty(pen);
    }
    
    protected boolean isEmpty(Pen pen) {
	return pen == null || pen.isEmpty();
    }
    
    ////

    public boolean hasBeforePen() {
	return hasPen(beforePen);
    }
    
    public boolean hasAfterPen() {
	return hasPen(afterPen);
    }
    
    public boolean isEmpty() {
	return isEmpty(beforePen) && isEmpty(afterPen);
    }

    @Override
    public String toString() {
	return "BorderNode[beforePen=" + beforePen + ", afterPen="  + afterPen + "]";
    }
    
}
