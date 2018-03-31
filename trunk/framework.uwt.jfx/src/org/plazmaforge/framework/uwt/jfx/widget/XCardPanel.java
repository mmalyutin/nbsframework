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
 *
 */

package org.plazmaforge.framework.uwt.jfx.widget;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
 
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ohapon
 *
 */
public class XCardPanel extends StackPane implements XContainer {

    private List<Node> children;
    private Node curr;
    private int index;

    private boolean performingLayout;

    public XCardPanel() {
	children = new ArrayList<>();
	index = -1;
    }

    protected ObservableList<Node> doGetChildren() {
	return getChildren();
    }

    protected boolean isEmpty() {
	return children.isEmpty();
    }
    
    @Override
    public void addChild(Node node) {
  	boolean wasEmpty = children.isEmpty();
  	doGetChildren().add(node);
  	children.add(node);

  	index = children.size() - 1;
  	setCurrent(index);
      }

    @Override
    public void removeChild(Node node) {
  	boolean wasCurr = curr == node;
  	doGetChildren().remove(node);
  	children.remove(node);

  	if (wasCurr) {
  	    if (children.isEmpty()) {
  		index = -1;
  	    } else {
  		index++;
  		if (index > children.size() - 1) {
  		    index = children.size() - 1;
  		}
  		setCurrent(index);
  	    }
  	}
      }
    
    
//    @Override
//    public void addChild(Node child) {
//	
//    }
//    
//    @Override
//    public void removeChild(Node child) {
//        getChildren().remove(child);
//    }
 
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

    ////
    
    protected void setCurrent(int index) {
	if (curr != null) {
	    curr.setVisible(false);
	    curr.toBack();
	}
	this.index = index;
	curr = children.get(index);
	curr.setVisible(true);
    }

  
    public void first() {
	if (isEmpty()) {
	    return;
	}
	index = 0;
	setCurrent(index);
    }

    public void last() {
	if (isEmpty()) {
	    return;
	}
	index = children.size() - 1;
	setCurrent(index);
    }

    public void prev() {
	if (isEmpty() || index <= 0) {
	    return;
	}
	index--;
	setCurrent(index);
    }

    public void next() {
	if (isEmpty() || index >= children.size() - 1) {
	    return;
	}
	index++;
	setCurrent(index);
    }

    @Override
    public void requestLayout() {
	if (performingLayout) {
	    return;
	}
	super.requestLayout();
    }

    @Override
    protected void layoutChildren() {
	performingLayout = true;

	List<Node> managed = getManagedChildren();

	double width = getWidth();
	double height = getHeight();
	double top = getInsets().getTop();
	double right = getInsets().getRight();
	double left = getInsets().getLeft();
	double bottom = getInsets().getBottom();
	double contentWidth = width - left - right;
	double contentHeight = height - top - bottom;

	double x = 0;
	double y = 0;
	double w = width;
	double h = height;

	// double x = left;
	// double y = top;
	// double w = contentWidth;
	// double h = contentHeight;

	for (int i = 0, size = managed.size(); i < size; i++) {
	    Node child = managed.get(i);
	    child.resizeRelocate(x, y, w, h);
	}

	performingLayout = false;
    }
    
}