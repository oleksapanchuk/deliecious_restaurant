package ua.deliciousrestaurant.model.dao;

import ua.deliciousrestaurant.model.dao.mysql.MySqlDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory instance;

    public static synchronized DaoFactory getInstance() {
        if (instance == null) {
            instance = new MySqlDaoFactory();
        }
        return instance;
    }

    protected DaoFactory () { }

    public abstract ClientDAO getClientDAO();
    public abstract ProductDAO getProductDAO();
    public abstract OrderDAO getOrderDAO();

}

