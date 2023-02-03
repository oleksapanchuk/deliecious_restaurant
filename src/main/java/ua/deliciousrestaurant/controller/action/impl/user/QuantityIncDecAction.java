package ua.deliciousrestaurant.controller.action.impl.user;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.entity.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static ua.deliciousrestaurant.constant.ActionConstant.*;

public class QuantityIncDecAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String act = request.getParameter(OPERATION);
        int id = Integer.parseInt(request.getParameter(PRODUCT_ID));

        ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

        if ( act != null && id >= 0) {
            if ( act.equals(INCREMENT) ) {
                for (Cart cart : cart_list) {
                    if ( cart.getProduct().getIdProduct() == id ) {
                        cart.setQuantity(cart.getQuantity() + 1);
                        break;
                    }
                }
            } else if ( act.equals(DECREMENT) ) {
                for (Cart cart : cart_list) {
                    if ( cart.getProduct().getIdProduct() == id && cart.getQuantity() > 1) {
                        cart.setQuantity(cart.getQuantity() - 1);
                        break;
                    }
                }
            }
        }
        return CART_PAGE;
    }
}
