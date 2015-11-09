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

package org.plazmaforge.framework.uwt.swing.util;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;

public class SwingGCUtils {

    private static boolean useFontMetricsGetStringBounds = true;
    
    public static float getStringWidth(final String text, final Graphics2D g2) {
	final FontMetrics fm = g2.getFontMetrics();
	final Rectangle2D bounds = getTextBounds(text, g2, fm);
	final float result = (float) bounds.getWidth();
	return result;
    }
    
    public static Rectangle2D getTextBounds(final String text, final Graphics2D g2) {
	 return getTextBounds(text, g2, g2.getFontMetrics());
    }
    
    public static Rectangle2D getTextBounds(final String text, final Graphics2D g2, final FontMetrics fm) {
        final Rectangle2D bounds;
        if (useFontMetricsGetStringBounds) {
            bounds = fm.getStringBounds(text, g2);
            // getStringBounds() can return incorrect height for some Unicode characters
            LineMetrics lm = fm.getFont().getLineMetrics(text, g2.getFontRenderContext());
            bounds.setRect(bounds.getX(), bounds.getY(), bounds.getWidth(), lm.getHeight());
        } else {
            final double width = fm.stringWidth(text);
            final double height = fm.getHeight();
            //if (logger.isDebugEnabled()) {
            //    logger.debug("Height = " + height);
            //}
            bounds = new Rectangle2D.Double(0.0, -fm.getAscent(), width, height);
        }
        return bounds;
    }
}
