package ua.deliciousrestaurant.service;

import ua.deliciousrestaurant.service.impl.ClientServiceImpl;
import ua.deliciousrestaurant.service.impl.OrderServiceImpl;
import ua.deliciousrestaurant.service.impl.ProductServiceImpl;

public final class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ClientService clientService;
    private OrderService orderService;
    private ProductService productService;

    private ServiceFactory() { }

    public static synchronized ServiceFactory getInstance() {
        if (serviceFactory != null) {
            serviceFactory = new ServiceFactory();
            serviceFactory.clientService = new ClientServiceImpl();
            serviceFactory.orderService = new OrderServiceImpl();
            serviceFactory.productService = new ProductServiceImpl();
        }
        return serviceFactory;
    }

    public ClientService getClientService() { return clientService; }
    public OrderService getOrderService() { return orderService; }
    public ProductService getProductService() { return productService; }
}
