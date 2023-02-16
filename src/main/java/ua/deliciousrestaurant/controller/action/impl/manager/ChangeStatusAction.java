package ua.deliciousrestaurant.controller.action.impl.manager;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.deliciousrestaurant.constant.ActionConstant.ORDER_ID;
import static ua.deliciousrestaurant.constant.ActionConstant.ORDER_STATUS;
import static ua.deliciousrestaurant.controller.action.ActionUtil.getActionToRedirect;
import static ua.deliciousrestaurant.controller.action.ActionUtil.redirect;

public class ChangeStatusAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int orderStatus = Integer.parseInt(request.getParameter(ORDER_STATUS));
        int orderId = Integer.parseInt(request.getParameter(ORDER_ID));

        ServiceFactory.getInstance().getOrderService().setOrderStatus(orderStatus, orderId);

        return redirect(request);
    }
}
