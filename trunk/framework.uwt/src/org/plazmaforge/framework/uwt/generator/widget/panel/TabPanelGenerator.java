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
package org.plazmaforge.framework.uwt.generator.widget.panel;

import java.util.List;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.generator.ScopeContext;
import org.plazmaforge.framework.uwt.generator.SourceWriter;
import org.plazmaforge.framework.uwt.widget.panel.TabItem;
import org.plazmaforge.framework.uwt.widget.panel.TabPanel;


/**
 * @author ohapon
 *
 */
public class TabPanelGenerator extends PanelGenerator {

    @Override
    public boolean accept(String type) {
	return eq(type, UIBuilder.TAB_PANEL_TYPE);
    }
    
    @Override
    protected void generatePopulateBody(ScopeContext context, IData data, String bean, SourceWriter sw) {
	generateTabContentChildren(context, data, bean, sw);
    }
    
    protected void generateTabContentChildren(ScopeContext context, IData data, String bean, SourceWriter sw) {
	List<IData> children = getChildrenOfNode(data, TabPanel.PROPERTY_TAB_ITEMS);
	generateTabContentChildren(context, bean, children, sw);
    }
    
    protected void generateTabContentChildren(ScopeContext context, String bean, List<IData> children, SourceWriter sw) {
	 if (children == null || children.isEmpty()) {
	     return;
	 }
	 String TabItemClass = prepareClassName(context, TabItem.class.getName());
	 for (IData child: children) {
	     sw.println();
	     String instanceRef = generateCreateData(context, "$#", child, sw);
	     if  (instanceRef == null) {
		 continue;
	     }
	     sw.println((bean == null ? "" : (bean + ".")) + "addItem((" + TabItemClass + ") " + instanceRef + ");");
	 }
   }
    
}
