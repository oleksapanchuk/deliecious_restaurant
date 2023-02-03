package ua.deliciousrestaurant.constant;

public class DBConstant {

    public static final String FIND_CLIENT_BY_EMAIL = "SELECT * FROM client WHERE email = ?";
    public static final String GET_ALL_CLIENTS = "SELECT * FROM client";
    public static final String INSERT_CLIENT = "INSERT INTO client (role_id, email, password, client_first_name, client_last_name, address, client_phone_number) VALUE (?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_CLIENT = "UPDATE client SET role_id = ?, email = ?, password = ?, client_first_name = ?, client_last_name = ?, address = ?, client_phone_number = ? WHERE client_id = ?";
    public static final String DELETE_CLIENT = "DELETE FROM client WHERE email = ?";

    public static final String GET_TOTAL_PRICE = "SELECT prod_cost FROM product WHERE prod_id = ?";

    public static final String GET_PRODUCT_BY_ID = "SELECT p.prod_id, p.prod_ua_name, c.category_ua_name, p.prod_cost, p.prod_weight, p.prod_img FROM product p JOIN category c ON p.category_id = c.category_id WHERE p.prod_id = ?";


    /* PRODUCT */
    public static final String GET_ALL_CATEGORIES_ENG = "SELECT category_id, category_eng_name, category_img FROM `category`";
    public static final String GET_ALL_CATEGORIES_UA = "SELECT category_id, category_ua_name, category_img FROM `category`";



    /* ORDER */
    public static final String INSERT_ORDER = "INSERT INTO `order` (client_id, status_id, order_amount, address_delivery, order_date, order_liked) VALUE (?, ?, ?, ?, ?, ?)";
    public static final String INSERT_PRODUCT_INTO_ORDER = "INSERT INTO `order_has_product` VALUE (?, ?, ?)";
    public static final String GET_CLIENT_ORDERS = "SELECT * FROM `order` WHERE client_id = ?";
    public static final String GET_PRODUCTS_FROM_ORDER = "SELECT * FROM `order_has_product` WHERE order_id = ?";
    public static final String UPDATE_LIKED_STATUS_ORDER = "UPDATE `order` SET order_liked = ? WHERE order_id = ?";


}
