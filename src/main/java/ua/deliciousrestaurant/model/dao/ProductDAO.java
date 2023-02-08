package ua.deliciousrestaurant.model.dao;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.dto.ProductDTO;
import ua.deliciousrestaurant.model.entity.Cart;
import ua.deliciousrestaurant.model.entity.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface ProductDAO {

    List<ProductDTO> getAllProduct(String query)  throws DaoException;
    Optional<Product> getProductById(int productId) throws DaoException;
    int getTotalCartPrice(List<Cart> cartList) throws DaoException;

    Map<Integer, String> getMapCategories(String locale) throws DaoException;

}
