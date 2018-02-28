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
package org.plazmaforge.framework.uwt.jfx.widget;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * The panel with single control filled horizontal and vertical
 * @author ohapon
 *
 */
public class XFitPanel extends AnchorPane implements XContainer {
 
    public XFitPanel() {
        super();
    }
 
    @Override
    public void addChild(Node child) {
        resetChild();
        getChildren().add(child);
        initChild(child);
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
 
    protected void resetChild() {
        if (hasChildren()) {
            removeAll();
        }
    }
 
    protected void initChild(Node child) {
        double padding = 0.0d; //TODO
        AnchorPane.setLeftAnchor(child, padding);
        AnchorPane.setRightAnchor(child, padding);
        AnchorPane.setTopAnchor(child, padding);
        AnchorPane.setBottomAnchor(child, padding);
    }

}
