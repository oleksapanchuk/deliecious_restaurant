package ua.deliciousrestaurant.service.impl;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.model.dao.DaoFactory;
import ua.deliciousrestaurant.model.entity.Cart;
import ua.deliciousrestaurant.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Cart> getCartProducts(List<Cart> cartList) throws DaoException {
        List<Cart> products = new ArrayList<>();

        if (!cartList.isEmpty()) {
            for (Cart cart : cartList) {
                products.add(Cart.builder()
                        .product(DaoFactory.getInstance().getProductDAO().getProductById(cart.getProduct().getIdProduct()).get())
                        .quantity(cart.getQuantity())
                        .build());
            }
        }

        return products;
    }

}
