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

package org.plazmaforge.framework.datastorage.support.json;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.plazmaforge.framework.core.datastorage.AbstractFileResultSet;
import org.plazmaforge.framework.core.datastorage.DSStructuredResultSet;
import org.plazmaforge.framework.core.exception.DSException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * 
 * @author ohapon
 *
 */
public class JSONResultSet extends AbstractFileResultSet implements DSStructuredResultSet {

    // the JSON select expression that gives the nodes to iterate
    private String selectExpression;

    private Iterator<JsonNode> jsonNodesIterator;

    // the current node
    private JsonNode currentJsonNode;

    private final String PROPERTY_SEPARATOR = ".";

    private final String ARRAY_LEFT = "[";

    private final String ARRAY_RIGHT = "]";

    private final String ATTRIBUTE_LEFT = "(";

    private final String ATTRIBUTE_RIGHT = ")";

    // the JSON tree as it is obtained from the JSON source
    private JsonNode jsonTree;

    private ObjectMapper mapper;

    private boolean init;

    public JSONResultSet(Reader reader) throws DSException {
	try {

	    this.reader = reader;
	    this.mapper = new ObjectMapper();

	    mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	    mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
	    mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

	    this.jsonTree = mapper.readTree(reader);
	    beforeFirst();
	} catch (JsonProcessingException e) {
	    throw new DSException(e);
	} catch (IOException e) {
	    throw new DSException(e);
	}
    }
    
    protected void initPosition() throws DSException {
	if (init) {
	    return;
	}
	init = true;
	beforeFirst();
    }          
	
    public void beforeFirst() throws DSException {
	if (jsonTree == null || jsonTree.isMissingNode()) {
	    throw new DSException("No JSON data to operate on!");
	}

	currentJsonNode = null;
	JsonNode result = getJsonData(jsonTree, selectExpression);
	if (result != null && result.isObject()) {
	    final List<JsonNode> list = new ArrayList<JsonNode>();
	    list.add(result);
	    jsonNodesIterator = new Iterator<JsonNode>() {
		private int count = -1;

		public void remove() {
		    list.remove(count);
		}

		public JsonNode next() {
		    count++;
		    return list.get(count);
		}

		public boolean hasNext() {
		    return count < list.size() - 1;
		}
	    };
	} else if (result != null && result.isArray()) {
	    jsonNodesIterator = result.elements();
	}
    }

    public boolean next() throws DSException {
	initPosition();
	if (jsonNodesIterator == null || !jsonNodesIterator.hasNext()) {
	    return false;
	}
	currentJsonNode = jsonNodesIterator.next();
	return true;
    }

    protected JsonNode getJsonData(JsonNode rootNode, String jsonExpression) throws DSException {
	if (jsonExpression == null || jsonExpression.length() == 0) {
	    return rootNode;
	}
	JsonNode tempNode = rootNode;
	StringTokenizer tokenizer = new StringTokenizer(jsonExpression, PROPERTY_SEPARATOR);

	while (tokenizer.hasMoreTokens()) {
	    String currentToken = tokenizer.nextToken();
	    int currentTokenLength = currentToken.length();
	    int indexOfLeftSquareBracket = currentToken.indexOf(ARRAY_LEFT);

	    // got Left Square Bracket - LSB
	    if (indexOfLeftSquareBracket != -1) {
		// a Right Square Bracket must be the last character in the
		// current token
		if (currentToken.lastIndexOf(ARRAY_RIGHT) != (currentTokenLength - 1)) {
		    throw new DSException("Invalid expression: " + jsonExpression + "; current token " + currentToken + " not ended properly");
		}

		// LSB not first character
		if (indexOfLeftSquareBracket > 0) {
		    // extract nodes at property
		    String property = currentToken.substring(0, indexOfLeftSquareBracket);
		    tempNode = goDownPathWithAttribute(tempNode, property);

		    String arrayOperators = currentToken.substring(indexOfLeftSquareBracket);
		    StringTokenizer arrayOpsTokenizer = new StringTokenizer(arrayOperators, ARRAY_RIGHT);
		    while (arrayOpsTokenizer.hasMoreTokens()) {
			if (!tempNode.isMissingNode() && tempNode.isArray()) {
			    String currentArrayOperator = arrayOpsTokenizer.nextToken();
			    tempNode = tempNode.path(Integer.parseInt(currentArrayOperator.substring(1)));
			}
		    }
		} else { // LSB first character
		    String arrayOperators = currentToken.substring(indexOfLeftSquareBracket);
		    StringTokenizer arrayOpsTokenizer = new StringTokenizer(arrayOperators, ARRAY_RIGHT);
		    while (arrayOpsTokenizer.hasMoreTokens()) {
			if (!tempNode.isMissingNode() && tempNode.isArray()) {
			    String currentArrayOperator = arrayOpsTokenizer.nextToken();
			    tempNode = tempNode.path(Integer.parseInt(currentArrayOperator.substring(1)));
			}
		    }
		}
	    } else {
		tempNode = goDownPathWithAttribute(tempNode, currentToken);
	    }
	}

	return tempNode;
    }
	
