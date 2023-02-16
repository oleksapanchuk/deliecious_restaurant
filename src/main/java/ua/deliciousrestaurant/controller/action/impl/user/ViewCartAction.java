package ua.deliciousrestaurant.controller.action.impl.user;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.entity.Cart;
import ua.deliciousrestaurant.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static ua.deliciousrestaurant.constant.ActionConstant.*;

public class ViewCartAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute(CART_LIST);
        String locale = (String) request.getSession().getAttribute(LOCALE);

        if (cartList != null) {

            request.setAttribute(CART_ITEMS, ServiceFactory.getInstance().getProductService().getCartProducts(cartList, locale));
            request.setAttribute(TOTAL_PRICE, ServiceFactory.getInstance().getProductService().getTotalCartPrice(cartList));

        }

        return PAGE_CART;
    }
}
