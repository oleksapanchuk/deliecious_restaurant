package ua.deliciousrestaurant.service;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClients() throws DaoException;
    ClientDTO login(String email, String password) throws ServiceException;

    void addInfoToClient(ClientDTO clientDTO) throws ServiceException;
    void updateWalletBalance(ClientDTO clientDTO) throws ServiceException;
    void updateNumberOfOrder(ClientDTO clientDTO) throws ServiceException;
    void updateTotalFundsSpent(ClientDTO clientDTO) throws ServiceException;

    void addFundsToWallet(int clientId, int funds) throws ServiceException;

    boolean checkEmailUniq(String email) throws ServiceException;
}
