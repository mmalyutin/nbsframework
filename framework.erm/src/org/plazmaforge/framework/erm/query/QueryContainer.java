package org.plazmaforge.framework.erm.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryContainer {

    private Map<QueryType, List<Query>> queryMap;
    
    public void addQuery(Query query) {
	if (query == null) {
	    throw new IllegalArgumentException("Query must be not null");
	}
	QueryTemplate template = query.getTemplate();
	if (template == null) {
	    throw new IllegalArgumentException("QueryTemplate must be not null");
	}
	QueryType queryType = template.getType();
	if (queryType == null) {
	    throw new IllegalArgumentException("QueryType must be not null");
	}
	List<Query> list = queryMap.get(queryType);
	if (list == null) {
	     list = new ArrayList<Query>();
	     getQueryMap().put(queryType, list);
	}
	list.add(query);
    }
    
    private Map<QueryType, List<Query>> getQueryMap() {
	if (queryMap == null) {
	    queryMap = new HashMap<QueryType, List<Query>>();
	}
	return queryMap;
    }
    
    public List<Query> getQueries(QueryType queryType) {
	return getQueryMap().get(queryType);
    }
    
    public void destroy( ){
	queryMap.clear();
	queryMap = null;
    }
    
}
