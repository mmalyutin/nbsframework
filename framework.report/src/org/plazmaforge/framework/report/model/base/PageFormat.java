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

/**
 * 
 * @author ohapon
 *
 */
public enum PageFormat {

    
	/**
	 * Specifies an undefined paper size.
	 */ 
	UNDEFINED("Custom", 0, 0),
	
	/**
	 * Specifies the Letter paper size.
	 * ANSI X3.151-1987 - "Letter" (216 x 279 mm)
	 */ 
	LETTER("Letter", 216, 279),
	
	/**
	 * Specifies the Legal paper size.
	 * ANSI X3.151-1987 - "Legal" (216 x 356 mm)
	 */ 
	LEGAL("Legal", 216, 356),
	
	/**
	 * Specifies the Executive paper size.
	 * ANSI X3.151-1987 - "Executive" (190 x 254 mm)
	 */ 
	EXECUTIVE("Executive", 190, 254),
	
	/**
	 * Specifies the A3 paper size.
	 * ISO 216 - "A3" (297 x 420 mm)
	 */ 
	A3("A3", 297, 420),
	
	/**
	 * Specifies the A4 paper size.
	 * ISO 216 - "A4" (210 x 297 mm)
	 */ 
	A4("A4", 210, 297),
	
	/**
	 * Specifies the A5 paper size.
	 * ISO 216 - "A5" (148 x 210 mm)
	 */ 
	A5("A5", 148, 210),

	/**
	 * Specifies the DL Envelope paper size.
	 * ISO 269 - "Envelope DL" (110 x 220 mm)
	 */ 
	ENVELOPE_DL("Envelope_DL", 110, 220);

	
	
	public static final float MM_PT = 72f / 25.4f;
	
	
	private String name;
	private int width;
	private int height;
	
	private PageFormat(String name, int width, int height) {
	    this.name = name;
	    this.width = width;
	    this.height = height;
	}

	public String getName() {
	    return name;
	}

	public int getWidth() {
	    return width;
	}

	public int getHeight() {
	    return height;
	}
	
	public static float toPT(int mm) {
	    return mm * MM_PT;
	}
	
	public static PageFormat find(String name) {
	    if (name == null) {
		return null;
	    }
	    for (PageFormat f :values()){
		if (name.equals(f.getName()) ) {
		    return f;
		}
	    }
	    return null;
	}

}
