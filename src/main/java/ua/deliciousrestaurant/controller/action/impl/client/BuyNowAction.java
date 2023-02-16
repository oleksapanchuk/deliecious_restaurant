package ua.deliciousrestaurant.controller.action.impl.client;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.dao.DaoFactory;
import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.model.entity.Cart;
import ua.deliciousrestaurant.model.entity.Order;
import ua.deliciousrestaurant.model.entity.Product;
import ua.deliciousrestaurant.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ua.deliciousrestaurant.constant.ActionConstant.*;
import static ua.deliciousrestaurant.controller.action.ActionUtil.isGetMethod;

public class BuyNowAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isGetMethod(request) ? exeGet(request) : exePost(request);
    }

    private String exeGet(HttpServletRequest request) {
        return "controller?action=view-cart";
    }

    private String exePost(HttpServletRequest request) throws ServiceException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd:hh-mm-ss");
        ClientDTO auth = (ClientDTO) request.getSession().getAttribute(AUTH);
        int prodId = Integer.parseInt(request.getParameter(PRODUCT_ID));
        int quantity = Integer.parseInt(request.getParameter(QUANTITY));
        ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute(CART_LIST);


        try {

            List<Cart> product = new ArrayList<>();
            product.add(Cart.builder().product(Product.builder().idProduct(prodId).build()).quantity(quantity).build());
            int price = DaoFactory.getInstance().getProductDAO().getTotalCartPrice(product);

            if (auth != null) {

                Order order = Order.builder()
                        .clientId(auth.getClientId())
                        .statusId(1)
                        .orderTotalPrice(price)
                        .addressDelivery(auth.getAddress())
                        .date(formatter.format(new Date()))
                        .isOrderLiked(false)
                        .orderProducts(ServiceFactory.getInstance().getProductService().getCartProducts(product, (String) request.getSession().getAttribute(LOCALE)))
                        .build();

                if (DaoFactory.getInstance().getOrderDAO().insertOrder(order)) {

                    for (Cart cart : cartList) {
                        if (cart.getProduct().getIdProduct() == prodId) {
                            cartList.remove(cart);
                            break;
                        }
                    }

                    ServiceFactory.getInstance().getClientService().addFundsToWallet(auth.getClientId(), -price);
                    ServiceFactory.getInstance().getClientService().updateWalletBalance(auth);
                    ServiceFactory.getInstance().getClientService().updateNumberOfOrder(auth);
                    ServiceFactory.getInstance().getClientService().updateTotalFundsSpent(auth);

                    return "controller?action=view-orders-for-user&sort_field=order_date&sort_order=desc&client_id_filter=" + auth.getClientId() + "&order_status=-1&offset=0&records=8&cur_page=1";
                }
                throw new ServiceException();

            } else {
                return HREF_LOGIN;
            }

        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

}
