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

package org.plazmaforge.framework.uwt.demo;


import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.CallbackResult;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Label;
import org.plazmaforge.framework.uwt.widget.TextField;
import org.plazmaforge.framework.uwt.wizard.IWizard;
import org.plazmaforge.framework.uwt.wizard.IWizardPage;
import org.plazmaforge.framework.uwt.wizard.WizardDialog;

public class DemoWizardDialog extends WizardDialog {

    private static final int FIELD_WIDTH = 200;

    private TextField firstNameField;
    private TextField middleNameField;
    private TextField lastNameField;
    private TextField jobTitleField;
    private TextField companyField;
    private TextField phoneField;
    private TextField emailField;
    private TextField addressField;


    private String firstName;
    private String middleName;
    private String lastName;
    private String jobTitle;
    private String company;
    private String phone;
    private String email;
    private String address;

    public DemoWizardDialog() {
	super();
	IWizard wizard = getWizard();
	addPage1(wizard);
	addPage2(wizard);
    }

    
    @Override
    protected void configure() {
	super.configure();
	setHeight(220);

    }
    
//    @Override
//    protected void buildContent(Composite parent) {
//	super.buildContent(parent);
//	IWizard wizard = getWizard();
//	addPage1(wizard);
//    }
    
    private void addPage1(IWizard wizard) {
	IWizardPage page = wizard.createPage();
	Composite content = page.getContent();
	GridLayout layout = new GridLayout(2);
	content.setLayout(layout);
	
	// First Name
	Label label = new Label("First Name");
	content.add(label);
	
	firstNameField = new TextField(FIELD_WIDTH);
	content.add(firstNameField);
	
	
	// Middle Name
	label = new Label("Middle Name");
	content.add(label);
	
	middleNameField = new TextField(FIELD_WIDTH);
	content.add(middleNameField);

	
	// Last Name
	label = new Label("Last Name");
	content.add(label);
	
	lastNameField = new TextField(FIELD_WIDTH);
	content.add(lastNameField);
	
	Label struts = new Label(" ");
	content.add(struts, new GridData(2, 1));
	
	// Job Title
	label = new Label("Job Title");
	content.add(label);
	
	jobTitleField = new TextField(FIELD_WIDTH);
	content.add(jobTitleField);


	// Company
	label = new Label("Company");
	content.add(label);
	
	companyField = new TextField(FIELD_WIDTH);
	content.add(companyField);

    }

    private void addPage2(IWizard wizard) {
	IWizardPage page = wizard.createPage();
	Composite content = page.getContent();
	GridLayout layout = new GridLayout(2);
	content.setLayout(layout);
	
	// Phone
	Label label = new Label("Phone");
	content.add(label);
	
	phoneField = new TextField(FIELD_WIDTH);
	content.add(phoneField);

	
	// Email
	label = new Label("Email");
	content.add(label);
	
	emailField = new TextField(FIELD_WIDTH);
	content.add(emailField);
	
	// Address
	label = new Label("Address");
	content.add(label);
	
	addressField = new TextField(FIELD_WIDTH);
	addressField.setHeight(50);
	content.add(addressField);

    }
    
    @Override
    protected void processResult(CallbackResult result) {
	// Page 1
	firstName = firstNameField.getValue();
	middleName = middleNameField.getValue();
	lastName = lastNameField.getValue();
	jobTitle = jobTitleField.getValue();
	company = companyField.getValue();
	
	// Page 2
	phone = phoneField.getValue();
	email = emailField.getValue();
	address = addressField.getValue();
    }

    public String getFirstName() {
	return firstName;
    }

    public String getMiddleName() {
	return middleName;
    }

    public String getLastName() {
	return lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    
}
