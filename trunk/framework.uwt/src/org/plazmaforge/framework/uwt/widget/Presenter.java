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

package org.plazmaforge.framework.uwt.widget;

import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.layout.FitLayout;
import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.menu.MenuBar;
import org.plazmaforge.framework.uwt.widget.panel.Panel;
import org.plazmaforge.framework.uwt.widget.tool.ButtonBar;
import org.plazmaforge.framework.uwt.widget.tool.CoolBar;
import org.plazmaforge.framework.uwt.widget.tool.StatusBar;
import org.plazmaforge.framework.uwt.widget.tool.ToolBar;

public class Presenter extends Composite implements IPresenter {

    private String title;
    
    private Image icon;

    
    private MenuBar menuBar;
    
    private Composite menuPanel;
    
    
    
    private ToolBar toolBar;
    
    private CoolBar coolBar;
    
    
    
    
    /**
     * Header container  (optional) 
     * 
     */
    private Composite header;
    
    /**
     * Content container
     */
    private Composite content;
    
    /**
     * Footer container (optional)
     */
    private Composite footer;
    
    /**
     * Button bar (container of buttons)
     */
    private ButtonBar buttonBar;
    
    /**
     * Status bar (optional)
     */
    private StatusBar statusBar;

    
    private boolean supportMenuBar;
    
    private boolean supportToolBar;
    
    private boolean supportHeader;
    
    private boolean supportFooter;
    
    private boolean supportButtonBar;
    
    private boolean supportStatusBar;
    
    public Presenter() {
    }
    
    protected void configure() {
	supportMenuBar = false;
	supportToolBar = true;
	supportStatusBar = true;
    }

