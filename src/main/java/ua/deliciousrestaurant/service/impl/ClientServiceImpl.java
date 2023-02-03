package ua.deliciousrestaurant.service.impl;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.exception.se.NoSuchClientException;
import ua.deliciousrestaurant.exception.se.WrongPasswordException;
import ua.deliciousrestaurant.model.dao.DaoFactory;
import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.model.entity.Client;
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

            if (PasswordCode.verify(client.getPassword(), password)) {
                clientDTO = ConvertorUtil.convertUserToDTO(client);
            } else {
                throw new WrongPasswordException();
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return clientDTO;
    }
}
