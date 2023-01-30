package ua.deliciousrestaurant.controller.action.impl;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.dao.DaoFactory;
import ua.deliciousrestaurant.model.dto.ProductDTO;
import ua.deliciousrestaurant.service.ServiceFactory;
import ua.deliciousrestaurant.utils.query.QueryBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static ua.deliciousrestaurant.constant.ActionConstant.*;
import static ua.deliciousrestaurant.utils.PaginationUtil.paginate;
import static ua.deliciousrestaurant.utils.query.QueryConstant.SELECT_PRODUCT;
import static ua.deliciousrestaurant.utils.query.QueryConstant.SELECT_PRODUCT_NUMBER;

public class ViewMenuAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
       String sortField = request.getParameter(SORT_FIELD);
       String sortOrder = request.getParameter(SORT_ORDER);
       int categoryFilter = Integer.parseInt(request.getParameter(CATEGORY_FILTER));
       int offset = Integer.parseInt(request.getParameter(OFFSET));
       int records = Integer.parseInt(request.getParameter(RECORDS));

       QueryBuilder queryBuilder = new QueryBuilder(sortField)
               .setOrder(sortOrder)
               .setOffset(offset)
               .setRecords(records);

       if (categoryFilter > 0) {
           queryBuilder.setProductCategoryFilter(categoryFilter);
       }

        try {
            List<ProductDTO> products = DaoFactory.getInstance().getProductDAO().getAllProduct(SELECT_PRODUCT+ queryBuilder.getQuery());
            int numberOfProducts = ServiceFactory.getInstance().getProductService().getNumberOfProducts(SELECT_PRODUCT_NUMBER + queryBuilder.getQueryNoLimits());

            request.getSession().setAttribute("products", products);

            paginate(request, numberOfProducts);

        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        return MENU_PAGE;
    }
}
