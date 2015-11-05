/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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
package org.plazmaforge.framework.core.datastorage.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.plazmaforge.framework.core.datastorage.DSDataConnector;
import org.plazmaforge.framework.core.datastorage.DSDataSet;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSExpressionParameter;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSParameter;
import org.plazmaforge.framework.core.datastorage.DataManager;
import org.plazmaforge.framework.core.datastorage.data.QueryAnalyzer;
import org.plazmaforge.framework.core.datastorage.data.QueryInfo;
import org.plazmaforge.framework.core.datastorage.data.QueryParameter;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.datastorage.sql.SQLResultSet;
import org.plazmaforge.framework.util.StringUtils;

/**
 * 
 * DSDataBuilder builds DSDataModel
 * 
 *         +-----------+
 *         |DSDataNode |           DSDataLevel 1
 *         +-----------+                | previous
 *           |       |                  |
 *           |       |                  |
 *           |       |                  |
 * +-----------+   +-----------+        | next
 * |DSDataNode |   | DSDataNode|   DSDataLevel 2
 * +-----------+   +-----------+
 * 
 * 
 * @author ohapon
 *
 */
public class DSDataBuilder {


    public DSDataModel buildDataModel(DSDataSource dataSource) throws DSException {
	List<DSDataSource> dataSources = null;
	if (dataSource != null) {
	    dataSources = new ArrayList<DSDataSource>();
	    dataSources.add(dataSource);
	}
	return buildDataModel(null, dataSources, null);
    }

    public DSDataModel buildDataModel(List<DSDataSource> dataSources) throws DSException {
	return buildDataModel(null, dataSources, null);
    }

    public DSDataModel buildDataModel(List<DSDataConnector> dataConnectors, List<DSDataSource> dataSources) throws DSException {
	return buildDataModel(dataConnectors, dataSources, null);
    }
    
    public DSDataModel buildDataModel(List<DSDataConnector> dataConnectors, List<DSDataSource> dataSources, List<DSDataSet> dataSets) throws DSException {
	DSDataBuilderContext context = new DSDataBuilderContext(dataConnectors, dataSources, dataSets);
	
	DSDataLevel rootLevel = buildRootLevel(context);
	DSDataModel dataModel = new DSDataModel();
	dataModel.setRootLevel(rootLevel);
	
	prepareLevels(dataModel);
	
	return dataModel;
    }
    
    protected DSDataLevel buildRootLevel(DSDataBuilderContext context) throws DSException {
	DSDataLevel rootLevel = new DSDataLevel();
	
	if (context.isEmptyData()) {
	    return rootLevel;
	}
	
	// Get first (root) level nodes
	List<DSDataNode> rootNodes = findChildren(context, null, true);
	if (rootNodes == null) {
	    return rootLevel;
	    //throw new DSException("Can't build RootLevel. Root DataNodes not found.");
	}
	populateNodes(context, null, rootNodes);
	populateLevel(rootLevel, rootNodes);
	
	return rootLevel;
    }
    
    protected void populateLevel(DSDataLevel level, List<DSDataNode> nodes) {
	if (level == null || nodes == null || nodes.isEmpty()) {
	    return;
	}
	level.setNodes(nodes);
	List<DSDataNode> nextNodes = new ArrayList<DSDataNode>();
	for (DSDataNode node: nodes) {
	    List<DSDataNode> children = node.getChildren();
	    if (children != null) {
		nextNodes.addAll(children);
	    }
	}
	if (nextNodes == null || nextNodes.isEmpty()) {
	    return;
	}
	DSDataLevel nextLevel = new DSDataLevel();
	
	// Assign prev/next level
	nextLevel.setPrevLevel(level);
	level.setNextLevel(nextLevel);
	
	populateLevel(nextLevel, nextNodes);
    }
    
    protected List<DSDataNode> findChildren(DSDataBuilderContext context, String parentName, boolean isRoot) {
	List<DSDataSource> dataSources = context.findDataSourceChildren(parentName, isRoot);
	if (isEmpty(dataSources)) {
	    return null;
	}
	List<DSDataNode> result = new ArrayList<DSDataNode>();
	for (DSDataSource dataSource : dataSources) {
	    DSDataNode dataNode = createDataNode(context, dataSource);
	    result.add(dataNode);
	}
	return result.isEmpty() ? null : result;
    }
    
