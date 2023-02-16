package ua.deliciousrestaurant.controller.action.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.deliciousrestaurant.controller.action.impl.manager.ChangeStatusAction;
import ua.deliciousrestaurant.controller.action.impl.user.LoginAction;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.service.ClientService;
import ua.deliciousrestaurant.service.OrderService;
import ua.deliciousrestaurant.service.ServiceFactory;
import ua.deliciousrestaurant.utils.MyRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.deliciousrestaurant.TestConstants.*;
import static ua.deliciousrestaurant.TestConstants.INCORRECT_PASSWORD;
import static ua.deliciousrestaurant.constant.ActionConstant.*;
import static ua.deliciousrestaurant.controller.action.ActionUtil.getActionToRedirect;
import static ua.deliciousrestaurant.service.impl.ClientServiceImpl.getTempClientDTO;

public class ManagerActionTest {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final ClientService clientService = mock(ServiceFactory.getInstance().getClientService().getClass());
    private final OrderService orderService = mock(ServiceFactory.getInstance().getOrderService().getClass());

    @Test
    void testChangeStatus() throws ServiceException {
        MyRequest myRequest = new MyRequest(request);

        when(request.getParameter(ORDER_STATUS)).thenReturn(String.valueOf(STATUS_ORDERED));
        when(request.getParameter(ORDER_ID)).thenReturn(String.valueOf(ORDER_ID_VALUE));

        assertEquals(String.valueOf(STATUS_ORDERED), request.getParameter(ORDER_STATUS));

    }



}
