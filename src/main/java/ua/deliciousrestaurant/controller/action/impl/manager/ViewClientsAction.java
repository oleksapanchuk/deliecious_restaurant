package ua.deliciousrestaurant.controller.action.impl.manager;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.service.ServiceFactory;
import ua.deliciousrestaurant.utils.query.QueryBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static ua.deliciousrestaurant.constant.ActionConstant.*;

public class ViewClientsAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String sortField = request.getParameter(SORT_FIELD);
        String sortOrder = request.getParameter(SORT_ORDER);
//        int offset = Integer.parseInt(request.getParameter(OFFSET));
//        int records = Integer.parseInt(request.getParameter(RECORDS));

        QueryBuilder queryBuilder = new QueryBuilder()
                .setSortField(sortField)
                .setOrder(sortOrder);
//                .setOffset(offset)
//                .setRecords(records);

        try {
            List<ClientDTO> clients = ServiceFactory.getInstance().getClientService().getAllClients();
            System.out.println(clients);
//            int numberOfProducts = ServiceFactory.getInstance().getProductService().getNumberOfProducts(SELECT_PRODUCT_NUMBER + queryBuilder.getQueryNoLimits());

            request.getSession().setAttribute("clients", clients);

            request.getSession().setAttribute(SORT_FIELD, sortField);
            request.getSession().setAttribute(SORT_ORDER, sortOrder);

//            paginate(request, numberOfProducts);

        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        return PAGE_CLIENTS;
    }
}
