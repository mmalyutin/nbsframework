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
package org.plazmaforge.framework.uwt.jfx.layout;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 
 * @author ohapon
 *
 */
public class XGridLayoutHelper {

    public static class GridLayout {
	 
	public int maxColumnCount;
	
	
	public List<Cell> cells;
	public int columnCount;
	public int rowCount;
 
        public String toString() {
            return "GridLayout["
                    + "columnCount=" + columnCount
                    + ", rowCount=" + rowCount
                    + ", " + toString("cells" , cells)
                    +"]";
        }
 
        private String toString(String field, Collection<Cell> cells) {
            if (cells == null) {
                return field + "=null";
            }
            if (cells.isEmpty()) {
                return field + "=[]";
            }
            return field + "=[\n" + Cell.toString(cells) + "\n]";
        }
    }
 
    public static class Cell {
        public int column;
        public int row;
        public int columnSpan;
        public int rowSpan;
        public HPos hPos;
        public VPos vPos;
        public Priority hGrow;
        public Priority vGrow;
 
        String type;
 
        public String toString() {
            return "Cell["
            + "column=" + column
            + ", row=" + row
            + ", columnSpan=" + columnSpan
            + ", rowSpan=" + rowSpan
            + ", hPos=" + hPos
            + ", vPos=" + vPos
            + ", hGrow=" + hGrow          
            + ", vGrow=" + vGrow              
            + ", type=" + type
            + "]";
        }
 
        public static String toString(Collection<Cell> cells) {
            if (cells == null || cells.isEmpty()) {
                return "";
            }
            StringBuilder buf = new StringBuilder();
            for (Cell cell: cells) {
                if (buf.length() > 0) {
                    buf.append("\n");
                }
                buf.append(cell.toString());
            }
            return buf.toString();
        }
    }
 
 
    /**
     * Returns cells of container
     * @param container
     * @return
     */
    public static List<Cell> getCells(GridPane container) {
        return getCells(container == null ? null : container.getChildren());
    }
 
    /**
     * Returns cells of children
     * @param children
     * @return
     */
    public static List<Cell> getCells(ObservableList<Node> children) {
        if (children == null) {
            return null;
        }
       List<Cell> cells = new ArrayList<>();
        Cell cell = null;
        for (Node child: children) {
            cell = createCell(child);
            cells.add(cell);
        }
        return cells;
    }
 
    /**
     * Returns GridLayout of container
     * @param container
     * @return
     */
    public static GridLayout getGridLayout(GridPane container) {
        if (container == null) {
            return null;
        }
 
        List<Cell> cells = getCells(container);
        GridLayout layout = getGridLayout(cells);
        return layout;
    }
 
    /**
     * Returns GridLayout of cells
     * @param cells
     * @return
     */
    public static GridLayout getGridLayout(List<Cell> cells) {
        GridLayout layout = new GridLayout();
 
        if (cells == null) {
            return layout;
        }
 
        int columnCount = 0;
        int rowCount = 0;
        for (Cell cell: cells) {
 
            // Column count
            int count = cell.column  + cell.columnSpan;
            if (count > columnCount) {
                columnCount = count;
            }
 
            // Row count
            count = cell.row  + cell.rowSpan;
            if (count > rowCount) {
                rowCount = count;
            }
 
        }
 
        layout.columnCount = columnCount;
        layout.rowCount = rowCount;
        layout.cells = cells;
 
        return layout;
    }
 
    /**
     * Create cell by Node
     * @param child
     * @return
     */
    public static Cell createCell(Node child) {
        Cell cell = new Cell();
        cell.column = intValue(GridPane.getColumnIndex(child));
        cell.row = intValue(GridPane.getRowIndex(child));
        cell.columnSpan = intValue(GridPane.getColumnSpan(child), 1);
        cell.rowSpan = intValue(GridPane.getRowSpan(child), 1);
 
        cell.type = child.getClass().getName();
        return cell;
    }
 
    /**
     * Finds place for new cell (columnSpan, rowSpan) and return first free cell
     * @param layout
     * @param columnSpan
     * @param rowSpan
     * @return
     */
    public static Cell findFreeCell(GridLayout layout, int columnSpan, int rowSpan) {
        int columnCount = Math.max(layout.maxColumnCount, layout.columnCount);
        int rowCount = layout.rowCount;
 
        columnSpan = normalyzeSpan(columnSpan);
        rowSpan = normalyzeSpan(rowSpan);
 
        if (layout.cells == null) {
            return new Cell();
        }
 
        List<Cell> cells = layout.cells;
        Cell cell = null;
 
 
        int freeColumn = -1;
        int freeRow = -1;
        int freeColumnSpan = columnSpan;
        int freeRowSpan = rowSpan;
        boolean found = false;
 
        cell = new Cell();
        for  (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
 
 
                cell.column  = column;
                cell.row = row;
                cell.columnSpan = columnSpan;
                cell.rowSpan = rowSpan;
 
                if (isFree(cells, cell)) {
 
                    found = true;
                    freeColumn  = column;
                    freeRow = row;
 
                    // last row
                    if (row == rowCount - 1) {
                        break;
                    }
                }
 
            }
        }
 
        // If free cell not found then free cell is first column in new row
        if (!found) {
            freeColumn  = 0; // first column
            freeRow = rowCount; // new row
        } else {
            if (freeRow < rowCount - 1) {
                //TODO
            } 
        }
 
        // fixed column span (increase)
        if (freeColumn + freeColumnSpan > columnCount) {
            freeColumnSpan = columnCount - freeColumn;
        }
 
        cell.column  = freeColumn;
        cell.row = freeRow;
        cell.columnSpan = freeColumnSpan;
        cell.rowSpan = freeRowSpan;
 
        return cell;
 
    }
 
 
    /**
     * Returns true if the cell is free
     * @param cells
     * @param cell
     * @return
     */
    public static boolean isFree(List<Cell> cells, Cell cell) {
        if (cells == null || cells.isEmpty()) {
            return true;
        }
        for (Cell c: cells) {
 
            if (isIntersects(c, cell)) {
                return false;
            }
        }
        return true;
    }
 
 
    //https://dxr.mozilla.org/mozilla-beta/source/toolkit/modules/Geometry.jsm
    //https://stackoverflow.com/questions/2752349/fast-rectangle-to-rectangle-intersection
    //[CORE]
    
    /**
     * Return true if cell are intersection
     * @param c1
     * @param c2
     * @return
     */
    private static boolean isIntersects(Cell c1, Cell c2) {
        if (c1 == null || c2 == null) {
            return false;
        }
        int x1 = c1.column;
        int y1 = c1.row;
        int x2 = c1.column + c1.columnSpan;
        int y2 = c1.row + c1.rowSpan;
 
        int a1 = c2.column;
        int b1 = c2.row;
        int a2 = c2.column + c2.columnSpan;
        int b2 = c2.row + c2.rowSpan;
 
        int max_x = Math.max(x1, a1);
        int min_x = Math.min(x2, a2);
        int max_y = Math.max(y1, b1);
        int min_y = Math.min(y2, b2);
        
        return max_x < min_x && max_y < min_y;
    }
 
    private static int intValue(Integer value) {
        return intValue(value, 0);
    }
 
    private static int intValue(Integer value, int def) {
        return value == null ? def : value.intValue();
    }
    
    private static int normalyzeSpan(int span) {
	return span < 1 ? 1 : span;
    }
    
}
