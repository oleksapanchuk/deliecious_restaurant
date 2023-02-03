package ua.deliciousrestaurant.controller.action.impl.user;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.exception.se.NoSuchClientException;
import ua.deliciousrestaurant.exception.se.WrongPasswordException;
import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.service.ClientService;
import ua.deliciousrestaurant.service.ServiceFactory;
import ua.deliciousrestaurant.service.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.deliciousrestaurant.constant.ActionConstant.*;
import static ua.deliciousrestaurant.controller.action.ActionUtil.getActionToRedirect;

public class LoginAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        System.out.println(email + " " + password);

        try {
            ClientDTO client = ServiceFactory.getInstance().getClientService().login(email, password);

            setLoggedUser(request, client);

            return ACCOUNT_PAGE;

        } catch (NoSuchClientException | WrongPasswordException e) {
            request.getSession().setAttribute(ERROR, e.getMessage());
            request.getSession().setAttribute(EMAIL, email);
        }
        return getActionToRedirect(ACTION_LOGIN);
    }

    private static void setLoggedUser(HttpServletRequest request, ClientDTO client) {
        request.getSession().setAttribute(AUTH, client);
        request.getSession().setAttribute(ROLE, client.getRole());
    }
}
