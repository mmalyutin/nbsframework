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
package org.plazmaforge.framework.report.storage.xml.report;

import org.plazmaforge.framework.report.storage.xml.XMLInfo;


/**
 * @author ohapon
 *
 */
public interface XMLReportInfo extends XMLInfo {

    
    
    //////////////////////////////////////////////////////////////
    // REPORT
    //////////////////////////////////////////////////////////////

    String XML_REPORT = "report";
    
    String XML_TEMPLATES = "templates";
    
    String XML_TEMPLATE = "template";
    
    String XML_BANDS = "bands";
    
    String XML_BAND = "band";
    
    String XML_REPORT_GROUPS = "report-groups";
    
    String XML_REPORT_GROUP = "report-group";
    
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


    String XML_ATTR_REPORT_HEADER_ON_PAGE = "report-header-on-page";
    
    String XML_ATTR_REPORT_FOOTER_ON_PAGE = "report-footer-on-page";
    
}
