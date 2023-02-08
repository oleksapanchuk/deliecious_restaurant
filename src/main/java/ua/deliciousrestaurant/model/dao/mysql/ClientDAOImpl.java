package ua.deliciousrestaurant.model.dao.mysql;


import ua.deliciousrestaurant.constant.DBConstant;
import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.model.connection.DataSource;
import ua.deliciousrestaurant.model.dao.ClientDAO;
import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.model.entity.Client;
import ua.deliciousrestaurant.model.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ua.deliciousrestaurant.constant.DBConstant.GET_ALL_CLIENTS;
import static ua.deliciousrestaurant.constant.DBConstant.INSERT_CLIENT;

public class ClientDAOImpl implements ClientDAO {


    @Override
    public int addClient(Client client) throws DaoException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_CLIENT, PreparedStatement.RETURN_GENERATED_KEYS)) {

            prepStatementInsUpd(client, ps);

            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return 0;
    }

    @Override
    public boolean updateClient(Client client) throws DaoException {

        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(DBConstant.UPDATE_CLIENT)) {

            prepStatementInsUpd(client, ps);
            ps.setInt(8, client.getClientId());

            if (ps.executeUpdate() >= 1) return true;

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    @Override
    public boolean deleteClient(String email) throws DaoException {

        Connection con = null;
        try {

            con = DataSource.getConnection();

            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(DBConstant.DELETE_CLIENT);

            ps.setString(1, email);

            if (ps.executeUpdate() == 1) {

                con.commit();
                con.setAutoCommit(true);

                return true;
            }


        } catch (SQLException e) {
            DataSource.rollback(Objects.requireNonNull(con));
        } finally {
            DataSource.close(Objects.requireNonNull(con));
        }
        return false;
    }

    @Override
    public List<ClientDTO> getAllClients() throws DaoException {
        List<ClientDTO> clientsDTOList = new ArrayList<>();

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(GET_ALL_CLIENTS)) {

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    clientsDTOList.add(ClientDTO.builder()
                            .clientId(rs.getInt(1))
                            .role((rs.getInt(2) == 1 ? Role.CLIENT : Role.MANAGER))
                            .email(rs.getString(3))
                            .firstName(rs.getString(5))
                            .lastName(rs.getString(6))
                            .build());
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clientsDTOList;
    }

    @Override
    public Optional<Client> getClientByEmail(String email) throws DaoException {

        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(DBConstant.FIND_CLIENT_BY_EMAIL)) {

            ps.setString(1, email);
            ps.executeQuery();

            try (ResultSet rs = ps.getResultSet()) {
                if (rs.next()) {
                    Optional<Client> client = createClient(rs);
                    if (client.isPresent()) {
                        return client;
                    }
                    throw new SQLException();
                }
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public int getNumberOfOrder(int clientId) throws DaoException {

        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(DBConstant.GET_CLIENT_TOTAL_ORDERS)) {

            ps.setInt(1, clientId);

            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return rs.getInt("total_orders");
                }
                throw new SQLException();
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int getTotalFundsSpent(int clientId) throws DaoException {

        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(DBConstant.GET_CLIENT_TOTAL_FUNDS_SPENT)) {

            ps.setInt(1, clientId);

            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return rs.getInt("total_sum");
                }
                throw new SQLException();
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int getCurrentWalletBalance(int clientId) throws DaoException {

        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(DBConstant.GET_CLIENT_CURRENT_BALANCE)) {

            ps.setInt(1, clientId);

            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                throw new SQLException();
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean addFundsToWallet(int clientId, int funds) throws DaoException {

        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(DBConstant.UPDATE_CLIENT_WALLET)) {

            ps.setInt(1, funds);
            ps.setInt(2, clientId);

            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                throw new DaoException();
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateNotificationState(int clientId, boolean isEnable) throws DaoException {

        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(DBConstant.UPDATE_CLIENT_NOTIFICATION)) {

            ps.setInt(1, isEnable ? 1 : 0);
            ps.setInt(2, clientId);

            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                throw new DaoException();
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean isEmailUniq(String email) throws DaoException {

        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(DBConstant.CHECK_EMAIL_UNIQ)) {

            ps.setString(1, email);

           return !ps.executeQuery().next();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Optional<Client> createClient(ResultSet rs) throws SQLException {

        return Optional.of(
                Client.builder()
                        .clientId(rs.getInt(1))
                        .role(rs.getInt(2) == 1 ? Role.CLIENT : Role.MANAGER)
                        .email(rs.getString(3))
                        .password(rs.getString(4))
                        .firstName(rs.getString(5))
                        .lastName(rs.getString(6))
                        .address(rs.getString(7))
                        .phoneNum(rs.getString(8))
                        .build()
        );
    }

    private void prepStatementInsUpd(Client client, PreparedStatement ps) throws SQLException {
        ps.setInt(1, client.getRole().compareTo(Role.CLIENT) == 0 ? 2 : 1);
        ps.setString(2, client.getEmail());
        ps.setString(3, client.getPassword());
        ps.setString(4, client.getFirstName());
        ps.setString(5, client.getLastName());
        ps.setString(6, client.getAddress());
        ps.setString(7, client.getPhoneNum());
    }
}