    protected void populateNodes(DSDataBuilderContext context, DSDataNode parent, List<DSDataNode> nodes) {
	for (DSDataNode dataNode: nodes) {
	    
	    dataNode.setParent(parent);
	    
	    DSDataItem dataItem = dataNode.getDataItem(); 
	    String name = normalize(dataItem.getDataSource().getName());
	    
	    if (name == null) {
		continue;
	    }
	    
	    List<DSDataNode> children = findChildren(context, name, false);
	    if (children != null) {
		dataNode.setChildren(children);
		populateNodes(context, dataNode, children);
	    }
	}
	
	
    }
    
    protected DSDataNode createDataNode(DSDataBuilderContext context, DSDataSource dataSource) {
	
	// Create new DataNode
	DSDataNode dataNode = new DSDataNode();
	
	// Assign DataSource
	DSDataItem dataItem = new DSDataItem(dataSource);
	dataNode.setDataItem(dataItem);

	// Find DataConnector
	DSDataConnector dataConnector = context.findDataConnector(dataSource.getDataConnectorName());
	if (dataConnector != null) {
	    dataItem.setDataConnector(dataConnector);
	}
	
	// Find DataSet
	DSDataSet dataSet = context.findDataSetByDataSource(dataSource.getName());
	if (dataSet != null) {
	    dataItem.setResultSet(dataSet);
	    dataItem.setEmbeddedData(true);
	}
	
	// Initialize fields:
	// 1. By DataSource
	List<DSField> fields = dataSource.getFields();
	if (!isEmpty(fields)) {
	    dataItem.setFields(fields);
	    return dataNode;
	}
	// 2. By DataSet
	if (dataSet != null) {
	    fields = dataSet.getFields();
	    if (!isEmpty(fields)) {
		dataItem.setFields(fields);
	    }
	}
	
	return dataNode;
    }

    protected void prepareLevels(DSDataModel dataModel) {
	
	DSDataLevel level = dataModel.getRootLevel();
	List<DSDataLevel> levels = new ArrayList<DSDataLevel>();
	dataModel.setLevels(levels);
	
	if (level == null) {
	    return;
	}
	
	int index = 0;
	
	// Navigation by levels
	while (true) {
	    
	    level.setIndex(index);
	    levels.add(level);
	    
	    // Go next level
	    if (level.getNextLevel() == null) {
		break;
	    }
	    level = level.getNextLevel();
	    index++;
	}
    }
    
    public void generateQueryParameters(DSDataSource dataSource) {
	generateQueryParameters(dataSource, null, null, false);
    }
	
    public void generateQueryParameters(DSDataSource dataSource, String parameterPrefix, String parameterSuffix, boolean override) {
	if (dataSource == null) {
	    return;
	}
	String query = dataSource.getQueryText();
	query = normalize(query);
	if (query == null) {
	    return;
	}

	QueryAnalyzer queryAnalyzer = new QueryAnalyzer();
	QueryInfo queryInfo = queryAnalyzer.analyzeQuery(query);
	
	Set<QueryParameter> parameters = queryInfo.getUniqueParameters();
	int parameterCount = parameters == null ? 0: parameters.size();
	if (parameterCount == 0) {
	    return;
	}
	
	parameterPrefix = normalize(parameterPrefix);
	parameterSuffix = normalize(parameterSuffix);
	
	boolean isExpression = parameterPrefix != null || parameterSuffix != null;
	
	parameterPrefix = parameterPrefix == null ? "" : parameterPrefix;
	parameterSuffix = parameterSuffix == null ? "" : parameterSuffix;
	
	for (QueryParameter parameter : parameters) {
	    String parameterName = parameter.getName();
	    DSParameter findParameter = dataSource.getParameter(parameterName);
	    boolean needParameter = false;
	    
	    if (findParameter == null) {
		needParameter = true;
	    } else {
		if (override && isExpression) {
		    dataSource.removeParameter(findParameter);
		    needParameter = true;
		}
	    }
	    
	    if (needParameter) {
		DSParameter newParameter = null;
		if (isExpression) {
		    DSExpressionParameter expressionParameter = new DSExpressionParameter();
		    expressionParameter.setExpressionText(parameterPrefix + parameterName + parameterSuffix);
		    newParameter = expressionParameter;
		} else {
		    newParameter = new DSParameter();
		}
		newParameter.setName(parameterName);
		dataSource.addParameter(newParameter);
	    }
	}
	
    }
    
    protected String normalize(String str) {
	return StringUtils.normalizeString(str);
    }
    
    protected boolean isEmpty(String str) {
	return normalize(str) == null;
    }
    
    protected boolean isEmpty(Collection<?> collection) {
	return collection == null || collection.isEmpty();
    }
    
    protected boolean equals(String s1, String s2) {
	if (s1 == null || s2 == null){
	    return false;
	}
	return s1.equals(s2);
    }
    
}