    @Override
    protected void createUI() {
	
	GridLayout layout = new GridLayout();
	layout.setFix(true); // TODO: SPECIAL MARGIN FIX
	layout.resetMargin();
	layout.resetSpacing();
	setLayout(layout);

	// MENU BAR (OPTIONAL)
	if (isSupportMenuBar() && isEmulateMenuBar()) {
	    // TODO
	    menuPanel = new Composite();
	    menuPanel.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.MIDDLE, true, false));
	    menuPanel.setLayout(new FitLayout());
	    add(menuPanel);
	}

	
	// TOOL BAR (OPTIONAL)
	if (isSupportToolBar()) {
	    coolBar = new CoolBar();
	    coolBar.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.MIDDLE, true, false));
	    add(coolBar);

	}
	
	
	// HEADER (OPTIONAL)
	if (isSupportHeader()) {
	    header = createHeader();
	    header.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.MIDDLE, true, false));
	    add(header);
	}
	
	
	// CONTENT
	content = createContent();
	content.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.FILL, true, true));
	add(content);
	
	
	// FOOTER (OPTIONAL)
	if (isSupportFooter()) {
	    footer = createFooter();
	    footer.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.MIDDLE, true, false));
	    add(footer);
	}

	// BUTTON BAR (OPTIONAL)
	if (isSupportButtonBar()) {
	    buttonBar = createButtonBar();
	    buttonBar.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.MIDDLE, true, false));
	    add(buttonBar);
	}

	
	// STATUS BAR (OPTIONAL)
	if (isSupportStatusBar()) {
	    statusBar = createStatusBar();
	    statusBar.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.BOTTOM, true, false));
	    add(statusBar);
	}
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public void setIcon(String path) {
        if (icon == null) {
	    icon = new Image();
	}
        icon.setPath(path);
    }

    //////////////////////////////////////////////////////////////////////////////
    // MENUBAR
    //////////////////////////////////////////////////////////////////////////////
    
    protected boolean isSupportMenuBar() {
        return supportMenuBar;
    }

    protected boolean isEmulateMenuBar() {
        return false;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
    
    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
        updateMenuBar();
        fireChangeProperty(PROPERTY_MENU_BAR, menuBar);
    }

    protected void updateMenuBar() {
	if (isSupportMenuBar() && isEmulateMenuBar()) {
	    if (menuPanel == null) {
		return;
	    }
	    menuPanel.removeAll();
	    
	    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    // TODO
	    // CRITICAL HACK
	    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    //menuBar.setData(PROPERTY_LAYOUT_DATA, new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.MIDDLE, true, false));
	    
	    menuPanel.add(menuBar);
	}
    }

    
    
    public void setToolBar(ToolBar toolBar) {
	if (!isSupportToolBar()) {
	    return;
	}
	if (coolBar == null) {
	    return;
	}

        this.toolBar = toolBar;
        updateToolBar();
        fireChangeProperty(PROPERTY_TOOL_BAR, toolBar);
    }

    public void addToolBar(ToolBar toolBar) {
	if (!isSupportToolBar()) {
	    return;
	}
	if (coolBar == null) {
	    return;
	}
	if (!coolBar.hasItems()) {
	    // Single ToolBar
	    this.toolBar = toolBar;
	}
	coolBar.add(toolBar);
        fireChangeProperty(PROPERTY_TOOL_BARS, toolBar);
    }

    protected void updateToolBar() {
	if (coolBar == null) {
	    return;
	}
	coolBar.removeAll();
	if (toolBar == null) {
	    return;
	}
	coolBar.add(toolBar);
    }

    public MenuBar getMenuBar(String name) {
        return menuBar;
    }

    public MenuBar createMenuBar() {
        return null;
    }

    //////////////////////////////////////////////////////////////////////////////
    // TOOLBAR
    //////////////////////////////////////////////////////////////////////////////
    
    public ToolBar getToolBar() {
	if (toolBar == null) {
	    
	    if (!isSupportToolBar()) {
		return null;
	    }
	    
	    if (coolBar == null) {
		return null;
	    }
	    
	    // Auto create ToolBar if need
	    toolBar = coolBar.getItem();
	    if (toolBar == null) {
		toolBar = new ToolBar();
		coolBar.add(toolBar);
	        fireChangeProperty(PROPERTY_TOOL_BARS, toolBar);
	    }
	}
        return toolBar;
    }

    protected boolean isSupportToolBar() {
        return supportToolBar;
    }

    public ToolBar createToolBar() {
        return null;
    }

    //////////////////////////////////////////////////////////////////////////////
    // HEADER
    //////////////////////////////////////////////////////////////////////////////

    
    public boolean isSupportHeader() {
        return supportHeader;
    }

    public void setSupportHeader(boolean supportHeader) {
        this.supportHeader = supportHeader;
    }

    public Composite getHeader() {
        return header;
    }

    protected Composite createHeader() {
	Panel panel = new Panel();
	GridLayout layout = new GridLayout();
	panel.setLayout(layout);
        return panel;
    }
    
    //////////////////////////////////////////////////////////////////////////////
    // CONTENT
    //////////////////////////////////////////////////////////////////////////////
    
    public Composite getContent() {
        return content;
    }

    protected Composite createContent() {
	Composite content = new Composite();
	GridLayout layout = new GridLayout();
	layout.resetMargin();
	content.setLayout(layout);
        return content;
    }
    
    //////////////////////////////////////////////////////////////////////////////
    // FOOTER
    //////////////////////////////////////////////////////////////////////////////

    
    public boolean isSupportFooter() {
        return supportFooter;
    }

    public void setSupportFooter(boolean supportFooter) {
        this.supportFooter = supportFooter;
    }

    public Composite getFooter() {
        return footer;
    }

    protected Composite createFooter() {
	Panel panel = new Panel();
	GridLayout layout = new GridLayout();
	panel.setLayout(layout);
        return panel;
    }

    //////////////////////////////////////////////////////////////////////////////
    // BUTTON BAR
    //////////////////////////////////////////////////////////////////////////////

    
    
    public ButtonBar getButtonBar() {
        return buttonBar;
    }
    
    public boolean isSupportButtonBar() {
        return supportButtonBar;
    }

    public void setSupportButtonBar(boolean supportButtonBar) {
        this.supportButtonBar = supportButtonBar;
    }

    protected ButtonBar createButtonBar() {
        return ButtonBar.createDailogButtonBar();
    }


    //////////////////////////////////////////////////////////////////////////////
    // STATUSBAR
    //////////////////////////////////////////////////////////////////////////////

    protected boolean isSupportStatusBar() {
        return supportStatusBar;
    }


    public void setSupportStatusBar(boolean supportStatusBar) {
        this.supportStatusBar = supportStatusBar;
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }


    protected StatusBar createStatusBar() {
	StatusBar statusBar = new StatusBar();
	return statusBar;
    }


    public void setStatusText(String text) {
	if (statusBar == null) {
	    return;
	}
	statusBar.setText(text);
    }
    
    
}
