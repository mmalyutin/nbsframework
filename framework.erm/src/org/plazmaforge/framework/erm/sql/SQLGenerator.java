package org.plazmaforge.framework.erm.sql;

import java.util.List;

import org.plazmaforge.framework.erm.query.ColumnDef;
import org.plazmaforge.framework.erm.query.FilterDef;
import org.plazmaforge.framework.erm.query.DeleteTemplate;
import org.plazmaforge.framework.erm.query.DiscriminatorValue;
import org.plazmaforge.framework.erm.query.ValueFilterDef;
import org.plazmaforge.framework.erm.query.InsertTemplate;
import org.plazmaforge.framework.erm.query.JoinDef;
import org.plazmaforge.framework.erm.query.OrderDef;
import org.plazmaforge.framework.erm.query.QueryInput;
import org.plazmaforge.framework.erm.query.QueryTemplate;
import org.plazmaforge.framework.erm.query.SelectTemplate;
import org.plazmaforge.framework.erm.query.TableDef;
import org.plazmaforge.framework.erm.query.UpdateTemplate;
import org.plazmaforge.framework.sql.dialect.Dialect;

public class SQLGenerator {

    private static final String COLUMN_SEPARATOR = ",";
    
    private static final String COLUMN_FIRST_TAB = "\n  ";
    private static final String COLUMN_TAB = COLUMN_SEPARATOR + "\n  ";

    private static final String PARAMETER_FIRST_TAB = "\n  ";
    private static final String PARAMETER_TAB = " AND" + "\n  ";

    private static final String COMMAND_TAB = "\n";
    private static final String TABLE_BLOCK_TAB = "\n";

    
    private Dialect dialect;
    
    
    public SQLGenerator(Dialect dialect) {
	this.dialect = dialect;
    }

    
    public Dialect getDialect() {
        return dialect;
    }


    /**
     * Return Load SQL to one entity
     * @param template
     * @return
     */
    public String getLoadSQL(SelectTemplate template) {
	StringBuffer buf = new StringBuffer();
	generateLoadSQL(template, buf);
	return buf.toString();
    }
	
    /**
     * Return Select SQL to more entities
     * @param template
     * @return
     */
    public String getSelectSQL(SelectTemplate template) {
	StringBuffer buf = new StringBuffer();
	generateSelectSQL(template, buf);
	return buf.toString();
    }

    public String getSelectForCountSQL(SelectTemplate template) {
	StringBuffer buf = new StringBuffer();
	generateSelectForCountSQL(template, buf);
	return buf.toString();
    }

    /**
     * Return Select COUNT(*) SQL to more entities
     * @param template
     * @return
     */
    public String getCountSelectSQL(SelectTemplate template) {
	String sql = getSelectForCountSQL(template);
	sql = getDialect().getCountString(sql);
	return sql;
    }
    
    /**
     * Return Limit Select SQL to more entities
     * @param template
     * @param offset
     * @param limit
     * @return
     */
    public String getLimitSelectSQL(SelectTemplate template, int offset, int limit) {
	String sql = getSelectSQL(template);
	sql = getDialect().getLimitString(sql, offset, limit);
	return sql;
    }    
    
    /**
     * Return Insert SQL to one entity
     * @param template
     * @return
     */
    public String getInsertSQL(InsertTemplate template) {
	StringBuffer buf = new StringBuffer();
	generateInsertSQL(template, buf);
	return buf.toString();
    }

    /**
     * Return Update SQL to one entity
     * @param template
     * @return
     */
    public String getUpdateSQL(UpdateTemplate template) {
	StringBuffer buf = new StringBuffer();
	generateUpdateSQL(template, buf);
	return buf.toString();
    }

    /**
     * Return Delete SQL to one entity
     * @param template
     * @return
     */
    public String getDeleteSQL(DeleteTemplate template) {
	StringBuffer buf = new StringBuffer();
	generateDeleteSQL(template, buf);
	return buf.toString();
    }

    
    ///////////////////////////////////////////////////////////////////////////////////////////

