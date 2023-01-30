package ua.deliciousrestaurant.utils.query;

public class QueryConstant {

    public static final String SORT_PRODUCT_BY_NAME = " ORDER BY prod_eng_name ";
    public static final String SORT_PRODUCT_BY_COST = " ORDER BY prod_cost ";
    public static final String SORT_PRODUCT_BY_CATEGORY = " ORDER BY p.category_id ";

    public static final String FILTER_BY_CATEGORY = " WHERE c.category_id = ";


    public static final String ASCENDING_ORDER = "asc";
    public static final String DESCENDING_ORDER = "desc";


    /* SELECT */
    public static final String SELECT_CLIENT = "SELECT * FROM `client` ";
    public static final String SELECT_ORDER = "SELECT * FROM `order` ";
    public static final String SELECT_PRODUCT = "SELECT p.prod_id, p.prod_ua_name, c.category_ua_name, p.prod_cost, p.prod_weight, p.prod_img, p.category_id FROM `product` p JOIN `category` c ON p.category_id = c.category_id ";
    public static final String SELECT_PRODUCT_NUMBER = "SELECT count(*) as total FROM `product` p JOIN `category` c ON p.category_id = c.category_id ";


    /* FIELDS */
    public static final String PRODUCT_ID = " prod_id ";
    public static final String PRODUCT_CATEGORY = " p.category_id ";
    public static final String PRODUCT_PRICE = " prod_cost ";
    public static final String PRODUCT_WEIGHT = " prod_weight ";
    public static final String PRODUCT_IMG = " prod_img ";
    public static final String PRODUCT_ENG_NAME = " prod_eng_name ";
    public static final String PRODUCT_UA_NAME = " prod_ua_name ";

    public static final String CLIENT_ID = " client_id ";
    public static final String CLIENT_ROLE = " role_id ";
    public static final String CLIENT_EMAIL = " email ";
    public static final String CLIENT_PASSWORD = " password ";
    public static final String CLIENT_FIRST_NAME = " client_first_name ";
    public static final String CLIENT_LAST_NAME = " client_last_name ";
    public static final String CLIENT_ADDRESS = " address ";

    public static final String ORDER_ID = " order_id ";
    public static final String ORDER_STATUS_ID = " status_id ";
    public static final String ORDER_ADDRESS = " address_delivery ";
    public static final String ORDER_IS_LIKED = " order_liked ";
    public static final String ORDER_TOTAL_PRICE = " order_amount ";
    public static final String ORDER_DATE = " order_date ";

    public static final String ORDER_HAS_PRODUCT_QUANTITY = " order_date ";





}
