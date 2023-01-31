package ua.deliciousrestaurant.controller.action.impl;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.dao.DaoFactory;
import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.model.entity.Cart;
import ua.deliciousrestaurant.model.entity.Order;
import ua.deliciousrestaurant.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ua.deliciousrestaurant.constant.ActionConstant.*;

public class BuyAllAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd:hh-mm-ss");
        ClientDTO auth = (ClientDTO) request.getSession().getAttribute(AUTH);
        List<Cart> products = (ArrayList<Cart>) request.getSession().getAttribute("cart-list"); //todo

        try {
            if (auth != null) {

                Order order = Order.builder()
                        .clientId(auth.getClientId())
                        .statusId(1)
                        .orderTotalPrice(DaoFactory.getInstance().getProductDAO().getTotalCartPrice(products))
                        .addressDelivery(auth.getAddress())
                        .date(formatter.format(new Date()))
                        .isOrderLiked(false)
                        .orderProducts(ServiceFactory.getInstance().getProductService().getCartProducts(products))
                        .build();

                if (DaoFactory.getInstance().getOrderDAO().insertOrder(order)) {
                    products.clear();
                    return ORDERS_PAGE;
                } else {
                    //todo
                    System.out.println("order failed");
                }

            } else {
                return LOGIN_PAGE;
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
