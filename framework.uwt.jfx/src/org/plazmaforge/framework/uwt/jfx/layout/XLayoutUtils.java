package org.plazmaforge.framework.uwt.jfx.layout;

import org.plazmaforge.framework.uwt.jfx.util.JFXUtils;
import org.plazmaforge.framework.uwt.jfx.widget.XLayoutContainer;
import org.plazmaforge.framework.uwt.widget.Widget;

import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * The Layout Utilities
 * - Gets LayoutData of node
 * - Gets Layout of container
 * 
 * @author ohapon
 *
 */
public class XLayoutUtils {

    /**
     * Returns LayoutData of node
     * @param node
     * @return
     */
    public static XLayoutData getLayoutData(Node node) {
	return JFXUtils.getDataOrNull(XLayoutData.class, node, Widget.PROPERTY_LAYOUT_DATA);
    }

    /**
     * Returns generic LayoutData of node
     * @param type
     * @param node
     * @return
     */
    public static <T extends XLayoutData> T getLayoutData(Class<T> type, Node node) {
	return JFXUtils.getDataOrNull(type, node, Widget.PROPERTY_LAYOUT_DATA);
    }
    
    /**
     * Sets LayoutData to node
     * @param node
     * @param layoutData
     */
    public static void setLayoutData(Node node, XLayoutData layoutData) {
	JFXUtils.setData(node, Widget.PROPERTY_LAYOUT_DATA, layoutData);
    }  
    
    /**
     * Gets Layout of container
     * @param container
     * @return
     */
    public static XLayout getLayout(XLayoutContainer container) {
	return container == null ? null : container.getLayout();
    }
    
    /**
     * Returns generic Layout of container
     * @param type
     * @param container
     * @return
     */
    public static <T extends XLayout> T getLayout(Class<T> type, XLayoutContainer container) {
	return JFXUtils.asTypeOrNull(type, getLayout(container));
    }  
    
    /**
     * Returns generic Layout of parent
     * @param type
     * @param parent
     * @return
     */
    public static <T extends XLayout> T getLayout(Class<T> type, Parent parent) {
	XLayoutContainer container = JFXUtils.asTypeOrNull(XLayoutContainer.class, parent);
	return getLayout(type, container);
    }    
}
