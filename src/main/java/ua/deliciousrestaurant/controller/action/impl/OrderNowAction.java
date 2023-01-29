package ua.deliciousrestaurant.controller.action.impl;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.dao.DaoFactory;
import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.model.entity.Cart;
import ua.deliciousrestaurant.model.entity.Client;
import ua.deliciousrestaurant.model.entity.Order;
import ua.deliciousrestaurant.model.entity.Product;
import ua.deliciousrestaurant.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ua.deliciousrestaurant.constant.ActionConstant.LOGIN_PAGE;
import static ua.deliciousrestaurant.constant.ActionConstant.ORDERS_PAGE;

public class OrderNowAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        try {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd:hh-mm-ss");
            ClientDTO auth = (ClientDTO) request.getSession().getAttribute("auth");
            int prodId = Integer.parseInt(request.getParameter("prod-id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

            List<Cart> product = new ArrayList<>();
            product.add(Cart.builder().product(Product.builder().idProduct(prodId).build()).quantity(quantity).build());

            if (auth != null) {

                Order order = Order.builder()
                        .clientId(auth.getClientId())
                        .statusId(1)
                        .orderTotalPrice(DaoFactory.getInstance().getProductDAO().getTotalCartPrice(product))
                        .addressDelivery(auth.getAddress())
                        .date(formatter.format(new Date()))
                        .isOrderLiked(false)
                        .orderProducts(ServiceFactory.getInstance().getProductService().getCartProducts(product))
                        .build();

                if (DaoFactory.getInstance().getOrderDAO().insertOrder(order)) {
                    if ( cartList != null ) {
                        for (Cart cart : cartList) {
                            if ( cart.getProduct().getIdProduct() == prodId ) {
                                cartList.remove( cartList.indexOf(cart) );
                                break;
                            }
                        }
                    }
                    return ORDERS_PAGE;
                } else {
                    throw new ServiceException();
                }

            } else {
                return LOGIN_PAGE;
            }

        } catch (DaoException e) {
            throw new ServiceException();
        }

    }
}
