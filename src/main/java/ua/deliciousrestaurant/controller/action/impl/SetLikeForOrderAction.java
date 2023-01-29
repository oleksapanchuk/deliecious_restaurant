package ua.deliciousrestaurant.controller.action.impl;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.deliciousrestaurant.constant.ActionConstant.*;

public class SetLikeForOrderAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
        String isLikedStatus = request.getParameter(IS_LIKED_ORDER);

        try {
            ServiceFactory.getInstance().getOrderService().setLikeDislike(orderId, isLikedStatus);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }


        return ORDERS_PAGE;
    }
}
