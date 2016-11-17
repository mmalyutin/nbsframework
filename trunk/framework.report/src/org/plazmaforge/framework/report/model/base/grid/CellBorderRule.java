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
package org.plazmaforge.framework.report.model.base.grid;

/**
 * 
 * Cell border rules.
 * 
 * 
 * - COLUMN_INNER:
 * 
 *     |   |
 *   1 | 2 | 3
 *     |   |    
 *     |   |
 *   4 | 5 | 6
 *     |   |   
 *     |   |
 *   7 | 8 | 9
 *     |   |
 *
 *
 *
 * - COLUMN:
 * 
 * |   |   |   |
 * | 1 | 2 | 3 |
 * |   |   |   |
 * |   |   |   |
 * | 4 | 5 | 6 |
 * |   |   |   |
 * |   |   |   |
 * | 7 | 8 | 9 |
 * |   |   |   |
 *
 *
 *         
 * - ROW_INNER:
 * 
 *         
 *   1   2   3
 *  ___________ 
 *          
 *   4   5   6
 *  ___________
 *          
 *   7   8   9
 *
 *
 *
 *  - ROW:
 *  ___________ 
 *       
 *   1   2   3
 *  ___________ 
 *          
 *   4   5   6
 *  ___________
 *          
 *   7   8   9
 *  ___________ 
 *
 * 
 * 
 * - INNER:
 * 
 *    |   |
 *  1 | 2 | 3
 * ___|___|___ 
 *    |   |
 *  4 | 5 | 6
 * ___|___|___
 *    |   |
 *  7 | 8 | 9
 *    |   |
 *
 *
 *
 * - ALL:
 * +---+---+---+
 * |   |   |   |
 * | 1 | 2 | 3 |
 * |___|___|___| 
 * |   |   |   |
 * | 4 | 5 | 6 |
 * |___|___|___|
 * |   |   |   |
 * | 7 | 8 | 9 |
 * +---+---+---+
 *
 *
 *
 *
 * @author ohapon
 *
 */


//////////////////////////////////////////////////////////
// General cell border: _| (bottom, right)
//////////////////////////////////////////////////////////

public enum CellBorderRule {

    COLUMN_INNER,
    COLUMN,
    ROW_INNER,
    ROW,
    INNER,
    ALL;
    
    
    public static CellBorderRule getValue(String str) {
	if (str == null) {
	    return null;
	}
	return CellBorderRule.valueOf(str.toUpperCase());
    }

    //
    
    public static boolean isBottom(CellBorderRule rule) {
	if (rule == null) {
	    return false;
	}
	return rule == CellBorderRule.INNER 
		    || rule == CellBorderRule.ROW_INNER
		    || rule == CellBorderRule.ROW
		    || rule == CellBorderRule.ALL;
    }
    
    public static boolean isRight(CellBorderRule rule) {
	if (rule == null) {
	    return false;
	}
	return rule == CellBorderRule.INNER
		    || rule == CellBorderRule.COLUMN_INNER
		    || rule == CellBorderRule.COLUMN
		    || rule == CellBorderRule.ALL;
    }
    
    //
    
    public static boolean isFirstTop(CellBorderRule rule) {
	if (rule == null) {
	    return false;
	}
	return rule == CellBorderRule.ROW
		|| rule == CellBorderRule.ALL;
    }
    
    public static boolean isFirstLeft(CellBorderRule rule) {
	if (rule == null) {
	    return false;
	}
	return rule == CellBorderRule.COLUMN
		|| rule == CellBorderRule.ALL;
    }

    public static boolean isLastBottom(CellBorderRule rule) {
	if (rule == null) {
	    return false;
	}
	return rule == CellBorderRule.ROW 
		|| rule == CellBorderRule.ALL;
    }

    public static boolean isLastRight(CellBorderRule rule) {
	if (rule == null) {
	    return false;
	}
	return rule == CellBorderRule.COLUMN 
		|| rule == CellBorderRule.ALL;
    }

    
}
