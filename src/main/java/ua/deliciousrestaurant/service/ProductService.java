package ua.deliciousrestaurant.service;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.model.entity.Cart;

import java.util.List;

public interface ProductService {

    List<Cart> getCartProducts(List<Cart> cartList) throws DaoException;
    int getNumberOfProducts(String query) throws DaoException;
}
