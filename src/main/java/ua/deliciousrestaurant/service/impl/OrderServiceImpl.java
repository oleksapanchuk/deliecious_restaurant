package ua.deliciousrestaurant.service.impl;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.model.dao.DaoFactory;
import ua.deliciousrestaurant.service.OrderService;

public class OrderServiceImpl implements OrderService {

    @Override
    public boolean setLikeDislike(int orderId, String isLiked) throws DaoException {
        return DaoFactory.getInstance().getOrderDAO().updateLikedStatus(orderId, isLiked.equals("true") ? 1 : 0);
    }
}
