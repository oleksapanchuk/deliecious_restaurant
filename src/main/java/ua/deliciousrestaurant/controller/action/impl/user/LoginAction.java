package ua.deliciousrestaurant.controller.action.impl.user;

import lombok.extern.slf4j.Slf4j;
import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.exception.se.WrongPasswordException;
import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.deliciousrestaurant.constant.ActionConstant.*;
import static ua.deliciousrestaurant.controller.action.ActionUtil.isGetMethod;
import static ua.deliciousrestaurant.controller.action.ActionUtil.setValueToRequestFromSession;

@Slf4j
public class LoginAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isGetMethod(request) ? exeGet(request) : exePost(request);
    }

    private String exeGet(HttpServletRequest request) {
        setValueToRequestFromSession(request, EMAIL);
        return PAGE_LOGIN;
    }

    private String exePost(HttpServletRequest request) throws ServiceException {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        if (ServiceFactory.getInstance().getClientService().checkEmailUniq(email)) {
            request.getSession().setAttribute(VALID_STATUS, "user.not.exist");
            return PAGE_LOGIN;
        }

        try {

            ClientDTO client = ServiceFactory.getInstance().getClientService().login(email, password);

            setLoggedUser(request, client);

            log.info("user logged in");

            return PAGE_ACCOUNT;

        } catch (WrongPasswordException e) {
            log.info("incorrect password or user not found");

            request.getSession().setAttribute(VALID_STATUS, "wrong.password");
            return PAGE_LOGIN;
        }
    }

    private static void setLoggedUser(HttpServletRequest request, ClientDTO client) {
        request.getSession().setAttribute(AUTH, client);
        request.getSession().setAttribute(ROLE, client.getRole());
    }
}
