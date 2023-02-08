package ua.deliciousrestaurant.controller.action.impl.user;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.entity.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static ua.deliciousrestaurant.constant.ActionConstant.*;

public class RemoveFromCartAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String id = request.getParameter(PRODUCT_ID);

        if ( id != null ) {
            int prodId = Integer.parseInt(id);
            ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute(CART_LIST);
            if ( cartList != null ) {
                for (Cart cart : cartList) {
                    if ( cart.getProduct().getIdProduct() == prodId ) {
                        cartList.remove(cart);
                        break;
                    }
                }
            }
        }
        return CART_PAGE;
    }
}
