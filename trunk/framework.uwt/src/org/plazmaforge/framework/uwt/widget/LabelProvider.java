package org.plazmaforge.framework.uwt.widget;

import org.plazmaforge.framework.uwt.graphics.Image;


public interface LabelProvider {

    /**
     * Returns the image for the label of the given element.
     * @param element
     * @return
     */
    Image getImage(Object element);

    /**
     * Returns the text for the label of the given element.
     * @param element
     * @return
     */
    String getText(Object element);
    
}
