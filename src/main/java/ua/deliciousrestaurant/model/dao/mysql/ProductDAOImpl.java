package ua.deliciousrestaurant.model.dao.mysql;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.model.connection.DataSource;
import ua.deliciousrestaurant.model.dao.ProductDAO;
import ua.deliciousrestaurant.model.entity.Cart;
import ua.deliciousrestaurant.model.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.deliciousrestaurant.constant.DBConstant.GET_PRODUCT_BY_ID;
import static ua.deliciousrestaurant.constant.DBConstant.GET_TOTAL_PRICE;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public List<Product> getAllProduct(String query) throws DaoException {

        List<Product> products = new ArrayList<>();

        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.executeQuery();

            try (ResultSet rs = ps.getResultSet()) {
                while (rs.next()) {
                    products.add(productBuilder(rs));
                }
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return products;
    }

    @Override
    public Optional<Product> getProductById(int productId) throws DaoException {

        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_PRODUCT_BY_ID)) {

            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(productBuilder(rs));
                }
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return Optional.empty();
    }

    public int getTotalCartPrice(List<Cart> cartList) throws DaoException {
        int totalPrice = 0;

        if (cartList == null || cartList.isEmpty()) return totalPrice;

        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_TOTAL_PRICE)) {

            for (Cart cart : cartList) {
                ps.setInt(1, cart.getProduct().getIdProduct());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        totalPrice += rs.getInt(1) * cart.getQuantity();
                    }
                }
                ps.clearParameters();
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return totalPrice;
    }


    private Product productBuilder(ResultSet rs) throws SQLException {
        return Product.builder()
                .idProduct(rs.getInt(1))
                .nameProduct(rs.getString(2))
                .categoryName(rs.getString(3))
                .costProduct(rs.getInt(4))
                .weightProduct(rs.getInt(5))
                .imgProduct(rs.getString(6))
                .build();
    }
}
