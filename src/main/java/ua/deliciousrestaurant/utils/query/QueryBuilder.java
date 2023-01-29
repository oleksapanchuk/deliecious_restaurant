package ua.deliciousrestaurant.utils.query;

import static ua.deliciousrestaurant.utils.query.QueryConstant.*;

public class QueryBuilder {

    private String sortField = SORT_PRODUCT_BY_CATEGORY;
    private String order = ASCENDING_ORDER;
    private int offset = 0;
    private int records = 10;
    private int filterId = 0;
    private String table;

    public QueryBuilder(String table) {
        this.table = table;
    }

    public String getQuery() {
        return "SELECT * FROM " + table
                + (filterId != 0 ? FILTER_BY_CATEGORY + filterId : " ")
                + sortField + order
                + " LIMIT " + offset + ", " + records;
    }

    public QueryBuilder setSortField(String sortField) {
        this.sortField = sortField;
        return this;
    }

    public QueryBuilder setOrder(String order) {
        this.order = order;
        return this;
    }

    public QueryBuilder setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public QueryBuilder setRecords(int records) {
        this.records = records;
        return this;
    }

    public QueryBuilder setFilterId(int filterId) {
        this.filterId = filterId;
        return this;
    }

    public QueryBuilder setTable(String table) {
        this.table = table;
        return this;
    }

    public static void main(String[] args) {
        QueryBuilder queryBuilder = new QueryBuilder(TABLE_PRODUCT)
                .setSortField(SORT_PRODUCT_BY_NAME)
                .setOffset(1).setOrder(DESCENDING_ORDER).setOffset(8).setFilterId(4);
        System.out.println(queryBuilder.getQuery());
    }

}
