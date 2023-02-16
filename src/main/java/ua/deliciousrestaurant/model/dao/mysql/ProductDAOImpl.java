package ua.deliciousrestaurant.model.dao.mysql;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.model.connection.DataSource;
import ua.deliciousrestaurant.model.dao.ProductDAO;
import ua.deliciousrestaurant.model.dto.ProductDTO;
import ua.deliciousrestaurant.model.entity.Cart;
import ua.deliciousrestaurant.model.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static ua.deliciousrestaurant.constant.DBConstant.*;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public List<ProductDTO> getAllProduct(String query) throws DaoException {

        List<ProductDTO> products = new ArrayList<>();

        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            System.out.println(query);
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
    public Optional<Product> getProductById(int productId, String locale) throws DaoException {
        String query;
        if (locale == null || locale.equals("en")) {
            query = GET_PRODUCT_BY_ID_ENG;
        } else {
            query = GET_PRODUCT_BY_ID_UA;
        }

        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(Product.builder()
                            .idProduct(rs.getInt(1))
                            .nameProduct(rs.getString(2))
                            .categoryName(rs.getString(3))
                            .costProduct(rs.getInt(4))
                            .weightProduct(rs.getInt(5))
                            .imgProduct(rs.getString(6))
                            .build());
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

    @Override
    public Map<Integer, String> getMapCategories(String locale) throws DaoException {
        Map<Integer, String> mapCategories = new HashMap<>();

        String query = locale.equals("en") ? GET_ALL_CATEGORIES_ENG : GET_ALL_CATEGORIES_UA;

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    mapCategories.put(rs.getInt(1), rs.getString(2));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return mapCategories;
    }


    private ProductDTO productBuilder(ResultSet rs) throws SQLException {
        return ProductDTO.builder()
                .idProduct(rs.getInt(1))
                .nameProduct(rs.getString(2))
                .categoryName(rs.getString(3))
                .costProduct(rs.getInt(4))
                .weightProduct(rs.getInt(5))
                .imgProduct(rs.getString(6))
                .categoryId(rs.getInt(7))
                .build();
    }
}
