package ua.deliciousrestaurant.service;

import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.dto.ClientDTO;

public interface ClientService {
    ClientDTO login(String email, String password) throws ServiceException;
}
