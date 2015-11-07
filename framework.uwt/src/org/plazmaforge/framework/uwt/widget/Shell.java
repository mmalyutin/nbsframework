package org.plazmaforge.framework.uwt.widget;

import org.plazmaforge.framework.uwt.widget.menu.MenuBar;

public class Shell extends Composite {

    private String title;
    
    private MenuBar menuBar;

    public Shell() {
	super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        fireChangeProperty(PROPERTY_TITLE, title);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
        menuBar.setParent(this);
        fireChangeProperty(PROPERTY_MENU_BAR, menuBar);
    }
    
//    @Override
//    public Object getDelegate() {
//	Object delegate = super.getDelegate();
//        return delegate;
//    }
//    
    
}
