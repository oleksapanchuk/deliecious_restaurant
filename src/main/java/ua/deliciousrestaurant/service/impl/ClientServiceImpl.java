package ua.deliciousrestaurant.service.impl;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.exception.se.NoSuchClientException;
import ua.deliciousrestaurant.exception.se.WrongPasswordException;
import ua.deliciousrestaurant.model.dao.DaoFactory;
import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.model.entity.Client;
import ua.deliciousrestaurant.model.entity.Role;
import ua.deliciousrestaurant.service.ClientService;
import ua.deliciousrestaurant.utils.ConvertorUtil;
import ua.deliciousrestaurant.utils.PasswordCode;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    @Override
    public List<ClientDTO> getAllClients() throws DaoException {
        return DaoFactory.getInstance().getClientDAO().getAllClients();
    }

    @Override
    public ClientDTO login(String email, String password) throws ServiceException {
        ClientDTO clientDTO;

        try {
            Client client = DaoFactory.getInstance().getClientDAO().getClientByEmail(email).orElseThrow(NoSuchClientException::new);

            if (!PasswordCode.verify(client.getPassword(), password)) {
                throw new WrongPasswordException();
            }

            clientDTO = ConvertorUtil.convertUserToDTO(client);

            if (client.getRole().equals(Role.CLIENT)) {
                addInfoToClient(clientDTO);
            }

        } catch (WrongPasswordException | DaoException e) {
            throw new WrongPasswordException();
        }

        return clientDTO;
    }

    @Override
    public void addInfoToClient(ClientDTO clientDTO) throws ServiceException {
        updateWalletBalance(clientDTO);
        updateNumberOfOrder(clientDTO);
        updateTotalFundsSpent(clientDTO);
    }

    public void updateWalletBalance(ClientDTO clientDTO) throws ServiceException {
        try {
            clientDTO.setWalletBalance(DaoFactory.getInstance().getClientDAO().getCurrentWalletBalance(clientDTO.getClientId()));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateNumberOfOrder(ClientDTO clientDTO) throws ServiceException {
        try {
            clientDTO.setTotalOrders(DaoFactory.getInstance().getClientDAO().getNumberOfOrder(clientDTO.getClientId()));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateTotalFundsSpent(ClientDTO clientDTO) throws ServiceException {
        try {
            clientDTO.setTotalFundsSpent(DaoFactory.getInstance().getClientDAO().getTotalFundsSpent(clientDTO.getClientId()));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addFundsToWallet(int clientId, int funds) throws ServiceException {
        try {
            int currentBalance = DaoFactory.getInstance().getClientDAO().getCurrentWalletBalance(clientId);
            DaoFactory.getInstance().getClientDAO().addFundsToWallet(clientId, funds + currentBalance);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkEmailUniq(String email) throws ServiceException {
        try {
            return DaoFactory.getInstance().getClientDAO().isEmailUniq(email);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
