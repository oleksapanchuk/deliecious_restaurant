package ua.deliciousrestaurant.controller.action.impl.user;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.service.ClientService;
import ua.deliciousrestaurant.service.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.deliciousrestaurant.constant.ActionConstant.*;

public class LoginAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        System.out.println(email + " " + password);

        ClientService clientService = new ClientServiceImpl();

//        try {
        ClientDTO client = clientService.login(email, password);

        setLoggedUser(request, client);
        return MENU_PAGE;
//        } catch (NoSuchUserException e) {
//            request.getSession().setAttribute(ERROR, e.getMessage());
//            request.getSession().setAttribute(EMAIL, email);
////        }
//        return getActionToRedirect(ACTION_LOGIN);
    }

    private static void setLoggedUser(HttpServletRequest request, ClientDTO client) {
        request.getSession().setAttribute(AUTH, client);
        request.getSession().setAttribute(ROLE, client.getRole());
    }
}
