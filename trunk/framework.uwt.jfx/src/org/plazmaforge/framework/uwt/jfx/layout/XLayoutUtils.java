package org.plazmaforge.framework.uwt.jfx.layout;

import org.plazmaforge.framework.uwt.jfx.util.JFXUtils;
import org.plazmaforge.framework.uwt.jfx.widget.XLayoutContainer;
import org.plazmaforge.framework.uwt.widget.Widget;

import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * 
 * @author ohapon
 *
 */
public class XLayoutUtils {

    public static XLayoutData getLayoutData(Node node) {
	return JFXUtils.getDataOrNull(XLayoutData.class, node, Widget.PROPERTY_LAYOUT_DATA);
    }

    public static <T extends XLayoutData> T getLayoutData(Class<T> type, Node node) {
	return JFXUtils.getDataOrNull(type, node, Widget.PROPERTY_LAYOUT_DATA);
    }
    
    public static void setLayoutData(Node node, XLayoutData layoutData) {
	JFXUtils.setData(node, Widget.PROPERTY_LAYOUT_DATA, layoutData);
    }  
    
    public static XLayout getLayout(XLayoutContainer container) {
	return container == null ? null : container.getLayout();
    }
    
    public static <T extends XLayout> T getLayout(Class<T> type, XLayoutContainer container) {
	return JFXUtils.asTypeOrNull(type, getLayout(container));
    }  
    
    public static <T extends XLayout> T getLayout(Class<T> type, Parent parent) {
	XLayoutContainer container = JFXUtils.asTypeOrNull(XLayoutContainer.class, parent);
	return getLayout(type, container);
    }    
}
