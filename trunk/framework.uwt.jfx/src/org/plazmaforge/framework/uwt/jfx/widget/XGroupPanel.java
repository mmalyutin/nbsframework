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

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * 
 * @author ohapon
 *
 */
public class XGroupPanel extends StackPane implements HasContent {

    String TITLE_STYLE = "-fx-background-color: white;"
	    + " -fx-translate-y: -16;";
	    
    String CONTENT_STYLE = "-fx-padding: 26 10 10 10;";
    
    String BORDER_STYLE = "-fx-content-display: top;"
	    + " -fx-border-insets: 20 15 15 15;"
	    //+ " -fx-background-color: white;"
	    + " -fx-border-color: black;"
	    + " -fx-border-width: 2;";
    
    
    protected Label titleLabel;
    
    protected StackPane contentPanel; 
    
    protected Node content;
    
    
    public XGroupPanel() {
	initialize(null, null);
    }
    
    public XGroupPanel(String title, Node content) {
	initialize(title, content);
    }
    
    protected void initialize(String titleString, Node content) {
	
	titleLabel = new Label(" " + titleString + " ");
	initTitleStyle(titleLabel);
	StackPane.setAlignment(titleLabel, Pos.TOP_CENTER);
	
	setText(titleString);

	contentPanel = new StackPane();
	initContentStyle(contentPanel);
	
	setContent(content);
	
	initBorderStyle(this);
	getChildren().addAll(titleLabel, contentPanel);
	
    }
    
    protected void initTitleStyle(Node node) {
	// node.getStyleClass().add("bordered-titled-title");
	node.setStyle(TITLE_STYLE);
    }

    protected void initContentStyle(Node node) {
	// node.getStyleClass().add("bordered-titled-content");
	node.setStyle(CONTENT_STYLE);
    }

    protected void initBorderStyle(Node node) {
	// node.getStyleClass().add("bordered-titled-border");
	node.setStyle(BORDER_STYLE);
    }
    
    protected String normalyzeTitle(String title) {
	if (title == null || title.isEmpty()) {
	    return "";
	}
	return " " + title + " ";
    }
    
    @Override
    public void setContent(Node content) {
	if (this.content != null) {
	    contentPanel.getChildren().remove(this.content);
	}
	this.content = content;
	if (content == null) {
	    return;
	}
	contentPanel.getChildren().add(content);
    }
    
    @Override
    public Node getContent() {
	return content;
    }
    
    public void setText(String text) {
	titleLabel.setText(normalyzeTitle(text));
    }
    
    public String getText() {
	return titleLabel.getText().trim();
    }
    
}
