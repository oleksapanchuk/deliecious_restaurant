<%@ page import="java.util.List" %>
<%@ page import="ua.deliciousrestaurant.model.entity.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ua.deliciousrestaurant.model.dao.DaoFactory" %>
<%@ page import="ua.deliciousrestaurant.service.ServiceFactory" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="includes/head.jsp" %>
    <title><fmt:message key="cart"/></title>
</head>

<body>

<jsp:include page="includes/header.jsp"/>

<c:if test="${ cart_list.size() <= 0 || cart_list == null }">
    <div class="container min-size-1">
        <div class="justify-content-center align-items-center" style="height:100px;">
            <h1 class="text-danger text-center mt-5">You have not selected anything yet!</h1>
            <a class="btn btn-xl btn-warning text-white text-center mt-5" href="controller?action=view-menu&sort-field=p.category_id&sort-order=asc&category-filter-id=0&offset=0&records=8&cur_page=1"><fmt:message key="menu"/></a>
        </div>
    </div>
</c:if>

<c:if test="${ cart_items.size() > 0}">
    <div class="container min-size-1">
        <div class="d-flex py-3 ">
            <h3>
                <fmt:message key="total.price"/>${ requestScope.totalPrice > 0 ? requestScope.totalPrice : 0 } <fmt:message
                    key="currency"/>
            </h3>
            <a class="mx-3 btn btn-primary" href="controller?action=order-all">
                <fmt:message key="check.out"/>
            </a>
        </div>

        <table class="table table-light">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col"><fmt:message key="name"/></th>
                <th scope="col"><fmt:message key="category"/></th>
                <th scope="col"><fmt:message key="price"/></th>
                <th scope="col" class="text-center"><fmt:message key="quantity"/></th>
                <th scope="col" class="text-center"><fmt:message key="total"/></th>
                <th scope="col" class="text-center"><fmt:message key="buy.now"/></th>
                <th scope="col" class="text-center"><fmt:message key="cancel"/></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="cart" items="${ requestScope.cart_items }">
                <tr>
                    <td><img class="card-img-to rounded-circle" src="${ cart.getProduct().getImgProduct() }"
                             alt="Card image cap" style="height:50px;width: 50px"></td>
                    <td><c:out value="${ cart.getProduct().getNameProduct() }"/></td>
                    <td><c:out value="${ cart.getProduct().getCategoryName() }"/></td>
                    <td><c:out value="${ cart.getProduct().getCostProduct() }"/> <fmt:message key="currency"/></td>
                    <td>
                        <form action="order-all" method="post" class="form-inline">
                            <input type="hidden" name="id" value="${ cart.getQuantity() }" class="form-input">
                            <div class="form-group d-flex justify-content-center">
                                <a class="btn btn-decre"
                                   href="controller?action=inc-dec-quantity&operation=inc&id=${ cart.getProduct().getIdProduct() }"><i
                                        class="fas fa-minus-square"></i></a>
                                <input type="text" name="quantity" class="form-control w-75 text-center"
                                       value="${ cart.getQuantity() }" readonly>
                                <a class="btn btn-incre"
                                   href="controller?action=inc-dec-quantity&operation=inc&id=${ cart.getProduct().getIdProduct() }"><i
                                        class="fas fa-plus-square"></i></a>
                            </div>
                        </form>
                    </td>
                    <td class="text-center"><c:out
                            value="${ cart.getProduct().getCostProduct() * cart.getQuantity() }"/> <fmt:message
                            key="currency"/></td>
                    <td class="text-center">
                        <a class="btn btn-warning"
                           href="controller?action=order-now&prod-id=${ cart.getProduct().getIdProduct() }&quantity=${ cart.getQuantity() }">Buy</a>
                    </td>
                    <td class="text-center">
                        <a class="btn btn-danger"
                           href="controller?action=remove-from-cart&id=${ cart.getProduct().getIdProduct() }">Remove</a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>

    </div>
</c:if>

<jsp:include page="includes/footer.jsp"/>

</body>
</html>
