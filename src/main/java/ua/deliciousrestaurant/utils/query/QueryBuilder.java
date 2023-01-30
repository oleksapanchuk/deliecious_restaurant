package ua.deliciousrestaurant.utils.query;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static ua.deliciousrestaurant.utils.query.QueryConstant.*;

public class QueryBuilder {
    private final List<String> filters = new ArrayList<>();
    private String sortField;
    private String order = ASCENDING_ORDER;
    private int offset = 0;
    private int records = 5;

    public QueryBuilder(String sortField) {
        this.sortField = sortField;
    }


    public QueryBuilder setClientIdFilter(int clientId) {
        filters.add("client_id=" + clientId);
        return this;
    }

    public QueryBuilder setProductCategoryFilter(int categoryId) {
        filters.add("p.category_id=" + categoryId);
        return this;
    }

    public QueryBuilder setSortField(String sortField) {
        if (sortField != null) {
            this.sortField = sortField;
        }
        return this;
    }

    public QueryBuilder setOrder(String order) {
        if (order != null && order.equalsIgnoreCase(DESCENDING_ORDER)) {
            this.order = DESCENDING_ORDER;
        }
        return this;
    }

    public QueryBuilder setLimits(String offset, String records) {
        if (offset != null) {
            this.offset = Integer.parseInt(offset);
        }
        if (records != null) {
            this.records = Integer.parseInt(records);
        }
        return this;
    }



    public String getQuery() {
        return getFilterQuery() + getSortQuery() + getLimitQuery();
    }
    public String getQueryNoLimits() {
        return getFilterQuery() + getSortQuery();
    }

    private String getFilterQuery() {
        StringJoiner stringJoiner = new StringJoiner(" AND ", " WHERE ", " ").setEmptyValue("");
        filters.forEach(stringJoiner::add);
        return stringJoiner.toString();
    }

    private String getSortQuery() {
        return " ORDER BY " + sortField + " " + order;
    }

    private String getLimitQuery() {
        return " LIMIT " + offset + ", " + records;
    }


    public QueryBuilder setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public QueryBuilder setRecords(int records) {
        this.records = records;
        return this;
    }
}
