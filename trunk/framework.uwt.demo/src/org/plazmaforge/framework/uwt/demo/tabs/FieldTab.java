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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.plazmaforge.framework.core.data.ValuePresenter;
import org.plazmaforge.framework.core.data.presenter.DatePresenter;
import org.plazmaforge.framework.core.data.presenter.DateTimePresenter;
import org.plazmaforge.framework.core.data.presenter.TimePresenter;
import org.plazmaforge.framework.uwt.event.SelectionEvent;
import org.plazmaforge.framework.uwt.event.SelectionListener;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.CheckBox;
import org.plazmaforge.framework.uwt.widget.ComboBox;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.DateField;
import org.plazmaforge.framework.uwt.widget.DateTimeField;
import org.plazmaforge.framework.uwt.widget.IntegerField;
import org.plazmaforge.framework.uwt.widget.Label;
import org.plazmaforge.framework.uwt.widget.MessageBox;
import org.plazmaforge.framework.uwt.widget.NumberField;
import org.plazmaforge.framework.uwt.widget.PasswordField;
import org.plazmaforge.framework.uwt.widget.SpinnerField;
import org.plazmaforge.framework.uwt.widget.TextArea;
import org.plazmaforge.framework.uwt.widget.TextField;
import org.plazmaforge.framework.uwt.widget.TimeField;

public class FieldTab extends AbstractTab {

    public FieldTab() {
    }

    @Override
    protected void createUI() {
	createContent(this);
    }
    
    
    private void createContent(Composite parent) {
	
	// Create grid layout with two columns
	GridLayout layout = new GridLayout(2); 
	setLayout(layout);
	
	// Text
	Label textLabel = new Label("Text");
	setToolTip(textLabel);
	add(textLabel);
	
	final TextField textField = new TextField();
	textField.setToolTip("Text field tooltip");
	add(textField);

	
	// Text Area
	Label textAreaLabel = new Label("Text Area");
	setToolTip(textAreaLabel);
	add(textAreaLabel);
	
	final TextArea textArea = new TextArea();
	textArea.setToolTip("Text Area tooltip");
	add(textArea);

	
	// Password
	Label passwordLabel = new Label("Password");
	setToolTip(passwordLabel);
	add(passwordLabel);
	
	final PasswordField passwordField = new PasswordField();
	passwordField.setToolTip("Password field tooltip");
	add(passwordField);

	// Number
	Label numberLabel = new Label("Number");
	setToolTip(numberLabel);
	add(numberLabel);
	
	final NumberField numberField = new NumberField();
	numberField.setToolTip("Number field tooltip");
	//numberField.setFormat("#0.000");
	add(numberField);

	
	// Integer
	Label integerLabel = new Label("Integer");
	setToolTip(integerLabel);
	add(integerLabel);
		
	final IntegerField integerField = new IntegerField();
	integerField.setToolTip("Integer field tooltip");
	add(integerField);

	// Date
	Label dateLabel = new Label("Date");
	setToolTip(dateLabel);
	add(dateLabel);
	
	final DateField dateField = new DateField();
	dateField.setToolTip("Date field tooltip");
	//dateField.setFormat("dd/MM/yyyy");
	
	add(dateField);

	// DateTime
	Label dateTimeLabel = new Label("DateTime");
	setToolTip(dateTimeLabel);
	add(dateTimeLabel);
	
	final DateTimeField dateTimeField = new DateTimeField();
	dateTimeField.setToolTip("DateTime field tooltip");
	//dateTimeField.setFormat("dd/MM/yyyy HH:mm:ss");
	
	add(dateTimeField);

	
	// Time
	Label timeLabel = new Label("Time");
	setToolTip(timeLabel);
	add(timeLabel);
	
	final TimeField timeField = new TimeField();
	timeField.setToolTip("Time field tooltip");
	//timeField.setFormat("HH:mm:ss");
	
	add(timeField);


	// Check
	Label checkLabel = new Label("Check");
	setToolTip(checkLabel);
	add(checkLabel);
	
	final CheckBox checkField = new CheckBox();
	checkField.setToolTip("Check tooltip");
	add(checkField);

	// Combo
	Label comboLabel = new Label("Combo");
	setToolTip(comboLabel);
	add(comboLabel);
	
	final ComboBox<String> comboField = new ComboBox<String>();
	comboField.setToolTip("Combo tooltip");
	List<String> items = new ArrayList<String>();
	items.add("Red");
	items.add("Green");
	items.add("Blue");
	comboField.setItems(items);
	
	add(comboField);

	// Spinner
	Label spinnerLabel = new Label("Spinner");
	setToolTip(spinnerLabel);
	add(spinnerLabel);

	final SpinnerField spinnerField = new SpinnerField(0, -100.00, 100.00, 0.01, 2);
	spinnerField.setToolTip("Spinner tooltip");
	
	add(spinnerField);

	final DatePresenter datePresenter = new DatePresenter();
	final DateTimePresenter dateTimePresenter = new DateTimePresenter();
	final TimePresenter timePresenter = new TimePresenter();
	
	Button getButton = new Button("Get values");
	getButton.addSelectionListener(new SelectionListener() {
	    public void select(SelectionEvent event) {
		StringBuffer buf = new StringBuffer();
		addValueInfo(buf, "Text", textField.getValue());
		addValueInfo(buf, "Text Area", textArea.getValue());
		addValueInfo(buf, "Password", passwordField.getValue());
		addValueInfo(buf, "Number", numberField.getValue());
		addValueInfo(buf, "Integer", integerField.getValue());
		addValueInfo(buf, "Date", dateField.getValue(), datePresenter);
		addValueInfo(buf, "DateTime", dateTimeField.getValue(), dateTimePresenter);
		addValueInfo(buf, "Time", timeField.getValue(), timePresenter);
		addValueInfo(buf, "Check", checkField.getValue());
		addValueInfo(buf, "Combo", comboField.getValue());
		addValueInfo(buf, "Spinner", spinnerField.getValue());
		//addValueInfo(buf, "Slider", sliderField.getValue());
		MessageBox.information("Get Values", buf.toString());
	    }
	});

	Button setButton = new Button("Set values");
	setButton.addSelectionListener(new SelectionListener() {
	    public void select(SelectionEvent event) {
		textField.setValue("Simple text");
		textArea.setValue("Multi\nline\ntext");
		passwordField.setValue("");
		numberField.setValue(123.45);
		integerField.setValue(543);
		dateField.setValue(new Date());
		dateTimeField.setValue(new Date());
		timeField.setValue(new Date());
		checkField.setValue(true);
		comboField.setValue("Green");
		spinnerField.setValue(87.65);
		//sliderField.setValue(30);
	    }
	});

	add(getButton);
	add(setButton);
	
	return;
    }
    
    private void addValueInfo(StringBuffer buf, String type, Object value) {
	addValueInfo(buf, type, value, null);
    }
    
    private void addValueInfo(StringBuffer buf, String type, Object value, ValuePresenter presenter) {
	String strValue = presenter != null ? presenter.toString(value) : ("" + value);
	buf.append(type + " = '" + strValue + "'");
	buf.append("\n");
    }

}
