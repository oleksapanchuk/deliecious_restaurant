package ua.deliciousrestaurant.controller.action.impl.client;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static ua.deliciousrestaurant.constant.ActionConstant.*;

public class LogoutAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        if (session.getAttribute(AUTH) != null) {
            String locale = (String) session.getAttribute(LOCALE);
            session.invalidate();
            request.getSession(true).setAttribute(LOCALE, locale);
        }
        return HREF_LOGIN;
    }
}
