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

/**
 * 
 */
package org.plazmaforge.framework.report.model.design;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.data.LocalizedIdentifier;
import org.plazmaforge.framework.core.datastorage.DSDataConnector;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.datastorage.DSParameter;
import org.plazmaforge.framework.core.datastorage.DSVariable;
import org.plazmaforge.framework.core.datastorage.HasExpressionBuilder;
import org.plazmaforge.framework.report.model.base.Element;

/**
 * @author ohapon
 *
 */
public class Report implements Serializable, LocalizedIdentifier, HasExpressionBuilder {

    private static final long serialVersionUID = 2207663710009758227L;
    
    /**
     * Id of report
     */
    private String id;
    
    /**
     * Name of report
     */
    private String name;
    
    /**
     * Caption (NLS display name) of report
     */
    private String caption;

    /**
     * Caption (NLS description) of report
     */
    private String description;
    
    /**
     * Type of report (Table, Crosstab...)
     */
    private String type;

    
    private String reportFile;
    
    private String reportDir;
    
    /**
     * Expression/Script language
     */
    private String language;
    
    /**
     * Report parameters
     */
    private List<DSParameter> parameters;
    
    /**
     * Report variables
     */
    private List<DSVariable> variables;
    

    /**
     * Report DataConnectors
     */
    private List<DSDataConnector> dataConnectors;
    
    /**
     * Report DataSources
     */
    private List<DSDataSource> dataSources;

    
    private List<Template> templates;
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
    
    public String getDisplayName() {
  	return getCaption() == null ? getName() : getCaption();
    }    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    public String getReportFile() {
        return reportFile;
    }

    public void setReportFile(String reportFile) {
        this.reportFile = reportFile;
    }

    public String getReportDir() {
        return reportDir;
    }

