package ua.deliciousrestaurant.model.dao.mysql;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.model.connection.DataSource;
import ua.deliciousrestaurant.model.dao.DaoFactory;
import ua.deliciousrestaurant.model.dao.OrderDAO;
import ua.deliciousrestaurant.model.entity.Cart;
import ua.deliciousrestaurant.model.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.deliciousrestaurant.constant.DBConstant.*;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean insertOrder(Order order) throws DaoException {

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_ORDER, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setInt(1, order.getClientId());
            pst.setInt(2, order.getStatusId());
            pst.setInt(3, order.getOrderTotalPrice());
            pst.setString(4, order.getAddressDelivery());
            pst.setString(5, order.getDate());
            pst.setInt(6, order.isOrderLiked() ? 1 : 0);

            if (pst.executeUpdate() > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        order.setOrderId(rs.getInt(1));
                    }
                }

            } else {
                System.out.println("ERROR");
                return false;
            }

            if (!insertProductsIntoOrder(order)) {
                System.out.println("ERROR");
                return false;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return true;
    }

    @Override
    public List<Order> getClientOrders(int id) throws DaoException {

        List<Order> orders = new ArrayList<>();

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(GET_CLIENT_ORDERS)) {
            ;
            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    orders.add(Order.builder()
                            .orderId(rs.getInt(1))
                            .clientId(rs.getInt(2))
                            .statusId(rs.getInt(3))
                            .addressDelivery(rs.getString(4))
                            .isOrderLiked(rs.getInt(5) == 1)
                            .date(rs.getString(7))
                            .build());
                }
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return orders;
    }

    @Override
    public List<Order> getOrderByQuery(String query) throws DaoException {
        List<Order> orderList = new ArrayList<>();

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    orderList.add(Order.builder()
                            .orderId(rs.getInt(1))
                            .clientId(rs.getInt(2))
                            .statusId(rs.getInt(3))
                            .addressDelivery(rs.getString(4))
                            .isOrderLiked(rs.getInt(5) == 1)
                            .date(rs.getString(7))
                            .build());
                }
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return orderList;
    }

    @Override
    public int getNumberRecordsByQuery(String query) throws DaoException {

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return 0;
    }

    @Override
    public List<Cart> getProductsFromOrder(int orderId) throws DaoException {

        List<Cart> listCart = new ArrayList<>();

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(GET_PRODUCTS_FROM_ORDER)) {

            pst.setInt(1, orderId);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    listCart.add(Cart.builder()
                            .product(DaoFactory.getInstance().getProductDAO().getProductById(rs.getInt(2)).get())
                            .quantity(rs.getInt(3))
                            .build());
                }
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return listCart;
    }

    @Override
    public boolean updateLikedStatus(int orderId, int isLikedStatus) throws DaoException {

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_LIKED_STATUS_ORDER)) {

            pst.setInt(1, isLikedStatus);
            pst.setInt(2, orderId);

            if (pst.executeUpdate() > 0) return true;

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    private boolean insertProductsIntoOrder(Order order) {

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_PRODUCT_INTO_ORDER)) {

            for (Cart cart : order.getOrderProducts()) {
                pst.setInt(1, order.getOrderId());
                pst.setInt(2, cart.getProduct().getIdProduct());
                pst.setInt(3, cart.getQuantity());

                if (pst.executeUpdate() <= 0) {
                    return false;
                }

                pst.clearParameters();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

}
