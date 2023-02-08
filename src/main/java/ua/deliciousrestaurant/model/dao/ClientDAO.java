package ua.deliciousrestaurant.model.dao;


import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.model.entity.Client;
import ua.deliciousrestaurant.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface ClientDAO {

    int addClient(Client client) throws DaoException;
    boolean updateClient(Client client) throws DaoException;
    boolean deleteClient(String email) throws DaoException;
    List<ClientDTO> getAllClients() throws DaoException;
    Optional<Client> getClientByEmail(String email) throws DaoException;
    int getNumberOfOrder(int clientId) throws DaoException;
    int getTotalFundsSpent(int clientId) throws DaoException;
    int getCurrentWalletBalance(int clientId) throws DaoException;
    boolean addFundsToWallet(int clientId, int funds) throws DaoException;
    boolean updateNotificationState(int clientId, boolean isEnable) throws DaoException;
    boolean isEmailUniq(String email) throws DaoException;

}