    public void generateLoadSQL(SelectTemplate template, StringBuffer buf) {
	generateSelectSQL(template, buf);
    }
    
    
    public void generateSelectSQL(SelectTemplate template, StringBuffer buf) {
	generateSelectSQL(template, buf, true);
    }
    
    /**
     * Generate Select SQL by Query
     * @param template
     * @param buf
     */
    public void generateSelectSQL(SelectTemplate template, StringBuffer buf, boolean isOrder) {
	
	checkTemplate(template, buf);
	checkColumns(template);

	//boolean loadMode = QueryType.LOAD.equals(template.getType());
	template.clearColumnAlias(); // Reset column alias index
	
	buf.append("SELECT");
	List<ColumnDef> columns = template.getQueryColumns(); // QueryColumns
	ColumnDef column = null;
	int count = columns.size();
	for (int i = 0; i < count; i++) {
	    column = columns.get(i);
	    buf.append(i == 0 ? COLUMN_FIRST_TAB : COLUMN_TAB);
	    buf.append(template.getQueryColumnString(column));
	}
	buf.append(COMMAND_TAB + "FROM");
	List<TableDef> tables = template.getTables();
	TableDef table = null;
	count = tables.size();
	for (int i = 0; i < count; i++) {
	    table = tables.get(i);
	    if (table.isJoin()) {
		continue;
	    }
	    buf.append(i == 0 ? COLUMN_FIRST_TAB : COLUMN_TAB);
	    buf.append(template.getQueryTableString(table));
	}
	List<JoinDef> joins = template.getJoins();
	JoinDef join = null;
	count = joins.size();
	for (int i = 0; i < count; i++) {
	    join = joins.get(i);
	    buf.append(COMMAND_TAB + template.getQueryJoinString(join));
	}
	
	List<FilterDef> filters = template.getFilters();
	count = filters.size();
	if (count > 0) {
	    buf.append(COMMAND_TAB + "WHERE ");
	    FilterDef filter = null;
	    for (int i = 0; i < count; i++) {
		filter = filters.get(i);
		buf.append(i == 0 ? PARAMETER_FIRST_TAB : PARAMETER_TAB);
		buf.append(template.getQueryFilterString(filter));
	    }
	}
	
	if (!isOrder) {
	    return;
	}
	
	List<OrderDef> orders = template.getOrders();
	count = orders.size();
	if (count > 0) {
	    buf.append(COMMAND_TAB + "ORDER BY ");
	    OrderDef order = null;
	    for (int i = 0; i < count; i++) {
		order = orders.get(i);
		buf.append(i == 0 ? COLUMN_FIRST_TAB : COLUMN_TAB);
		buf.append(template.getQueryOrderString(order));
	    }
	}
    }
    
    /**
     * Generate Select SQL for COUNT(*) command
     * @param template
     * @param buf
     */
    public void generateSelectForCountSQL(SelectTemplate template, StringBuffer buf) {
	checkTemplate(template, buf);
	checkColumns(template);
	generateSelectSQL(template, buf, false);
    }
    

    /**
     * Generate Insert SQL by Query
     * @param template
     * @param buf
     */
    public void generateInsertSQL(InsertTemplate template, StringBuffer buf) {
	checkTemplate(template, buf);
	checkColumns(template);
	QueryInput queryInput = template.getQueryInput();
	DiscriminatorValue discriminatorValue = queryInput.getDiscriminatorValue(); 
	buf.append("INSERT INTO");
	List<ColumnDef> columns = template.getQueryColumns(); // QueryColumns
	buf.append(TABLE_BLOCK_TAB + "  " + template.getTableName());
	ColumnDef column = null;
	int count = columns.size();
	buf.append(COMMAND_TAB + "(");
	for (int i = 0; i < count; i++) {
	    column = columns.get(i);
	    buf.append(i == 0 ? COLUMN_FIRST_TAB : COLUMN_TAB);
	    buf.append(column.getColumnName());
	}
	// TODO: DISCRIMINATOR STUB
	if (discriminatorValue != null) {
	    buf.append(COLUMN_TAB);
	    buf.append(discriminatorValue.getColumn().getName());
	}
	buf.append(COMMAND_TAB + ")");
	buf.append(COMMAND_TAB + "VALUES");
	buf.append(COMMAND_TAB + "(");
	for (int i = 0; i < count; i++) {
	    buf.append(i == 0 ? COLUMN_FIRST_TAB : COLUMN_TAB);
	    buf.append("?");
	}
	// TODO: DISCRIMINATOR STUB
	if (discriminatorValue != null) {
	    buf.append(COLUMN_TAB);
	    buf.append("'" + discriminatorValue.getStringValue() + "'");
	}
	buf.append(COMMAND_TAB + ")");
    }
    
