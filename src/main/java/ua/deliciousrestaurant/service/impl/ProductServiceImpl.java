package ua.deliciousrestaurant.service.impl;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.model.connection.DataSource;
import ua.deliciousrestaurant.model.dao.DaoFactory;
import ua.deliciousrestaurant.model.entity.Cart;
import ua.deliciousrestaurant.service.ProductService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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


}
