<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <%@include file="includes/head.jsp" %>
    <title><fmt:message key="mng.orders"/></title>
</head>
<body>

<jsp:include page="includes/header.jsp"/>

<section class="vh-200 section-style">
    <div class="container h-100 container-style" style="min-height: 400px;">
        <div class="row my-2">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col" class="text-center"><fmt:message key="order.id"/></th>
                    <th scope="col"><fmt:message key="order.date"/></th>
                    <th scope="col" class="text-center"><fmt:message key="order.total.price"/></th>
                    <th scope="col" class="text-center"><fmt:message key="order.status"/></th>
                    <th scope="col" class="text-center"><fmt:message key="order.details"/></th>
                    <th scope="col" class="text-center"><fmt:message key="order.liked"/></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="order" items="${ sessionScope.order_list }">
                    <tr>
                        <td class="text-center"><c:out value="${ order.getOrderId() }"/></td>
                        <td><c:out value="${ order.getDate() }"/></td>
                        <td><c:out value="${ order.getOrderTotalPrice() }"/> <fmt:message key="currency"/></td>
                        <td class="text-center">
                            <c:choose>

                                <c:when test="${ order.getStatusId() == 1 }">
                                    <div class="text-dark">
                                        <i class="far fa-dot-circle"> </i>
                                        <fmt:message key="order.status.ordered"/>
                                    </div>
                                </c:when>

                                <c:when test="${ order.getStatusId() == 2 }">
                                    <div class="text-info">
                                        <i class="far fa-dot-circle"> </i>
                                        <fmt:message key="order.status.preparing"/>
                                    </div>
                                </c:when>

                                <c:when test="${ order.getStatusId() == 3 }">
                                    <div class="text-warning">
                                        <i class="far fa-dot-circle"> </i>
                                        <fmt:message key="order.status.delivering"/>
                                    </div>
                                </c:when>

                                <c:when test="${ order.getStatusId() == 4 }">
                                    <div class="text-success">
                                        <i class="far fa-dot-circle"> </i>
                                        <fmt:message key="order.status.delivered"/>
                                    </div>
                                </c:when>

                                <c:otherwise>
                                    <div class="text-danger">
                                        <i class="far fa-dot-circle"> </i>
                                        <fmt:message key="order.status.canceled"/>
                                    </div>
                                </c:otherwise>

                            </c:choose>

                        </td>
                        <td class="text-center">
                            <a class="btn btn-warning" href="#"><fmt:message key="order.show.more"/></a>
                        </td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${ order.isOrderLiked() }">
                                    <a class="btn btn-danger"
                                       href="controller?action=set-like-for-order&order-id=${ order.getOrderId() }&is-liked-order=true">
                                        <i class="fa-solid fa-heart"></i>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a class="btn btn-secondary"
                                       href="controller?action=set-like-for-order&order-id=${ order.getOrderId() }&is-liked-order=false">
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
    </div>
</section>


<c:set var="href" scope="request"
       value="controller?action=view-orders-for-managers&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&client_id_filter=${ sessionScope.client_id_filter }&order_status=${ sessionScope.order_status }&"/>
<jsp:include page="includes/pagination.jsp"/>

<jsp:include page="includes/footer.jsp"/>

</body>
</html>
