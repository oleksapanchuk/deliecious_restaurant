package ua.deliciousrestaurant.controller.action.impl.client;

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
        String sortField = (String) request.getSession().getAttribute(SORT_FIELD);
        String sortOrder = (String) request.getSession().getAttribute(SORT_ORDER);
        int clientIdFilter = (Integer) request.getSession().getAttribute(CLIENT_ID_FILTER);
        int orderStatus = (Integer) request.getSession().getAttribute(ORDER_STATUS);
        int offset = Integer.parseInt(request.getParameter(OFFSET));
        int curPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));

        int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
        String isLikedStatus = request.getParameter(IS_LIKED_ORDER);

        try {
            ServiceFactory.getInstance().getOrderService().setLikeDislike(orderId, isLikedStatus);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

        return "controller?action=view-orders-for-user&sort_field=" + sortField +
                "&sort_order=" + sortOrder + "&client_id_filter=" + clientIdFilter +
                "&order_status=" + orderStatus + "&offset=" + offset +
                "&records=8&cur_page=" + curPage;
    }
}
