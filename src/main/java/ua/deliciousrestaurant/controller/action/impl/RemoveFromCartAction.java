package ua.deliciousrestaurant.controller.action.impl;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.entity.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;

import static ua.deliciousrestaurant.constant.ActionConstant.CART_PAGE;
import static ua.deliciousrestaurant.constant.ActionConstant.PRODUCT_ID;

public class RemoveFromCartAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String id = request.getParameter(PRODUCT_ID);

        if ( id != null ) {
            int prodId = Integer.parseInt(id);
            ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            if ( cartList != null ) {
                for (Cart cart : cartList) {
                    if ( cart.getProduct().getIdProduct() == prodId ) {
                        cartList.remove( cartList.indexOf(cart) );
                        break;
                    }
                }
            }
        }
        return CART_PAGE;
    }
}
