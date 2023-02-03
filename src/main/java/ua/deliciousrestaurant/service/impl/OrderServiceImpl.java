package ua.deliciousrestaurant.service.impl;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.connection.DataSource;
import ua.deliciousrestaurant.model.dao.DaoFactory;
import ua.deliciousrestaurant.model.entity.Order;
import ua.deliciousrestaurant.service.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Override
    public List<Order> getAllOrdersByClientId(int clientId) throws ServiceException {
        List<Order> orders;
        try {
            orders = DaoFactory.getInstance().getOrderDAO().getClientOrders(clientId);

            for (Order order : orders) {
                order.setOrderProducts(DaoFactory.getInstance().getOrderDAO().getProductsFromOrder(order.getOrderId()));
                order.setOrderTotalPrice(DaoFactory.getInstance().getProductDAO().getTotalCartPrice(order.getOrderProducts()));
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return orders;
    }

    @Override
    public List<Order> getOrderByQuery(String query) throws ServiceException {
        List<Order> orderList;

        try {
            orderList = DaoFactory.getInstance().getOrderDAO().getOrderByQuery(query);

            for (Order order : orderList) {
                order.setOrderProducts(DaoFactory.getInstance().getOrderDAO().getProductsFromOrder(order.getOrderId()));
                order.setOrderTotalPrice(DaoFactory.getInstance().getProductDAO().getTotalCartPrice(order.getOrderProducts()));
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return orderList;
    }

    @Override
    public int getNumberOrdersByQuery(String query) throws ServiceException, DaoException {
        return DaoFactory.getInstance().getOrderDAO().getNumberRecordsByQuery(query);
    }

    @Override
    public boolean setLikeDislike(int orderId, String isLiked) throws DaoException {
        return DaoFactory.getInstance().getOrderDAO().updateLikedStatus(orderId, isLiked.equals("true") ? 0 : 1);
    }
}
