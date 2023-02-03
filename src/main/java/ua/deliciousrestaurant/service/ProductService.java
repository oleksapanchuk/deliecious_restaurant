package ua.deliciousrestaurant.service;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.model.dto.ProductDTO;
import ua.deliciousrestaurant.model.entity.Cart;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductService {

    List<Cart> getCartProducts(List<Cart> cartList) throws DaoException;
    int getNumberOfProducts(String query) throws DaoException;
    List<ProductDTO> getAllProducts(HttpServletRequest request, String endQuery) throws DaoException, ServiceException;
    Set<Map.Entry<Integer, String>> getAllCategories(HttpServletRequest request) throws DaoException, ServiceException;
}
