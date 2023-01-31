package ua.deliciousrestaurant.controller.action.impl;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.model.entity.Order;
import ua.deliciousrestaurant.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static ua.deliciousrestaurant.constant.ActionConstant.*;

public class ViewOrdersForUserAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        if (request.getSession().getAttribute(AUTH) == null) {
            return LOGIN_PAGE;
        } else {

            ClientDTO client = (ClientDTO) request.getSession().getAttribute(AUTH);

            List<Order> orders = ServiceFactory.getInstance().getOrderService().getAllOrdersByClientId(client.getClientId());
            request.getSession().setAttribute(ORDER_LIST, orders);

        }

        return ORDERS_PAGE_JSP;
    }
}
