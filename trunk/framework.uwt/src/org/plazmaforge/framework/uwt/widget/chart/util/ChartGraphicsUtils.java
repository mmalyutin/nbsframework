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

package org.plazmaforge.framework.uwt.widget.chart.util;

import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.GC;
import org.plazmaforge.framework.uwt.graphics.Rectangle;
import org.plazmaforge.framework.uwt.graphics.Size;

public class ChartGraphicsUtils {

    
    public static void drawString(GC gc, String string, int x, int y, Color color) {
	if (string == null) {
	    return;
	}

	// Check string
	string = string.trim();
	if (string.isEmpty()) {
	    return;
	}
	

	if (color != null) {
	    gc.setForeground(color);
	}

	gc.drawText(string, x, y);
    }

    /*
    public static void drawString(GC gc, String string, Rectangle area, Color color) {
	drawString(gc, string, area, 0, color, false);
    }
    
    public static void drawString(GC gc, String string, Rectangle area, Color color, boolean center) {
	drawString(gc, string, area, 0, color, false);
    }
    */

    ////
    
    public static void drawString(GC gc, String string, Rectangle area, /*int angle, */Color color) {
	drawString(gc, string, area, /*angle, */color, false);
    }
    
    public static void drawString(GC gc, String string, Rectangle area, /*int angle,*/ Color color, boolean center) {
	if (string == null) {
	    return;
	}

	// Check string
	string = string.trim();
	if (string.isEmpty()) {
	    return;
	}

	// Check area
	if (area.getWidth() == 0 || area.getHeight() == 0) {
	    // No area
	    return;
	}

	// Get text size
	Size stringSize = gc.getStringSize(string);

	if (stringSize.getHeight() > area.getHeight()) {
	    return;
	}

	if (stringSize.getWidth() > area.getWidth()) {
	    string = getCollapseString(string, stringSize.getWidth(), area.getWidth());
	    stringSize = gc.getStringSize(string);

	    if (stringSize.getHeight() > area.getHeight() || stringSize.getWidth() > area.getWidth()) {
		return;
	    }
	}

	int textX = 0;

	int textY = 0;
	
	if (center) {
	    // Center by X
	    textX = (area.getWidth() - stringSize.getWidth()) / 2;
	    
	    // Center by Y
	    textY = (area.getHeight() - stringSize.getHeight()) / 2;	    
	}
	

	if (color != null) {
	    gc.setForeground(color);
	}

	gc.drawText(string, area.getX() + textX, area.getY() + textY);
	
	//if (angle == 0) {
	//    gc.drawText(string, area.getX() + textX, area.getY() + textY);
	//} else {
	//    gc.drawText(string, area.getX() + textX, area.getY() + textY, angle);
	//}
	
    }
 
    public static Color generateColor() {
	int r = generateRGB();
	int g = generateRGB();
	int b = generateRGB();
      	return new Color(r, g, b);
    }
    
    public static int generateRGB() {
      	return rand(0, 255);
    }
    
    private static Integer rand(int min, int max) {
      	return Integer.valueOf(min + (int) (Math.random() * (double) ((max - min) + 1)));
    }
    
    public static String getCollapseString(String string, int stringWidth, int width) {
	float k = (float) stringWidth / (float) width; // calculate width coef.
	int i = (int) (string.length() / k); // calculate count of chars by coef.
	if (i == 0) {
	    string = string.substring(0, 1);
	} else if (i > 3) {
	    string = string.substring(0, i - 3) + "...";
	} else {
	    string = string.substring(0, i);
	}
	return string;
    }
    
    
}
