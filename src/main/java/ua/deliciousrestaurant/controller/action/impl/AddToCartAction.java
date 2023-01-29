package ua.deliciousrestaurant.controller.action.impl;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.entity.Cart;
import ua.deliciousrestaurant.model.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static ua.deliciousrestaurant.constant.ActionConstant.*;

public class AddToCartAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Cart> cartList = new ArrayList<>();
        int id = Integer.parseInt(request.getParameter(PRODUCT_ID));

        Cart cartProducts = Cart.builder()
                .product(Product.builder().idProduct(id).build())
                .quantity(1)
                .build();

        HttpSession session = request.getSession();
        List<Cart> sessionCartList = (ArrayList<Cart>) session.getAttribute(CART_LIST);

        if (sessionCartList == null) {
            cartList.add(cartProducts);
            session.setAttribute(CART_LIST, cartList);
            return MENU_PAGE;
        } else {
            cartList = sessionCartList;
            boolean exist = false;

            for (Cart c : sessionCartList) {
                if (c.getProduct().getIdProduct() == id) {
                    exist = true;
                    //todo
                    //out.println("<h3 style='color:crimson; text-align:center'> Item already exist in cart. <a href='cart.jsp'> Go to Cart page!<a></h3> ");
                    break;
                }
            }

            if (!exist) {
                cartList.add(cartProducts);
                return MENU_PAGE;
            }

        }
        return MENU_PAGE;
    }
}
