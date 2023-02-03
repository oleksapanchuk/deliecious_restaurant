package ua.deliciousrestaurant.controller.action.impl.user;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.dao.DaoFactory;
import ua.deliciousrestaurant.model.entity.Cart;
import ua.deliciousrestaurant.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static ua.deliciousrestaurant.constant.ActionConstant.CART_LIST;
import static ua.deliciousrestaurant.constant.ActionConstant.CART_PAGE_JSP;

public class ViewCartAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        ArrayList<Cart> sessionCartList = (ArrayList<Cart>) request.getSession().getAttribute(CART_LIST);
        System.out.println(sessionCartList);
        List<Cart> cartList;
        if (sessionCartList != null) {
            try {
                cartList = ServiceFactory.getInstance().getProductService().getCartProducts(sessionCartList);
                request.setAttribute("cart_items", cartList);
                request.getSession().setAttribute("cart_list", sessionCartList);
                //todo
                request.setAttribute("totalPrice", DaoFactory.getInstance().getProductDAO().getTotalCartPrice(sessionCartList));
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }

        }

        return CART_PAGE_JSP;
    }
}
