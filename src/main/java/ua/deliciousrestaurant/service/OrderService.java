package ua.deliciousrestaurant.service;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrdersByClientId(int clientId) throws ServiceException;

    boolean setLikeDislike(int orderId, String isLiked) throws DaoException;
}
