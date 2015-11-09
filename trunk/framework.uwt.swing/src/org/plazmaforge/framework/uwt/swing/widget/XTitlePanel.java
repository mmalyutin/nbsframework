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
import java.awt.Color;
import java.awt.Container;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class XTitlePanel extends JPanel {

    
    public final static String EMPTY_TITLE = " ";
    
    protected Icon titleIcon;
    
    protected JLabel titleLabel;
    
    protected GradientPanel titlePanel;
    
    protected JPanel contentPanel;
    
    
    
    
    public XTitlePanel() {
	super();
	init();
    }
    
    
    private void init() {
	
        setLayout(new BorderLayout());

        createTitlePanel();
        super.add(titlePanel, BorderLayout.NORTH);
        
        createContentPanel();
        super.add(contentPanel, BorderLayout.CENTER);

        // TODO: Stub
        Border outerBorder = null;
        
        if (outerBorder == null) {
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
        } else {
            setBorder(BorderFactory.createCompoundBorder(outerBorder, BorderFactory.createLineBorder(Color.GRAY)));
        }

    }
    
    
    protected void createTitlePanel() {
        titleIcon = null; //TODO: STUB;
        titleLabel = new JLabel(EMPTY_TITLE, titleIcon, JLabel.LEADING);
        titleLabel.setForeground(Color.WHITE);

        titlePanel = new GradientPanel(Color.BLACK);
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.WEST);
        
        int borderOffset = 2;
        
        //if(titleIcon == null) {
        //  borderOffset += 1;
        //}
        
        titlePanel.setBorder(BorderFactory.createEmptyBorder(borderOffset, 4, borderOffset, 1));
    }
    
    protected void createContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        //northPanel.add(content,BorderLayout.NORTH);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    }
    
    protected boolean isSupportToggle() {
	return true;
    }
    
    public void setTitle(String title) {
        titleLabel.setText(normalyzeTitle(title));
    }
    
    private String normalyzeTitle(String title) {
	if (title == null) {
	    return EMPTY_TITLE; 
	}
	title = title.trim();
	if (title.isEmpty()) {
	    title = EMPTY_TITLE;
	}
	return title;
    }
    
    public void setIcon(Icon icon) {
        titleLabel.setIcon(icon);
    }
    
   
    public Container getContentPane() {
        return contentPanel;
    }
    

    @Override
    public void setBackground(Color bg) {
	if (contentPanel == null) {
	    super.setBackground(bg);
	    return;
	}
	contentPanel.setBackground(bg);
    }


    @Override
    public Color getBackground() {
	if (contentPanel == null) {
	    return super.getBackground();
	}
	return contentPanel.getBackground();
    }
    

    static class GradientPanel extends JPanel {
	
	private static final long serialVersionUID = -6385751027379193053L;

	private GradientPanel(Color background) {
	    setBackground(background);
	}

	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    if (isOpaque()) {
		
		//Color endColor = UIManager.getColor("control");
		//Color endColor = new Color(252, 198, 82);
		//Color endColor = new Color(99, 153, 255); // default
		
		//Color startColor = getBackground(); // default
		Color startColor = SystemColor.inactiveCaption;
		Color endColor = SystemColor.activeCaption;
			
		int width = getWidth();
		int height = getHeight();

		Graphics2D g2 = (Graphics2D) g;
		Paint oldPaint = g2.getPaint();
		
		//g2.setPaint(new GradientPaint(0, 0, startColor, width, 0, endColor)); // Horizontal Gradient
		g2.setPaint(new GradientPaint(0, 0, startColor, 0, height, endColor)); // Vertical Gradient
		
		g2.fillRect(0, 0, width, height);
		g2.setPaint(oldPaint);
	    }
	}
	
	
    }
    
    
    

}
