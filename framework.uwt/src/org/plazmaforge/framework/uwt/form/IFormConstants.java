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

package org.plazmaforge.framework.uwt.form;

import org.plazmaforge.framework.uwt.widget.IActionConstants;

/**
 * Form Constants
 * 
 * @author ohapon
 *
 */
public interface IFormConstants extends IActionConstants {

    String ENTER_ACTION  = "Enter";
    String REFRESH_ACTION = "Refresh";
    String CHOOSE_ACTION = "Choose";
    
    String ADD_ACTION = "Add";
    String COPY_ACTION = "Copy";
    String DEL_ACTION = "Del";
    String EDIT_ACTION = "Edit";
    String VIEW_ACTION = "View";
    String ADD_CYCLE_ACTION = "AddCycle";
    String PRINT_ACTION = "Print";
    String SORT_ACTION = "Sort";
    String FIND_ACTION = "Find";
    
    String TREE_ADD_ACTION  = "TreeAdd";
    String TREE_EDIT_ACTION  = "TreeEdit";
    String TREE_DEL_ACTION  = "TreeDel";
    String TREE_ENTER_ACTION  = "TreeEnter";    
    
    String FILTER_ACTION = "Filter";
    String FILTER_INTERNAL_ACTION = "FilterInternal";
    String FILTER_FAST_ACTION = "FilterFast";
    String FILTER_ADVANCED_ACTION = "FilterAdvanced";
    String FILTER_ADD_ACTION = "FilterAadd";
    String FILTER_REMOVE_ACTION = "FilterRemove";
    String FILTER_CLEAR_ACTION = "FilterClear";
    String FILTER_PERIOD_ACTION = "FilterPeriod";
    String EXPORT_ACTION = "Export";
    
    String SETTINGS_ACTION = "Settings";
    
    String ITEM_ADD_ACTION = "ItemAdd";
    String ITEM_COPY_ACTION = "ItemCopy";
    String ITEM_EDIT_ACTION = "ItemEdit";
    String ITEM_DEL_ACTION = "ItemDel";
    
    String INFO_ACTION = "Info";
    String RESET_ACTION = "Reset";
    String CLEAR_ACTION = "Clear";
    
    String ADD_BY_COPY_ACTION = "AddByCopy"; // Add new object by copy selected object
    String ADD_BY_OBJECT_ACTION = "AddByObject"; // Add new object by other object
    String ADD_OTHER_OBJECTS_ACTION = "AddOtherObjects"; // Add new objects to other storage by selected object 
    
    String FIRST_PAGE_ACTION = "FirstPage";
    String PREV_PAGE_ACTION = "PrevPage";
    String NEXT_PAGE_ACTION = "NextPage";
    String LAST_PAGE_ACTION = "LastPage";

}
