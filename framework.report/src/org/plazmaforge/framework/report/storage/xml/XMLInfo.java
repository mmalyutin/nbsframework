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

package org.plazmaforge.framework.report.storage.xml;


/**
 * @author ohapon
 *
 */
public interface XMLInfo {

    boolean USE_DATA_TYPE_IN_EXPRESSION = false;
    
    //////////////////////////////////////////////////////////////
    // BASE
    //////////////////////////////////////////////////////////////

    String XML_ATTR_ID = "id";
    
    String XML_ATTR_NAME = "name";
    
    String XML_ATTR_CAPTION = "caption";
    
    String XML_ATTR_DESCRIPTION = "description";    
    
    String XML_ATTR_TYPE = "type";
    
    String XML_ATTR_FORMAT = "format";

    String XML_ATTR_X = "x";
    
    String XML_ATTR_Y = "y";

    String XML_ATTR_WIDTH = "width";
    
    String XML_ATTR_HEIGHT = "height";
    
    String XML_ATTR_LEFT = "left";

    String XML_ATTR_RIGHT = "right";
    
    String XML_ATTR_TOP = "top";

    String XML_ATTR_BOTTOM = "bottom";

    String XML_ATTR_COLSPAN = "colspan";
    
    String XML_ATTR_ROWSPAN = "rowspan";
    
    String XML_ATTR_HORIZONTAL_ALIGN = "horizontal-align";
    
    String XML_ATTR_VERTICAL_ALIGN = "vertical-align";
    
    
    String XML_ATTR_VALUE = "value";
    
    String XML_ATTR_DEFAULT_VALUE = "default-value";
    
    
    String XML_ATTR_BACKGROUND = "background";
    
    String XML_ATTR_FOREGROUND = "foreground";
    
    String XML_ATTR_FONT = "font";
    
    
    String XML_ATTR_MARGIN = "margin";
    
    String XML_ATTR_MARGIN_LEFT = "margin-left";

    String XML_ATTR_MARGIN_RIGHT = "margin-right";
    
    String XML_ATTR_MARGIN_TOP = "margin-top";

    String XML_ATTR_MARGIN_BOTTOM = "margin-bottom";
    
    
    String XML_ATTR_PADDING = "padding";
    
    String XML_ATTR_PADDING_LEFT = "padding-left";

    String XML_ATTR_PADDING_RIGHT = "padding-right";
    
    String XML_ATTR_PADDING_TOP = "padding-top";

    String XML_ATTR_PADDING_BOTTOM = "padding-bottom";
    

    String XML_ATTR_CELL_BORDER_RULE = "cell-border-rule";
    
    String XML_ATTR_CELL_LINE = "cell-line";
    
    String XML_ATTR_COLUMN_LINE = "column-line";
    
    String XML_ATTR_ROW_LINE = "row-line";
    
    
    String XML_ATTR_BORDER = "border";
    
    String XML_ATTR_BORDER_STYLE = "border-style";
    
    String XML_ATTR_BORDER_COLOR = "border-color";
    
    
    String XML_ATTR_BORDER_LEFT = "border-left";

    String XML_ATTR_BORDER_RIGHT = "border-right";
    
    String XML_ATTR_BORDER_TOP = "border-top";

    String XML_ATTR_BORDER_BOTTOM = "border-bottom";
    
    
    String XML_ATTR_BORDER_LEFT_STYLE = "border-left-style";

    String XML_ATTR_BORDER_RIGHT_STYLE = "border-right-style";
    
    String XML_ATTR_BORDER_TOP_STYLE = "border-top-style";

    String XML_ATTR_BORDER_BOTTOM_STYLE = "border-bottom-style";
    

    String XML_ATTR_BORDER_LEFT_COLOR = "border-left-color";

    String XML_ATTR_BORDER_RIGHT_COLOR = "border-right-color";
    
    String XML_ATTR_BORDER_TOP_COLOR = "border-top-color";

    String XML_ATTR_BORDER_BOTTOM_COLOR = "border-bottom-color";
    
    
    String XML_ATTR_DATA_TYPE = "data-type";
    
    String XML_ATTR_EXPRESSION = "expression";
    
    String XML_ATTR_PATH = "path";
    
    String XML_ATTR_LANGUAGE = "language";
    

    String XML_MARGIN = "margin";
    
    String XML_PADDING = "padding";
    
    String XML_PAGE_SETUP = "page-setup";
    
    String XML_COLUMNS = "columns";
    
    String XML_COLUMN = "column";

    String XML_ROWS = "rows";
    
    String XML_ROW = "row";
    
    String XML_CELLS = "cells";
    
    String XML_CELL = "cell";
    
    String XML_GRID = "grid";
    
    String XML_VALUE = "value";
    
    String XML_DEFAULT_VALUE = "default-value";    
    
    String XML_EXPRESSION = "expression";
    
    String XML_INIT_EXPRESSION = "init-expression";
    
    String XML_PROPERTIES = "properties";
    
    String XML_PROPERTY = "property";

    String XML_ATTR_RESET_TYPE = "reset-type";
    
    String XML_ATTR_RESET_NAME = "reset-name";
    
    String XML_ATTR_AGGREGATION = "aggregation";
    
    ////
    
    String XML_ELEMENTS = "elements";
    
    String XML_ELEMENT = "element";
    
    
    // DATA-STORAGE
    
    String XML_DATA_CONNECTORS = "data-connectors";
    
    String XML_DATA_CONNECTOR = "data-connector";
    
    String XML_DATA_SOURCES = "data-sources";
    
    String XML_DATA_SOURCE = "data-source";

    String XML_DATA_SETS = "data-sets";
    
    String XML_DATA_SET = "data-set";
    
    String XML_PARAMETERS = "parameters";
    
    String XML_PARAMETER = "parameter";
    
    String XML_VARIABLES = "variables";
    
    String XML_VARIABLE = "variable";
    
    String XML_FIELDS = "fields";
    
    String XML_FIELD = "field";
    
    String XML_QUERY = "query";
    
    String XML_GROUPS = "groups";
    
    String XML_GROUP = "group";

    String XML_ORDERS = "orders";
    
    String XML_ORDER = "order";
    
    String XML_FILTERS = "filters";
    
    String XML_FILTER = "filter";


    String XML_ATTR_FIELD = "field";
    
    String XML_ATTR_OPERATOR = "operator";
    
    String XML_ATTR_ASC = "asc";
    
    
}