    /**
     * Generate Update SQL by Query
     * @param template
     * @param buf
     */
    public void generateUpdateSQL(UpdateTemplate template, StringBuffer buf) {
	checkTemplate(template, buf);
	checkColumns(template);
	buf.append("UPDATE");
	List<ColumnDef> columns = template.getQueryColumns(); // QueryColumns
	buf.append(TABLE_BLOCK_TAB + "  " + template.getTableName());
	buf.append(COMMAND_TAB + "SET");
	ColumnDef column = null;
	int count = columns.size();
	for (int i = 0; i < count; i++) {
	    column = columns.get(i);
	    buf.append(i == 0 ? COLUMN_FIRST_TAB : COLUMN_TAB);
	    buf.append(column.getColumnName() + " = ?" );
	}
	buf.append(COMMAND_TAB + "WHERE ");
	List<FilterDef> keys = template.getFilters();
	ValueFilterDef key = null;
	count = keys.size();
	for (int i = 0; i < count; i++) {
	    key = (ValueFilterDef) keys.get(i); // TODO: ONLY FILTER SUPPORT
	    buf.append(i == 0 ? PARAMETER_FIRST_TAB : PARAMETER_TAB);
	    buf.append(template.getQueryFilterString(key.getColumnName(), key));
	}
    }
    
    public void generateDeleteSQL(DeleteTemplate template, StringBuffer buf) {
	checkTemplate(template, buf);
	buf.append("DELETE FROM " + template.getTableName());
	buf.append(COMMAND_TAB + "WHERE ");
	List<FilterDef> keys = template.getFilters();
	ValueFilterDef key = null;
	int count = keys.size();
	for (int i = 0; i < count; i++) {
	    key = (ValueFilterDef) keys.get(i); // TODO: ONLY FILTER SUPPORT 
	    buf.append(i == 0 ? PARAMETER_FIRST_TAB : PARAMETER_TAB);
	    buf.append(template.getQueryFilterString(key.getColumnName(), key));
	}
    }
    
    public void generateExistsSQL(SelectTemplate template, StringBuffer buf) {
	checkTemplate(template, buf);
	checkColumns(template);
	buf.append("SELECT");
	List<ColumnDef> columns = template.getQueryColumns(); // QueryColumns
	ColumnDef column = null;
	int count = columns.size();
	for (int i = 0; i < count; i++) {
	    column = columns.get(i);
	    buf.append(i == 0 ? COLUMN_FIRST_TAB : COLUMN_TAB);
	    buf.append(column.getColumnName());
	}
	buf.append(COMMAND_TAB + "FROM");
	buf.append(TABLE_BLOCK_TAB + "  " + template.getTableName());
	buf.append(COMMAND_TAB + "WHERE ");
	List<FilterDef> filters = template.getFilters();
	ValueFilterDef filter = null;
	count = filters.size();
	for (int i = 0; i < count; i++) {
	    filter = (ValueFilterDef) filters.get(i); // TODO: ONLY FILTER SUPPORT
	    buf.append(i == 0 ? PARAMETER_FIRST_TAB : PARAMETER_TAB);
	    buf.append(template.getQueryFilterString(filter.getColumnName(), filter));
	}
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////
    
    private void checkTemplate(QueryTemplate template, StringBuffer buf) {
	if (template == null) {
	    new IllegalArgumentException("Template must be not null");
	}
    }

    private void checkColumns(QueryTemplate template) {
	if (!template.hasColumns()) {
	    new IllegalArgumentException("Template has not columns");
	}
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    
}
