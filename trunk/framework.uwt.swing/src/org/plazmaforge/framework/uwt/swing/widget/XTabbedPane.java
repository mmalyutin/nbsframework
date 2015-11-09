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

package org.plazmaforge.framework.uwt.swing.widget;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * TabbedPane with closable mode
 * 
 * @author ohapon
 *
 */
public class XTabbedPane extends JTabbedPane {
    
    private static final Icon CLOSE_TAB_ICON = new ImageIcon(XTabbedPane.class.getResource("closeTabButton.png"));

    private boolean closable; 
    
    public XTabbedPane() {
	super();
    }

    public XTabbedPane(int tabPlacement, int tabLayoutPolicy) {
	super(tabPlacement, tabLayoutPolicy);
    }

    public XTabbedPane(int tabPlacement) {
	super(tabPlacement);
    }

    @Override
    public void insertTab(String title, Icon icon, Component component, String tip, int index) {
	super.insertTab(title, icon, component, tip, index);
	if (!isClosable()) {
	    return;
	}
	initTabComponent(index, title, icon);
    }
    
    @Override
    public void setTitleAt(int index, String title) {
	super.setTitleAt(index, title);
	TabComponent tabComponent = (TabComponent) getTabComponentAt(index);
	tabComponent.setTitle(title);
    }

    private void initTabComponent(int i, String title, Icon icon) {
	this.setTabComponentAt(i, createTabComponent(this, title, icon));
    }
       
    public boolean isClosable() {
        return closable;
    }

    public void setClosable(boolean closable) {
        this.closable = closable;
    }

    protected Component createTabComponent(final JTabbedPane tabbedPane, String title, Icon icon) {
	return new TabComponent(tabbedPane, title, icon);
    }
    
    class TabComponent extends JPanel {

	private JLabel label;
	private JButton button;
		
	public TabComponent(JTabbedPane tabbedPane, String title, Icon icon) {
	    super();
	    
	        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
	        setOpaque(false);
	        label = new JLabel();
	        if (title != null){
	            label.setText(title);
	        }
	        if (icon != null) {
	            label.setIcon(icon);
	        }
	        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 2)); 
	        add(label);
	        
	        button = new JButton();
	        button.setToolTipText("Close");
	        
	        //button.setRolloverIcon(CLOSE_TAB_ICON);
	        button.setIcon(CLOSE_TAB_ICON);
	        button.setContentAreaFilled(false);
	        button.setFocusable(false);
	        button.setBorder(null);
	        button.setBorderPainted(false);
	        //button.addMouseListener(buttonMouseListener);
	        button.setRolloverEnabled(true);
	        button.addActionListener(new TabActionListener(tabbedPane, this));
	        add(button);
	        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));

	}

	public void setTitle(String title) {
	    label.setText(title);
	}
    }
    
    class TabActionListener implements ActionListener {
	
	private JTabbedPane tabbedPane;
	private Component tabComponent;

	public TabActionListener(JTabbedPane tabbedPane, Component tabComponent) {
	    this.tabbedPane = tabbedPane;
	    this.tabComponent = tabComponent;
	}

	public void actionPerformed(ActionEvent e) {
	    int i = tabbedPane.indexOfTabComponent(tabComponent);
	    if (i != -1) {
		tabbedPane.remove(i);
	    }
	}
    }
    
  
    
}
