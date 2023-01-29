package ua.deliciousrestaurant.model.dao;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDAO {

    boolean insertOrder(Order order) throws DaoException;

    List<Order> getClientOrders(int id) throws DaoException;

    boolean updateLikedStatus(int orderId, int isLikedStatus) throws DaoException;

}
