package ua.deliciousrestaurant.model.dao;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.model.entity.Cart;
import ua.deliciousrestaurant.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDAO {

    boolean insertOrder(Order order) throws DaoException;
    boolean updateStatusOrder(int status, int order) throws DaoException;

    List<Order> getClientOrders(int id) throws DaoException;
    List<Order> getOrderByQuery(String query) throws DaoException;
    int getNumberRecordsByQuery(String query) throws DaoException;

    List<Cart> getProductsFromOrder(int orderId) throws DaoException;

    boolean updateLikedStatus(int orderId, int isLikedStatus) throws DaoException;

}