    protected JsonNode goDownPathWithAttribute(JsonNode rootNode, String pathWithAttributeExpression) throws DSException {
	// check if path has attribute selector
	int indexOfLeftRoundBracket = pathWithAttributeExpression.indexOf(ATTRIBUTE_LEFT);
	if (indexOfLeftRoundBracket != -1) {

	    // a Right Round Bracket must be the last character in the current
	    // pathWithAttribute
	    if (pathWithAttributeExpression.indexOf(ATTRIBUTE_RIGHT) != (pathWithAttributeExpression.length() - 1)) {
		throw new DSException("Invalid attribute selection expression: " + pathWithAttributeExpression);
	    }

	    if (rootNode != null && !rootNode.isMissingNode()) {

		String path = pathWithAttributeExpression.substring(0, indexOfLeftRoundBracket);

		// an expression in a form like: attribute==value
		String attributeExpression = pathWithAttributeExpression.substring(indexOfLeftRoundBracket + 1,	pathWithAttributeExpression.length() - 1);

		JsonNode result = null;
		if (rootNode.isObject()) {
		    // select only those nodes for which the attribute
		    // expression applies
		    if (!rootNode.path(path).isMissingNode()) {
			if (rootNode.path(path).isObject()) {
			    if (isValidExpression(rootNode.path(path), attributeExpression)) {
				result = rootNode.path(path);
			    }
			} else if (rootNode.path(path).isArray()) {
			    result = mapper.createArrayNode();
			    for (JsonNode node : rootNode.path(path)) {
				if (isValidExpression(node, attributeExpression)) {
				    ((ArrayNode) result).add(node);
				}
			    }
			}
		    }
		} else if (rootNode.isArray()) {
		    result = mapper.createArrayNode();
		    for (JsonNode node : rootNode) {
			JsonNode deeperNode = node.path(path);
			if (!deeperNode.isMissingNode()) {
			    if (deeperNode.isArray()) {
				for (JsonNode arrayNode : deeperNode) {
				    if (isValidExpression(arrayNode, attributeExpression)) {
					((ArrayNode) result).add(arrayNode);
				    }
				}
			    } else if (isValidExpression(deeperNode, attributeExpression)) {
				((ArrayNode) result).add(deeperNode);
			    }
			}
		    }
		}
		return result;
	    }

	} else { // path has no attribute selectors
	    return goDownPath(rootNode, pathWithAttributeExpression);
	}
	return rootNode;
    }

    protected JsonNode goDownPath(JsonNode rootNode, String simplePath) {
	if (rootNode != null && !rootNode.isMissingNode()) {
	    JsonNode result = null;
	    if (rootNode.isObject()) {
		result = rootNode.path(simplePath);
	    } else if (rootNode.isArray()) {
		result = mapper.createArrayNode();
		for (JsonNode node : rootNode) {
		    JsonNode deeperNode = node.path(simplePath);
		    if (!deeperNode.isMissingNode()) {
			if (deeperNode.isArray()) {
			    for (JsonNode arrayNode : deeperNode) {
				((ArrayNode) result).add(arrayNode);
			    }
			} else {
			    ((ArrayNode) result).add(deeperNode);
			}
		    }
		}
	    }
	    return result;
	}
	return rootNode;
    }	
	
    /**
     * Validates an attribute expression on a JsonNode
     * 
     * @param operand
     * @param attributeExpression
     * @throws DSException
     */
    protected boolean isValidExpression(JsonNode operand, String attributeExpression) throws DSException {
	// TODO: Utilities
	return evaluateJsonExpression(operand, attributeExpression);
    }	
	
////
	
	public static boolean evaluateJsonExpression(JsonNode contextNode, String attributeExpression) throws DSException {
		
		if (attributeExpression == null) {
			return true;
		}
		
		String attribute = null;
		JsonOperatorEnum operator = null;
		String value = null;
		boolean result = false;
		
		for (JsonOperatorEnum joe: JsonOperatorEnum.values()) {
			int indexOfOperator = attributeExpression.indexOf(joe.getValue());
			if (indexOfOperator != -1) {
				operator = joe;
				attribute = attributeExpression.substring(0, indexOfOperator).trim();
				value = attributeExpression.substring(indexOfOperator + joe.getValue().length()).trim();
				break;
			}
		}
		
		if (operator == null) {
			StringBuffer possibleOperations = new StringBuffer();
			for (JsonOperatorEnum op: JsonOperatorEnum.values()) {
				possibleOperations.append(op.getValue()).append(",");
			}
			throw new DSException("Unknown operator in expression: " + attributeExpression + "; Operator must be one of: " + possibleOperations);
		}
		
		if (attribute != null && operator != null && value != null) {
			// going down the path of the attribute must return a value node 
			if (!contextNode.path(attribute).isValueNode()) {
				result = false;	
			} else {
				String contextValue = contextNode.path(attribute).asText();
				switch(operator) {
				case LT:
					try {
						result = Double.parseDouble(contextValue) < Double.parseDouble(value);
					} catch (NumberFormatException nfe) {
						result = false;
					}
					break;
				case LE:
					try {
						result = Double.parseDouble(contextValue) <= Double.parseDouble(value);
					} catch (NumberFormatException nfe) {
						result = false;
					}
					break;
				case GT:
					try {
						result = Double.parseDouble(contextValue) > Double.parseDouble(value);
					} catch (NumberFormatException nfe) {
						result = false;
					}
					break;
				case GE:
					try {
						result = Double.parseDouble(contextValue) >= Double.parseDouble(value);
					} catch (NumberFormatException nfe) {
						result = false;
					}
					break;
				case EQ:
					result = contextValue.equals(value);
					break;
				case NE:
					result = !contextValue.equals(value);
					break;
				}
			}
		}
		
		return result;
	}
	

	public enum JsonOperatorEnum {
		LT("<", "Lower than"),
		LE("<=", "Lower or equal"),
		GT(">", "Greater than"),
		GE(">=", "Greater or equal"),
		EQ("==", "Equal"),
		NE("!=", "Not equal");

		private final transient String value;
		private final transient String name;

		private JsonOperatorEnum(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public final String getValue() {
			return value;
		}
		
		public String getName() {
			return name;
		}
	}


	@Override
	public Object getValue(String name) throws DSException {
	    // TODO Auto-generated method stub
	    return null;
	}	
}
