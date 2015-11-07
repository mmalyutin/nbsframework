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

package org.plazmaforge.framework.uwt.demo.tabs;


import org.plazmaforge.framework.uwt.event.SelectionEvent;
import org.plazmaforge.framework.uwt.event.SelectionListener;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.ImageBox;
import org.plazmaforge.framework.uwt.widget.Label;
import org.plazmaforge.framework.uwt.widget.Link;
import org.plazmaforge.framework.uwt.widget.MessageBox;
import org.plazmaforge.framework.uwt.widget.Slider;

public class AdvancedTab extends AbstractTab {

    public AdvancedTab() {
    }

    @Override
    protected void createUI() {
	createContent(this);
    }
    
    
    private void createContent(Composite parent) {
	
	// Create grid layout with two columns
	GridLayout layout = new GridLayout(2); 
	setLayout(layout);

	// Link /////////////////////////////////////////////////////////////////
	Label linkLabel = new Label("Link");
	setToolTip(linkLabel);
	add(linkLabel);

	final Link linkField = new Link("www.plazmaforge.org");
	linkField.addSelectionListener(new SelectionListener() {

	    @Override
	    public void select(SelectionEvent event) {
		MessageBox.information("Link '" + linkField.getText() + "' was selected");
	    }
	    
	});
	linkField.setToolTip("Link tooltip");
	add(linkField);

	
	// Slider //////////////////////////////////////////////////////////////
	Label sliderLabel = new Label("Slider");
	setToolTip(sliderLabel);
	add(sliderLabel);

	final Slider sliderField = new Slider();
	sliderField.setToolTip("Slider tooltip");
	add(sliderField);

	// Image ///////////////////////////////////////////////////////////////
	Label imageLabel = new Label("Image");
	setToolTip(imageLabel);
	add(imageLabel);

	final ImageBox imageBox = new ImageBox();
	imageBox.setImage("widget/folder.gif");
	imageBox.setToolTip("Image tooltip");
	add(imageBox);

	return;
    }
    

}
