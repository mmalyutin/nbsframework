package org.plazmaforge.framework.uwt.jfx.widget;

import org.plazmaforge.framework.uwt.jfx.layout.XGridData;
import org.plazmaforge.framework.uwt.jfx.layout.XGridLayout;
import org.plazmaforge.framework.uwt.jfx.layout.XGridLayoutHelper;
import org.plazmaforge.framework.uwt.jfx.layout.XGridLayoutHelper.Cell;
import org.plazmaforge.framework.uwt.jfx.layout.XGridLayoutHelper.GridLayout;
import org.plazmaforge.framework.uwt.jfx.layout.XLayoutUtils;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

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
	add(child, cell.column, cell.row, cell.columnSpan, cell.rowSpan);
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
        
        System.out.println(cell);
        
        return cell;
    }
    
    protected XGridData getGridData(Node child) {
	return XLayoutUtils.getLayoutData(XGridData.class, child);
    }

}
