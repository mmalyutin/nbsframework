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

import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class XGroupPanel extends JPanel {

    private TitledBorder titledBorder;
    
    public XGroupPanel() {
	super();
	init();
    }

    public XGroupPanel(boolean isDoubleBuffered) {
	super(isDoubleBuffered);
	init();
    }

    public XGroupPanel(LayoutManager layout, boolean isDoubleBuffered) {
	super(layout, isDoubleBuffered);
	init();
    }

    public XGroupPanel(LayoutManager layout) {
	super(layout);
	init();
    }

    private void init() {
	titledBorder = BorderFactory.createTitledBorder("");
	titledBorder.setTitleJustification(TitledBorder.LEFT);
	setBorder(titledBorder);
    }
    
    public void setTitle(String title) {
	titledBorder.setTitle(title);
	repaint();
    }
    
    
    public String getTitle() {
	return titledBorder.getTitle();
    }
    
}
