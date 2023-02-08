package ua.deliciousrestaurant.controller.action.impl.user;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.exception.se.WrongPasswordException;
import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.deliciousrestaurant.constant.ActionConstant.*;

public class LoginAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        if (ServiceFactory.getInstance().getClientService().checkEmailUniq(email)) {
            request.getSession().setAttribute(VALID_STATUS, "user.not.exist");
            return LOGIN_PAGE;
        }

        try {

            ClientDTO client = ServiceFactory.getInstance().getClientService().login(email, password);

            setLoggedUser(request, client);

            return ACCOUNT_PAGE;

        } catch (WrongPasswordException e) {
            request.getSession().setAttribute(VALID_STATUS, "wrong.password");
            return LOGIN_PAGE;
        }

    }

    private static void setLoggedUser(HttpServletRequest request, ClientDTO client) {
        request.getSession().setAttribute(AUTH, client);
        request.getSession().setAttribute(ROLE, client.getRole());
    }
}
