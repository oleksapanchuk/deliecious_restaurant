package ua.deliciousrestaurant.constant;

public final class ActionConstant {

    /* ACTION */
    public static final String ACTION_DEFAULT = "default";
    public static final String ACTION_LOGIN = "log-in";
    public static final String ACTION_SIGN_UP = "sign-up";
    public static final String ACTION_LOGOUT = "log_out";
    public static final String ACTION_ADD_TO_CART = "atc";
    public static final String ACTION_ORDER_NOW = "order-now";
    public static final String ACTION_REMOVE_FROM_CART = "remove-from-cart";
    public static final String ACTION_INC_DEC_QUANTITY = "inc-dec-quantity";
    public static final String ACTION_ORDER_ALL = "order-all";
    public static final String ACTION_VIEW_MENU = "view-menu";
    public static final String ACTION_VIEW_CART = "view-cart";
    public static final String ACTION_VIEW_ORDERS_FOR_USER = "view-orders-for-user";
    public static final String ACTION_SET_LIKE_FOR_ORDER = "set-like-for-order";
    public static final String ACTION_VIEW_CLIENT_ACCOUNT = "view-client-account";
    public static final String ACTION_VIEW_ALL_CLIENTS = "view-all-clients";
    public static final String ACTION_VIEW_ORDERS_FOR_MANAGER = "view-orders-for-managers";


    /* PAGES */
    public static final String HREF_ORDER_PAGE = "controller?action=view-orders-for-managers&sort_field=order_id&sort_order=des&client_id_filter=0&order_status=-1&offset=0&records=8&cur_page=1";

    public static final String CONTROLLER_PAGE = "controller";
    public static final String INDEX_PAGE = "index.jsp";
    public static final String MENU_PAGE = "menu.jsp";
    public static final String CART_PAGE = "controller?action="  + ACTION_VIEW_CART;
    public static final String CART_PAGE_JSP = "cart.jsp";
    public static final String ORDERS_PAGE_JSP = "orders.jsp";
    public static final String CLIENT_PAGE = "client_account.jsp";
    public static final String CLIENTS_PAGE = "client_manager.jsp";
    public static final String MNG_ORDER_PAGE = "order_manager.jsp";
    public static final String LOGIN_PAGE = "login.jsp";
    public static final String SIGN_UP_PAGE = "sign_up.jsp";




    /* PARAMETERS */
    public static final String ACTION = "action";
    public static final String LOCALE = "locale";
    public static final String AUTH = "auth";
    public static final String ROLE = "role";
    public static final String ERROR = "error";


    public static final String REG_EMAIL = "email";
    public static final String REG_PASSWORD = "pass";
    public static final String REG_FIRST_NAME = "fname";
    public static final String REG_LAST_NAME = "lname";
    public static final String REG_ADDRESS = "address";



    public static final String CART_LIST = "cart-list";



    public static final String OPERATION = "operation";
    public static final String PRODUCT_ID = "id";
    public static final String ORDER_ID = "order-id";
    public static final String IS_LIKED_ORDER = "is-liked-order";

    public static final String ORDER_LIST = "order_list";


    public static final String SORT_FIELD = "sort_field";
    public static final String SORT_ORDER = "sort_order";
    public static final String CATEGORY_FILTER = "category_filter_id";
    public static final String OFFSET = "offset";
    public static final String RECORDS = "records";
    public static final String FIND_TEXT = "find_text";
    public static final String ORDER_STATUS = "order_status";
    public static final String CLIENT_ID_FILTER = "client_id_filter";

    public static final String TOTAL_PAGES = "total_pages";
    public static final String CURRENT_PAGE = "cur_page";
    public static final String START_PAGE = "start";
    public static final String END_PAGE = "end";





    public static final String EMAIL = "login-email";
    public static final String PASSWORD = "login-password";




    /* VALUES PARAMETERS */
    public static final String INCREMENT = "inc";
    public static final String DECREMENT = "dec";





}
