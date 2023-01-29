package ua.deliciousrestaurant.model.dao.mysql;

import ua.deliciousrestaurant.model.dao.ClientDAO;
import ua.deliciousrestaurant.model.dao.DaoFactory;
import ua.deliciousrestaurant.model.dao.OrderDAO;
import ua.deliciousrestaurant.model.dao.ProductDAO;

public class MySqlDaoFactory extends DaoFactory {

    private ClientDAO clientDAO;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;

    @Override
    public ClientDAO getClientDAO() {
        if (clientDAO == null) {
            clientDAO = new ClientDAOImpl();
        }
        return clientDAO;
    }

    @Override
    public ProductDAO getProductDAO() {
        if (productDAO == null) {
            productDAO = new ProductDAOImpl();
        }
        return productDAO;
    }

    @Override
    public OrderDAO getOrderDAO() {
        if (orderDAO == null) {
            orderDAO = new OrderDAOImpl();
        }
        return orderDAO;
    }
}
