package org.plazmaforge.framework.erm.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JoinDef implements Serializable {

    private TableDef table1;
    
    private TableDef table2;
    
    private String columnName1;
    
    private String columnName2;


    private List<ValueFilterDef> filters;
    
    
    private JoinType joinType;

    
    public TableDef getTable1() {
        return table1;
    }

    public void setTable1(TableDef table1) {
        this.table1 = table1;
    }

    public TableDef getTable2() {
        return table2;
    }

    public void setTable2(TableDef table2) {
        this.table2 = table2;
    }
    
    public String getTableName1() {
        return table1 == null ? null : table1.getTableName();
    }

    public String getTableName2() {
        return table2 == null ? null : table2.getTableName();
    }


    public String getColumnName1() {
        return columnName1;
    }

    public void setColumnName1(String columnName1) {
        this.columnName1 = columnName1;
    }

    public String getColumnName2() {
        return columnName2;
    }

    public void setColumnName2(String columnName2) {
        this.columnName2 = columnName2;
    }

    public JoinType getJoinType() {
        return joinType;
    }

    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }
    
    public List<ValueFilterDef> getFilters() {
	if (filters == null) {
	    filters = new ArrayList<ValueFilterDef>();
	}
	return filters;
    }

    public ValueFilterDef addFilter(ValueFilterDef filter) {
	getFilters().add(filter);
	return filter;
    }
    
    public boolean hasFilters() {
	return filters != null && !filters.isEmpty();
    }
}
