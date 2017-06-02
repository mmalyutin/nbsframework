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

package org.plazmaforge.framework.report.model.design;

import java.io.Serializable;

/**
 * 
 * @author ohapon
 *
 */
public class BandLayout implements Serializable {

    private static final long serialVersionUID = -8016138499851988652L;
    
    private int height;
    
    private int firstHeight;
    
    private int lastHeight;

    public BandLayout() {
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getFirstHeight() {
        return firstHeight;
    }

    public void setFirstHeight(int firstHeight) {
        this.firstHeight = firstHeight;
    }

    public int getLastHeight() {
        return lastHeight;
    }

    public void setLastHeight(int lastHeight) {
        this.lastHeight = lastHeight;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + firstHeight;
	result = prime * result + height;
	result = prime * result + lastHeight;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	BandLayout other = (BandLayout) obj;
	if (firstHeight != other.firstHeight)
	    return false;
	if (height != other.height)
	    return false;
	if (lastHeight != other.lastHeight)
	    return false;
	return true;
    }
    
    
    
    
    

}
