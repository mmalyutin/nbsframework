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

public class FormEvents {

    // FORM EVENTS

    // Load
    public static final String LoadData = "LoadData";
    public static final String BeforeLoadData = "BeforeLoadData";
    public static final String AfterLoadData = "AfterLoadData";

    // Save: Create or Store
    public static final String SaveData = "SaveData";
    public static final String BeforeSaveData = "BeforeSaveData";
    public static final String AfterSaveData = "AfterSaveData";

    // Create
    public static final String CreateData = "CreateData";
    public static final String BeforeCreateData = "BeforeCreateData";
    public static final String AfterCreateData = "AfterCreateData";

    // Store
    public static final String StoreData = "StoreData";
    public static final String BeforeStoreData = "BeforeStoreData";
    public static final String AfterStoreData = "AfterStoreData";

    // Remove
    public static final String RemoveData = "RemoveData";
    public static final String BeforeRemoveData = "BeforeRemoveData";
    public static final String AfterRemoveData = "AfterRemoveData";
    
    // Modify: Create or Store or Remove
    public static final String ModifyData = "ModifyData";
    public static final String BeforeModifyData = "BeforeModifyData";
    public static final String AfterModifyData = "AfterModifyData";
    
    

    public static final String CancelData = "CancelData";
    public static final String BeforeCancelData = "BeforeCancelData";
    public static final String AfterCancelData = "AfterCancelData";

    public static final String FindData = "FindData";
    public static final String BeforeFindData = "BeforeFindData";
    public static final String AfterFindData = "AfterFindData";

    public static final String FilterData = "FilterData";
    public static final String BeforeFilterData = "BeforeFilterData";
    public static final String AfterFilterData = "AfterFilterData";

    public static final String UpdateData = "UpdateData"; 
    public static final String UpdateForm = "UpdateForm";
}
