package ua.deliciousrestaurant.service;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClients() throws DaoException;
    ClientDTO login(String email, String password) throws ServiceException;
}
