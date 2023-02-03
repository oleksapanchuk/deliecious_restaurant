package ua.deliciousrestaurant.service.impl;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.connection.DataSource;
import ua.deliciousrestaurant.model.dao.DaoFactory;
import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.model.dto.ProductDTO;
import ua.deliciousrestaurant.model.entity.Cart;
import ua.deliciousrestaurant.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static ua.deliciousrestaurant.utils.query.QueryConstant.SELECT_PRODUCT_ENG;
import static ua.deliciousrestaurant.utils.query.QueryConstant.SELECT_PRODUCT_UA;

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

    @Override
    public int getNumberOfProducts(String query) throws DaoException {

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    System.out.println(rs.getInt("total"));
                    return rs.getInt("total");
                }
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return 0;
    }

    @Override
    public List<ProductDTO> getAllProducts(HttpServletRequest request, String endQuery) throws DaoException, ServiceException {
        return DaoFactory.getInstance().getProductDAO().getAllProduct((getLocale(request).equals("en") ? SELECT_PRODUCT_ENG : SELECT_PRODUCT_UA) + endQuery);
    }

    @Override
    public Set<Map.Entry<Integer, String>> getAllCategories(HttpServletRequest request) throws DaoException, ServiceException {
        return DaoFactory.getInstance().getProductDAO().getMapCategories(getLocale(request)).entrySet();
    }

    private String getLocale(HttpServletRequest request) throws ServiceException {
        if (request.getSession().getAttribute("locale") == null) {
            throw new ServiceException();
        }
        return ((String) request.getSession().getAttribute("locale")).equals("en") ? "en" : "uk_UA";
    }


}
