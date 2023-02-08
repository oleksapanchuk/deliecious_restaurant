package ua.deliciousrestaurant.controller.action.impl.client;

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
        List<Cart> products = (ArrayList<Cart>) request.getSession().getAttribute(CART_LIST);

        int totalPrice = Integer.parseInt(request.getParameter("total_price"));
        String addressBuy = request.getParameter("address_buy");

        try {
            if (auth == null) { return LOGIN_PAGE; }

            Order order = Order.builder()
                    .clientId(auth.getClientId())
                    .statusId(1)
                    .orderTotalPrice(DaoFactory.getInstance().getProductDAO().getTotalCartPrice(products))
                    .addressDelivery(auth.getAddress())
                    .date(formatter.format(new Date()))
                    .isOrderLiked(false)
                    .orderTotalPrice(totalPrice)
                    .addressDelivery(addressBuy)
                    .orderProducts(ServiceFactory.getInstance().getProductService().getCartProducts(products))
                    .build();

            if (DaoFactory.getInstance().getOrderDAO().insertOrder(order)) {
                products.clear();
                ServiceFactory.getInstance().getClientService().addFundsToWallet(auth.getClientId(), -totalPrice);
                ServiceFactory.getInstance().getClientService().updateWalletBalance(auth);
                ServiceFactory.getInstance().getClientService().updateNumberOfOrder(auth);
                ServiceFactory.getInstance().getClientService().updateTotalFundsSpent(auth);
                return HREF_ORDER_PAGE_CLIENT;
            }

            request.setAttribute("status", "order_failed");
            return CART_PAGE;

        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
