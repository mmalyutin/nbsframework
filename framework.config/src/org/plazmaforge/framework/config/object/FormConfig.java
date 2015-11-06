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


package org.plazmaforge.framework.config.object;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author ohapon
 *
 */
public class FormConfig extends UIObjectConfig implements IFormConfig {

    private static final long serialVersionUID = -460911673556518029L;

    private String formType;

    private List<IActionDescriptorConfig> actionDescriptors;

    private List<IReportDescriptorConfig> reportDescriptors;

    public String getFormType() {
	return formType;
    }

    public void setFormType(String formType) {
	this.formType = formType;
    }

    public List<IActionDescriptorConfig> getActionDescriptors() {
	if (actionDescriptors == null) {
	    actionDescriptors = new ArrayList<IActionDescriptorConfig>(); 
	}
	return actionDescriptors;
    }

    public void addActionDescriptor(IActionDescriptorConfig actionDescriptor) {
	getActionDescriptors().add(actionDescriptor);
    }

    public void removeActionDescriptor(IActionDescriptorConfig actionDescriptor) {
	getActionDescriptors().remove(actionDescriptor);
    }
    
    
    public void setActionDescriptors(List<IActionDescriptorConfig> actionDescriptors) {
        this.actionDescriptors = actionDescriptors;
    }

    ////
    

    public List<IReportDescriptorConfig> getReportDescriptors() {
	if (reportDescriptors == null) {
	    reportDescriptors = new ArrayList<IReportDescriptorConfig>(); 
	}
	return reportDescriptors;
    }

    public void addReportDescriptor(IReportDescriptorConfig reportDescriptor) {
	getReportDescriptors().add(reportDescriptor);
    }
    
    public void removeReportDescriptor(IReportDescriptorConfig reportDescriptor) {
	getReportDescriptors().remove(reportDescriptor);
    }

    public void setReportDescriptors(List<IReportDescriptorConfig> reportDescriptor) {
        this.reportDescriptors = reportDescriptor;
    }
    
    ////
    
    public IReportDescriptorConfig getReportDescriptor(IReportConfig report) {
	if (report == null) {
	    return null;
	}
	return getReportDescriptorByName(report.getConfigName());
    }
    
    public IReportDescriptorConfig getReportDescriptorByName(String reportName) {
	if (reportName == null) {
	    return null;
	}
	List<IReportDescriptorConfig> reportDescriptors = getReportDescriptors();
	if (reportDescriptors == null) {
	    return null;
	}
	for (IReportDescriptorConfig reportDescriptor : reportDescriptors) {
	    if (reportName.equals(reportDescriptor.getRef())) {
		return reportDescriptor;
	    }
	}
	return null;
    }
    
    
    ////
    
    public void removeReportConfig(IReportConfig report) {
	IReportDescriptorConfig reportDescriptor = getReportDescriptor(report);
	removeReportDescriptor(reportDescriptor);
    }
    
    public void addReportConfig(IReportConfig report) {
	IReportDescriptorConfig reportDescriptor = new ReportDescriptorConfig();
	reportDescriptor.setRef(report.getName());
	addReportDescriptor(reportDescriptor);
    }
}
