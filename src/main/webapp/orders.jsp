<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="includes/head.jsp" %>
    <title><fmt:message key="log.in"/></title>
</head>

<body>

<jsp:include page="includes/header.jsp"/>

<div class="container">
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col" class="text-center">ID Order</th>
            <th scope="col">Date</th>
            <th scope="col">Total Price</th>
            <th scope="col" class="text-center">Details</th>
            <th scope="col" class="text-center">Liked</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="order" items="${ sessionScope.order_list }">
            <tr>
                <td class="text-center"><c:out value="${ order.getOrderId() }"/></td>
                <td><c:out value="${ order.getDate() }"/></td>
                <td><c:out value="${ order.getOrderTotalPrice() }"/> <fmt:message key="currency"/></td>
                <td class="text-center">
                    <a class="btn btn-warning" href="#">Show more</a>
                </td>
                <td class="text-center">
                    <c:choose>
                        <c:when test="${ order.isOrderLiked() }">
                            <a class="btn btn-danger" href="controller?action=set-like-for-order&order-id=${ order.getOrderId() }&is-liked-order=true">
                                <i class="fa-solid fa-heart"></i>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-secondary" href="controller?action=set-like-for-order&order-id=${ order.getOrderId() }&is-liked-order=false">
                                <i class="fa-regular fa-heart"></i>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>


<jsp:include page="includes/footer.jsp"/>

</body>
</html>
