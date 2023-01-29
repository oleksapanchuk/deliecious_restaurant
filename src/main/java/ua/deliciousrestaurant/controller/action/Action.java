package ua.deliciousrestaurant.controller.action;

import ua.deliciousrestaurant.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
