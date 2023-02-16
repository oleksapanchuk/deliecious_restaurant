package ua.deliciousrestaurant.controller.action.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.deliciousrestaurant.controller.action.impl.user.LoginAction;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.exception.se.NoSuchClientException;
import ua.deliciousrestaurant.service.ClientService;
import ua.deliciousrestaurant.service.ServiceFactory;
import ua.deliciousrestaurant.utils.MyRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static junit.framework.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.deliciousrestaurant.TestConstants.*;
import static ua.deliciousrestaurant.constant.ActionConstant.*;
import static ua.deliciousrestaurant.service.impl.ClientServiceImpl.getTempClientDTO;

public class LoginActionTest {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final ClientService clientService = mock(ServiceFactory.getInstance().getClientService().getClass());

    @Test
    void testExecutePost() throws ServiceException {
        MyRequest myRequest = new MyRequest(request);
        setPostRequest();
        when(clientService.login(EMAIL_VALUE, PASSWORD_VALUE)).thenReturn(getTempClientDTO());

        Assertions.assertEquals(PAGE_ACCOUNT, new LoginAction().execute(myRequest, response));
        assertEquals(getTempClientDTO(), myRequest.getSession().getAttribute(AUTH));
        assertEquals(getTempClientDTO().getRole(), myRequest.getSession().getAttribute(ROLE));
    }

    @Test
    void testExecuteWrongPost() throws ServiceException {
        MyRequest myRequest = new MyRequest(request);
        setPostBadRequest();
        when(clientService.login(INCORRECT_EMAIL, INCORRECT_PASSWORD)).thenThrow(new NoSuchClientException());

        assertEquals(PAGE_LOGIN, new LoginAction().execute(myRequest, response));
    }

    @Test
    void testExecuteGet() throws ServiceException {
        MyRequest myRequest = new MyRequest(request);

        setGetRequest(myRequest);

        assertEquals(PAGE_LOGIN, new LoginAction().execute(myRequest, response));
        assertEquals(EMAIL_VALUE, myRequest.getSession().getAttribute(EMAIL));
        assertNotNull(myRequest.getSession().getAttribute(EMAIL));
    }

    void setPostRequest() {
        when(request.getMethod()).thenReturn(POST);
        when(request.getParameter(EMAIL)).thenReturn(EMAIL_VALUE);
        when(request.getParameter(PASSWORD)).thenReturn(PASSWORD_VALUE);
    }

    void setPostBadRequest() {
        when(request.getMethod()).thenReturn(POST);
        when(request.getParameter(EMAIL)).thenReturn(INCORRECT_EMAIL);
        when(request.getParameter(PASSWORD)).thenReturn(INCORRECT_PASSWORD);
    }

    void setGetRequest(MyRequest myRequest) {
        when(request.getMethod()).thenReturn(GET);
        HttpSession session = myRequest.getSession();
        session.setAttribute(EMAIL, EMAIL_VALUE);
    }

}
