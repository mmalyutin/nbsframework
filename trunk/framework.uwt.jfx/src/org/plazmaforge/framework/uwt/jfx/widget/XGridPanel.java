package org.plazmaforge.framework.uwt.jfx.widget;

import org.plazmaforge.framework.uwt.jfx.layout.XGridData;
import org.plazmaforge.framework.uwt.jfx.layout.XGridLayout;
import org.plazmaforge.framework.uwt.jfx.layout.XGridLayoutHelper;
import org.plazmaforge.framework.uwt.jfx.layout.XGridLayoutHelper.Cell;
import org.plazmaforge.framework.uwt.jfx.layout.XGridLayoutHelper.GridLayout;
import org.plazmaforge.framework.uwt.jfx.layout.XLayoutData;
import org.plazmaforge.framework.uwt.jfx.layout.XLayoutUtils;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * 
 * @author ohapon
 *
 */
public class XGridPanel extends GridPane implements XContainer {

    public XGridPanel() {
	super();
    }

    @Override
    public void addChild(Node child) {
	
	// Create new free cell for child
	Cell cell = createCell(child);
	
	GridPane.setConstraints(child, 
		cell.column,
		cell.row,
		cell.columnSpan,
		cell.rowSpan,
		cell.hPos,
		cell.vPos,
		cell.hGrow,
		cell.vGrow);
	
	GridPane.setFillWidth(child, cell.hFill);
	GridPane.setFillHeight(child, cell.vFill);
	
	if (child instanceof Region) {
	    if (isFillWidth(cell)) {
		((Region) child).setMaxWidth(Double.MAX_VALUE);
	    }
	    if (isFillHeight(cell)) {
		((Region) child).setMaxHeight(Double.MAX_VALUE);
	    }	    
	}
	
	//add(child, cell.column, cell.row, cell.columnSpan, cell.rowSpan);
	getChildren().add(child);
    }
    
    protected boolean isFillWidth(Cell cell) {
	if (cell == null) {
	    return false;
	}
	return cell.hFill || isGrow(cell.hGrow);
    }

    protected boolean isFillHeight(Cell cell) {
	if (cell == null) {
	    return false;
	}
	return cell.vFill || isGrow(cell.vGrow);
    }
    
    protected boolean isGrow(Priority priority) {
	return priority == Priority.ALWAYS || priority == Priority.SOMETIMES;
    }
    
    @Override
    public void removeChild(Node child) {
        getChildren().remove(child);
    }
 
    @Override
    public void removeAll() {
        getChildren().clear();
    }
 
    @Override
    public boolean hasChildren() {
        return !getChildren().isEmpty();
    }
 
    @Override
    public int getChildrenCount() {
        return getChildren().size();
    }
 
    @Override
    public Node getChild(int index) {
        return getChildren().get(index);
    }
 
    protected XGridData getGridData(Node child) {
	XGridData layoutData = XLayoutUtils.getLayoutData(XGridData.class, child);
	if (layoutData == null) {
	    layoutData = new XGridData();
	}
	return layoutData;	
    }
    
    protected XGridLayout getGridLayout() {
	XGridLayout layout = XLayoutUtils.getLayout(XGridLayout.class, getParent());
	if (layout == null) {
	    layout = new XGridLayout();
	}
	return layout;
    }
    
    /**
     * Create new free cell for child
     * @param child
     * @return
     */
    protected Cell createCell(Node child) {
	
	XGridLayout layout = getGridLayout(); 
        XGridData layoutData = getGridData(child);
        
        int columnSpan = layoutData.getColSpan();
        int rowSpan = layoutData.getRowSpan();
        
        GridLayout layouInfo = XGridLayoutHelper.getGridLayout(this);
        layouInfo.maxColumnCount = layout.getColumnCount();
        
        Cell cell = XGridLayoutHelper.findFreeCell(layouInfo, columnSpan, rowSpan);
        
        cell.hPos = XLayoutData.toHPos(layoutData.getHorizontalAlign());
        cell.vPos = XLayoutData.toVPos(layoutData.getVerticalAlign());
        
        cell.hFill = XLayoutData.isFill(layoutData.getHorizontalAlign());
        cell.vFill = XLayoutData.isFill(layoutData.getVerticalAlign());
        
        cell.hGrow = layoutData.isHorizontalFlex() ? Priority.ALWAYS : Priority.NEVER;
        cell.vGrow = layoutData.isVerticalFlex() ? Priority.ALWAYS : Priority.NEVER;
        
        // adopt
        if (cell.hPos == null) {
            cell.hPos = HPos.LEFT;
        }
        if (cell.vPos == null) {
            cell.vPos = VPos.CENTER;
            if (cell.vFill) {
        	cell.vPos = VPos.TOP;
            }
        }
        
        //System.out.println(cell);
        
        return cell;
    }
    
  

}