    public void setReportDir(String reportDir) {
        this.reportDir = reportDir;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    ////

    public List<DSParameter> getParameters() {
	if (parameters == null) {
	    parameters = new ArrayList<DSParameter>();
	}
        return parameters;
    }

    public void setParameters(List<DSParameter> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(DSParameter parameter) {
        getParameters().add(parameter);
    }

    public void removeParameter(DSParameter parameter) {
        getParameters().remove(parameter);
    }

    public boolean hasParameters() {
	return parameters != null && !parameters.isEmpty();
    }

    public int getParameterCount() {
	return parameters == null ? 0 : parameters.size();
    }

    public List<DSVariable> getVariables() {
	if (variables == null) {
	    variables = new ArrayList<DSVariable>();
	}
        return variables;
    }

    public void setVariables(List<DSVariable> variables) {
        this.variables = variables;
    }
    
    public void addVariable(DSVariable variable) {
	getVariables().add(variable);
    }

    public void removeVariable(DSVariable variable) {
	getVariables().remove(variable);
    }

    public boolean hasVariables() {
	return variables != null && !variables.isEmpty();
    }

    public int getVariableCount() {
	return variables == null ? 0 : variables.size();
    }
    
    public List<DSDataConnector> getDataConnectors() {
	if (dataConnectors == null) {
	    dataConnectors = new ArrayList<DSDataConnector>();
	}
        return dataConnectors;
    }

    public void setDataConnectors(List<DSDataConnector> dataConnectors) {
        this.dataConnectors = dataConnectors;
    }
    
    public DSDataConnector getDataConnector() {
	return dataConnectors == null || dataConnectors.isEmpty() ? null : dataConnectors.get(0);
    }

    public void setDataConnector(DSDataConnector dataConnector) {
	getDataConnectors().add(0, dataConnector);
    }
    
    public void addDataConnector(DSDataConnector dataConnector) {
	getDataConnectors().add(dataConnector);
    }

    public void removeDataConnector(DSDataConnector dataConnector) {
	getDataConnectors().remove(dataConnector);
    }
    
    public boolean hasDataConnectors() {
 	return dataConnectors != null && !dataConnectors.isEmpty();
    }    

    public int getDataConnectorCount() {
 	return dataConnectors == null ? 0 : dataConnectors.size();
    }    
    
    public List<DSDataSource> getDataSources() {
	if (dataSources == null) {
	    dataSources = new ArrayList<DSDataSource>();
	}
        return dataSources;
    }

    public void setDataSources(List<DSDataSource> dataSources) {
        this.dataSources = dataSources;
    }

    public DSDataSource getDataSource() {
	return dataSources == null || dataSources.isEmpty() ? null : dataSources.get(0);
    }
    
    public void setDataSource(DSDataSource dataSource) {
	getDataSources().add(0, dataSource);
    }

    public void addDataSource(DSDataSource dataSource) {
	getDataSources().add(dataSource);
    }

    public void removeDataSource(DSDataSource dataSource) {
	getDataSources().remove(dataSource);
    }

    public boolean hasDataSources() {
 	return dataSources != null && !dataSources.isEmpty();
    }    

    public int getDataSourceCount() {
 	return dataSources == null ? 0 : dataSources.size();
    }    

    ////

    public List<Template> getTemplates() {
	if (templates == null) {
	    templates = new ArrayList<Template>();
	}
        return templates;
    }

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
    }
    
    public void addTemplate(Template template) {
	getTemplates().add(template);
    }
    
    public void removeTemplate(Template template) {
	getTemplates().remove(template);
    }
    
    public boolean hasTemplates() {
	return templates != null && !templates.isEmpty();
    }

    public int getTemplateCount() {
	return templates == null ? 0 : templates.size();
    }
    
    public boolean isEmpty() {
	return !hasTemplates();
    }

    @Override
    public List<DSExpression> buildExpressions() {
	List<DSExpression> expressions = new ArrayList<DSExpression>();
	populateExpressions(expressions);
	return expressions;
    }

    public void populateExpressions(List<DSExpression> expressions) {
	// TODO: add own report expressions

	if (hasParameters()) {
	    for (DSParameter parameter: parameters) {
		populateExpressions(expressions, parameter);
	    }
	}
	if (hasVariables()) {
	    for (DSVariable variable: variables) {
		populateExpressions(expressions, variable);
	    }
	}
	
	////

	if (hasDataConnectors()) {
	    for (DSDataConnector dataConnector: dataConnectors) {
		populateExpressions(expressions, dataConnector);
	    }
	}
	if (hasDataSources()) {
	    for (DSDataSource dataSource: dataSources) {
		populateExpressions(expressions, dataSource);
	    }
	}

	////
	
	if (hasTemplates()) {
	    for (Template template: templates) {
		populateExpressions(expressions, template);
	    }
	}
	
    }
    
    
    private static void populateExpressions(List<DSExpression> expressions, Object element) {
	Element.populateExpressions(expressions, element);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((caption == null) ? 0 : caption.hashCode());
	result = prime * result
		+ ((dataConnectors == null) ? 0 : dataConnectors.hashCode());
	result = prime * result
		+ ((dataSources == null) ? 0 : dataSources.hashCode());
	result = prime * result
		+ ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result
		+ ((language == null) ? 0 : language.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result
		+ ((parameters == null) ? 0 : parameters.hashCode());
	result = prime * result
		+ ((reportDir == null) ? 0 : reportDir.hashCode());
	result = prime * result
		+ ((reportFile == null) ? 0 : reportFile.hashCode());
	result = prime * result
		+ ((templates == null) ? 0 : templates.hashCode());
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	result = prime * result
		+ ((variables == null) ? 0 : variables.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Report other = (Report) obj;
	if (caption == null) {
	    if (other.caption != null)
		return false;
	} else if (!caption.equals(other.caption))
	    return false;
	if (dataConnectors == null) {
	    if (other.dataConnectors != null)
		return false;
	} else if (!dataConnectors.equals(other.dataConnectors))
	    return false;
	if (dataSources == null) {
	    if (other.dataSources != null)
		return false;
	} else if (!dataSources.equals(other.dataSources))
	    return false;
	if (description == null) {
	    if (other.description != null)
		return false;
	} else if (!description.equals(other.description))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (language == null) {
	    if (other.language != null)
		return false;
	} else if (!language.equals(other.language))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (parameters == null) {
	    if (other.parameters != null)
		return false;
	} else if (!parameters.equals(other.parameters))
	    return false;
	if (reportDir == null) {
	    if (other.reportDir != null)
		return false;
	} else if (!reportDir.equals(other.reportDir))
	    return false;
	if (reportFile == null) {
	    if (other.reportFile != null)
		return false;
	} else if (!reportFile.equals(other.reportFile))
	    return false;
	if (templates == null) {
	    if (other.templates != null)
		return false;
	} else if (!templates.equals(other.templates))
	    return false;
	if (type == null) {
	    if (other.type != null)
		return false;
	} else if (!type.equals(other.type))
	    return false;
	if (variables == null) {
	    if (other.variables != null)
		return false;
	} else if (!variables.equals(other.variables))
	    return false;
	return true;
    }
    
    
    
}
