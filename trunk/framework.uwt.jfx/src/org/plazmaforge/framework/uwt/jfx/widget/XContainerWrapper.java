package org.plazmaforge.framework.uwt.jfx.widget;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * 
 * @author ohapon
 *
 */
public class XContainerWrapper implements XContainer {

    private Pane container;

    public XContainerWrapper(Pane container) {
	super();
	this.container = container;
    }

    public Pane getContainer() {
        return container;
    }

    public void setContainer(Pane container) {
        this.container = container;
    }
 
    public ObservableList<Node> getChildren() {
        return container.getChildren();
    }
    
    protected boolean isXContainer() {
	return container instanceof XContainer;
    }

    protected XContainer asXContainer() {
	return (XContainer) container;
    }
    
    

    //////////////////////////////////////////////////////////////////////////////////
    // CONTAINER METHODS
    //////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public void addChild(Node child) {
	if (isXContainer()) {
	    asXContainer().addChild(child);
	} else {
	    getChildren().add(child);
	}
    }
 
    @Override
    public void removeChild(Node child) {
	if (isXContainer()) {
	    asXContainer().removeChild(child);
	} else {
	    getChildren().remove(child);
	}
    }
 
    @Override
    public void removeAll() {
	if (isXContainer()) {
	    asXContainer().removeAll();
	} else {
	    getChildren().clear();
	}
    }
 
    @Override
    public boolean hasChildren() {
	if (isXContainer()) {
	    return asXContainer().hasChildren();
	} else {
	    return !getChildren().isEmpty();
	}
    }
 
    @Override
    public int getChildrenCount() {
	if (isXContainer()) {
	    return asXContainer().getChildrenCount();
	} else {
	    return getChildren().size();
	}
    }
 
    @Override
    public Node getChild(int index) {
	if (isXContainer()) {
	    return asXContainer().getChild(index);
	} else {
	    return getChildren().get(index);
	}
    }
    
}
