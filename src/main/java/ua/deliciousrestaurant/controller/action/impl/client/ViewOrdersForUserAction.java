package ua.deliciousrestaurant.controller.action.impl.client;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.entity.Order;
import ua.deliciousrestaurant.service.ServiceFactory;
import ua.deliciousrestaurant.utils.query.QueryBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static ua.deliciousrestaurant.constant.ActionConstant.*;
import static ua.deliciousrestaurant.utils.PaginationUtil.paginate;
import static ua.deliciousrestaurant.utils.query.QueryConstant.SELECT_ORDER;
import static ua.deliciousrestaurant.utils.query.QueryConstant.SELECT_ORDER_NUMBER;

public class ViewOrdersForUserAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String sortField = request.getParameter(SORT_FIELD);
        String sortOrder = request.getParameter(SORT_ORDER);
        int clientIdFilter = Integer.parseInt(request.getParameter(CLIENT_ID_FILTER));
        int orderStatus = Integer.parseInt(request.getParameter(ORDER_STATUS));
        int offset = Integer.parseInt(request.getParameter(OFFSET));
        int records = Integer.parseInt(request.getParameter(RECORDS));

        QueryBuilder queryBuilder = new QueryBuilder()
                .setSortField(sortField)
                .setOrder(sortOrder)
                .setClientIdFilter(clientIdFilter)
                .setOffset(offset)
                .setRecords(records);

        if (orderStatus >= 0) {
            queryBuilder.setOrderStatusFilter(orderStatus);
        }

        try {

            List<Order> orders = ServiceFactory.getInstance().getOrderService().getOrderByQuery(SELECT_ORDER + queryBuilder.getQuery());
            int numberOfProducts = ServiceFactory.getInstance().getOrderService().getNumberOrdersByQuery(SELECT_ORDER_NUMBER + queryBuilder.getQueryNoLimits());

            request.getSession().setAttribute(ORDER_LIST, orders);

            request.getSession().setAttribute(SORT_FIELD, sortField);
            request.getSession().setAttribute(SORT_ORDER, sortOrder);
            request.getSession().setAttribute(CLIENT_ID_FILTER, clientIdFilter);
            request.getSession().setAttribute(ORDER_STATUS, orderStatus);
            request.getSession().setAttribute(OFFSET, offset);
            request.getSession().setAttribute(RECORDS, records);

            paginate(request, numberOfProducts);

        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

        return ORDERS_PAGE_JSP;
    }
}