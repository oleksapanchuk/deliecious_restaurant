package ua.deliciousrestaurant.service.impl;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.dao.DaoFactory;
import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.model.entity.Client;
import ua.deliciousrestaurant.service.ClientService;
import ua.deliciousrestaurant.utils.ConvertorUtil;
import ua.deliciousrestaurant.utils.PasswordCode;

import java.util.Optional;

public class ClientServiceImpl implements ClientService {

    @Override
    public ClientDTO login(String email, String password) throws ServiceException {
        ClientDTO clientDTO;

        try {
            Optional<Client> clientOptional = DaoFactory.getInstance().getClientDAO().getClientByEmail(email);
            if (clientOptional.isEmpty()) {
                throw new ServiceException(); // "email doesn't exist"
            }
            Client client = clientOptional.get();
            System.out.println(client.getPassword() + " " + password);
            if (PasswordCode.verify(client.getPassword(), password)) {
                clientDTO = ConvertorUtil.convertUserToDTO(client);
            } else {
                throw new ServiceException();
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return clientDTO;
    }
}
