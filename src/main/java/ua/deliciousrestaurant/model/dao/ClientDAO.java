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

}
