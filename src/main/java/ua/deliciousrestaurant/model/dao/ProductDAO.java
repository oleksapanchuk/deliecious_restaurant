package ua.deliciousrestaurant.model.dao;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.model.entity.Cart;
import ua.deliciousrestaurant.model.entity.Product;

import java.util.List;
import java.util.Optional;


public interface ProductDAO {

    List<Product> getAllProduct(String query)  throws DaoException;
    Optional<Product> getProductById(int productId) throws DaoException;
    int getTotalCartPrice(List<Cart> cartList) throws DaoException;

}
