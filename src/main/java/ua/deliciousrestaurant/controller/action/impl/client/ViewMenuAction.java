package ua.deliciousrestaurant.controller.action.impl.client;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.dao.DaoFactory;
import ua.deliciousrestaurant.model.dto.ProductDTO;
import ua.deliciousrestaurant.model.entity.Cart;
import ua.deliciousrestaurant.model.entity.Product;
import ua.deliciousrestaurant.service.ServiceFactory;
import ua.deliciousrestaurant.utils.query.QueryBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static ua.deliciousrestaurant.constant.ActionConstant.*;
import static ua.deliciousrestaurant.utils.PaginationUtil.paginate;
import static ua.deliciousrestaurant.utils.query.QueryConstant.SELECT_PRODUCT_NUMBER;

public class ViewMenuAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
       String sortField = request.getParameter(SORT_FIELD);
       String sortOrder = request.getParameter(SORT_ORDER);
       int categoryFilter = Integer.parseInt(request.getParameter(CATEGORY_FILTER));
       int offset = Integer.parseInt(request.getParameter(OFFSET));
       int records = Integer.parseInt(request.getParameter(RECORDS));
       String searchedText = request.getParameter(SEARCH_FIELD);
       String locale = (String) request.getSession().getAttribute(LOCALE);

       QueryBuilder queryBuilder = new QueryBuilder()
               .setSortField(sortField)
               .setOrder(sortOrder)
               .setOffset(offset)
               .setRecords(records);

       if (categoryFilter > 0) {
           queryBuilder.setProductCategoryFilter(categoryFilter);
       }

        if (searchedText != null) {
            if (!searchedText.equals("") && !searchedText.equals("NoNe")) {
                if (locale == null || locale.equals("en")) {
                    queryBuilder.setFindTextFilter(" prod_eng_name ", searchedText);
                } else {
                    queryBuilder.setFindTextFilter(" prod_ua_name ", searchedText);
                }
            }
        }

        if (request.getParameter(PRODUCT_ID) != null && !request.getParameter(PRODUCT_ID).equals("none")) {
            addToCart(request);
        }

        try {
            List<ProductDTO> products = ServiceFactory.getInstance().getProductService().getAllProducts(request, queryBuilder.getQuery());
            int numberOfProducts = ServiceFactory.getInstance().getProductService().getNumberOfProducts(SELECT_PRODUCT_NUMBER + queryBuilder.getQueryNoLimits());
            Set<Map.Entry<Integer, String>> mapCategories = ServiceFactory.getInstance().getProductService().getAllCategories(request);

            request.getSession().setAttribute("products", products);
            request.getSession().setAttribute("categories", mapCategories);

            request.getSession().setAttribute(SORT_FIELD, sortField);
            request.getSession().setAttribute(SORT_ORDER, sortOrder);
            request.getSession().setAttribute(SEARCH_FIELD, searchedText);
            request.getSession().setAttribute(CATEGORY_FILTER, categoryFilter);

            paginate(request, numberOfProducts);

        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        return MENU_PAGE;
    }

    void addToCart(HttpServletRequest request) {

        int prodId = Integer.parseInt(request.getParameter(PRODUCT_ID));
        Cart product = Cart.builder()
                .product(Product.builder().idProduct(prodId).build())
                .quantity(1)
                .build();

        List<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute(CART_LIST);
        if (cartList == null) {
            cartList = new ArrayList<>();
        } else {
            for (Cart c : cartList) {
                if (c.getProduct().getIdProduct() == prodId) {
                    request.setAttribute("isAlreadyAdded", true);
                    return;
                }
            }
        }

        cartList.add(product);
        request.getSession().setAttribute(CART_LIST, cartList);
    }

}
