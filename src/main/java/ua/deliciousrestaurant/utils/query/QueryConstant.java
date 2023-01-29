package ua.deliciousrestaurant.utils.query;

public class QueryConstant {

    public static final String SORT_PRODUCT_BY_NAME = " ORDER BY prod_eng_name ";
    public static final String SORT_PRODUCT_BY_COST = " ORDER BY prod_cost ";
    public static final String SORT_PRODUCT_BY_CATEGORY = " ORDER BY category_id ";

    public static final String FILTER_BY_CATEGORY = " WHERE category_id = ";


    public static final String ASCENDING_ORDER = " ASC ";
    public static final String DESCENDING_ORDER = " DESC ";

    public static final String TABLE_PRODUCT = "product";
    public static final String TABLE_CLIENT = "client";
    public static final String TABLE_ORDER = "order";
}
