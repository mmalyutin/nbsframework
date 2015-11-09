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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JLabel;

public class XCollapsePanel extends XTitlePanel {

    protected Icon expandIcon;
    
    protected Icon collapseIcon;
    
    protected JLabel toggleLabel;

    public XCollapsePanel() {
	super();
	init();
    }

    private void init() {
	titlePanel.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		changeExpandState();
	    }
	});
    }
    
    protected void createTitlePanel() {
	super.createTitlePanel();
	expandIcon = new ExpandIcon();
	collapseIcon = new CollapseIcon();
	toggleLabel = new JLabel(expandIcon);
	titlePanel.add(toggleLabel, BorderLayout.EAST);
    }
    
    private int visibleWidth = 0;
    
    protected void changeExpandState() {
	boolean visible = contentPanel.isVisible();
	if (visible) {
	    Dimension size = getPreferredSize();
	    if (size != null) {
		visibleWidth = size.width;
	    }
	}
	visible = !visible;
	contentPanel.setVisible(visible);
	toggleLabel.setIcon(visible ? expandIcon : collapseIcon);
	titleLabel.repaint();
    }
    
    public Dimension getPreferredSize() {
	Dimension size = super.getPreferredSize();
	if (size == null) {
	    return size;
	}
	if (visibleWidth > 0) {
	    size.width = visibleWidth;
	}
	return size;
    }
    
    @Override
    public void setToolTipText(String text) {
	super.setToolTipText(text);
	titlePanel.setToolTipText(text); // FIX
    }

    
    static class ExpandIcon implements Icon {

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
            g.drawPolygon(new int[] {1, 4, 5, 8}, new int[] {3, 6, 6, 3}, 4);
            g.fillPolygon(new int[] {1, 4, 5, 8}, new int[] {3, 6, 6, 3}, 4);
	}

	@Override
	public int getIconWidth() {
	    return 10;
	}

	@Override
	public int getIconHeight() {
	    return 10;
	}
	
    }
    
    
    static class CollapseIcon implements Icon {

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
            g.drawPolygon(new int[] {3, 6, 6, 3}, new int[] {1, 4, 5, 8}, 4);
            g.fillPolygon(new int[] {3, 6, 6, 3}, new int[] {1, 4, 5, 8}, 4);
	}

	@Override
	public int getIconWidth() {
	    return 10;
	}

	@Override
	public int getIconHeight() {
	    return 10;
	}
	
    }
}
