package ua.deliciousrestaurant.controller.action.impl;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.dao.DaoFactory;
import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.model.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static ua.deliciousrestaurant.constant.ActionConstant.*;

public class ViewOrdersForUserAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {


        if (request.getSession().getAttribute(AUTH) == null) {
            return LOGIN_PAGE;
        } else {

            ClientDTO client = (ClientDTO) request.getSession().getAttribute(AUTH);

            try {

                List<Order> orders = DaoFactory.getInstance().getOrderDAO().getClientOrders(client.getClientId());
                request.getSession().setAttribute(ORDER_LIST, orders);

            } catch (DaoException e) {
                throw new ServiceException(e);
            }

        }

        return ORDERS_PAGE_JSP;
    }
}
