package ua.deliciousrestaurant.controller.action.impl;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.dao.DaoFactory;
import ua.deliciousrestaurant.model.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static ua.deliciousrestaurant.constant.ActionConstant.MENU_PAGE;

public class ViewMenuAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String query =  "SELECT p.prod_id, p.prod_ua_name, c.category_ua_name, p.prod_cost, p.prod_weight, p.prod_img " +
                "FROM product p " +
                "JOIN category c " +
                "ON p.category_id = c.category_id";
        try {
            List<Product> products = DaoFactory.getInstance().getProductDAO().getAllProduct(query);
            System.out.println(products);
            request.getSession().setAttribute("products", products);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        return MENU_PAGE;
    }
}
