package ua.deliciousrestaurant.controller.action.impl.user;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.entity.Cart;
import ua.deliciousrestaurant.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static ua.deliciousrestaurant.constant.ActionConstant.CART_LIST;
import static ua.deliciousrestaurant.constant.ActionConstant.CART_PAGE_JSP;

public class ViewCartAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute(CART_LIST);

        if (cartList != null) {

            try {

                request.setAttribute("cart_items", ServiceFactory.getInstance().getProductService().getCartProducts(cartList));
                request.setAttribute("totalPrice", ServiceFactory.getInstance().getProductService().getTotalCartPrice(cartList));

            } catch (DaoException e) {
                throw new ServiceException(e);
            }

        }

        return CART_PAGE_JSP;
    }
}
