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

package org.plazmaforge.framework.report.export;

import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;

/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractBaseExporter extends AbstractReportExporter {

    protected Color parentBackground;
    protected Color parentForeground;
    protected Font parentFont;
	
    protected Color background;
    protected Color foreground;
    protected Font font;

    
    protected Color getColor(Color color, Color parentColor) {
	return color == null ? parentColor : color;
    }

    protected Font getFont(Font font, Font parentFont) {
	return font == null ? parentFont : font;
    }
    
    protected void normalizeCurrentStyle() {
	background = getColor(background, parentBackground);
	foreground = getColor(foreground, parentForeground);
	font = getFont(font, parentFont);
    }
    

}
