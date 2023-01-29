package ua.deliciousrestaurant.service;

import ua.deliciousrestaurant.exception.DaoException;

public interface OrderService {

    boolean setLikeDislike(int orderId, String isLiked) throws DaoException;
}
